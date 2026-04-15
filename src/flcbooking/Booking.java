package flcbooking;

public class Booking {
    private String bookingId;
    private Member member;
    private Lesson lesson;
    private String status;

    public Booking(String bookingId, Member member, Lesson lesson, String status) {
        this.bookingId = bookingId;
        this.member = member;
        this.lesson = lesson;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Member getMember() {
        return member;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public String getStatus() {
        return status;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                ", Member: " + member.getName() +
                ", Lesson: " + lesson.getLessonId() + " - " + lesson.getExerciseType() +
                ", Status: " + status;
    }
}