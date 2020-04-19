// A Java program for a Client 
import java.net.*; 
import java.io.*; 
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;
import java.xml
  
public class Client 
{ 
	/* 
	Initialize socket and input output streams 
	Changed DataInputStream to BufferedReader due to deprecation in code
    BufferedReader is supposed to get info from the socket
	DataOutputStream is thus used to write to the socket */
    
    private Socket socket            = null; 
    private BufferedReader  in       = null; 
    private DataOutputStream out     = null; 


    // Print the element
    public static void PrintElement(Element elmnt, String name){
        System.out.println(elmnt.getNodeName());
        NodeList nodes = elmnt.getElementsByTagName(name);
        PrintAttribute(nodes);
    }

    //Print the attributes
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
  
    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("CONNECTED!!!!!"); 
  
            // takes input from terminal 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // can also use a reader and call bufferedReader(inputStreamReader(Reader))

            // sends output to the socket 
            out = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
        	System.out.println("There is an Unknown Host Exception"); 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
        	System.out.println("IO Exception. Quickly! Unplug and plug it back in!"); 
            System.out.println(i); 
        } 

        // string to read message from input 
        String line = ""; 
  
        // keep reading until "Over" is input 
        while (!line.equals("Over")) 
        { 
            try
            { 
                line = in.readLine(); 
                out.writeUTF(line); 
            } 
            catch(IOException i) 
            { 
            	System.out.println("This IO Exception is when line!=Over")
                System.out.println(i); 
            } 
        } 
  
        // close the connection 
        try
        { 
            in.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    }

    // Boilerplate for parser
    public void parse() {
    	try {
            File inputFile = new File("system.xml");

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

    // SECOND BOILERPLATE FOR PARSER
    /*
	public void parse(){
        try{
            File sysXML = new File(".system.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(systemXML);

            doc.getDocumentElement().normalize();
            NodeList servers = doc.getElementsByTagName("server");
            for(int i = 0; i<servers.getLength(); i++){
                System.out.println(i);
                Element server =(Element) servers.item(i);
                System.out.println("Type:"+(server.getAttribute("type")));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    */
    

    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 50000);
         
    } 
} 
