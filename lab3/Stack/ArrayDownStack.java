public class ArrayDown implements Stack {
    private int[] array;
    private int top;
    private final int MAX_SIZE;

    public ArrayDown(int size) {
        MAX_SIZE = size;
        array = new int[size];
        top = -1;
    }

    @Override
    public void push(int element) {
        if (isFull()) {
            System.out.println("Stack is full. Cannot push element.");
            return;
        }
        array[++top] = element - 1;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop element.");
            return -1; // or throw an exception
        }
        return array[top--] + 1;
    }

    @Override
    public int element() {
        if (isEmpty()) {
            System.out.println("Stack is empty. No element to return.");
            return -1; // or throw an exception
        }
        return array[top] + 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == MAX_SIZE - 1;
    }
}
