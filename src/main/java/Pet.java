import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Pet {
  private String animalName;
  private String animalType;
  private String raceType;
  private int animalAge;
  private int roomNumber;
  private LocalDate checkInDate;
  private LocalDate checkOutDate;
  private ArrayList<Service> service;

  public Pet(String animalName, String animalType, String raceType, int animalAge, int roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
    this.animalName = animalName;
    this.animalType = animalType;
    this.raceType = raceType;
    this.animalAge = animalAge;
    this.roomNumber = roomNumber;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.service = new ArrayList<>();
  }

  public String getAnimalName() {
    return animalName;
  }

  public void setAnimalName(String animalName) {
    this.animalName = animalName;
  }

  public String getAnimalType() {
    return animalType;
  }

  public void setAnimalType(String animalType) {
    this.animalType = animalType;
  }

  public String getRaceType() {
    return raceType;
  }

  public void setRaceType(String raceType) {
    this.raceType = raceType;
  }

  public int getAnimalAge() {
    return animalAge;
  }

  public void setAnimalAge(int animalAge) {
    this.animalAge = animalAge;
  }

  public int getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
  }

  public LocalDate getCheckInDate() {
    return checkInDate;
  }

  public void setCheckInDate(LocalDate checkInDate) {
    this.checkInDate = checkInDate;
  }

  public LocalDate getCheckOutDate() {
    return checkOutDate;
  }

  public void setCheckOutDate(LocalDate checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  public ArrayList<Service> getService() {
    return service;
  }

  public void setService(ArrayList<Service> service) {
    this.service = service;
  }

  private String printServiceList(ArrayList<Service> serviceList) {
    StringBuilder builder = new StringBuilder();
    if (serviceList.size() == 0) {
      return "No additional service";
    } else {
      for (int i = 0; i < serviceList.size(); i++) {
        if (i != (serviceList.size() - 1)) {
          builder.append(serviceList.get(i) + ", ");
        } else {
          builder.append(serviceList.get(i));
        }
      }
      return builder.toString();
    }
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pet pet = (Pet) o;
    return getAnimalAge() == pet.getAnimalAge() &&
            getRoomNumber() == pet.getRoomNumber() &&
            Objects.equals(getAnimalName(), pet.getAnimalName()) &&
            Objects.equals(getAnimalType(), pet.getAnimalType()) &&
            Objects.equals(getRaceType(), pet.getRaceType());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getAnimalName(), getAnimalType(), getRaceType(), getAnimalAge(), getRoomNumber());
  }

  public String printPet(Pet pet) {
    return "Pet name: '" + pet.getAnimalName() + "', " +
            "Animal type: '" + pet.getAnimalType() + "', " +
            "Race: '" + pet.getAnimalName() + "', " +
            "Age: '" + pet.getAnimalAge() + "', " +
            "Room number: '" + pet.getRoomNumber() + "', " +
            "Check in date: '" + pet.getCheckInDate() + "', " +
            "Check out date: '" + pet.getCheckOutDate() + "', " +
            "Service: '" + printServiceList(pet.getService()) + "'";
  }
}
