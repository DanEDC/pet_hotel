import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

public class Room {

    private int roomNumber;
    private LocalDate occupiedFrom;
    private LocalDate occupiedTo;

    public Room() {
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.occupiedFrom = null;
        this.occupiedTo = null;
    }

    public void printHotelRoomsAvailability(ArrayList<Room> roomList) {
        ListIterator<Room> roomIterator = roomList.listIterator();
        System.out.println("Pet Hotel rooms availability:");
        while (roomIterator.hasNext()) {
            if (roomIterator.next().getOccupiedFrom() == null) {
                roomIterator.previous();
                System.out.println("Room number: '" + roomIterator.next().getRoomNumber() + "', Occupied from: '          ', Occupied to: '          '");
            } else {
                roomIterator.previous();
                System.out.println(roomIterator.next());
            }
        }
    }

    public void printSingleRoomAvailability(ArrayList<Room> roomList, int roomNumber) {
        if (roomList.get(roomNumber).getOccupiedFrom() == null) {
            System.out.println("Room number: '" + roomNumber + "', Occupied from:'          ', Occupied to:'          '");
        } else {
            System.out.println(roomList.get(roomNumber - 1));
        }
    }

    public int getRoomNumber() {
        return roomNumber;
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

