package Model.Exp;

import Model.MyException;
import Model.ADT.MyInterfaceDictionary;
import Model.Type.IntType;
import Model.Value.Value;
import Model.Value.BoolValue;

public class LogicExp implements Exp {
    private Exp _exp1;
    private Exp _exp2;
    private int _operand;

    public LogicExp(Exp exp1, Exp exp2, int operand) {
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
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (_operand == 1) //and
                    if (n1 == false || n2 == false)
                        return new BoolValue(false);
                    else
                        return new BoolValue(true);
                if (_operand == 2)//or
                    if (n1 == true || n2 == true)
                        return new BoolValue(true);
                    else
                        return new BoolValue(false);
            } else
                throw new MyException("Error:Second operand is not a boolean!");
        } else
            throw new MyException("Error:First operand is not a boolean!");
        return new BoolValue(false);
    }

    @Override
    public String toString() {
        if (_operand == 1) {
            return _exp1.toString() + " AND " + _exp2.toString();
        }
        if (_operand == 2) {
            return _exp1.toString() + " OR " + _exp2.toString();
        }
        return null;
    }
}
