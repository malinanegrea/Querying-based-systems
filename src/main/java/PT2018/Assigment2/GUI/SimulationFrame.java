package PT2018.Assigment2.GUI;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SimulationFrame{

	JFrame frame;
	JTextField[] queue;
	JTextField[] waitingTime;
	JButton btnClose;
	JTextField timpAsteptare;
	JTextField timpProcesare;
	JTextField timpDeVarf;
	JTextField log;
	JTextField time;
	JLabel[] queueNr;	

	public SimulationFrame(int maxQueue) {

		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		frame.setLocationRelativeTo(null);

		JLabel statisticClLabel = new JLabel("Statistica");
		statisticClLabel.setBounds(10, 20, 150, 25);
		frame.getContentPane().add(statisticClLabel);

		JLabel timpAsteptareL =new JLabel("Timp mediu de astepare");
		timpAsteptareL.setBounds(10, 50, 150, 25);
		frame.getContentPane().add(timpAsteptareL);

		timpAsteptare = new JTextField();
		timpAsteptare.setBounds(10, 80, 150, 25);
		frame.getContentPane().add(timpAsteptare);
		timpAsteptare.setEditable(false);
		
		JLabel timpProcesareL =new JLabel("Timp mediu de procesare");
		timpProcesareL.setBounds(10, 110, 150, 25);
		frame.getContentPane().add(timpProcesareL);

		timpProcesare = new JTextField();
		timpProcesare.setBounds(10, 140, 150, 25);
		frame.getContentPane().add(timpProcesare);
		timpProcesare.setEditable(false);
		
		JLabel timpDeVarfL =new JLabel("Ora de varf");
		timpDeVarfL.setBounds(10, 170, 150, 25);
		frame.getContentPane().add(timpDeVarfL);

		timpDeVarf = new JTextField();
		timpDeVarf.setBounds(10, 200, 150, 25);
		frame.getContentPane().add(timpDeVarf);
		timpDeVarf.setEditable(false);

		JLabel queueLabel = new JLabel("Cozi");
		queueLabel.setBounds(170, 20, 150, 25);
		frame.getContentPane().add(queueLabel);

		int x = 170;
		int y = 50;
		queueNr = new JLabel[maxQueue];
		queue = new JTextField[maxQueue];
		waitingTime = new JTextField[maxQueue];
		for (int i = 0; i < maxQueue; i++) {
			waitingTime[i] = new JTextField();
			waitingTime[i].setBounds(x, y, 50, 25);
			waitingTime[i].setEditable(false);
			waitingTime[i].setVisible(false);
			
			queue[i] = new JTextField();
			queue[i].setBounds(x+60, y, 200, 25);
			queue[i].setEditable(false);
			queue[i].setVisible(false);

			queueNr[i] = new JLabel("Q " + i);
			queueNr[i].setBounds(x + 270, y, 50, 25);
			queueNr[i].setVisible(false);
			
			

			frame.getContentPane().add(queue[i]);
			frame.getContentPane().add(queueNr[i]);
			frame.getContentPane().add(waitingTime[i]);
			y += 35;

		}

		JLabel timeLabel = new JLabel("Time");
		timeLabel.setBounds(500, 20, 100, 25);
		frame.getContentPane().add(timeLabel);

		time = new JTextField();
		time.setBounds(500, 50, 100, 50);
		frame.getContentPane().add(time);
		time.setEditable(false);

		JLabel logLabel = new JLabel("Log");
		logLabel.setBounds(10, 250, 100, 25);
		frame.getContentPane().add(logLabel);

		log = new JTextField();
		log.setBounds(10, 280, 500, 25);
		log.setEditable(false);
		frame.getContentPane().add(log);

		btnClose = new JButton("Close");
		btnClose.setBounds(520, 280, 100, 25);
		frame.getContentPane().add(btnClose);

	}

	public void setVisible() {
		frame.setVisible(true);
	}

	public void setInvisible() {
		frame.setVisible(false);
	}

	public void setLog(String text) {
		log.setText(text);
	}

	public void setVisibilityQueue(int i, boolean state) {
		queue[i].setVisible(state);
		queueNr[i].setVisible(state);
		waitingTime[i].setVisible(state);
	}

	public void setWaitingTime(int i, int min) {
		waitingTime[i].setText(Integer.toString(min));
	}
	
	public void setQueueText(int i, String text) {
		queue[i].setText(text);
	}

	public void setTAsteptare(String text) {
		timpAsteptare.setText(text);
	}
	
	public void setTProcesare(String text) {
		timpProcesare.setText(text);
	}
	
	public void setPeak(String text) {
		timpDeVarf.setText(text);
	}

	public void setTime(String currentTime) {
		time.setText(currentTime);
	}

	public void addBtnCloseListener(ActionListener e) {
		btnClose.addActionListener(e);

	}

}
