package adapter.calendar;

public class Main {

    public static void main(String[] args) {
        NewDateInterface date = new CalendarToNewDateAdapter();

        date.setYear(2024);
        date.setMonth(2);
        date.setDay(28);
        printDate("Initial date", date);

        date.advanceDays(1);
        printDate("After 1 day", date);

        date.advanceDays(1);
        printDate("After another day", date);

        date.setYear(2026);
        date.setMonth(12);
        date.setDay(31);
        printDate("End of year", date);

        date.advanceDays(1);
        printDate("After 1 day", date);

        date.advanceDays(30);
        printDate("After 30 days", date);
    }

    private static void printDate(String label, NewDateInterface date) {
        System.out.printf("%s: %04d-%02d-%02d%n",
                label, date.getYear(), date.getMonth(), date.getDay());
    }
}
