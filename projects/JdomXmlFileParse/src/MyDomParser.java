import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

public class MyDomParser {

    public static void main(String[] args) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(en.xml);
    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    }
    }
}
