
/**
 * EntityDoesNotExistsException is an exception when an
 * item, category or location does not exist
 *
 * @author  Matheus de Andrade
 * @author  Pedro Henrique
 * @version 1.0
 */
public class EntityDoesNotExistsException extends Exception{
    public EntityDoesNotExistsException(String message) {
        super(message);
    }
}
