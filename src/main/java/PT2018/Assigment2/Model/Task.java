package PT2018.Assigment2.Model;

public class Task {
	
	private int arrivalTime;
	private int processingTime;
	private int id;
	
	
	public Task(int arrivalTime, int processingTime, int id) {
		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
		this.id = id;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public int getProcessingTime() {
		return processingTime;
	}
	
	public int getID() {
		return id;
	}

}
