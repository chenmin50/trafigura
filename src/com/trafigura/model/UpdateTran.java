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
 * Sub classes to inherit from Transaction class to model Update operation
 *
 */
public class UpdateTran extends Transaction {

	public UpdateTran(String strTransactionId, String strTradeId, String strVersionId, String securityCode,
			String strQuantity, String type) {
		super(strTransactionId, strTradeId, strVersionId, securityCode, strQuantity, type);
	}

	@Override
	public List<Correction> operate(Transaction previousTransaction) {
		
		List<Correction> resultList = new ArrayList<Correction>();
		
		// Security code is the same
		if (previousTransaction.getSecurityCode().equals(this.getSecurityCode()))
		{
			String previousType = previousTransaction.getType();
			String currentType = this.getType();
			int correctAmount = 0;
			if (Constants.BUY_TYPE.equalsIgnoreCase(previousType))
			{
				if (Constants.BUY_TYPE.equalsIgnoreCase(currentType))
				{
					correctAmount = -previousTransaction.getQuantity() + this.getQuantity();
				}
				else
				{
					correctAmount = -previousTransaction.getQuantity() - this.getQuantity();
				}
			}
			else // Previous is sell
			{
				if (Constants.BUY_TYPE.equalsIgnoreCase(currentType))
				{
					correctAmount = previousTransaction.getQuantity() + this.getQuantity();
				}
				else
				{
					correctAmount = previousTransaction.getQuantity() - this.getQuantity();
				}
			}
			
			Correction correction = new Correction(securityCode, correctAmount);
			resultList.add(correction);
		}
		else // Security code is different
		{
			// Offset amount on previous security code
			String previousType = previousTransaction.getType();
			int correctAmount = 0;
			if (Constants.BUY_TYPE.equalsIgnoreCase(previousType))
			{
				correctAmount = -previousTransaction.getQuantity();
			}
			else
			{
				correctAmount = previousTransaction.getQuantity();
			}
			Correction correction = new Correction(previousTransaction.getSecurityCode(), correctAmount);
			resultList.add(correction);
			
			String currentType = this.getType();
			if (Constants.BUY_TYPE.equalsIgnoreCase(currentType))
			{
				correctAmount = this.getQuantity();
			}
			else
			{
				correctAmount = -this.getQuantity();
			}
			
			correction = new Correction(this.getSecurityCode(), correctAmount);
			resultList.add(correction);
		}
		
		
		return resultList;
	}
	
}
