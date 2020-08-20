import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel
{
	StartFrame frame = new StartFrame();
	private JPanel container1;
	private JPanel container2;
	private JPanel container3;
	private JPanel container4;
	private JPanel container5;
	private JPanel container6;
	private JLabel username;
	private JLabel nrofquestions;
	private JLabel averagetime;
	private JLabel success;
	private JLabel rightwrong;
	private JButton start;
	
	public StartPanel()
	{
		Color mycolor = new Color(178, 192, 204);
		setLayout(new GridLayout(6,1));
		container1 = new JPanel();
		container1.setBackground(mycolor);
		container2 = new JPanel();
		container2.setBackground(mycolor);
		container3 = new JPanel();
		container3.setBackground(mycolor);
		container4 = new JPanel();
		container4.setBackground(mycolor);
		container5 = new JPanel();
		container5.setBackground(mycolor);
		container6 = new JPanel();
		container6.setBackground(mycolor);
		container1.setLayout(new GridLayout(2,1));
		username = new JLabel();
		username.setFont(new Font("myFont", 3, 30));
		nrofquestions = new JLabel();
		nrofquestions.setFont(new Font("myFont", 3, 30));
		averagetime = new JLabel();
		averagetime.setFont(new Font("myFont", 3, 30));
		success = new JLabel();
		rightwrong = new JLabel();
		rightwrong.setFont(new Font("myFont", 3, 30));
		success.setFont(new Font("myFont", 3, 30));
		start = new JButton("Start the game");
		start.setBackground(Color.blue);
		start.setForeground(Color.white);
		
		if(frame.returnNicknamePanel().returnUser().getNrOfQuestions() != 0 && !(frame.returnNicknamePanel().returnUser().getName().equals("")))
		{
			username.setText("WELCOME " + frame.returnNicknamePanel().returnUser().getName() + " !");
			nrofquestions.setText("Nr. of questions visualized: " + String.valueOf(frame.returnNicknamePanel().returnUser().getNrOfQuestions()));
			averagetime.setText("Average time per question: " + String.valueOf(frame.returnNicknamePanel().returnUser().getAverageTime()) + " seconds");
			rightwrong.setText("Correct answers: " + String.valueOf(frame.returnNicknamePanel().returnUser().getCorrectQuestions()) + " and false answers: " + String.valueOf(frame.returnNicknamePanel().returnUser().getWrongQuestions()));
			success.setText("Success: " + String.valueOf(frame.returnNicknamePanel().returnUser().getSuccess()) + " %");	
		}
		
		else if(frame.returnNicknamePanel().returnUser().getNrOfQuestions() == 0 && !(frame.returnNicknamePanel().returnUser().getName().equals("")))
		{
			username.setText("WELCOME " + frame.returnNicknamePanel().returnUser().getName() + " !");
			nrofquestions.setText("Press start to start the quiz and enjoy in the game :)");
		}
		
		else
			username.setText("WELCOME GUEST !");
		
		start.addActionListener(new start_click());
		container1.add(start);
		container2.add(username);
		container3.add(nrofquestions);
		container6.add(rightwrong);
		container4.add(averagetime);
		container5.add(success);
		add(container2);
		add(container3);
		add(container6);
		add(container4);
		add(container5);
		add(container1);
	}
	
	private class start_click implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			
			try 
			{
				frame.showQuizFrame();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
