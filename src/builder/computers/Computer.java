package builder.computers;

public class Computer {

    private String processor;
    private int ram;
    private String hardDrive;
    private String graphicsCard;
    private String operatingSystem;

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getProcessor() {
        return processor;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRam() {
        return ram;
    }

    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }

    public String getHardDrive() {
        return hardDrive;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public String toString() {
        return "Processor: " + processor + "\n"
                + "RAM: " + ram + " GB\n"
                + "Hard drive: " + hardDrive + "\n"
                + "Graphics card: " + graphicsCard + "\n"
                + "Operating system: " + operatingSystem;
    }
}
