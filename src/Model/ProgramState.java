package Model;

import Model.ADT.*;
import Model.Statement.InterfaceStatement;
import Model.Value.Value;

public class ProgramState {
    private MyInterfaceStack<InterfaceStatement> exeStack;
    private MyInterfaceDictionary<String, Value> symbolTable;
    private MyInterfaceList<Value> out;
    private InterfaceStatement originalProgram;

    public ProgramState() {
        exeStack = new MyStack<>();
        symbolTable = new MyDictionary<>();
        out = new MyList<>();
        //originalProgram=deepCopy(program);
    }


    public MyInterfaceStack<InterfaceStatement> getStack() {
        return exeStack;
    }

    public MyInterfaceDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public MyInterfaceList<Value> getList() {
        return out;
    }

    public String toString() {
        return exeStack.toString() + symbolTable.toString() + out.toString();
    }

}
