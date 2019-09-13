package me.piyush.itspiyushbot;

import me.piyush.itspiyushbot.lib.ItsPiyushBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        try {
            ApiContextInitializer.init();

            TelegramBotsApi botAPI = new TelegramBotsApi();

            try {
                botAPI.registerBot(new ItsPiyushBot());
            } catch(TelegramApiException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
