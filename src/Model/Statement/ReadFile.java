package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.Exp.Exp;

import Model.MyException;
import Model.ProgramState;
import Model.Value.IntValue;
import Model.Value.Value;

import java.io.BufferedReader;

public class ReadFile implements InterfaceStatement {
    private Exp exp;
    private String val;

    public ReadFile(Exp exp, String val) {
        this.exp = exp;
        this.val = val;
    }


    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyInterfaceDictionary<Integer,Value> heapTable = state.getHeapTable();
        if (symbolTable.getValue(val).getType().toString().equals("int")) {
            if (exp.evaluate(symbolTable,heapTable).getType().toString().equals("string")) {
                if (fileTable.isDefined(exp.evaluate(symbolTable,heapTable).toString())) {
                    try {
                        BufferedReader reader = fileTable.getValue(exp.evaluate(symbolTable,heapTable).toString());
                        symbolTable.update(val, new IntValue(Integer.parseInt(reader.readLine())));
                    } catch (Exception e ) {
                        throw new MyException("Error while parsing int!");
                    }
                } else {
                    throw new MyException("Error:File is not opened!");
                }
            } else {
                throw new MyException("Error:First argument must be a string!");
            }
        } else {
            throw new MyException("Error:The second argument must be a variable that's an int!");
        }
        return null;
    }

    public String toString() {
        return "ReadFile(" + exp.toString() + ")";
    }
}
