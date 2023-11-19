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
}
