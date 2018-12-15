
package data;

/**
 *
 * @author home
 */
public abstract class Snack implements ISnack{
    final long          nId;
    final ESnackTypes   eType;
    final int           nInitialNo;
    Double              dPrice;
    int                 nQuantity;

    public Snack(long id, ESnackTypes eType, double dPrice, int nQuantity, int nInitialNo) {
        this.nId = id;
        this.eType = eType;
        this.dPrice = dPrice;
        this.nQuantity = nQuantity;
        this.nInitialNo = nInitialNo;
    }
    
    /**
     * Gets sold quantity
     * @return
     */
    @Override
    public int getSoldQuantity() {
        return (nInitialNo - nQuantity);
    }

    /**
     * Gets current quantity
     * @return
     */
    @Override
    public int getnQuantity() {
        return nQuantity;
    }

    /**
     * Sets current quantity
     * @param nQuantity
     */
    @Override
    public void setnQuantity(int nQuantity) {
        this.nQuantity = nQuantity;
    }
    
    /**
     * Gets snack id
     * @return
     */
    @Override
    public long getnId() {
        return nId;
    }

    /**
     * Gets the type of the snack
     * @return
     */
    @Override
    public ESnackTypes geteType() {
        return eType;
    }

    /**
     * Gets the price of the snack
     * @return
     */
    @Override
    public Double getdPrice() {
        return dPrice;
    }    
}
