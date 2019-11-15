import org.glassfish.grizzly.http.server.io.ServerOutputBuffer;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class GoddardBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

        //System.out.println(update.getMessage().getFrom().getFirstName() );
        //System.out.println(update.getMessage().getText());

        String command=update.getMessage().getText();
        SendMessage message = new SendMessage();

        System.out.println("Command: " + command);

        if(command.equals("/myname")){
            System.out.println(update.getMessage().getFrom().getFirstName() );

            message.setText(update.getMessage().getFrom().getFirstName());
        }

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
