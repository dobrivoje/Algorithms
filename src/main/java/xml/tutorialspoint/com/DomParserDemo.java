package xml.tutorialspoint.com;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParserDemo {
    
    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException {
        
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        String resLocation = "xml/tutorialspoint/com/class.xml";
        File inputFile = new File(cl.getResource(resLocation).getFile());
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        
        doc.getDocumentElement().normalize();
        // root element
        System.out.println("root: " + doc.getDocumentElement().getNodeName());
        // "Stident" element
        NodeList nList = doc.getElementsByTagName("student");
        System.err.println("***********************************");
        System.err.println(nList.getLength());
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.err.println("Curr elem : " + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) nNode;
                System.err.println("Att rollno: " + e.getAttribute("rollno"));
                System.err.println("Att sex: " + e.getAttribute("sex"));
                
                System.err.println("First: " + e.getElementsByTagName("firstname").item(0).getTextContent());
                System.err.println("Last: " + e.getElementsByTagName("lastname").item(0).getTextContent());
                System.err.println("Nick: " + e.getElementsByTagName("nickname").item(0).getTextContent());
                System.err.println("Marks: " + e.getElementsByTagName("marks").item(0).getTextContent());

                System.err.println();
            }
        }
    }
}
