package com.cvl.brm.tf.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cvl.brm.tf.api.CytaAddProdDiscounts;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.PortalContext;
import com.portal.pcm.PortalOp;

public class CytaAddProdDiscounts {

	protected final static Logger logger = LoggerFactory.getLogger(CytaAddProdDiscounts.class
			.getName());
	
	public static FList addProdDisctSACustomer(PortalContext ctx, String accountPoidStr) throws EBufException {
		
		FList outFlist = null;
		
		String[] tokens = accountPoidStr.split(" ");
		String acctPoidId0 = tokens[2];		
		
		try {
		String inpFlistStr = "0 PIN_FLD_POID           POID [0] 0.0.0.1 /plan -1 0\n"
				+ "0 PIN_FLD_ACCOUNT_OBJ    POID [0] "+ accountPoidStr +" \n"
				+ "0 PIN_FLD_PROGRAM_NAME    STR [0] \"Product & Discount Purchase\"\n"
				+ "0 PIN_FLD_SERVICES      ARRAY [0] allocated 20, used 5\n"
				+ "1     PIN_FLD_SERVICE_OBJ    POID [0] 0.0.0.1 /service/ip -1 0\n"
				+ "1     PIN_FLD_PASSWD_CLEAR    STR [0] \"XXXX\" \n"
				+ "1     PIN_FLD_LOGIN           STR [0] \"IP-"+acctPoidId0+"\"\n"
				+ "1     PIN_FLD_DEAL_INFO    SUBSTRUCT [0] allocated 20, used 6\n"
				+ "2         PIN_FLD_PRODUCTS      ARRAY [0] allocated 20, used 11\n"
				+ "3             PIN_FLD_DESCR           STR [0] \"New 2play Home 24 Month\"\n"
				+ "3             PIN_FLD_PRODUCT_OBJ    POID [0] 0.0.0.1 /product 65533044 0\n"
				+ "3             PIN_FLD_QUANTITY     DECIMAL [0] 1\n"
				+ "3             PIN_FLD_STATUS         ENUM [0] 1\n"
				+ "3             PIN_FLD_STATUS_FLAGS    INT [0] 0\n"
				+ "3             PIN_FLD_PURCHASE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_PURCHASE_END_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_END_T  TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_END_T  TSTAMP [0] (0) <null>\n"
				+ "2         PIN_FLD_DISCOUNTS     ARRAY [1] allocated 20, used 11\n"
				+ "3             PIN_FLD_DESCR           STR [0] \"Offer - Discount New 2play Home Employee A\"\n"
				+ "3             PIN_FLD_DISCOUNT_OBJ   POID [0] 0.0.0.1 /discount 69852224 0\n"
				+ "3             PIN_FLD_QUANTITY     DECIMAL [0] 1\n"
				+ "3             PIN_FLD_STATUS         ENUM [0] 1\n"
				+ "3             PIN_FLD_STATUS_FLAGS    INT [0] 0\n"
				+ "3             PIN_FLD_PURCHASE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_PURCHASE_END_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_END_T  TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_END_T  TSTAMP [0] (0) <null>\n"
				+ "2         PIN_FLD_NAME            STR [0] \"\"\n"
				+ "2         PIN_FLD_POID           POID [0] 0.0.0.1 /deal -1 0\n"
				+ "2         PIN_FLD_END_T        TSTAMP [0] (0) <null>\n"
				+ "2         PIN_FLD_START_T      TSTAMP [0] (0) <null>\n"
				+ "1     PIN_FLD_ALIAS_LIST    ARRAY [1] allocated 20, used 1\n"
				+ "2         PIN_FLD_NAME            STR [0] \"IP-"+acctPoidId0+"\"\n"
				+ "0 PIN_FLD_SERVICES      ARRAY [1] allocated 20, used 5\n"
				+ "1     PIN_FLD_SERVICE_OBJ    POID [0] 0.0.0.1 /service/voip -1 0\n"
				+ "1     PIN_FLD_PASSWD_CLEAR    STR [0] \"XXXX\" \n"
				+ "1     PIN_FLD_LOGIN           STR [0] \"215"+ acctPoidId0 +"\" \n" 
				+ "1     PIN_FLD_DEAL_INFO    SUBSTRUCT [0] allocated 20, used 6\n"
				+ "2         PIN_FLD_PRODUCTS      ARRAY [0] allocated 20, used 11\n"
				+ "3             PIN_FLD_DESCR           STR [0] \"New Home 2play - VOIP - charge\"\n"
				+ "3             PIN_FLD_PRODUCT_OBJ    POID [0] 0.0.0.1 /product 67258316 0\n"
				+ "3             PIN_FLD_QUANTITY     DECIMAL [0] 1\n"
				+ "3             PIN_FLD_STATUS         ENUM [0] 1\n"
				+ "3             PIN_FLD_STATUS_FLAGS    INT [0] 0\n"
				+ "3             PIN_FLD_PURCHASE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_PURCHASE_END_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_END_T  TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_END_T  TSTAMP [0] (0) <null>\n"
				+ "2         PIN_FLD_PRODUCTS      ARRAY [1] allocated 20, used 11\n"
				+ "3             PIN_FLD_DESCR           STR [0] \"New 2play HOME - National - Unlimited - monthly fee\"\n"
				+ "3             PIN_FLD_PRODUCT_OBJ    POID [0] 0.0.0.1 /product 65536788 0\n"
				+ "3             PIN_FLD_QUANTITY     DECIMAL [0] 1\n"
				+ "3             PIN_FLD_STATUS         ENUM [0] 1\n"
				+ "3             PIN_FLD_STATUS_FLAGS    INT [0] 0\n"
				+ "3             PIN_FLD_PURCHASE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_PURCHASE_END_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_END_T  TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_END_T  TSTAMP [0] (0) <null>\n"
				+ "2         PIN_FLD_NAME            STR [0] \"\"\n"
				+ "2         PIN_FLD_POID           POID [0] 0.0.0.1 /deal -1 0\n"
				+ "2         PIN_FLD_END_T        TSTAMP [0] (0) <null>\n"
				+ "2         PIN_FLD_START_T      TSTAMP [0] (0) <null>\n"
				+ "1     PIN_FLD_ALIAS_LIST    ARRAY [1] allocated 20, used 1\n"
				+ "2         PIN_FLD_NAME            STR [0] \"215"+ acctPoidId0 +"\" \n" 
				+ "0 PIN_FLD_SERVICES      ARRAY [2] allocated 20, used 5\n"
				+ "1     PIN_FLD_SERVICE_OBJ    POID [0] 0.0.0.1 /service/tv -1 0\n"
				+ "1     PIN_FLD_PASSWD_CLEAR    STR [0] \"XXXX\" \n"
				+ "1     PIN_FLD_LOGIN           STR [0] \"TV-"+acctPoidId0+"\"\n"
				+ "1     PIN_FLD_DEAL_INFO    SUBSTRUCT [0] allocated 20, used 5\n"
				+ "2         PIN_FLD_PRODUCTS      ARRAY [0] allocated 20, used 11\n"
				+ "3             PIN_FLD_DESCR           STR [0] \"CytaVision \316\222\316\261\317\203\316\271\316\272\317\214 \316\240\316\261\316\272\316\255\317\204\316\277\"\n"
				+ "3             PIN_FLD_PRODUCT_OBJ    POID [0] 0.0.0.1 /product 48547002 0\n"
				+ "3             PIN_FLD_QUANTITY     DECIMAL [0] 1\n"
				+ "3             PIN_FLD_STATUS         ENUM [0] 1\n"
				+ "3             PIN_FLD_STATUS_FLAGS    INT [0] 0\n"
				+ "3             PIN_FLD_PURCHASE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_PURCHASE_END_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_CYCLE_END_T  TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_START_T TSTAMP [0] (0) <null>\n"
				+ "3             PIN_FLD_USAGE_END_T  TSTAMP [0] (0) <null>\n"
				+ "2         PIN_FLD_NAME            STR [0] \"\"\n"
				+ "2         PIN_FLD_POID           POID [0] 0.0.0.1 /deal -1 0\n"
				+ "2         PIN_FLD_END_T        TSTAMP [0] (0) <null>\n"
				+ "2         PIN_FLD_START_T      TSTAMP [0] (0) <null>\n"
				+ "1     PIN_FLD_ALIAS_LIST    ARRAY [1] allocated 20, used 1\n"
				+ "2         PIN_FLD_NAME            STR [0] \"TV-"+acctPoidId0+"\"\n";
		
		FList inflist = FList.createFromString(inpFlistStr);
		
		logger.trace("\n Purchase prod_disct input: \n\n"+inflist.asString());
		outFlist = ctx.opcode(PortalOp.CUST_MODIFY_CUSTOMER, inflist);
		logger.debug("Purchase prod_disct output: \n"+outFlist.asString());
			} catch (EBufException ebufex) {
		logger.error("Error in Purchase prod_disct : \n"+ebufex.toString());
			}
		
		return outFlist;
		
	}

}
