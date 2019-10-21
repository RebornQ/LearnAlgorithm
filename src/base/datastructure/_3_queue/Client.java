package base.datastructure._3_queue;

import base.datastructure._3_queue.exception.QueueException;

import java.util.Objects;
import java.util.Scanner;

public class Client {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println("请选择：");
        System.out.println("1.顺序循环队列");
        System.out.println("2.链式队列");
        System.out.println("注意：队中的箭头方向指入队方向（也是出队方向）");
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
            queue.output("\n再次入队后：");
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
