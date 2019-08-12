package datastructure.queue;

import datastructure.queue.exception.QueueException;

public class Client {
    public static void main(String[] args) {
        SequenceQueue<Integer> sequenceQueue = new SequenceQueue<>(Integer.class);
        try {
            for (int i = 0; i < 9; i++) {
                sequenceQueue.enQueue(i);
            }
            sequenceQueue.output();
            System.out.println("deQueue->" + sequenceQueue.deQueue());
            System.out.println("deQueue->" + sequenceQueue.deQueue());
            System.out.println("deQueue->" + sequenceQueue.deQueue());
            sequenceQueue.output();
            for (int i = 0; i < 3; i++) {
                sequenceQueue.enQueue(i);
            }
            sequenceQueue.output("再次入队后：");
            while (!sequenceQueue.isEmpty()) {
                System.out.println("deQueue->" + sequenceQueue.deQueue());
            }
            sequenceQueue.output();
        } catch (QueueException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
