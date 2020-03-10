/**
 * 
 */
package com.trafigura.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raymond
 * Sub classes to inherit from Transaction class to model Cancel operation
 *
 */
public class CancelTran extends Transaction {

	public CancelTran(String strTransactionId, String strTradeId, String strVersionId, String securityCode,
			String strQuantity, String type) {
		super(strTransactionId, strTradeId, strVersionId, securityCode, strQuantity, type);
	}

	@Override
	public List<Correction> operate(Transaction previousTransaction) {
		int amount = previousTransaction.getQuantity();
		String secCode = previousTransaction.getSecurityCode();
		
		List<Correction> resultList = new ArrayList<Correction>();
		Correction correction = new Correction(secCode, -amount);
		resultList.add(correction);
		return resultList;
	}

	
	
}
