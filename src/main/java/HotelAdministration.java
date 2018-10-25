import java.util.LinkedList;

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
                System.out.println(pet.getAnimalName() + " is already registered in the hotel");
            }
        }
    }

     private boolean findPet (Pet pet){
            for(int i=0; i<registeredPetsList.size(); i++){
                if(registeredPetsList.get(i).equals(pet)){
                    return true;
                }
            }
            return false;

    }

    public void removePetFromTheList (int index, String petName){
        if(registeredPetsList.get(index-1).getAnimalName().equals(petName)){
            registeredPetsList.remove(index);
            System.out.println(petName + " from position " + (index-1) + ". has been removed from the list");
        }
    }
    //Not working
    public void modifyRegistrationDetails (int index, String petName){
        if(registeredPetsList.get(index-1).getAnimalName().equals(petName)){
            Pet petToChange = registeredPetsList.get(index-1);
            //petToChange.setAnimalType();
            System.out.println(petName + " from position " + (index-1) + ". has been removed from the list");
        }
    }

    public void printRegisteredPets (){
        for(int i=0; i<registeredPetsList.size(); i++){
            Pet petToPrint = registeredPetsList.get(i);
            System.out.println((i + 1) +". " + petToPrint.getAnimalName() + ", " +petToPrint.getAnimalType() + ", " +petToPrint.getRaceType()
                    + ", " + petToPrint.getAnimalAge() + ", " + petToPrint.isVaccinations() + ", " + petToPrint.getCheckInDate() + ", "
            + petToPrint.getCheckOutDate());
        }
    }
















}
