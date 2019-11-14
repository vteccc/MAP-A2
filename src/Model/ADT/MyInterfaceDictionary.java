package Model.ADT;

public interface MyInterfaceDictionary<T, U> {
    boolean isDefined(T id);

    U getValue(T id);

    void update(T id, U v);

    String toString();
}
