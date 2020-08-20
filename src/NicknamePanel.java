import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NicknamePanel extends JPanel
{
	private StartFrame startframe;
	private User nickname;
	private UserList userlist;
	private JPanel container1;
	private JPanel container2;
	private JPanel container3;
	private JPanel container4;
	private JTextField text;
	private JButton loguser;
	private JLabel english;
	private JLabel enternick;
	
	public NicknamePanel() throws FileNotFoundException
	{
		Color mycolor = new Color(178, 192, 204);
		
		setLayout(new GridLayout(4,1));

		container1 = new JPanel();
		container1.setBackground(mycolor);
		container2 = new JPanel();
		container2.setBackground(mycolor);
		container3 = new JPanel();
		container3.setBackground(mycolor);
		container4 = new JPanel();
		container4.setBackground(mycolor);
		container2.setLayout(new GridLayout());
		container3.setLayout(new GridLayout());
		text = new JTextField(25);
		text.setBackground(Color.lightGray);
		text.setForeground(Color.black);
		text.setFont(new Font("myFont", 2, 20));
		
		enternick = new JLabel("Enter username: ");
		english = new JLabel("English Quiz");
		english.setFont(new Font("myFont", 3, 30));
		
		loguser = new JButton("Enter");
		loguser.setBackground(Color.blue);
		loguser.setForeground(Color.white);
		loguser.addActionListener(new loguser_click());
		text.addKeyListener(new text_changed());
		container1.add(enternick);
		container1.add(text);
		container2.add(loguser);
		container4.add(english);
		
		add(container4);
		add(container1, TOP_ALIGNMENT);
		add(container3, CENTER_ALIGNMENT);
		add(container2, BOTTOM_ALIGNMENT);
		
		userlist = new UserList();
		userlist.readUsers();
	}
	
	public class loguser_click implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	
			if(text.getText().equals(""))
			{
				nickname = new User();
				startframe = new StartFrame();
				startframe.showStartFrame();
			}
			
			else
			{
				if(!(userlist.checkIfExists(text.getText())))
				{
					nickname = new User(text.getText());
					userlist.addUser(nickname);
				}
				
				else
				{
					nickname = userlist.getSavedUser(text.getText());
				}
				
				try 
				{
					userlist.updateFile();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
				startframe = new StartFrame();
				startframe.showStartFrame();
			}	
		}
	}
	
	public class text_changed implements KeyListener
	{
		public void keyReleased(KeyEvent arg0) 
		{
			if(arg0.getKeyChar() == ' ')
			{
				text.setText(text.getText().substring(0, text.getText().length() - 1));
				while(text.getText().contains(" "))
					text.setText(text.getText().substring(0, text.getText().length() - 1));
			}
		}
		
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent arg0) {}
	}
	
	public User returnUser()
	{
		return nickname;
	}
	
	public UserList returnUserList()
	{
		return userlist;
	}
}
