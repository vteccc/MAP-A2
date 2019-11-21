package Model;

import Model.ADT.*;
import Model.Statement.InterfaceStatement;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;

public class ProgramState {
    private MyInterfaceStack<InterfaceStatement> exeStack;
    private MyInterfaceDictionary<String, Value> symbolTable;
    private MyInterfaceDictionary<String, BufferedReader> fileTable;
    private MyInterfaceList<Value> out;
    private InterfaceStatement originalProgram;

    public ProgramState(InterfaceStatement IStmt) {
        exeStack = new MyStack<>();
        symbolTable = new MyDictionary<>();
        fileTable = new MyDictionary<>();
        out = new MyList<>();
        exeStack.push(IStmt);
        //originalProgram=deepCopy(program);
    }


    public MyInterfaceStack<InterfaceStatement> getStack() {
        return exeStack;
    }

    public MyInterfaceDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public MyInterfaceDictionary<String, BufferedReader> getFileTable() { return fileTable; }

    public MyInterfaceList<Value> getList() {
        return out;
    }

    public String toString() {
        return "\nExecution Stack:\n"+exeStack.toString() +"\nSymbol Table:\n" +symbolTable.toString()+"\nFile Table:\n" + fileTable.toString() +"\nOutput List:\n"+ out.toString()
                +"\n------------------------------------------------------------------------------------------------------------------------";
    }

}
