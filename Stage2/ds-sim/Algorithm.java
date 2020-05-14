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

	//Worst-fit algorithm implemeneted by Sakura Mukhopadhyay
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

}
