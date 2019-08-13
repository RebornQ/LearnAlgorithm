package datastructure._2_stack;

import datastructure._2_stack.exception.EmptyStackException;
import datastructure._2_stack.exception.StackException;

public class LinkedStack<T, R extends LinkedStack.Node<T>> extends AbstractStack<T, R> {

    // 定义一个节点类
    protected static class Node<U> {
        U data;
        Node<U> next;

        public Node() {
            data = null;
            next = null;
        }

        public Node(U data, Node<U> next) {
            this.data = data;
            this.next = next;
        }

        public boolean isEmpty() {
            return data == null && next == null;
        }
    }

    // 栈顶指针
//    private Node<T> top;

    LinkedStack() {
        top = (R) new Node<>();
    }

    @Override
    public boolean isEmpty() {
        return top.isEmpty();
    }

    @Override
    public void push(T data) throws StackException {
        if (data == null) throw new StackException("坏人！想骗吃骗喝？");
        top = (R) new Node<>(data, top);    // 更新栈顶
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T result = top.data;
        return result;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T result = top.data;
        if (!top.isEmpty()) {
            top = (R) top.next;
        }
        return result;
    }

    @Override
    void output(String message) {
        System.out.println(message);
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Node<T> temp = top;
        System.out.print("top ➡︎ | ");
        while (temp != null && !temp.isEmpty()) {
            if (temp.next.isEmpty()) System.out.print(temp.data);
            else System.out.print(temp.data+" → ");
            temp = temp.next;
        }
        System.out.println(" | ⬅︎ bottom");
    }
}
