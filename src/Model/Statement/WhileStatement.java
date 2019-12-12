package Model.Statement;

import Model.ADT.MyInterfaceDictionary;
import Model.ADT.MyInterfaceStack;
import Model.Exp.Exp;
import Model.MyException;
import Model.ProgramState;
import Model.Value.Value;

public class WhileStatement implements InterfaceStatement {
    private Exp exp;
    private InterfaceStatement loop;

    public WhileStatement(Exp exp, InterfaceStatement loop) {
        this.exp = exp;
        this.loop = loop;
    }

    public String toString() {
        return "while(" + exp.toString() + ") execute {\n" + loop.toString()+"\n}";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyInterfaceDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyInterfaceDictionary<Integer,Value> heapTable = state.getHeapTable();
        MyInterfaceStack<InterfaceStatement> stack = state.getStack();
        if (exp.evaluate(symbolTable,heapTable).getType().toString().equals("boolean")) {
            if (exp.evaluate(symbolTable,heapTable).toString().equals("true")) {
                stack.push(new WhileStatement(exp, loop));
                loop.execute(state);
            }
        } else {
            throw new MyException("Error:While must contain a boolean type in its first argument!");
        }
        return null;
    }
}
