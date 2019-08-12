package datastructure.stack;

public class LinkedStack<T> implements MyStack<T> {

    // 定义一个节点类
    private static class Node<U> {
        private U data;
        private Node<U> next;

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
    private Node<T> top;

    LinkedStack() {
        top = new Node<>();
    }

    @Override
    public boolean isEmpty() {
        return top.isEmpty();
    }

    @Override
    public void push(T data) throws StackException {
        if (data == null) throw new StackException("Data不能为空！");
        top = new Node<>(data, top);    // 更新栈顶
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException("栈是空的！");
        }
        T result = top.data;
        return result;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException("栈是空的！");
        }
        T result = top.data;
        if (!top.isEmpty()) {
            top = top.next;
        }
        return result;
    }
}
