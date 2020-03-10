/**
 * 
 */
package com.trafigura.model;

import java.util.ArrayList;
import java.util.List;

import com.trafigura.util.Constants;

/**
 * @author raymond
 * 
 * Sub classes to inherit from Transaction class to model Insert operation
 *
 */
public class InsertTran extends Transaction {

	public InsertTran(String strTransactionId, String strTradeId, String strVersionId, String securityCode,
			String strQuantity, String type) {
		super(strTransactionId, strTradeId, strVersionId, securityCode, strQuantity, type);
	}

	@Override
	public List<Correction> operate(Transaction previousTransaction) {
		List<Correction> resultList = new ArrayList<Correction>();
		Correction correction = null;
		if (Constants.BUY_TYPE.equalsIgnoreCase(type))
		{
			correction = new Correction(securityCode, quantity);
		}
		else 
		{
			correction = new Correction(securityCode, -quantity);
		}
		
		if (correction!=null)
		{
			resultList.add(correction);
		}
		return resultList;
	}

	

}
