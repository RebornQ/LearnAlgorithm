package datastructure.stack;

import java.lang.reflect.Array;
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

    private Class<T> type;

    @SuppressWarnings("unchecked")
    public SequenceStack(Class<T> type) {
//        array = (T[]) new Object[this.CAPACITY_DEFAULT];
        this.type = type;
        array = (T[]) Array.newInstance(type, this.CAPACITY_DEFAULT);
    }

    @SuppressWarnings("unchecked")
    public SequenceStack(Class<T> type, int capacity) {
//        array = (T[]) new Object[capacity];
        this.type = type;
        array = (T[]) Array.newInstance(type, capacity);
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public void push(T data) {
        // 判断是否达到容量限制
        if (top == array.length - 1)   // 超出栈容量
//            throw new StackOverflowError("爆水管啦！");
            // 扩容
            ensureCapacity(array.length * 2 + 1);
        // 从栈顶添加元素
        array[++top] = data;
        size++;
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

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int capacity) {
        // 如果需要扩展的容量比现在数组容量还小，则无需扩容
        if (capacity < size) return;

        T[] oldArray = array;
        array = (T[]) Array.newInstance(type, capacity);
        // 复制元素到扩容后的新数组
        for (int i = 0; i < size; i++) {
            array[i] = oldArray[i];
        }
        oldArray = null;
    }

    /**
     * 获取栈的大小
     *
     * @return 栈的大小
     */
    public int size() {
        return size;
    }
}
