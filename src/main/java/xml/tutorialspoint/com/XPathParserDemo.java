package xml.tutorialspoint.com;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathParserDemo {

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

        ClassLoader clazz = ClassLoader.getSystemClassLoader();
        String resLocation = "xml/tutorialspoint/com/class.xml";
        File inputFile = new File(clazz.getResource(resLocation).getFile());

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/class/student[@rollno='493']";
        
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.err.println("Element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nNode;

                System.err.println("RollNo: " + e.getAttribute("rollno"));
                System.err.println("Sex: " + e.getAttribute("sex"));
                System.err.println("First: " + e.getElementsByTagName("firstname").item(0).getTextContent());
                System.err.println("Last: " + e.getElementsByTagName("lastname").item(0).getTextContent());
                System.err.println("Nick: " + e.getElementsByTagName("nickname").item(0).getTextContent());
                System.err.println("Marks: " + e.getElementsByTagName("marks").item(0).getTextContent());

                System.err.println();
            }
        }
    }
}
