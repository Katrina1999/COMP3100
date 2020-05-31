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


	/*
	*	Best-Fit Algorithm
	*/


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


	/*
	*	Worst-Fit Algorithm
	*/


	//Worst-fit algorithm implemeneted by Sakura Mukhopadhyay
	public Server worstFit(Job currjob) {
		// Defining the flags and variables which track servers and the fitness value
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
				if (fitnessValue > worstFit && (serv.availableTime == -1 || serv.availableTime == currjob.bootupTime)) { //if the server has more available resources than what is required to run job j then)
					worstFit = fitnessValue;  // fitness value is calculated
					worstFound = true;	 //worstFit has been found as such it has been set to true
					worst = serv;		//sets the worst server to the value of server
				} else if (fitnessValue > altFit && serv.availableTime >= 0) {
					altFit = fitnessValue;
					altFound = true;
					alt = serv;
				}
			}
		}
		// Return the server most suitable or 'worst-fit' and with the highest fitnesss value 
		if (worstFound) { 	//if the worstFit is found then do the following:
			return worst;	//returns the server with the best 'worst-fit' value 
		} else if (altFound) {	//if not found we return the next server available and do the following:
			return alt;
		}
		int lowest = Integer.MIN_VALUE;		//sets the value of lowest to a very small number
		Server current = null;			//sets the current value of the current server to null 
		for (Server serv: XMLServers) {		//goes through the current server and compares against all the XML servers 
			int fit = serv.coreCount - currjob.coreCount;	//sets the value of fitness value to that of the difference between the current server's coreCount and the current job's coreCount
			if (fit > lowest && serv.disk >= currjob.disk && serv.memory >= currjob.memory) { //it then compares if the fitness value is greater than the current job's memory, disk and coreCount size
				lowest = fit;		//the highest fitness value is selected and we set the previous value of lowest to this
				current = serv;		//the previous current server (alternative server) is now set to the current server
			}
		}
		current.id = 0; //sets the current id of the alternative(previously current) server to 0
		return current;	//returns the current server (worstFit active server) based on highest resource capacity 
	}

	/*
	*	First Fit Algorithm
	*/
	//First-fit algorithm implemeneted by Trideep Lal Das
	public Server firstFit(Job currjob) {

		// 1. Obtain server state information
		Server[] sortedServers = sortByID(XMLServers);

		/*
			The fitness value of a job to a server is defined as
			the difference between the number of cores the job requires and that in the server.
		*/

		// For each server type i, s i , from the smallest to the largest
		for (Server serv : sortedServers) {
			// For each server j, s i,j of server type s i , from 0 to limit - 1 // j is server ID
			for (Server serv2 : servers) {
				// If server s i,j has sufficient available resources to run job j i then
				if ((serv.type).equals(serv2.type)) {
					if (serv2.coreCount >= currjob.coreCount && serv2.disk >= currjob.disk && serv2.memory >= currjob.memory
							&& serv2.state != 4) {
						return serv2;
					}
				}
			}
		}
		// iterate through the whole arrayList of servers
		// and find the next active server that can run the job.
		for (Server serv : XMLServers) {
			Server temp = null;
			if (serv.coreCount >= currjob.coreCount && serv.disk >= currjob.disk && serv.memory >= currjob.disk && serv.state != 4) {
				temp = serv;
				temp.id = 0; // If this isn't zero, server thinks it doesn't exist.
				return temp;
			}
		}
		return null;
	}

	//server type i, s i , from the smallest to the largest
	public Server[] sortByID(Server[] servArr) {
		int n = servArr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (servArr[j].coreCount > servArr[j + 1].coreCount) {
					Server temp = servArr[j];
					servArr[j] = servArr[j + 1];
					servArr[j + 1] = temp;
				}
			}
		}
		return servArr;
	}

		//server type i, s i , from the smallest to the largest
	public Server[] sortByState(Server[] servArr) {
		int n = servArr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (stateRanking(servArr[j].state) > stateRanking(servArr[j + 1].state)) {
					Server temp = servArr[j];
					servArr[j] = servArr[j + 1];
					servArr[j + 1] = temp;
				}
			}
		}
		return servArr;
	}
	
	public int stateRanking(int state)
	{
		switch(state) {
		case 0:
			return 3;
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
			return 4;
		default:
            return 5;
		}
		
	}

	public Server smartFF(Job currjob) {

		// 1. Obtain server state information
		Server[] sortedServers = sortByState(XMLServers);

		/*
			The fitness value of a job to a server is defined as
			the difference between the number of cores the job requires and that in the server.
		*/

		// For each server type i, s i , from the smallest to the largest
		for (Server serv : sortedServers) {
			// For each server j, s i,j of server type s i , from 0 to limit - 1 // j is server ID
			for (Server serv2 : servers) {
				// If server s i,j has sufficient available resources to run job j i then
				if ((serv.type).equals(serv2.type)) {
					if (serv2.coreCount >= currjob.coreCount && serv2.disk >= currjob.disk && serv2.memory >= currjob.memory
							&& serv2.state != 4) {
						return serv2;
					}
				}
			}
		}
		// iterate through the whole arrayList of servers
		// and find the next active server that can run the job.
		for (Server serv : XMLServers) {
			Server temp = null;
			if (serv.coreCount >= currjob.coreCount && serv.disk >= currjob.disk && serv.memory >= currjob.disk && serv.state != 4) {
				temp = serv;
				temp.id = 0; // If this isn't zero, server thinks it doesn't exist.
				return temp;
			}
		}
		return null;
	}
}