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

                while(locations_reader.hasNextLine()) {
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

                while(categories_reader.hasNextLine()) {
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

                while(items_reader.hasNextLine()) {
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

        items.remove(0);
        locations.remove(0);
        categories.remove(0);
        saveToFile();
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
