import java.util.Iterator;
import java.util.Vector;

public class Manager {
    private static Manager instance = new Manager();
    Vector items = new Vector();
    Vector categories = new Vector();
    Vector locations = new Vector();

    public static Manager getInstance(){
        return instance;
    }

    private Manager(){
        //TODO: Read all files and save objects
        System.out.println("Manager constructor");
    }

    private void saveToFile(){
        //TODO: Write vectors info in files
        System.out.println("Saving info in files");
    }

    public String printHelp(){
        return  "HELP:\n" +
                " - use /register to register a new item, category or location\n" +
                " - use /list to list all items, categories or locations\n" +
                " - use /search to search an item\n" +
                " - use /edit to change the location of an item\n" +
                " - use /report to generate a report\n";
    }

    public String printWelcome(){
        return  "Welcome to GoddardBot, a bot to manage items from your institution.\n" +
                "Type /help to see more!\n";
    }
}
