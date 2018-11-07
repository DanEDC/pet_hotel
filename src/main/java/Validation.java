import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {

    public String checkDateFormat(Scanner scanner) {
        boolean success = false;
        while (!success) {
            try {
                String date = scanner.next();
                LocalDate.parse(date);
                success = true;
                return date;
            } catch (DateTimeParseException | NullPointerException e) {
                System.out.println("Wrong date format, please try again, YYYY-MM-DD:");
            }
        }
        return null;
    }

    public int checkIntFormat(Scanner scanner) {
        boolean success = false;
        while (!success) {
            try {
                int age = scanner.nextInt();
                success = true;
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Must be a number, please try again:");
                scanner.nextLine();
            }
        }
        return -1;
    }

    public int animalAgeValidation(Scanner scanner) {
        boolean success = false;
        while (!success) {
            int number = checkIntFormat(scanner);
            if (number < 0 || number > 30) {
                System.out.println("Please choose between number 0 and 30:");
            } else {
                success = true;
                return number;
            }
        }
        return -1;
    }

    public int roomNumberValidation(Scanner scanner, int roomsInHotel) {
        boolean success = false;
        while (!success) {
            int number = checkIntFormat(scanner);
            if (number < 1 || number > roomsInHotel) {
                System.out.println("There are " + roomsInHotel + " rooms in the hotel, please choose number between 1 and " + roomsInHotel + " :");
            } else {
                success = true;
                return number;
            }
        }
        return -1;
    }

    public LocalDate checkInDateValidation(Scanner scanner, LocalDate earlyCheckOutDate) {
        boolean success = false;
        while (!success) {
            String date = checkDateFormat(scanner);
            LocalDate dateParse = LocalDate.parse(date);
            if (dateParse.isBefore(earlyCheckOutDate)) {
                System.out.println("The date must be equal or after " + earlyCheckOutDate + ":");
            } else {
                success = true;
                return dateParse;
            }

        }
        return null;
    }

    public LocalDate checkOutDateValidation(Scanner scanner, LocalDate checkInDate) {
        boolean success = false;
        while (!success) {
            String date = checkDateFormat(scanner);
            LocalDate dateParse = LocalDate.parse(date);
            if (!dateParse.isAfter(checkInDate)) {
                System.out.println("Check out date must be at least one day after " + checkInDate + ":");
            } else {
                success = true;
                return dateParse;
            }
        }
        return null;
    }
}
