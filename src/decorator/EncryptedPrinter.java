package decorator;

public class EncryptedPrinter extends PrinterDecorator {
    public EncryptedPrinter(Printer wrapped) {
        super(wrapped);
    }

    @Override
    public void print(String message) {
        wrapped.print(encrypt(message));
    }

    // Simple Caesar cipher (shift = 3) for demonstration.
    // In a real application, use a proper encryption library.
    private String encrypt(String message) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encrypted.append((char) ((c - base + 3) % 26 + base));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }
}