package datastructure._3_queue;

public interface MyQueue<T> {

    // 1.https://blog.csdn.net/javazejian/article/details/53375004

    /**
     * 入队
     *
     * @param data 入队元素
     */
    void enQueue(T data);

    /**
     * 出队
     *
     * @return 出队元素
     */
    T deQueue();

    /**
     * 队列是否为空
     */
    boolean isEmpty();
}
