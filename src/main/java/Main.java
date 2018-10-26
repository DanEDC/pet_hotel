import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HotelAdministration hotelAdministration = new HotelAdministration();
        Scanner scanner  =new Scanner(System.in);

        LocalDate localDate = LocalDate.now();



       Pet pet1 = new Pet("Binek", "Dog", "Dogg", 8, "2018-05-02", "2018-05-10", null);
        Pet pet2 = new Pet("Olek", "Dog", "Dogg", 2,  "2018-05-02", "2018-05-10", null);
        Pet pet3 = new Pet("Reksio", "Dog", "Dogg", 1,  "2018-05-02", "2018-05-10", null);
        Pet pet4 = new Pet("Nesik", "Cat", "Dogg", 5,  "2018-05-02", "2018-05-10", null);
        Pet pet5 = new Pet("Fifraczek", "Dog", "Dogg", 8,  "2018-05-02", "2018-05-10", null);
        Pet pet6 = new Pet("Bolek", "Cat", "Cat", 10,  "2018-05-02", "2018-05-10", null);

        hotelAdministration.registerNewPet(pet1);
        hotelAdministration.registerNewPet(pet2);
        hotelAdministration.registerNewPet(pet3);
        hotelAdministration.registerNewPet(pet4);
        hotelAdministration.registerNewPet(pet4);
        hotelAdministration.registerNewPet(pet6);

        hotelAdministration.printRegisteredPets();

        hotelAdministration.deletePetRegistration(2, "Olek");

        hotelAdministration.printRegisteredPets();
        hotelAdministration.deletePetRegistration(2, "Olek");

        hotelAdministration.modifyPetRegistration(1,"Binek", scanner);

    }



}
