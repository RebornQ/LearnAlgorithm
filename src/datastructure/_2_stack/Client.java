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
        System.out.println("注意：栈中的箭头方向仅指入栈方向");
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
            for (int i = 0; i < 11; i++) {
                Objects.requireNonNull(stack).push(i);
            }
            stack.output();
            for (int i = 0; i < 3; i++) {
                System.out.println("Pop->" + stack.pop());
            }
            stack.output();
            System.out.println("\nPeek->" + stack.peek());
            for (int i = 0; i < 3; i++) {
                stack.push(i);
            }
            stack.output("\n再次入栈后：");
            while (!stack.isEmpty()) {
                System.out.println("Pop->" + stack.pop());
            }
            System.out.println("\nEmpty? " + stack.isEmpty());
            stack.output();
        } catch (StackException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
