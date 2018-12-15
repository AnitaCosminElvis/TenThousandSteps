
package data;

/**
 *
 * @author Cosmin Elvis Anita
 */
public interface ISnack {
    
    /**
     * 
     * @return
     */
    public long getnId();

    /**
     *
     * @return
     */
    public ESnackTypes geteType();

    /**
     *
     * @return
     */
    public Double getdPrice();
    
    /**
     *
     * @return
     */
    public int getSoldQuantity();

    /**
     *
     * @return
     */
    public int getnQuantity();

    /**
     *
     * @param nQuantity
     */
    public void setnQuantity(int nQuantity);
}
