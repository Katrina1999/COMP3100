// A Java program for a Client
import java.net.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;

import Folder.Server;
import Folder.Job;

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
    Server[] sArr 			 = new Server[1];
	private ArrayList<Server> serverArrList = new ArrayList<Server>();
	int largeServer          = 0;
	String globalString      ="";
   	Boolean end              = false;
   	private String algorithmType = "ff";

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

		send("HELO");
		globalString = recv();
		send("AUTH " + System.getProperty("user.name"));
		globalString = recv();
		parse();
		send("REDY");
		globalString = recv();

        System.out.println(serverArrList);

		if (!globalString.equals("NONE")) {
			while (!end) {
				// end variable is changed when we receive "NONE"
				if (globalString == "OK") {
					send("REDY");
					globalString = recv();
					// this will be the job information
				}
				if (globalString == "NONE") {
					end = true; // time to go...
					break;
				}

				// need to parse the job here
				String[] jobString = globalString.split("\\s+");
				// break the job information up so we can create obj
				Job job = new Job(0, jobString[0], Integer.parseInt(jobString[1]),
				Integer.parseInt(jobString[2]), Float.parseFloat(jobString[3]), Integer.parseInt(jobString[4]),
				Integer.parseInt(jobString[5]), Integer.parseInt(jobString[6]));

				send("RESC All");
        		globalString = recv();
        		send("OK");

        		globalString = recv();
        		while (!globalString.equals(".")) {
        		// we know the server has stopped sending information when we get "."
				// therefore, we'll keeping reading information in and adding array til then

			//Add server information from string to serverArrList so algorithm performs on all the info
            	String[] serverInfo = globalString.split("\\s+");
            	serverArrList.add(new Server(0,
                	serverInfo[0],
                	Integer.parseInt(serverInfo[1]),
                	Integer.parseInt(serverInfo[2]),
                	Float.parseFloat(serverInfo[3]),
                	Integer.parseInt(serverInfo[4]),
                	Integer.parseInt(serverInfo[5]),
                	Integer.parseInt(serverInfo[6])
            	));
            	System.out.println("ADDED SERVER");
            	send("OK");
            	globalString = recv();
        		}
				Algorithm baselineAlgorithms = new Algorithm(serverArrList, sArr);

				Server sendTo = null;

				if (algorithmType.equals("bf")) {
				        sendTo = baselineAlgorithms.bestFit(job);
				        send("SCHD " + job.id + " " + sendTo.type + " " + sendTo.id);
				} else if (algorithmType.equals("ff")) {
				        sendTo = baselineAlgorithms.firstFit(job);
				        send("SCHD " + job.id + " " + sendTo.type + " " + sendTo.id);
				} else if (algorithmType.equals("wf")) {
				        sendTo = baselineAlgorithms.worstFit(job);
				        send("SCHD " + job.id + " " + sendTo.type + " " + sendTo.id);
				} else {
				        String[] jobData = globalString.split("\\s+");
						int count = Integer.parseInt(jobData[2]);
				        send("SCHD " + count + " " + sArr[largeServer].type + " " + "0");
				}

				globalString = recv();
			}
		}
		quit();
	}

    // Send messages to socket
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

	// Receive message from socket
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


    // Parser to read from XML Files
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
				serverArrList.add(temp);
				System.out.println(sArr[i].coreCount);
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
        if (args.length == 2) {
			if (args[0].equals("-a")) {
				if (args[1].equals("bf")) {
					client.algorithmType = "bf";
				} else if (args[1].equals("wf")) {
					client.algorithmType = "wf";
				} else if (args[1].equals("ff")) {
					client.algorithmType = "ff";
				}
			}
		}
        client.run();
    }
}
