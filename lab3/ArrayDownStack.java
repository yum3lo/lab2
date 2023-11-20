package lab3;

public class ArrayDownStack implements Stack {
    private int[] array;
    private int top;
    private final int MAX_SIZE = 5;

    public ArrayDownStack(int MAX_SIZE) {
        array = new int[MAX_SIZE];
        top = 4;
    }

    @Override
    public void push(int element) {
        if (!isFull()) {
            top--;
            array[top] = element;
        } else {
            System.out.println("Stack is full");
        }
    }

    @Override
    public int pop() {
        if (!isEmpty()) {
            top++;
            return array[top];
        } else {
            System.out.println("Stack is empty");
            return -1;
        }
    }

    @Override
    public int element() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return array[top + 1];
    }

    @Override
    public boolean isEmpty() {
        return top == MAX_SIZE - 1;
    }

    @Override
    public boolean isFull() {
        return top == - 1;
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = MAX_SIZE - 1; i >= top; i--) {
            if (array[i] != 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getElementAt(int index) {
        if (index < top || index >= MAX_SIZE) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }
}