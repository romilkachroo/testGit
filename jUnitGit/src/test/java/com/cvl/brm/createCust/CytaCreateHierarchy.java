package com.cvl.brm.createCust;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.cvl.brm.tf.api.CommitCustomer;
import com.cvl.brm.tf.api.CytaAddProdDiscounts;
import com.cvl.brm.tf.api.CytaCreateSACustomer;
import com.cvl.brm.tf.api.CytaOneOff;
import com.cvl.brm.tf.api.CytaWebHosting;
import com.cvl.brm.tf.utils.BRMTestAbstract;
import com.cvl.brm.tf.utils.BRMUtils;
import com.cvl.brm.tf.utils.BRMAssert;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.PortalOp;
import com.portal.pcm.fields.FldAccountNo;
import com.portal.pcm.fields.FldAcctinfo;
import com.portal.pcm.fields.FldArBillinfoObj;
import com.portal.pcm.fields.FldBillinfo;
import com.portal.pcm.fields.FldBillinfoId;
import com.portal.pcm.fields.FldGroupInfo;
import com.portal.pcm.fields.FldLastName;
import com.portal.pcm.fields.FldNameinfo;
import com.portal.pcm.fields.FldParentBillinfoObj;
import com.portal.pcm.fields.FldPoid;
import com.portal.pcm.fields.FldServices;
import com.portal.pcm.fields.FldSvcCode;

public class CytaCreateHierarchy extends BRMTestAbstract {

	@Test
	public void connectivityTest() {
		// fail("Not yet implemented");
		logger.debug("Check BRM & DB COnnectivity !!!");
	}

	/*
	 * Create Hierarchy with one Parent & One child
	 * Child has - voip , tv & ip service
	 * One time purchase product with Child
	 * */
	@Test
	public void cytaCreateHier() throws SQLException, EBufException, IOException {

		FList outFlist = null;

		String inFlistStr = BRMUtils.readFlistFromFile(inputFlistPath + "CytaBACustInput.txt");
		FList inFlist = FList.createFromString(inFlistStr);
		logger.trace("\n commit_customer input: \n\n" + inFlist.asString());

		String accountNumber = "BA_FGT_TGT_MV_" + Long.toHexString(System.currentTimeMillis());
		inFlist.getElement(FldAcctinfo.getInst(), 0).set(FldAccountNo.getInst(), accountNumber);
		inFlist.getElement(FldBillinfo.getInst(), 0).set(FldBillinfoId.getInst(), accountNumber);

		logger.trace("\n commit_customer input: \n\n" + inFlist.asString());
		try {
			outFlist = portalContext.opcode(PortalOp.CUST_COMMIT_CUSTOMER, inFlist);
			logger.debug("commit_customer output: \n" + outFlist.asString());
		} catch (EBufException ebufex) {
			logger.error("Error in commit_customer : \n" + ebufex.toString());
			fail("Error in commit_customer");
		}

		logger.info("Account Number of BA(parent) : " + accountNumber);

		BRMAssert custCommitAssert = new BRMAssert(outFlist);

		String accountPoidStr = custCommitAssert.getFieldByXpath("//ACCOUNT_OBJ");
		logger.info("Account Poid of BA(parent) : " + accountPoidStr);

		String billinfoPoidStr = custCommitAssert.getFieldByXpath("//BAL_INFO/BILLINFO_OBJ");
		logger.info("Billinfo Poid of BA(parent) : " + billinfoPoidStr);

		String payinfoPoidStr = custCommitAssert.getFieldByXpath("//BILLINFO/PAYINFO_OBJ");
		logger.info("Payinfo Poid of  BA(parent) : " + payinfoPoidStr);

		FList cytaCreateSAOutFlist = CytaCreateSACustomer.createSACustomer(portalContext, billinfoPoidStr, payinfoPoidStr,
				accountPoidStr);

		BRMAssert cytaCreateSAAssert = new BRMAssert(cytaCreateSAOutFlist);
		
		String accountPoidSAStr = cytaCreateSAAssert.getFieldByXpath("//ACCOUNT_OBJ");
		logger.info("Account Poid of BA : "+ accountPoidSAStr);
		
		FList cytaAddProdDisctOutFlist = CytaAddProdDiscounts.addProdDisctSACustomer(portalContext, accountPoidSAStr);

		FList cytaOneOffProdOutFlist = CytaOneOff.oneOffSACustomer(portalContext, accountPoidSAStr);

	}

	
	/*
	 * Create Hierarchy with one Parent & One child
	 * Child has - web hosting service
	 * */	
	@Test
	public void cytaCreateHierWithWebHosting() throws SQLException, EBufException, IOException {

		FList outFlist = null;

		String inFlistStr = BRMUtils.readFlistFromFile(inputFlistPath + "CytaBACustInput.txt");
		FList inFlist = FList.createFromString(inFlistStr);
		logger.trace("\n commit_customer input: \n\n" + inFlist.asString());

		String accountNumber = "BA_FGT_TGT_MV_" + Long.toHexString(System.currentTimeMillis());
		inFlist.getElement(FldAcctinfo.getInst(), 0).set(FldAccountNo.getInst(), accountNumber);
		inFlist.getElement(FldBillinfo.getInst(), 0).set(FldBillinfoId.getInst(), accountNumber);

		logger.trace("\n commit_customer input: \n\n" + inFlist.asString());
		try {
			outFlist = portalContext.opcode(PortalOp.CUST_COMMIT_CUSTOMER, inFlist);
			logger.debug("commit_customer output: \n" + outFlist.asString());
		} catch (EBufException ebufex) {
			logger.error("Error in commit_customer : \n" + ebufex.toString());
			fail("Error in commit_customer");
		}

		logger.info("Account Number of BA(parent) : " + accountNumber);

		BRMAssert custCommitAssert = new BRMAssert(outFlist);

		String accountPoidStr = custCommitAssert.getFieldByXpath("//ACCOUNT_OBJ");
		logger.info("Account Poid of BA(parent) : " + accountPoidStr);

		String billinfoPoidStr = custCommitAssert.getFieldByXpath("//BAL_INFO/BILLINFO_OBJ");
		logger.info("Billinfo Poid of BA(parent) : " + billinfoPoidStr);

		String payinfoPoidStr = custCommitAssert.getFieldByXpath("//BILLINFO/PAYINFO_OBJ");
		logger.info("Payinfo Poid of  BA(parent) : " + payinfoPoidStr);

		FList cytaCreateSAOutFlist = CytaCreateSACustomer.createSACustomer(portalContext, billinfoPoidStr, payinfoPoidStr,
				accountPoidStr);

		BRMAssert cytaCreateSAAssert = new BRMAssert(cytaCreateSAOutFlist);
		
		String accountPoidSAStr = cytaCreateSAAssert.getFieldByXpath("//ACCOUNT_OBJ");
		logger.info("Account Poid of BA : "+ accountPoidSAStr);
		
		FList cytaWebHostingProdOutFlist = CytaWebHosting.addWebHosting(portalContext, accountPoidSAStr);
		
	}

}
