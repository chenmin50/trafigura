package com.trafigura.util;

/**
 * @author raymond
 *
 * This class hold constant values at the central place
 */

public class Constants {
	
	public static String BUY_TYPE = "Buy";
	public static String SELL_TYPE = "Sell";
	
	public static int NO_OF_FILEDS = 7;
	
	// Index to map to comma delimiter field
	public static int TRANSACTION_TYPE_INDEX = 5;
	public static int TRANSACTION_ID_INDEX = 0;
	public static int TRADE_ID_INDEX = 1;
	public static int VERSION_ID_INDEX = 2;
	public static int SECURITY_CODE_INDEX = 3;
	public static int QUANTITY_INDEX = 4;
	public static int OPERATION_INDEX = 6;
	
	public static String INSERT_TYPE = "INSERT";
	public static String UPDATE_TYPE = "UPDATE";
	public static String CANCEL_TYPE = "CANCEL";
}
