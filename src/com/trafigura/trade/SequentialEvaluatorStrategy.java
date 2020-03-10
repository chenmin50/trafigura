package com.trafigura.trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trafigura.model.Transaction;

// This strategy is to hold out of sequence transactions and wait for previous version
// to reaches first. Once it is okay it will return the list of Transaction to be processed
public class SequentialEvaluatorStrategy implements Evaluator {
	private Map<Integer, Integer> lastVersionMap;
	private Map<Integer, List<Transaction>> holdingTransactionMap;
	
	public SequentialEvaluatorStrategy()
	{
		lastVersionMap = new HashMap<Integer, Integer>();
		holdingTransactionMap = new HashMap<Integer, List<Transaction>>();
	}
	
	// This method will return those transactions in order which is ready to be processed
	// If previous version is not received then it will hold the transaction in memory
	@Override
	public List<Transaction> evaluate(Transaction tran) {
		List<Transaction> readyTrans = new ArrayList<Transaction>();
		
		int tradingId = tran.getTradeId();
		int curVersion = tran.getVersionId();
		
		// TODO implement the algorithm to sort the transaction
		if (lastVersionMap.containsKey(tradingId))
		{
			int lastVersion = lastVersionMap.get(tradingId);
		
		}
		else
		{
			
		}
		
		return readyTrans;
	}

}
