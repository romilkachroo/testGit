package com.cvl.brm.tf.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cvl.brm.tf.api.CytaOneOff;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.PortalContext;
import com.portal.pcm.PortalOp;

public class CytaOneOff {

	protected final static Logger logger = LoggerFactory.getLogger(CytaOneOff.class
			.getName());
	
	public static FList oneOffSACustomer(PortalContext ctx, String accountPoidStr) throws EBufException {
		
		FList outFlist = null;
	
		try {
		String inpFlistStr = "0 PIN_FLD_POID           POID [0] "+ accountPoidStr +" \n"
				+ "0 PIN_FLD_PROGRAM_NAME    STR [0] \"Purchase one-off\"\n"
				+ "0 PIN_FLD_DEAL_INFO    SUBSTRUCT [0] allocated 20, used 5\n"
				+ "1     PIN_FLD_NAME            STR [0] \"\"\n"
				+ "1     PIN_FLD_POID           POID [0] 0.0.0.1 /deal -1 0\n"
				+ "1     PIN_FLD_END_T        TSTAMP [0] (0) <null>\n"
				+ "1     PIN_FLD_START_T      TSTAMP [0] (0) <null>\n"
				+ "1     PIN_FLD_PRODUCTS      ARRAY [0] allocated 20, used 4\n"
				+ "2         PIN_FLD_DESCR           STR [0] \"One-off - Installation in new address - Active Loop\"\n"
				+ "2         PIN_FLD_PRODUCT_OBJ    POID [0] 0.0.0.1 /product 48017203 0\n"
				+ "2         PIN_FLD_QUANTITY     DECIMAL [0] 1\n"
				+ "2         PIN_FLD_STATUS         ENUM [0] 1\n";
		
		FList inflist = FList.createFromString(inpFlistStr);
		
		logger.trace("\n purchase one off deal input: \n\n"+inflist.asString());
		outFlist = ctx.opcode(PortalOp.BILL_PURCHASE_DEAL, inflist);
		logger.debug("purchase one off deal output: \n"+outFlist.asString());
			} catch (EBufException ebufex) {
		logger.error("Error in purchase one off deal : \n"+ebufex.toString());
			}
		
		return outFlist;
		
	}

}
