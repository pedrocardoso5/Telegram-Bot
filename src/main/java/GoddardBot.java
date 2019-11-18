import org.glassfish.grizzly.http.server.io.ServerOutputBuffer;
import java.lang.*;
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

        // commands switcher
        manager = manager.getInstance();
        System.out.println("Command: " + command);

        if (command.equals("/help")) {
            message.setText(manager.printHelp());
        }
        else if (command.equals("/start")) {
            message.setText(manager.printWelcome());
        }
        else if (command.equals("/registerLocation")) {
            message.setText("Error: command not available yet");
        }
        else if (command.equals("/registerCategory")) {
            message.setText("Error: command not available yet");
        }
        else if (command.equals("/registerItem")) {
            message.setText("Error: command not available yet");
        }
        else if (command.equals("/listLocations")) {
            message.setText(manager.listLocations());
        }
        else if (command.equals("/listCategories")) {
            message.setText(manager.listCategories());
        }
        else if (command.equals("/listItems")) {
            message.setText(manager.listItems());
        }
        else if (command.equals("/searchByCode")) {
            message.setText("Error: command not available yet");
        }
        else if (command.equals("/searchByName")) {
            message.setText("Error: command not available yet");
        }
        else if (command.equals("/searchByDescription")) {
            message.setText("Error: command not available yet");
        }
        else if (command.equals("/editItemLocation")) {
            message.setText("Error: command not available yet");
        }
        else if (command.equals("/report")) {
            message.setText("Error: command not available yet");
        }
        else if (command.equals("/register") || command.equals("/list") ||
                 command.equals("/search") || command.equals("/edit")) {
            message.setText(manager.printSubmenu(command));
        }
        else {
            message.setText("Error: Invalid command\n\n"+manager.printHelp());
        }

        // sending answer
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
