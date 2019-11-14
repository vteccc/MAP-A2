package Model.Statement;

import Model.MyException;
import Model.ADT.MyInterfaceStack;
import Model.ProgramState;

public class CompStatement implements InterfaceStatement {
    private InterfaceStatement _first;
    private InterfaceStatement _second;

    public CompStatement(InterfaceStatement first, InterfaceStatement second) {
        _first = first;
        _second = second;
    }

    @Override
    public String toString() {
        return _first.toString() + "\n" + _second.toString();
    }

    public ProgramState execute(ProgramState state){
        MyInterfaceStack<InterfaceStatement> stk = state.getStack();
        stk.push(_second);
        stk.push(_first);
        return state;
    }
}
