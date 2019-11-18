
/**
 * Location class implements a location of an item
 *
 * @author  Matheus de Andrade
 * @author  Pedro Henrique
 * @version 1.0
 */
public class Location extends DBEntity {

    /**
     * Location constructor
     *
     * @param   name location name
     * @param   description location description
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
    public Location(String name, String description) {
        super(name, description);
    }

    /**
     * Location constructor
     *
     * @param   location location object
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
    public Location(Location location) {
        super(location.getName(), location.getDescription());
    }

    /**
     * Generates a string with all data of this location object
     *
     * @return  String with all data
     * @author  Matheus de Andrade
     * @author  Pedro Henrique
     * @version 1.0
     */
    public String toString(){
        return super.getName() + " " + super.getDescription();
    }

}
