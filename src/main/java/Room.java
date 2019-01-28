import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class Room {

    private int roomNumber;
    private LinkedList<RoomBookedDates> bookedDates;

    public Room() {
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.bookedDates = new LinkedList();
    }

    public void printHotelRoomsAvailability(ArrayList<Room> roomList) {
        for (int i = 0; i < roomList.size(); i++) {
            printSingleRoomAvailability(roomList.get(i));
        }
    }

    public void printSingleRoomAvailability(Room room) {
        LinkedList<RoomAvailableDates> r = generateRoomAvailabilityDates(room);
        System.out.print("Room number: '" + room.getRoomNumber() + "', Available from: ");
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i).getFreeTo() == null) {
                System.out.print("'" + r.get(i).getFreeFrom() + "', to: '          '.");
            } else {
                System.out.print(r.get(i));
            }
        }
        System.out.println();
    }

    public void sortBookedDatesChronological(LinkedList<RoomBookedDates> r) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 1; i < r.size(); i++) {
                if (r.get(i).getOccupiedFrom().isBefore(r.get(i - 1).getOccupiedFrom())) {
                    RoomBookedDates objectBefore = new RoomBookedDates(r.get(i).getOccupiedFrom(), r.get(i).getOccupiedTo());
                    RoomBookedDates objectAfter = new RoomBookedDates(r.get(i - 1).getOccupiedFrom(), r.get(i - 1).getOccupiedTo());
                    r.set((i - 1), objectBefore);
                    r.set((i), objectAfter);
                    flag = true;
                }
            }
        }

    }

    public LinkedList<RoomAvailableDates> generateRoomAvailabilityDates(Room roomToCheck) {
        LinkedList<RoomAvailableDates> r = new LinkedList<>();
        LinkedList<RoomBookedDates> b = roomToCheck.getBookedDates();
        sortBookedDatesChronological(b);
        if (b.size() == 0) {
            RoomAvailableDates roomAvailableDate = new RoomAvailableDates(LocalDate.now(), null);
            r.add(roomAvailableDate);
        }
        if (b.size() == 1) {
            if (!LocalDate.now().isBefore(b.getFirst().getOccupiedTo())) {
                b.removeFirst();
                RoomAvailableDates roomAvailableDate = new RoomAvailableDates(LocalDate.now(), null);
                r.add(roomAvailableDate);
            } else if (!LocalDate.now().isBefore(b.getFirst().getOccupiedFrom())) {
                RoomAvailableDates roomAvailableDate = new RoomAvailableDates(b.getFirst().getOccupiedTo(), null);
                r.add(roomAvailableDate);
            } else {
                RoomAvailableDates r1 = new RoomAvailableDates(LocalDate.now(), b.getFirst().getOccupiedFrom());
                RoomAvailableDates r2 = new RoomAvailableDates(b.getFirst().getOccupiedTo(), null);
                r.add(r1);
                r.add(r2);
            }
        }
        if (b.size() > 1) {
            if (LocalDate.now().isBefore(b.getFirst().getOccupiedFrom())) {
                LocalDate freeFrom = LocalDate.now();
                LocalDate freeTo = b.getFirst().getOccupiedFrom();
                RoomAvailableDates roomAvailableDate = new RoomAvailableDates(freeFrom, freeTo);
                r.addFirst(roomAvailableDate);
            }
            for (int i = 0; i < (b.size() - 1); i++) {
                if (b.get(i).getOccupiedTo().isBefore(b.get(i + 1).getOccupiedFrom())) {
                    LocalDate freeFrom = b.get(i).getOccupiedTo();
                    LocalDate freeTo = b.get(i + 1).getOccupiedFrom();
                    RoomAvailableDates roomAvailableDate = new RoomAvailableDates(freeFrom, freeTo);
                    r.add(roomAvailableDate);
                }
            }
            LocalDate freeFrom = b.getLast().getOccupiedTo();
            RoomAvailableDates roomAvailableDate = new RoomAvailableDates(freeFrom, null);
            r.add(roomAvailableDate);
        }

        return r;
    }


    public void deleteBookedDates(ArrayList<Room> rooms, Pet pet) {
        LinkedList<RoomBookedDates> l = rooms.get(pet.getRoomNumber() - 1).getBookedDates();
        for (int i = 0; i < l.size(); i++) {
            if (pet.getCheckOutDate().isEqual(l.get(i).getOccupiedTo())) {
                l.remove(i);
            }
        }
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public LinkedList<RoomBookedDates> getBookedDates() {
        return bookedDates;
    }

}

