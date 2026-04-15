package flcbooking;

import java.util.Scanner;

public class FLCBookingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingSystem system = new BookingSystem();

        int choice;

        do {
            System.out.println("\n========================================");
            System.out.println("Furzefield Leisure Centre Booking System");
            System.out.println("========================================");
            System.out.println("1. Show all members");
            System.out.println("2. Show all lessons");
            System.out.println("3. Show timetable by day");
            System.out.println("4. Show timetable by exercise type");
            System.out.println("5. Book a lesson");
            System.out.println("6. Change a booking");
            System.out.println("7. Cancel a booking");
            System.out.println("8. Attend a lesson and add review");
            System.out.println("9. Monthly lesson report");
            System.out.println("10. Monthly champion exercise report");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Members ---");
                    system.showAllMembers();
                    break;

                case 2:
                    System.out.println("\n--- All Lessons ---");
                    system.showAllLessons();
                    break;

                case 3:
                    System.out.print("Enter month (May or June): ");
                    String dayMonth = scanner.nextLine();

                    System.out.print("Enter day (Saturday or Sunday): ");
                    String day = scanner.nextLine();

                    System.out.println("\n--- Timetable by Day ---");
                    system.showTimetableByDay(dayMonth, day);
                    break;

                case 4:
                    System.out.print("Enter exercise type (Yoga, Zumba, Aquacise, Box Fit, Body Blitz): ");
                    String exerciseType = scanner.nextLine();

                    System.out.println("\n--- Timetable by Exercise Type ---");
                    system.showTimetableByExercise(exerciseType);
                    break;

                case 5:
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();

                    System.out.print("Enter lesson ID: ");
                    String lessonId = scanner.nextLine();

                    System.out.println(system.bookLesson(memberId, lessonId));
                    break;

                case 6:
                    System.out.print("Enter booking ID to change: ");
                    String bookingIdToChange = scanner.nextLine();

                    System.out.print("Enter new lesson ID: ");
                    String newLessonId = scanner.nextLine();

                    System.out.println(system.changeBooking(bookingIdToChange, newLessonId));
                    break;

                case 7:
                    System.out.print("Enter booking ID to cancel: ");
                    String bookingIdToCancel = scanner.nextLine();

                    System.out.println(system.cancelBooking(bookingIdToCancel));
                    break;

                case 8:
                    System.out.print("Enter booking ID to attend: ");
                    String bookingIdToAttend = scanner.nextLine();

                    System.out.print("Enter rating (1 to 5): ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Please enter a valid rating number: ");
                        scanner.next();
                    }
                    int rating = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter review comment: ");
                    String comment = scanner.nextLine();

                    System.out.println(system.attendLesson(bookingIdToAttend, rating, comment));
                    break;

                case 9:
                    System.out.print("Enter month for report (May or June): ");
                    String reportMonth = scanner.nextLine();

                    system.printMonthlyLessonReport(reportMonth);
                    break;

                case 10:
                    System.out.print("Enter month for champion report (May or June): ");
                    String championMonth = scanner.nextLine();

                    system.printMonthlyChampionReport(championMonth);
                    break;

                case 11:
                    System.out.println("Exiting program. Goodbye.");
                    break;

                default:
                    System.out.println("Invalid choice. Please select from 1 to 11.");
            }

        } while (choice != 11);

        scanner.close();
    }
}