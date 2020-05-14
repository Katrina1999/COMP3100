import java.util.ArrayList;
import Folder.Server; 
import Folder.Job; 

public class Algorithm {
	private ArrayList<Server> servers = new ArrayList<Server>();
	private Server[] XMLServers;

	Algorithm(ArrayList<Server> servers, Server[] XMLServers) {
		this.servers = servers;
		this.XMLServers = XMLServers;
	}


	//Worst-fit algorithm implemented by Sakura Mukhopadhyay
	public Server worstFit(Job currjob) {
		  // Defining variables  
		  int worstFit = Integer.MIN_VALUE;
		  int altFit = Integer.MIN_VALUE;
		  Server worst = null;
		  Server alt = null;
		  Boolean worstFound = false;
		  Boolean altFound = false;

		  for (Server serv: servers) { //for every server type in the order that it appears in the xml do the following
			    if (serv.coreCount >= currjob.coreCount && serv.disk >= currjob.disk && serv.memory >= currjob.memory
					&& (serv.state == 0 || serv.state == 2 || serv.state == 3)) {
				    int fitnessValue = serv.coreCount - currjob.coreCount;
				    if (fitnessValue > worstFit && (serv.availableTime == -1 || serv.availableTime == currjob.bootupTime)//if the server has more available resources than what is required to run job j then) {
					        worstFit = fitnessValue; // fitness value is calculated
					        worstFound = true;	//worstFit has been found as such it has been set to true
					        worst = serv;		////sets the worst server to the value of server
				         } else if (fitnessValue > altFit && serv.availableTime >= 0) {
					    altFit = fitnessValue;
					  altFound = true;
					alt = serv;
				}
			}
		}
    
		// Return the server most suitable or 'worst-fit' and with the highest fitnesss value 
		if (worstFound) { //if the worstFit is found then do the following:
			return worst;  //returns the server with the best 'worst-fit' value 
		} else if (altFound) { //if not found we return the next server available and do the following:
			return alt;
		}
		int lowest = Integer.MIN_VALUE; //sets the value of lowest to a very small number
		Server current = null;		//sets the current value of the current server to null 
		for (Server serv: XMLServers) {	//goes through the current server and compares against all the XML servers 
			int fit = serv.coreCount - currjob.coreCount; //sets the value of fitness value to that of the difference between the current server's coreCount and the current job's coreCount
			if (fit > lowest && serv.disk >= currjob.disk && serv.memory >= currjob.memory) { //it then compares if the fitness value is greater than the current job's memory, disk and coreCount size
				lowest = fit; //the highest fitness value is selected and we set the previous value of lowest to this
				current = serv; //the previous current server (alternative server) is now set to the current server
			}
		}
		current.id = 0; //sets the current id of the alternative(previously current) server to 0
		return current; //returns the current server (worstFit active server) based on highest resource capacity 
		}
	}
}
