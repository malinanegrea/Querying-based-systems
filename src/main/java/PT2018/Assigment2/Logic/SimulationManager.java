package PT2018.Assigment2.Logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import PT2018.Assigment2.GUI.Controler;
import PT2018.Assigment2.GUI.InitialisingFrame;
import PT2018.Assigment2.Model.SelectionPolicy;
import PT2018.Assigment2.Model.Server;
import PT2018.Assigment2.Model.Task;

public class SimulationManager implements Runnable {

	public int closeTime;
	private int openTime;
	public int maxProcessingTime;
	public int minProcessingTime;
	public int noOfServers;
	public int noOfClient;
	private Scheduler scheduler;
	private List<Task> generatedTask;
	private int maxTaskPerServer = 10;
	private int minTaskPerServer = 3;
	private int maxWaitingTimePerServer = 30;
	private int minWaitingTimePerServer = 3;
	private int minTimeBetweenCustomers;
	private int maxTimeBetweenCustomers;
	private int averegeService = 0;
	private int peak;
	private Controler c;

	public SimulationManager(int maxProcessingTime, int minProcessingTime, int noOfServers, int minTimeBetweenCustomers,
			int maxTimeBetweenCustomers, int openTime, int closeTime, SelectionPolicy selectionPolicy, int noOfClient,
			Controler c) {

		this.maxProcessingTime = maxProcessingTime;
		this.minProcessingTime = minProcessingTime;
		this.noOfServers = noOfServers;
		this.minTimeBetweenCustomers = minTimeBetweenCustomers;
		this.maxTimeBetweenCustomers = maxTimeBetweenCustomers;
		this.noOfClient = noOfClient;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.c = c;

		scheduler = new Scheduler(noOfServers, maxTaskPerServer, minTaskPerServer, maxWaitingTimePerServer,
				minWaitingTimePerServer, selectionPolicy, c);
		scheduler.getServer().get(0).openServer();
		c.setVisibleQueue(0);
		c.setMessage("Casa " + 0 + " se deschide");
		generatedTask = new ArrayList<Task>();
		generateNRandomTasks();

	}

	private void generateNRandomTasks() {
		Random rand = new Random();
		int arrivalTime;
		int processingTime;

		arrivalTime = openTime + 5;

		for (int i = 0; i < noOfClient; i++) {
			if (i > 0) {

				arrivalTime = arrivalTime + rand.nextInt(maxTimeBetweenCustomers) + minTimeBetweenCustomers;
				if (arrivalTime > closeTime)
					arrivalTime = closeTime;
			}

			processingTime = rand.nextInt(maxProcessingTime) + (minProcessingTime);

			Task t = new Task(arrivalTime, processingTime, i);
			generatedTask.add(t);
		}

	}

	@SuppressWarnings("deprecation")
	public void run() {
		int currentTime = openTime;
		int p = 0;
		int maxP = 0;

		while (currentTime < closeTime) {
			for (Task t : generatedTask) {

				if (t.getArrivalTime() == currentTime) {

					p = scheduler.dispatchTask(t);
					averegeService += t.getProcessingTime();
				}
			}

			if (p > maxP) {
				peak = currentTime;
			}

			currentTime++;
			try {
				Thread.sleep(180);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Iterator<Task> iterator = generatedTask.iterator(); iterator.hasNext();) {
				Task t = iterator.next();
				if (t.getArrivalTime() < currentTime) {
					iterator.remove();
				}
			}

			c.setTimer(currentTime / 60 + ":" + currentTime % 60);
			scheduler.closeServers();

		}

		for (Server s : scheduler.getServer()) {
			while (s.getTask().isEmpty() != true) {
				try {
					Thread.sleep(180);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (Thread t : scheduler.getThread()) {
			t.stop();
		}

		String a= "" + getAveregeWaiting() ;
		String pr = " " +getAveregeProcessing() ;
		String peakStr = peak/60 + ":" +peak%60;
		 c.setStatistic(a, pr, peakStr);
	}

	private double getAveregeWaiting() {
		return (1.0 * scheduler.averegeWaiting()) / noOfClient;
	}

	private double getAveregeProcessing() {
		return (1.0 * averegeService) / noOfClient;
	}

	
	public static void main(String[] args) {

		InitialisingFrame f = new InitialisingFrame();
		f.setVisible();
		Controler c = new Controler(f);
		c.setVisible();
		
	}
}
