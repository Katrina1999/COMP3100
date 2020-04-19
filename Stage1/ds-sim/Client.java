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
	DataOutputStream is thus used to write to the socket */
    
    Socket socket            = null; 
    BufferedReader  in       = null; 
    DataOutputStream out     = null;
    Server[] sArr = new Server[1];
	   int largeServer          = 0;
	   String globalString      ="";
   	Boolean end              = false;
     


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

        // HELO AUTH AND RCVD
        /*
        RCVD 
		ERR: invalid message ()!
		SENT ERR
		RCVD 
		ERR: invalid message ()!
		*/
        // string to read message from input 
    }

    public void run() {
		// CONNECTION SET-UP
		
		send("HELO");
		globalString = recv();
		send("AUTH " + System.getProperty("user.name"));
		globalString = recv();
		parse();
		send("REDY");
		globalString = recv();
		
		

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

    // SENDING MESSAGES TO THE SERVER
	public void send(String message) {
		try {
			// message += "\n";
			out.write(message.getBytes());
			// System.out.print("SENT: " + message);
			out.flush();
		} catch (IOException i) {
			System.out.println("ERR: " + i);
		}
	}

	// RECEIVING MESSAGES FROM THE SOCKET
	public String recv() {
		String message = "";
		try {
			while (!in.ready()) {
			} 
			while (in.ready()) {
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
			sArr = new Server[servers.getLength()];
			for (int i = 0; i < servers.getLength(); i++) {
				Element server = (Element) servers.item(i);
				String t = server.getAttribute("type");
				int l = Integer.parseInt(server.getAttribute("limit"));
				int b = Integer.parseInt(server.getAttribute("bootupTime"));
				float r = Float.parseFloat(server.getAttribute("rate"));
				int c = Integer.parseInt(server.getAttribute("coreCount"));
				int m = Integer.parseInt(server.getAttribute("memory"));
				int d = Integer.parseInt(server.getAttribute("disk"));
				Server temp = new Server(i, t, l, b, r, c, m, d);
				sArr[i] = temp;
				System.out.println(sArr[i].coreCount);
			}
			largeServer = largeServer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

    }

    public int largeServer() {
		int largeServer = sArr[0].id;
		for (int i = 0; i < sArr.length; i++) {
			if (sArr[i].coreCount > sArr[largeServer].coreCount) {
				largeServer = sArr[i].id;
			}
		}
		return largeServer;
	}

    
    //I need to send messages to the server and back from the server oh shit.

    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 50000);
        client.run();
    } 
} 

