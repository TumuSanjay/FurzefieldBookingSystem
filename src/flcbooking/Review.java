package flcbooking;

public class Review {
    private Booking booking;
    private int rating;
    private String comment;

    public Review(Booking booking, int rating, String comment) {
        this.booking = booking;
        this.rating = rating;
        this.comment = comment;
    }

    public Booking getBooking() {
        return booking;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Rating: " + rating + ", Comment: " + comment;
    }
}