import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HotelAdministration hotelAdministration = new HotelAdministration();
        Scanner scanner  = new Scanner(System.in);

      Pet pet1 = new Pet("Binek", "Dog", "Dogg", 8, "2018-10-24", "2018-11-01", null);
      Pet pet2 = new Pet("Olek", "Dog", "Dogg", 2, "2018-10-24", "2018-10-31", null);
      Pet pet3 = new Pet("Reksio", "Dog", "Dogg", 1, "2018-10-30", "2018-10-25", null);
      Pet pet4 = new Pet("Nesik", "Cat", "Dogg", 5, "2018-10-30", "2018-11-15", null);
      Pet pet5 = new Pet("Fifraczek", "Dog", "Dogg", 8, "2018-10-30", "2018-10-26", null);
      Pet pet6 = new Pet("Bolek", "Cat", "Cat", 10, "2018-11-05", "2018-11-15", null);
      Pet pet7 = new Pet("Kicius", "Cat", "Cat", 6, "2018-11-04", "2018-11-10", null);
      Pet pet8 = new Pet("Felek", "Cat", "Cat", 3, "2018-10-30", "2018-11-10", null);

        hotelAdministration.registerNewPet(pet1);
        hotelAdministration.registerNewPet(pet2);
        hotelAdministration.registerNewPet(pet3);
        hotelAdministration.registerNewPet(pet4);
        hotelAdministration.registerNewPet(pet5);
        hotelAdministration.registerNewPet(pet6);
      hotelAdministration.registerNewPet(pet7);
      hotelAdministration.registerNewPet(pet8);

        hotelAdministration.printRegisteredPets();

        //hotelAdministration.deletePetRegistration(2, "Olek");

        //hotelAdministration.printRegisteredPets();
        //hotelAdministration.deletePetRegistration(2, "Olek");

        //hotelAdministration.modifyPetRegistration(1,"Binek", scanner);

      //hotelAdministration.hotelCheckOutCheckIn();
      //hotelAdministration.printRegisteredPets();


    }

}
