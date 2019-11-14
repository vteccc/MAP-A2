package Model.Exp;

import Model.MyException;
import Model.ADT.MyInterfaceDictionary;
import Model.Value.Value;

public interface Exp {
    Value evaluate(MyInterfaceDictionary<String, Value> table) throws MyException;

    String toString();
}
