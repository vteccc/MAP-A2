package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.Exp.Exp;
import Model.MyException;
import Model.ProgramState;
import Model.Type.ReferenceType;
import Model.Value.ReferenceValue;
import Model.Value.Value;

public class WriteHeapStatement implements InterfaceStatement {
    private String variable_name;
    private Exp exp;

    public WriteHeapStatement(String variable_name, Exp exp) {
        this.variable_name = variable_name;
        this.exp = exp;
    }

    public String toString() {
        return "readHeap(" + variable_name + ", " + exp.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<Integer, Value> heapTable = state.getHeapTable();
        if (symbolTable.isDefined(variable_name) && symbolTable.getValue(variable_name).getType().toString().contains("Reference(")) {
            ReferenceType referenceType = (ReferenceType) symbolTable.getValue(variable_name).getType();
            if (exp.evaluate(symbolTable, heapTable).getType().equals(referenceType.getInner())) {
                ReferenceValue referenceValue= (ReferenceValue) symbolTable.getValue(variable_name);
                heapTable.update(referenceValue.getAddress(),exp.evaluate(symbolTable,heapTable));
            } else {
                throw new MyException("Error:Expression type do not match with the inner type of the referenced value!");
            }
        } else {
            throw new MyException("Error:Variable name is not in the Symbol Table or its type is not reference type!");
        }
        return null;
    }
}
