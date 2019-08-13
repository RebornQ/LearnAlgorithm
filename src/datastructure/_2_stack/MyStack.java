package datastructure._2_stack;

import datastructure._2_stack.exception.StackException;

public interface MyStack<T> {
    // 1.https://blog.csdn.net/javazejian/article/details/53362993
    // 2.https://segmentfault.com/a/1190000002516799
    // 3.https://www.cnblogs.com/qianguyihao/p/4795984.html

    /**
     * 栈是否为空
     */
    boolean isEmpty();

    /**
     * 元素入栈
     * @param data 入栈的元素
     */
    void push(T data) throws StackException;

    /**
     * 返回栈顶元素，未出栈
     * @return 栈顶元素
     */
    T peek();

    /**
     * 出栈，返回栈顶元素，同时从栈中移除该元素
     * @return 栈顶元素
     */
    T pop();
}
