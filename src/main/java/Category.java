
/**
 * Category class implements a category of an item
 *
 * @author  Matheus de Andrade
 * @author  Pedro Henrique
 * @version 1.0
 */
public class Category extends DBEntity {
    private String code;

    /**
     * Category constructor
     *
     * @param   code category code
     * @param   name category name
     * @param   description category description
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
    public Category(String code, String name, String description) {
        super(name, description);
        this.code = code;
    }

    /**
     * Category constructor
     *
     * @param   category category object
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
    public Category(Category category) {
        super(category.getName(), category.getCode());
        this.code = category.getCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Generates a string with all data of this category object
     *
     * @return  String with all data
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
    public String toString(){
        return code + " " + super.getName() + " " + super.getDescription();
    }
}
