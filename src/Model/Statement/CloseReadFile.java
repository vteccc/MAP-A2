package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.Exp.Exp;
import Model.MyException;
import Model.ProgramState;
import Model.Value.IntValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFile implements InterfaceStatement {
    private Exp _exp;

    public CloseReadFile(Exp exp) {
        _exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (_exp.evaluate(symbolTable).getType().toString().equals("string")) {
            if (fileTable.isDefined(_exp.evaluate(symbolTable).toString())) {
                try {
                    BufferedReader reader = fileTable.getValue(_exp.evaluate(symbolTable).toString());
                    reader.close();
                    fileTable.remove(_exp.evaluate(symbolTable).toString());
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
        return "CloseReadFile(" + _exp.toString() + ")";
    }
}
