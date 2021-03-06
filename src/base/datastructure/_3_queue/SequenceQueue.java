package base.datastructure._3_queue;

import base.datastructure._3_queue.exception.EmptyQueueException;
import base.datastructure._3_queue.exception.QueueException;
import base.datastructure._3_queue.exception.QueueOverFlowError;

import java.lang.reflect.Array;

/*
 顺序循环队列
 // front 和 rear 的下标取值范围为0~size-1，不会造成假溢出，size为队列容量
 front = (front+1)%size;    // 队头下标，取余
 rear = (rear+1)%size;  // 下一个入队元素的下标，不是队尾下标
 1. 出队，约定 front=rear 时队列为空。
 2. 入队，约定 front=(rear+1)%size 时队列为满，即 (队尾下标+1) % 数组长度 = 队头下标，
    注意此时队列中仍有一个空的位置，队尾指针也永远要空出1位，所以队列最大容量比数组长度小1，
    主要用于避免与队列空的条件 front=rear 相同。
 */
public class SequenceQueue<T, R extends Integer> extends AbstractQueue<T, R> {

    private Class<T> type;

    private T[] queue;

    /**
     * 队列的初始长度为10
     */
    private final int CAPACITY_DEFAULT = 10;

    private int front, rear;

    @SuppressWarnings("unchecked")
    public SequenceQueue(Class<T> type) {
        this.type = type;
        queue = (T[]) Array.newInstance(type, CAPACITY_DEFAULT);
        front = rear = 0;
    }

    @SuppressWarnings("unchecked")
    public SequenceQueue(Class<T> type, int capacity) {
        this.type = type;
        queue = (T[]) Array.newInstance(type, capacity);
        front = rear = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void enQueue(T data) throws QueueException {
        // 约定 front=(rear+1)%size 时队列为满
        if ((rear + 1) % queue.length == front) {
            throw new QueueOverFlowError();
        }
        queue[rear] = data;
        // 入队操作改变rear下标指向
        rear = (rear + 1) % queue.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deQueue() throws QueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T deQueueElement = queue[front];
        // 出队操作改变front下标指向
        front = (front + 1) % queue.length;
        return deQueueElement;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 输出队列
     */
    @Override
    public void output(String message) {
        System.out.println(message);
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        System.out.print("front ➡︎ | ");
        for (int i = front; i != rear; i = (i + 1) % queue.length) {
            if (i == rear - 1) System.out.print(queue[i]);
            else System.out.print(queue[i] + " ← ");
        }
        System.out.println(" | ⬅︎ rear");
    }
}
