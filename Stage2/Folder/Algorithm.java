import java.util.ArrayList;

public class Algorithm {
	private ArrayList<Server> servers = new ArrayList<Server>();
	private Server[] XMLServers;

	Algorithm(ArrayList<Server> servers, Server[] XMLServers) {
		this.servers = servers;
		this.XMLServers = XMLServers;
	}

	public void firstFit(Server job) {
				int fitness = 0;
				int coreServer = 0;
				int coreJob = 0;

				// 1. Obtain server state information
				Server[] sortedServ = sortedType(xmlServers);

				/*
					The fitness value of a job to a server is defined as
					the difference between the number of cores the job requires and that in the server.		
				*/

				// For each server type i, s i , from the smallest to the largest
				for (Server serv : sortedServ) {
					// For each server j, s i,j of server type s i , from 0 to limit - 1 // j is server ID
					for (Server serv2 : servers) { 
						// If server s i,j has sufficient available resources to run job j i then
						if ((serv.type).equals(serv2.type)) {
							if (serv2.coreCount >= job.coreCores && serv2.disk >= job.disk && serv2.memory >= job.memory
									&& serv2.state != 4) {
								return serv2;
							}
						}
					}
				}
				// iterate through the whole arrayList of servers and find the next active server that can run the job.
				for (Server serv : xmlServers) {
					Server temp = null;
					if (serv.coreCount >= job.coreCores && serv.disk >= job.disk && serv.memory >= job.disk && serv.state != 4) {
						temp = serv;
						temp.id = 0; // If this isn't zero, server thinks it doesn't exist.
						return temp;
					}
				}
				return null;
			}

			//server type i, s i , from the smallest to the largest
			public Server[] sortedType(Server[] servArr) {
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