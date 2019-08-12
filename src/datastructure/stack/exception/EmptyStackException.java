package datastructure.stack.exception;

public class EmptyStackException extends StackException {

    public EmptyStackException() {
        super("骗子！栈里没有东西还叫我去拿！");
    }

    public EmptyStackException(String message) {
        super(message);
    }
}