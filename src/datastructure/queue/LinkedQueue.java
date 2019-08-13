package datastructure.queue;

import datastructure.queue.exception.EmptyQueueException;

public class LinkedQueue<T> implements MyQueue<T> {

    private static class Node<U> {
        U data;
        Node<U> next;

        public Node(U data, Node<U> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> front, rear;

    public LinkedQueue() {
        // 初始化空队列
        front = rear = null;
    }

    @Override
    public void enQueue(T data) {
        Node<T> node = new Node<>(data, null);
        if (front == null) {    // 空队列插入元素
            front = node;
        } else {    // 非空队列，队尾插入元素
            rear.next = node;
        }
        rear = node;
//        rear = rear.next; // 不可用，因为空队列插入时队尾指针null，访问rear.next会报空指针异常
    }

    @Override
    public T deQueue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T node = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return node;
    }

    @Override
    public boolean isEmpty() {
        return front == null && rear == null;
    }

    @Override
    public void output(String message) {
        System.out.println(message);
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        Node<T> temp = front;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    @Override
    public void output() {
        output("输出当前队列：");
    }
}
