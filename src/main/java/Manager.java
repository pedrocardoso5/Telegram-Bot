public class Manager {
    private static Manager instance = new Manager();


    public static Manager getInstance(){
        return instance;
    }

    private Manager(){
        //TODO: Read all files and save objects
    }

    public String printHelp(){
        return "aaaaaaaaaaaaaaaaaaaa";
    }
}
