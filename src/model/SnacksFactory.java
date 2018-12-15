
package model;

import data.CocaCola;
import data.Crisps;
import data.ESnackTypes;
import data.Eugenia;
import data.ISnack;
import data.MarsBar;
import data.Water;


/**
 * The implementation of the Factory Method design pattern
 * @author Cosmin Elvis Anita
 */
public class SnacksFactory {
    
     /**
     * Gets the snack objects by their type
     * @param eSnackType
     */
    static ISnack getSnack(ESnackTypes eSnackType){
        switch(eSnackType){
            case E_CRISPS:{
                return  Crisps.getInstance();
            }
            case E_MARS:{
                return  MarsBar.getInstance();
            }
            case E_COCA_COLA:{
                return  CocaCola.getInstance();
            }
            case E_EUGENIA:{
                return  Eugenia.getInstance();
            }
            case E_WATER:{
                return  Water.getInstance();
            }
            default: return null;
        }
    }
}
