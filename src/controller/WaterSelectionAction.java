
package controller;

import data.ESnackTypes;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Model;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class WaterSelectionAction  extends AbstractAction {
    Model           model;
    ESnackTypes     type = ESnackTypes.E_WATER;
    
    /**
     *Sets the model
     * @param model
     */
    public WaterSelectionAction(Model model) {
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