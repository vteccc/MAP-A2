package Model.Exp;

import Model.MyException;
import Model.ADT.MyInterfaceDictionary;
import Model.Value.Value;

public class VarExp implements Exp {

    private String _id;

    public VarExp(String id) {
        _id = id;
    }

    public Value evaluate(MyInterfaceDictionary<String, Value> table){
        return table.getValue(_id);
    }

    @Override
    public String toString() {
        return _id;
    }
}
