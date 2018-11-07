import java.time.LocalDate;

public class Room {

    private int roomNumber;
    private LocalDate occupiedFrom;
    private LocalDate occupiedTo;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.occupiedFrom = null;
        this.occupiedTo = null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    @Override
    public String toString() {
        return "Room number: '" + roomNumber +
                "', Occupied from: '" + occupiedFrom +
                "', Occupied to: '" + occupiedTo +
                "'";
    }
}

