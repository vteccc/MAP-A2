package Model.Statement;

import Model.ADT.MyDictionary;
import Model.ADT.MyInterfaceDictionary;
import Model.ADT.MyInterfaceStack;
import Model.MyException;
import Model.ProgramState;
import Model.Value.Value;

public class ForkStatement implements InterfaceStatement{
    private InterfaceStatement statement;
    public ForkStatement(InterfaceStatement statement){
        this.statement=statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ProgramState PrgClone;
        try {
                PrgClone = (ProgramState) state.clone();
        }catch (CloneNotSupportedException ex){
            throw new MyException("Clone error!!");
        }

        ProgramState forkProgram = new ProgramState(statement);
        forkProgram.setFileTable(PrgClone.getFileTable());
        forkProgram.setHeapTable(PrgClone.getHeapTable());
        forkProgram.setList(PrgClone.getList());

        forkProgram.setSymbolTable(PrgClone.getSymbolTable());
        return forkProgram;
    }

    @Override
    public String toString(){
        return "fork{"+statement.toString()+"}";
    }
}
