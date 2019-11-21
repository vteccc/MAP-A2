package Model.ADT;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class MyDictionary<T, U> implements MyInterfaceDictionary<T, U> {
    private HashMap<T, U> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<T, U>();
    }

    public boolean isDefined(T id) {
        Set<T> keys = dictionary.keySet();
        return keys.contains(id);
    }

    public U getValue(T id) {
        return dictionary.get(id);
    }

    public void update(T k, U v) {
        dictionary.put(k, v);
    }

    public void remove(T id) {
        dictionary.remove(id);
    }

    @Override
    public String toString() {
        Set<T> keys = dictionary.keySet();
        Collection<U> val = dictionary.values();
        Object[] a1 = keys.toArray();
        Object[] a2 = val.toArray();
        StringBuilder result = new StringBuilder();
        int i;
        for (i = 0; i < dictionary.size(); i++) {
            result.append(a1[i]).append(" = ").append(a2[i]).append("\n");
        }
        return String.valueOf(result);
    }
}
