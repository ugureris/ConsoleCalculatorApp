import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> stack;
    private int top;

    public Stack() {
        stack = new ArrayList<>();
        top = -1;
    }

    public void push(T element) {
        stack.add(element);
        top++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop from an empty stack.");
        }

        T element = stack.get(top);
        stack.remove(top);
        top--;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek an empty stack.");
        }

        return stack.get(top);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

}

