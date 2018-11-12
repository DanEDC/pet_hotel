import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {

    public Validation() {
    }

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

    public LocalDate checkInDateValidation(Scanner scanner, LocalDate roomOccupiedFromDate, LocalDate roomOccupiedToDate) {
        boolean success = false;
        while (!success) {
            String date = checkDateFormat(scanner);
            LocalDate dateParse = LocalDate.parse(date);
            if (roomOccupiedFromDate == null) {
                if (dateParse.isBefore(LocalDate.now())) {
                    System.out.println("The date must be equal/greater then " + LocalDate.now() + ":");
                } else {
                    success = true;
                    return dateParse;
                }
            } else if (LocalDate.now().isBefore(roomOccupiedFromDate)) {
                if ((dateParse.isBefore(LocalDate.now()) || ((dateParse.isAfter(roomOccupiedFromDate.minusDays(1))) && (dateParse.isBefore(roomOccupiedToDate))))) {
                    System.out.println("The date must be between " + LocalDate.now() + " - " + (roomOccupiedFromDate.minusDays(1))
                            + ", or equal/greater then " + roomOccupiedToDate + ":");
                } else {
                    success = true;
                    return dateParse;
                }
            } else {
                if (dateParse.isBefore(roomOccupiedToDate)) {
                    System.out.println("The date must be equal/greater then " + roomOccupiedToDate + ":");
                } else {
                    success = true;
                    return dateParse;
                }
            }
        }
        return null;
    }

    public LocalDate checkOutDateValidation(Scanner scanner, LocalDate checkInDate, LocalDate roomOccupiedFromDate, LocalDate roomOccupiedToDate) {
        boolean success = false;
        while (!success) {
            String date = checkDateFormat(scanner);
            LocalDate dateParse = LocalDate.parse(date);
            if (roomOccupiedToDate == null) {
                if (!dateParse.isAfter(checkInDate)) {
                    System.out.println("The date must be greater then " + checkInDate + ":");
                } else {
                    success = true;
                    return dateParse;
                }
            } else {
                if (checkInDate.isBefore(roomOccupiedFromDate)) {
                    if (dateParse.isAfter(checkInDate) && (!dateParse.isAfter(roomOccupiedFromDate))) {
                        success = true;
                        return dateParse;
                    } else {
                        System.out.println("The date must be between " + (checkInDate.plusDays(1)) + " - " + roomOccupiedFromDate + ":");
                    }
                } else {
                    if (dateParse.isAfter(checkInDate)) {
                        success = true;
                        return dateParse;

                    } else {
                        System.out.println("The date must be greater then " + checkInDate + ":");
                    }
                }
            }
        }
        return null;
    }

}
