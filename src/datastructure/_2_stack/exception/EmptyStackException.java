package datastructure._2_stack.exception;

public class EmptyStackException extends StackException {

    public EmptyStackException() {
        super("坏了！栈里的奶酪被杰瑞偷偷吃光了！");
    }

    public EmptyStackException(String message) {
        super(message);
    }
}