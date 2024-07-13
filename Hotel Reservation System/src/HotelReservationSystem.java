import java.sql.*;
import java.util.Scanner;
public class HotelReservationSystem {

    private static final String url="jdbc:mysql://127.0.0.1:3306/hotel_db";
    private static final String username="root";
    private static final String password="sql12";


    public static void main(String[] args) throws  ClassNotFoundException, SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver load successfull");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        // connention
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");

            Scanner sc=new Scanner(System.in);
            while(true) {
                System.out.println("********** WELCOME IN HOTEL RESERVATION SYSTEM **********");
                System.out.println("1.Reserve Room\n2.View Reservation\n3.Get Room Number\n4.Update Reservation \n5.Delete Reservations \n6.Exit");


                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        reserveRoom(connection, sc);
                        break;
                    case 2:
                        viewReservations(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, sc);
                        break;
                    case 4:
                        updateReservation(connection, sc);
                        break;
                    case 5:
                        deleteReservation(connection, sc);
                        break;
                    case 6:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice! try again");


                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static void reserveRoom(Connection connection, Scanner sc){
        try {
            System.out.println("Enter the Guest Name");
            String guest_name = sc.next();
            sc.nextLine();
            System.out.println("Enter the Room Number");
            int room_number = sc.nextInt();
            System.out.println("Enter the contact Number");
            String contact_number = sc.next();
            sc.nextLine();

            String query = " INSERT INTO reservations (guest_name,room_no,contact_no) " +
                    " VALUES ('" + guest_name + "'," + room_number + ",'" + contact_number + "');";
            try (Statement stmt = connection.createStatement()) {
                int affected = stmt.executeUpdate(query);
                if (affected > 0) {
                    System.out.println("Reservation successfully");
                } else {
                    System.out.println("Reservation Failed !");
                }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void viewReservations(Connection connection){
        String query="SELECT * FROM reservations;";
        try(Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(query)){
            System.out.println("Current Reservations");
            System.out.println("+-----------------+-----------------+----------+-------------------+-------------------------+");
            System.out.println("| Reservation ID  |  Guest Name     |  Room No |  Contact Number   |  Reservation date       |");
            System.out.println("+-----------------+-----------------+----------+-------------------+-------------------------+");

            while(rs.next()) {
                int res_id = rs.getInt("res_id");
                String guest_name = rs.getString("guest_name");
                int room_no = rs.getInt("room_no");
                String contact = rs.getString("contact_no");
                String date = String.valueOf(rs.getTimestamp("res_date"));
                System.out.printf("| %-14d  | %-14s  |  %-6d  |  %-15s  |  %-19s  |\n",
                        res_id, guest_name, room_no, contact, date);
            }
            System.out.println("+-----------------+-----------------+----------+-------------------+-------------------------+");


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getRoomNumber(Connection connection, Scanner sc){
        try{
            System.out.print("Enter the Reservation Id : ");
            int res_id=sc.nextInt();
            System.out.println("Enter the Guest Name");
            String guest_name=sc.next();
            sc.nextLine();

            String query="SELECT room_no FROM reservations where res_id="+res_id+" and guest_name = '" +guest_name+"';";
            try(Statement stmt=connection.createStatement();
                ResultSet sr=stmt.executeQuery(query)){
                if(sr.next()) {
                    int room_number = sr.getInt("room_no");
                    System.out.println("Room Number for Reservation ID : " + room_number + " and Guest " + guest_name + " is : " + room_number);
                }
                else{
                    System.out.println("Reservation not found for the given Id and Name");
                }

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static  void updateReservation(Connection connection , Scanner sc){
        System.out.println("Enter the Reservatin Id to Update");
        int reservationid=sc.nextInt();
        sc.nextLine();
        if(!reservationExists(connection,reservationid)){
            System.out.println("Id Is not found");
            return;

        }
        System.out.println("Enter the new  Guest Name");
        String guest_name = sc.nextLine();
        System.out.println("Enter the new Room Number");
        int room_number = sc.nextInt();
        System.out.println("Enter the new contact Number");
        String contact_number = sc.next();
        sc.nextLine();

        String query = " UPDATE reservations SET guest_name = '"+guest_name+"', room_no="+room_number+", " +
                "contact_no ='"+contact_number+"'where res_id="+reservationid+";";
        try (Statement stmt = connection.createStatement()) {
            int affected = stmt.executeUpdate(query);
            if (affected > 0) {
                System.out.println("Reservation successfull");
            } else {
                System.out.println("Reservation Faild !");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }


    }
    public static void deleteReservation(Connection connection , Scanner sc){
        System.out.println("Enter the ID to delete");
        int id=sc.nextInt();
        if(!reservationExists(connection,id)){
            System.out.println("Reservation not found");
            return ;

        }
        String query="DELETE FROM reservations where res_id="+id+";";
        try(Statement stmt= connection.createStatement()){
            int affected=stmt.executeUpdate(query);
            if(affected>0){
                System.out.println("The Reservation Deleted Successfull .");

            }
            else{
                System.out.println("The Reservation Failed to Delete");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }


    public static boolean reservationExists(Connection connection,int reservationId) {
        try {
            String query = "Select res_id from reservations where res_id=" + reservationId;
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }
    public static void exit()throws  InterruptedException{
        System.out.print("Existing system");
        int i=5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(200);
            i--;
        }
        System.out.println();
        System.out.println("Thankyou for using Reservation System !!");

    }
}
