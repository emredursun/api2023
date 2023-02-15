package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponseBodyPojo {

    //1)Create private variables for every key
    private Integer bookingid;
    private BookingDataPojo booking;

    //2)Create constructor with all parameters, and without any parameter

    public BookingResponseBodyPojo(Integer bookingid, BookingDataPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public BookingResponseBodyPojo() {
    }

    //3)Create getters and setters
    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingDataPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingDataPojo booking) {
        this.booking = booking;
    }

    //4)Create toString() method
    @Override
    public String toString() {
        return "BookingResponseBodyPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
