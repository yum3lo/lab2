package lab3;

import java.util.Scanner;

public class Main {
    private static final int MAX_SIZE = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter max " + MAX_SIZE + " int values:");
        int[] values = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            values[i] = scanner.nextInt();
        }

        Stack stack = null;

        System.out.println("Choose implementation (1:ArrayUpStack, 2:LinkedStack, 3:ArrayDownStack):");
        String implementationType = scanner.next();

        switch (implementationType) {
            case "1":
                stack = new ArrayUpStack(5);
                break;
            case "2":
                stack = new LinkedStack();
                break;
            case "3":
                stack = new ArrayDownStack(5);
                break;
            default:
                System.out.println("Invalid implementation.");
                scanner.close();
                return;
        }
        operateOnStack(stack, scanner, values);
        scanner.close();
    }

    public static void operateOnStack(Stack stack, Scanner scanner, int[] values) {
        // performs operations until the user exits
        while (true) {
            System.out.println("Choose operation (1:push, 2:pop, 3:element, 4:isEmpty, 5:isFull, e:exit):");
            String operation = scanner.next();

            switch (operation) {
                case "1":
                    System.out.println("Enter value to push:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "2":
                    stack.pop();
                    break;
                case "3":
                    stack.element();
                    break;
                case "4":
                    stack.isEmpty();
                    break;
                case "5":
                    stack.isFull();
                    break;
                case "e":
                    return;
                default:
                    System.out.println("Invalid operation.");
                    break;
            }
        }
    }

    public static void printCurrentState(Stack stack) {
        System.out.println("Current Stack state:");
        if (stack.isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            for (int i = MAX_SIZE - 1; i >= 0; i--) {
                if (i <= MAX_SIZE - stack.size() - 1) {
                    System.out.print("- ");
                } else {
                    System.out.print(stack.getElementAt(i - (MAX_SIZE - stack.size())) + " ");
                }
            }
            System.out.println();
        }
    }
}