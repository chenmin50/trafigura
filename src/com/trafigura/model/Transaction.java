package com.trafigura.model;

import java.util.List;

/**
 * @author raymond
 * 
 * 
 *
 */
public abstract class Transaction {
	protected long transactionId;
	protected int tradeId;
	protected int versionId;
	
	protected String securityCode;
	protected int quantity;
	
	// Buy or Sell
	protected String type;

	public Transaction (String strTransactionId, String strTradeId, String strVersionId, 
			String securityCode, String strQuantity, String type)
	{
		this.transactionId = Long.parseLong(strTransactionId);
		this.tradeId = Integer.parseInt(strTradeId);
		this.versionId = Integer.parseInt(strVersionId);
		this.securityCode = securityCode;
		this.quantity = Integer.parseInt(strQuantity);
		this.type = type;
	}
	
	
	/**
	 * This method takes in the previous Transaction from the same Trading Desk to evaluate the amount 
	 * which should be corrected to the Equity position
	 * 
	 * @param previousTransaction - previousTransaction
	 * @return list of Corrections
	 */
	public abstract List<Correction> operate(Transaction previousTransaction);
	
	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
