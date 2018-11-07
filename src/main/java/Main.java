import java.time.LocalDate;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    HotelAdministration hotelAdministration = new HotelAdministration();
    Scanner scanner  = new Scanner(System.in);

    Pet pet1 = new Pet("Binek", "Dog", "Labrador", 4, 1, LocalDate.parse("2018-11-01"), LocalDate.parse("2018-11-08"), null);
    Pet pet2 = new Pet("Franek", "Cat", "Cat", 2, 2, LocalDate.parse("2018-11-01"), LocalDate.parse("2018-11-08"), null);
    Pet pet3 = new Pet("Olek", "Cat", "Cat", 8, 3, LocalDate.parse("2018-11-01"), LocalDate.parse("2018-11-08"), null);
    Pet pet4 = new Pet("Nesik", "Cat", "Cat", 7, 4, LocalDate.parse("2018-11-01"), LocalDate.parse("2018-11-08"), null);
    Pet pet5 = new Pet("Fifraczek", "Dog", "York", 8, 5, LocalDate.parse("2018-11-01"), LocalDate.parse("2018-11-08"), null);

    hotelAdministration.registerNewPet(pet1);
    hotelAdministration.registerNewPet(pet2);
    hotelAdministration.registerNewPet(pet3);
    hotelAdministration.registerNewPet(pet4);
    hotelAdministration.registerNewPet(pet5);

    hotelAdministration.checkRoomsAvailability();

    boolean quit = false;
    int choice = 0;
    printMenu();
    while (!quit) {
      System.out.println("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 0:
          printMenu();
          break;
        case 1:
          hotelAdministration.registerNewPet(scanner);
          break;
        case 2:
          hotelAdministration.modifyPetRegistration(scanner);
          break;
        case 3:
          hotelAdministration.deletePetRegistration(scanner);
          break;
        case 4:
          hotelAdministration.printRegisteredPets();
          break;
        case 5:
          quit = true;
          break;
      }
    }
  }

  public static void printMenu() {
    System.out.println("\nPress ");
    System.out.println("\n 0 - To print choice options");
    System.out.println("\n 1 - To register Pet in the hotel");
    System.out.println("\n 2 - To modify Pet registration");
    System.out.println("\n 3 - To delete Pet registration");
    System.out.println("\n 4 - To print hotel lists");
    System.out.println("\n 5 - To quit the application");
  }

}