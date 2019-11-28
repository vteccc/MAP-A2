package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.Exp.Exp;
import Model.MyException;
import Model.ProgramState;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFile implements InterfaceStatement {
    private Exp exp;

    public CloseReadFile(Exp exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyInterfaceDictionary<Integer,Value> heapTable = state.getHeapTable();
        if (exp.evaluate(symbolTable,heapTable).getType().toString().equals("string")) {
            if (fileTable.isDefined(exp.evaluate(symbolTable,heapTable).toString())) {
                try {
                    BufferedReader reader = fileTable.getValue(exp.evaluate(symbolTable,heapTable).toString());
                    reader.close();
                    fileTable.remove(exp.evaluate(symbolTable,heapTable).toString());
                } catch (IOException e) {
                    throw new MyException("Error while closing buffer!");
                }
            } else {
                throw new MyException("Error:File is not opened!");
            }
        }
        else{
            throw new MyException("Error:Closing a read file requires a string as its argument!");
        }
        return state;
    }

    public String toString() {
        return "CloseReadFile(" + exp.toString() + ")";
    }
}
