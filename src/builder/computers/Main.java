package builder.computers;

public class Main {

    public static void main(String[] args) {
        ComputerDirector gamingDirector = new ComputerDirector(new GamingComputerBuilder());
        Computer gamingComputer = gamingDirector.constructComputer();
        System.out.println("Gaming computer:");
        System.out.println(gamingComputer);

        System.out.println();

        ComputerDirector officeDirector = new ComputerDirector(new OfficeComputerBuilder());
        Computer officeComputer = officeDirector.constructComputer();
        System.out.println("Office computer:");
        System.out.println(officeComputer);
    }
}
