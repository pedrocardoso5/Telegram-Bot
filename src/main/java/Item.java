
/**
 * Item class implements an Item
 *
 * @author  Matheus de Andrade
 * @author  Pedro Henrique
 * @version 1.0
 */
public class Item extends DBEntity {
    private String code;
    private Location location;
    private Category category;

    /**
     * Category constructor
     *
     * @param   code category code
     * @param   name category name
     * @param   description category description
     * @param   category category object
     * @param   location location object
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
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

    /**
     * Generates a string with all data of this item object
     *
     * @return  String with all data
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
    public String toString(){
        return  code + " " + super.getName() + " " + super.getDescription() + " " +
                category.getCode() + " " + location.getName();
    }
}
