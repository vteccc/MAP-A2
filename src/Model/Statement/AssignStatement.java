package Model.Statement;

import Model.*;
import Model.ADT.MyInterfaceDictionary;
import Model.ADT.MyInterfaceStack;
import Model.Exp.Exp;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStatement implements InterfaceStatement {
    private String _id;
    private Exp _exp;

    public AssignStatement(String id, Exp exp) {
        _id = id;
        _exp = exp;
    }

    @Override
    public String toString() {
        return _id + " = " + _exp.toString();
    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceStack<InterfaceStatement> stk = state.getStack();
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        Value val = _exp.evaluate(symbolTable);
        if (symbolTable.isDefined(_id)) {
            Type typeID = (symbolTable.getValue(_id)).getType();
            if (val.getType().equals(typeID))
                symbolTable.update(_id, val);
            else
                throw new MyException("Error:Declared type of variable " + _id + " and type of the assigned expression do not match!");
        } else
            throw new MyException("Error:The used variable " + _id + " was not declared before!");
        return state;
    }
}
