
package controller;

import data.ESnackTypes;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Model;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class PayForSnackAction extends AbstractAction {
    Model           model;
    ESnackTypes     snackType;
    int             selectedQuantity = 1;

    /**
     * Sets the model
     * @param model
     */
    public PayForSnackAction(Model model) {
        this.model = model;
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
     * it instantiates a PayForSnackWorker to process calculations in a separate thread
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        PayForSnackWorker payForSnackWorker;
        
        payForSnackWorker = new PayForSnackWorker(model, snackType,selectedQuantity);
        
        payForSnackWorker.execute();
        
    }
}
