package Model.Exp;

import Model.ADT.MyInterfaceDictionary;
import Model.Value.Value;

public class VarExp implements Exp {

    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    public Value evaluate(MyInterfaceDictionary<String, Value> table, MyInterfaceDictionary<Integer, Value> heapTable) {
        return table.getValue(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
