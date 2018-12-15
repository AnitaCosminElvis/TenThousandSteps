
package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Model;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class LoginAction extends AbstractAction {
    Model           model;
    String          user    = "";
    String          pass    = "";
    
    /**
     *
     * @param model
     */
    public LoginAction(Model model) {
        this.model = model;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user){
        this.user = user;
    }
    
    /**
     *
     * @param pass
     */
    public void setPass(String pass){
        this.pass = pass;
    }
    
    /**
     * triggered action event from view component
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        model.login(user, pass);
    }
}
