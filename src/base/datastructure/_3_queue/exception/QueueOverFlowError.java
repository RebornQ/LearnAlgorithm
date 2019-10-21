package base.datastructure._3_queue.exception;

public class QueueOverFlowError extends QueueException {

    public QueueOverFlowError() {
        super("不要再喂我啦！好撑啊！吃不下啦！");
    }

    public QueueOverFlowError(String message) {
        super(message);
    }
}
