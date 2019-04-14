package PT2018.Assigment2.Logic;

import java.util.ArrayList;
import java.util.List;

import PT2018.Assigment2.GUI.Controler;
import PT2018.Assigment2.Model.SelectionPolicy;
import PT2018.Assigment2.Model.Server;
import PT2018.Assigment2.Model.Task;

public class Scheduler {

	private List<Server> servers;
	private int maxNoServers;
	private int maxTaskPerServer;
	private int minTaskPerServer;
	private int maxWaitingTimePerServer;
	private int minWaitingTimePerServer;
	private Strategy strategy;
	private SelectionPolicy policy;
	private List<Thread> threads;
	private Controler c;
	private int averegeWaiting = 0;

	public Scheduler(int maxNoServer, int maxTaskPerServer, int minTaskPerServer, int maxWaitingTimePerServer,
			int minWaitingTimePerServer, SelectionPolicy policy, Controler c) {
		this.maxNoServers = maxNoServer;
		this.maxTaskPerServer = maxTaskPerServer;
		this.minTaskPerServer = minTaskPerServer;
		this.maxWaitingTimePerServer = maxWaitingTimePerServer;
		this.minWaitingTimePerServer = minWaitingTimePerServer;
		this.policy = policy;
		this.c = c;

		if (policy == SelectionPolicy.SHORTES_QUEUE)
			this.strategy = new ConcreteStategyQueue();
		else
			this.strategy = new ConcreteStrategyTime();

		servers = new ArrayList<Server>();
		threads = new ArrayList<Thread>();

		for (int i = 0; i < maxNoServers; i++) {
			Server s = new Server(maxTaskPerServer, i, c);
			Thread t = new Thread(s);
			servers.add(s);
			threads.add(t);
			t.start();
			servers.get(i).closeServer();
		}

	}


	public int dispatchTask(Task t) {

		boolean full = true;
		System.out.println(t.getID() + " " + t.getProcessingTime());

		for (Server s : servers) {
			// System.out.println(s.getWaitingPeriod().intValue() + t.getProcessingTime());
			if ((s.size() < maxTaskPerServer - 1
					&& s.getWaitingPeriod().intValue() + t.getProcessingTime() < maxWaitingTimePerServer)
					&& s.getStatus() == true) {
				full = false;
			}

		}

		if (full) {
			int size = servers.size();
			for (int i = 0; i < size; i++) {
				if (servers.get(i).getStatus() == false) {
					c.setMessage("Casa " + i + " se deschide");
					c.setVisibleQueue(i);
					servers.get(i).addTask(t);
					servers.get(i).openServer();
					i = servers.size();

					// System.out.println("Resume");
				}
			}
			return 0;

		} else {

			int i = strategy.addTask(servers, t, c);
			averegeWaiting += i;
			return i;
		
		}
		
	}

	public void closeServers() {
		for (Server s : servers) {
			if (s.getId() != 0 && s.getStatus() == true) {
				if (policy == SelectionPolicy.SHORTES_QUEUE) {
					if (s.size() < minTaskPerServer) {
						s.closeServer();
						c.setMessage("Casa " + s.getId() + " se inchide ");
					}
				} else if (s.size() < minWaitingTimePerServer) {
					s.closeServer();
				}
				;

			}
		}
	}

	public List<Server> getServer() {
		return servers;
	}

	public int getMaxNoServers() {
		return maxNoServers;
	}

	public int getMaxmaxTaskPerServer() {
		return maxTaskPerServer;
	}

	public Thread getThread(int i) {
		return threads.get(i);
	}

	public int averegeWaiting() {
		return averegeWaiting;
	}

	public List<Thread> getThread() {
		return threads;

	}

}
