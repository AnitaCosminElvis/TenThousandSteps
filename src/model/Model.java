
package model;

import data.ChangeDisperser;
import data.AdminReport;
import data.ECoinTypes;
import data.ESnackTypes;
import data.Defines;
import data.ISnack;
import data.SyncUpData;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

/**
 * The main model from a Model-View-Controller architecture
 * @author Cosmin Elvis Anita
 */
public class Model {
    final PropertyChangeSupport     propChangeSupp      = new PropertyChangeSupport(this);
    HashMap<ESnackTypes, ISnack>    snacksMap           = new HashMap<>();// snack id   -   snack object
    HashMap<ESnackTypes, Double>    profitPerSnackMap   = new HashMap<>();// snack id   -   profit
    ChangeDisperser                 changeDisperser     = new ChangeDisperser();
    Double                          dInsertedCoins      = 0.0;
    Double                          dCashInAmount       = 0.0;

    final Double                    dBuyingPrice        = 0.45;
    final String                    user                = "10976";
    final String                    pass                = "1234";
   
    /**
     *Initialises the object and all it's containing objects
     */
    public synchronized void initialize(){
        dInsertedCoins      = 0.0;
        dCashInAmount       = 0.0;    
        
        try{
            initializeSnacks();
            initializeProfitPerSnack();
            changeDisperser.initialize();
        }catch(Exception ex){
            throw ex;
        }
    }
    
    /**
     * Sets the counter for each type of change  
     * @param type
     * @param nValue
     */
    public synchronized void transferInsertedChangeToMachine(ECoinTypes type, int nValue) {
        int lastCountValue = changeDisperser.getChangeCountByType(type);
        this.changeDisperser.setChangeCountByType(type, nValue + lastCountValue);
    }
    
    /**
     * Checks the credentials and fires data for the report page
     * @param user
     * @param pass
     */
    public synchronized void login(String user, String pass){
        if (((this.user.equals(user)) && (this.pass.equals(pass))) 
          || (user.isEmpty() && pass.isEmpty())){
            AdminReport report = new AdminReport();
            Double dTotalChange = changeDisperser.getTotalChange();
            
            report.dTotalMoney = dTotalChange;
            report.dTotalMoney = Double.valueOf(Defines.DECIMAL_FORMAT.format(report.dTotalMoney));
            report.dProfit =  calculateTotalProfits();
            report.dProfit = Double.valueOf(Defines.DECIMAL_FORMAT.format(report.dProfit));
            report.dLosses = 0.0;
            
            this.propChangeSupp.firePropertyChange(Defines.EVENT_LOGGED, null, report);
        }else{
            this.propChangeSupp.firePropertyChange(Defines.EVENT_ERROR, null, Defines.POP_UP_ERR_INVALID_CRED);
        }
    }
        
    /**
     * Triggers event for showing error message
     * @param szMessage
     */
    public synchronized void showPopUpErrorMessage(String szMessage){
        this.propChangeSupp.firePropertyChange(Defines.EVENT_ERROR, null, szMessage);
    }
    
    /**
     * Triggers event for updating the view by snack selection 
     * @param type
     */
    public synchronized void syncByType(ESnackTypes type){
        SyncUpData data = new SyncUpData();
        
        data.type = type;
        data.dCashIn = this.dCashInAmount;
        data.dInsertedCoins = this.dInsertedCoins;
        data.dPrice = this.getSnackPriceByType(type);
        data.nQuantity = this.getSnackQuantityByType(type);
        
        this.propChangeSupp.firePropertyChange(Defines.EVENT_SYNC_UP, null, data);
    }
    
    /**
     * Gets the price from the snacks hash map by type 
     * @param type
     * @return
     */
    public synchronized Double getSnackPriceByType(ESnackTypes type){
        return snacksMap.get(type).getdPrice();
    }
    
    /**
     * Gets the quantity of a snack from the snacks hash map by type
     * @param type
     * @return
     */
    public synchronized int getSnackQuantityByType(ESnackTypes type){
        return snacksMap.get(type).getnQuantity();
    }
    
    /**
     * Sets the quantity of a snack to the snacks hash map by type
     * and triggers an event to update the UI (badge, quantity slider)
     * @param type
     * @param nQuantity
     */
    public synchronized void setSnackQuantityByType(ESnackTypes type, int nQuantity){
        int nOldQuantity = snacksMap.get(type).getnQuantity();
        String szEventName;
        
        switch(type){
            case E_CRISPS:
                szEventName = Defines.EVENT_CRISPS_Q;
                break;
            case E_MARS:
                szEventName = Defines.EVENT_MARS_Q;
                break;
            case E_COCA_COLA:
                szEventName = Defines.EVENT_COKE_Q;
                break;
            case E_EUGENIA:
                szEventName = Defines.EVENT_EUGENIA_Q;
                break;
            case E_WATER:
                szEventName = Defines.EVENT_WATER_Q;
                break;
            default:
                throw new AssertionError(type.name());
        }
        
        this.propChangeSupp.firePropertyChange(szEventName, nOldQuantity, nQuantity);
        
        snacksMap.get(type).setnQuantity(nQuantity);
    }
    
    /**
     * Gets the value of a particular coin by type from the ChangeDispenser object
     * @param type
     * @return
     */
    public synchronized Double getCoinValueByType(ECoinTypes type){
        return changeDisperser.getCoinValueByType(type);
    }
        
    /**
     * Gets the amount of coins for a particular type from the ChangeDisperser object
     * @param type
     * @return
     */
    public synchronized int getChangeCountByType(ECoinTypes type){
        return changeDisperser.getChangeCountByType(type);
    }
    
    /**
     * Sets the amount of coins for a particular type in the ChangeDisperser object
     * @param type
     * @param nNewValue
     */
    public synchronized void setChangeCountByType(ECoinTypes type, int nNewValue){
        changeDisperser.setChangeCountByType(type,nNewValue);      
    }
    
    /**
     * Gets the total amount of inserted coins for a given transaction
     * @return
     */
    public synchronized Double getdInsertedCoins() {
        return dInsertedCoins;
    }

    /**
     * Sets the total amount of inserted coins for a given transaction
     * @param dInsertedCoins
     */
    public synchronized void setdInsertedCoins(Double dInsertedCoins) {
        this.propChangeSupp.firePropertyChange(Defines.EVENT_INSERTED_COIN, this.dInsertedCoins, dInsertedCoins);
        this.dInsertedCoins = dInsertedCoins;
    }

    /**
     * Gets the cash in amount
     * @return
     */
    public synchronized Double getdCashInAmount() {
        return dCashInAmount;
    }

    /**
     * Sets the cash in amount
     * @param dCashInAmount
     * @throws java.lang.Exception
     */
    public synchronized void setdCashInAmount(Double dCashInAmount) throws Exception {
        boolean bHasError = false;
        
        try{
            changeDisperser.substractOptimalChange(dCashInAmount);
        } catch (Exception ex) {
            dCashInAmount = this.dInsertedCoins;
            bHasError = true;
        }
        
        dCashInAmount += this.dCashInAmount;
        
        this.propChangeSupp.firePropertyChange(Defines.EVENT_PAY_FOR_SNACK, this.dCashInAmount, dCashInAmount);
        this.dCashInAmount = dCashInAmount;
        this.setdInsertedCoins(0.0);
        if (true == bHasError){
            throw new Exception(Defines.POP_UP_ERR_NO_CHANGE);
        }
    }

    /**
     * Triggers event for reseting the cash in amount label 
     * and sets the cash in amount to 0
     */
    public synchronized void resetCashInAmount(){
        this.propChangeSupp.firePropertyChange(Defines.EVENT_CASH_IN, this.dCashInAmount, 0.0);
        this.dCashInAmount = 0.0;
    }
    
    /**
     * Adds a listener to the PropertyChangeSupport object
     * The listener can be any UI component which implements PropertyChangeListener
     * @param listener
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        propChangeSupp.addPropertyChangeListener(listener);
    }
    
     /**
     * Uses SnacksFactory class to instantiate the snacks and adds allocated objects to the 
     * snacks hash map
     */
    private void initializeSnacks(){
        ISnack crispsSnack = SnacksFactory.getSnack(ESnackTypes.E_CRISPS);
        ISnack marsSnack = SnacksFactory.getSnack(ESnackTypes.E_MARS);
        ISnack cokeSnack = SnacksFactory.getSnack(ESnackTypes.E_COCA_COLA);
        ISnack eugeniaSnack = SnacksFactory.getSnack(ESnackTypes.E_EUGENIA);
        ISnack waterSnack = SnacksFactory.getSnack(ESnackTypes.E_WATER);

        snacksMap.put(ESnackTypes.E_CRISPS, crispsSnack);
        snacksMap.put(ESnackTypes.E_MARS, marsSnack);
        snacksMap.put(ESnackTypes.E_COCA_COLA, cokeSnack);
        snacksMap.put(ESnackTypes.E_EUGENIA, eugeniaSnack);
        snacksMap.put(ESnackTypes.E_WATER, waterSnack);
    }
    
     /**
     * Initialises the profit per hash map  
     * snacks hash map
     */
    private void initializeProfitPerSnack(){
        for(int i = 0; i < snacksMap.size(); i++){
            ISnack      snack;
            ESnackTypes type;
            Double      dProfit;
            
            type = getSnackTypeByIndex(i);
            snack = snacksMap.get(type);
            
            dProfit = snack.getdPrice() - dBuyingPrice;
            
            profitPerSnackMap.put(type, Double.valueOf(Defines.DECIMAL_FORMAT.format(dProfit)));
        }
    }
    
     /**
     * Extracts the profits from the has map and calculates the total 
     */
    private Double calculateTotalProfits(){
        Double dTotalProfits = 0.0;
        
        for(int i = 0; i < snacksMap.size(); i++){
            ISnack      snack;
            ESnackTypes type;
            Double      dSnackProfit;
            
            type = getSnackTypeByIndex(i);
            snack = snacksMap.get(type);
            
            dSnackProfit = profitPerSnackMap.get(type);
            
            dTotalProfits += snack.getSoldQuantity() * dSnackProfit;
        }
        
        return Double.valueOf(Defines.DECIMAL_FORMAT.format(dTotalProfits));
    }
    
    /**
     * Converts the enum snack type to integer
     */
    private int getIndexBySnackType(ESnackTypes type){
        int index = 0;
        switch(type){
            case E_CRISPS:
                index = 0;
                break;
            case E_MARS:
                index = 1;
                break;
            case E_COCA_COLA:
                index = 2;
                break;
            case E_EUGENIA:
                index = 3;
                break;
            case E_WATER:
                index = 4;
                break;
            default:
                throw new AssertionError(type.name());
        
        }
        return index;
    }
    
    /**
     * Converts a integer index to a enum snack type
     */
    private ESnackTypes getSnackTypeByIndex(int index){
        ESnackTypes type = ESnackTypes.E_CRISPS;
        
        switch(index){
            case 0:
                type = ESnackTypes.E_CRISPS;
                break;
            case 1:
                type = ESnackTypes.E_MARS;
                break;
            case 2:
                type = ESnackTypes.E_COCA_COLA;
                break;
            case 3:
                type = ESnackTypes.E_EUGENIA;
                break;
            case 4:
                type = ESnackTypes.E_WATER;
                break;
            default:
                throw new AssertionError(type.name());
        
        }
        
        return type;
    }
}