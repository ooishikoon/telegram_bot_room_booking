package my.uum;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class is to connect database and retrieve data inputted by the user
 * @author koko
 **/
public class SQLite {
    /**
     * This method is to connect the sqlite database
     **/
    public static Connection connect() {
        String jdbc = "jdbc:sqlite:C:/Users/MAY NG/Downloads/database (1).db";
        Connection con = null;

        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(jdbc);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    /**
     * This method is to retrieve and insert user personal information into tbl_users
     **/
    public void insert(String ic_no, String staff_id, String name, String mobile_telno, String email, String password) {

        String sql = "INSERT INTO tbl_users (user_email,staff_id,user_name,user_ic,user_phone,user_password) VALUES (?,?,?,?,?,?);";
        try {
            Connection con = SQLite.connect();
            PreparedStatement ps;

            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, staff_id);
            ps.setString(3, name);
            ps.setString(4, ic_no);
            ps.setString(5, mobile_telno);
            ps.setString(6, password);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to check the validation of the user's account
     **/
    public String checkuser(String email, String password) {
        String message = "false";
        String sql = "SELECT * FROM tbl_users WHERE user_email= '" + email + "' AND user_password= '" + password + "'";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                message = "true";
            }
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;

    }

    /**
     * This method is to check the validation of email of the user
     **/
    public String checkemail(String email) {
        String message = "true";
        String sql = "SELECT * FROM tbl_users WHERE user_email= '" + email + "'";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                message = "false";
            }
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;

    }

    /**
     * This method is to delete a booking reserved by the user
     **/
    public void deletebooking(String room_id) {
        String sql = "DELETE FROM tbl_booking WHERE available_room_id= '" + room_id+"'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to update the status of a room
     **/
    public void updateroomstatus(String room_id) {
        String sql = "Update tbl_availability SET available_status=null WHERE tbl_availability.available_room_id= '" + room_id+"'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to retrieve the api token of the telegram bot
     **/
    public void applyadmin(String user_email, String school_id, String admin_status) {
        String sql = "INSERT INTO tbl_admin (user_email,school_id,admin_status) VALUES (?,?,?);";
        try {
            Connection con = SQLite.connect();
            PreparedStatement ps;

            ps = con.prepareStatement(sql);
            ps.setString(1, user_email);
            ps.setString(2, school_id);
            ps.setString(3, admin_status);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to review the user's personal information
     **/
    public String readuserinfo(String user_email,String user_password) {
        String sql = "SELECT * FROM tbl_users WHERE user_email= '"+user_email+"' AND user_password= '"+user_password+"'";

        String message = "Below are your latest info with us!"+ "\n";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                message +=  "\n" +
                        "1. Email: " + rs.getString("user_email")+ "\n"
                        + '\n' +
                        "2. Staff_ID: " + rs.getString("staff_id") + "\n"
                        + '\n' +
                        "3. Name: " + rs.getString("user_name") + "\n"
                        + '\n' +
                        "4. IC No: " +rs.getString("user_ic") + "\n"
                        + '\n' +
                        "5. Phone NO: " + rs.getString("user_phone") + "\n"
                        + '\n' +
                        "6. Password: " + rs.getString("user_password") + "\n"
                        + '\n' +
                        "Please enter the numbering of information you want to edit. \n"
                        + '\n' +
                        "Enter 0: Back to menu \n"
                        + '\n' ;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return message;
    }
    /**
     * This method is to print out the user's booking information
     **/
    public String readuserbooking(String user_email) {
        String sql = "SELECT * FROM tbl_booking INNER JOIN tbl_availability ON tbl_booking.available_room_id=tbl_availability.available_room_id WHERE tbl_booking.user_email= '"+user_email+ "'";

        String message = "";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                message +=  "\n" +
                        "1. Room ID: " + rs.getString("available_room_id") + "\n"
                        + '\n' +
                        "2. Email: " + rs.getString("user_email") + "\n"
                        + '\n' +
                        "3. Booking ID: " + rs.getString("booking_id")+ "\n"
                        + '\n' +
                        "4. Booking Purpose: " +rs.getString("booking_purpose") + "\n"
                        + '\n' +
                        "5. Booking Date: " + rs.getString("booking_date") + "\n"
                        + '\n' +
                        "6. Booking Time: " + rs.getString("available_time") + "\n"
                        + '\n' ;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

    /**
     * This method is to review and delete a specific booking
     **/
    public String readuserchosendeletebooking(String room_id) {
        String sql = "SELECT * FROM tbl_booking INNER JOIN tbl_availability ON tbl_booking.available_room_id=tbl_availability.available_room_id WHERE tbl_booking.available_room_id= '"+room_id+ "'";

        String message = "";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                message =  "Do you want to delete this booking? \n" +
                        "\n" +
                        "1. Booking ID: " + rs.getString("booking_id")+ "\n"
                        + '\n' +
                        "2. Email: " + rs.getString("user_email") + "\n"
                        + '\n' +
                        "3. Room ID: " + rs.getString("available_room_id") + "\n"
                        + '\n' +
                        "4. Booking Purpose: " +rs.getString("booking_purpose") + "\n"
                        + '\n' +
                        "5. Booking Date: " + rs.getString("booking_date") + "\n"
                        + '\n' +
                        "6. Booking Time: " + rs.getString("available_time") + "\n"
                        + '\n' +
                        "Enter 1: Confirm \n"
                        + '\n' +
                        "Enter 0: Back to menu \n"
                        + '\n' ;;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

    /**
     * This method is to update the user's email
     **/
    public void updateuseremail(String user_emailnew, String user_emailold) {
        String sql = "Update tbl_users SET user_email= '"+user_emailnew+"' Where user_email= '"+user_emailold+"'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to update the admin's email
     **/
    public void updateadminemail(String user_emailnew, String user_emailold) {
        String sql = "Update tbl_admin SET user_email= '"+user_emailnew+"' Where user_email= '"+user_emailold+"'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to update the user's staff id
     **/
    public void updateuserstaffid(String staff_id, String user_email) {
        String sql = "Update tbl_users SET staff_id= '"+staff_id+"' Where user_email= '"+user_email+"'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to update the user's name
     **/
    public void updateusername(String name, String user_email) {
        String sql = "Update tbl_users SET user_name= '"+name+"' Where user_email= '"+user_email+"'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to update the user's ic number
     **/
    public void updateuseric(String ic, String user_email) {
        String sql = "Update tbl_users SET user_ic= '"+ic+"' Where user_email= '"+user_email+"'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to update the user's phone number
     **/
    public void updateuserphone(String phone, String user_email) {
        String sql = "Update tbl_users SET user_phone= '"+phone+"' Where user_email= '"+user_email+"'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to update the user's password
     **/
    public void updateuserpassword(String password, String user_email) {
        String sql = "Update tbl_users SET user_password= '"+password+"' Where user_email= '"+user_email+"'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to check the validation of an admin
     **/
    public String checkadmin(String email, String password) {
        String message = "false";
        String sql = "SELECT * FROM tbl_users INNER JOIN tbl_admin ON tbl_users.user_email=tbl_admin.user_email WHERE tbl_admin.user_email= '" + email + "' AND tbl_users.user_password= '" + password + "' AND tbl_admin.admin_status='Approved'";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                message = "true";
            }
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;

    }

    /**
     * This method is to register a new room into the list
     **/
    public void regRoom(String room_id, String room_desc, String room_max_cap, String room_type) {

        String sql = "INSERT INTO tbl_room (room_id,room_description,room_max_capacity,room_type) VALUES (?,?,?,?);";
        int roomID = Integer.parseInt(room_id);
        try {
            Connection con = SQLite.connect();
            PreparedStatement ps;

            ps = con.prepareStatement(sql);
            ps.setInt(1, roomID);
            ps.setString(2, room_desc);
            ps.setString(3, room_max_cap);
            ps.setString(4, room_type);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to check the validation of a room
     **/
    public String checkroom(String room_id) {
        String message = "false";
        String sql = "SELECT * FROM tbl_room WHERE room_id= '" + room_id + "';";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                message = "true";
            }
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;

    }

    /**
     * This method is to update the information of a room
     **/
    public void upRoom(String room_id, String room_desc, String room_max_cap, String room_type) {

        String sql = "UPDATE tbl_room SET room_description = '" + room_desc + "', room_max_capacity = '" + room_max_cap + "', room_type = '" + room_type + "'WHERE room_id = '" + room_id + "';";
        int roomID2 = Integer.parseInt(room_id);
        try {
            Connection con = SQLite.connect();
            PreparedStatement ps;

            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to display admin application list
     **/
    public String dispApplication() {
        String sql = "SELECT * FROM tbl_users INNER JOIN tbl_admin ON tbl_users.user_email=tbl_admin.user_email WHERE tbl_admin.admin_status='Pending approval'";
        String msg = "";

        try {
            Connection con = SQLite.connect();
            Statement stmt;

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                msg += "\nEmail: " + rs.getString("user_email") +
                        "\nStaff ID: " + rs.getString("staff_id") +
                        "\nName: " + rs.getString("user_name") +
                        "\nIC No.: " + rs.getString("user_ic") +
                        "\nPhone No.: " + rs.getString("user_phone") +
                        "\nSchool ID: " + rs.getInt("school_id") +
                        "\nAdmin ID: " + rs.getInt("admin_id") +
                        "\nStatus: " + rs.getString("admin_status") +
                        "\n\n";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * This method is to approve admin application
     **/
    public void approveAdmin(String admin_id) {

        String sql = "UPDATE tbl_admin SET admin_status = 'Approved' WHERE admin_id = '" + admin_id + "';";
        try {
            Connection con = SQLite.connect();
            PreparedStatement ps;

            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to run sql query for deleting the data that is 7 days older than current date
     **/
    public void deleteData(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -7);

        String sql_checkDate = "DELETE FROM `tbl_booking` WHERE booking_date <= ?";
        try {
            Connection con = SQLite.connect();
            PreparedStatement ps;
            ps = con.prepareStatement(sql_checkDate);
            ps.setString(1, formatter.format(cal.getTime()));
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to check the date that the user input to match the available date
     **/
    public String checkdate(String availableDate) {
        String message = "false";
        String sql = "SELECT * FROM tbl_availability WHERE available_date= '" + availableDate + "'";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                message = "true";
            }
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;

    }

    /**
     * This method is used to list the information of the booking to be confirm
     **/
    public String displayBookingConfirmation(String availableRoomID){
        String bookingconfirmationrecord = "";

        String sqlDisplay = "SELECT tbl_availability.available_room_id, tbl_availability.available_date, tbl_availability.available_time, tbl_room.room_type FROM tbl_availability INNER JOIN tbl_room ON tbl_availability.room_id = tbl_room.room_id WHERE tbl_availability.available_room_id = '"+availableRoomID+"'";

        try (
                Connection con = SQLite.connect();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sqlDisplay)){

            // loop through the result set
            while (rs.next()) {
                bookingconfirmationrecord =
                        "\n" + "Room ID: " + rs.getInt("available_room_id") +
                                "\n" +  "Room Type: " + rs.getString("room_type") +
                                "\n" + "Booking Date: " + rs.getString("available_date") +
                                "\n" + "Booking Time: " + rs.getString("available_time");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingconfirmationrecord;
    }

    /**
     * This method is used to register a new room booking
     **/
    public void addBooking(String user_email, int available_room_id, String booking_purpose, String booking_date) {
        String sql = "INSERT INTO tbl_booking (user_email, available_room_id, booking_purpose, booking_date) " +
                "VALUES (?,?,?,?);";
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.setString(1, user_email);
            stmt.setInt(2, available_room_id);
            stmt.setString(3, booking_purpose);
            stmt.setString(4, booking_date);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to set available status in tbl_availability to 'booked'
     **/
    public void bookingStatusB(int avail_room_id) {

        String sql = "UPDATE tbl_availability SET available_status = 'booked' WHERE tbl_availability.available_room_id = '" + avail_room_id + "'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement ps;

            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to set available status in tbl_availability to 'null'
     **/
    public void bookingStatusN(int avail_room_id) {

        String sql = "UPDATE tbl_availability SET available_status = null WHERE tbl_availability.available_room_id = '" + avail_room_id + "'";
        try {
            Connection con = SQLite.connect();
            PreparedStatement ps;

            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to display all the booking record based on the email
     **/
    public String displayAllBookingRecord(String userEmail) {
        String booking = "";

        String sqlDisplay = "SELECT * FROM ((tbl_availability INNER JOIN tbl_room ON tbl_availability.room_id = tbl_room.room_id)INNER JOIN tbl_booking on tbl_availability.available_room_id=tbl_booking.available_room_id) WHERE tbl_booking.user_email='" + userEmail + "'";

        try (
                Connection con = SQLite.connect();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sqlDisplay)) {

            // loop through the result set
            while (rs.next()) {
                booking +=
                        "\nRoom ID: " + rs.getString("available_room_id") +
                                "\nRoom Type: " + rs.getString("room_type") +
                                "\nRoom Description: " + rs.getString("room_description") +
                                "\nRoom Capacity: " + rs.getString("room_max_capacity") +
                                "\nBooking Date: " + rs.getString("available_date") +
                                "\nBooking Time: " + rs.getString("available_time") +
                                "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    /**
     * This method is used to display the room booking list of the users available
     **/
    public String displayUserslist() {
        String users = "";
        String sqlList = "SELECT *\n" +
                "FROM tbl_users\n" +
                "    INNER JOIN tbl_booking ON tbl_users.user_email = tbl_booking.user_email\n" +
                "    INNER JOIN tbl_availability ON tbl_booking.available_room_id = tbl_availability.available_room_id\n" +
                "INNER JOIN tbl_room ON tbl_availability.room_id = tbl_room.room_id";

        try (
            Connection con = SQLite.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlList)) {

                while (rs.next()) {
                    users +=
                            "\n" + "User Email: " + rs.getString("user_email") +
                                    "\n" +  "Booking Purpose: " + rs.getString("booking_purpose") +
                                    "\n" +  "Room ID: " + rs.getString("available_room_id") +
                                    "\n" + "Room Description: " + rs.getString("room_description") +
                                    "\n" + "Room Max Capacity: " + rs.getString("room_max_capacity") +
                                    "\n" + "Room Type: " + rs.getString("room_type") +
                                    "\n" + "Booking Date: " + rs.getString("booking_date") +
                                    "\n" + "Booking Time: " + rs.getString("available_time")
                    + "\n";

                    }
                }catch (SQLException e) {
            e.printStackTrace();
            }
        return users;
        }

    /**
     * This method is used to check the room status is null or booked
     **/
    public String checkRoomStatus(String date) {
        String message = "false";
        String sql = "SELECT * FROM tbl_availability WHERE available_status = 'null' AND available_date = '"+ date +"'";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                message = "true";
            }
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;

    }

    /**
     * This method is used to display the available room list on the certain date
     **/
    public String displayAvailableRoom(String userdate){
        String availablerecord = "";

        String sqlDisplay = "SELECT tbl_availability.available_room_id, tbl_availability.available_date, tbl_availability.available_time, tbl_room.room_type FROM tbl_availability INNER JOIN tbl_room ON tbl_availability.room_id = tbl_room.room_id WHERE tbl_availability.available_status = 'null' AND tbl_availability.available_date = '" + userdate+ "'";

        try (
                Connection con = SQLite.connect();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sqlDisplay)){

            // loop through the result set
            while (rs.next()) {
                availablerecord +=
                        "\n" + rs.getInt("available_room_id") +
                                "\t" + rs.getString("room_type") +
                                "\t" + rs.getString("available_time");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availablerecord;
        }

    }
