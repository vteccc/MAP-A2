package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.Exp.Exp;
import Model.MyException;
import Model.ProgramState;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements InterfaceStatement {
    private Exp exp;

    public OpenReadFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyInterfaceDictionary<Integer,Value> heapTable = state.getHeapTable();
        if (exp.evaluate(symbolTable,heapTable).getType().toString().equals("string")) {
            if(!fileTable.isDefined(exp.evaluate(symbolTable,heapTable).toString())){
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(exp.evaluate(symbolTable,heapTable).toString()));
                    fileTable.update(exp.evaluate(symbolTable,heapTable).toString(), reader);
                } catch (FileNotFoundException e) {
                    throw new MyException("Error:File doesn't exist!");
                }
            }
            else{
                throw new MyException("Error:String variable is not declared!");
            }
        }
        else{
            throw new MyException("Error:Opening a read file requires a string as its argument!");
        }
        return null;
    }

    public String toString() {
        return "OpenReadFile(" + exp.toString() + ")";
    }
}
