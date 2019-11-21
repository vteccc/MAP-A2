package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.Exp.Exp;
import Model.Exp.ValueExp;

import Model.MyException;
import Model.ProgramState;
import Model.Value.IntValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements InterfaceStatement {
    private Exp _exp;
    private String _val;

    public ReadFile(Exp exp, String val) {
        _exp = exp;
        _val = val;
    }


    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (symbolTable.getValue(_val).getType().toString().equals("int")) {
            if (_exp.evaluate(symbolTable).getType().toString().equals("string")) {
                if (fileTable.isDefined(_exp.evaluate(symbolTable).toString())) {
                    try {
                        BufferedReader reader = fileTable.getValue(_exp.evaluate(symbolTable).toString());
                        symbolTable.update(_val, new IntValue(Integer.parseInt(reader.readLine())));
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
        return state;
    }

    public String toString() {
        return "ReadFile(" + _exp.toString() + ")";
    }
}
