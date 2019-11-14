package Model.Type;

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
}
