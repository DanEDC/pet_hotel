import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.LinkedList;

public class RoomTest {

    public Room testRoom;
    public LinkedList<RoomAvailableDates> r;

    @Before
    public void setUp() {
        testRoom = new Room(1);
        r = new LinkedList<>();
    }

    @Test
    public void shouldSortDatesChronological() {
        //Given
        RoomBookedDates testBookedDate1 = new RoomBookedDates(LocalDate.parse("2019-01-03"), LocalDate.parse("2019-01-07"));
        RoomBookedDates testBookedDate2 = new RoomBookedDates(LocalDate.parse("2019-01-09"), LocalDate.parse("2019-01-10"));
        RoomBookedDates testBookedDate3 = new RoomBookedDates(LocalDate.parse("2019-01-12"), LocalDate.parse("2019-01-20"));
        testRoom.getBookedDates().add(testBookedDate3);
        testRoom.getBookedDates().add(testBookedDate2);
        testRoom.getBookedDates().add(testBookedDate1);
        testRoom.sortBookedDatesChronological(testRoom.getBookedDates());
        //Expected
        LinkedList<RoomBookedDates> r = new LinkedList<>();
        r.add(testBookedDate1);
        r.add(testBookedDate2);
        r.add(testBookedDate3);

        Assert.assertEquals(r.toString(), testRoom.getBookedDates().toString());

    }

    @Test
    public void shouldGenerateAvailableDateForEmptyRoom() {
        //Given
        RoomAvailableDates roomAvailableDate = new RoomAvailableDates(LocalDate.now(), null);
        r.add(roomAvailableDate);

        Assert.assertEquals(r.toString(), testRoom.generateRoomAvailabilityDates(testRoom).toString());

    }

    @Test
    public void shouldGenerateAvailableDatesWhenCurrentDateIsEqualToOccupiedToDate() {
        //Given
        RoomBookedDates testBookedDates = new RoomBookedDates(LocalDate.now().minusDays(2), LocalDate.now());
        testRoom.getBookedDates().add(testBookedDates);

        RoomAvailableDates roomAvailableDate = new RoomAvailableDates(LocalDate.now(), null);
        r.add(roomAvailableDate);

        Assert.assertEquals(r.toString(), testRoom.generateRoomAvailabilityDates(testRoom).toString());

    }

    @Test
    public void shouldGenerateAvailableDatesWhenCurrentDateIsAfterOccupiedToDate() {
        //Given
        RoomBookedDates testBookedDates = new RoomBookedDates(LocalDate.now().minusDays(5), LocalDate.now().minusDays(2));
        testRoom.getBookedDates().add(testBookedDates);

        RoomAvailableDates roomAvailableDate = new RoomAvailableDates(LocalDate.now(), null);
        r.add(roomAvailableDate);

        Assert.assertEquals(r.toString(), testRoom.generateRoomAvailabilityDates(testRoom).toString());

    }

    @Test
    public void shouldGenerateAvailableDatesWhenCurrentDateIsEqualToOccupiedFromDate() {
        //Given
        RoomBookedDates testBookedDates = new RoomBookedDates(LocalDate.now(), LocalDate.now().plusDays(2));
        testRoom.getBookedDates().add(testBookedDates);

        RoomAvailableDates roomAvailableDate = new RoomAvailableDates(LocalDate.now().plusDays(2), null);
        r.add(roomAvailableDate);

        Assert.assertEquals(r.toString(), testRoom.generateRoomAvailabilityDates(testRoom).toString());

    }

    @Test
    public void shouldGenerateAvailableDatesWhenCurrentDateIsAfterOccupiedFromDate() {
        //Given
        RoomBookedDates testBookedDates = new RoomBookedDates(LocalDate.now().minusDays(3), LocalDate.now().plusDays(2));
        testRoom.getBookedDates().add(testBookedDates);

        RoomAvailableDates roomAvailableDate = new RoomAvailableDates(LocalDate.now().plusDays(2), null);
        r.add(roomAvailableDate);

        Assert.assertEquals(r.toString(), testRoom.generateRoomAvailabilityDates(testRoom).toString());

    }

    @Test
    public void shouldGenerateAvailableDatesWhenOccupiedFromDateIsBeforeCurrentDate() {
        //Given
        RoomBookedDates testBookedDates = new RoomBookedDates(LocalDate.now().plusDays(2), LocalDate.now().plusDays(5));
        testRoom.getBookedDates().add(testBookedDates);

        RoomAvailableDates roomAvailableDate1 = new RoomAvailableDates(LocalDate.now(), LocalDate.now().plusDays(2));
        RoomAvailableDates roomAvailableDate2 = new RoomAvailableDates(LocalDate.now().plusDays(5), null);
        r.add(roomAvailableDate1);
        r.add(roomAvailableDate2);

        Assert.assertEquals(r.toString(), testRoom.generateRoomAvailabilityDates(testRoom).toString());

    }

    @Test
    public void shouldGenerateAvailableDatesForTwoBookedPeriodsWhenCurrentDateIsBeforeFirstOccupiedDate() {
        //Given
        RoomBookedDates testBookedDates1 = new RoomBookedDates(LocalDate.now().plusDays(2), LocalDate.now().plusDays(5));
        RoomBookedDates testBookedDates2 = new RoomBookedDates(LocalDate.now().plusDays(8), LocalDate.now().plusDays(10));
        testRoom.getBookedDates().add(testBookedDates1);
        testRoom.getBookedDates().add(testBookedDates2);

        RoomAvailableDates roomAvailableDate1 = new RoomAvailableDates(LocalDate.now(), LocalDate.now().plusDays(2));
        RoomAvailableDates roomAvailableDate2 = new RoomAvailableDates(LocalDate.now().plusDays(5), LocalDate.now().plusDays(8));
        RoomAvailableDates roomAvailableDate3 = new RoomAvailableDates(LocalDate.now().plusDays(10), null);
        r.add(roomAvailableDate1);
        r.add(roomAvailableDate2);
        r.add(roomAvailableDate3);

        Assert.assertEquals(r.toString(), testRoom.generateRoomAvailabilityDates(testRoom).toString());

    }

    @Test
    public void shouldGenerateAvailableDatesForTwoBookedPeriodsWhenCurrentDateIsAfterFirstOccupiedDate() {
        //Given
        RoomBookedDates testBookedDates1 = new RoomBookedDates(LocalDate.now().minusDays(2), LocalDate.now().plusDays(5));
        RoomBookedDates testBookedDates2 = new RoomBookedDates(LocalDate.now().plusDays(8), LocalDate.now().plusDays(10));
        testRoom.getBookedDates().add(testBookedDates1);
        testRoom.getBookedDates().add(testBookedDates2);

        RoomAvailableDates roomAvailableDate1 = new RoomAvailableDates(LocalDate.now().plusDays(5), LocalDate.now().plusDays(8));
        RoomAvailableDates roomAvailableDate2 = new RoomAvailableDates(LocalDate.now().plusDays(10), null);
        r.add(roomAvailableDate1);
        r.add(roomAvailableDate2);

        Assert.assertEquals(r.toString(), testRoom.generateRoomAvailabilityDates(testRoom).toString());

    }

    @Test
    public void shouldGenerateAvailableDatesForThreeBookedPeriodsWhenCurrentDateIsBeforeFirstOccupiedDate() {
        //Given
        RoomBookedDates testBookedDates1 = new RoomBookedDates(LocalDate.now().plusDays(2), LocalDate.now().plusDays(5));
        RoomBookedDates testBookedDates2 = new RoomBookedDates(LocalDate.now().plusDays(8), LocalDate.now().plusDays(10));
        RoomBookedDates testBookedDates3 = new RoomBookedDates(LocalDate.now().plusDays(12), LocalDate.now().plusDays(15));
        testRoom.getBookedDates().add(testBookedDates1);
        testRoom.getBookedDates().add(testBookedDates2);
        testRoom.getBookedDates().add(testBookedDates3);

        RoomAvailableDates roomAvailableDate1 = new RoomAvailableDates(LocalDate.now(), LocalDate.now().plusDays(2));
        RoomAvailableDates roomAvailableDate2 = new RoomAvailableDates(LocalDate.now().plusDays(5), LocalDate.now().plusDays(8));
        RoomAvailableDates roomAvailableDate3 = new RoomAvailableDates(LocalDate.now().plusDays(10), LocalDate.now().plusDays(12));
        RoomAvailableDates roomAvailableDate4 = new RoomAvailableDates(LocalDate.now().plusDays(15), null);
        r.add(roomAvailableDate1);
        r.add(roomAvailableDate2);
        r.add(roomAvailableDate3);
        r.add(roomAvailableDate4);

        Assert.assertEquals(r.toString(), testRoom.generateRoomAvailabilityDates(testRoom).toString());

    }
}