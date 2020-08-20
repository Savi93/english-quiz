import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class StartFrame 
{
	private static JMenuItem nickclose;
	private static JMenuItem startclose;
	private static JMenuItem quizclose;
	private static JCheckBox checkbox;
	private static JMenuItem back;
	private static boolean marked;
	
	private static NicknamePanel panel;
	private static JFrame nickframe;
	private static JFrame startframe;
	private static StartPanel startpanel;
	private static JFrame quizframe;
	private static QuizPanel quizpanel;
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		JMenuBar nickbar = new JMenuBar();
		JMenu nickmenu = new JMenu("APP");
		nickclose = new JMenuItem("Exit");
		JMenuItem nickabout = new JMenuItem("About");
		
		nickclose.addActionListener(new close_click());
		nickabout.addActionListener(new about_click());
		
		nickmenu.add(nickabout);
		nickmenu.add(nickclose);
		nickbar.add(nickmenu);
		
		panel = new NicknamePanel();
		nickframe = new JFrame("EnglishQuiz");
		nickframe.setJMenuBar(nickbar);
		nickframe.add(panel);
		nickframe.setSize(1200, 700);
		nickframe.setLocation(100, 20);
		nickframe.setVisible(true);
	}
	
	public void showNickFrame() throws FileNotFoundException
	{
		JMenuBar nickbar = new JMenuBar();
		JMenu nickmenu = new JMenu("APP");
		nickclose = new JMenuItem("Exit");
		JMenuItem nickabout = new JMenuItem("About");
		
		nickclose.addActionListener(new close_click());
		nickabout.addActionListener(new about_click());
		
		nickmenu.add(nickabout);
		nickmenu.add(nickclose);
		nickbar.add(nickmenu);
		
		panel = new NicknamePanel();
		nickframe = new JFrame("EnglishQuiz");
		nickframe.setJMenuBar(nickbar);
		nickframe.add(panel);
		nickframe.setSize(1200, 700);
		nickframe.setLocation(100, 20);
		nickframe.setVisible(true);
	}

	public void showStartFrame()
	{
		JMenuBar startbar = new JMenuBar();
		JMenu startmenu = new JMenu("APP");
		startclose = new JMenuItem("Exit");
		JMenuItem startabout = new JMenuItem("About");
		
		startmenu.add(startabout);
		startmenu.add(startclose);
		startbar.add(startmenu);
		startclose.addActionListener(new close_click());
		startabout.addActionListener(new about_click());
		
		startpanel = new StartPanel();
		startframe = new JFrame("EnglishQuiz");
		startframe.setJMenuBar(startbar);
		startframe.add(startpanel);
		startframe.setVisible(true);
		startframe.setSize(1200, 700);
		startframe.setLocation(100, 20);
		closeNickFrame();
	}
	
	public void showQuizFrame() throws IOException
	{
		JMenuBar quizbar = new JMenuBar();
		JMenu quizcheckbox = new JMenu("Functionality");
		back = new JMenuItem("Return home");
		checkbox = new JCheckBox("Don't show correct answers");
		
		if(marked == true)
			checkbox.setSelected(true);
		
		JMenu quizmenu = new JMenu("Application");
		quizclose = new JMenuItem("Exit");
		JMenuItem quizabout = new JMenuItem("About");
		
		checkbox.addActionListener(new checked());
		
		quizcheckbox.add(back);
		quizcheckbox.add(checkbox);
		quizmenu.add(quizabout);
		quizmenu.add(quizclose);
		quizbar.add(quizmenu);
		quizbar.add(quizcheckbox);
		quizclose.addActionListener(new close_click());
		quizabout.addActionListener(new about_click());
		back.addActionListener(new back_click());
		
		quizpanel = new QuizPanel();
		quizframe = new JFrame("EnglishQuiz");
		quizframe.setJMenuBar(quizbar);
		quizframe.add(quizpanel);
		quizframe.setVisible(true);
		quizframe.setSize(1200, 700);
		quizframe.setLocation(100, 20);
		closeStartFrame();
	}
	
	public static void closeNickFrame()
	{
		nickframe.dispose();
	}
	
	public static void closeStartFrame()
	{
		startframe.dispose();
	}
	
	public static void closeQuizFrame()
	{
		quizframe.dispose();
	}
	
	public NicknamePanel returnNicknamePanel()
	{
		return panel;
	}
	
	private static class about_click implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			JOptionPane.showMessageDialog(null, "ENGLISH QUIZ APPLICATION by Savev David\n\n" +
			"NR. OF CLASSES: 9\n" + "NR. OF METHODS: N.D.\n" + "NR. OF LINES: 1400 lines", "About", JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	
	private static class close_click implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			if(arg0.getSource() == nickclose )
				closeNickFrame();
			else if(arg0.getSource() == startclose)
				closeStartFrame();
			else if(arg0.getSource() == quizclose)
			{
				quizpanel.getTiming().stop();
				closeQuizFrame();
			}
		}
	}
	
	private static class checked implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			if(checkbox.isSelected())
				marked = true;
			else
				marked = false;
		}
	}
	
	private class back_click implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			quizpanel.getTiming().stop();
			
			try 
			{
				returnNicknamePanel().returnUserList().updateFile();
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			
			closeQuizFrame();
			
			try 
			{
				showNickFrame();
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public boolean getMarked()
	{
		return marked;
	}
}
