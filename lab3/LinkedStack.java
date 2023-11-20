package lab3;

public class LinkedStack implements Stack {
    private Node top;

    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedStack() {
        top = null;
    }

    @Override
    public void push(int element) {
        if (isFull()) {
            System.out.println("Stack is full");
            return;
        }
        Node newNode = new Node(element);
        newNode.next = top;
        top = newNode;
    }

    @Override
    public int pop() {
        if (!isEmpty()) {
            int poppedElement = top.data;
            top = top.next;
            return poppedElement;
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
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        int count = 0;
        Node current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public int getElementAt(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }

        int currentIndex = 0;
        Node current = top;

        while (current != null) {
            if (currentIndex == index) {
                return current.data;
            }
            currentIndex++;
            current = current.next;
        }

        throw new IndexOutOfBoundsException("Index out of bounds");
    }
}