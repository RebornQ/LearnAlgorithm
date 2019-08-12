package datastructure.stack;

import java.util.EmptyStackException;

public class SequenceStack<T> implements MyStack<T> {

    /**
     * 栈顶指针，-1代表空栈
     */
    private int top = -1;

    /**
     * 栈的初始容量为10
     */
    private final int CAPACITY_DEFAULT = 10;

    /**
     * 数组实现顺序栈
     */
    private T[] array;

    private int size = 0;

    @SuppressWarnings("unchecked")
    public SequenceStack() {
        array = (T[]) new Object[this.CAPACITY_DEFAULT];
    }

    @SuppressWarnings("unchecked")
    public SequenceStack(int capacity) {
        array = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void push(T data) {
        // 判断是否达到
        if (top == array.length - 1) {  // 超出栈容量
            throw new StackOverflowError("爆水管啦！");
        } else {
            array[++top] = data;
            size++;
        }
    }

    @Override
    public T peek() {
        if (top == -1) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    @Override
    public T pop() {
        if (top == -1) {
            throw new EmptyStackException();
        }
        size--;
        return array[top--];
    }

    /**
     * 获取栈的大小
     * @return 栈的大小
     */
    public int size() {
        return size;
    }
}
