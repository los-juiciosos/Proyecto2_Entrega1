package Interfaz.Admin_Local;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeeksInEachMonthExample {

    public static void main(String[] args) {
        // Specify the year you are interested in
        int year = 2023;

        // Iterate over each month
        for (Month month : Month.values()) {
            // Get the first day of the month
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

            // Determine the week fields using the ISO calendar system
            WeekFields weekFields = WeekFields.of(Locale.getDefault());

            // Get the last day of the month
            LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());

            // Get the week number for the last day of the month
            int lastWeekNumber = lastDayOfMonth.get(weekFields.weekOfWeekBasedYear());

            // Print the number of weeks in the month
            System.out.println("Number of weeks in " + month + ": " + lastWeekNumber);
        }
    }
}
