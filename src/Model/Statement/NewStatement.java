package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.Exp.Exp;
import Model.MyException;
import Model.ProgramState;
import Model.Type.ReferenceType;
import Model.Value.ReferenceValue;
import Model.Value.Value;

public class NewStatement implements InterfaceStatement {
    private String variable_name;
    private Exp exp;

    public NewStatement(String variable_name, Exp exp) {
        this.variable_name = variable_name;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "new(" + variable_name + ", " + exp.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<Integer, Value> heapTable = state.getHeapTable();
        if (symbolTable.isDefined(variable_name) && symbolTable.getValue(variable_name).getType().toString().contains("Reference(")) {
            ReferenceType referenceType = (ReferenceType) symbolTable.getValue(variable_name).getType();
            if (exp.evaluate(symbolTable,heapTable).getType().equals(referenceType.getInner())) {
                int index=heapTable.getIndex();
                heapTable.update(index,exp.evaluate(symbolTable,heapTable));
                ReferenceValue referenceValue=new ReferenceValue(index,referenceType.getInner());
                symbolTable.update(variable_name,referenceValue);
            } else {
                throw new MyException("Error:Location type and Expression type do not match!");
            }
        } else {
            throw new MyException("Error: Variable must be declared first and it must be Reference Type!");
        }
        return null;
    }
}
