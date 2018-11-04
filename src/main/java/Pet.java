import java.time.LocalDate;
import java.util.Objects;

public class Pet {
  private String animalName;
  private String animalType;
  private String raceType;
  private int animalAge;
  private LocalDate checkInDate;
  private LocalDate checkOutDate;
  private Service service;

    public Pet(String animalName, String animalType, String raceType, int animalAge, String checkInDate, String checkOutDate, Service service) {
    this.animalName = animalName;
    this.animalType = animalType;
    this.raceType = raceType;
    this.animalAge = animalAge;
        this.checkInDate = LocalDate.parse(checkInDate);
        this.checkOutDate = LocalDate.parse(checkOutDate);
    this.service = service;
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

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pet pet = (Pet) o;
    return getAnimalAge() == pet.getAnimalAge() &&
            Objects.equals(getAnimalName(), pet.getAnimalName()) &&
            Objects.equals(getAnimalType(), pet.getAnimalType()) &&
            Objects.equals(getRaceType(), pet.getRaceType());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getAnimalName(), getAnimalType(), getRaceType(), getAnimalAge());
  }

  @Override
  public String toString() {
    return
        "Pet name: '" + animalName + '\'' +
        ", Animal type: '" + animalType + '\'' +
        ", Race: '" + raceType + '\'' +
        ", Age: '" + animalAge + '\'' +
        ", Check in date: '" + checkInDate + '\'' +
        ", Check out date: '" + checkOutDate + '\'' +
        ", Service: '" + service + '\'';

  }
}
