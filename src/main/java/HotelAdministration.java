import java.time.LocalDate;
import java.util.*;

public class HotelAdministration {

    private LinkedList<Pet> registeredPetsList;
    private ArrayList<Room> roomsList;
    private int placesInHotel = 5;
    private Validation validation = new Validation();
    private Room room = new Room();

    public HotelAdministration() {
        this.registeredPetsList = new LinkedList<>();
        this.roomsList = new ArrayList<>();
        roomsInHotel(placesInHotel);
    }

    private void roomsInHotel(int placesInHotel) {
        for (int i = 0; i < placesInHotel; i++) {
            Room room = new Room((i + 1));
            roomsList.add(room);
        }
    }

    public Pet registerPetInHotel(Scanner scanner) {
        room.printHotelRoomsAvailability(roomsList);
        System.out.println("---------");
        System.out.println("Enter room number you want to book, choose between 1 and " + placesInHotel + ":");
        int roomNumber = validation.roomNumberValidation(scanner, placesInHotel);
        Room chosenRoom = roomsList.get(roomNumber - 1);
        System.out.println("Please check room " + roomNumber + " availability:");
        room.printSingleRoomAvailability(chosenRoom);
        System.out.println("---------");
        System.out.println("Enter check in date, YYYY-MM-DD:");
        LocalDate checkInDate = validation.checkInDateValidation(scanner, chosenRoom);
        System.out.println("Enter check out date, YYYY-MM-DD:");
        LocalDate checkOutDate = validation.checkOutDateValidation(scanner, checkInDate, chosenRoom);
        System.out.println("Enter animal name:");
        String animalName = validation.checkStringFormat(scanner);
        System.out.println("Enter animal type:");
        String animalType = validation.checkStringFormat(scanner);
        System.out.println("Enter animal race:");
        String raceType = validation.checkStringFormat(scanner);
        System.out.println("Enter animal age:");
        int animalAge = validation.animalAgeValidation(scanner);
        Pet newPet = new Pet(animalName, animalType, raceType, animalAge, roomNumber, checkInDate, checkOutDate);
        newPet.setService(addServiceToList(newPet, scanner));
        registeredPetsList.add(newPet);
        RoomBookedDates roomBookedDates = new RoomBookedDates(checkInDate, checkOutDate);
        chosenRoom.getBookedDates().add(roomBookedDates);
        System.out.println(newPet.getAnimalName() + " has been successful registered in the hotel from " + newPet.getCheckInDate() + " to " + newPet.getCheckOutDate());
        return newPet;
    }

    public void deletePetRegistration(Scanner scanner) {
        System.out.println("Enter Pet index number followed by his name:");
        int index = validation.checkIntFormat(scanner);
        String petName = scanner.next();
        try {
            if (registeredPetsList.get(index - 1).getAnimalName().equals(petName)) {
                Pet petToDelete = registeredPetsList.get(index - 1);
                room.deleteBookedDates(roomsList, petToDelete);
                registeredPetsList.remove(index - 1);
                System.out.println(petName + " from position " + index + ". has been removed from the registered list");
            } else {
                System.out.println(petName + " is not found on position " + index + ".");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(petName + " is not found on position " + index + ".");
        }
    }

    public void modifyPetData(Scanner scanner) {
        System.out.println("Enter Pet index number followed by his name:");
        int index = validation.checkIntFormat(scanner);
        String petName = scanner.next();
        try {
            if (registeredPetsList.get(index - 1).getAnimalName().equals(petName)) {
                Pet petToDelete = registeredPetsList.get(index - 1);
                room.deleteBookedDates(roomsList, petToDelete);
                registeredPetsList.remove(index - 1);
                Pet newPet = registerPetInHotel(scanner);
                registeredPetsList.add((index - 1), newPet);
                registeredPetsList.removeLast();
            } else {
                System.out.println(petName + " is not found on position " + index + ".");
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(petName + " is not found on position " + index + ".");
        }
        }

    public void printRegisteredPets() {
        System.out.println("List of registered pets in hotel:");
        if (registeredPetsList.size() == 0) {
            System.out.println("There are no registered pets in the hotel at the moment");
        } else {
            for (int i = 0; i < registeredPetsList.size(); i++) {
                Pet petToPrint = registeredPetsList.get(i);
                System.out.print((i + 1) + ". " + petToPrint.printPet(petToPrint));
                System.out.println();
            }
        }
    }

    public void hotelCheckOut() {
        ListIterator<Pet> listIterator = registeredPetsList.listIterator();
        while (listIterator.hasNext()) {
            if (LocalDate.now().isEqual(listIterator.next().getCheckOutDate())) {
                room.deleteBookedDates(roomsList, listIterator.previous());
                System.out.println(listIterator.next().getAnimalName() + " has been checked out from the hotel");
                listIterator.remove();
            }
        }
    }

    private ArrayList<Service> addServiceToList(Pet pet, Scanner scanner) {
        ArrayList<Service> serviceList = pet.getService();
        Service[] list = Service.values();
        for (Service service : list) {
            System.out.println("Type Y to include " + service + " into service list, neither type N:");
            if (validation.checkYAndNFormat(scanner)) {
                serviceList.add(service);
            }
        }
        return serviceList;
    }
}








