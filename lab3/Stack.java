package lab3;

public interface Stack {
    void push(int element);
    int pop();
    int element();
    boolean isEmpty();
    boolean isFull();
    int size();
    int getElementAt(int index);
}