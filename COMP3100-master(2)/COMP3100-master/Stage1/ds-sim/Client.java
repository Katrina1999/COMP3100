// A Java program for a Client 
import java.net.*; 
import java.io.*; 
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;

import Folder.Server; 
  
public class Client 
{ 
	/* 
	Initialize socket and input output streams 
	Changed DataInputStream to BufferedReader due to deprecation in code
    	BufferedReader is supposed to get info from the socket
	DataOutputStream is thus used to write to the socket
	Server[] sArr is supposed to help iterate through the values
	largeServer is as the nam suggest for largeServer meaning used to only print largeServer
	globalString helps with connection aspects
	*/
    
    Socket socket            = null; 
    BufferedReader  in       = null; 
    DataOutputStream out     = null;
    Server[] sArr = new Server[1];
	   int largeServer          = 0;
	   String globalString      ="";
   	Boolean end              = false;
  
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
            System.out.println("Unknown Host Exception");
        } 
        catch(IOException i) 
        { 
        	System.out.println("IO Exception. Quickly! Unplug and plug it back in!"); 
            System.out.println(i); 
        }
    }

	
	// To run client and set up connection
    public void run() {
		
	    
	    // Message being sent and received
		send("HELO");
		globalString = recv();
		send("AUTH " + System.getProperty("user.name"));
		globalString = recv();
		parse();
		send("REDY");
		globalString = recv();
		
		
		//For when the string is not equal to none
	    	//print out the SCHD...jobData
	    	//end the loop/algorithm if at anystage globalString = NONE
		if (!globalString.equals("NONE")) {
			while (!end) {
				if (globalString.equals("OK")) {
					send("REDY");
					globalString = recv();
				}
				if (globalString.equals("NONE")) {
					end = true;
					break;
				}
				String[] jobData = globalString.split("\\s+");
				int count = Integer.parseInt(jobData[2]);
				send("SCHD " + count + " " + sArr[largeServer].type + " " + "0");
				globalString = recv();
			}
		}
		quit();
	}

    // Send messages to socket
	public void send(String message) {
		//create Try-catch specifically for IOException
		//Essentially "sending" the message
		try {
			out.write(message.getBytes());
			out.flush();
		} catch (IOException i) {
			System.out.println("ERR: " + i);
		}
	}

	// Receive message from socket
	public String recv() {
		
		//Takes in a string
		String message = "";
		try {
			while (!in.ready()) { //do nothing if in is not ready
			} 
			while (in.ready()) { //if it is then return the read char.
				message += (char) in.read();
			}
			// System.out.print("RCVD: " + message);
			globalString = message;
		} catch (IOException i) {
			System.out.println("ERR: " + i);
		}
		return message;
	}

	/*
	Today I learnt that if you use quit, either as a method or in the send string argument, it wont work properly for some reason
	As to what the reason, I've got no clue.

	Still a cool reference for future uses
	*/
	public void quit() {
		//Essentially closes the program.
		try {
			send("QUIT");
			globalString = recv();
			if (globalString == "QUIT") {
				in.close();
				out.close();
				socket.close();
			}
		} catch (IOException i) {
			System.out.println("ERR: " + i);
		}
	}

    
    // Parser to read from XML Files
	public void parse(){
        try{
            File sysXML = new File("system.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(sysXML);

		
            doc.getDocumentElement().normalize();
			NodeList servers = doc.getElementsByTagName("server"); //creating a list of nodes for server
			sArr = new Server[servers.getLength()];
			for (int i = 0; i < servers.getLength(); i++) {	       //looking at each node and retaining information to print it
				Element server = (Element) servers.item(i);
				String t = server.getAttribute("type");
				int l = Integer.parseInt(server.getAttribute("limit"));
				int b = Integer.parseInt(server.getAttribute("bootupTime"));
				float r = Float.parseFloat(server.getAttribute("rate"));
				int c = Integer.parseInt(server.getAttribute("coreCount"));
				int m = Integer.parseInt(server.getAttribute("memory"));
				int d = Integer.parseInt(server.getAttribute("disk"));
				Server temp = new Server(i, t, l, b, r, c, m, d);	//initialising data server temp to contain all the nodes nes
				sArr[i] = temp;						//
				System.out.println(sArr[i].coreCount);			//printing out the number of cores
				//Server is a data structure from Folder
			}
			largeServer = largeServer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

    }

	// Parses but only ones where the server type is large.
    public int largeServer() {
		int largeServer = sArr[0].id;
		for (int i = 0; i < sArr.length; i++) {
			if (sArr[i].coreCount > sArr[largeServer].coreCount) {
				largeServer = sArr[i].id;
			}
		}
		return largeServer;
	}

    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 50000);
        client.run();
    } 
} 

