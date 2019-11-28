package Model.Exp;

import Model.ADT.MyInterfaceDictionary;
import Model.Value.Value;

public class ValueExp implements Exp {
    private Value e;

    public ValueExp(Value e) {
        this.e = e;
    }

    @Override
    public Value evaluate(MyInterfaceDictionary<String, Value> table, MyInterfaceDictionary<Integer, Value> heapTable) {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
