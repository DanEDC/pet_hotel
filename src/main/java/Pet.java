import java.time.LocalDate;

public class Pet {

  private String animalType;
  private String raceType;
  private int animalAge;
  private boolean vaccinations;
  private LocalDate checkInDate;
  private LocalDate checkOutDate;
  private Service service;

  public Pet(String animalType, String raceType, int animalAge, boolean vaccinations, LocalDate checkInDate, LocalDate checkOutDate, Service service) {
    this.animalType = animalType;
    this.raceType = raceType;
    this.animalAge = animalAge;
    this.vaccinations = vaccinations;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.service = service;
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

  public boolean isVaccinations() {
    return vaccinations;
  }

  public void setVaccinations(boolean vaccinations) {
    this.vaccinations = vaccinations;
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
}
