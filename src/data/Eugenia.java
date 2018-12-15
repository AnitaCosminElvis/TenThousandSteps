
package data;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class Eugenia extends Snack {
    private static final Eugenia instance = new Eugenia();

    /**
     * Sets the super class
     */
    private Eugenia() {
        super(4,ESnackTypes.E_EUGENIA, 0.5, 10, 10);
    }
    
    /**
     * Gets the singleton instance
     * @return
     */
    public static Eugenia getInstance() {
        return instance;
    }
}
