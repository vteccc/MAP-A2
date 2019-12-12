package Model.Statement;

import Model.ADT.MyInterfaceStack;
import Model.ProgramState;

public class CompStatement implements InterfaceStatement {
    private InterfaceStatement first;
    private InterfaceStatement second;

    public CompStatement(InterfaceStatement first, InterfaceStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first.toString() + "\n" + second.toString();
    }

    public ProgramState execute(ProgramState state){
        MyInterfaceStack<InterfaceStatement> stk = state.getStack();
        stk.push(second);
        stk.push(first);
        return null;
    }
}
