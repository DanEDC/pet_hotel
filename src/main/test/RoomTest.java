import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.LinkedList;

public class RoomTest {

    public Room testRoom;
    public RoomBookedDates testBookedDate1;
    public RoomBookedDates testBookedDate2;
    public RoomBookedDates testBookedDate3;

    @Before
    public void setUp() {
        testRoom = new Room(1);
        testBookedDate1 = new RoomBookedDates(LocalDate.parse("2019-01-03"), LocalDate.parse("2019-01-07"));
        testBookedDate2 = new RoomBookedDates(LocalDate.parse("2019-01-09"), LocalDate.parse("2019-01-10"));
        testBookedDate3 = new RoomBookedDates(LocalDate.parse("2019-01-12"), LocalDate.parse("2019-01-20"));
        //testRoom.getBookedDates().add(testBookedDate1);
        //testRoom.getBookedDates().add(testBookedDate2);
    }

    @Test
    public void shouldSortDatesChronological() {

        testRoom.getBookedDates().add(testBookedDate3);
        testRoom.getBookedDates().add(testBookedDate2);
        testRoom.getBookedDates().add(testBookedDate1);
        testRoom.sortBookedDatesChronological(testRoom.getBookedDates());

        LinkedList<RoomBookedDates> r = new LinkedList<>();
        r.add(testBookedDate1);
        r.add(testBookedDate2);
        r.add(testBookedDate3);

        Assert.assertEquals(r.toString(), testRoom.getBookedDates().toString());

    }

}