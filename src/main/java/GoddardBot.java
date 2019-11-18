import java.lang.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * The GoddardBot implement the bot
 *
 * @author  Matheus de Andrade
 * @author  Pedro Henrique
 * @version 1.0
 */
public class GoddardBot extends TelegramLongPollingBot {
    private Manager manager = null;

    /**
     * Is called every new message to bot
     *
     * @param   update Update object with a new message
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
    public void onUpdateReceived(Update update) {
        // Receiving commands
        String msgReceived = update.getMessage().getText();
        SendMessage message = new SendMessage();

        String arr[] = msgReceived.split(" ", 2);
        String command = arr[0];
        String args = "";
        if (arr.length > 1)
            args = arr[1];

        // commands switcher
        manager = manager.getInstance();
        System.out.println("Command: " + command);
        System.out.println("Resto: " + args);

        if (command.equals("/help")) {
            message.setText(manager.printHelp());
        }
        else if (command.equals("/start")) {
            message.setText(manager.printWelcome());
        }
        else if (command.equals("/registerLocation")) {
            try {
                manager.addLocation(args);
                message.setText("Location added!");
            } catch (EntityAlreadyExistsException e) {
                message.setText("Already exists a location with this name");
            } catch (IllegalArgumentException e) {
                message.setText("Wrong arguments to this command");
            }
        }
        else if (command.equals("/registerCategory")) {
            try {
                manager.addCategory(args);
                message.setText("Category added!");
            } catch (EntityAlreadyExistsException e) {
                message.setText("Already exists a category with this code");
            } catch (IllegalArgumentException e) {
                message.setText("Wrong arguments to this command");
            }
        }
        else if (command.equals("/registerItem")) {
            try {
                manager.addItem(args);
                message.setText("Item added!");
            } catch (EntityAlreadyExistsException e) {
                message.setText("Already exists an item with this code");
            } catch (EntityDoesNotExistsException e) {
                message.setText(e.getMessage());
            } catch (IllegalArgumentException e) {
                message.setText("Wrong arguments to this command");
            }
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
            if(args.split(" ").length != 1 || args.equals("")){
                message.setText("Wrong arguments to this command");
            }
            else {
                message.setText(manager.searchItemByCode(args));
            }
        }
        else if (command.equals("/searchByName")) {
            if(args.split(" ").length != 1 || args.equals("")){
                message.setText("Wrong arguments to this command");
            }
            else {
                message.setText(manager.searchItemByName(args));
            }
        }
        else if (command.equals("/searchByDescription")) {
            if(args.split(" ").length != 1 || args.equals("")){
                message.setText("Wrong arguments to this command");
            }
            else {
                message.setText(manager.searchItemByDescription(args));
            }
        }
        else if (command.equals("/editItemLocation")) {
            String args_arr[] = args.split(" ");
            if(args_arr.length != 2) {
                message.setText("Wrong arguments to this command");
            }
            else {
                try {
                    manager.editItemLocation(args_arr[0], args_arr[1]);
                    message.setText("Location changed!");
                } catch (EntityDoesNotExistsException e) {
                    message.setText(e.getMessage());
                }
            }
        }
        else if (command.equals("/report")) {
            message.setText(manager.report());
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
