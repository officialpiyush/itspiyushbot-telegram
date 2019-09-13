package me.piyush.itspiyushbot.lib;

import kong.unirest.Unirest;
import me.piyush.itspiyushbot.util.env;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class ItsPiyushBot extends TelegramLongPollingBot {

    private final env _env;

    public ItsPiyushBot() {
        this._env = new env();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if(message.contains("/say")) {
                SendMessage msg = new SendMessage()
                        .setChatId(chat_id)
                        .setText(message.replace("/say ", ""));

                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if(message.equals("/meme")) {
                String joke = Unirest.get("https://icanhazdadjoke.com/")
                        .accept("application/json")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getString("joke");

                try {
                    execute(new SendMessage().setChatId(chat_id).setText(joke));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if(message.equals("/start")) {
                SendMessage msg = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Here are my commands:");

                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();

                row.add("/meme");

                keyboard.add(row);
                keyboardMarkup.setKeyboard(keyboard);

                msg.setReplyMarkup(keyboardMarkup);

                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this._env.get("BOT_USERNAME");
    }

    @Override
    public String getBotToken() {
        return this._env.get("BOT_TOKEN");
    }
}
