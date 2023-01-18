package my.uum;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * This class is for running the telegram bot
 * @author koko
 **/

public class App {
    /**
     * This method is to run the bot while creating a thread that is able to delete unneeded data task
     **/
    public static void main(String[] args) {
        // Create new Daemon Thread to check 12:01 a.m. daily to run delete data task
        MyThread deleteDataThread = new MyThread();
        deleteDataThread.setDaemon(true);
        deleteDataThread.start();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
