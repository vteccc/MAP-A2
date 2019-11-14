package Model.Value;

import Model.Type.Type;
import Model.Type.BooleanType;

public class BoolValue implements Value {
    private boolean val;

    public BoolValue(boolean v) {
        val = v;
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

    public Type getType() {
        return new BooleanType();
    }
}
