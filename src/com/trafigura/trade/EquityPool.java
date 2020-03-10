/**
 * 
 */
package com.trafigura.trade;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.trafigura.model.Correction;
import com.trafigura.model.Transaction;

/**
 * @author raymond
 * 
 * 
 *
 */
public class EquityPool {
	private static volatile EquityPool instance = null;
	
	// Internal memory structure to hold up-to-date equity position
	private HashMap<String, Integer> equityPosition;
	
	// Internal memory structure to hold previous 
	private HashMap<Integer, Transaction> previousTransactionMap;
	
	// private constructor
    private EquityPool() {
    	equityPosition = new HashMap<String, Integer>();
    	previousTransactionMap = new HashMap<Integer, Transaction>();
    }

    
    /**
     * Lazy initialization of singleton implementation
     * @return singleton instance of EquityPool
     */
    public static EquityPool getInstance() {
        if (instance == null) {
            synchronized (EquityPool.class) {
                instance = new EquityPool();
            }
        }
        return instance;
    }
    
    public void push(Transaction transaction)
    {
    	int tradeId = transaction.getTradeId();
    	Transaction previousTransaction = previousTransactionMap.get(tradeId);
    	
    	List<Correction> corrections = transaction.operate(previousTransaction);
    	
    	for (Correction cor : corrections)
    	{
    		String secCode = cor.getSecurityCode();
    		
    		if (equityPosition.containsKey(secCode))
    		{
    			int existingQuantity = equityPosition.get(secCode);
    			int newAmount = existingQuantity + cor.getQuantity();
    			equityPosition.put(secCode, newAmount);
    		}
    		else
    		{
    			equityPosition.put(secCode, cor.getQuantity());
    		}
    	}
    }
    
    /**
     * This method is to display real-time equity quotes which is ready to show based on the rules
     */
    public void display() {
    	Iterator<String> it = equityPosition.keySet().iterator();
    	
    	while (it.hasNext()) {
    		String strSecurityCode = it.next();
    		int quantity = equityPosition.get(strSecurityCode);
    		
    		System.out.println(strSecurityCode + "," + quantity);
    	}
    }
    
    
}
