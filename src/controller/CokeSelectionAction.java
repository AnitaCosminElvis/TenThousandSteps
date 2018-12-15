
package controller;

import data.ESnackTypes;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Model;

/**
 *Sets the model
 * @author Cosmin Elvis Anita
 */
public class CokeSelectionAction  extends AbstractAction {
    Model           model;
    ESnackTypes     type = ESnackTypes.E_COCA_COLA;
    
    /**
     *
     * @param model
     */
    public CokeSelectionAction(Model model) {
        this.model = model;
    }

    /**
     * triggered action event from view component
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        model.syncByType(type);
    }
}
