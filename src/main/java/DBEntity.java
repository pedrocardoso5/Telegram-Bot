
/**
 * Abstract class to Item, Category and Location
 *
 * @author  Matheus de Andrade
 * @author  Pedro Henrique
 * @version 1.0
 */
public abstract class DBEntity {
    private String name;
    private String description;

    /**
     * DBEntity constructor
     *
     * @param   name entity name
     * @param   description entity description
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
    public DBEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
