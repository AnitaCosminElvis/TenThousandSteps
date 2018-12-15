
package data;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class Water extends Snack {
    private static final Water instance = new Water();

     /**
     * Sets the super class
     * @return
     */
    private Water() {
        super(5,ESnackTypes.E_WATER, 0.85, 10, 10);
    }
    
    /**
     * Gets the singleton instance
     * @return
     */
    public static Water getInstance() {
        return instance;
    }
}
