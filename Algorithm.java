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




	// public Server bestFit(Job currJob){
	// 	int bestFit = Integer.MAX_VALUE;
	// 	int minAvail = Integer.MAX_VALUE;
	// 	Server best = null; 
	// 	Boolean found = false;

	// 	for(Server serv: servers){
	// 		if((serv.coreCount >= currJob.coreCount && serv.disk >= currJob.disk && serv.memory >= currJob.memory)){
	// 			int fitnessValue = serv.coreCount - currJob.coreCount;
	// 			if((fitnessValue < bestFit) || (fitnessValue == bestFit && serv.availableTime < minAvail)){
	// 				bestFit = fitnessValue;
	// 				minAvail = serv.availableTime;
	// 				if(serv.state == 0 || serv.state == 1 || serv.state == 2 || serv.state == 3){
	// 					found = true;
	// 					best = serv;
	// 				}
	// 			}
	// 		}
	// 	}
	// 	if(found) {
	// 		return best;
	// 	} else {
	// 		int bestFitOther = Integer.MAX_VALUE;
	// 		Server servAlt = null;
	// 		for (Server serv : XMLServers){
	// 			int fitnessValueOther = serv.coreCount - currJob.coreCount;
	// 			if(fitnessValueOther >= 0 && fitnessValueOther < bestFitOther && serv.disk > currJob.disk & serv.memory > currJob.memory) {
	// 				bestFitOther = fitnessValueOther;
	// 				servAlt = serv;
	// 			}
	// 		}
	// 		servAlt.id = 0;
	// 		return servAlt;
	// 	}


	// }
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
