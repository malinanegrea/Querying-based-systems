package PT2018.Assigment2.Logic;

import java.util.List;

import PT2018.Assigment2.GUI.Controler;
import PT2018.Assigment2.Model.Server;
import PT2018.Assigment2.Model.Task;

public class ConcreteStategyQueue implements Strategy{

	public int addTask(List<Server> server, Task t, Controler c) {
		
		Server s=getMinClient(server);
		s.addTask(t);
		c.setMessage("Clientul "+ t.getID() + " s-a asezat la casa " + s.getId());
		
		return s.getWaitingPeriod().intValue();
	}

	private static Server getMinClient(List<Server> server) {
		Server min = server.get(0);
		int minClients = min.size();
		for(Server s: server) {
			if(s.size() < minClients && s.getStatus() == true) {
				min = s;
				minClients = s.size();
			}
				
		}
		
		return min;
	}
	
	

}
