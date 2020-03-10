/**
 * 
 */
package com.trafigura.trade;

import java.util.ArrayList;
import java.util.List;

import com.trafigura.model.Transaction;
import com.trafigura.parser.CsvParser;
import com.trafigura.parser.Parser;

/**
 * @author raymond
 *
 */
public class TradeDispatcher {

	private Parser parser;
	private List<Transaction> transactions;
	private Evaluator evaluator;
	private EquityPool pool;
	
	public TradeDispatcher(Parser parser)
	{
		this.parser = parser;
		transactions = new ArrayList<Transaction>();
		this.pool = EquityPool.getInstance();
		evaluator = new SequentialEvaluatorStrategy();
	}
	
	public void publish(String contents)
	{
		List<Transaction> results = parser.parse(contents);
		transactions.addAll(results);
		
		for (Transaction tran : results)
		{
			List<Transaction> readyTransactions = evaluator.evaluate(tran);
			for (Transaction readyTransaction : readyTransactions)
			{
				pool.push(readyTransaction);
			}
		}
	}
	
	// Not enough time to complete
	public EquityPool getPool() {
		return pool;
	}

	public static void main(String args[]) {
		// Test case 1 - In Sequence, same security code, multiple update
		String strInput = "1,1,1,REL,50,INSERT,Buy\n" + 
		"2,2,1,ITC,40,INSERT,Sell\n";
		Parser parser = new CsvParser();
		TradeDispatcher dispatch = new TradeDispatcher(parser);
		dispatch.publish(strInput);
		dispatch.getPool().display();
		
		// Test case 2 - Out of sequence
		// ...
	}
}
