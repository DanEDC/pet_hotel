import java.util.ArrayList;

public class HotelAdministration {

    private ArrayList<Pet> registeredPetsList = new ArrayList<>(20);

    public void registerNewPet (Pet pet){
        if(registeredPetsList.size()==20){
            System.out.println("The hotel is currently fully booked, please try again next day");
        }
        else{
            registeredPetsList.add(pet);
            System.out.println("Your pet has been successful registered in the hotel from " + pet.getCheckInDate()
            + " to " + pet.getCheckOutDate());
        }
    }
















}
