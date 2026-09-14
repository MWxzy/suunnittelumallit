package decorator;

public class Main {
    public static void main(String[] args) {

        Printer printer = new BasicPrinter();
        printer.print("Hello World!");

        System.out.println("\n Decorated (XML then Encrypted) ");

        Printer printer2 = new EncryptedPrinter(new XMLPrinter(new BasicPrinter()));
        printer2.print("Hello World!");

        System.out.println("\n Decorated (Encrypted then XML) ");
        Printer printer3 = new XMLPrinter(new EncryptedPrinter(new BasicPrinter()));
        printer3.print("Hello World!");
    }
}