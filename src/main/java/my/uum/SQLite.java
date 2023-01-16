package my.uum;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SQLite {

    public static Connection connect() {
        String jdbc = "jdbc:sqlite:C:/sqlite/database/database.db";
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


    public void delete(String staff_id) {
        String sql = "DELETE FROM tbl_booking WHERE staff_id= " + staff_id;
        try {
            Connection con = SQLite.connect();
            PreparedStatement stmt;

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String display() {
        String sql = "SELECT *  FROM tbl_booking INNER JOIN tbl_room ON tbl_booking.room_id = tbl_room.room_id";

        String message = "Below are the list of users." + "\n";

        try (Connection con = SQLite.connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            // loop through the result set
            while (rs.next()) {
                int staffid = rs.getInt("staff_id");
                int bookingid = rs.getInt("booking_id");
                int roomid = rs.getInt("room_id");
                int maxcapa = rs.getInt("max_capa");
                message += "\n" +
                        "Booking ID: " + String.valueOf(bookingid) + "\n"
                        + '\n' +
                        "IC NO: " + rs.getString("ic_no") + "\n"
                        + '\n' +
                        "Staff ID: " + String.valueOf(staffid) + "\n"
                        + '\n' +
                        "Name: " + rs.getString("name") + "\n"
                        + '\n' +
                        "Mobile NO: " + rs.getString("mobile_telno") + "\n"
                        + '\n' +
                        "Email: " + rs.getString("email") + "\n"
                        + '\n' +
                        "Purpose of booking: " + rs.getString("purpose") + "\n"
                        + '\n' +
                        "Booking Date: " + rs.getString("booking_date") + "\n"
                        + '\n' +
                        "Booking Time: " + rs.getString("booking_time") + "\n"
                        + '\n' +
                        "Room ID: " + String.valueOf(roomid) + "\n"
                        + '\n' +
                        "Room Description: " + rs.getString("room_desc") + "\n"
                        + '\n' +
                        "Room Maximum Capacity: " + String.valueOf(maxcapa) + "\n"
                        + '\n';


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

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

    public String checkadmin(String email, String password) {
        String message = "false";
        String sql = "SELECT * FROM tbl_users INNER JOIN tbl_admin ON tbl_users.user_email=tbl_admin.user_email WHERE tbl_admin.user_email= '" + email + "' AND tbl_users.user_password= '" + password + "' AND tbl_admin.user_status='Approved'";

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

    public String dispApplication() {
        String sql = "SELECT * FROM tbl_users INNER JOIN tbl_admin ON tbl_users.user_email=tbl_admin.user_email WHERE tbl_admin.user_status='Pending approval'";
        String msg1 = "";

        try {
            Connection con = SQLite.connect();
            Statement stmt;

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String msg = "Email: " + rs.getString("user_email") +
                        "\nStaff ID: " + rs.getString("staff_id") +
                        "\nName: " + rs.getString("user_name") +
                        "\nIC No.: " + rs.getString("user_ic") +
                        "\nPhone No.: " + rs.getString("user_phone") +
                        "\nSchool ID: " + rs.getInt("school_id") +
                        "\nAdmin ID: " + rs.getInt("admin_id") +
                        "\nStatus: " + rs.getString("user_status") +
                        "\n\n";


                StringBuffer bfr = new StringBuffer(msg);
                bfr.append(msg);
                msg1 = msg + msg1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg1;
    }

    public void approveAdmin(String admin_id) {

        String sql = "UPDATE tbl_admin SET user_status = 'Approved' WHERE admin_id = '" + admin_id + "';";
        try {
            Connection con = SQLite.connect();
            PreparedStatement ps;

            ps = con.prepareStatement(sql);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This method is used to run sql query for deleting the data that is 7 days older than current date
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

}