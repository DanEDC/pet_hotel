import java.time.LocalDate;

public class RoomBookedDates {

    private LocalDate occupiedFrom;
    private LocalDate occupiedTo;

    public RoomBookedDates(LocalDate occupiedFrom, LocalDate occupiedTo) {
        this.occupiedFrom = occupiedFrom;
        this.occupiedTo = occupiedTo;
    }

    public LocalDate getOccupiedFrom() {
        return occupiedFrom;
    }

    public void setOccupiedFrom(LocalDate occupiedFrom) {
        this.occupiedFrom = occupiedFrom;
    }

    public LocalDate getOccupiedTo() {
        return occupiedTo;
    }

    public void setOccupiedTo(LocalDate occupiedTo) {
        this.occupiedTo = occupiedTo;
    }

}


