package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.Exp.Exp;
import Model.MyException;
import Model.ProgramState;
import Model.Value.Value;

public class IfStatement implements InterfaceStatement {
    private Exp exp;
    private InterfaceStatement thenStatement;
    private InterfaceStatement elseStatement;

    public IfStatement(InterfaceStatement then, InterfaceStatement _else) {
        thenStatement = then;
        elseStatement = _else;
    }

    public IfStatement(Exp e, InterfaceStatement t, InterfaceStatement el) {
        exp = e;
        thenStatement = t;
        elseStatement = el;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenStatement.toString() + ")ELSE("
                + elseStatement.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        if (exp.evaluate(symbolTable).getType().toString().equals("boolean")) {
            if (exp.evaluate(symbolTable).toString().equals("true")) {
                thenStatement.execute(state);
            } else {
                if (exp.evaluate(symbolTable).toString().equals("false")) {
                    elseStatement.execute(state);
                }
            }
        } else {
            throw new MyException("Error:If must contain a boolean type in its arguments!");
        }

        return state;
    }
}
