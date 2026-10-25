package memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Memento implements IMemento {

    private static final String[] COLOR_NAMES = {"Red", "Blue", "Yellow"};
    private static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private final int[] options;
    private final boolean isSelected;
    private final LocalDateTime timestamp;

    public Memento(int[] options, boolean isSelected) {
        this.options = options.clone();
        this.isSelected = isSelected;
        this.timestamp = LocalDateTime.now();
        System.out.println("Memento created: " + getDescription());
    }

    public int[] getOptions() {
        return options.clone();
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String getDescription() {
        return String.format("Box 1: %s, Box 2: %s, Box 3: %s, Checkbox: %s",
                colorName(options[0]),
                colorName(options[1]),
                colorName(options[2]),
                isSelected ? "checked" : "unchecked");
    }

    private static String colorName(int index) {
        if (index >= 0 && index < COLOR_NAMES.length) {
            return COLOR_NAMES[index];
        }
        return "Unknown";
    }

    @Override
    public String toString() {
        return timestamp.format(TIME_FORMAT) + "  " + getDescription();
    }
}
