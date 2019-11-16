import org.glassfish.grizzly.http.server.io.ServerOutputBuffer;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class GoddardBot extends TelegramLongPollingBot {
    private Manager manager = null;

    public void onUpdateReceived(Update update) {
        // Receiving commands
        String command=update.getMessage().getText();
        SendMessage message = new SendMessage();

        //TODO: Call manager method according to received command
        manager = manager.getInstance();
        message.setChatId(update.getMessage().getChatId());

        try{
            execute(message);
        } catch (TelegramApiException e){
            System.out.println("No message were returned");
        }

    }

    public String getBotUsername() {
        return "goddard_bot";
    }

    public String getBotToken() {
        return "958287749:AAEyd8S7Y7u6xvYYjyFp9FnC2Da5R1k13RY";
    }
}
