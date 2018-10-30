import java.time.LocalDate;
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

    public void registerNewPet(Pet pet) {

        if (registeredPetsList.size() == placesInHotel && !findPet(pet, reserveList)) {
            reserveList.add(pet);
            System.out.println("The hotel is currently fully booked, " + pet.getAnimalName()
                    + " has been added to the reserve list");
        } else if (registeredPetsList.size() == placesInHotel && findPet(pet, reserveList)) {
            System.out.println("Your pet is already added to the reserve list");
        } else {
            if (!findPet(pet, registeredPetsList)) {
                registeredPetsList.add(pet);
                System.out.println(
                        pet.getAnimalName() + " has been successful registered in the hotel from " + pet
                                .getCheckInDate()
                                + " to " + pet.getCheckOutDate());
            } else {
                System.out.println(pet.getAnimalName() + " is already registered in the hotel.");
            }
        }
    }

    private boolean findPet(Pet pet, LinkedList<Pet> list) {
        for (Pet petToFind : list) {
            if (petToFind.equals(pet)) {
                return true;
            }
        }
        return false;
    }

    public void deletePetRegistration(int index, String petName) {
        if (registeredPetsList.get(index - 1).getAnimalName().equals(petName)) {
            registeredPetsList.remove(index - 1);
            System.out
                    .println(petName + " from position " + index + ". has been removed from the hotel list");
            if (reserveList.size() > 0) {
                registeredPetsList.add(reserveList.getFirst());
                System.out.println(reserveList.getFirst().getAnimalName()
                        + " has been moved from reserve list to the hotel list");
                reserveList.removeFirst();
            }
        } else {
            System.out.println(
                    petName + " is not found on position " + index + ". Delete registration failed.");
        }
    }

    public void modifyPetRegistration(int index, String petName, Scanner scanner) {
        if (registeredPetsList.get(index - 1).getAnimalName().equals(petName)) {
            Pet petToChange = registeredPetsList.get(index - 1);
            System.out.println("Please select new details for " + petName + ":");
            System.out.println("Enter animal type:");
            petToChange.setAnimalType(scanner.next());
            System.out.println("Enter animal race:");
            petToChange.setRaceType(scanner.next());
            System.out.println("Enter animal age:");
            petToChange.setAnimalAge(scanner.nextInt());
            System.out.println("Enter check out date");
            petToChange.setCheckOutDate(LocalDate.parse(scanner.next()));
            System.out.println("Change data succeed:");
            System.out.println(petToChange);
            hotelCheckOutCheckIn();
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

    public Pet createNewPetAccount(String animalName, String animalType, String raceType,
                                   int animalAge, String checkInDate, String checkOutDate, Service service) {
        Pet newPet = new Pet(animalName, animalType, raceType, animalAge, checkInDate, checkOutDate,
                service);
        return newPet;
    }

    public boolean hotelCheckOutCheckIn() {
        ListIterator<Pet> listIterator = registeredPetsList.listIterator();
        while (listIterator.hasNext()) {
            if (LocalDate.now().isEqual(listIterator.next().getCheckOutDate())) {
                System.out.println(listIterator.previous().getAnimalName() + " has been checked out from the hotel");
                listIterator.remove();
            }
        }
        if (reserveList.size() > 0 && registeredPetsList.size() < placesInHotel) {
            int count = registeredPetsList.size();
            for (int j = 0; j < reserveList.size(); j++) {
                registeredPetsList.add(reserveList.get(j));
                System.out.println(reserveList.get(j).getAnimalName() + " has been checked in to the hotel");
                reserveList.remove(j);
                count++;

            }
        }return true;
    }
}




