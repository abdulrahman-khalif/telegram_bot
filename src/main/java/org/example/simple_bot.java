package org.example;
/**
 * this class for get message and responded to user.
 *
 * @auther Abdulrahman Khalif Hashi Nur
 */

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.sql.*;

public class simple_bot extends TelegramLongPollingBot {

    private String previousMessage = "";
    StringBuilder response = new StringBuilder();
    StringBuilder response2 = new StringBuilder();
    DatabaseSqlite get_data = new DatabaseSqlite();
    String name = null;
    boolean check = false;

    /**
     *  receives a new update from Telegram. It can be used to handle messages, commands, and other events.
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {

        String getMessage = update.getMessage().getText();
        SendMessage re = new SendMessage();
        re.setChatId(update.getMessage().getChatId().toString());


        try {


           response =  get_data.Display_LectureName(getMessage);
           name = get_data.getName();


            //check if name exit in database?
            if (name != (null)){
                previousMessage = name;
                check = true;

                re.setText("Are you searching for Dr." +name+ "? \n 1. Yes \n " + "2. No");

            }else{
                re.setText("Invalid Input!! " +
                        "\n There is no name such as "+getMessage+" in our data." +
                        "\n Note: Please Enter First Name of the lecture.");
            }

            if((getMessage.equals("1")) && check == true ) {

                response = get_data.displayNearbay(previousMessage);
                response2 = get_data.Display_LectureName(previousMessage);
                re.setText(response2.toString()+"   \n Nearby Lecturers: \n \n "+response.toString());
                check = false;

            }else if (getMessage.equals("2")){
                re.setText("Sorry For that.\n Please try to Enter the First Name of the lecture again.");
            }

            if(getMessage.equals("/start")){
                re.setText("Welcome to our Telegram Bot. " +
                        "Our Telegram Bot provides a searchable list of SOC lecturers." +
                        " Information such as the lecturer's room number and the nearest five lecturers to the required lecturer's room.");

            }


            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }


        //  end
            try {
                execute(re);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }

}


    /**
     * this method to get my bot username.
     *
     * @return return my bot username.
     */
    @Override
    public String getBotUsername() {
        return "s274922_bot";
    }

    /**
     * this method for get my bot Token
     *
     * @return my telegram Token
     */
    @Override
    public String getBotToken() {
        // TODO
        return "5991230688:AAHcjEOOkkHtqc9Kcu7AlH7NFOdne86I6W4";
    }

}
