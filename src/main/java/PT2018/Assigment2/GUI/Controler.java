package PT2018.Assigment2.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PT2018.Assigment2.Logic.SimulationManager;

public class Controler {

	InitialisingFrame initFrame;
	SimulationFrame simFrame;
	SimulationManager simMan;

	public Controler(InitialisingFrame initFrame) {

		this.initFrame = initFrame;

		initFrame.addBtnStartListener(new StartActionListener());
		initFrame.addBtnCloseListener(new CloseInitActionListener());

	}

	public void setVisible() {
		initFrame.setVisible();
	}

	public void setMessage(String message) {
		simFrame.setLog(message);
	}

	public void setVisibleQueue(int i) {
		simFrame.setVisibilityQueue(i, true);
	}

	public void setTextQueue(int i, String text) {
		simFrame.setQueueText(i, text);
	}

	public void setInvivibleQueue(int i) {
		simFrame.setVisibilityQueue(i, false);
	}

	public void setTimer(String s) {
		simFrame.setTime(s);
	}

	public void setWaitingTime(int i, int min) {
		simFrame.setWaitingTime(i, min);
	}

	public void setStatistic(String a,String pr, String peak) {
		simFrame.setTAsteptare(a);
		simFrame.setTProcesare(pr);
		simFrame.setPeak(peak);
	}
	

	public class CloseInitActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			initFrame.setInvisible();

		}

	}

	public class CloseSimActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			simFrame.setInvisible();

		}

	}

	public class StartActionListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {

			initFrame.setInvisible();
			simFrame = new SimulationFrame(initFrame.getClients());
			simFrame.addBtnCloseListener(new CloseSimActionListener());
			simMan = new SimulationManager(initFrame.getMaxServiceTime(), initFrame.getMinServiceTime(),
					initFrame.getServers(), initFrame.getMinTimeBetween(), initFrame.getMaxTimeBetween(),
					initFrame.getOpenHour(), initFrame.getCloseHour(), initFrame.getPolicy(), initFrame.getClients(),
					Controler.this);
			Thread t = new Thread(simMan);
			t.start();
			simFrame.setVisible();

			
		}

	}

}
