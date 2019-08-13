package datastructure._2_stack;

import datastructure._2_stack.exception.EmptyStackException;

import java.lang.reflect.Array;

public class SequenceStack<T, R extends Integer> extends AbstractStack<T, R> {

    /**
     * 栈顶指针，-1代表空栈
     */
//    private int top = -1;

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
        top = (R) new Integer(-1);
    }

    @SuppressWarnings("unchecked")
    public SequenceStack(Class<T> type, int capacity) {
//        array = (T[]) new Object[capacity];
        this.type = type;
        array = (T[]) Array.newInstance(type, capacity);
        top = (R) new Integer(-1);
    }

    @Override
    public boolean isEmpty() {
        return top.equals(-1);
    }

    @Override
    public void push(T data) {
        // 判断是否达到容量限制
        if (top.equals(array.length - 1))   // 超出栈容量
//            throw new StackOverflowError("爆水管啦！");
            // 扩容
            ensureCapacity(array.length * 2 + 1);
        // 从栈顶添加元素
        top = (R) new Integer(top.intValue() + 1);
        array[top.intValue()] = data;
        size++;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--;
        T temp = array[top.intValue()];
        top = (R) new Integer(top.intValue() - 1);
        return temp;
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

    @Override
    void output(String message) {
        System.out.println(message);
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        System.out.print("top ➡︎ | ");
        for (int i = top; i > 0; i--) {
            if (i == 1) System.out.print(array[i]);
            else System.out.print(array[i] + " → ");
        }
        System.out.println(" | ⬅︎ bottom");
    }
}
