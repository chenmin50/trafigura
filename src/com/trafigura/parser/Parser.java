/**
 * @author raymond
 * This interface is used to decouple the actual implementation of parsing different 
 * types of raw inputs and submit the equity transaction to the central controller. 
 *
 */
package com.trafigura.parser;

import java.util.List;
import com.trafigura.model.Transaction;


public interface Parser {
	public List<Transaction> parse(String contents);
}

