package PT2018.Assigment2.Logic;

import java.util.List;

import PT2018.Assigment2.GUI.Controler;
import PT2018.Assigment2.Model.Server;
import PT2018.Assigment2.Model.Task;

public class ConcreteStrategyTime implements Strategy {

	public int addTask(List<Server> server, Task t, Controler c) {

		Server s = getMinTime(server);
		s.addTask(t);
		c.setMessage("Clientul " + t.getID() + " s-a asezat la casa " + s.getId());
		return s.getWaitingPeriod().intValue();

	}

	private static Server getMinTime(List<Server> server) {
		Server min = server.get(0);
		int minPeriod = min.getWaitingPeriod().intValue();
		for (Server s : server) {
			if (s.getWaitingPeriod().intValue() < minPeriod && s.getStatus() == true) {
				min = s;
				minPeriod = s.getWaitingPeriod().intValue();
				System.out.println(s.getId());
			}

		}

		return min;

	}

}
