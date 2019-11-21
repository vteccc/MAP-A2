package Model.Value;

import Model.Type.Type;
import Model.Type.BooleanType;
import com.sun.jdi.BooleanValue;

public class BoolValue implements Value {
    private boolean val;

    public BoolValue(boolean v) {
        val = v;
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }

    public boolean getVal() {
        return val;
    }

    public String toString() {
        if (val)
            return "true";
        else
            return "false";
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof BooleanType))
        return false;
        if(((BoolValue)object).getVal()==val){
            return true;
        }
        else
            return false;
    }
}
