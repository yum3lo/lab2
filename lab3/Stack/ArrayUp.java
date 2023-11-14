public class ArrayUp implements Stack {
    private int[] array;
    private int top;
    private final int MAX_SIZE;

    public ArrayUp(int size) {
        MAX_SIZE = size;    
        array = new int[size];
        top = -1;
    }

    @Override
    public void push(int element) {
        if (isFull()) {
            System.out.println("Stack is full");
            return;
        }
        array[++top] = element;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return array[top--];
    }

    @Override
    public int element() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return array[top];
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