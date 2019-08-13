package datastructure._2_stack;

import datastructure._2_stack.exception.StackException;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("请选择：");
        System.out.println("1.顺序栈");
        System.out.println("2.链栈");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                SequenceStack<Integer> sequenceStack = new SequenceStack<>(Integer.class);
                System.out.println("Empty? " + sequenceStack.isEmpty());
                System.out.print("Inputting...");
                for (int i = 0; i < 10; i++) {
                    sequenceStack.push(i);
                }
                sequenceStack.push(10);
                System.out.println("Done");
                System.out.println("Empty? " + sequenceStack.isEmpty());
                System.out.println("\nPeek->" + sequenceStack.peek());
                System.out.println("\nPopping...");
                while (!sequenceStack.isEmpty()) {
                    System.out.println("Pop->" + sequenceStack.pop());
                }
                System.out.println("Done");
                System.out.println("\nEmpty? " + sequenceStack.isEmpty());
                break;
            case 2:
                LinkedStack<Integer> linkedStack = new LinkedStack<>();
                try {
                    System.out.println("Empty? " + linkedStack.isEmpty());
                    System.out.print("Inputting...");
                    for (int i = 0; i < 10; i++) {
                        linkedStack.push(i);
                    }
                    System.out.println("Done");
                    System.out.println("Empty? " + linkedStack.isEmpty());
                    System.out.println("\nPeek->" + linkedStack.peek());
                    System.out.println("\nPopping...");
                    while (!linkedStack.isEmpty()) {
                        System.out.println("Pop->" + linkedStack.pop());
                    }
                    System.out.println("Done");
                    System.out.println("\nEmpty? " + linkedStack.isEmpty());
                } catch (StackException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
