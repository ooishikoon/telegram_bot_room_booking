package my.uum;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is for enabling the telegram bot to run according to specific command and user input
 * @author koko
 **/
public class Bot extends TelegramLongPollingBot {
    SQLite sql = new SQLite();
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();  // map for user to register new account
    Map<String, ArrayList<String>> map1 = new HashMap<String, ArrayList<String>>(); // map for user to log in if already has account
    Map<String, ArrayList<String>> map2 = new HashMap<String, ArrayList<String>>(); // map for user apply admin
    Map<String, ArrayList<String>> map3 = new HashMap<String, ArrayList<String>>(); // map for admin login
    Map<String, ArrayList<String>> map4 = new HashMap<String, ArrayList<String>>(); // map for register room
    Map<String, ArrayList<String>> map5 = new HashMap<String, ArrayList<String>>(); // map for update room
    Map<String, ArrayList<String>> map6 = new HashMap<String, ArrayList<String>>(); // map for display booking users list
    Map<String, ArrayList<String>> map7 = new HashMap<String, ArrayList<String>>(); // map for display admin application list
    Map<String, ArrayList<String>> map8 = new HashMap<String, ArrayList<String>>(); // map for user update information
    Map<String, ArrayList<String>> map9 = new HashMap<String, ArrayList<String>>(); // map for booking process
    Map<String, ArrayList<String>> map10 = new HashMap<String, ArrayList<String>>(); // map for user delete booking
    Map<String, ArrayList<String>> map11 = new HashMap<String, ArrayList<String>>(); // map for update booking
    Map<String, String> testmap = new HashMap<String, String>();
    String admin_status = "Pending approval";

    /**
     * This method is to update commands and input given by the user
     * Instructions are listed out
     **/
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
        System.out.println(update.getMessage().getFrom().getFirstName());
        String command = update.getMessage().getText();
        System.out.println(testmap.get(update.getMessage().getChatId().toString()));
        SendMessage response = new SendMessage();

        if (!map.containsKey(update.getMessage().getChatId().toString())) {
            map.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map1.containsKey(update.getMessage().getChatId().toString())) {
            map1.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map2.containsKey(update.getMessage().getChatId().toString())) {
            map2.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map3.containsKey(update.getMessage().getChatId().toString())) {
            map3.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map4.containsKey(update.getMessage().getChatId().toString())) {
            map4.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map5.containsKey(update.getMessage().getChatId().toString())) {
            map5.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map6.containsKey(update.getMessage().getChatId().toString())) {
            map6.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map7.containsKey(update.getMessage().getChatId().toString())) {
            map7.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map8.containsKey(update.getMessage().getChatId().toString())) {
            map8.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map9.containsKey(update.getMessage().getChatId().toString())) {
            map9.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map10.containsKey(update.getMessage().getChatId().toString())) {
            map10.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }
        if (!map11.containsKey(update.getMessage().getChatId().toString())) {
            map11.put(update.getMessage().getChatId().toString(), new ArrayList<String>());
        }

        //main menu after enter bot
        if (command.equals("/start")
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("adminemail"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("adminpassword"))
                || (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("menu"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("mainmenu"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("loginusermenu"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("usermenu"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("loginuseremail"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("loginuserpassword"))
        ) {
            String message = "Hi there! I'm Koko, Bot Virtual Assistant.\n"
                    + '\n' +
                    "Welcome to UUM Room Booking Bot. \n"
                    + '\n' +
                    "Live chat with me to book a meeting room or any enquires. \n"
                    + '\n' +
                    "Our room are available as usual on Monday-Sunday, 8am-5pm for your needs.\n"
                    + '\n' +
                    "Please choose an option to begin.\n"
                    + '\n' +
                    "Reply 1: If you are a user. \n"
                    + '\n' +
                    "Reply 2: If you are an admin. \n"
                    + '\n' +
                    "Reply 0: Back to menu.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "mainmenu");
        }
        //end main menu after enter bot

        //user menu after they choose user not admin
        else if ((command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("mainmenu"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("rusericno"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("rstaffid"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("rusername"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("ruserphone"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("ruseremail"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("ruserpassword"))
        ) {
            String message = "Welcome to UUM Room Booking Bot. \n"
                    + '\n' +
                    "Please choose an option to begin as an user.\n"
                    + '\n' +
                    "1: Register if you are first time user \n"
                    + '\n' +
                    "2: Log in if you already have an account.  \n"
                    + '\n' +
                    "Reply 0: Back to menu."
                    ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "usermenu");
        }
        //end user menu after they choose user not admin

        //start register as new user
        //1. ic no
        else if (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("usermenu")) {
            String message = "1. First. Please enter your IC No.\n\n(Example: 000619126688)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "rusericno");
        }
        //2. staff id
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("rusericno")) {
            if (map.get(update.getMessage().getChatId().toString()).size() == 0) {
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map.get(update.getMessage().getChatId().toString()).set(0, update.getMessage().getText());
            }
            String message = "2. Please enter your Staff ID.\n\n(Example: 278788)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "rstaffid");
        }
        //3. name
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("rstaffid")) {
            if (map.get(update.getMessage().getChatId().toString()).size() == 1) {
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map.get(update.getMessage().getChatId().toString()).set(1, update.getMessage().getText());
            }
            String message = "3. Please enter your full name.\n\n(Example: Max Tin)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "rusername");
        }
        //4. phone no
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("rusername")) {
            if (map.get(update.getMessage().getChatId().toString()).size() == 2) {
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map.get(update.getMessage().getChatId().toString()).set(2, update.getMessage().getText());
            }
            String message = "4. Almost there! Please enter your phone no.\n\n(Example: 01251106688)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "ruserphone");
        }
        //5. email
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("ruserphone")) {
            if (map.get(update.getMessage().getChatId().toString()).size() == 3) {
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map.get(update.getMessage().getChatId().toString()).set(3, update.getMessage().getText());
            }
            String message = "5. Please enter your email.\n\n(Example: max@gmail.com)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "ruseremail");
        }
        //6. password
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("ruseremail")) {
            if (map.get(update.getMessage().getChatId().toString()).size() == 4) {
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map.get(update.getMessage().getChatId().toString()).set(4, update.getMessage().getText());
            }
            String message = "6. Please enter your password.\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "ruserpassword");
        }
        //registration details
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("ruserpassword")) {
            if (map.get(update.getMessage().getChatId().toString()).size() == 5) {
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map.get(update.getMessage().getChatId().toString()).set(5, update.getMessage().getText());
            }
            sql.checkemail(map.get(update.getMessage().getChatId().toString()).get(4));
            String bool = sql.checkemail(map.get(update.getMessage().getChatId().toString()).get(4));
            if (bool.equals("true")) {
                sql.insert(map.get(update.getMessage().getChatId().toString()).get(0), map.get(update.getMessage().getChatId().toString()).get(1), map.get(update.getMessage().getChatId().toString()).get(2), map.get(update.getMessage().getChatId().toString()).get(3), map.get(update.getMessage().getChatId().toString()).get(4), map.get(update.getMessage().getChatId().toString()).get(5));
                String message = "Registration Successful. Below are your details. \n"
                        + '\n' +
                        "IC No: " + map.get(update.getMessage().getChatId().toString()).get(0) + '\n'
                        + '\n' +
                        "Staff ID: " + map.get(update.getMessage().getChatId().toString()).get(1) + '\n'
                        + '\n' +
                        "Name: " + map.get(update.getMessage().getChatId().toString()).get(2) + '\n'
                        + '\n' +
                        "Phone: " + map.get(update.getMessage().getChatId().toString()).get(3) + '\n'
                        + '\n' +
                        "Email: " + map.get(update.getMessage().getChatId().toString()).get(4) + '\n'
                        + '\n' +
                        "You may log in to proceed with booking. \n"
                        + '\n' +
                        "Reply 0: Back to menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "mainmenu");
            } else if (bool.equals("false")) {
                String message = "Registration Fail. \n"
                        + '\n' +
                        "This email has been registered.\n"+
                        '\n' +
                        "You may log in to proceed.\n"
                        + '\n' +
                        "Reply 0: Back to menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "mainmenu");
            }

        }
        //end register user

        // start user log in
        //1. input email
        else if (command.equals("2") && testmap.get(update.getMessage().getChatId().toString()).equals("usermenu")) {
            String message = "1. Please enter your email.\n\nReply 0: If you do not wish to proceed with the login.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "loginuseremail");
        }
        //2. input password
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("loginuseremail")) {
            if (map1.get(update.getMessage().getChatId().toString()).size() == 0) {
                map1.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map1.get(update.getMessage().getChatId().toString()).set(0, update.getMessage().getText());
            }
            String message = "2. Please enter your password.\n\nReply 0: If you do not wish to proceed with the login.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "loginuserpassword");
        }
        //check login
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("loginuserpassword")) {
            if (map1.get(update.getMessage().getChatId().toString()).size() == 1) {
                map1.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map1.get(update.getMessage().getChatId().toString()).set(1, update.getMessage().getText());
            }
            sql.checkuser(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1));
            String bool = sql.checkuser(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1));
            if (bool.equals("true")) {
                String message = "Successfully Logged In!\n"
                        + '\n' +
                        "Reply 1: Continue to Menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "loggedin");
            } else if (bool.equals("false")) {
                String message = "Login fail. Please try again or perform register if you are a new user.\n" +
                        '\n' +
                        "Reply 1: Back to menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "mainmenu");
            }
        }
        //end user login

        //start user logged in main menu
        else if ((command.equals("1")  && testmap.get(update.getMessage().getChatId().toString()).equals("loggedin"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("doneapplyadmin"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("submitapplyadmin"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("applyadminemail"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("chooseschool"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("chooseupdateinfo"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("userupdateemail"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("userupdatestaffid"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("userupdatename"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("userupdateicno"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("userupdatephone"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("userupdatepw"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("choosedeletebooking"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("donechoosedeletebooking"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("deletebookingpassword"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("deletepwwrong"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("deletesuccess"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("updatebooking"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("bookdate"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("availablelist"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("availablelist1"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("bookingpurpose"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("bookingConfirmation"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("bookingsuccess"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("updatedate"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("updatepurpose"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("updateConfirmation"))

        ) {
            String message =
                    "Please choose an option to begin.\n"
                            + '\n' +
                            "1: Update Personal Info\n"
                            + '\n' +
                            "2: Book a Room \n"
                            + '\n' +
                            "3: Update Booking \n"
                            + '\n' +
                            "4: Delete Booking \n"
                            + '\n' +
                            "5: Apply School Admin \n"
                            + "\n" +
                            "Reply 0: Back to menu."
                    ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "loginusermenu");
        }
        //end user logged in main menu

        //start user update info
        else if (command.equals("1")  && testmap.get(update.getMessage().getChatId().toString()).equals("loginusermenu")) {
            sql.readuserinfo(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1));
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(sql.readuserinfo(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1)));
            testmap.put(update.getMessage().getChatId().toString(),"chooseupdateinfo");
        }

        //start user update email
        else if (command.equals("1")  && testmap.get(update.getMessage().getChatId().toString()).equals("chooseupdateinfo")) {
            String message = "Please enter your new email.\n\n(Example: max@gmail.com)\n\nReply 0: If you do not wish to proceed with the update.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"userupdateemail");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("userupdateemail")) {
            if(map8.get(update.getMessage().getChatId().toString()).size()==0){
                map8.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map8.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            sql.updateuseremail(map8.get(update.getMessage().getChatId().toString()).get(0),map1.get(update.getMessage().getChatId().toString()).get(0));
            sql.updateadminemail(map8.get(update.getMessage().getChatId().toString()).get(0),map1.get(update.getMessage().getChatId().toString()).get(0));
            map1.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            String message = "Email updated successfully.\n\n" + sql.readuserinfo(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1));
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"chooseupdateinfo");
        }
        //end user update email

        //start user update staff_id
        else if (command.equals("2")  && testmap.get(update.getMessage().getChatId().toString()).equals("chooseupdateinfo")) {
            String message = "Please enter your new staff ID.\n\n(Example: 278888)\n\nReply 0: If you do not wish to proceed with the update.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"userupdatestaffid");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("userupdatestaffid")) {
            if(map8.get(update.getMessage().getChatId().toString()).size()==0){
                map8.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map8.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            sql.updateuserstaffid(map8.get(update.getMessage().getChatId().toString()).get(0),map1.get(update.getMessage().getChatId().toString()).get(0));
            String message = "Staff ID updated successfully.\n\n" + sql.readuserinfo(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1)) ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"chooseupdateinfo");
        }
        //end user update staff_id

        //start user update name
        else if (command.equals("3")  && testmap.get(update.getMessage().getChatId().toString()).equals("chooseupdateinfo")) {
            String message = "Please enter your new name.\n\n(Example: Thomas Wong)\n\nReply 0: If you do not wish to proceed with the update.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"userupdatename");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("userupdatename")) {
            if(map8.get(update.getMessage().getChatId().toString()).size()==0){
                map8.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map8.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            sql.updateusername(map8.get(update.getMessage().getChatId().toString()).get(0),map1.get(update.getMessage().getChatId().toString()).get(0));
            String message = "Name updated successfully.\n\n" + sql.readuserinfo(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1)) ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"chooseupdateinfo");
        }
        //end user update name

        //start user update icno
        else if (command.equals("4")  && testmap.get(update.getMessage().getChatId().toString()).equals("chooseupdateinfo")) {
            String message = "Please enter your new IC no.\n\n(Example: 000516131122)\n\nReply 0: If you do not wish to proceed with the update.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"userupdateicno");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("userupdateicno")) {
            if(map8.get(update.getMessage().getChatId().toString()).size()==0){
                map8.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map8.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            sql.updateuseric(map8.get(update.getMessage().getChatId().toString()).get(0),map1.get(update.getMessage().getChatId().toString()).get(0));
            String message = "IC No updated successfully.\n\n" + sql.readuserinfo(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1)) ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"chooseupdateinfo");
        }
        //end user update icno

        //start user update phone
        else if (command.equals("5")  && testmap.get(update.getMessage().getChatId().toString()).equals("chooseupdateinfo")) {
            String message = "Please enter your new phone no.\n\n(Example: 01151108899)\n\nReply 0: If you do not wish to proceed with the update.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"userupdatephone");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("userupdatephone")) {
            if(map8.get(update.getMessage().getChatId().toString()).size()==0){
                map8.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map8.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            sql.updateuserphone(map8.get(update.getMessage().getChatId().toString()).get(0),map1.get(update.getMessage().getChatId().toString()).get(0));
            String message = "Phone No updated successfully.\n\n" +sql.readuserinfo(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1)) ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"chooseupdateinfo");
        }
        //end user update phone

        //start user update password
        else if (command.equals("6")  && testmap.get(update.getMessage().getChatId().toString()).equals("chooseupdateinfo")) {
            String message = "Please enter your new password.\n\nReply 0: If you do not wish to proceed with the update.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"userupdatepw");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("userupdatepw")) {
            if(map8.get(update.getMessage().getChatId().toString()).size()==0){
                map8.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map8.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            sql.updateuserpassword(map8.get(update.getMessage().getChatId().toString()).get(0),map1.get(update.getMessage().getChatId().toString()).get(0));
            map1.get(update.getMessage().getChatId().toString()).set(1,update.getMessage().getText());
            String message = "Password updated successfully.\n\n" + sql.readuserinfo(map1.get(update.getMessage().getChatId().toString()).get(0), map1.get(update.getMessage().getChatId().toString()).get(1)) ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"chooseupdateinfo");
        }
        //end user update password
        //end user update info

        // start book new room process
        else if ((command.equals("2") && testmap.get(update.getMessage().getChatId().toString()).equals("loginusermenu"))
                || (command.equals("2") && testmap.get(update.getMessage().getChatId().toString()).equals("bookingConfirmation"))
                || (command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("datefull"))
        ){
            String message = "Please enter the date you want to book." +
                    "\n\n(Example: yyyy-mm-dd)" +
                    "\n\nRemind: You only can book the room within 10 days from now." +
                    "\n\nReply 0: If you do not wish to proceed with the booking.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"bookdate");
        }

        //check the date input
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("bookdate")
                ||(command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("datefull"))
        ) {
            if(map9.get(update.getMessage().getChatId().toString()).size()==0){
                map9.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map9.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            sql.checkdate(map9.get(update.getMessage().getChatId().toString()).get(0));
            String bool = sql.checkdate(map9.get(update.getMessage().getChatId().toString()).get(0));
            String bool1 = sql.checkRoomStatus(map9.get(update.getMessage().getChatId().toString()).get(0));
            //check room status
            if (bool.equals("true")) {
                if (bool1.equals("true")){
                    String message = "Available Rooms on : " + map9.get(update.getMessage().getChatId().toString()).get(0) + "\n";
                    String message1 = "\n\nPlease select the slot that you want to book." + "\n" +
                            "\nPlease enter the room ID (4XXX).\n\n" +
                            "Reply 0: Back to menu.\n";
                    response.setText(message + sql.displayAvailableRoom(map9.get(update.getMessage().getChatId().toString()).get(0) ) + message1);
                    response.setChatId(update.getMessage().getChatId().toString());
                    testmap.put(update.getMessage().getChatId().toString(),"availablelist");
                }
                else if (bool1.equals("false")){
                    String message = "The slot on " + map9.get(update.getMessage().getChatId().toString()).get(0) + " is full.\n\n" +
                            "Please another date." + "\n\n" +
                            "Reply 0: To choose another date.\n";
                    response.setChatId(update.getMessage().getChatId().toString());
                    response.setText(message);
                    testmap.put(update.getMessage().getChatId().toString(),"datefull");
                }
            } else if (bool.equals("false")) {
                String message = "The slot on " + map9.get(update.getMessage().getChatId().toString()).get(0) + " is not available to be book yet.\n\n" +
                        "Please another date." + "\n\n" +
                        "Reply 0: To choose another date.\n";
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(),"datefull");
            }
        }

        // ask for booking purpose
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("availablelist")){
            if(map9.get(update.getMessage().getChatId().toString()).size()==1){
                map9.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map9.get(update.getMessage().getChatId().toString()).set(1,update.getMessage().getText());
            }
            String message = "Thanks. You are almost done the booking process." +
                    "\n\nMay we have your booking purpose.\n\n" +
                    "Reply 0: If you do not wish to proceed with the booking.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"bookingpurpose");
        }

        // booking confirmation message
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("bookingpurpose")){
            if(map9.get(update.getMessage().getChatId().toString()).size()==2){
                map9.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map9.get(update.getMessage().getChatId().toString()).set(2,update.getMessage().getText());
            }
            String message = "This is your booking details: \n" +
                    "\nEmail: " + map1.get(update.getMessage().getChatId().toString()).get(0);
            String message1 =
                    "\nBooking purpose: " + map9.get(update.getMessage().getChatId().toString()).get(2) +
                            "\n\n" + "Are these booking details correct?" +
                            "\n" + "Reply 1: Yes" +
                            "\n" + "Reply 2: No, I would like to change the booking. " +
                            "\n(Remind: You would have to reselect the date to proceed.)" +
                            "\n\n" + "Reply 0: Back to menu.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message + sql.displayBookingConfirmation(map9.get(update.getMessage().getChatId().toString()).get(1)) + message1);
            testmap.put(update.getMessage().getChatId().toString(),"bookingConfirmation");
        }

        // confirm booking = yes
        else if (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("bookingConfirmation")){
            String message = "Your booking had successfully booked!" +
                    "\n\nReply 0: Back to menu.";
            sql.addBooking(
                    map1.get(update.getMessage().getChatId().toString()).get(0), // email
                    Integer.parseInt(map9.get(update.getMessage().getChatId().toString()).get(1)), // available room id
                    map9.get(update.getMessage().getChatId().toString()).get(2), // booking purpose
                    map9.get(update.getMessage().getChatId().toString()).get(0)); // booking date
            sql.bookingStatusB(Integer.parseInt(map9.get(update.getMessage().getChatId().toString()).get(1)));
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"bookingsuccess");
        }
        // end booking process

        // start 3. update booking
        else if (command.equals("3") && testmap.get(update.getMessage().getChatId().toString()).equals("loginusermenu")) {
            String updatemessage= sql.readuserbooking(map1.get(update.getMessage().getChatId().toString()).get(0));
            if ( updatemessage.equals("")) {
                String message = "There is no booking record for now." +
                        "\n\nReply 0: Back to menu.\n\n" ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "updatebooking");
            } else if (! updatemessage.equals("")) {
                String message =
                        "These are your booking record with us!\n"
                                + '\n' +
                                "Your Email: " + map1.get(update.getMessage().getChatId().toString()).get(0) + "\n"
                        ;
                String message1 =
                        "\nPlease enter the Room ID you want to update" +
                                "\n\nReply 0: Back to menu.";
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message + sql.displayAllBookingRecord(map1.get(update.getMessage().getChatId().toString()).get(0)) + message1);
                testmap.put(update.getMessage().getChatId().toString(), "updatebooking");
            }
        }

        //update the new date want to book
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("updatebooking")){
            if(map11.get(update.getMessage().getChatId().toString()).size()==0){
                map11.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map11.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            String message = "Please enter the date you want to book." +
                    "\n\n(Example: yyyy-mm-dd)" +
                    "\n\nRemind: You only can book the room within 10 days from now." +
                    "\n\nReply 0: If you do not wish to proceed with the booking.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"updatedate");
        }

        //check the date input
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("updatedate")
                ||(command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("datefull"))
        ) {
            if(map11.get(update.getMessage().getChatId().toString()).size()==1){
                map11.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map11.get(update.getMessage().getChatId().toString()).set(1,update.getMessage().getText());
            }
            sql.checkdate(map11.get(update.getMessage().getChatId().toString()).get(1));
            String bool = sql.checkdate(map11.get(update.getMessage().getChatId().toString()).get(1));
            String bool1 = sql.checkRoomStatus(map11.get(update.getMessage().getChatId().toString()).get(1));
            //check room status
            if (bool.equals("true")) {
                if (bool1.equals("true")){
                    String message = "Available Rooms on : " + map11.get(update.getMessage().getChatId().toString()).get(1) + "\n";
                    String message1 = "\n\nPlease select the slot that you want to book." + "\n" +
                            "\nPlease enter the room ID (4XXX).\n\n" +
                            "Reply 0: Back to menu.\n";
                    response.setText(message + sql.displayAvailableRoom(map11.get(update.getMessage().getChatId().toString()).get(1) ) + message1);
                    response.setChatId(update.getMessage().getChatId().toString());
                    testmap.put(update.getMessage().getChatId().toString(),"availablelist1");
                }
                else if (bool1.equals("false")){
                    String message = "The slot on " + map11.get(update.getMessage().getChatId().toString()).get(1) + " is full.\n\n" +
                            "Please another date." + "\n\n" +
                            "Reply 0: To choose another date.\n";
                    response.setChatId(update.getMessage().getChatId().toString());
                    response.setText(message);
                    testmap.put(update.getMessage().getChatId().toString(),"datefull");
                }
            } else if (bool.equals("false")) {
                String message = "The slot on " + map11.get(update.getMessage().getChatId().toString()).get(1) + " is not available to be book yet.\n\n" +
                        "Please another date." + "\n\n" +
                        "Reply 0: To choose another date.\n";
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(),"datefull");
            }
        }

        // ask for booking purpose
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("availablelist1")){
            if(map11.get(update.getMessage().getChatId().toString()).size()==2){
                map11.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map11.get(update.getMessage().getChatId().toString()).set(2,update.getMessage().getText());
            }
            String message = "Thanks. You are almost done the booking process." +
                    "\n\nMay we have your booking purpose.\n\n" +
                    "Reply 0: If you do not wish to proceed with the booking.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"updatepurpose");
        }

        // booking confirmation message
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("updatepurpose")){
            if(map11.get(update.getMessage().getChatId().toString()).size()==3){
                map11.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map11.get(update.getMessage().getChatId().toString()).set(3,update.getMessage().getText());
            }
            String message = "This is your booking details: \n" +
                    "\nEmail: " + map1.get(update.getMessage().getChatId().toString()).get(0);
            String message1 =
                    "\nBooking purpose: " + map11.get(update.getMessage().getChatId().toString()).get(3) +
                            "\n\n" + "Are these booking details correct?" +
                            "\n" + "Reply 1: Yes" +
                            "\n" + "Reply 2: No, I would like to change the booking. " +
                            "\n(Remind: You would have to reselect the date to proceed.)" +
                            "\n\n" + "Reply 0: Back to menu.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message + sql.displayBookingConfirmation(map11.get(update.getMessage().getChatId().toString()).get(2)) + message1);
            testmap.put(update.getMessage().getChatId().toString(),"updateConfirmation");
        }

        // confirm booking = yes
        else if (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("updateConfirmation")){
            String message = "Your booking had successfully booked!" +
                    "\n\nReply 0: Back to menu.";
            sql.addBooking(
                    map1.get(update.getMessage().getChatId().toString()).get(0), // email
                    Integer.parseInt(map11.get(update.getMessage().getChatId().toString()).get(2)), // available room id
                    map11.get(update.getMessage().getChatId().toString()).get(3), // booking purpose
                    map11.get(update.getMessage().getChatId().toString()).get(1)); // booking date
            sql.bookingStatusB(Integer.parseInt(map11.get(update.getMessage().getChatId().toString()).get(2)));
            sql.bookingStatusN(Integer.parseInt(map11.get(update.getMessage().getChatId().toString()).get(0)));
            sql.deletebooking(map11.get(update.getMessage().getChatId().toString()).get(0));
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"bookingsuccess");
        }
        // end update process


        //start user delete booking
        else if (command.equals("4")  && testmap.get(update.getMessage().getChatId().toString()).equals("loginusermenu")) {
            System.out.println(map1.get(update.getMessage().getChatId().toString()).get(0));
            String book= sql.readuserbooking(map1.get(update.getMessage().getChatId().toString()).get(0));
            if ( book.equals("")) {
                String message = "There is no application for now." +
                        "\n\nReply 0: Back to menu.\n\n" ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "choosedeletebooking");
            } else if (! book.equals("")) {
                String message = "Below are your bookings with us!"+ "\n";
                String message1="Please enter the room ID of the booking you want to delete.\n"
                        + '\n' +
                        "Enter 0: Back to menu \n"
                        + '\n' ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message+sql.readuserbooking(map1.get(update.getMessage().getChatId().toString()).get(0))+message1);
                testmap.put(update.getMessage().getChatId().toString(),"choosedeletebooking");
            }
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("choosedeletebooking")) {
            if (map10.get(update.getMessage().getChatId().toString()).size() == 0) {
                map10.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map10.get(update.getMessage().getChatId().toString()).set(0, update.getMessage().getText());
            }
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(sql.readuserchosendeletebooking(map10.get(update.getMessage().getChatId().toString()).get(0)));
            testmap.put(update.getMessage().getChatId().toString(),"donechoosedeletebooking");
        }
        else if (command.equals("1")  &&  testmap.get(update.getMessage().getChatId().toString()).equals("donechoosedeletebooking")) {
            String message="Please enter your password to confirm your delete action.\n"
                    + '\n' +
                    "Enter 0: Back to menu \n"
                    + '\n' ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"deletebookingpassword");
        }
        else if (update.getMessage().hasText() &&  testmap.get(update.getMessage().getChatId().toString()).equals("deletebookingpassword")) {
            if (map10.get(update.getMessage().getChatId().toString()).size() == 1) {
                map10.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map10.get(update.getMessage().getChatId().toString()).set(1, update.getMessage().getText());
            }
            sql.checkuser(map1.get(update.getMessage().getChatId().toString()).get(0), map10.get(update.getMessage().getChatId().toString()).get(1));
            String bool = sql.checkuser(map1.get(update.getMessage().getChatId().toString()).get(0), map10.get(update.getMessage().getChatId().toString()).get(1));
            if (bool.equals("true")) {
                sql.deletebooking(map10.get(update.getMessage().getChatId().toString()).get(0));
                sql.updateroomstatus(map10.get(update.getMessage().getChatId().toString()).get(0));
                String message = "Your booking is successfully deleted!\n"
                        + '\n' +
                        "Reply 0: Back to Menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "deletesuccess");
            } else if (bool.equals("false")) {
                String message = "Password Incorrect. Please try again.\n" +
                        '\n' +
                        "Reply 0 : Back to menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "deletepwwrong");
            }
        }
        //end user delete booking

        //start 5. apply admin
        else if (command.equals("5") && testmap.get(update.getMessage().getChatId().toString()).equals("loginusermenu")) {
            String message = "Here's the application for school admin.\n"
                    + '\n' +
                    "Please enter your email.\n"
                    + '\n' +
                    "Reply 0: If you do not wish to proceed with the application.\n"
                    + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "applyadminemail");
        }
        //choose the school want to apply
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("applyadminemail")) {
            if (map2.get(update.getMessage().getChatId().toString()).size() == 0) {
                map2.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map2.get(update.getMessage().getChatId().toString()).set(0, update.getMessage().getText());
            }
            String message =
                    "Please choose the school you want to apply as admin.\n"
                            + '\n' +
                            "2001: SOC\n"
                            + '\n' +
                            "2002: SMMTC\n"
                            + '\n' +
                            "2003: CAS\n"
                            + '\n' +
                            "2004: SQS\n"
                            + '\n' +
                            "2005: SLCP\n"
                            + '\n' +
                            "2006: COB\n"
                            + '\n' +
                            "Reply 0: If you do not wish to proceed with the application.\n"
                            + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "chooseschool");
        }
        //confirmation message
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("chooseschool")) {
            if (map2.get(update.getMessage().getChatId().toString()).size() == 1) {
                map2.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map2.get(update.getMessage().getChatId().toString()).set(1, update.getMessage().getText());
            }
            String message =
                    "Email: " + map2.get(update.getMessage().getChatId().toString()).get(0) + '\n'
                            + '\n' +
                            "School chosen: " + map2.get(update.getMessage().getChatId().toString()).get(1) + '\n'
                            + '\n' +
                            "Do you confirm the application? Once submitted, it cannot be modify anymore.\n"
                            + '\n' +
                            "Reply 1: Confirm \n"
                            + '\n' +
                            "Reply 0: Back to menu.\n"
                            + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "submitapplyadmin");
        }
        //application successful submit message
        else if (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("submitapplyadmin")) {
            sql.applyadmin(map2.get(update.getMessage().getChatId().toString()).get(0), map2.get(update.getMessage().getChatId().toString()).get(1), admin_status);
            String message = "Application sent successfully. \n"
                    + '\n' +
                    "We will get back to you in a short while.\n"
                    + '\n' +
                    "Reply 0: Back to menu.\n"
                    + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "doneapplyadmin");
        }
        //done 5. apply admin

        //start admin login
        else if (command.equals("2") && testmap.get(update.getMessage().getChatId().toString()).equals("mainmenu")) {
            String message = "You must login first.\n\n1. Please enter your email.\n\nReply 0: If you do not wish to proceed with the login.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "adminemail");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("adminemail")) {
            if (map3.get(update.getMessage().getChatId().toString()).size() == 0) {
                map3.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map3.get(update.getMessage().getChatId().toString()).set(0, update.getMessage().getText());
            }
            String message = "2. Please enter your password.\n\nReply 0: If you do not wish to proceed with the login.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "adminpassword");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("adminpassword")) {
            if (map3.get(update.getMessage().getChatId().toString()).size() == 1) {
                map3.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map3.get(update.getMessage().getChatId().toString()).set(1, update.getMessage().getText());
            }
            sql.checkadmin(map3.get(update.getMessage().getChatId().toString()).get(0), map3.get(update.getMessage().getChatId().toString()).get(1));
            String bool1 = sql.checkadmin(map3.get(update.getMessage().getChatId().toString()).get(0), map3.get(update.getMessage().getChatId().toString()).get(1));
            if (bool1.equals("true")) {
                String message = "Successfully Logged In!\n"
                        + '\n' +
                        "Reply 1: Continue to Menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "adminloggedin");
            } else if (bool1.equals("false")) {
                String message = "Login failed. Please try again.\n" +
                        '\n' +
                        "Reply 1: Back to menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "menu");
            }
        }
        //end admin login

        //start admin login menu
        else if ((command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("adminloggedin"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("regroomid"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("regroomdesc"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("regroommaxcap"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("regroomtype"))
                || (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("submitregroom"))
                || (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("doneregroom"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("uproomid"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("uproomdesc"))
                || (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("backadminmenu"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("uproommaxcap"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("uproomtype"))
                || (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("submituproom"))
                || (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("doneuproom"))
                || (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("userslist"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("applicationlist"))
                || (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("appadminid"))
                || (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("doneappadmin"))) {
            String message =
                    "Please choose an option to begin.\n"
                            + '\n' +
                            "1: Register New Room\n"
                            + '\n' +
                            "2: Update Room Details \n"
                            + '\n' +
                            "3: Display Booking Users List \n"
                            + '\n' +
                            "4: Display Admin Application \n"
                            + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "loginadminmenu");
        }
        //end admin login menu

        //start reg room
        else if (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("loginadminmenu")) {
            String message = "1. Please enter the room ID.\n\nReply 0: If you do not wish to proceed with the room registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "regroomid");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("regroomid")) {
            if (map4.get(update.getMessage().getChatId().toString()).size() == 0) {
                map4.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map4.get(update.getMessage().getChatId().toString()).set(0, update.getMessage().getText());
            }
            String message = "2. Please enter the room description.\n\nReply 0: If you do not wish to proceed with the room registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "regroomdesc");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("regroomdesc")) {
            if (map4.get(update.getMessage().getChatId().toString()).size() == 1) {
                map4.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map4.get(update.getMessage().getChatId().toString()).set(1, update.getMessage().getText());
            }
            String message = "3. Please enter the room maximum capacity.\n\nReply 0: If you do not wish to proceed with the room registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "regroommaxcap");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("regroommaxcap")) {
            if (map4.get(update.getMessage().getChatId().toString()).size() == 2) {
                map4.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map4.get(update.getMessage().getChatId().toString()).set(2, update.getMessage().getText());
            }
            String message = "4. Please enter the room type.\n\nReply 0: If you do not wish to proceed with the room registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "regroomtype");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("regroomtype")) {
            if (map4.get(update.getMessage().getChatId().toString()).size() == 3) {
                map4.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map4.get(update.getMessage().getChatId().toString()).set(3, update.getMessage().getText());
            }
            String message =
                    "Room ID: " + map4.get(update.getMessage().getChatId().toString()).get(0) + '\n'
                            + '\n' +
                            "Room Description: " + map4.get(update.getMessage().getChatId().toString()).get(1) + '\n'
                            + '\n' +
                            "Room Maximum Capacity: " + map4.get(update.getMessage().getChatId().toString()).get(2) + '\n'
                            + '\n' +
                            "Room Type: " + map4.get(update.getMessage().getChatId().toString()).get(3) + '\n'
                            + '\n' +
                            "Do you confirm the room details?\n"
                            + '\n' +
                            "Reply 0: Confirm \n"
                            + '\n' +
                            "Reply 1: Back to menu.\n"
                            + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "submitregroom");
        } else if (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("submitregroom")) {
            sql.regRoom(map4.get(update.getMessage().getChatId().toString()).get(0), map4.get(update.getMessage().getChatId().toString()).get(1), map4.get(update.getMessage().getChatId().toString()).get(2), map4.get(update.getMessage().getChatId().toString()).get(3));
            String message = "Room register successfully. \n"
                    + '\n' +
                    "Reply 1: Back to menu.\n"
                    + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "doneregroom");
        }
        //end reg room

        //start update room
        else if (command.equals("2") && testmap.get(update.getMessage().getChatId().toString()).equals("loginadminmenu")) {
            String message = "1. Please enter the room ID.\n\nReply 0: If you do not wish to proceed with the room update.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "uproomid");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("uproomid")) {
            if (map5.get(update.getMessage().getChatId().toString()).size() == 0) {
                map5.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map5.get(update.getMessage().getChatId().toString()).set(0, update.getMessage().getText());
            }
            sql.checkroom(map5.get(update.getMessage().getChatId().toString()).get(0));
            String bool2 = sql.checkroom(map5.get(update.getMessage().getChatId().toString()).get(0));
            if (bool2.equals("true")) {
                String message = "2. Please enter the room description.\n\nReply 0: If you do not wish to proceed with the room update.";
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "uproomdesc");
            } else if (bool2.equals("false")) {
                String message = "Room does not exist. Please try again or perform register if it is a new room.\n" +
                        '\n' +
                        "Reply 1: Back to menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "backadminmenu");
            }

        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("uproomdesc")) {
            if (map5.get(update.getMessage().getChatId().toString()).size() == 1) {
                map5.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map5.get(update.getMessage().getChatId().toString()).set(1, update.getMessage().getText());
            }
            String message = "3. Please enter the room maximum capacity.\n\nReply 0: If you do not wish to proceed with the room update.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "uproommaxcap");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("uproommaxcap")) {
            if (map5.get(update.getMessage().getChatId().toString()).size() == 2) {
                map5.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map5.get(update.getMessage().getChatId().toString()).set(2, update.getMessage().getText());
            }
            String message = "4. Please enter the room type.\n\nReply 0: If you do not wish to proceed with the room update.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "uproomtype");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("uproomtype")) {
            if (map5.get(update.getMessage().getChatId().toString()).size() == 3) {
                map5.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map5.get(update.getMessage().getChatId().toString()).set(3, update.getMessage().getText());
            }
            String message =
                    "Room ID: " + map5.get(update.getMessage().getChatId().toString()).get(0) + '\n'
                            + '\n' +
                            "Room Description: " + map5.get(update.getMessage().getChatId().toString()).get(1) + '\n'
                            + '\n' +
                            "Room Maximum Capacity: " + map5.get(update.getMessage().getChatId().toString()).get(2) + '\n'
                            + '\n' +
                            "Room Type: " + map5.get(update.getMessage().getChatId().toString()).get(3) + '\n'
                            + '\n' +
                            "Do you confirm the room details?\n"
                            + '\n' +
                            "Reply 0: Confirm \n"
                            + '\n' +
                            "Reply 1: Back to menu.\n"
                            + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "submituproom");
        } else if (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("submituproom")) {
            sql.upRoom(map5.get(update.getMessage().getChatId().toString()).get(0), map5.get(update.getMessage().getChatId().toString()).get(1), map5.get(update.getMessage().getChatId().toString()).get(2), map5.get(update.getMessage().getChatId().toString()).get(3));
            String message = "Room updated successfully. \n"
                    + '\n' +
                    "Reply 1: Back to menu.\n"
                    + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "doneuproom");
        } //end update room

        //start display users and booked room list
        else if (command.equals("3") && testmap.get(update.getMessage().getChatId().toString()).equals("loginadminmenu")) {
            if (sql.displayUserslist().equals("")) {
                String message = "There is no user available." +
                        "\n\nReply 0: Back to menu. \n\n" ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "userslist");
            } else if (!sql.dispApplication().equals("")) {
                String message = "Here is the list of users available.\n" +
                        sql.displayUserslist() +
                        "\n\nReply 0: Back to menu. \n\n";
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "usersList");
            }
        } //end display list

        //start display application
        else if (command.equals("4") && testmap.get(update.getMessage().getChatId().toString()).equals("loginadminmenu")) {
            if (sql.dispApplication().equals("")) {
                String message = "There is no application for now." +
                        "\n\nReply 0: Back to menu.\n\n" ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "applicationlist");
            } else if (!sql.dispApplication().equals("")) {
                String message = "Here is the list of application for school admin.\n" +
                        sql.dispApplication() +
                        "Reply 1: If you would like to approve admin." +
                        "\n\nReply 0: If you do not wish to proceed with the approval for application.\n\n";
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "applicationlist");
            }
        } else if (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("applicationlist")) {
            String message = "Please enter the admin ID of the admin that you would like to approve. \n"
                    + '\n' +
                    "Reply 0: If you do not wish to proceed with the approval for application.\n"
                    + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "appadminid");
        } else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("appadminid")) {
            if (map7.get(update.getMessage().getChatId().toString()).size() == 0) {
                map7.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            } else {
                map7.get(update.getMessage().getChatId().toString()).set(0, update.getMessage().getText());
            }
            sql.approveAdmin(map7.get(update.getMessage().getChatId().toString()).get(0));
            String message = "Approved successfully. \n"
                    + '\n' +
                    "Reply 1: Back to menu.\n"
                    + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "doneappadmin");
        }//end approve admin


        try {
            execute(response);
        } catch (TelegramApiException E) {
            E.printStackTrace();
        }
    }

    /**
     * This method is to retrieve the username of the telegram bot
     **/
    @Override
    public String getBotUsername() {
        // TODO
        return "STIW3054_koko_bot";
    }

    /**
     * This method is to retrieve the api token of the telegram bot
     **/
    @Override
    public String getBotToken() {
        // TODO
        return "5968085786:AAFbSugQxoLAvYnyvI5PL94VZt4AwwBWP-A";
    }
}