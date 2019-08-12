package datastructure.queue;

import datastructure.queue.exception.EmptyQueueException;
import datastructure.queue.exception.QueueException;
import datastructure.queue.exception.QueueOverFlowError;

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
public class SequenceQueue<T> implements MyQueue<T> {

    private Class<T> type;

    private T[] array;

    /**
     * 队列的初始长度为10
     */
    private final int CAPACITY_DEFAULT = 10;

    private int front, rear;

    @SuppressWarnings("unchecked")
    public SequenceQueue(Class<T> type) {
        this.type = type;
        array = (T[]) Array.newInstance(type, CAPACITY_DEFAULT);
        front = rear = 0;
    }

    @SuppressWarnings("unchecked")
    public SequenceQueue(Class<T> type, int capacity) {
        this.type = type;
        array = (T[]) Array.newInstance(type, capacity);
        front = rear = 0;
    }

    @Override
    public void enQueue(T data) throws QueueException {
        // 约定 front=(rear+1)%size 时队列为满
        if ((rear + 1) % array.length == front) {
            throw new QueueOverFlowError();
        }
        array[rear] = data;
        // 入队操作改变rear下标指向
        rear = (rear + 1) % array.length;
    }

    @Override
    public T deQueue() throws QueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T deQueueElement = array[front];
        // 出队操作改变front下标指向
        front = (front + 1) % array.length;
        return deQueueElement;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 输出队列
     */
    public void output(String message) {
        System.out.println(message);
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        for (int i = front; i != rear; i = (i + 1) % array.length) {
            System.out.println(array[i]);
        }
    }

    public void output() {
        output("输出当前队列：");
    }
}
