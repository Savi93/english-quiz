import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class QuizPanel extends JPanel
{
	private Difficulty difficulty = new Difficulty();
	private StartFrame startframe = new StartFrame();
	private QuizQuestion question;
	private Timer timing;
	private int seconds;
	private JPanel container1;
	private JPanel container2;
	private JPanel container3;
	private JPanel container4;
	private JPanel container5;
	private JPanel container6;
	private JPanel container7;
	private JPanel container8;
	private JPanel container9;
	private JButton answer1;
	private JButton answer2;
	private JButton answer3;
	private JButton answer4;
	private JButton sure;
	private JLabel getcorrect;
	private JLabel otherquest;
	private JLabel quest;
	private JLabel timer;
	private JLabel scores;
	private JLabel wrongquestions;
	
	private long startime;
	
	public QuizPanel() throws IOException 
	{
		question = new QuizQuestion();
		
		startframe.returnNicknamePanel().returnUser().incrementNrOfQuestions();		
		timing = new Timer(500, new timer_event());
		startime = System.currentTimeMillis();
		timing.start();
		
		Color mycolor = new Color(178, 192, 204);
		
		container1 = new JPanel();
		container1.setBackground(mycolor);
		container2 = new JPanel();
		container2.setBackground(mycolor);
		container3 = new JPanel(new GridLayout(1,2));
		container3.setBackground(mycolor);
		container4 = new JPanel(new GridLayout(1,2));
		container4.setBackground(mycolor);
		container5 = new JPanel(new GridLayout(1,1));
		container5.setBackground(mycolor);
		container6 = new JPanel(new GridLayout(1,3));
		container6.setBackground(mycolor);
		container7 = new JPanel(new GridLayout());
		container7.setBackground(mycolor);
		container8 = new JPanel();
		container8.setBackground(mycolor);
		container9 = new JPanel();
		container9.setBackground(mycolor);
		
		answer1 = new JButton(question.getFirst());
		answer1.setBackground(Color.blue);
		answer1.setForeground(Color.white);
		answer2 = new JButton(question.getSecond());
		answer2.setBackground(Color.blue);
		answer2.setForeground(Color.white);
		answer3 = new JButton(question.getThird());
		answer3.setBackground(Color.blue);
		answer3.setForeground(Color.white);
		answer4 = new JButton(question.getFour());
		answer4.setBackground(Color.blue);
		answer4.setForeground(Color.white);
		quest = new JLabel(question.getQuestion());
		quest.setBackground(Color.white);
		
		wrongquestions = new JLabel("TOP 5 WRONG QUESTIONS");
		wrongquestions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		wrongquestions.setForeground(Color.red);
		quest.setFont(new Font("myFont", 3, 20));
		quest.setFont(new Font("myFont", 3, 20));
		timer = new JLabel("TIME ELAPSED: " + String.valueOf(seconds) + " sec.");
		timer.setBackground(Color.orange);
		
		sure = new JButton("It is the definitly answer");
		sure.setBackground(Color.BLACK);
		sure.setForeground(Color.white);
		sure.setVisible(false);
		getcorrect = new JLabel(new ImageIcon("questionmark.png"));
		getcorrect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		otherquest = new JLabel(new ImageIcon("arrow.png"));
		otherquest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scores = new JLabel("                    SHOW SCOREBOARD");
		scores.setFont(new Font("myFont", 3, 18));
		scores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		setLayout(new GridLayout(8, 1));
		
		scores.addMouseListener(new scores_click());
		sure.addActionListener(new sure_click());
		getcorrect.addMouseListener(new getcorrect_click());
		otherquest.addMouseListener(new otherquest_click());
		wrongquestions.addMouseListener(new wrongquestions_click());
		
		answer1.addActionListener(new answer_click());
		answer2.addActionListener(new answer_click());
		answer3.addActionListener(new answer_click());
		answer4.addActionListener(new answer_click());
		
	    container1.add(timer);
	    container9.add(wrongquestions);
	    container2.add(quest, CENTER_ALIGNMENT);
		container3.add(answer1);
		container3.add(answer2);
		container4.add(answer3);
		container4.add(answer4);
		container5.add(sure);
		container6.add(getcorrect);
		container6.add(scores);
		container6.add(otherquest);
		
		add(container9);
		add(container1);
		add(container2);
		add(container3);
		add(container4);
		add(container5);
		add(container7);
		add(container6);
		
		difficulty.readDifficult();
		difficulty.addQuestion(question.getRandomNr(), 0, question.getQuestion());
		
	}
	
	private class answer_click implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			sure.setVisible(true);
			
			if(arg0.getSource() == answer1)
			{
				resetColorsButtons();
				answer1.setBackground(Color.ORANGE);
			}
				
			else if(arg0.getSource() == answer2)
			{
				resetColorsButtons();
				answer2.setBackground(Color.ORANGE);
			}
			
			else if(arg0.getSource() == answer3)
			{
				resetColorsButtons();
				answer3.setBackground(Color.ORANGE);
			}
			
			else if(arg0.getSource() == answer4)
			{
				resetColorsButtons();
				answer4.setBackground(Color.ORANGE);
			}
		}
	}
	
	private class sure_click implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			timing.stop();
			
			if(answer1.getBackground() == Color.orange && answer1.getText().equals(question.getRightAnswer()))
			{
				answer1.setBackground(Color.green);
				JOptionPane.showMessageDialog(null, "CORRECT ANSWER!");
				startframe.returnNicknamePanel().returnUser().incrementCorrect();
			}
			
			else if(answer2.getBackground() == Color.orange && answer2.getText().equals(question.getRightAnswer()))
			{
				answer2.setBackground(Color.green);
				JOptionPane.showMessageDialog(null, "CORRECT ANSWER!");
				startframe.returnNicknamePanel().returnUser().incrementCorrect();
			}
			
			else if(answer3.getBackground() == Color.orange && answer3.getText().equals(question.getRightAnswer()))
			{
				answer3.setBackground(Color.green);
				JOptionPane.showMessageDialog(null, "CORRECT ANSWER!");
				startframe.returnNicknamePanel().returnUser().incrementCorrect();
			}
			
			else if(answer4.getBackground() == Color.orange && answer4.getText().equals(question.getRightAnswer()))
			{
				answer4.setBackground(Color.green);
				JOptionPane.showMessageDialog(null, "CORRECT ANSWER!");
				startframe.returnNicknamePanel().returnUser().incrementCorrect();
			}
			
			else
			{
				if(answer1.getBackground() == Color.orange)
					answer1.setBackground(Color.red);
				else if(answer2.getBackground() == Color.orange)
					answer2.setBackground(Color.red);
				else if(answer3.getBackground() == Color.orange)
					answer3.setBackground(Color.red);
				else if(answer4.getBackground() == Color.orange)
					answer4.setBackground(Color.red);
				
				if(answer1.getText().equals(question.getRightAnswer()))
					answer1.setBackground(Color.green);
				else if(answer2.getText().equals(question.getRightAnswer()))
					answer2.setBackground(Color.green);
				else if(answer3.getText().equals(question.getRightAnswer()))
					answer3.setBackground(Color.green);
				else if(answer4.getText().equals(question.getRightAnswer()))
					answer4.setBackground(Color.green);
				
				JOptionPane.showMessageDialog(null, "FALSE ANSWER!");
				startframe.returnNicknamePanel().returnUser().incrementWrong();
				
				try 
				{
					difficulty.incrementError();
					difficulty.printDifficult();
				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}
			}
			
			try 
			{
				startframe.returnNicknamePanel().returnUserList().updateFile();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}	
			
			startframe.closeQuizFrame();
		
			try 
			{
				startframe.showQuizFrame();
			} 
			
			catch (IOException e) 
			{
				e.printStackTrace();
			} 
		}
	}
	
	private class getcorrect_click implements MouseListener
	{
		public void mousePressed(MouseEvent arg0) 
		{
			timing.stop();
			
			if(answer1.getText().equals(question.getRightAnswer()))
				answer1.setBackground(Color.green);
			if(answer2.getText().equals(question.getRightAnswer()))
				answer2.setBackground(Color.green);
			if(answer3.getText().equals(question.getRightAnswer()))
				answer3.setBackground(Color.green);
			if(answer4.getText().equals(question.getRightAnswer()))
				answer4.setBackground(Color.green);	
			
			answer1.setEnabled(false);
			answer2.setEnabled(false);
			answer3.setEnabled(false);
			answer4.setEnabled(false);
			sure.setVisible(false);
		}
		
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}	
	}
	
	private class otherquest_click implements MouseListener
	{
		public void mousePressed(MouseEvent arg0) 
		{
			timing.stop();
			
			try 
			{
				startframe.returnNicknamePanel().returnUserList().updateFile();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			
			startframe.closeQuizFrame();
			
			try 
			{
				startframe.showQuizFrame();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	private class scores_click implements MouseListener
	{
		public void mousePressed(MouseEvent arg0) 
		{
			try
			{
				JOptionPane.showMessageDialog(null, "CURRENT USER: \n" + startframe.returnNicknamePanel().returnUser().getInformationText() + "\n" +
				"\nWORST USER: \n" + startframe.returnNicknamePanel().returnUserList().getWorst().getInformationText() + "\n" +
				"\nBEST USER: \n" + startframe.returnNicknamePanel().returnUserList().getBest().getInformationText(), "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			
			catch(NullPointerException e)
			{
				try
				{
					JOptionPane.showMessageDialog(null, "WORST USER: \n" + startframe.returnNicknamePanel().returnUserList().getWorst().getInformationText() + "\n" +
					"\nBEST USER: \n" + startframe.returnNicknamePanel().returnUserList().getBest().getInformationText(), "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
				catch(IndexOutOfBoundsException e1)
				{
					JOptionPane.showMessageDialog(null, "NO USERS REGISTERED!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		
	}
	
	private class wrongquestions_click implements MouseListener
	{
		public void mousePressed(MouseEvent arg0) 
		{
			try
			{	
				JOptionPane.showMessageDialog(null, "TOP 5 MOST WRONG QUESTIONS:\n" +
			    difficulty.getWorstQuestions(), "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			
			catch(NullPointerException | IndexOutOfBoundsException e) 
			{
				JOptionPane.showMessageDialog(null, "WE DON'T HAVE 5 WORST QUESTIONS!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	private class timer_event implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			seconds = (int)((System.currentTimeMillis() - startime)/1000);
			
			timer.setText("TIME ELAPSED: " + String.valueOf(seconds) + " sec.");
			timing.start();
			startframe.returnNicknamePanel().returnUser().incrementTime();
			
			try 
			{
				startframe.returnNicknamePanel().returnUserList().updateFile();
			}
			
			catch (IOException e) 
			{e.printStackTrace();}
		}
	}
	
	public void resetColorsButtons()
	{
		answer1.setBackground(Color.blue);
		answer2.setBackground(Color.blue);
		answer3.setBackground(Color.blue);
		answer4.setBackground(Color.blue);
	}
	
	public Timer getTiming()
	{
		return timing;
	}
}
