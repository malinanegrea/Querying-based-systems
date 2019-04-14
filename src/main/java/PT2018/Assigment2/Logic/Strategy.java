package PT2018.Assigment2.Logic;
import java.util.List;

import PT2018.Assigment2.GUI.Controler;
import PT2018.Assigment2.Model.Server;
import PT2018.Assigment2.Model.Task;

public interface Strategy {

	public int addTask(List<Server> server, Task t, Controler c);
}
