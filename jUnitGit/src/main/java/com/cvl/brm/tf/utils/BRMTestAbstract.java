package com.cvl.brm.tf.utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cvl.brm.tf.utils.BRMTestAbstract;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;
import com.portal.pcm.PortalContext;
import com.portal.pcm.PortalOp;
import com.portal.pcm.fields.FldPoid;
import com.portal.pcm.fields.FldVirtualT;

public abstract class BRMTestAbstract {

	@Rule
	public TestName name = new TestName();

	/**
	 * Initiate the logger in Abstract class
	 */
	public static Logger logger = LoggerFactory.getLogger(BRMTestAbstract.class.getName());

	/**
	 * Initialize the env variables portal context is opened in the Abstract
	 * class
	 */
	public static String pinHome;
	static public PortalContext portalContext = null;
	static public PortalContext cmbPortalContext = null;
	public static String inputFlistPath;

	/**
	 * 
	 * BRM database connection variables
	 */
	static public Connection dbConn;
	static String dbuser;
	static String dbpass;
	static String dbhost;
	static String dbport;
	static String dbservicename;
	static CallableStatement callableStmt = null;
	static ResultSet rs = null;

	static Properties prop;

	@BeforeClass
	public static void connect() {
		logger.info("****In Before Class****");
		logger.info("Begin test suite: .....");

		/*
		 * Read values from Infranet.properties file
		 */

		prop = new Properties();
		String propFileName = "Infranet.properties";
		InputStream inputStream = BRMTestAbstract.class.getClassLoader().getResourceAsStream(propFileName);

		try {
			prop.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		/*
		 * Open BRM connection
		 */
		FList inFlist = null, outFlist = null;
		try {
			portalContext = new PortalContext();
			portalContext.connect();
			PortalContext.setAppName("BRMTest");
			logger.info("Connected to {}:{}", portalContext.getHost(),
			portalContext.getPort());
			Poid poid = new Poid(portalContext.getCurrentDB(), 1, "/account");
			inFlist = new FList();
			inFlist.set(FldPoid.getInst(), poid);
			outFlist = portalContext.opcode(PortalOp.GET_PIN_VIRTUAL_TIME,inFlist);
			logger.info("server time is {}",outFlist.get(FldVirtualT.getInst()));
			
		} catch (Exception e) {
			logger.error("Error connecting to BRM");
			fail("Error connecting to BRM");
			e.printStackTrace();
		}
		
	
		/*
		 * Open database connection
		 */
// TODO : Comment out the below code as db ports not open from VPN		
/*		dbuser = prop.getProperty("db.user");
		dbpass = prop.getProperty("db.pass");
		dbhost = prop.getProperty("db.host");
		dbport = prop.getProperty("db.port");
		dbservicename = prop.getProperty("db.servicename");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.info("Connecting to database...");
			logger.info("jdbc:oracle:thin:" + dbuser + "/" + dbpass + "@//"
					+ dbhost + ":" + dbport + "/" + dbservicename);
			dbConn = DriverManager.getConnection(
			"jdbc:oracle:thin:@//" + dbhost + ":" + dbport + "/"
					+ dbservicename, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fail("Error connecting to BRM DB");
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Error connecting to BRM DB");
		}*/
		
		
		/*
		 * get $PIN_HOME for the BRM instance
		 */
		pinHome = prop.getProperty("env.home");
		
		/*
		 * inputFlistPath to get the Path of the Input FLists.
		 */
		inputFlistPath = prop.getProperty("infranet.custom.inputflistpath");
	}

	@Before
	public void setup() {
		logger.info("****In Before*****");
		logger.info("Begin test: " + name.getMethodName());
	}

}
