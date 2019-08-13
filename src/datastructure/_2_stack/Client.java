package datastructure._2_stack;

import datastructure._2_stack.exception.StackException;

import java.util.Objects;
import java.util.Scanner;

public class Client {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println("请选择：");
        System.out.println("1.顺序栈");
        System.out.println("2.链栈");
        Scanner scanner = new Scanner(System.in);
        AbstractStack stack = null;
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                stack = new SequenceStack<Integer, Integer>(Integer.class);
                break;
            case 2:
                stack = new LinkedStack<Integer, LinkedStack.Node<Integer>>();
                break;
        }
        try {
            System.out.println("Empty? " + Objects.requireNonNull(stack).isEmpty());
            System.out.print("Inputting...");
            for (int i = 0; i < 11; i++) {
                stack.push(i);
            }
            System.out.println("Done");
            System.out.println("Empty? " + stack.isEmpty());
            System.out.println("\nPeek->" + stack.peek());
            System.out.println("\nPopping...");
            while (!stack.isEmpty()) {
                System.out.println("Pop->" + stack.pop());
            }
            System.out.println("Done");
            System.out.println("\nEmpty? " + stack.isEmpty());
        } catch (StackException e) {
            e.printStackTrace();
        }
    }
}
