package base.datastructure._3_queue.exception;

public class EmptyQueueException extends QueueException {

    public EmptyQueueException() {
        super("坏了！队列里的奶酪被杰瑞偷偷吃光了！");
    }

    public EmptyQueueException(String message) {
        super(message);
    }
}
