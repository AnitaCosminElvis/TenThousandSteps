
package data;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class Crisps extends Snack {
    private static final Crisps instance = new Crisps();

    /**
     * Sets the super class
     */
    private Crisps() {
        super(1,ESnackTypes.E_CRISPS, 0.75, 10, 10);
    }
    
    /**
     * Gets the singleton instance
     * @return
     */
    public static Crisps getInstance() {
        return instance;
    }
}
