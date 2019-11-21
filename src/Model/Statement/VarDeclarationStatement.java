package Model.Statement;

import Model.MyException;
import Model.ADT.MyInterfaceDictionary;
import Model.ADT.MyInterfaceStack;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;

public class VarDeclarationStatement implements InterfaceStatement {
    private String _name;
    private Type _type;

    public VarDeclarationStatement(String name, Type type) {
        _name = name;
        _type = type;
    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceStack<InterfaceStatement> stk = state.getStack();
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        if (symbolTable.isDefined(_name)) {
            throw new MyException("Error:Variable is already declared!");
        } else {
            symbolTable.update(_name, _type.defaultValue());
            return state;
        }
    }


    public String toString() {
        return _name + " is " + _type.toString();
    }
}
