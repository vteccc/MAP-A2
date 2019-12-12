package Model.Statement;

import Model.MyException;
import Model.ADT.MyInterfaceDictionary;
import Model.ADT.MyInterfaceStack;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

public class VarDeclarationStatement implements InterfaceStatement {
    private String name;
    private Type type;

    public VarDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceStack<InterfaceStatement> stk = state.getStack();
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        if (symbolTable.isDefined(name)) {
            throw new MyException("Error:Variable is already declared!");
        } else {
            symbolTable.update(name, type.defaultValue());
            return null;
        }
    }


    public String toString() {
        return name + " is " + type.toString();
    }
}
