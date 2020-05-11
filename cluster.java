//Worst-fit algorithm 

	public Server worstFit(Job job) {
		// Establish flags and fit variables to track fitness scores and servers.
		int worstFit = Integer.MIN_VALUE;
		int altFit = Integer.MIN_VALUE;
		Server worst = null;
		Server alt = null;
		Boolean worstFound = false;
		Boolean altFound = false;

		for (sArr : servers) {
			if (serv.coreCount >= job.coreCount && serv.disk >= job.disk && serv.memory >= job.memory
					&& (serv.state == 0 || serv.state == 2 || serv.state == 3)) {
				int fitnessValue = serv.coreCount - job.coreCount;
				if (fitnessValue > worstFit && (serv.bootupTime == -1 || serv.bootupTime == job.bootupTime)) {
					worstFit = fitnessValue;
					worstFound = true;
					worst = serv;
				} else if (fitnessValue > altFit && serv.bootupTime >= 0) {
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
		Server forNow = null;
		for (sArr : xmlServers) {
			int fit = serv.coreCount - job.coreCount;
			if (fit > lowest && serv.disk >= job.disk && serv.memory >= job.memory) {
				lowest = fit;
				forNow = serv;
			}
		}
		forNow.id = 0; // If this isn't zero, server thinks it doesn't exist.
		return forNow;
	}
}
