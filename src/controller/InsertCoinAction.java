
package controller;

import data.ECoinTypes;
import data.ESnackTypes;
import data.Defines;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Model;

/**
 *
 * @author Cosmin Elvis Anita
 */

public class InsertCoinAction extends AbstractAction {
    Model           model;
    ECoinTypes      coinType            = ECoinTypes.E_0_05;
    ESnackTypes     snackType;
    int             selectedQuantity    = 0;

    /**
     *
     * @param model
     */
    public InsertCoinAction(Model model) {
        this.model = model;
    }

    /**
     * Sets the selected coin type
     * @param coinType
     */
    public void setCoinType(ECoinTypes coinType) {
        this.coinType = coinType;
    }

    /**
     * Sets the selected snack type
     * @param snackType
     */
    public void setSnackType(ESnackTypes snackType) {
        this.snackType = snackType;
    }
    
    /**
     * Sets the selected quantity 
     * @param selectedQuantity
     */
    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }
    
    /**
     * triggered action event from view component
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Double coinValue = model.getCoinValueByType(coinType);
        Double oldInsertedCoins = model.getdInsertedCoins();
        Double newInsertedCoinsValue = oldInsertedCoins + coinValue;
        Double totalOwedValue = selectedQuantity * model.getSnackPriceByType(snackType);
        
        if (oldInsertedCoins >= totalOwedValue){
            model.showPopUpErrorMessage(Defines.POP_UP_ERR_NO_NEED);
            return;
        }
        
        model.setdInsertedCoins(Double.valueOf(Defines.DECIMAL_FORMAT.format(newInsertedCoinsValue)) );
        
        model.transferInsertedChangeToMachine(coinType, 1);
    }
}
