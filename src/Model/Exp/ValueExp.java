package Model.Exp;

import Model.MyException;
import Model.ADT.MyInterfaceDictionary;
import Model.Value.Value;

public class ValueExp implements Exp {
    private Value _e;

    public ValueExp(Value e) {
        _e = e;
    }

    public Value evaluate(MyInterfaceDictionary<String, Value> table){
        return _e;
    }

    @Override
    public String toString() {
        return _e.toString();
    }
}
