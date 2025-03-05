import java.util.*;

class Room {
    int roomNumber;
    String category; 
    boolean isAvailable;
    double price;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
        this.price = price;
    }
}

class Reservation {
    int reservationId;
    String guestName;
    Room room;
    String checkInDate;
    String checkOutDate;

    Reservation(int reservationId, String guestName, Room room, String checkInDate, String checkOutDate) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static int reservationCounter = 1;

    public static void main(String[] args) {
        initializeRooms();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hotel Reservation System!");
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Search for Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(301, "Single", 50.0));
        rooms.add(new Room(302, "Single", 50.0));
        rooms.add(new Room(311, "Double", 80.0));
        rooms.add(new Room(312, "Double", 80.0));
        rooms.add(new Room(501, "Suite", 150.0));
        rooms.add(new Room(402, "Suite", 150.0));
    }

    static void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room " + room.roomNumber + " (" + room.category + ") - $" + room.price + " per night");
            }
        }
    }

    static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        scanner.nextLine(); // Consume newline
        String guestName = scanner.nextLine();

        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room is not available or does not exist. Please try again.");
            return;
        }

        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.next();
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.next();

        selectedRoom.isAvailable = false;
        Reservation reservation = new Reservation(reservationCounter++, guestName, selectedRoom, checkInDate, checkOutDate);
        reservations.add(reservation);

        System.out.println("Reservation successful! Your reservation ID is " + reservation.reservationId);
        processPayment(selectedRoom.price);
    }

    static void viewBookingDetails(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();

        for (Reservation reservation : reservations) {
            if (reservation.reservationId == reservationId) {
                System.out.println("Reservation Details:");
                System.out.println("Guest Name: " + reservation.guestName);
                System.out.println("Room Number: " + reservation.room.roomNumber + " (" + reservation.room.category + ")");
                System.out.println("Check-In Date: " + reservation.checkInDate);
                System.out.println("Check-Out Date: " + reservation.checkOutDate);
                return;
            }
        }

        System.out.println("No reservation found with ID " + reservationId);
    }

    static void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + "...");
        System.out.println("Payment successful!");
    }
}
