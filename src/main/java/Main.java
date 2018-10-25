import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        HotelAdministration hotelAdministration = new HotelAdministration();

        LocalDate localDate = LocalDate.now();



       Pet pet1 = new Pet("Binek", "Dog", "Dogg", 8, true, "2018-05-02", "2018-05-10", null);
        Pet pet2 = new Pet("Olek", "Dog", "Dogg", 2, true, "2018-05-02", "2018-05-10", null);
        Pet pet3 = new Pet("Reksio", "Dog", "Dogg", 1, true, "2018-05-02", "2018-05-10", null);
        Pet pet4 = new Pet("Nesik", "Cat", "Dogg", 5, true, "2018-05-02", "2018-05-10", null);
        Pet pet5 = new Pet("Fifraczek", "Dog", "Dogg", 8, true, "2018-05-02", "2018-05-10", null);
        Pet pet6 = new Pet("Bolek", "Cat", "Cat", 10, true, "2018-05-02", "2018-05-10", null);

        hotelAdministration.registerNewPet(pet1);
        hotelAdministration.registerNewPet(pet2);
        hotelAdministration.registerNewPet(pet3);
        hotelAdministration.registerNewPet(pet4);
        hotelAdministration.registerNewPet(pet5);
        hotelAdministration.registerNewPet(pet6);

        hotelAdministration.printRegisteredPets();

    }



}
