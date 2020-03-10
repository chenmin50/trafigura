package com.trafigura.trade;

import java.util.List;

import com.trafigura.model.Transaction;

public interface Evaluator {
	public List<Transaction> evaluate(Transaction tran);
}
