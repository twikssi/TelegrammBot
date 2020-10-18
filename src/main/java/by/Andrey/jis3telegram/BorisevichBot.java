package by.Andrey.jis3telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BorisevichBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "BorisevichBot";
    }

    @Override
    public String getBotToken() {
        return "1332611656:AAEoQL_vpni2ACJxJ0_3_kEx_2gGio4kukE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();
            if(message.hasText()){
                String text = message.getText().toLowerCase();
                if (text.equals("hello")){
                    String response = "Hello you 2, " + message.getFrom().getFirstName();
                    sendMesg(message, response);
                }else if (text.equals("bye")){
                    String response = "Oh, man, bye, " + message.getFrom().getFirstName();
                    sendMesg(message, response);
                }else if (text.contains("street")){
                    String response = "Where are you going?";
                    sendMesg(message, response);
                }
            }
        }
    }

    public void  sendMesg(Message message, String response){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(response);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
