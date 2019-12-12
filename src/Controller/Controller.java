package Controller;

import Model.ADT.MyInterfaceStack;
import Model.MyException;
import Model.ProgramState;
import Model.Statement.InterfaceStatement;
import Model.Value.ReferenceValue;
import Model.Value.Value;
import Repository.RepoInterface;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {
    private RepoInterface repository;
    private ExecutorService executor;

    private Map<Integer, Value> garbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddresses(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> {
                    ReferenceValue v1 = (ReferenceValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    public Controller(RepoInterface repo) {
        repository = repo;
    }

    List<ProgramState> removeCompletedProgram(List<ProgramState> inProgramList){
        return inProgramList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<ProgramState> programList) throws InterruptedException {
        //print into log

        programList.forEach(prg-> {
            try {
                repository.logProgramStateExecute(prg);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });

        List<Callable<ProgramState>> callList = programList.stream()
                .map((ProgramState p) ->(Callable<ProgramState>)(()->{
                    try {
                        return p.one_step();
                    } catch (MyException e) {
                        System.out.println(e);
                    }
                    return null;
                })).collect(Collectors.toList());

        List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());

        programList.addAll(newProgramList);

        //print into log

        programList.forEach(prg-> {
            try {
                repository.logProgramStateExecute(prg);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });

        repository.setProgramList(programList);
    }

    public void complete_exe() throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList=removeCompletedProgram(repository.getProgramList());
        while(programList.size()>0){

            //GarbageCollector:
            List<Integer> concatenate = getAddresses(programList.get(0).getSymbolTable().getContent().values());
            concatenate.addAll(getAddresses(programList.get(0).getHeapTable().getContent().values()));
            programList.get(0).getHeapTable().setContent(garbageCollector(concatenate, programList.get(0).getHeapTable().getContent()));

            oneStepForAllPrograms(programList);
            programList=removeCompletedProgram(repository.getProgramList());
        }
        executor.shutdownNow();

        repository.setProgramList(programList);

    }

    public String display_state() throws MyException {
        StringBuilder result = new StringBuilder();
        for (ProgramState prg:repository.getProgramList()) {
            result.append(prg.toString());
        }
        /*
        ProgramState programState = repository.getCurrentProgram();
        repository.logProgramStateExecute();
        return programState.toString();
         */
        return result.toString();
    }
}