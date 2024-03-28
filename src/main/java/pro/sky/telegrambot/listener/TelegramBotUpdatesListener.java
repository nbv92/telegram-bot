package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.service.TelegramBotSender;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    private final TelegramBotSender telegramBotSender;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, TelegramBotSender telegramBotSender) {
        this.telegramBot = telegramBot;
        this.telegramBotSender = telegramBotSender;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String message = update.message().text();
            Long chatid = update.message().chat().id();
            if (message.equals(" /start")) {
                logger.info("Получили сообщение: " + message);
                //hi
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
