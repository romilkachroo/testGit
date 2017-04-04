package com.cvl.brm.tf.utils;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.cvl.brm.tf.utils.BRMAssert;
import com.portal.pcm.EBufException;
import com.portal.pcm.FList;
import com.portal.pcm.Poid;

public class BRMAssert {

	protected final static Logger logger = LoggerFactory
			.getLogger(BRMAssert.class.getName());

	public FList flist;
	private Document xmlDocument;
	private XPath xPath;

	public BRMAssert(FList flist) throws IOException {
		super();
		this.flist = flist;
		try {
			this.xmlDocument = this.flist.toXMLDocument(); // xmlConverter.getXMLDoc();
			this.xPath = XPathFactory.newInstance().newXPath();
			logger.trace("BRM XML: " + this.flist.toXMLString());
			
			final String FILENAME = "C:\\Users\\Romil\\workspace\\CYTAjUnit\\InputFiles\\filename.xml";
			BufferedWriter bw = null;
			FileWriter fw = null;

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);

			bw.write(this.flist.toXMLString());

			if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();
				
		} catch (EBufException e) {
			e.printStackTrace();
		}
	}
	
	public BRMAssert(ResultSet resultSet) {
		super();
		this.xmlDocument = toDocument(resultSet);
		this.xPath = XPathFactory.newInstance().newXPath();
		if (logger.isTraceEnabled())
		{
			this.printXMLDoc();
		}
	}
	

	
	public static Document toDocument(ResultSet rs) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;

		try {
			builder = factory.newDocumentBuilder();
			doc = builder.newDocument();

			org.w3c.dom.Element results = doc.createElement("ResultSet");
			doc.appendChild(results);

			ResultSetMetaData rsmd;
			rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();

			int rowCnt=0;
			while (rs.next()) {
				org.w3c.dom.Element row = doc.createElement("Row");
				row.setAttribute("elem", Integer.valueOf(rowCnt++).toString());
				results.appendChild(row);

				for (int i = 1; i <= colCount; i++) {
					String columnName = rsmd.getColumnName(i);
					Object value = rs.getObject(i);
					if (value==null) {
						value="[null]";
					}
					org.w3c.dom.Element node = doc.createElement(columnName);
					node.appendChild(doc.createTextNode(value.toString()));
					row.appendChild(node);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return doc;
	}
	
	public void printXMLDoc() {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(this.xmlDocument), new StreamResult(writer));
			String output = writer.getBuffer().toString(); 
			logger.info("RS XML: " + output);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a String for the field specified by the XPATH.
	 * 
	 * @param xpath
	 * @return string value
	 */
	public String getFieldByXpath(String xpath) {
		String ret = null;
		try {
			ret = xPath.evaluate(xpath, xmlDocument.getDocumentElement());
		} catch (XPathExpressionException e1) {
			e1.printStackTrace();
		}
		return ret;
	}
	

	/**
	 * Assert that the value returned by the xpath search does not equal the
	 * expected value
	 * 
	 * @param message
	 * @param xpath
	 * @param expected
	 */
	public void assertNotEquals(java.lang.String message,
			java.lang.String xpath, java.lang.String expected) {
		try {
			String str = xPath
					.evaluate(xpath, xmlDocument.getDocumentElement());
			Assert.assertNotEquals(message, expected, str);
		} catch (XPathExpressionException e1) {
			e1.printStackTrace();
		}
	}
	
	public void assertEquals(java.lang.String message,
			java.lang.String xpath, java.lang.String expected) {
		try {
			String str = xPath
					.evaluate(xpath, xmlDocument.getDocumentElement());
			Assert.assertEquals(message, expected, str);
		} catch (XPathExpressionException e1) {
			e1.printStackTrace();
		}
	}
	


}
