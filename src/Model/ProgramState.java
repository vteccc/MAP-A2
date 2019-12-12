package Model;

import Model.ADT.*;
import Model.Statement.InterfaceStatement;
import Model.Type.Type;
import Model.Value.Value;

import java.io.BufferedReader;

public class ProgramState implements Cloneable{
    private MyInterfaceStack<InterfaceStatement> exeStack;
    private MyInterfaceDictionary<String, Value> symbolTable;
    private MyInterfaceDictionary<String, BufferedReader> fileTable;
    private MyInterfaceList<Value> out;
    private MyInterfaceDictionary<Integer, Value> heapTable;
    //private InterfaceStatement originalProgram;
    static private int counter=0;
    private int id;

    public ProgramState(InterfaceStatement IStmt) {
        counter++;
        id=counter;
        exeStack = new MyStack<>();
        symbolTable = new MyDictionary<>();
        fileTable = new MyDictionary<>();
        out = new MyList<>();
        heapTable = new MyDictionary<>();
        exeStack.push(IStmt);
        //originalProgram=deepCopy(program);
    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public int getId(){
        return id;
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

    public void setList(MyInterfaceList<Value> out){
        this.out=out;
    }

    public void setHeapTable(MyInterfaceDictionary<Integer, Value> heapTable) {
        this.heapTable=heapTable;
    }

    public void setFileTable(MyInterfaceDictionary<String, BufferedReader> fileTable) {
        this.fileTable=fileTable;
    }

    public void setSymbolTable(MyInterfaceDictionary<String, Value> symbolTable) {
        this.symbolTable=symbolTable;
    }

    public void setStack(MyInterfaceStack<InterfaceStatement> exeStack) {
        this.exeStack=exeStack;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public ProgramState one_step() throws MyException {
        if (!exeStack.isEmpty()) {
            InterfaceStatement stmt = exeStack.pop();
            return stmt.execute(this);
        } else {
            throw new MyException("Error:Program State Stack is empty!");
        }

    }

    public String toString() {
        return "\nThread number: "+id+"\nExecution Stack:\n" + exeStack.toString() + "\nSymbol Table:\n" + symbolTable.toString() +
                "\nFile Table:\n" + fileTable.toString() + "\nHeap Table:\n" + heapTable.toString() + "\nOutput List:\n" +
                out.toString()
                + "\n------------------------------------------------------------------------------------------------------------------------";
    }

}
