package PT2018.Assigment2.GUI;

import java.awt.event.ActionListener;

import javax.swing.*;

import PT2018.Assigment2.Model.SelectionPolicy;

public class InitialisingFrame {

	JFrame frame;
	JTextField maxClients;
	JTextField maxServers;
	JTextField openHour;
	JTextField openMin;
	JTextField closeHour;
	JTextField closeMin;
	JTextField minServiceTime;
	JTextField maxServiceTime;
	JTextField minTimeBetween;
	JTextField maxTimeBetween;
	JRadioButton shortestQueue;
	JRadioButton shortestTime;
	JButton btnStart;
	JButton btnClose;
	
	public InitialisingFrame() {
		
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 300);
		frame.setLocationRelativeTo(null);

		JLabel p1 = new JLabel("Numarul clienti");
		p1.setBounds(10, 10, 120, 25);
		frame.getContentPane().add(p1);

		JLabel p2 = new JLabel("Numarul cozi");
		p2.setBounds(10, 40, 120, 25);
		frame.getContentPane().add(p2);
		
		JLabel p3 = new JLabel("Ora deschidere");
		p3.setBounds(10, 70, 120, 25);
		frame.getContentPane().add(p3);
		
		JLabel p4 = new JLabel("Ora inchidere");
		p4.setBounds(10, 100, 120, 25);
		frame.getContentPane().add(p4);
		
		JLabel p5 = new JLabel("Timp procesare minim");
		p5.setBounds(310, 10, 200, 25);
		frame.getContentPane().add(p5);
		
		JLabel p6 = new JLabel("Timp procesare maxim");
		p6.setBounds(310, 40, 200, 25);
		frame.getContentPane().add(p6);
		
		JLabel p7 = new JLabel("Timp minim de sosire intre 2 clienti");
		p7.setBounds(310, 70, 200, 25);
		frame.getContentPane().add(p7);
		
		JLabel p8 = new JLabel("Timp minim de sosire intre 2 clienti");
		p8.setBounds(310, 100, 200, 25);
		frame.getContentPane().add(p8);
		
		JLabel p9 = new JLabel("Politica de asezare la cozi");
		p9.setBounds(10, 140, 150, 25);
		frame.getContentPane().add(p9);
		
		shortestQueue = new JRadioButton("Cea mai scurta coada");
		shortestQueue.setBounds(170, 140, 150, 25);
		frame.getContentPane().add(shortestQueue);
		
		shortestTime = new JRadioButton("Cel mai scurt timp");
		shortestTime.setBounds(330, 140, 150, 25);
		frame.getContentPane().add(shortestTime);
		
		ButtonGroup group = new ButtonGroup();
		group.add(shortestQueue);
		group.add(shortestTime);

		maxClients = new JTextField();
		maxClients.setBounds(140, 10, 150, 25);
		frame.getContentPane().add(maxClients);
		
		maxServers = new JTextField();
		maxServers.setBounds(140, 40, 150, 25);
		frame.getContentPane().add(maxServers);
		
		openHour = new JTextField();
		openHour.setBounds(140, 70, 70, 25);
		frame.getContentPane().add(openHour);
		
		openMin = new JTextField();
		openMin.setBounds(220, 70, 70, 25);
		frame.getContentPane().add(openMin);
		
		closeHour = new JTextField();
		closeHour.setBounds(140, 100, 70, 25);
		frame.getContentPane().add(closeHour);
		
		closeMin = new JTextField();
		closeMin.setBounds(220, 100, 70, 25);
		frame.getContentPane().add(closeMin);
		
		
		minServiceTime = new JTextField();
		minServiceTime.setBounds(520, 10, 150, 25);
		frame.getContentPane().add(minServiceTime);
		
		maxServiceTime = new JTextField();
		maxServiceTime.setBounds(520, 40, 150, 25);
		frame.getContentPane().add(maxServiceTime);
		
		minTimeBetween = new JTextField();
		minTimeBetween.setBounds(520, 70, 150, 25);
		frame.getContentPane().add(minTimeBetween);
		
		maxTimeBetween = new JTextField();
		maxTimeBetween.setBounds(520, 100, 150, 25);
		frame.getContentPane().add(maxTimeBetween);
		
		
		btnClose = new JButton("Close");
		btnClose.setBounds(590, 150, 100, 25);
		frame.getContentPane().add(btnClose);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(480, 150, 100, 25);
		frame.getContentPane().add(btnStart);

		
	}

		public void setVisible() {
			frame.setVisible(true);
		}
		
		public void setInvisible() {
			frame.setVisible(false);
		}
		
		public int getClients() {
			return Integer.parseInt(maxClients.getText());
		}
		
		public int getServers() {
			return Integer.parseInt(maxServers.getText());
		}
		
		public int getOpenHour() {
			return Integer.parseInt(openHour.getText())*60 + getOpenMin();
		}
		
		private int getOpenMin() {
			return Integer.parseInt(openMin.getText());
		}
		
		public int getCloseHour() {
			return Integer.parseInt(closeHour.getText())*60 + getCloseMin();
		}
		
		private int getCloseMin() {
			return Integer.parseInt(closeMin.getText());
		}
		
		public int getMinServiceTime() {
			return Integer.parseInt(minServiceTime.getText());
		}
		
		public int getMaxServiceTime() {
			return Integer.parseInt(maxServiceTime.getText());
		}
		
		public int getMinTimeBetween() {
			return Integer.parseInt(minTimeBetween.getText());
		}
		
		public int getMaxTimeBetween() {
			return Integer.parseInt(minTimeBetween.getText());
		}
		
		public SelectionPolicy getPolicy() {
			if(shortestQueue.isSelected()) {
				return SelectionPolicy.SHORTES_QUEUE;
			}
			else return SelectionPolicy.SHORTES_TIME;

		}
		
		
		public void addBtnCloseListener(ActionListener e) {
			btnClose.addActionListener(e);
			
		}
		public void addBtnStartListener(ActionListener e) {
			btnStart.addActionListener(e);
		}
		


}
