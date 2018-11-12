import java.time.LocalDate;
import java.util.*;

public class HotelAdministration {

    private LinkedList<Pet> registeredPetsList;
    private LinkedList<Pet> reserveList;
    private ArrayList<Room> roomsList;
    private int placesInHotel = 5;
    private Validation validation = new Validation();
    private Room room = new Room();

    public HotelAdministration() {
        this.registeredPetsList = new LinkedList<>();
        this.reserveList = new LinkedList<>();
        this.roomsList = new ArrayList<>();
        roomsInHotel(placesInHotel);
        hotelCheckOutCheckIn();
    }

    private void roomsInHotel(int placesInHotel) {
        for (int i = 0; i < placesInHotel; i++) {
            Room room = new Room((i + 1));
            roomsList.add(room);
        }
    }

    public Pet registerPetInHotel(Scanner scanner) {
        room.printHotelRoomsAvailability(roomsList);
        System.out.println("Enter room number you want to book, choose between 1 and " + placesInHotel + ":");
        int roomNumber = validation.roomNumberValidation(scanner, placesInHotel);
        Room chosenRoom = roomsList.get(roomNumber);
        System.out.println("Please check room " + roomNumber + " availability:");
        room.printSingleRoomAvailability(roomsList, roomNumber);
        System.out.println("Enter check in date, YYYY-MM-DD:");
        LocalDate checkInDate = validation.checkInDateValidation(scanner, chosenRoom.getOccupiedFrom(), chosenRoom.getOccupiedTo());
        System.out.println("Enter check out date, YYYY-MM-DD:");
        LocalDate checkOutDate = validation.checkOutDateValidation(scanner, checkInDate, chosenRoom.getOccupiedFrom(), chosenRoom.getOccupiedTo());
        System.out.println("Enter animal name:");
        String animalName = scanner.next();
        System.out.println("Enter animal type:");
        String animalType = scanner.next();
        System.out.println("Enter animal race:");
        String raceType = scanner.next();
        System.out.println("Enter animal age:");
        int animalAge = validation.animalAgeValidation(scanner);
        Service service = null;
        Pet newPet = new Pet(animalName, animalType, raceType, animalAge, roomNumber, checkInDate, checkOutDate, service);
        registeredPetsList.add(newPet);
        if (chosenRoom.getOccupiedFrom() == null || checkInDate.isBefore(chosenRoom.getOccupiedFrom())) {
            chosenRoom.setOccupiedFrom(checkInDate);
        }
        if (chosenRoom.getOccupiedTo() == null || checkOutDate.isAfter(chosenRoom.getOccupiedTo())) {
            chosenRoom.setOccupiedTo(checkOutDate);
        }
        System.out.println(newPet.getAnimalName() + " has been successful registered in the hotel from " + newPet.getCheckInDate() + " to " + newPet.getCheckOutDate());
        return newPet;
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
            petToChange.setAnimalAge(validation.checkIntFormat(scanner));
            System.out.println("Enter new check in date. The date must be equal or after " + getEarlierCheckOutDateOfRegisteredPets() + ":");
            LocalDate checkInDateParse = validation.checkInDateValidation(scanner, getEarlierCheckOutDateOfRegisteredPets(), getEarlierCheckOutDateOfRegisteredPets());
            petToChange.setCheckInDate(checkInDateParse);
            System.out.println("Enter new check out date:");
            LocalDate checkOutDateParse = validation.checkOutDateValidation(scanner, checkInDateParse, checkInDateParse, checkInDateParse);
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



}








