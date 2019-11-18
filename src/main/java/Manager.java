import org.apache.commons.io.IOExceptionWithCause;

import java.util.Iterator;
import java.util.Vector;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

import static org.glassfish.grizzly.http.util.DataChunk.Type.String;

public class Manager {
    private static Manager instance = new Manager();
    Vector<Item> items = new Vector<Item>();
    Vector<Category> categories = new Vector<Category>();
    Vector<Location> locations = new Vector<Location>();

    public static Manager getInstance(){
        return instance;
    }

    private Manager(){
        // TODO: Read all files and save objects
        System.out.println("Manager constructor");

        // Loading locations
        File locations_file = null;
        boolean isOpen = false;
        while(!isOpen) {
            try {

                locations_file = new File("data/locations.txt");
                isOpen = true;
                Scanner locations_reader = new Scanner(locations_file);

                while(locations_reader.hasNext()) {
                    String name = new String(locations_reader.next());
                    String description = new String(locations_reader.next());
                    Location location = new Location(name, description);
                    locations.add(location);
                    System.out.println("Adding Location : " + location.toString());
                }

                locations_reader.close();

            } catch (FileNotFoundException fe) {

                System.out.println("File not found, creating it...");
                try {
                    FileWriter aux_file = new FileWriter("data/locations.txt");
                    aux_file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }

        // Loading categories
        File categories_file = null;
        isOpen = false;
        while(!isOpen) {
            try {

                categories_file = new File("data/categories.txt");
                isOpen = true;
                Scanner categories_reader = new Scanner(categories_file);

                while(categories_reader.hasNext()) {
                    String code = new String(categories_reader.next());
                    String name = new String(categories_reader.next());
                    String description = new String(categories_reader.next());

                    Category category = new Category(code, name, description);
                    categories.add(category);
                    System.out.println("Adding category : " + category.toString());
                }

                categories_reader.close();

            } catch (FileNotFoundException fe) {

                System.out.println("File not found, creating it...");
                try {
                    FileWriter aux_file = new FileWriter("data/categories.txt");
                    aux_file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }

        // Loading items
        File items_file = null;
        isOpen = false;
        while(!isOpen) {
            try {

                items_file = new File("data/items.txt");
                isOpen = true;
                Scanner items_reader = new Scanner(items_file);

                while(items_reader.hasNext()) {
                    String code = new String(items_reader.next());
                    String name = new String(items_reader.next());
                    String description = new String(items_reader.next());
                    String category_code = new String(items_reader.next());
                    String location_name = new String(items_reader.next());

                    Category item_category = findCategoryByCode(category_code);
                    Location item_location = findLocationByName(location_name);

                    Item item = new Item(code, name, description, item_category, item_location);
                    items.add(item);
                    System.out.println("Adding item : " + item.toString());
                }

                items_reader.close();

            } catch (FileNotFoundException fe) {

                System.out.println("File not found, creating it...");
                try {
                    FileWriter aux_file = new FileWriter("data/items.txt");
                    aux_file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }

    public void addLocation(String str) throws EntityAlreadyExistsException {
        // splitting string
        String arr[] = str.split(" ");
        if(arr.length != 2){
            throw new IllegalArgumentException("Wrong arguments");
        }

        // checking if the location already exists
        Location location = findLocationByName(arr[0]);
        if(location != null) {
            throw new EntityAlreadyExistsException("A location with this name already exists");
        }

        // adding
        location = new Location(arr[0], arr[1]);
        locations.add(location);

        // saving
        saveToFile();
    }

    public void addCategory(String str) throws EntityAlreadyExistsException {
        // splitting string
        String arr[] = str.split(" ");
        if(arr.length != 3){
            throw new IllegalArgumentException("Wrong arguments");
        }

        // checking if the category already exists
        Category category = findCategoryByCode(arr[0]);
        if(category != null) {
            throw new EntityAlreadyExistsException("A category with this code already exists");
        }

        // adding
        category = new Category(arr[0], arr[1], arr[2]);
        categories.add(category);

        // saving
        saveToFile();
    }

    public void addItem(String str) throws EntityAlreadyExistsException, EntityDoesNotExistsException {
        //splitting string
        System.out.println(str);
        String arr[] = str.split(" ");
        if(arr.length != 5){
            throw new IllegalArgumentException("Wrong arguments");
        }

        // checking if the item already exists
        Item item = findItemByCode(arr[0]);
        if(item != null) {
            throw new EntityAlreadyExistsException("An item with this code already exists");
        }

        // checking if the category exists
        Category category = findCategoryByCode(arr[3]);
        if(category == null){
            throw new EntityDoesNotExistsException("Category does not exist");
        }

        // checking if the location exists
        Location location = findLocationByName(arr[4]);
        if (location == null){
            throw new EntityDoesNotExistsException("Location does not exist");
        }

        // adding
        item = new Item(arr[0], arr[1], arr[2], category, location);
        items.add(item);

        // saving
        saveToFile();
    }

    public String listLocations(){
        String text = "\n";
        for (int index = 0 ; index < locations.size() ; index++){
            text = text + locations.get(index).toString() + "\n";
        }
        return text;
    }

    public String listCategories(){
        String text = "\n";
        for (int index = 0 ; index < categories.size() ; index++){
            text = text + categories.get(index).toString() + "\n";
        }
        return text;
    }

    public String listItems(){
        String text = "\n";
        for (int index = 0 ; index < items.size() ; index++){
            text = text + items.get(index).toString() + "\n";
        }
        return text;
    }

    public void editItemLocation(String code, String location_name) throws EntityDoesNotExistsException {
        // check if item exists
        System.out.println(code + " === " + location_name);
        Item item = findItemByCode(code);
        if(item == null) {
            throw new EntityDoesNotExistsException("Item does not exist");
        }

        // check if new location exists
        Location new_location = findLocationByName(location_name);
        if(new_location == null) {
            throw new EntityDoesNotExistsException("Location does not exist");
        }

        // find index of item in vector
        int item_index = items.indexOf(item);

        // change location to the new
        items.get(item_index).setLocation(new_location);

        // saving
        saveToFile();
    }

    public String printHelp(){
        return  "HELP:\n" +
                " - use /register to register a new item, category or location\n" +
                " - Use /list to list all items, categories or locations:\n" +
                " - use /search to search an item\n" +
                " - use /edit to change the location of an item\n" +
                " - use /report to generate a report\n";
    }

    public String printWelcome(){
        return  "Welcome to GoddardBot, a bot to manage items from your institution.\n" +
                "Type /help to see more!\n";
    }

    public String printSubmenu(String command) {
        String str = new String();

        if (command.equals("/register")){
            str =   "Register:\n" +
                    "- Use /registerLocation to add a new location:\n" +
                    "    /registerLocation <name> <description>\n\n" +
                    "- Use /registerCategory to add a new category\n" +
                    "    /registerCategory <code> <name> <description>\n\n" +
                    "- Use /registerItem to add a new item\n" +
                    "    /registerItem <code> <name> <description> <category_code> <location name>\n";
        }
        if (command.equals("/list")) {
            str =   "List:\n" +
                    "- Use /listLocations to list all locations\n" +
                    "- Use /listCategories to list all categories\n" +
                    "- use /listItems to list all items";
        }
        if (command.equals("/search")) {
            str =   "Search:\n" +
                    "- Use /searchByCode to search an item by code:\n" +
                    "    /searchByCode <item code>\n\n" +
                    "- Use /searchByName to search an item by name:\n" +
                    "    /searchByName <item name>\n\n" +
                    "- Use /searchByDescription to search an item by description:\n" +
                    "    /searchByDescription <item description>\n\n";

        }
        if (command.equals("/edit")) {
            str =   "Edit:\n" +
                    "- Use /editItemLocation to change a item location:\n" +
                    "    /editItemLocation <item code> <new location name>\n";
        }

        return str;
    }

    private Category findCategoryByCode(String code) {
        for (int index = 0 ; index < categories.size() ; index++) {
            if(categories.get(index).getCode().equals(code)){
                return categories.get(index);
            }
        }

        return null;
    }

    private Location findLocationByName(String name) {
        for (int index = 0 ; index < locations.size() ; index++) {
            if(locations.get(index).getName().equals(name)){
                return locations.get(index);
            }
        }

        return null;
    }

    private Item findItemByCode(String code) {
        for (int index = 0 ; index < items.size() ; index++) {
            if(items.get(index).getCode().equals(code)) {
                return items.get(index);
            }
        }

        return null;
    }

    private void saveToFile(){
        System.out.println("Saving info in files");
        // Saving locations
        try {

            // saving locations vector info in a aux file
            FileWriter fw = new FileWriter("data/aux_file.txt");
            for (int index = 0 ; index < locations.size() ; index++){
                fw.write(locations.get(index).toString() + "\n");
            }
            fw.close();
            // deleting locations.txt and changing aux file name to locations.txt
            File old_file = new File("data/locations.txt");
            File new_file = new File("data/aux_file.txt");
            new_file.renameTo(old_file);

            // saving categories vector info in a aux file
            fw = new FileWriter("data/aux_file.txt");
            for (int index = 0 ; index < categories.size() ; index++){
                fw.write(categories.get(index).toString() + "\n");
            }
            fw.close();
            // deleting categories.txt and changing aux file name to categories.txt
            old_file = new File("data/categories.txt");
            new_file = new File("data/aux_file.txt");
            new_file.renameTo(old_file);

            // saving categories vector info in a aux file
            fw = new FileWriter("data/aux_file.txt");
            for (int index = 0 ; index < items.size() ; index++){
                fw.write(items.get(index).toString() + "\n");
            }
            fw.close();
            // deleting items.txt and changing aux file name to items.txt
            old_file = new File("data/items.txt");
            new_file = new File("data/aux_file.txt");
            new_file.renameTo(old_file);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
