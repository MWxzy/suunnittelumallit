package builder.computers;

public class OfficeComputerBuilder implements ComputerBuilder {

    private final Computer computer = new Computer();

    @Override
    public void buildProcessor() {
        computer.setProcessor("Intel Core i5-13400");
    }

    @Override
    public void buildRAM() {
        computer.setRam(16);
    }

    @Override
    public void buildHardDrive() {
        computer.setHardDrive("512 GB SSD");
    }

    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCard("Intel UHD Graphics 730");
    }

    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem("Windows 11 Home");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
