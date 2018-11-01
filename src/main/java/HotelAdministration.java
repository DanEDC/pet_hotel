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

    private Pet createNewPet(Scanner scanner){
        System.out.println("Enter animal name:");
        String animalName = scanner.next();
        System.out.println("Enter animal type:");
        String animalType = scanner.next();
        System.out.println("Enter animal race:");
        String raceType  = scanner.next();
        System.out.println("Enter animal age:");
        int animalAge = scanner.nextInt();
        System.out.println("Enter check in date, YYYY-MM-DD:");
        LocalDate checkInDate = (LocalDate.parse(scanner.next()));
        System.out.println("Enter check in date, YYYY-MM-DD:");
        LocalDate checkOutDate = (LocalDate.parse(scanner.next()));
        if(!checkOutDate.isAfter(checkInDate)){
            System.out.println("Check out date must be equal or after the check in date:");
            checkOutDate = (LocalDate.parse(scanner.next()));
        }
        Service service = null;
        Pet newPet = new Pet(animalName, animalType, raceType, animalAge, checkInDate, checkOutDate, service);
        return newPet;
    }

    public void registerNewPet(Scanner scanner) {
        Pet pet = createNewPet(scanner);
        if (registeredPetsList.size() == placesInHotel && !findPet(pet, reserveList)) {
            reserveList.add(pet);
            checkAvailabilityBetweenLists(pet);
            if (checkAvailabilityBetweenLists(pet)) {
                System.out.println(
                        pet.getAnimalName() + " has been successful registered in the hotel from " + pet.getCheckInDate() + " to " + pet.getCheckOutDate());
            } else {
                if (pet.getCheckInDate().isBefore(getEarlierCheckOutDateOfRegisteredPets())) {
                    pet.setCheckInDate(getEarlierCheckOutDateOfRegisteredPets());
                }
                if (!pet.getCheckOutDate().isAfter(pet.getCheckInDate())) {
                    pet.setCheckOutDate(pet.getCheckInDate().plusDays(1));
                }
                System.out.println("The hotel is currently fully booked, " + pet.getAnimalName()
                        + " has been added to the reserve list. Your Pet will be checked in to the hotel from " + pet
                        .getCheckInDate() + " to " + pet.getCheckOutDate() + " earliest. Please go ti Option 3 in order to change check in and check out dates");
            }
        } else if (registeredPetsList.size() == placesInHotel && findPet(pet, reserveList)) {
            System.out.println("Your pet is already added to the reserve list");
        } else {
            if (!findPet(pet, registeredPetsList)) {
                registeredPetsList.add(pet);
                System.out.println(
                        pet.getAnimalName() + " has been successful registered in the hotel from " + pet
                                .getCheckInDate() + " to " + pet.getCheckOutDate());
            } else {
                System.out.println(pet.getAnimalName() + " is already registered in the hotel");
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
            reserveList.remove(petName);
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
            petToChange.setAnimalAge(scanner.nextInt());
            System.out.println("Enter new check in date. The date must be equal or after " + getEarlierCheckOutDateOfRegisteredPets());
            petToChange.setCheckInDate(LocalDate.parse(scanner.next()));
            System.out.println("Enter new check out date:");
            petToChange.setCheckOutDate(LocalDate.parse(scanner.next()));
            System.out.println("Change data succeed:");
            System.out.println(petToChange);
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







