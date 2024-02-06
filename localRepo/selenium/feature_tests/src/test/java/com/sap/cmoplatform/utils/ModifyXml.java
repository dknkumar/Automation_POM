package com.sap.cmoplatform.utils;

import com.sap.cmoplatform.pages.genericReporting.genericReportingPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class ModifyXml {
    public static void modifyXml(String filename) {

        try {
            String filepath = System.getProperty("user.dir") + "/src/test/resources/testfiles/uploadProduct/" + filename;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);
            Random random = new Random();
            String id = "automationtest" + random.nextInt(900000) + random.nextInt(900000);
            String lot = "auto" + random.nextInt(90000);
//String disposition="auto"+random.nextInt(90000);


            doc.getElementsByTagName("sbdh:InstanceIdentifier").item(0).setTextContent(id);
            int count = doc.getElementsByTagName("LOTNO").getLength();
            String lots[] = new String[count];
            for (int i = 0; i < count; i++) {
                doc.getElementsByTagName("LOTNO").item(i).setTextContent(lot);
            }

            //  doc.getElementsByTagName("DATEX").item(0).setTextContent("123456");
            //   doc.getElementsByTagName("disposition").item(0).setTextContent("urn:epcglobal:cbv:disp:commissioning_auto_test11");

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);
            System.out.println("Done");

            String path = "src/test/resources/properties/uploadproductpackdata/UploadProductPackData.properties";
            FileOutputStream out = new FileOutputStream(path);
            FileInputStream in = new FileInputStream(path);
            Properties props = new Properties();
            props.load(in);
            in.close();
            props.setProperty("Identifier", id);
            props.setProperty("LOT", lot);

//            props.setProperty("ExpiryDate", "67567");

            props.store(out, null);
            out.close();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }

    public static void modifySFDAXml(String filename, String updatedFileName, String ReceiverID, String InstanceID, String ObjCode1, String ObjCode2, String manufDate, String expDate) throws Exception {
        //Build the doc from the XML file
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(filename));
        //Locate the node(s) with xpath
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodeReceiverID = (NodeList) xpath.evaluate("//ReceiverId[1]/text()[1]", doc, XPathConstants.NODESET);
        NodeList nodeObjCode1 = null;
        NodeList nodeObjCode2 = null;
        if (filename.contains("upload")) {
            nodeObjCode1 = (NodeList) xpath.evaluate("//*[@type='ObjHierarchy']/ObjContentGroup/ObjCode[1]/@id", doc, XPathConstants.NODESET);
            nodeObjCode2 = (NodeList) xpath.evaluate("//*[@type='ObjHierarchy']/ObjContentGroup/ObjCode[2]/@id", doc, XPathConstants.NODESET);
        }
        else {
            nodeObjCode1 = (NodeList) xpath.evaluate("//*[@type='SGTINList']/ObjContentGroup/ObjCode[1]/@id", doc, XPathConstants.NODESET);
            nodeObjCode2 = (NodeList) xpath.evaluate("//*[@type='SGTINList']/ObjContentGroup/ObjCode[2]/@id", doc, XPathConstants.NODESET);
        }
        NodeList dateManuf = (NodeList) xpath.evaluate("//@dateManuf", doc, XPathConstants.NODESET);
        NodeList dateExp = (NodeList) xpath.evaluate("//@dateExp", doc, XPathConstants.NODESET);
        NodeList nodeInstanceID = (NodeList) xpath.evaluate("//InstanceId[1]/text()[1]", doc, XPathConstants.NODESET);

        //Make the change on the selected nodes
        nodeReceiverID.item(0).setTextContent(ReceiverID);
        nodeObjCode1.item(0).setNodeValue(ObjCode1);
        nodeObjCode2.item(0).setNodeValue(ObjCode2);
        dateManuf.item(0).setNodeValue(manufDate);
        dateExp.item(0).setNodeValue(expDate);
        nodeInstanceID.item(0).setTextContent(InstanceID);

        //Save the result to a new XML doc
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(new DOMSource(doc), new StreamResult(new File(updatedFileName)));
    }

    public static void modifySFDAXml10obj(String filename, String updatedFileName, String ReceiverID, String InstanceID, String manufDate, String expDate) throws Exception {
        //Build the doc from the XML file
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(filename));
        //Locate the node(s) with xpath
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodeReceiverID = (NodeList) xpath.evaluate("//ReceiverId[1]/text()[1]", doc, XPathConstants.NODESET);
        NodeList dateManuf = (NodeList) xpath.evaluate("//@dateManuf", doc, XPathConstants.NODESET);
        NodeList dateExp = (NodeList) xpath.evaluate("//@dateExp", doc, XPathConstants.NODESET);
        NodeList nodeInstanceID = (NodeList) xpath.evaluate("//InstanceId[1]/text()[1]", doc, XPathConstants.NODESET);

        //Make the change on the selected nodes
        nodeReceiverID.item(0).setTextContent(ReceiverID);
        dateManuf.item(0).setNodeValue(manufDate);
        dateExp.item(0).setNodeValue(expDate);
        nodeInstanceID.item(0).setTextContent(InstanceID);
        //For loop to update multiple Object Codes
        String ObjCode;
        String generatedString;
        NodeList nodeObjCode;
        for (int i = 0; i < 10; i++) {
            nodeObjCode = (NodeList) xpath.evaluate("//*[@type='SGTINList']//*/ObjCode/@id", doc, XPathConstants.NODESET);
            generatedString = RandomStringUtils.randomAlphabetic(3).toUpperCase();
            ObjCode = "01" + genericReportingPage.configGenreicReporting.getProperty("GTINSFDA") + "21" + genericReportingPage.configGenreicReporting.getProperty("SFDAObjCode1") + generatedString;
            nodeObjCode.item(i).setNodeValue(ObjCode);
        }

        //Save the result to a new XML doc
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(new DOMSource(doc), new StreamResult(new File(updatedFileName)));
    }
    public static void modifySFDAXml50Kobj(String filename, String updatedFileName, String ReceiverID, String InstanceID, String manufDate, String expDate) throws Exception {
        //Build the doc from the XML file
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(filename));
        //Locate the node(s) with xpath
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodeReceiverID = (NodeList) xpath.evaluate("//ReceiverId[1]/text()[1]", doc, XPathConstants.NODESET);
        NodeList dateManuf = (NodeList) xpath.evaluate("//@dateManuf", doc, XPathConstants.NODESET);
        NodeList dateExp = (NodeList) xpath.evaluate("//@dateExp", doc, XPathConstants.NODESET);
        NodeList nodeInstanceID = (NodeList) xpath.evaluate("//InstanceId[1]/text()[1]", doc, XPathConstants.NODESET);

        //Make the change on the selected nodes
        nodeReceiverID.item(0).setTextContent(ReceiverID);
        dateManuf.item(0).setNodeValue(manufDate);
        dateExp.item(0).setNodeValue(expDate);
        nodeInstanceID.item(0).setTextContent(InstanceID);
        //For loop to update multiple Object Codes
        String ObjCode;
        String generatedString;
        NodeList nodeObjCode;
        for (int i = 0; i < 50000; i++) {
            nodeObjCode = (NodeList) xpath.evaluate("//*[@type='SGTINList']//*/ObjCode/@id", doc, XPathConstants.NODESET);
            generatedString = RandomStringUtils.randomAlphabetic(3).toUpperCase();
            ObjCode = "01" + genericReportingPage.configGenreicReporting.getProperty("GTINSFDA") + "21" + genericReportingPage.configGenreicReporting.getProperty("SFDAObjCode1") + generatedString;
            nodeObjCode.item(i).setNodeValue(ObjCode);
            System.out.println("Updated objocode " + i);
        }

        //Save the result to a new XML doc
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(new DOMSource(doc), new StreamResult(new File(updatedFileName)));
    }
}
