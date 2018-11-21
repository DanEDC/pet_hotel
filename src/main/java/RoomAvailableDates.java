import java.time.LocalDate;

public class RoomAvailableDates {

    private LocalDate freeFrom;
    private LocalDate freeTo;

    public RoomAvailableDates(LocalDate freeFrom, LocalDate freeTo) {
        this.freeFrom = freeFrom;
        this.freeTo = freeTo;
    }

    public LocalDate getFreeFrom() {
        return freeFrom;
    }

    public void setFreeFrom(LocalDate freeFrom) {
        this.freeFrom = freeFrom;
    }

    public LocalDate getFreeTo() {
        return freeTo;
    }

    public void setFreeTo(LocalDate freeTo) {
        this.freeTo = freeTo;
    }

    @Override
    public String toString() {
        return "'" + freeFrom +
                "', to: '" + freeTo + "' | ";
    }
}
