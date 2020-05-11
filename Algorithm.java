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

	//Worst-fit algorithm 

	public Server worstFit(Job currjob) {
		// Establish flags and fit variables to track fitness scores and servers.
		int worstFit = Integer.MIN_VALUE;
		int altFit = Integer.MIN_VALUE;
		Server worst = null;
		Server alt = null;
		Boolean worstFound = false;
		Boolean altFound = false;

		for (Server serv: servers) {
			if (serv.coreCount >= currjob.coreCount && serv.disk >= currjob.disk && serv.memory >= currjob.memory
					&& (serv.state == 0 || serv.state == 2 || serv.state == 3)) {
				int fitnessValue = serv.coreCount - currjob.coreCount;
				if (fitnessValue > worstFit && (serv.availableTime == -1 || serv.availableTime == currjob.bootupTime)) {
					worstFit = fitnessValue;
					worstFound = true;
					worst = serv;
				} else if (fitnessValue > altFit && serv.availableTime >= 0) {
					altFit = fitnessValue;
					altFound = true;
					alt = serv;
				}
			}
		}
		// Return the server most suitable 
		if (worstFound) {
			return worst;
		} else if (altFound) {
			return alt;
		}
		int lowest = Integer.MIN_VALUE;
		Server current = null;
		for (Server serv: XMLServers) {
			int fit = serv.coreCount - currjob.coreCount;
			if (fit > lowest && serv.disk >= currjob.disk && serv.memory >= currjob.memory) {
				lowest = fit;
				current = serv;
			}
		}
		current.id = 0; // If not zero, server thinks it doesn't exist
		return current;
	}
}
