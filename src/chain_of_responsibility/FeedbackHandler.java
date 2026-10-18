package chain_of_responsibility;

public abstract class FeedbackHandler {

    private FeedbackHandler nextHandler;

    private int handledCount;

    public FeedbackHandler setNext(FeedbackHandler next) {
        if (next == null) {
            throw new IllegalArgumentException("The next handler cannot be null.");
        }
        this.nextHandler = next;
        return next;
    }

    public FeedbackHandler getNext() {
        return nextHandler;
    }

    public final void handle(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null.");
        }

        if (canHandle(message)) {
            handledCount++;
            process(message);
        } else if (nextHandler != null) {
            nextHandler.handle(message);
        } else {
            reportUnhandled(message);
        }
    }

    protected boolean canHandle(Message message) {
        return message.getType() == getHandledType();
    }

    protected abstract void process(Message message);

    protected abstract MessageType getHandledType();

    protected void reportUnhandled(Message message) {
        System.out.println("   [!] No handler in the chain is responsible for "
                + message.getType().getDisplayName() + ".");
        System.out.println("       Please add a handler for this feedback type.");
    }

    public String getName() {
        return getClass().getSimpleName();
    }

    public int getHandledCount() {
        return handledCount;
    }

    protected void log(String text) {
        System.out.println("   [" + getName() + "] " + text);
    }
}
