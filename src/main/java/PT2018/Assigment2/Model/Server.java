package PT2018.Assigment2.Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import PT2018.Assigment2.GUI.Controler;

public class Server implements Runnable {

	private BlockingQueue<Task> tasks;
	private AtomicInteger waitingPeriod;
	private boolean status;
	private int id;
	private Controler c;
	private String queue;
	
	public Server(int maxNoOfTask, int id, Controler c) {
		tasks = new ArrayBlockingQueue<Task>(maxNoOfTask);
		waitingPeriod = new AtomicInteger(0);
		this.c = c;
		// close
		status = false;
		this.id = id;
		queue = new String(" ");
	}

	public void addTask(Task newTask) {
		try {
		tasks.add(newTask);
		waitingPeriod.addAndGet(newTask.getProcessingTime());
		queue = queue + newTask.getID() + " ";
		c.setTextQueue(id, queue);
		}
		catch(Exception e) {
			
		}
	}

	public void run() {

		Task task;
		long minutes;
		
		while (tasks.isEmpty() == true ) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		while (tasks.isEmpty() == false || status == true) {
			if (tasks.isEmpty() == false) {
				task = tasks.peek();
				minutes = task.getProcessingTime();

				try {
					for (int i = 0; i < minutes; i++) {
						Thread.sleep(180);
						waitingPeriod.decrementAndGet();
						c.setWaitingTime(id, waitingPeriod.intValue());
					}
				} catch (InterruptedException e) {
					System.out.println("Thread interupted");
					e.printStackTrace();
				}

				c.setMessage("Clientul " + task.getID() + " a plecat de la casa " + id);
				String toRemove = task.getID() + " ";

				queue = queue.replace(toRemove, "");
				tasks.remove(task);
				c.setTextQueue(id, queue);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(queue);
			}
			while (tasks.isEmpty() == true ) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		System.out.println("End thread");
	}

	public BlockingQueue<Task> getTask() {
		return tasks;
	}

	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

	
	public int size() {

		return tasks.size();
	}

	public void openServer() {
		System.out.println("open server");
		status = true;
	}

	public void closeServer() {
		status = false;
	}

	public boolean getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

		

}
