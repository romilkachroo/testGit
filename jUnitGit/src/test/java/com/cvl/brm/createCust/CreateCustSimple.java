package com.cvl.brm.createCust;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.cvl.brm.tf.api.CommitCustomer;
import com.cvl.brm.tf.utils.BRMTestAbstract;
import com.cvl.brm.tf.utils.BRMUtils;
import com.cvl.brm.tf.utils.BRMAssert;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.PortalOp;
import com.portal.pcm.fields.FldAccountNo;
import com.portal.pcm.fields.FldLastName;
import com.portal.pcm.fields.FldNameinfo;
import com.portal.pcm.fields.FldServices;
import com.portal.pcm.fields.FldSvcCode;


public class CreateCustSimple extends BRMTestAbstract {	
	
	@Test
	public void connectivityTest() {
		//fail("Not yet implemented");
		logger.debug("Check BRM & DB COnnectivity !!!");
	}
	
	
	@Test
	public void createCustomer() throws SQLException, EBufException, IOException {
		
		String newMsisdn = BRMUtils.getMsisdn(dbConn);
		logger.debug("MSISDN poid = " + newMsisdn);
		
		FList custCommitOutputFlist = CommitCustomer.createCustomer(portalContext, newMsisdn);
		BRMAssert custCommitAssert = new BRMAssert(custCommitOutputFlist);
		custCommitAssert.assertNotEquals( "Check Account Poid","//Row [@elem='0']/ACCOUNT_OBJ", "0");
	}
	
	
	@Test
	public void createCustomerFromFile() throws SQLException, EBufException, IOException {
		
		String newMsisdn = BRMUtils.getMsisdn(dbConn);
		logger.debug("MSISDN poid = " + newMsisdn);
		
		String inFlistStr = BRMUtils.readFlistFromFile(inputFlistPath+"CustCommitInput.txt");
		FList inFlist = FList.createFromString(inFlistStr);
		logger.trace("\n commit_customer input: \n\n"+inFlist.asString());
		
	//	inFlist.set(FldAccountNo.getInst(),dummyAccountNumber); 
		inFlist.getElement(FldNameinfo.getInst(), 1).set(FldLastName.getInst(), "New_Last_Name");
		
		logger.trace("\n commit_customer input: \n\n"+inFlist.asString());
		 try { 
			 FList outFlist = portalContext.opcode(PortalOp.CUST_COMMIT_CUSTOMER,inFlist);
			 logger.debug("commit_customer output: \n"+outFlist.asString());
		 } catch (EBufException ebufex) {
				logger.error("Error in commit_customer : \n"+ebufex.toString());
				fail("Error in commit_customer");
			}
	
	}
	
	@Test
	public void zoneToXML() throws SQLException, EBufException, IOException {
		
		String inFlistStr = BRMUtils.readFlistFromFile(inputFlistPath+"zone_test.txt");
		FList inFlist = FList.createFromString(inFlistStr);
		logger.trace("\n commit_customer input: \n\n"+inFlist.asString());
		
		BRMAssert custCommitAssert = new BRMAssert(inFlist);
	}

}
