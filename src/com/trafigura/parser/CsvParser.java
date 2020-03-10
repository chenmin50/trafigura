/**
 * @author raymond
 * Simple csv delimiter data will be used as the parser
 *
 */

package com.trafigura.parser;

import java.util.ArrayList;
import java.util.List;

import com.trafigura.model.CancelTran;
import com.trafigura.model.InsertTran;
import com.trafigura.model.Transaction;
import com.trafigura.model.UpdateTran;
import com.trafigura.util.Constants;


public class CsvParser implements Parser {

	
	/**
	 * @param content - the string contents with new line to separate transactions, and commas to delimiter the fields/attritutes of the transaction
	 * This constructor takes in
	 */
	public CsvParser() {
	}
	
	
	@Override
	public List<Transaction> parse(String content) {
		List<Transaction> resultList = new ArrayList<Transaction>();
	
		if (content!=null)
		{
			String [] strLines = content.split("\n");
			for (String strLine : strLines)
			{
				String [] fields = strLine.split(",");
				if (fields.length == Constants.NO_OF_FILEDS) 
				{
					String strTranType = fields[Constants.TRANSACTION_TYPE_INDEX];
					String strTranId = fields[Constants.TRANSACTION_ID_INDEX];
					String strTradeId = fields[Constants.TRADE_ID_INDEX];
					
					String strVersionId = fields[Constants.VERSION_ID_INDEX];
					String strSecurityCode = fields[Constants.SECURITY_CODE_INDEX];
					String strQuantity = fields[Constants.QUANTITY_INDEX];
					String strOperation = fields[Constants.OPERATION_INDEX];
					
					Transaction transaction = null;
					
					// TODO refactor in the future to use reflection of Factory method to avoid if/else to reduce code complexity
					if (Constants.INSERT_TYPE.equalsIgnoreCase(strTranType))
					{
						transaction = new InsertTran(strTranId, strTradeId, strVersionId, strSecurityCode, strQuantity, strOperation);
					}
					else if (Constants.INSERT_TYPE.equalsIgnoreCase(strTranType))
					{
						transaction = new UpdateTran(strTranId, strTradeId, strVersionId, strSecurityCode, strQuantity, strOperation);
					}
					else if (Constants.INSERT_TYPE.equalsIgnoreCase(strTranType))
					{
						transaction = new CancelTran(strTranId, strTradeId, strVersionId, strSecurityCode, strQuantity, strOperation);
					}
					else
					{
						//TODO
						// add exception handling, output error to audit logs
					}
					
					if (transaction!=null)
					{
						resultList.add(transaction);
					}
				}
				else
				{
					// TODO 
					// ignore invalid input transaction, for real system it will audit error logs
				}
			}
				
		}
		
		return resultList;
	}

}
