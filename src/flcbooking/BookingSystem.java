package flcbooking;

import java.util.ArrayList;

public class BookingSystem {
    private ArrayList<Member> members;
    private ArrayList<Lesson> lessons;
    private ArrayList<Booking> bookings;
    private int bookingCounter;

    public BookingSystem() {
        members = new ArrayList<>();
        lessons = new ArrayList<>();
        bookings = new ArrayList<>();
        bookingCounter = 1;

        loadMembers();
        loadLessons();
    }

    private void loadMembers() {
        members.add(new Member("M001", "Sanjay"));
        members.add(new Member("M002", "Ravi"));
        members.add(new Member("M003", "Priya"));
        members.add(new Member("M004", "Neha"));
        members.add(new Member("M005", "John"));
        members.add(new Member("M006", "Sarah"));
        members.add(new Member("M007", "David"));
        members.add(new Member("M008", "Asha"));
        members.add(new Member("M009", "Kumar"));
        members.add(new Member("M010", "Mary"));
    }

    private void loadLessons() {
        addWeekend("May", 1, "Yoga", "Zumba", "Aquacise", "Box Fit", "Yoga", "Body Blitz");
        addWeekend("May", 2, "Box Fit", "Yoga", "Zumba", "Aquacise", "Body Blitz", "Yoga");
        addWeekend("May", 3, "Zumba", "Aquacise", "Yoga", "Body Blitz", "Box Fit", "Zumba");
        addWeekend("May", 4, "Aquacise", "Body Blitz", "Box Fit", "Yoga", "Zumba", "Aquacise");

        addWeekend("June", 1, "Yoga", "Zumba", "Aquacise", "Box Fit", "Yoga", "Body Blitz");
        addWeekend("June", 2, "Box Fit", "Yoga", "Zumba", "Aquacise", "Body Blitz", "Yoga");
        addWeekend("June", 3, "Zumba", "Aquacise", "Yoga", "Body Blitz", "Box Fit", "Zumba");
        addWeekend("June", 4, "Aquacise", "Body Blitz", "Box Fit", "Yoga", "Zumba", "Aquacise");
    }

    private void addWeekend(String month, int weekendNo,
                            String satMorning, String satAfternoon, String satEvening,
                            String sunMorning, String sunAfternoon, String sunEvening) {

        lessons.add(new Lesson(generateLessonId(), satMorning, "Saturday", "Morning", month, getPriceForExercise(satMorning)));
        lessons.add(new Lesson(generateLessonId(), satAfternoon, "Saturday", "Afternoon", month, getPriceForExercise(satAfternoon)));
        lessons.add(new Lesson(generateLessonId(), satEvening, "Saturday", "Evening", month, getPriceForExercise(satEvening)));

        lessons.add(new Lesson(generateLessonId(), sunMorning, "Sunday", "Morning", month, getPriceForExercise(sunMorning)));
        lessons.add(new Lesson(generateLessonId(), sunAfternoon, "Sunday", "Afternoon", month, getPriceForExercise(sunAfternoon)));
        lessons.add(new Lesson(generateLessonId(), sunEvening, "Sunday", "Evening", month, getPriceForExercise(sunEvening)));
    }

    private String generateLessonId() {
        int nextNumber = lessons.size() + 1;
        return String.format("L%03d", nextNumber);
    }

    private double getPriceForExercise(String exerciseType) {
        switch (exerciseType) {
            case "Yoga":
                return 15.0;
            case "Zumba":
                return 12.0;
            case "Aquacise":
                return 18.0;
            case "Box Fit":
                return 14.0;
            case "Body Blitz":
                return 16.0;
            default:
                return 10.0;
        }
    }

    public Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return member;
            }
        }
        return null;
    }

    public Lesson findLessonById(String lessonId) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonId().equalsIgnoreCase(lessonId)) {
                return lesson;
            }
        }
        return null;
    }

    public Booking findBookingById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equalsIgnoreCase(bookingId)) {
                return booking;
            }
        }
        return null;
    }

    public void showAllMembers() {
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public void showAllLessons() {
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
    }

    public void showTimetableByDay(String month, String day) {
        boolean found = false;

        for (Lesson lesson : lessons) {
            if (lesson.getMonth().equalsIgnoreCase(month) &&
                    lesson.getDay().equalsIgnoreCase(day)) {
                System.out.println(lesson);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No lessons found for " + day + " in " + month + ".");
        }
    }

    public void showTimetableByExercise(String exerciseType) {
        boolean found = false;

        for (Lesson lesson : lessons) {
            if (lesson.getExerciseType().equalsIgnoreCase(exerciseType)) {
                System.out.println(lesson);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No lessons found for exercise type: " + exerciseType);
        }
    }

    public String bookLesson(String memberId, String lessonId) {
        Member member = findMemberById(memberId);
        if (member == null) {
            return "Member not found.";
        }

        Lesson lesson = findLessonById(lessonId);
        if (lesson == null) {
            return "Lesson not found.";
        }

        if (lesson.isFull()) {
            return "Booking failed. Lesson is full.";
        }

        for (Booking booking : bookings) {
            if (booking.getMember().getMemberId().equalsIgnoreCase(memberId) &&
                    booking.getLesson().getLessonId().equalsIgnoreCase(lessonId) &&
                    !booking.getStatus().equalsIgnoreCase("cancelled")) {
                return "Duplicate booking not allowed.";
            }
        }

        for (Booking booking : bookings) {
            if (booking.getMember().getMemberId().equalsIgnoreCase(memberId) &&
                    !booking.getStatus().equalsIgnoreCase("cancelled")) {

                Lesson bookedLesson = booking.getLesson();

                if (bookedLesson.getMonth().equalsIgnoreCase(lesson.getMonth()) &&
                        bookedLesson.getDay().equalsIgnoreCase(lesson.getDay()) &&
                        bookedLesson.getTimeSlot().equalsIgnoreCase(lesson.getTimeSlot())) {
                    return "Time conflict. Member already has a lesson in this slot.";
                }
            }
        }

        boolean added = lesson.addMember(member);
        if (!added) {
            return "Booking failed.";
        }

        String bookingId = String.format("B%03d", bookingCounter);
        bookingCounter++;

        Booking booking = new Booking(bookingId, member, lesson, "booked");
        bookings.add(booking);

        return "Booking successful. Booking ID: " + bookingId;
    }

    public String changeBooking(String bookingId, String newLessonId) {
        Booking booking = findBookingById(bookingId);
        if (booking == null) {
            return "Booking not found.";
        }

        if (booking.getStatus().equalsIgnoreCase("cancelled")) {
            return "Cannot change a cancelled booking.";
        }

        if (booking.getStatus().equalsIgnoreCase("attended")) {
            return "Cannot change an attended booking.";
        }

        Lesson oldLesson = booking.getLesson();
        Lesson newLesson = findLessonById(newLessonId);

        if (newLesson == null) {
            return "New lesson not found.";
        }

        if (newLesson.isFull()) {
            return "Change failed. New lesson is full.";
        }

        for (Booking otherBooking : bookings) {
            if (otherBooking.getMember().getMemberId().equalsIgnoreCase(booking.getMember().getMemberId()) &&
                    !otherBooking.getBookingId().equalsIgnoreCase(bookingId) &&
                    !otherBooking.getStatus().equalsIgnoreCase("cancelled")) {

                Lesson existingLesson = otherBooking.getLesson();

                if (existingLesson.getMonth().equalsIgnoreCase(newLesson.getMonth()) &&
                        existingLesson.getDay().equalsIgnoreCase(newLesson.getDay()) &&
                        existingLesson.getTimeSlot().equalsIgnoreCase(newLesson.getTimeSlot())) {
                    return "Time conflict. Change not allowed.";
                }
            }
        }

        oldLesson.removeMember(booking.getMember());
        boolean added = newLesson.addMember(booking.getMember());

        if (!added) {
            oldLesson.addMember(booking.getMember());
            return "Change failed.";
        }

        booking.setLesson(newLesson);
        booking.setStatus("changed");

        return "Booking changed successfully. Same Booking ID kept: " + bookingId;
    }

    public String cancelBooking(String bookingId) {
        Booking booking = findBookingById(bookingId);
        if (booking == null) {
            return "Booking not found.";
        }

        if (booking.getStatus().equalsIgnoreCase("cancelled")) {
            return "Booking is already cancelled.";
        }

        if (booking.getStatus().equalsIgnoreCase("attended")) {
            return "Cannot cancel an attended booking.";
        }

        booking.getLesson().removeMember(booking.getMember());
        booking.setStatus("cancelled");

        return "Booking cancelled successfully.";
    }

    public String attendLesson(String bookingId, int rating, String comment) {
        Booking booking = findBookingById(bookingId);
        if (booking == null) {
            return "Booking not found.";
        }

        if (booking.getStatus().equalsIgnoreCase("cancelled")) {
            return "Cannot attend a cancelled booking.";
        }

        if (booking.getStatus().equalsIgnoreCase("attended")) {
            return "Lesson already attended.";
        }

        if (rating < 1 || rating > 5) {
            return "Invalid rating. Enter a rating between 1 and 5.";
        }

        Review review = new Review(booking, rating, comment);
        booking.getLesson().addReview(review);
        booking.setStatus("attended");

        return "Lesson attended successfully. Review added.";
    }

    public void printMonthlyLessonReport(String month) {
        System.out.println("\n===== Monthly Lesson Report for " + month + " =====");

        boolean found = false;

        for (Lesson lesson : lessons) {
            if (lesson.getMonth().equalsIgnoreCase(month)) {
                int attendedCount = 0;

                for (Booking booking : bookings) {
                    if (booking.getLesson().getLessonId().equalsIgnoreCase(lesson.getLessonId()) &&
                            booking.getStatus().equalsIgnoreCase("attended")) {
                        attendedCount++;
                    }
                }

                System.out.printf("%s | Attended Members: %d | Average Rating: %.2f%n",
                        lesson, attendedCount, lesson.getAverageRating());

                found = true;
            }
        }

        if (!found) {
            System.out.println("No lessons found for month: " + month);
        }
    }

    public void printMonthlyChampionReport(String month) {
        System.out.println("\n===== Monthly Champion Exercise Report for " + month + " =====");

        double yogaIncome = 0.0;
        double zumbaIncome = 0.0;
        double aquaciseIncome = 0.0;
        double boxFitIncome = 0.0;
        double bodyBlitzIncome = 0.0;

        for (Booking booking : bookings) {
            if (booking.getStatus().equalsIgnoreCase("attended") &&
                    booking.getLesson().getMonth().equalsIgnoreCase(month)) {

                String type = booking.getLesson().getExerciseType();
                double price = booking.getLesson().getPrice();

                switch (type) {
                    case "Yoga":
                        yogaIncome += price;
                        break;
                    case "Zumba":
                        zumbaIncome += price;
                        break;
                    case "Aquacise":
                        aquaciseIncome += price;
                        break;
                    case "Box Fit":
                        boxFitIncome += price;
                        break;
                    case "Body Blitz":
                        bodyBlitzIncome += price;
                        break;
                }
            }
        }

        System.out.printf("Yoga Income: £%.2f%n", yogaIncome);
        System.out.printf("Zumba Income: £%.2f%n", zumbaIncome);
        System.out.printf("Aquacise Income: £%.2f%n", aquaciseIncome);
        System.out.printf("Box Fit Income: £%.2f%n", boxFitIncome);
        System.out.printf("Body Blitz Income: £%.2f%n", bodyBlitzIncome);

        double highest = yogaIncome;
        String champion = "Yoga";

        if (zumbaIncome > highest) {
            highest = zumbaIncome;
            champion = "Zumba";
        }
        if (aquaciseIncome > highest) {
            highest = aquaciseIncome;
            champion = "Aquacise";
        }
        if (boxFitIncome > highest) {
            highest = boxFitIncome;
            champion = "Box Fit";
        }
        if (bodyBlitzIncome > highest) {
            highest = bodyBlitzIncome;
            champion = "Body Blitz";
        }

        System.out.println("Champion Exercise Type: " + champion + " with income £" + String.format("%.2f", highest));
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}