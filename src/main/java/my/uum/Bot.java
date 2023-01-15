package my.uum;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    SQLite sql= new SQLite();
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    Map<String, ArrayList<String>> map1 = new HashMap<String, ArrayList<String>>();
    Map<String, ArrayList<String>> map2 = new HashMap<String, ArrayList<String>>();
    Map<String, String> testmap = new HashMap<String, String>();
    String admin_status="Pending approval";

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
        System.out.println(update.getMessage().getFrom().getFirstName());
        String command = update.getMessage().getText();
        System.out.println(testmap.get(update.getMessage().getChatId().toString()));
        SendMessage response = new SendMessage();

        if(!map.containsKey(update.getMessage().getChatId().toString())){
            map.put(update.getMessage().getChatId().toString(),new ArrayList<String>());
        }
        if(!map1.containsKey(update.getMessage().getChatId().toString())){
            map1.put(update.getMessage().getChatId().toString(),new ArrayList<String>());
        }
        if(!map2.containsKey(update.getMessage().getChatId().toString())){
            map2.put(update.getMessage().getChatId().toString(),new ArrayList<String>());
        }

        //main menu after enter bot
        if (command.equals("/start")) {
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
                    + '\n'
                    ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"mainmenu");
        }
        //end main menu after enter bot

        //user menu after they choose user not admin
        else if ((command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("mainmenu"))
                ||(command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("rusericno"))
                ||(command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("rstaffid"))
                ||(command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("rusername"))
                ||(command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("ruserphone"))
                ||(command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("ruseremail"))
                ||(command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("ruserpassword"))
        ) {
            String message = "Welcome to UUM Room Booking Bot. \n"
                    + '\n' +
                    "Please choose an option to begin as an user.\n"
                    + '\n' +
                    "1: Register if you are first time user \n"
                    + '\n' +
                    "2: Log in if you already have an account.  \n"
                    + '\n'
                    ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"usermenu");
        }
        //end user menu after they choose user not admin

        //start register as new user
        else if (command.equals("1") && testmap.get(update.getMessage().getChatId().toString()).equals("usermenu")) {
            String message = "1. First. Please enter your IC No.\n\n(Example: 000619126688)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"rusericno");
        }

        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("rusericno")) {
            if(map.get(update.getMessage().getChatId().toString()).size()==0){
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            String message = "2. Please enter your Staff ID.\n\n(Example: 278788)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"rstaffid");
        }
        else if (update.getMessage().hasText() &&testmap.get(update.getMessage().getChatId().toString()).equals("rstaffid")) {
            if(map.get(update.getMessage().getChatId().toString()).size()==1){
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map.get(update.getMessage().getChatId().toString()).set(1,update.getMessage().getText());
            }
            String message = "3. Please enter your full name.\n\n(Example: Max Tin)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"rusername");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("rusername")) {
            if(map.get(update.getMessage().getChatId().toString()).size()==2){
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map.get(update.getMessage().getChatId().toString()).set(2,update.getMessage().getText());
            }
            String message = "4. Almost there! Please enter your phone no.\n\n(Example: 01251106688)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"ruserphone");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("ruserphone")) {
            if(map.get(update.getMessage().getChatId().toString()).size()==3){
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map.get(update.getMessage().getChatId().toString()).set(3,update.getMessage().getText());
            }
            String message = "5. Please enter your email.\n\n(Example: max@gmail.com)\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"ruseremail");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("ruseremail")) {
            if(map.get(update.getMessage().getChatId().toString()).size()==4){
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map.get(update.getMessage().getChatId().toString()).set(4,update.getMessage().getText());
            }
            String message = "6. Please enter your password.\n\nReply 0: If you do not wish to proceed with the registration.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"ruserpassword");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("ruserpassword")) {
            if(map.get(update.getMessage().getChatId().toString()).size()==5){
                map.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map.get(update.getMessage().getChatId().toString()).set(5,update.getMessage().getText());
            }
            sql.insert(map.get(update.getMessage().getChatId().toString()).get(0), map.get(update.getMessage().getChatId().toString()).get(1), map.get(update.getMessage().getChatId().toString()).get(2), map.get(update.getMessage().getChatId().toString()).get(3), map.get(update.getMessage().getChatId().toString()).get(4), map.get(update.getMessage().getChatId().toString()).get(5));
            String message = "Registration Successful. Below are your details. \n"
                    + '\n' +
                    "IC No: " + map.get(update.getMessage().getChatId().toString()).get(0)+ '\n'
                    + '\n' +
                    "Staff ID: "+ map.get(update.getMessage().getChatId().toString()).get(1)+'\n'
                    + '\n' +
                    "Name: "+ map.get(update.getMessage().getChatId().toString()).get(2)+'\n'
                    + '\n' +
                    "Phone: "+ map.get(update.getMessage().getChatId().toString()).get(3)+'\n'
                    + '\n' +
                    "Email: "+ map.get(update.getMessage().getChatId().toString()).get(4)+'\n'
                    + '\n' +
                    "You may log in to proceed with booking. \n"
                    + '\n' +
                    "Reply 1: Back to menu.\n"
                    + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"mainmenu");
        }
        //end register user

        // start user log in
        else if (command.equals("2") && testmap.get(update.getMessage().getChatId().toString()).equals("usermenu")) {
            String message = "1. Please enter your email.\n\nReply 0: If you do not wish to proceed with the login.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"loginuseremail");
        }

        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("loginuseremail")) {
            if(map1.get(update.getMessage().getChatId().toString()).size()==0){
                map1.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map1.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            String message = "2. Please enter your password.\n\nReply 0: If you do not wish to proceed with the login.";
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"loginuserpassword");
        }
        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("loginuserpassword") ) {
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
                        + '\n'
                        ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(), "loggedin");
            }
            else if (bool.equals("false")) {
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
                ||(command.equals("1")  && testmap.get(update.getMessage().getChatId().toString()).equals("doneapplyadmin"))
                ||(command.equals("1")  && testmap.get(update.getMessage().getChatId().toString()).equals("submitapplyadmin"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("applyadminemail"))
                ||(command.equals("0")  && testmap.get(update.getMessage().getChatId().toString()).equals("chooseschool"))) {
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
                    + '\n'
                    ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(), "loginusermenu");
        }
        //end user logged in main menu

        //start 5. apply admin
        else if (command.equals("5")  && testmap.get(update.getMessage().getChatId().toString()).equals("loginusermenu")) {
                String message = "Here's the application for school admin.\n"
                        + '\n' +
                        "Please enter your email.\n"
                        + '\n' +
                        "Reply 0: If you do not wish to proceed with the application.\n"
                        + '\n'
                        ;
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(),"applyadminemail");
        }
        else if (update.getMessage().hasText()  && testmap.get(update.getMessage().getChatId().toString()).equals("applyadminemail")) {
            if(map2.get(update.getMessage().getChatId().toString()).size()==0){
                map2.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map2.get(update.getMessage().getChatId().toString()).set(0,update.getMessage().getText());
            }
            String message =
                    "Please choose the school you want to apply as admin.\n"
                    + '\n' +
                    "2001: SOIS\n"
                    + '\n' +
                    "2002: SMMTC\n"
                    + '\n' +
                   "Reply 0: If you do not wish to proceed with the application.\n"
                     + '\n'
                    ;
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"chooseschool");
        }

        else if (update.getMessage().hasText() && testmap.get(update.getMessage().getChatId().toString()).equals("chooseschool")) {
            if(map2.get(update.getMessage().getChatId().toString()).size()==1){
                map2.get(update.getMessage().getChatId().toString()).add(update.getMessage().getText());
            }else{
                map2.get(update.getMessage().getChatId().toString()).set(1,update.getMessage().getText());
            }
            String message =
                    "Email: "+ map2.get(update.getMessage().getChatId().toString()).get(0)+'\n'
                    + '\n' +
                    "School chosen: "+ map2.get(update.getMessage().getChatId().toString()).get(1)+'\n'
                    + '\n' +
                    "Do you confirm the application? Once submitted, it cannot be modify anymore.\n"
                    + '\n' +
                    "Reply 0: Confirm \n"
                    + '\n' +
                    "Reply 1: Back to menu.\n"
                    + '\n';
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);
            testmap.put(update.getMessage().getChatId().toString(),"submitapplyadmin");
        }
        else if (command.equals("0") && testmap.get(update.getMessage().getChatId().toString()).equals("submitapplyadmin")) {
                sql.applyadmin(map2.get(update.getMessage().getChatId().toString()).get(0),map2.get(update.getMessage().getChatId().toString()).get(1),admin_status);
                String message = "Application sent successfully. \n"
                        + '\n' +
                        "We will get back to you in a short while.\n"
                        + '\n' +
                        "Reply 1: Back to menu.\n"
                        + '\n';
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);
                testmap.put(update.getMessage().getChatId().toString(),"doneapplyadmin");
        }
        //done 5. apply admin

        try {
            execute(response);
        } catch (TelegramApiException E) {
            E.printStackTrace();
        }
        }


    @Override
    public String getBotUsername() {
        // TODO
        return "STIW3054_koko_bot";
    }


    @Override
    public String getBotToken() {
        // TODO
        return "5968085786:AAFbSugQxoLAvYnyvI5PL94VZt4AwwBWP-A";
    }
}