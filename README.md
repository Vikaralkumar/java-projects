# Hotel Reservation System

This is a simple Hotel Reservation System implemented in Java, which connects to a MySQL database using the JDBC API. The system allows users to reserve rooms, view reservations, get room numbers, update reservations, and delete reservations.

## Features

- **Reserve Room**: Allows users to reserve a room by providing guest name, room number, and contact number.
- **View Reservations**: Displays a list of all current reservations with details.
- **Get Room Number**: Retrieves the room number for a specific reservation ID and guest name.
- **Update Reservation**: Updates reservation details such as guest name, room number, and contact number.
- **Delete Reservation**: Deletes a reservation by reservation ID.
- **Exit**: Exits the system with a thank-you message.

## Prerequisites

- Java Development Kit (JDK) installed.
- MySQL server installed and running.
- MySQL JDBC Driver (Connector/J) in the classpath.

## Setup

1. **Clone the repository**:

    ```bash
    git clone https://github.com/Vikaralkumar/java-projects/tree/main/Hotel%20Reservation%20System
    cd hotel-reservation-system
    ```

2. **Database setup**:

    Create a database named `hotel_db` and a table `reservations` with the following schema:

    ```sql
    CREATE DATABASE hotel_db;

    USE hotel_db;

    CREATE TABLE reservations (
        res_id INT AUTO_INCREMENT PRIMARY KEY,
        guest_name VARCHAR(255) NOT NULL,
        room_no INT NOT NULL,
        contact_no VARCHAR(15) NOT NULL,
        res_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    ```

3. **Update database credentials**:

    Update the `url`, `username`, and `password` in `HotelReservationSystem.java` to match your MySQL database setup.

    ```java
    private static final String url = "jdbc:mysql://127.0.0.1:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "*****";
    ```

## Running the Application

Compile and run the Java application:

```bash
javac HotelReservationSystem.java
java HotelReservationSystem
