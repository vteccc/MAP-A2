package Model.Value;

import Model.Type.Type;

public interface Value {
    Type getType();
    boolean equals(Object object);
    String toString();
}
