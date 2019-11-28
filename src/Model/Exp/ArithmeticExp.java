package Model.Exp;

import Model.MyException;
import Model.ADT.MyInterfaceDictionary;
import Model.Type.IntType;
import Model.Value.Value;
import Model.Value.IntValue;

import java.util.Set;

public class ArithmeticExp implements Exp {
    private Exp exp1;
    private Exp exp2;
    private String operand;
    private Set<String> operands;

    public ArithmeticExp(Exp exp1, String operand, Exp exp2) {
        operands = Set.of("+", "-", "*", "/");
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operand = operand;
    }

    public Value evaluate(MyInterfaceDictionary<String, Value> table, MyInterfaceDictionary<Integer,Value> heapTable) throws MyException {
        if (operands.contains(operand)) {
            Value v1, v2;
            v1 = exp1.evaluate(table,heapTable);
            if (v1.getType().equals(new IntType())) {
                v2 = exp2.evaluate(table,heapTable);
                if (v2.getType().equals(new IntType())) {
                    IntValue i1 = (IntValue) v1;
                    IntValue i2 = (IntValue) v2;
                    int n1, n2;
                    n1 = i1.getVal();
                    n2 = i2.getVal();
                    if (operand.equals("+")) return new IntValue(n1 + n2);
                    if (operand.equals("-")) return new IntValue(n1 - n2);
                    if (operand.equals("*")) return new IntValue(n1 * n2);
                    if (operand.equals("/")) {
                        if (n2 == 0) {
                            throw new MyException("Error:Division by zero!");
                        } else
                            return new IntValue(n1 / n2);
                    }
                } else
                    throw new MyException("Error:Second operand is not an integer!");
            } else
                throw new MyException("Error:First operand is not an integer!");
            return new IntValue(0);
        } else
            throw new MyException("Error:Invaid operand!");
    }

    @Override
    public String toString() {
        return exp1.toString() + operand + exp2.toString();
    }
}
