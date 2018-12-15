
package data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Cosmin Elvis Anita
 */
public class ChangeDisperser {
    HashMap<ECoinTypes, Double>     coinValuesMap       = new HashMap<>();// coin id    -   coin value
    HashMap<ECoinTypes, Integer>    changeCountMap      = new HashMap<>();// coin id    -   counter
    ArrayList<int[]>                changeCombinations  = new ArrayList<>();
    final Double                    totalInitialChange  = 27.0;
    final int                       maximumCoins        = 20;

    /**
     * Gets the total initialChange
     * @return
     */
    public Double getTotalInitialChange() {
        return totalInitialChange;
    }
    
    /**
     * Gets the total change
     * @return
     */
    public Double getTotalChange(){
        Double dTotal = 0.0;
        
        for(int i = 0; i < this.changeCountMap.size(); i++) {
            if (0 < changeCountMap.get(getCoinTypeByIndex(i))){
                dTotal += changeCountMap.get(getCoinTypeByIndex(i))
                            * coinValuesMap.get(getCoinTypeByIndex(i));
            }
        }   
        
        return dTotal;
    }
    
    /**
     *  Initializes the hash maps
     */
    public void initialize(){
        initializeChangeCountMap();
        initializeCoinValuesMap();
    }
    
    /**
     * Extracts the optimal combination of change
     * @param dValue
     * @throws Exception
     */
    public void substractOptimalChange(Double dValue) throws Exception{
        
        if (0 == dValue) return;
        
        int[] tempCombination = new int[coinValuesMap.size()];

        setAllChangeCombinations(tempCombination,ECoinTypes.E_0_05, 0,dValue);
        
        if (0 < changeCombinations.size()){
            tempCombination = getOptimalChange();
        }else{
            throw new Exception();
        }
        
        setOptimalChange(tempCombination);
        
        changeCombinations.clear();
    }
    
     /**
     * Gets the optimal combination of change by applying a custom minimisation function
     */
    private int[] getOptimalChange() {
        int optimalValue = Integer.MAX_VALUE;
        int optimalIndex = 0;
        
        for (int i = 0; i < changeCombinations.size(); i++) {
            int currentFunctionValue = 0;
            int spreadValue = 0;
            for (int j = 0; j < coinValuesMap.size(); j++){
                if (0 < changeCombinations.get(i)[j]){
                    if (4 != j) currentFunctionValue += 2 * changeCombinations.get(i)[j]
                                    + (maximumCoins - changeCountMap.get(getCoinTypeByIndex(j)));
                    ++spreadValue;
                }
            }
            currentFunctionValue -= spreadValue;
            
            if (optimalValue > currentFunctionValue){
                optimalValue = currentFunctionValue;
                optimalIndex = i;
            }
        }
        
        return changeCombinations.get(optimalIndex).clone();
    }
    
     /**
     * Gets the optimal combination of change by applying a custom minimisation function
     * @param combination
     * @param type
     * @param coinIndex
     * @param nRemainingTotal
     */
    private void setAllChangeCombinations(int[] combination,
                                            ECoinTypes type,
                                            int coinIndex,
                                            Double nRemainingTotal){
        if (!canSupportChange(combination)) return;
        
        for(int iCoin = coinIndex; iCoin < coinValuesMap.size(); iCoin++) {
            Double nRemainingChange = nRemainingTotal - coinValuesMap.get(getCoinTypeByIndex(iCoin));
            nRemainingChange = Double.valueOf(Defines.DECIMAL_FORMAT.format(nRemainingChange));
            
            int[] tempCombination = combination.clone();
            tempCombination[getIndexByCoinType(getCoinTypeByIndex(iCoin))]++;

            if (!canSupportChange(tempCombination)) break;
            
            if (0 > nRemainingChange) break;
            
            if (0 == nRemainingChange){
                if (canSupportChange(tempCombination)) changeCombinations.add(tempCombination);
                break;
            }else{
                setAllChangeCombinations(tempCombination,
                                            getCoinTypeByIndex(iCoin), iCoin,
                                            nRemainingChange);
            }
        }
    }
    
     /**
     * Checks if the combination can be subtracted from the current change pool  
     * @param combination
     */
    private boolean canSupportChange(int[] combination){
        boolean bIsValid = true;
        
        for(int i = 0; i < changeCountMap.size(); i++) {
            if (combination[i] > changeCountMap.get(getCoinTypeByIndex(i))){
                bIsValid = false;
                break;
            }
        }   

        return bIsValid;
    }
    
     /**
     * Subtracts the optimal change combination from the current coin pool  
     * @param combination
     */
    private void setOptimalChange(int[] combination){        
        for(int i = 0; i < changeCountMap.size(); i++) {
            if (0 < combination[i]){
                int nValue = changeCountMap.get(getCoinTypeByIndex(i))
                            - combination[i];
                changeCountMap.put(getCoinTypeByIndex(i),nValue);
            }
        }   
    }
    
     /**
     * Initialises the type of coins in the hash map  
     */
    private void initializeCoinValuesMap(){
        coinValuesMap.put(ECoinTypes.E_0_05,0.05);
        coinValuesMap.put(ECoinTypes.E_0_1,0.1);
        coinValuesMap.put(ECoinTypes.E_0_2,0.2);
        coinValuesMap.put(ECoinTypes.E_0_5,0.5);
        coinValuesMap.put(ECoinTypes.E_1_0,1.0);   
    }
    
     /**
     * Initialises the change count in the hash map  
     */
    private void initializeChangeCountMap(){
        changeCountMap.put(ECoinTypes.E_0_05,20);
        changeCountMap.put(ECoinTypes.E_0_1,20);
        changeCountMap.put(ECoinTypes.E_0_2,20);
        changeCountMap.put(ECoinTypes.E_0_5,20);
        changeCountMap.put(ECoinTypes.E_1_0,10);   
    }
    
    /**
     * Gets the coin value by type
     * @param type
     * @return
     */
    public Double getCoinValueByType(ECoinTypes type){
        return coinValuesMap.get(type);
    }
        
    /**
     * Gets the change count by type
     * @param type
     * @return
     */
    public int getChangeCountByType(ECoinTypes type){
        return changeCountMap.get(type);
    }
    
    /**
     * Sets the change count by type
     * @param type
     * @param nNewValue
     */
    public void setChangeCountByType(ECoinTypes type, int nNewValue){
        changeCountMap.put(type,nNewValue);      
    }

     /**
     * Gets the index by the coin type
     * @param type
     */
    private int getIndexByCoinType(ECoinTypes type){
        int index = 0;
        switch(type){
            case E_0_05:
                index = 0;
                break;
            case E_0_1:
                index = 1;
                break;
            case E_0_2:
                index = 2;
                break;
            case E_0_5:
                index = 3;
                break;
            case E_1_0:
                index = 4;
                break;
            default:
                throw new AssertionError(type.name());
        
        }
        return index;
    }
    
     /**
     * Gets the coin type by the index
     * @param type
     */
    private ECoinTypes getCoinTypeByIndex(int index){
        ECoinTypes type = ECoinTypes.E_0_05;
        
        switch(index){
            case 0:
                type = ECoinTypes.E_0_05;
                break;
            case 1:
                type = ECoinTypes.E_0_1;
                break;
            case 2:
                type = ECoinTypes.E_0_2;
                break;
            case 3:
                type = ECoinTypes.E_0_5;
                break;
            case 4:
                type = ECoinTypes.E_1_0;
                break;
            default:
                throw new AssertionError(type.name());
        
        }
        
        return type;
    }
}