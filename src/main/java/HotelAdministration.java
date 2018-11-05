import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class HotelAdministration {

    private LinkedList<Pet> registeredPetsList;
    private LinkedList<Pet> reserveList;
    private int placesInHotel = 5;

    public HotelAdministration() {
        this.registeredPetsList = new LinkedList<>();
        this.reserveList = new LinkedList<>();
        hotelCheckOutCheckIn();
    }

    private Pet createNewPet(Scanner scanner) {
        System.out.println("Enter animal name:");
        String animalName = scanner.next();
        System.out.println("Enter animal type:");
        String animalType = scanner.next();
        System.out.println("Enter animal race:");
        String raceType = scanner.next();
        System.out.println("Enter animal age:");
        int animalAge = checkAgeFormat(scanner);
        System.out.println("Enter check in date, YYYY-MM-DD:");
        String checkInDate = checkDateFormat(scanner);
        LocalDate checkInDateParse = LocalDate.parse(checkInDate);
        System.out.println("Enter check out date, YYYY-MM-DD:");
        String checkOutDate = checkDateFormat(scanner);
        LocalDate checkOutDateParse = LocalDate.parse(checkOutDate);
        if (!checkOutDateParse.isAfter(checkInDateParse)) {
            System.out.println("Check out date must be at least one day after the check in date:");
            checkOutDate = checkDateFormat(scanner);
        }
        Service service = null;
        Pet newPet = new Pet(animalName, animalType, raceType, animalAge, checkInDate, checkOutDate, service);
        return newPet;
    }

    public boolean registerNewPet(Scanner scanner) {
        Pet pet = createNewPet(scanner);
        if (registeredPetsList.size() == placesInHotel && !findPet(pet, reserveList)) {
            reserveList.add(pet);
            if (checkAvailabilityBetweenLists(pet)) {
                System.out.println(
                        pet.getAnimalName() + " has been successful registered in the hotel from " + pet.getCheckInDate() + " to " + pet.getCheckOutDate());
                return true;
            } else {
                if (pet.getCheckInDate().isBefore(getEarlierCheckOutDateOfRegisteredPets())) {
                    pet.setCheckInDate(getEarlierCheckOutDateOfRegisteredPets());
                }
                if (!pet.getCheckOutDate().isAfter(pet.getCheckInDate())) {
                    pet.setCheckOutDate(pet.getCheckInDate().plusDays(1));
                }
                System.out.println("The hotel is currently fully booked, " + pet.getAnimalName()
                        + " has been added to the reserve list. Your Pet will be checked in to the hotel from " + pet
                        .getCheckInDate() + " to " + pet.getCheckOutDate() + " earliest. Please go to Option 2 in order to change check in and check out dates");
                return true;
            }
        } else if (registeredPetsList.size() == placesInHotel && findPet(pet, reserveList)) {
            System.out.println("Your pet is already added to the reserve list");
            return false;
        } else {
            if (!findPet(pet, registeredPetsList)) {
                registeredPetsList.add(pet);
                System.out.println(
                        pet.getAnimalName() + " has been successful registered in the hotel from " + pet
                                .getCheckInDate() + " to " + pet.getCheckOutDate());
                return true;
            } else {
                System.out.println(pet.getAnimalName() + " is already registered in the hotel");
                return false;
            }
        }
    }

    public void registerNewPet(Pet pet) {
        registeredPetsList.add(pet);
    }

    private boolean findPet(Pet pet, LinkedList<Pet> list) {
        for (Pet petToFind : list) {
            if (petToFind.equals(pet)) {
                return true;
            }
        }
        return false;
    }

    public void deletePetRegistration(Scanner scanner) {
        System.out.println("Enter Pet index number followed by his name:");
        int index = scanner.nextInt();
        String petName = scanner.next();
        if (registeredPetsList.get(index - 1).getAnimalName().equals(petName)) {
            registeredPetsList.remove(index - 1);
            System.out
                    .println(petName + " from position " + index + ". has been removed from the registered list");
            if (reserveList.size() > 0) {
                registeredPetsList.add(reserveList.getFirst());
                System.out.println(reserveList.getFirst().getAnimalName()
                        + " has been moved from reserve list to the registered list");
                reserveList.removeFirst();
            }
        } else if (reserveList.get(index - 1).getAnimalName().equals(petName)) {
            System.out.println(petName + " from position " + index + ". has been removed from the reserve list");
            reserveList.remove(index - 1);
        } else {
            System.out.println(petName + " is not found on position " + index + ".");
        }
    }

    public void modifyPetRegistration(Scanner scanner) {
        System.out.println("Enter Pet index number followed by his name:");
        int index = scanner.nextInt();
        String petName = scanner.next();
        if (registeredPetsList.get(index - 1).getAnimalName().equals(petName)) {
            Pet petToChange = registeredPetsList.get(index - 1);
            System.out.println("Please select new details for " + petName + ":");
            System.out.println("Enter animal type:");
            petToChange.setAnimalType(scanner.next());
            System.out.println("Enter animal race:");
            petToChange.setRaceType(scanner.next());
            System.out.println("Enter animal age:");
            petToChange.setAnimalAge(scanner.nextInt());
            System.out.println("Change data succeed:");
            System.out.println(petToChange);
        } else if (reserveList.get(index - 1).getAnimalName().equals(petName)) {
            Pet petToChange = reserveList.get(index - 1);
            System.out.println("Please select new details for " + petName + ":");
            System.out.println("Enter animal type:");
            petToChange.setAnimalType(scanner.next());
            System.out.println("Enter animal race:");
            petToChange.setRaceType(scanner.next());
            System.out.println("Enter animal age:");
            petToChange.setAnimalAge(checkAgeFormat(scanner));
            System.out.println("Enter new check in date. The date must be equal or after " + getEarlierCheckOutDateOfRegisteredPets() + ":");
            LocalDate checkInDateParse = checkInDateValidation(scanner);
            petToChange.setCheckInDate(checkInDateParse);
            System.out.println("Enter new check out date:");
            LocalDate checkOutDateParse = checkOutDateValidation(scanner, checkInDateParse);
            petToChange.setCheckOutDate(checkOutDateParse);
            System.out.println("Change data succeed:");
            System.out.println(petToChange);
            checkAvailabilityBetweenLists(petToChange);
        } else {
            System.out.println(petName + " is not found on position " + index + ".");
        }
    }

    public void printRegisteredPets() {
        System.out.println("Hotel list:");
        for (int i = 0; i < registeredPetsList.size(); i++) {
            Pet petToPrint = registeredPetsList.get(i);
            System.out.println((i + 1) + ". " + petToPrint);
        }
        System.out.println("Reserve list:");
        for (int i = 0; i < reserveList.size(); i++) {
            Pet petToPrint = reserveList.get(i);
            System.out.println((i + 1) + ". " + petToPrint);
        }
    }

    private void hotelCheckOutCheckIn() {
        ListIterator<Pet> listIterator = registeredPetsList.listIterator();
        while (listIterator.hasNext()) {
            if (LocalDate.now().isBefore(listIterator.next().getCheckOutDate())) {
            } else {
                System.out.println(listIterator.previous().getAnimalName() + " has been checked out from the hotel");
                listIterator.remove();
            }
        }
        ListIterator<Pet> reserveListIterator = reserveList.listIterator();
        while (reserveListIterator.hasNext() && registeredPetsList.size() < placesInHotel) {
            registeredPetsList.add(reserveListIterator.next());
            System.out.println(
                    reserveListIterator.previous().getAnimalName() + " has been checked in to the hotel");
            reserveListIterator.remove();
        }
    }

    private LocalDate getEarlierCheckOutDateOfRegisteredPets() {
        ListIterator<Pet> listIterator = registeredPetsList.listIterator();
        LocalDate earlierCheckOutDate = registeredPetsList.getFirst().getCheckOutDate();
        while (listIterator.hasNext()) {
            int check = listIterator.next().getCheckOutDate().compareTo(earlierCheckOutDate);
            if (check < 0) {
                earlierCheckOutDate = listIterator.previous().getCheckOutDate();
            }
        }
        return earlierCheckOutDate;
    }

    private boolean checkAvailabilityBetweenLists(Pet pet) {
        for (int j = 0; j < registeredPetsList.size(); j++) {
            if ((pet.getCheckInDate().isBefore(registeredPetsList.get(j).getCheckInDate()))
                    && (!pet.getCheckOutDate().isAfter(registeredPetsList.get(j).getCheckInDate()))) {
                reserveList.addFirst(registeredPetsList.get(j));
                registeredPetsList.remove(j);
                registeredPetsList.add(pet);
                reserveList.remove(pet);
                return true;
            }

        }
        return false;
    }

    private String checkDateFormat(Scanner scanner) {
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

    private int checkAgeFormat(Scanner scanner) {
        boolean success = false;
        while (!success) {
            try {
                int age = scanner.nextInt();
                success = true;
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Must be a number. Enter animal age:");
                scanner.nextLine();
            }
        }
        return -1;
    }

    private LocalDate checkInDateValidation(Scanner scanner) {
        boolean success = false;
        while (!success) {
            String date = checkDateFormat(scanner);
            LocalDate dateParse = LocalDate.parse(date);
            if (dateParse.isBefore(getEarlierCheckOutDateOfRegisteredPets())) {
                System.out.println("The date must be equal or after " + getEarlierCheckOutDateOfRegisteredPets() + ":");
            } else {
                success = true;
                return dateParse;
            }

        }
        return null;
    }

    private LocalDate checkOutDateValidation(Scanner scanner, LocalDate checkInDate) {
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








