public class Location extends DBEntity {
    public Location(String name, String description) {
        super(name, description);
    }

    public String toString(){
        return super.getName() + " " + super.getDescription();
    }

}
