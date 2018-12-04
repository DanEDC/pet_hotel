import java.util.Scanner;

public class Main {


  public static void main(String[] args) {

    HotelAdministration hotelAdministration = new HotelAdministration();
    Validation validation = new Validation();
    Scanner scanner  = new Scanner(System.in);

    boolean quit = false;
    int choice = 0;
    hotelAdministration.hotelCheckOutCheckIn();
    printMenu();
    while (!quit) {
      System.out.println("Enter your choice: ");
      choice = validation.checkIntFormat(scanner);
      scanner.nextLine();

      switch (choice) {
        case 0:
          printMenu();
          break;
        case 1:
          hotelAdministration.registerPetInHotel(scanner);
          break;
        case 2:
          hotelAdministration.modifyPetData(scanner);
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
    System.out.println("\n 2 - To modify Pet data");
    System.out.println("\n 3 - To delete Pet registration");
    System.out.println("\n 4 - To print Pets registered in the hotel");
    System.out.println("\n 5 - To quit the application");
  }

}