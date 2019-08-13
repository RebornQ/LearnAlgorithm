package datastructure._3_queue;

public abstract class AbstractQueue<T, R> implements MyQueue<T> {

    protected R front, rear;

    /**
     * 自定义输出
     *
     * @param message 输出的信息
     */
    abstract void output(String message);

    /**
     * 默认输出
     */
    public void output() {
        output("输出当前队列：");
    }
}
