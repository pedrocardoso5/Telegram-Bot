public class Item extends DBEntity {
    private String code;
    private Location location;
    private Category category;

    public Item(String code, String name, String description, Category category, Location location) {
        super(name, description);
        this.code = code;
        this.location = location;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String toString(){
        return  code + " " + super.getName() + " " + super.getDescription() + " " +
                category.getCode() + " " + location.getName();
    }
}
