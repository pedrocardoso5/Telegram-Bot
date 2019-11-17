public class Location extends DBEntity {
    public Location(String name, String description) {
        super(name, description);
    }

    public Location(Location location) {
        super(location.getName(), location.getDescription());
    }

    public String toString(){
        return super.getName() + " " + super.getDescription();
    }

}
