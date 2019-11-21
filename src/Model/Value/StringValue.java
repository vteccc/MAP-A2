package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value {
    private String val;

    public StringValue(String v) {
        val = v;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    public String getVal() {
        return val;
    }

    public String toString() {
        return val;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof StringType))
            return false;
        if(((StringValue)object).getVal()==val){
            return true;
        }
        else
            return false;
    }
}
