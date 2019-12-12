package Model.Value;

import Model.Type.ReferenceType;
import Model.Type.Type;

public class ReferenceValue implements Value {
    private int address;
    private Type locationType;

    public ReferenceValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public Type getType() {
        return new ReferenceType(locationType);
    }

    public int getAddress() {
        return address;
    }

    public String toString() {
        return "address: " + address + ", type " + locationType;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ReferenceType))
            return false;
        if (((ReferenceValue) object).getAddress() == address && ((ReferenceValue) object).getType() == locationType) {
            return true;
        } else
            return false;
    }
}
