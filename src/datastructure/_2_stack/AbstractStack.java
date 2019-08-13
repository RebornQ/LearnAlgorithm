package datastructure._2_stack;

public abstract class AbstractStack<T, R> implements MyStack<T>{

    /**
     * 栈顶指针，-1或null代表空栈
     */
    protected R top;

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
        output("输出当前栈：");
    }

}
