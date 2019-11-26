package Model.Statement;

import Model.Exp.Exp;
import Model.MyException;
import Model.ADT.MyInterfaceList;
import Model.ProgramState;
import Model.Value.Value;

public class PrintStatement implements InterfaceStatement {
    private Exp exp;

    public PrintStatement(Exp exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceList<Value> list = state.getList();
        list.add(exp.evaluate(state.getSymbolTable()));
        return state;
    }
}
