package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TelegramBotSender {

    private Logger logger = LoggerFactory.getLogger(TelegramBotSender.class);
    private final TelegramBot telegramBot;

    public TelegramBotSender(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void send (Long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        SendResponse response = telegramBot.execute(sendMessage);

        if (response.isOk()) {
            logger.info("Successfully sent message {}", message);
        } else {
            logger.error("Error during sending message: {}", response.errorCode());
        }
    }

}
