package flcbooking;

import java.util.ArrayList;

public class Lesson {
    private String lessonId;
    private String exerciseType;
    private String day;
    private String timeSlot;
    private String month;
    private double price;
    private int capacity;
    private ArrayList<Member> bookedMembers;
    private ArrayList<Review> reviews;

    public Lesson(String lessonId, String exerciseType, String day, String timeSlot, String month, double price) {
        this.lessonId = lessonId;
        this.exerciseType = exerciseType;
        this.day = day;
        this.timeSlot = timeSlot;
        this.month = month;
        this.price = price;
        this.capacity = 4;
        this.bookedMembers = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public String getDay() {
        return day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getMonth() {
        return month;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Member> getBookedMembers() {
        return bookedMembers;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public boolean isFull() {
        return bookedMembers.size() >= capacity;
    }

    public boolean addMember(Member member) {
        if (isFull()) {
            return false;
        }

        for (Member bookedMember : bookedMembers) {
            if (bookedMember.getMemberId().equals(member.getMemberId())) {
                return false;
            }
        }

        bookedMembers.add(member);
        return true;
    }

    public boolean removeMember(Member member) {
        return bookedMembers.remove(member);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        }

        int total = 0;
        for (Review review : reviews) {
            total += review.getRating();
        }

        return (double) total / reviews.size();
    }

    @Override
    public String toString() {
        return lessonId + " | " + exerciseType + " | " + day + " | " + timeSlot +
                " | " + month + " | £" + price + " | Spaces: " +
                (capacity - bookedMembers.size()) + "/" + capacity;
    }
}