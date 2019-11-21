package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.ADT.MyInterfaceList;
import Model.ADT.MyInterfaceStack;
import Model.ProgramState;
import Model.Value.Value;

public class NopStatement {
    private ProgramState _old;

    public NopStatement(ProgramState old) {
        _old = old;
    }

    public ProgramState execute(ProgramState state) {
        return state;
    }

    @Override
    public String toString() {
        return "No operation";
    }
}
