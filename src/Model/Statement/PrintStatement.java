package Model.Statement;

import Model.Exp.Exp;
import Model.MyException;
import Model.ADT.MyInterfaceList;
import Model.ProgramState;
import Model.Value.Value;

public class PrintStatement implements InterfaceStatement {
    private Exp _exp;

    public PrintStatement(Exp exp) {
        _exp = exp;
    }

    @Override
    public String toString() {
        return "print(" + _exp.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceList<Value> list = state.getList();
        list.add(_exp.evaluate(state.getSymbolTable()));
        return state;
    }
}
