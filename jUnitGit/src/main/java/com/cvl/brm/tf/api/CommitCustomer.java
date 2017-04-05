package com.cvl.brm.tf.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cvl.brm.tf.api.CommitCustomer;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.PortalContext;
import com.portal.pcm.PortalOp;

public class CommitCustomer {

	protected final static Logger logger = LoggerFactory.getLogger(CommitCustomer.class
			.getName());
	
	public static FList createCustomer(PortalContext ctx, String msisdnPoid ) throws EBufException {
		
		FList outFlist = null;
		
		String serviceId = Long.toHexString(System.currentTimeMillis());
		
		try {
		String inpFlistStr = "0 PIN_FLD_POID           POID [0] 0.0.0.1 /plan 2027888 16\n"
		+ "0 PIN_FLD_SERVICES      ARRAY [0] allocated 20, used 13 \n"
		+ "1     PIN_FLD_PASSWD       STR [0] \"password\"\n"
		+ "1     PIN_FLD_LOGIN           STR [0] \"telco"+ serviceId +" \"\n"				
		+ "1     PIN_FLD_SUBSCRIPTION_OBJ   POID [0] NULL poid pointer\n"
		+ "1     PIN_FLD_SERVICE_ID      STR [0] \"telservice1"+ serviceId +" \"\n"
		+ "1     PIN_FLD_DEVICES       ARRAY [0] allocated 20, used 2\n"
		+ "2         PIN_FLD_DEVICE_OBJ     POID [0] 0.0.0.1 /device/num "+ msisdnPoid +" 1 \n"
		+ "2         PIN_FLD_FLAGS           INT [0] 1\n"
		+ "1     PIN_FLD_DEALS         ARRAY [0] allocated 20, used 14\n"
		+ "2         PIN_FLD_ACCOUNT_OBJ    POID [0] 0.0.0.1 /account 1 0\n"
		+ "2         PIN_FLD_DEAL_OBJ       POID [0] 0.0.0.1 /deal 2028144 0\n"
		+ "1     PIN_FLD_SERVICE_OBJ    POID [0] 0.0.0.1 /service/telco/gsm/telephony -1 0\n"
		+ "0 PIN_FLD_SERVICES      ARRAY [1] allocated 20, used 13\n"
		+ "1     PIN_FLD_PASSWD    STR [0] \" password\"\n" 
		+ "1     PIN_FLD_LOGIN           STR [0] \" telco"+ serviceId +"\"\n"
		+ "1     PIN_FLD_SUBSCRIPTION_OBJ   POID [0] NULL poid pointer\n"
		+ "1     PIN_FLD_SERVICE_ID      STR [0] \"gprsservice1"+ serviceId +"\"\n"
		+ "1     PIN_FLD_DEVICES       ARRAY [0] allocated 20, used 2\n"
		+ "2         PIN_FLD_DEVICE_OBJ     POID [0] 0.0.0.1 /device/num "+ msisdnPoid +" 1\n"
		+ "2         PIN_FLD_FLAGS           INT [0] 1\n"
		+ "1     PIN_FLD_DEALS         ARRAY [0] allocated 20, used 14\n"
		+ "2         PIN_FLD_ACCOUNT_OBJ    POID [0] 0.0.0.1 /account 1 0 \n"
		+ "2         PIN_FLD_DEAL_OBJ       POID [0] 0.0.0.1 /deal 2030192 0 \n"
		+ "1     PIN_FLD_SERVICE_OBJ    POID [0] 0.0.0.1 /service/telco/gprs -1 0 \n"
		+ "0 PIN_FLD_CODE            STR [0] \"8dd58fb0-c7ef-49b8-a56f-d078ba85599a\"\n"
		+ "0 PIN_FLD_NAMEINFO      ARRAY [1] allocated 20, used 10 \n"
		+ "1     PIN_FLD_LAST_NAME       STR [0] \"Account\"\n"
		+ "1     PIN_FLD_FIRST_NAME      STR [0] \"First\"\n"
		+ "1     PIN_FLD_SALUTATION      STR [0] \"Mr\"\n"
		+ "1     PIN_FLD_CONTACT_TYPE    STR [0] \"Account holder\"\n"
		+ "1     PIN_FLD_EMAIL_ADDR      STR [0] \"first.account@cvl.com\"\n"
		+ "1     PIN_FLD_COUNTRY         STR [0] \"IN\"\n"
		+ "1     PIN_FLD_ZIP             STR [0] \"123456\"\n"
		+ "1     PIN_FLD_STATE           STR [0] \"Karnataka\"\n"
		+ "1     PIN_FLD_CITY            STR [0] \"BLR\"\n"
		+ "1     PIN_FLD_ADDRESS         STR [0] \"test\"\n"
		+ "0 PIN_FLD_NAME            STR [0] \"test_prepaid_package\"\n"
		+ "0 PIN_FLD_DEAL_OBJ       POID [0] 0.0.0.0 / 0 0 \n"
		+ "0 PIN_FLD_ACCTINFO      ARRAY [0] allocated 20, used 4 \n"
		+ "1     PIN_FLD_POID           POID [0] 0.0.0.1 /account -1 0 \n"
		+ "1     PIN_FLD_BAL_INFO      ARRAY [0]     NULL array ptr \n"
		+ "1     PIN_FLD_CURRENCY        INT [0] 840 \n"
		+ "1     PIN_FLD_BUSINESS_TYPE   ENUM [0] 1 \n"
		+ "0 PIN_FLD_DESCR           STR [0] \"\"\n"
		+ "0 PIN_FLD_FLAGS           INT [0] 0 \n"
		+ "0 PIN_FLD_PAYINFO       ARRAY [0] allocated 20, used 6 \n"
		+ "1     PIN_FLD_INV_TYPE       ENUM [0] 257 \n"
		+ "1     PIN_FLD_NAME            STR [0] \" Invoice1\"\n"
		+ "1     PIN_FLD_POID           POID [0] 0.0.0.1 /payinfo/invoice -1 0 \n"
		+ "1     PIN_FLD_INHERITED_INFO SUBSTRUCT [0] allocated 20, used 1 \n"
		+ "2         PIN_FLD_INV_INFO      ARRAY [0] allocated 20, used 10 \n"
		+ "3             PIN_FLD_DELIVERY_PREFER   ENUM [0] 0 \n"
		+ "3             PIN_FLD_NAME            STR [0] \"First Account\"\n"
		+ "3             PIN_FLD_INV_TERMS      ENUM [0] 0 \n"
		+ "3             PIN_FLD_DELIVERY_DESCR    STR [0] \"first.account@cvl.com\"\n"
		+ "3             PIN_FLD_EMAIL_ADDR      STR [0] \" \"\n"
		+ "3             PIN_FLD_COUNTRY         STR [0] \"IN\"\n"
		+ "3             PIN_FLD_ZIP             STR [0] \"123456\"\n"
		+ "3             PIN_FLD_STATE           STR [0] \"Karnataka\"\n"
		+ "3             PIN_FLD_CITY            STR [0] \"BLR\"\n"
		+ "3             PIN_FLD_ADDRESS         STR [0] \"test\"\n"
		+ "1     PIN_FLD_FLAGS           INT [0] 1 \n"
		+ "1     PIN_FLD_PAY_TYPE       ENUM [0] 10001 \n"
		+ "0 PIN_FLD_BILLINFO      ARRAY [0] allocated 20, used 5 \n"
		+ "1     PIN_FLD_POID           POID [0] 0.0.0.1 /billinfo -1 0 \n"
		+ "1     PIN_FLD_BILLINFO_ID     STR [0] \"Bill Unit(1)\"\n"
		+ "1     PIN_FLD_PAYINFO       ARRAY [0]     NULL array ptr \n"
		+ "1     PIN_FLD_BAL_INFO      ARRAY [0]     NULL array ptr \n"
		+ "1     PIN_FLD_PAY_TYPE       ENUM [0] 10001 \n"
		+ "0 PIN_FLD_LOCALES       ARRAY [1] allocated 20, used 1 \n"
		+ "1     PIN_FLD_LOCALE          STR [0] \"en_US\"\n" ;
		
		FList inflist = FList.createFromString(inpFlistStr);
		
		logger.trace("\n commit_customer input: \n\n"+inflist.asString());
		outFlist = ctx.opcode(PortalOp.CUST_COMMIT_CUSTOMER, inflist);
		logger.debug("commit_customer output flist: \n"+outFlist.asString());
			} catch (EBufException ebufex) {
		logger.error("Error in commit_customer : \n"+ebufex.toString());
			}
		
		return outFlist;
		
	}

}
