package Model.ADT;

import java.util.Stack;

public class MyStack<T> implements MyInterfaceStack<T> {
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    public T pop() {
        return stack.pop();
    }

    public void push(T v) {
        stack.push(v);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (T statement : stack) {
            result.append(statement.toString()).append("\n");
        }
        return String.valueOf(result);
    }
}
