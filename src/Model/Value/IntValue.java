package Model.Value;

import Model.Type.IntType;
import Model.Type.Type;
import com.sun.jdi.IntegerValue;

public class IntValue implements Value {
    private int val;

    public IntValue(int v) {
        val = v;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    public int getVal() {
        return val;
    }

    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof IntType))
            return false;
        if(((IntValue)object).getVal()==val){
            return true;
        }
        else
            return false;
    }
}
