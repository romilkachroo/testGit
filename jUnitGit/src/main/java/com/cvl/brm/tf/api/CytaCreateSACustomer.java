package com.cvl.brm.tf.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cvl.brm.tf.api.CytaCreateSACustomer;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.PortalContext;
import com.portal.pcm.PortalOp;

public class CytaCreateSACustomer {

	protected final static Logger logger = LoggerFactory.getLogger(CytaCreateSACustomer.class
			.getName());
	
	public static FList createSACustomer(PortalContext ctx, String billinfoPoidStr, 
											String payinfoPoidStr, String accountPoidStr) throws EBufException {
		
		FList outFlist = null;
		
		String saAccountNo = "SA_FGT_TGT_MV_"+Long.toHexString(System.currentTimeMillis());
		logger.debug(saAccountNo);
		
		try {
		String inpFlistStr = "0 PIN_FLD_POID           POID [0] 0.0.0.1 /plan -1 0 \n"
				+ "0 PIN_FLD_FLAGS           INT [0] 0 \n"
				+ "0 PIN_FLD_BILLINFO      ARRAY [0] allocated 20, used 5 \n"
				+ "1     PIN_FLD_POID           POID [0] 0.0.0.1 /billinfo -1 0 \n"
				+ "1     PIN_FLD_BILLINFO_ID     STR [0] \""+ saAccountNo +"\" \n" 
				+ "1     PIN_FLD_AR_BILLINFO_OBJ   POID [0] "+ billinfoPoidStr +" \n"
				+ "1     PIN_FLD_PARENT_BILLINFO_OBJ   POID [0] "+ billinfoPoidStr +" \n"
				+ "1     PIN_FLD_PAY_TYPE       ENUM [0] 10007 \n"
				+ "0 PIN_FLD_PAYINFO       ARRAY [0] allocated 20, used 2 \n"
				+ "1     PIN_FLD_POID           POID [0] "+ payinfoPoidStr +" \n"
				+ "1     PIN_FLD_PAY_TYPE       ENUM [0] 10007 \n"
				+ "0 PIN_FLD_GROUP_INFO   SUBSTRUCT [0] allocated 20, used 1 \n"
				+ "1     PIN_FLD_PARENT         POID [0] "+ accountPoidStr +" \n"
				+ "0 PIN_FLD_ACCTINFO      ARRAY [0] allocated 20, used 6 \n"
				+ "1     PIN_FLD_POID           POID [0] 0.0.0.1 /account -1 0 \n"
				+ "1     PIN_FLD_ACCOUNT_NO      STR [0] \""+ saAccountNo +"\" \n"
				+ "1     PIN_FLD_BUSINESS_TYPE   ENUM [0] 1 \n"
				+ "1     PIN_FLD_CURRENCY        INT [0] 978 \n"
				+ "1     PIN_FLD_BAL_INFO      ARRAY [0]     NULL array ptr \n"
				+ "1     PIN_FLD_AAC_PACKAGE     STR [0] \"CYTA_PRODUCT_CATEGORY\" \n"
				+ "0 PIN_FLD_NAMEINFO      ARRAY [1] allocated 20, used 11 \n"
				+ "1     PIN_FLD_COMPANY         STR [0] \"NAMEINFO_SA\" \n"
				+ "1     PIN_FLD_ADDRESS         STR [0] \"NAMEINFO_SA_Address\" \n"
				+ "1     PIN_FLD_CITY            STR [0] \"NAMEINFO_SA_City\" \n"
				+ "1     PIN_FLD_COUNTRY         STR [0] \"GR\" \n"
				+ "1     PIN_FLD_EMAIL_ADDR      STR [0] \"sa@hotmail.com\" \n"
				+ "1     PIN_FLD_FIRST_NAME      STR [0] \"NAMEINFO_SA_FirstName\" \n"
				+ "1     PIN_FLD_LAST_NAME       STR [0] \"NAMEINFO_SA_LastName\" \n"
				+ "1     PIN_FLD_CONTACT_TYPE    STR [0] \"Service\" \n"
				+ "1     PIN_FLD_STATE           STR [0] \"NAMEINFO_SA_Athens\" \n"
				+ "1     PIN_FLD_ZIP             STR [0] \"14562\" \n"
				+ "1     PIN_FLD_TITLE           STR [0] \"3654\" \n"
				+ "0 PIN_FLD_BAL_INFO      ARRAY [0] allocated 20, used 3 \n"
				+ "1     PIN_FLD_POID           POID [0] 0.0.0.1 /balance_group -1 0 \n"
				+ "1     PIN_FLD_NAME            STR [0] \"Default BG - SA\" \n"
				+ "1     PIN_FLD_BILLINFO      ARRAY [0]     NULL array ptr \n"
				+ "0 PIN_FLD_PROFILES      ARRAY [0] allocated 20, used 2 \n"
				+ "1     PIN_FLD_PROFILE_OBJ    POID [0] 0.0.0.1 /profile/cyta_account -1 0 \n"
				+ "1     PIN_FLD_INHERITED_INFO SUBSTRUCT [0] allocated 20, used 5 \n"
				+ "2         PIN_FLD_PROFILE_INFO SUBSTRUCT [0] allocated 20, used 1 \n"
				+ "3             CYT_FLD_HEAD_NUMBER     STR [0] \"HEAD NUMBER\" \n"
				+ "2         CYTA_FLD_LLU_LIST     ARRAY [0] allocated 20, used 1 \n"
				+ "3             CYT_FLD_LLU_ID          STR [0] \"LLU ID_1\" \n"
				+ "2         CYTA_FLD_LLU_LIST     ARRAY [1] allocated 20, used 1 \n"
				+ "3             CYT_FLD_LLU_ID          STR [0] \"LLU ID_2\" \n"
				+ "2         CYTA_FLD_LLU_LIST     ARRAY [2] allocated 20, used 1 \n"
				+ "3             CYT_FLD_LLU_ID          STR [0] \"LLU ID_3\" \n"
				+ "2         CYTA_FLD_LLU_LIST     ARRAY [3] allocated 20, used 1 \n"
				+ "3             CYT_FLD_LLU_ID          STR [0] \"LLU ID_4\" \n"
				+ "0 PIN_FLD_PROFILES      ARRAY [1] allocated 20, used 2 \n"
				+ "1     PIN_FLD_PROFILE_OBJ    POID [0] 0.0.0.1 /profile/acct_extrating -1 0 \n"
				+ "1     PIN_FLD_INHERITED_INFO SUBSTRUCT [0] allocated 20, used 1 \n"
				+ "2         PIN_FLD_DATA_ARRAY    ARRAY [0] allocated 20, used 4 \n"
				+ "3             PIN_FLD_NAME            STR [0] \"loyalty\" \n"
				+ "3             PIN_FLD_VALUE           STR [0] \"gold\" \n"
				+ "3             PIN_FLD_VALID_FROM   TSTAMP [0] (0) <null> \n"
				+ "3             PIN_FLD_VALID_TO     TSTAMP [0] (0) <null> \n"; 
		
		FList inflist = FList.createFromString(inpFlistStr);
		
		logger.trace("\n commit_customer (SA) input: \n\n"+inflist.asString());
		outFlist = ctx.opcode(PortalOp.CUST_COMMIT_CUSTOMER, inflist);
		logger.debug("commit_customer (SA) output: \n"+outFlist.asString());
		logger.info("Account Number of SA : "+ saAccountNo);
			} catch (EBufException ebufex) {
		logger.error("Error in commit_customer : \n"+ebufex.toString());
			}
		
		return outFlist;
		
	}

}
