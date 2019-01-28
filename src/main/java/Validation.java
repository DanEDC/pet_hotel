import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.LinkedList;
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

    public String checkStringFormat(Scanner scanner) {
        boolean success = false;
        while (!success) {
            String text = scanner.next();
            try {
                Integer.parseInt(text);
                System.out.println("Please type a text, instead of a number:");
            } catch (NumberFormatException e) {
                success = true;
                return text;
            }
        }
        return null;
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

    public LocalDate checkInDateValidation(Scanner scanner, Room room) {
        LinkedList<RoomAvailableDates> r = room.generateRoomAvailabilityDates(room);
        boolean success = false;
        while (!success) {
            String date = checkDateFormat(scanner);
            LocalDate dateParse = LocalDate.parse(date);
            int check = 0;
            for (int i = 0; i < r.size(); i++) {
                if (r.get(i).getFreeTo() != null) {
                    if (dateParse.isEqual(r.get(i).getFreeTo())) {
                        System.out.println("Check in date must be at least " + r.get(i).getFreeTo().minusDays(1) + ":");
                        check = 1;
                    } else if (!dateParse.isBefore(r.get(i).getFreeFrom()) && (!dateParse.isAfter(r.get(i).getFreeTo()))) {
                        success = true;
                        return dateParse;
                    }
                }
                if (r.get(i).getFreeTo() == null) {
                    if (!dateParse.isBefore(r.get(i).getFreeFrom())) {
                        success = true;
                        return dateParse;
                    }
                }
            }
            if (!success) {
                if (check == 0) {
                    System.out.println("The date must be included in available dates:");
                    room.printSingleRoomAvailability(room);
                }
            }
        }
        return null;
    }

    public LocalDate checkOutDateValidation(Scanner scanner, LocalDate checkInDate, Room room) {
        LinkedList<RoomAvailableDates> r = room.generateRoomAvailabilityDates(room);
        boolean success = false;
        while (!success) {
            String date = checkDateFormat(scanner);
            LocalDate dateParse = LocalDate.parse(date);
            for (int i = 0; i < r.size(); i++) {
                if (r.get(i).getFreeTo() != null) {
                    if ((dateParse.isAfter(checkInDate)) && (!dateParse.isAfter(r.get(i).getFreeTo()))) {
                        success = true;
                        return dateParse;
                    }
                }
                if (r.get(i).getFreeTo() == null) {
                    if (dateParse.isAfter(checkInDate) && (!checkInDate.isBefore(r.get(i).getFreeFrom()))) {
                        success = true;
                        return dateParse;
                    }
                }
            }
            if (!success) {
                for (int j = 0; j < r.size(); j++) {
                    if (!r.get(j).getFreeFrom().isAfter(checkInDate) && r.get(j).getFreeTo() != null && r.get(j).getFreeTo().isAfter(checkInDate)) {
                        System.out.println("The date must be greater then " + checkInDate + " and lower/equal to " + r.get(j).getFreeTo() + ":");
                    } else if (!r.get(j).getFreeFrom().isAfter(checkInDate) && r.get(j).getFreeTo() == null) {
                        System.out.println("The date must be greater then " + checkInDate + ":");
                    }
                }

            }
        }
        return null;
    }

    public boolean checkYAndNFormat(Scanner scanner) {
        boolean success = false;
        while (!success) {
            String type = scanner.next();
            if (type.equals("Y")) {
                success = true;
                return true;
            } else if (type.equals("N")) {
                success = true;
                return false;
            } else {
                System.out.println("Please type Y or N:");
            }
        }
        return false;
    }

}
