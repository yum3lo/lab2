import java.util.Scanner;

public class StackDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter up to 5 integer values separated by spaces:");
        String input = scanner.nextLine();
        String[] inputValues = input.split(" ");

        ArrayUp arrayStack = new ArrayUp(5);
        LinkedList linkedStack = new LinkedList();
        ArrayDown anotherStack = new ArrayDown(5);

        for (String value : inputValues) {
            int intValue = Integer.parseInt(value);
            arrayStack.push(intValue);
            linkedStack.push(intValue);
            anotherStack.push(intValue);
        }

        System.out.println("ArrayUpStack popped elements:");
        while (!arrayStack.isEmpty()) {
            System.out.println("Popped element: " + arrayStack.pop());
        }

        System.out.println("LinkedStack popped elements:");
        while (!linkedStack.isEmpty()) {
            System.out.println("Popped element: " + linkedStack.pop());
        }

        System.out.println("AnotherStackImplementation popped elements:");
        while (!anotherStack.isEmpty()) {
            System.out.println("Popped element: " + anotherStack.pop());
        }

        scanner.close();
    }
}