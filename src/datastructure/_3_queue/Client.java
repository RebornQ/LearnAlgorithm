package datastructure._3_queue;

import datastructure._3_queue.exception.QueueException;

import java.util.Objects;
import java.util.Scanner;

public class Client {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println("请选择：");
        System.out.println("1.顺序循环队列");
        System.out.println("2.链式队列");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        AbstractQueue queue = null;
        switch (choose) {
            case 1:
                queue = new SequenceQueue<>(Integer.class);
                break;
            case 2:
                queue = new LinkedQueue<Integer, LinkedQueue.Node<Integer>>();
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
