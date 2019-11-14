package Model.ADT;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyInterfaceList<T> {
    private List<T> list;

    public MyList() {
        list = new ArrayList<T>();
    }

    public void add(T v) {
        list.add(v);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\nList:\n");
        int i;
        for (i = 0; i < list.size(); i += 1) {
            result.append(list.get(i).toString());
            result.append("\n");
        }

        return String.valueOf(result);
    }
}
