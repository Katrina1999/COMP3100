// COMP3100 Assignment: Java program for a Client 
import java.net.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;
import javax.xml.parsers.*;
 
public class Client
{
    /*
    Initialize socket and input output streams
    Changed DataInputStream to BufferedReader due to deprecation in code
    BufferedReader is supposed to get info from the socket
    DataOutputStream is thus used to write to the socket */
   
    Socket socket            = null;
    BufferedReader  in       = null;
    DataOutputStream out     = null;
     
 
 
    // Took out the trash
 
    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("HI COMP3100, WE'RE GROUP 8 AND WELCOME TO OUR CRIB :)");
 
            // takes input from terminal
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // can also use a reader and call bufferedReader(inputStreamReader(Reader))
 
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
            System.out.println("IDK who dat");
        }
        catch(IOException i)
        {
            System.out.println("IO Exception. Quickly! Unplug and plug it back in!");
            System.out.println(i);
        }
 
        /*
        I saw this in the Diff logs, apart from Auth I've got no idea what the rest do
        Butt they seemed important so yea...
       
        */
        // HELO AUTH AND RCVD
        // string to read message from input
        String line = ""; // do something with this...
        // SENDING MESSAGES TO THE SERVER
        // SENDING MESSAGES TO THE SERVER
       
     
        // keep reading until "Over" is input
        // writeUTF is a bastard and has to go like fuck you of that!!
        // while (!line.equals("Over"))
        // {
        //     try
        //     {
        //         line = in.readLine();
        //         out.writeUTF(line);
        //     }
        //     catch(IOException i)
        //     {
        //      System.out.println(i);
        //      System.out.println("This IO Exception is when line!=Over which unlike your relationships is OVER :> ");
        //     }
        // }
 
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
   
    // The parse thingy to run the thingy
    // W5 Magic not happening sadly :(
    public void parse(){
        try{
            File sysXML = new File("system.xml");
 
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(sysXML);
 
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
   
    //I need to send messages to the server and back from the server oh shit.
 
    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 50000);
        parse();
    }
}
