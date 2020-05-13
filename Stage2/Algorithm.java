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



	//BestFit Algorithm implemented by Katrina David
	public Server bestFit(Job currJob){
		int bestFit = Integer.MAX_VALUE; //sets the value of bestFit to a very large number  
		int minAvail = Integer.MAX_VALUE; //sets the value of minAvail to a very large number  
		Server best = null; 
		Boolean found = false; //sets the value of found (indicates if bestFit has been found) to false

		for(Server serv: servers){ //for every server type in the order that it appears in the xml do the following
			if((serv.coreCount >= currJob.coreCount && serv.disk >= currJob.disk && serv.memory >= currJob.memory)){
				int fitnessValue = serv.coreCount - currJob.coreCount;
				if((fitnessValue < bestFit) || (fitnessValue == bestFit && serv.availableTime < minAvail)){ //if the server has sufficient available resources to run job j then 
					bestFit = fitnessValue; //calculates the fitnessValue
					minAvail = serv.availableTime; //sets the minimum time available to that of the available time of the server
					if(serv.state == 0 || serv.state == 1 || serv.state == 2 || serv.state == 3){
						found = true; //bestFit has been found as such it has been set to true 
						best = serv; //sets the best server to the value of server 
					}
				}
			}
		}
		if(found) { //if the bestFit is found then do the following 
			return best; //returns the server best because it has been found 
		} else {
			int bestFitOther = Integer.MAX_VALUE; //sets the value of bestFitOther to a very large number 
			Server servAlt = null; //sets the current value of the alternative server to null 
			for (Server serv : XMLServers){ //goes through the server against all the XML servers 
				int fitnessValueOther = serv.coreCount - currJob.coreCount; //sets the value of fitnessValueOther to that of the difference between the current server's coreCount and the current job's coreCount 
				if(fitnessValueOther >= 0 && fitnessValueOther < bestFitOther && serv.disk > currJob.disk & serv.memory > currJob.memory) {
					bestFitOther = fitnessValueOther; //sets the value of bestFitOther to equal the other fitnessValue found
					servAlt = serv; //sets the value of the alternative server to that of the current server 
				}
			}
			servAlt.id = 0; //sets the current id of the alternative server to 0 
			return servAlt; //returns the alternative server (bestFit active server) based on initial resource capacity 
		}


	}


}