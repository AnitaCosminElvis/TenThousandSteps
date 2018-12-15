
package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Model;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class CashInAction extends AbstractAction {
    Model           model;
    
    /**
     *
     * @param model
     */
    public CashInAction(Model model) {
        this.model = model;
    }

    /**
     * triggered action event from view component
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        model.resetCashInAmount();
    }
}
