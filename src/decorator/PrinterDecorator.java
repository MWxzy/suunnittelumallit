package decorator;

public abstract class PrinterDecorator implements Printer {
    protected Printer wrapped;

    public PrinterDecorator(Printer wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void print(String message) {
        wrapped.print(message);
    }
}