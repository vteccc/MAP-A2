package Model.Type;

import Model.Value.BoolValue;
import Model.Value.Value;

public class BooleanType implements Type {
    @Override
    public boolean equals(Object another) {
        if (another instanceof BooleanType)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "boolean";
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
