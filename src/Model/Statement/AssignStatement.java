package Model.Statement;

import Model.*;
import Model.ADT.MyInterfaceDictionary;
import Model.ADT.MyInterfaceStack;
import Model.Exp.Exp;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStatement implements InterfaceStatement {
    private String id;
    private Exp exp;

    public AssignStatement(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return id + " = " + exp.toString();
    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceStack<InterfaceStatement> stk = state.getStack();
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        Value val = exp.evaluate(symbolTable);
        if (symbolTable.isDefined(id)) {
            Type typeID = (symbolTable.getValue(id)).getType();
            if (val.getType().equals(typeID))
                symbolTable.update(id, val);
            else
                throw new MyException("Error:Declared type of variable " + id + " and type of the assigned expression do not match!");
        } else
            throw new MyException("Error:The used variable " + id + " was not declared before!");
        return state;
    }
}
