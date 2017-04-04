package com.cvl.brm.tf.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cvl.brm.tf.api.CytaWebHosting;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.PortalContext;
import com.portal.pcm.PortalOp;

public class CytaWebHosting {

	protected final static Logger logger = LoggerFactory.getLogger(CytaWebHosting.class
			.getName());
	
	public static FList addWebHosting(PortalContext ctx, String accountPoidStr) throws EBufException {
		
		FList outFlist = null;
		
		String[] tokens = accountPoidStr.split(" ");
		String acctPoidId0 = tokens[2];		
	
		try {
		String inpFlistStr = "0 PIN_FLD_POID           POID [0] 0.0.0.1 /plan -1 0\n"
				+ "0 PIN_FLD_ACCOUNT_OBJ    POID [0] "+ accountPoidStr +" \n"
				+ "0 PIN_FLD_PROGRAM_NAME   STR [0] \"Domain-Web Hosting products test\" \n"
				+ "0 PIN_FLD_SERVICES      	ARRAY [1] allocated 20, used 5 \n"
				+ "1     PIN_FLD_SERVICE_OBJ    	POID [0] 0.0.0.1 /service/web -1 0 \n"
				+ "1     PIN_FLD_PASSWD_CLEAR    	STR [0] \"XXXX\" \n"
				+ "1     PIN_FLD_LOGIN           	STR [0] \"WEB-"+acctPoidId0+"\"\n"
				+ "1     PIN_FLD_DEAL_INFO    	SUBSTRUCT [0] allocated 20, used 5 \n"
				+ "2         PIN_FLD_PRODUCTS      ARRAY [0] allocated 20, used 10 \n"
				+ "3            PIN_FLD_DESCR          	STR [0] \"Extra Web Space 50MB\" \n"
				+ "3            PIN_FLD_PRODUCT_OBJ    	POID [0] 0.0.0.1 /product 52044445 0 \n"
				+ "3            PIN_FLD_QUANTITY       	DECIMAL [0] 2 \n"
				+ "3            PIN_FLD_STATUS         	ENUM [0] 1 \n"
				+ "3            PIN_FLD_STATUS_FLAGS   	INT [0] 0 \n"
				+ "3            PIN_FLD_PURCHASE_START_T   TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_PURCHASE_END_T     TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_CYCLE_START_T      TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_CYCLE_END_T        TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_USAGE_START_T      TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_USAGE_END_T        TSTAMP [0] (0) \n"
				+ "2         PIN_FLD_PRODUCTS      ARRAY [1] allocated 20, used 10 \n"
				+ "3            PIN_FLD_DESCR           	STR [0] \"Extra Web Space 1GB\" \n"
				+ "3            PIN_FLD_PRODUCT_OBJ    	POID [0] 0.0.0.1 /product 52043869 0 \n"
				+ "3            PIN_FLD_QUANTITY           DECIMAL [0] 1 \n"
				+ "3            PIN_FLD_STATUS             ENUM [0] 1 \n"
				+ "3            PIN_FLD_STATUS_FLAGS       INT [0] 0 \n"
				+ "3            PIN_FLD_PURCHASE_START_T   TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_PURCHASE_END_T     TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_CYCLE_START_T      TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_CYCLE_END_T        TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_USAGE_START_T      TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_USAGE_END_T        TSTAMP [0] (0) \n"
				+ "2         PIN_FLD_PRODUCTS      ARRAY [2] allocated 20, used 10 \n"
				+ "3            PIN_FLD_DESCR           	STR [0] \"Extra Database Space 200MB\" \n"
				+ "3            PIN_FLD_PRODUCT_OBJ    	POID [0] 0.0.0.1 /product 52046173 0 \n"
				+ "3            PIN_FLD_QUANTITY           DECIMAL [0] 3 \n"
				+ "3            PIN_FLD_STATUS             ENUM [0] 1 \n"
				+ "3            PIN_FLD_STATUS_FLAGS       INT [0] 0 \n"
				+ "3            PIN_FLD_PURCHASE_START_T   TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_PURCHASE_END_T     TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_CYCLE_START_T      TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_CYCLE_END_T        TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_USAGE_START_T      TSTAMP [0] (0) \n"
				+ "3            PIN_FLD_USAGE_END_T        TSTAMP [0] (0) \n"
				+ "2         PIN_FLD_NAME          STR [0] \"\" \n"
				+ "2         PIN_FLD_POID          POID [0] 0.0.0.1 /deal -1 0 \n"
				+ "2         PIN_FLD_END_T        	TSTAMP [0] (0) <null> \n"
				+ "2         PIN_FLD_START_T      	TSTAMP [0] (0) <null> \n"
				+ "1     PIN_FLD_ALIAS_LIST    	ARRAY [1] allocated 20, used 1 \n"
				+ "2         PIN_FLD_NAME          STR [0] \"WEB-"+acctPoidId0+"\"\n";
		
		FList inflist = FList.createFromString(inpFlistStr);
		
		logger.trace("\n purchase web hosting input: \n\n"+inflist.asString());
		outFlist = ctx.opcode(PortalOp.CUST_MODIFY_CUSTOMER, inflist);
		logger.debug("purchase web hosting output: \n"+outFlist.asString());
			} catch (EBufException ebufex) {
		logger.error("Error in purchase web hosting : \n"+ebufex.toString());
			}
		
		return outFlist;
		
	}

}
