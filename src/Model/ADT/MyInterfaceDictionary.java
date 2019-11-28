package Model.ADT;

import java.util.HashMap;
import java.util.Map;

public interface MyInterfaceDictionary<T, U> {
    boolean isDefined(T id);

    U getValue(T id);

    void update(T id, U v);

    void remove(T id);

    String toString();

    void setContent(Map<T,U> dictionary);

    int getIndex();

    Map<T,U> getContent();
}
