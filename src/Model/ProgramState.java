package Model;

import Model.ADT.*;
import Model.Statement.InterfaceStatement;
import Model.Type.Type;
import Model.Value.Value;

import java.io.BufferedReader;

public class ProgramState {
    private MyInterfaceStack<InterfaceStatement> exeStack;
    private MyInterfaceDictionary<String, Value> symbolTable;
    private MyInterfaceDictionary<String, BufferedReader> fileTable;
    private MyInterfaceList<Value> out;
    private MyInterfaceDictionary<Integer, Value> heapTable;
    private InterfaceStatement originalProgram;

    public ProgramState(InterfaceStatement IStmt) {
        exeStack = new MyStack<>();
        symbolTable = new MyDictionary<>();
        fileTable = new MyDictionary<>();
        out = new MyList<>();
        heapTable = new MyDictionary<>();
        exeStack.push(IStmt);
        //originalProgram=deepCopy(program);
    }


    public MyInterfaceStack<InterfaceStatement> getStack() {
        return exeStack;
    }

    public MyInterfaceDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public MyInterfaceDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyInterfaceDictionary<Integer, Value> getHeapTable() {
        return heapTable;
    }

    public MyInterfaceList<Value> getList() {
        return out;
    }

    public String toString() {
        return "\nExecution Stack:\n" + exeStack.toString() + "\nSymbol Table:\n" + symbolTable.toString() +
                "\nFile Table:\n" + fileTable.toString() +"\nHeap Table:\n" +heapTable.toString()+"\nOutput List:\n" +
                out.toString()
                + "\n------------------------------------------------------------------------------------------------------------------------";
    }

}
