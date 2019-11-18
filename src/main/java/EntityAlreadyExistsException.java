
/**
 * EntityAlreadyExistsException is an exception when an
 * item, category or location already exist
 *
 * @author  Matheus de Andrade
 * @author  Pedro Henrique
 * @version 1.0
 */
public class EntityAlreadyExistsException extends Exception {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
