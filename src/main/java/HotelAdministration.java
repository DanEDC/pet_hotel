import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class HotelAdministration {

    private LinkedList<Pet> registeredPetsList;

    public HotelAdministration() {
        this.registeredPetsList = new LinkedList<>();
    }

    public void registerNewPet (Pet pet) {
        if (registeredPetsList.size() == 5) {
            System.out.println("The hotel is currently fully booked, please try again next day");
        } else {
            if (!findPet(pet)) {
                registeredPetsList.add(pet);
                System.out.println(pet.getAnimalName() + " has been successful registered in the hotel from " + pet.getCheckInDate()
                        + " to " + pet.getCheckOutDate());
            } else {
                System.out.println(pet.getAnimalName() + " is already registered in the hotel.");
            }
        }
    }

     private boolean findPet (Pet pet){
         for (Pet petToFind : registeredPetsList) {
             if (petToFind.equals(pet)) {
                 return true;
             }
         }
            return false;
    }

    public void deletePetRegistration (int index, String petName){
        if(registeredPetsList.get(index-1).getAnimalName().equals(petName)){
            registeredPetsList.remove(index-1);
            System.out.println(petName + " from position " + index + ". has been removed from the list.");
        } else {
            System.out.println(petName + " is not found on position " + index + ". Delete registration failed.");
        }
    }

    public void modifyPetRegistration (int index, String petName, Scanner scanner){
        if(registeredPetsList.get(index-1).getAnimalName().equals(petName)){
            Pet petToChange = registeredPetsList.get(index-1);
            System.out.println("Please select new details for " + petName + ":");
            System.out.println("Enter animal type:");
            petToChange.setAnimalType(scanner.next());
            System.out.println("Enter animal race:");
            petToChange.setRaceType(scanner.next());
            System.out.println("Enter animal age:");
            petToChange.setAnimalAge(scanner.nextInt());
            System.out.println("Enter check in date");
            petToChange.setCheckInDate(LocalDate.parse(scanner.next()));
            System.out.println("Enter check out date");
            petToChange.setCheckOutDate(LocalDate.parse(scanner.next()));
            System.out.println("Change data succeed:");
            System.out.println(petToChange);
        }
    }

    public void printRegisteredPets () {
        for (int i = 0; i < registeredPetsList.size(); i++) {
            Pet petToPrint = registeredPetsList.get(i);
            System.out.println((i + 1) + ". " + petToPrint);
        }
    }

    public Pet createNewPetAccount (String animalName, String animalType, String raceType, int animalAge, String checkInDate, String checkOutDate, Service service){
        Pet newPet = new Pet(animalName, animalType, raceType, animalAge, checkInDate, checkOutDate, service);
        return newPet;
    }


}
