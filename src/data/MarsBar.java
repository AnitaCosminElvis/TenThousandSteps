
package data;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class MarsBar extends Snack {
    static final MarsBar instance = new MarsBar();
     
    /**
     * Sets the super class
     */
    private MarsBar() {
        super(2,ESnackTypes.E_MARS, 0.7, 10, 10);
    }
    
    /**
     * Gets the singleton instance
     * @return
     */
    public static MarsBar getInstance() {
        return instance;
    }  
}
