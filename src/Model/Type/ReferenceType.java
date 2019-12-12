package Model.Type;

import Model.Value.ReferenceValue;
import Model.Value.Value;

public class ReferenceType implements Type {
    private Type inner;
    public ReferenceType(Type inner){
        this.inner=inner;
    }

    public Type getInner(){
        return inner;
    }

    public boolean equals(Object another){
        if(another instanceof ReferenceType){
            return true;
        }
        else{
            return false;
        }
    }
    public String toString(){
        return "Reference("+inner.toString()+")";
    }
    @Override
    public Value defaultValue() {
        return new ReferenceValue(0,inner);
    }
}
