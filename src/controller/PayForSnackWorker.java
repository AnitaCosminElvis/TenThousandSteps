
package controller;

import data.ESnackTypes;
import data.Defines;
import javax.swing.SwingWorker;
import model.Model;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class PayForSnackWorker  extends SwingWorker{
    Model           model;
    int             selectedQuantity;
    ESnackTypes     snackType;
    
    /**
     * Sets the model, the snack type and the selectedQuantity
     * @param model
     * @param snackType
     * @param selectedQuantity
     */
    public PayForSnackWorker(Model model, ESnackTypes snackType, int selectedQuantity){
        this.model = model;
        this.snackType = snackType;
        this.selectedQuantity = selectedQuantity;
    }
    
    /**
     * Processes change calculations on a seperate thread and updates the data from the model
     * @return
     * @throws Exception
     */
    @Override
    protected Object doInBackground() throws Exception {
        
        int     snackQuantity = model.getSnackQuantityByType(snackType);
        
        if ((0 == selectedQuantity) && (0 == snackQuantity)){
            model.showPopUpErrorMessage(Defines.POP_UP_ERR_OUT_OF_STOCK);
            return null;
        }
        
        if (0 == selectedQuantity){
            model.showPopUpErrorMessage(Defines.POP_UP_ERR_QUANTITY);
            return null;
        }
        
        if (selectedQuantity > snackQuantity){
            model.showPopUpErrorMessage(Defines.POP_UP_ERR_MAXIMUM_NO);
            return null;
        }
        
        double totalOwedValue = selectedQuantity * model.getSnackPriceByType(snackType);
        double insertedCoins = model.getdInsertedCoins();

        if (insertedCoins >= totalOwedValue){
            try{
                model.setdCashInAmount(Double.valueOf(Defines.DECIMAL_FORMAT
                        .format(insertedCoins - totalOwedValue)));

                model.setSnackQuantityByType(snackType, snackQuantity - selectedQuantity);
            }catch(Exception ex){
                model.showPopUpErrorMessage(ex.getMessage());
            }
        }else {
            model.showPopUpErrorMessage(Defines.POP_UP_ERR_NO_COINS);
        }
        
        return null; 
    }
    
}
