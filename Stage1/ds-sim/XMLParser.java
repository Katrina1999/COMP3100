import javax.xml.parsers.*;
import java.io.*;
import org.w3c.dom.*;
import java.util.*;

class XMLParser {

    public static void PrintElement(Element elmnt, String name){
        System.out.println(elmnt.getNodeName());
        NodeList nodes = elmnt.getElementsByTagName(name);
        PrintAttribute(nodes);
    }

    public static void PrintAttribute(NodeList nodes){
        for(int i = 0; i< nodes.getLength(); i++){
            Node child  = nodes.item(i);
            System.out.print("Node: " + child.getNodeName());
            if(child.getNodeType() == Node.ELEMENT_NODE){
                NamedNodeMap attribs = child.getAttributes();
                for(int y = 0; y < attribs.getLength(); y++){
                    Node temp = attribs.item(y);
                    System.out.print(" " + temp.getNodeName() + "=" + temp.getNodeValue());
                }
                System.out.println("");
            }
        }
    }

    public static void main(String[] args){
        try {
            File inputFile = new File("ds-config01.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            Element root  = doc.getDocumentElement();

            Element server_root = (Element)root.getElementsByTagName("servers").item(0);
            Element job_root = (Element)root.getElementsByTagName("jobs").item(0);
            NodeList workload = root.getElementsByTagName("workload");
            Element termination_root = (Element)root.getElementsByTagName("termination").item(0);

            PrintElement(server_root, "server");
            PrintElement(job_root, "job");
            PrintAttribute(workload);
            PrintElement(termination_root, "condition");

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
