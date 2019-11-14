package Model.ADT;

public interface MyInterfaceStack<T> {
    T pop();

    void push(T v);

    boolean isEmpty();

    String toString();
}
