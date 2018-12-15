
package data;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class CocaCola extends Snack {
    private static final CocaCola instance = new CocaCola();

    /**
     * Sets the super class
     */
    private CocaCola() {
        super(3,ESnackTypes.E_COCA_COLA, 1, 10, 10);
    }
    
    /**
     * Gets the singleton instance
     * @return
     */
    public static CocaCola getInstance() {
        return instance;
    } 
}
