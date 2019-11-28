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

    public IfStatement(Exp exp, InterfaceStatement thenStatement, InterfaceStatement elseStatement) {
        this.exp = exp;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenStatement.toString() + ")ELSE("
                + elseStatement.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<Integer,Value> heapTable = state.getHeapTable();
        if (exp.evaluate(symbolTable,heapTable).getType().toString().equals("boolean")) {
            if (exp.evaluate(symbolTable,heapTable).toString().equals("true")) {
                thenStatement.execute(state);
            } else {
                if (exp.evaluate(symbolTable,heapTable).toString().equals("false")) {
                    elseStatement.execute(state);
                }
            }
        } else {
            throw new MyException("Error:If must contain a boolean type in its arguments!");
        }

        return state;
    }
}
