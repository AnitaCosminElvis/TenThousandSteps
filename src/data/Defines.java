
package data;

import java.text.DecimalFormat;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class Defines {
    public static final DecimalFormat   DECIMAL_FORMAT          = new DecimalFormat("#.########");
    public static final String          EVENT_ERROR             = "Error"; 
    public static final String          EVENT_SYNC_UP           = "SyncUp";
    public static final String          EVENT_INSERTED_COIN     = "InsertedCoin";
    public static final String          EVENT_PAY_FOR_SNACK     = "PayForSnack";
    public static final String          EVENT_CASH_IN           = "CashInAmount";
    public static final String          EVENT_CRISPS_Q          = "CrispsQuantity";
    public static final String          EVENT_MARS_Q            = "MarsQuantity";
    public static final String          EVENT_COKE_Q            = "CokeQuantity";
    public static final String          EVENT_EUGENIA_Q         = "EugeniaQuantity";
    public static final String          EVENT_WATER_Q           = "WaterQuantity";
    public static final String          EVENT_LOGGED            = "Logged";
    public static final String          RESOURCES_PATH          = "/resources/";
    public static final String          POP_UP_ERR_OUT_OF_STOCK = "Out of stock, please select another snack!";
    public static final String          POP_UP_ERR_QUANTITY     = "Please select your snack's quantity!";
    public static final String          POP_UP_ERR_MAXIMUM_NO   = "Selected Quantity is bigger then the actual stock!";
    public static final String          POP_UP_ERR_NO_COINS     = "Not enough coins inserted!";
    public static final String          POP_UP_ERR_NO_NEED      = "No need to insert more coins!";
    public static final String          POP_UP_ERR_NO_CHANGE    = "We are sorry, but no change is available for this selection!"
                                                                    + " Please cash in your money!";
    public static final String          POP_UP_ERR_INVALID_CRED = "Invalid User or Password!";
}
