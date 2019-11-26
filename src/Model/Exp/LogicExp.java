package Model.Exp;

import Model.MyException;
import Model.ADT.MyInterfaceDictionary;
import Model.Type.IntType;
import Model.Value.Value;
import Model.Value.BoolValue;

import java.util.Set;

public class LogicExp implements Exp {
    private Exp exp1;
    private Exp exp2;
    private String operand;
    private Set<String> operands;

    public LogicExp(Exp exp1, String operand, Exp exp2) {
        operands = Set.of("&&", "||");
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operand = operand;
    }

    public Value evaluate(MyInterfaceDictionary<String, Value> table) throws MyException {
        if (operands.contains(operand)) {
            Value v1, v2;
            v1 = exp1.evaluate(table);
            if (v1.getType().equals(new IntType())) {
                v2 = exp2.evaluate(table);
                if (v2.getType().equals(new IntType())) {
                    BoolValue i1 = (BoolValue) v1;
                    BoolValue i2 = (BoolValue) v2;
                    boolean n1, n2;
                    n1 = i1.getVal();
                    n2 = i2.getVal();
                    if (operand.equals("&&")) //and
                        if (n1 == false && n2 == false)
                            return new BoolValue(false);
                        else
                            return new BoolValue(true);
                    if (operand.equals("||"))//or
                        if (n1 == true || n2 == true)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                } else
                    throw new MyException("Error:Second operand is not a boolean!");
            } else
                throw new MyException("Error:First operand is not a boolean!");
            return new BoolValue(false);
        } else {
            throw new MyException("Error:Invalid operand!");
        }
    }

    @Override
    public String toString() {
        return exp1.toString() + operand + exp2.toString();
    }
}
