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

    public ProgramState execute() {
        MyInterfaceList<Value> _list = _old.getList();
        MyInterfaceDictionary<String, Value> _table = _old.getSymbolTable();
        MyInterfaceStack<InterfaceStatement> _stack = _old.getStack();
        _stack.pop();
        return new ProgramState();
    }

    @Override
    public String toString() {
        return "No operation";
    }
}
