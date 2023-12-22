package org.example;
/**
 * The class for create a Telegram bot in Java
 *
 * @author Abdulrahman Khalif Hashi Nur
 */

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;



public class s274922_bot {

    public static void main(String[] args) {



        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new simple_bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}
