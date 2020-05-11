//Worst-fit algorithm 

	public Server wf(Job currjob) {
		// Establish flags and fit variables to track fitness scores and servers.
		int wf = Integer.MIN_VALUE;
		int altFit = Integer.MIN_VALUE;
		Server worst = null;
		Server alt = null;
		Boolean worstFound = false;
		Boolean altFound = false;

		for (Server serv: servers) {
			if (serv.coreCount >= currjob.coreCount && serv.disk >= currjob.disk && serv.memory >= currjob.memory
					&& (serv.state == 0 || serv.state == 2 || serv.state == 3)) {
				int fitnessValue = serv.coreCount - job.coreCount;
				if (fitnessValue > wf && (serv.availableTime == -1 || serv.availableTime == currjob.bootupTime)) {
					wf = fitnessValue;
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
		Server forNow = null;
		for (Server serv: xmlServers) {
			int fit = serv.coreCount - job.coreCount;
			if (fit > lowest && serv.disk >= job.disk && serv.memory >= job.memory) {
				lowest = fit;
				current = serv;
			}
		}
		current.id = 0; // If not zero, server thinks it doesn't exist
		return current;
	}

