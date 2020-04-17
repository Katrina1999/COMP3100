// import java.net.*; 
// import java.io.*;
  
// public class Client 
// { 
//     // initialize socket and input output streams 
//     private Socket socket            = null; 
//     private BufferedReader buffInput = null;
//     private DataInputStream  input   = null; 
//     private DataOutputStream out     = null; 
  
//     // constructor to put ip address and port 
//     public Client(String address, int port) 
//     { 
//         // establish a connection 
//         try
//         { 
//             socket = new Socket(address, port); 
//             System.out.println("Connected to " + address); 
  
//             // takes input from terminal 
//             Reader reader = new InputStreamReader(System.in);
//             buffInput = new BufferedReader(reader);
//             // input  = new DataInputStream(System.in);
  
//             // sends output to the socket 
//             out    = new DataOutputStream(socket.getOutputStream()); 
//         } 
//         catch(UnknownHostException u) 
//         { 
//             System.out.println(u); 
//         } 
//         catch(IOException i) 
//         { 
//             System.out.println(i); 
//         } 
  
//         // string to read message from input 
//         String line = ""; 
  
//         // keep reading until "Over" is input 
//         while (!line.equals("Over")) 
//         { 
//             try
//             {
//                 line = buffInput.readLine();
//                 buffInput = new BufferedReader(new InputStreamReader(input));
//                 out.writeUTF(line); 
//             } 
//             catch(IOException i) 
//             { 
//                 System.out.println(i); 
//             }
//             catch(NullPointerException i) 
//             {
//                 System.out.println("Null Pointer Exception");
//                 System.out.println(i);
//             }
//         } 
  
//         // close the connection 
//         try
//         { 
//             buffInput.close(); 
//             out.close(); 
//             socket.close(); 
//         } 
//         catch(IOException i) 
//         { 
//             System.out.println(i); 
//         } 
//     } 
  
//     public static void main(String args[]) 
//     { 
//         Client client = new Client("127.0.0.1", 5000); 
//     } 
// } 

// A Java program for a Client 
import java.net.*; 
import java.io.*; 
  
public class Client 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
  
    BufferedReader brin = new BufferedReader(new InputStreamReader(new DataInputStream(System.in)));

    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
             
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = ""; 
  
        // keep reading until "Over" is input 
        while (!line.equals("Over")) 
        { 
            try
            { 
                line = brin.readLine();
                out.writeUTF(line); 
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        } 
  
        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 5000); 
    } 
} 

// import java.net.*;
// import java.io.*;
// import java.util.concurrent.TimeUnit;
// import org.w3c.dom.Element;

// //com.Server and Server.config are not java libraries but other areas of our assignment
// import com.Parser; 
// import com.Server; 
// import com.ServerConfig;
// //Above we have important all of the libraries we will use for this Client side dataset. 
// //Bellow is the entire data block for the Client class 

// public class Client {
// 	private Socket clientSocket;
// 	private OutputStream out;
// 	private BufferedReader in;
// 	private String ipAddress;
// 	private int port;

// 	public Client(final String ip, final int port){ 
// 		//This code sets the public Client class as the IP Address as well as port number of the Client side
//  		this.ipAddress = ip; 
// 		this.port = port;
// 	}

// 	public void startConnection() throws IOException {
// 		// Displays attempting connection as well as IP address anad port of target when attempting to make a connection with server side
// 		System.out.println("Attempting Connection: " + this.ipAddress + ":" + this.port);
// 		clientSocket = new Socket(this.ipAddress, this.port);
// 		out = clientSocket.getOutputStream(); //output stream
// 		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input stream
// 	}

// 	public String sendMessage(final String msg) throws IOException { //printing empty arrays fixed
// 		final char resp[] = new char[200];
// 		out.write(msg.getBytes());
// 		out.flush();
// 		int numChars = in.read(resp);
// 		return new String(resp, 0, numChars);
// 	}
// 	//This code block is used to stop connections.
// 	public void stopConnection() throws IOException {
// 		in.close();
// 		//input is closed
// 		out.close();
// 		//output is closed
// 		clientSocket.close();
// 		//clientsocket is closed
// 	}

// 	public String schedule_job(String jobID, Server server) throws IOException{
// 		String status = this.sendMessage("SCHD " + jobID + " " + server.getType() + " 0");
// 		return status;
// 	}

// 	public static void main(final String args[]) throws	IOException {

// 		final Client client = new Client("127.0.0.1", 50000);
// 		client.startConnection();

// 		ServerConfig serverConfig = new ServerConfig();

// 		//code bellow is relevant between creating the communication between client and server side
// 		String in_msg = "";
// 		in_msg = client.sendMessage("HELO");
// 		System.out.println(in_msg);
// 		in_msg = client.sendMessage("AUTH root");
// 		System.out.println(in_msg);
// 		if (in_msg.contains("OK")){
// 			//TimeUnit.SECONDS.sleep(10);
// 			Parser parser = new Parser("./ds-sim/system.xml");
// 			System.out.println("Parsing system.xml");
// 			Element server_root = (Element) parser.root.getElementsByTagName("servers").item(0);
// 			parser.convertAttribs(server_root.getElementsByTagName("server"), serverConfig);
// 		}
//     //Getting largest as specified
// 		System.out.println(serverConfig.getLargest().toString());

// 		in_msg = client.sendMessage("REDY");
// 		while (in_msg.contains("JOBN")) {
// 			final String[] res = in_msg.split(" ");
// 			String status = client.schedule_job(res[2], serverConfig.getLargest());
// 			System.out.println(status);
// 			in_msg = client.sendMessage("REDY");
// 		}
// 		System.out.println(in_msg);
// 		client.sendMessage("QUIT");
// 	}

// }
