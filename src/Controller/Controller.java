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
import java.util.stream.Collectors;


public class Controller {
    private RepoInterface repository;

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

    public void one_step() throws MyException {
        ProgramState programState = repository.getCurrentProgram();
        MyInterfaceStack<InterfaceStatement> stk = programState.getStack();
        if (!stk.isEmpty()) {
            InterfaceStatement stmt = stk.pop();
            stmt.execute(programState);
            System.out.println(programState.toString());
            repository.logProgramStateExecute();
        } else {
            throw new MyException("Error:Program State Stack is empty!");
        }

    }

    public void complete_exe() throws MyException {
        ProgramState programState = repository.getCurrentProgram();
        MyInterfaceStack<InterfaceStatement> stk = programState.getStack();
        System.out.println(programState.toString());
        repository.logProgramStateExecute();
        while (!stk.isEmpty()) {
            InterfaceStatement stmt = stk.pop();
            stmt.execute(programState);
            System.out.println(programState.toString());
            repository.logProgramStateExecute();

            //GarbageCollector:
            List<Integer> concatenate = getAddresses(programState.getSymbolTable().getContent().values());
            concatenate.addAll(getAddresses(programState.getHeapTable().getContent().values()));
            programState.getHeapTable().setContent(garbageCollector(concatenate, programState.getHeapTable().getContent()));

            System.out.println(programState.toString());
            repository.logProgramStateExecute();
        }
    }

    public String display_state() throws MyException {
        ProgramState programState = repository.getCurrentProgram();
        repository.logProgramStateExecute();
        return programState.toString();
    }
}