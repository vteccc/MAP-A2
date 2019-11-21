package Controller;


import Model.ADT.MyInterfaceStack;
import Model.MyException;
import Model.ProgramState;
import Model.Statement.InterfaceStatement;
import Repository.RepoInterface;

public class Controller {
    private RepoInterface repository;

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
        }
    }

    public String display_state() throws MyException{
        ProgramState programState = repository.getCurrentProgram();
        repository.logProgramStateExecute();
        return programState.toString();
    }
}