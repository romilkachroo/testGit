package com.cvl.brm.tf.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientException;
import java.sql.Statement;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cvl.brm.tf.utils.BRMTestAbstract;

public class BRMUtils {
	
	protected final static Logger logger = LoggerFactory
			.getLogger(BRMTestAbstract.class.getName());
	
	/**
	 * Function to read Flist from File.
	 * @param path
	 * @return
	 * @throws IOException
	 */

	public static String readFlistFromFile(String path)
	        throws IOException
	{
		File inputFlistFile = new File(path);
	    FileInputStream fileInputStream = new FileInputStream(inputFlistFile);
	    byte[] buffer = new byte[fileInputStream.available()];
	    int length = fileInputStream.read(buffer);
	    fileInputStream.close();
	    String flistInStr = new String(buffer, 0, length, "UTF-8");
	    
	    return flistInStr;
	}

	public static String getMsisdn(Connection dbConn) throws SQLException {
		Statement statement = null;
		ResultSet rs = null;
		String msisdn = "0";
		try {
			statement = dbConn.createStatement();
			String sql = "SELECT " 
					+ " max(poid_id0) NUMPOID"
					+ " FROM "
					+ " device_t d "
					+ "WHERE state_id = 1";
		
			logger.debug("getMsisdn query:" + sql);
			rs = statement.executeQuery(sql);
		} catch (SQLNonTransientException e) {
			logger.error("getMsisdn failed");
			e.printStackTrace();
		}
		
		BRMAssert ass = new BRMAssert(rs);
		msisdn = ass.getFieldByXpath("//NUMPOID");
		
		return msisdn;
	}

	
	public static String getZone(Connection dbConn) throws SQLException {
		Statement statement = null;
		ResultSet rs = null;
		String msisdn = "0";
		try {
			statement = dbConn.createStatement();
			String sql = "SELECT " 
					+ " max(poid_id0) NUMPOID"
					+ " FROM "
					+ " device_t d "
					+ "WHERE state_id = 1";
		
			logger.debug("getMsisdn query:" + sql);
			rs = statement.executeQuery(sql);
		} catch (SQLNonTransientException e) {
			logger.error("getMsisdn failed");
			e.printStackTrace();
		}
		
		BRMAssert ass = new BRMAssert(rs);
		msisdn = ass.getFieldByXpath("//NUMPOID");
		
		return msisdn;
	}
}
