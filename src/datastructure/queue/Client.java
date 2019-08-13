package datastructure.queue;

import datastructure.queue.exception.QueueException;

import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("请选择：");
        System.out.println("1.顺序循环队列");
        System.out.println("2.链式队列");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        MyQueue<Integer> queue = null;
        switch (choose) {
            case 1:
                queue = new SequenceQueue<>(Integer.class);
                break;
            case 2:
                queue = new LinkedQueue<>();
                break;
        }
        try {
            for (int i = 0; i < 9; i++) {
                Objects.requireNonNull(queue).enQueue(i);
            }
            queue.output();
            for (int i = 0; i < 3; i++) {
                System.out.println("deQueue->" + queue.deQueue());
            }
            queue.output();
            for (int i = 0; i < 3; i++) {
                queue.enQueue(i);
            }
            queue.output("再次入队后：");
            while (!queue.isEmpty()) {
                System.out.println("deQueue->" + queue.deQueue());
            }
            queue.output();
        } catch (QueueException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
