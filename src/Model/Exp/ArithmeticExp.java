package Model.Exp;

import Model.MyException;
import Model.ADT.MyInterfaceDictionary;
import Model.Type.IntType;
import Model.Value.Value;
import Model.Value.IntValue;

public class ArithmeticExp implements Exp {
    private Exp _exp1;
    private Exp _exp2;
    private int _operand;

    public ArithmeticExp(Exp exp1, Exp exp2, int operand) {
        _exp1 = exp1;
        _exp2 = exp2;
        _operand = operand;
    }

    public Value evaluate(MyInterfaceDictionary<String, Value> table) throws MyException {
        Value v1, v2;
        v1 = _exp1.evaluate(table);
        if (v1.getType().equals(new IntType())) {
            v2 = _exp2.evaluate(table);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (_operand == 1) return new IntValue(n1 + n2);
                if (_operand == 2) return new IntValue(n1 - n2);
                if (_operand == 3) return new IntValue(n1 * n2);
                if (_operand == 4) {
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
    }

    @Override
    public String toString() {
        if (_operand == 1) {
            return _exp1.toString() + " + " + _exp2.toString();
        }
        if (_operand == 2) {
            return _exp1.toString() + " - " + _exp2.toString();
        }
        if (_operand == 3) {
            return _exp1.toString() + " * " + _exp2.toString();
        }
        if (_operand == 4) {
            return _exp1.toString() + " / " + _exp2.toString();
        }
        return null;
    }
}
