package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.Exp.Exp;
import Model.MyException;
import Model.ProgramState;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements InterfaceStatement {
    private Exp _exp;

    public OpenReadFile(Exp exp) {
        _exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (_exp.evaluate(symbolTable).getType().toString().equals("string")) {
            if(!fileTable.isDefined(_exp.evaluate(symbolTable).toString())){
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(_exp.evaluate(symbolTable).toString()));
                    fileTable.update(_exp.evaluate(symbolTable).toString(), reader);
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
        return state;
    }

    public String toString() {
        return "OpenReadFile(" + _exp.toString() + ")";
    }
}
