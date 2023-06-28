## Requirements for Group Project
[Read the instruction](https://github.com/STIW3054-A221/class-activity-soc/blob/main/GroupProject.md)

## Group Info:

| No. | Photo                                       | Name                  | Matric Number | Semester | Phone Number |
| ------------- |---------------------------------------------|-----------------------|---------------|----------|--------------|
|1.| <img src= "images/siew_chin.jpg" width=100> | Loh Siew Chin         | 278723        | 5        | 017-4918235  |
|2.| <img src= "images/zhi_yi.jpg" width=100>    | Teoh Zhi Yi           | 278645        | 5        | 018-2014036  |
|3.| <img src= "images/shi_koon.jpg" width=100>  | Ooi Shi Koon          | 277290        | 5        | 017-5774491  |
|4.| <img src= "images/fanny.jpg" width=100>     | Fanny Tang Tung Huong | 278366        | 5        | 011-51103470 |
|5.| <img src= "images/zhun_wei.JPG" width=100>  | Lim Zhun Wei          | 279632        | 5        | 014-7461218  |

## Title of your application

<img alt="gif" src="https://github.com/STIW3054-A221/group-project-koko/blob/master/gif/mochi-peach.gif" width="100" height="100"/>  Koko Room Booking System  <img alt="gif" src="https://github.com/STIW3054-A221/group-project-koko/blob/master/gif/mochi-peach.gif" width="100" height="100"/>
<br>

## Introduction

Nowadays, people are demanding the simplification of tasks due to technological advances. People prefer to complete
tasks online, such as online shopping or online booking. Therefore, several Telegram bots are born to fulfill people's
demands. Telegram bots are a program that can be embedded in Telegram chats or public channels to perform specific
tasks. They can provide personalised keyboards, create memes on demand, accept payments and serve as a digital
storefront.

The Telegram bot developed in this project is separated into two parts which are user part and admin part. This telegram
bot is built purposely to let users book the meeting room anytime and wherever
they are. Besides, the admin can also manage the room and approve new admin by using this Telegram bot.

For the user part, the user must login to the account first to start booking a room. If the user does not have n
account, the user can register an account through the bot. After login, the bot will show several options, such as "
Update Personal Info", "Book a Room", "Update Booking", "Delete Booking" and "Apply School Admin". The user can choose
the option they want by replying to the bot, and the bot will continue to let the user key in some details needed for
the process.

For the admin part, the admin must login first only to continue to access the admin menu. The bot will show several menu
option such as "Register New Room", "Update Room Details", "3: Display Booking Users List" and "Display Admin
Application" after the admin successfully login. The admin can approve the school admin application and manage rooms by
replying to the bot with some details needed through this bot. This bot can make the room booking process more
effective.

## Flow Diagram of the requirements

![Flow Diagram](https://user-images.githubusercontent.com/73092542/202361668-882423f7-1603-4687-be9a-8e40154ee2d8.jpg)

## User manual for installing your application on Heroku Server

## User manual/guideline for testing the system
User

1. Register 

User may click "/start" from command keyboard or type "/start" to start the bot. User reply "1" to enter the user menu. 
User reply "1" again to register as a first time user. User may enter IC No, staff ID, full name, phone no, email and 
password to complete the registration. Along the process, user may reply "0" back to main menu. If the email already 
exist, the registration will be failed.

2. Login

After start the bot, user may reply "1" to user menu. User may reply "2" to log in. User need to enter valid email and 
password in order to log in successfully. If the login credential is wrong, user need to reenter until correct email and 
password is provided in order to log in. Along the process, user may reply "0" back to main menu.

3. Update Personal Info

After log in, user may reply "1" and enter the numbering of user's information they want to edit. User need to enter the 
new information and the user information will be updated successfully and the bot will send the latest info of user. 
Along the process, user may reply "0" back to main menu.

4. Book New Room

After log in, user may reply "2" to perform book room action. User need to enter the date they wish to have booking. 
User are only allow to book room within 10 days so if user enter date exceed 10 days duration, the bot will inform user
that the date is not available yet. After user input valid date, the bot will list the slot available with room ID, 
room type, booking date and time on the date input by user. User may reply room ID for the slot that they desired. 
Then, user need to input their booking purpose and reply "1" to confirm their booking. Then, user booking is confirmed. 
Along the process, user may reply "0" back to main menu.

5. Update Room

After log in, user may reply "3" to proceed the update room booking details action. The system will display all the
booking records with all the details that the user had booked before exclude the past date. The user can review and
check which booking that they want to update by entering the room ID. Then the system will let the user enters another
date to check is the date available to be booked. If the date enter exceed 10 days duration or there are no any free 
slots on the date, the bot will inform the user the date is not available yet. After user input valid date, the bot will
list the slot available with room ID, room type, booking date and time on the date input by user. User may reply room ID
for the slot that they desired. Then, user need to input their booking purpose and reply "1" to confirm their booking
update. Then, user booking is updated. Along the process, user may reply "0" back to main menu.

6. Delete Room

After log in, user may reply "4" to proceed delete room booking action. Same with the update action, the system will
display all the booking records with all the details that the user had booked before exclude the past date. The user
can review and check which booking that they want to delete by entering the room ID. Then, the bot will display the
booking record details to alert the user checks is it the booking record that the user want to delete. The user may
reply "1" to confirm or "0" back to menu. The bot will require the user to enter their password to double confirm the
deletion action. Then, user booking is deleted successful. Along the process, user may reply "0" back to main menu.

7. Apply Admin

After log in, user may reply "5" to proceed delete room booking action. First, the bot require the user to enter their
email to apply as the school admin and also the school ID that they want to apply. Then the bot will display the 
application to let the user check. If the details are correct, the user may reply "1" to confirm the application. Then,
the application process is sent successful, will get back to the user to process the admin approval. Along the process,
user may reply "0" back to main menu.


Admin

1. Login

When the bot is started, the admin needs to reply 2 to the bot and login to access the admin menu by replying to the bot
with the email and password. If either one email or password is entered wrongly, then the bot will send a message which
shows "Login failed" and the admin needs to reply 1 to back to the main menu, then only can proceed with the login
process again. When the admin is proceeding with the login process and wishes to exit it, they can reply 0 to the bot
and go back to the main menu. After successful login, the bot will send a message showing the menu options to the admin
to choose.

2. Register New Room

Admin can reply 1 to the bot to choose to proceed with registering the new room. After entering the registration process
of new room, the admin needs to key in the room details asked from the bot, such as room ID, room description, room type
and room maximum capacity. Before the registration process is done, the bot will send a message which shows the details
that the admin entered in the process to ask for confirmation from the admin. If the admin confirms the details, the
admin can reply 0 to the bot, and the bot will send a message showing the room has been registered successfully. If the
admin realizes that the details are wrongly entered, the admin can reply 1 to the bot and go back to the menu to reenter
the process of registering a new room. Same as the login process, if the admin wants to exit the registration process,
the admin can reply 0 to the bot anytime to exit this process.

3. Update Room Details

If the admin would like to update room details, the admin can reply 2 to the bot after successful login. Then the bot
will ask for the room ID from the admin that the admin wants to update the details. If the room Id does not exist in the
database, the bot will send a message showing "Room does not exist. Please try again or perform register if it is a new
room." and the admin needs to reply 1 to the bot to go back to the menu to reenter the process of updating room details.
Besides, if the room ID entered by the admin exists in the database, the bot will continue to send messages asking for
the room details that the admin wants to update. The admin can just enter the new details of that room by following the
instruction. Same as the process of registering a new room, before the updating process is done, the bot will send a
message which shows the room details that the admin entered just and ask for confirmation from the admin. The admin can
just reply 0 to confirm the details, and then the bot will send a message to show that the room details have been
updated successfully. If the admin realizes that the details are wrongly entered, the admin can reply 1 to the bot and
go back to the menu to reenter the process of updating the room details. Moreover, if the admin would like to exit the
updating process, the admin can reply 0 to the bot anytime to exit the process and go back to the menu.

4. Display Booking Users List

To display users and booked room, the admin  should reply 3 after successfully login into the admin section. The bot will then display the user email, booking information (eg. booking purpose, booking date, and booking time), and information about the booked rom (eg. room ID, room description, room maximum capacity, and room type). The admin will be able to view any rooms that are booked at the moment. To return to admin menu list, the admin should reply 0.

5. Display Admin Application

The admin can reply 4 to view admin applications submitted by the users after login into the admin section. The screen will then display the user's email, staff ID, name, IC number, phone number, school ID, admin ID and the status whether the application has been approved or not. To cancel the approval process, the admin can enter 0 to exit the process. To proceed with approving a certain user as an admin, the admin should then reply 1 and enter the admin ID of the user to approve its application. After the admin replying the admin ID, the admin will then be asked to redirect back to the admin menu list by replying 1.

## Result/Output (Screenshot of the output)

User

1. Register

   <img src= "images/output/u1_register_01.png" height=200>
   <img src= "images/output/u1_register_02.png" height=100% width=400>
   <img src= "images/output/u1_register_03.png" height=100% width=360>
   <img src= "images/output/u1_register_04.png" height=300 width=400>

2. Login

   <img src= "images/output/u1_login_01.png" height=200>
   <img src= "images/output/u1_login_02.png" height=200>

3. Update Personal Info

   <img src= "images/output/u2_updateU_01.png" height=200>
   <img src= "images/output/u2_updateU_02.png" height=200>
   <img src= "images/output/u2_updateU_03.png" height=100% width=410>
   <img src= "images/output/u2_updateU_04.png" height=200>

4. Booking

   <img src= "images/output/u3_booking_01.png" height=200 width=380>
   <img src= "images/output/u3_booking_02.png" height=200 width=380>
   <img src= "images/output/u3_booking_03.png" height=100% width=380>
   <img src= "images/output/u3_booking_04.png" height=100% width=380>
   <img src= "images/output/u3_booking_05.png" height=160 width=760>

5. Update Booking

   <img src= "images/output/u4_updateB_01.jpg" height=350>
   <img src= "images/output/u4_updateB_02.jpg" height=350>
   <img src= "images/output/u4_updateB_03.jpg" height=350>
   <img src= "images/output/u4_updateB_04.jpg" height=350>

   If the date enter is not available to book yet or the time slot is full on the date.

   <img src= "images/output/u4_updateB_05.jpg" height=350>
   
6. Delete Booking

   <img src= "images/output/u5_deleteB_01.jpg" height=350>
   <img src= "images/output/u5_deleteB_02.jpg" height=350>
   <img src= "images/output/u5_deleteB_03.jpg" height=350>
   
7. Apply Admin

   <img src= "images/output/u6_apply_admin_01.jpg" height=350>
   <img src= "images/output/u6_apply_admin_02.jpg" height=350>
   <img src= "images/output/u6_apply_admin_03.jpg" height=350>


Admin
1. Login

   <img src= "images/output/a_login_01.png" width=350>
   <img src= "images/output/a_login_02.png" width=350>
   <img src= "images/output/a_login_03.png" width=350>
   <img src= "images/output/a_login_04.png" width=350>

2. Register room

   <img src= "images/output/a1_registerR_01.png" width=350>
   <img src= "images/output/a1_registerR_02.png" width=350>
   <img src= "images/output/a1_registerR_03.png" width=350>
   <img src= "images/output/a1_registerR_04.png" width=350>
   
3. Update room

   <img src= "images/output/a2_updateR_01.png" width=350>
   <img src= "images/output/a2_updateR_02.png" width=350>
   <img src= "images/output/a2_updateR_03.png" width=350>
   <img src= "images/output/a2_updateR_04.png" width=350>
   <img src= "images/output/a2_updateR_05.png" width=350>
   <img src= "images/output/a2_updateR_06.png" width=350>
   
4. Display booking user list

   <img src= "images/output/a3_displayUser_01.png" width=350>
   <img src= "images/output/a3_displayUser_02.png" width=350>

5. Approve admin

   <img src= "images/output/a4_approveAdmin_01.png" width=350>
   <img src= "images/output/a4_approveAdmin_02.png" width=350>

## Use Case Diagram

![Use Case Diagram](https://user-images.githubusercontent.com/73092542/202361715-e4204a2e-b55e-4bb0-8f6b-82d43273d936.jpg)

## UML Class Diagram

![img.png](images/uml.png)

## Database Design

<img src= "images/database_design.png">

## Youtube Presentation
<p><img alt="gif" src="https://github.com/STIW3054-A221/group-project-koko/blob/master/gif/youtube-logo.gif" width="100" height="70"/> https://youtu.be/oqIq5wxL1kY </p>
<br>

## References (Not less than 20)

1. B., R. (2022, December 14). _What is MySQL: MySQL Explained for Beginners._
   Hostinger. https://www.hostinger.com/tutorials/what-is-mysql
2. Carnes, B. (2020, January 1). _Basic SQL Commands - The List of Database Queries and Statements You Should Know._
   freeCodeCamp. https://www.freecodecamp.org/news/basic-sql-commands/
3. _Connect To The SQLite Database Using SQLite JDBC Driver._ (2021, April 10). SQLite
   Tutorial. https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
4. _HashMap in Java - javatpoint._ (n.d.). Javatpoint. https://www.javatpoint.com/java-hashmap
5. _HashMap Java Tutorial._ (2019, May 23). YouTube. https://www.youtube.com/watch?v=70qy6_gw1Hc
6. _How to create Telegram Bot in Java._ (2021, November 11). YouTube. https://www.youtube.com/watch?v=XjOnp8TVNSQ
7. _Java HashMap._ (n.d.). W3Schools. https://www.w3schools.com/java/java_hashmap.asp
8. _Java HashMap (With Examples)._ (n.d.). Programiz. https://www.programiz.com/java-programming/hashmap
9. _Java Program to Retrieve Contents of a Table Using JDBC connection._ (2022, April 25).
   GeeksforGeeks. https://www.geeksforgeeks.org/java-program-to-retrieve-contents-of-a-table-using-jdbc-connection/
10. _Java SQLite Example - javatpoint. (n.d.). Javatpoint._ https://www.javatpoint.com/java-sqlite
11. Lenka, C. (2022, September 6). _HashMap in Java with Examples._
    GeeksforGeeks. https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/
12. _Map and HashMap in Java - Full Tutorial._ (2021, June 14). YouTube. https://www.youtube.com/watch?v=H62Jfv1DJlU
13. Newman, L. H. (2019, January 17). _Be Careful Using Bots on Telegram._
    WIRED. https://www.wired.com/story/telegram-bots-tls-encryption/
14. Sidborn, J., Wiberg, D., Öhman, J., Öhman, & P. (2020). Room Booking System - Online Room Booking
    Software. https://www.roombookingsystem.co.uk/
15. _SQLite INNER JOIN with Examples._ (n.d.). SQLite Tutorial. https://www.sqlitetutorial.net/sqlite-inner-join/
16. _SQLite - Java._ (n.d.). Tutorialspoint. https://www.tutorialspoint.com/sqlite/sqlite_java.htm
17. _SQLite Java: Select Data._ (n.d.). SQLite Tutorial. https://www.sqlitetutorial.net/sqlite-java/select/
18. _Telegram Bot - Process response from user based on last bot question._ (2019, December 15). Stack
    Overflow. https://stackoverflow.com/questions/59341135/telegram-bot-process-response-from-user-based-on-last-bot-question
19. Vaghela V. (2020, December 21). _How to create a Telegram bot using java?_ Viral Vaghela. Retrieved January 18,
    2023, from https://vaghelaviral.medium.com/how-to-create-a-telegram-bot-using-java-5710bed16c0f
20. _What Is a Database._ (n.d.). Oracle. https://www.oracle.com/my/database/what-is-database/

## JavaDoc

https://zhiyi16510.github.io/JavaDoc%20(Grp%20Proj)/index.html

<br><br>
<p align="center"><img align="center" alt="gif" src="https://github.com/STIW3054-A221/group-project-koko/blob/master/gif/pompom-pompomfriends.gif" width="200" height="200"/></p>
