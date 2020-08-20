import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList 
{
	private ArrayList<User> users = new ArrayList<User>();
	private Scanner scan;
	
	public void addUser(User user)
	{
		users.add(user);
	}
	
	public int getNumberOfUSers()
	{
		return users.size();
	}
	
	public void updateFile() throws IOException
	{
	
	PrintStream write = new PrintStream("users.dat");
	
	for(User myUser : users)
		write.println(myUser);
	}
	
	public void readUsers() throws FileNotFoundException
	{
		scan = new Scanner(new File("users.dat"));
		
		while(scan.hasNext())
		{
			User user = new User(scan.next());
			user.setNrquestions(Integer.valueOf(scan.next()));
			user.setCorrect(Integer.valueOf(scan.next()));
			user.setWrong(Integer.valueOf(scan.next()));
			user.setTime(Double.valueOf(scan.next()));
			user.setAveragetime(scan.nextDouble());
			user.setSuccess(scan.nextDouble());
			
			users.add(user);
		}
	}
	
	public boolean checkIfExists(String user) 
	{
		boolean bool = false;
		try 
		{
			scan = new Scanner(new File("users.dat"));
		} 
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		while(scan.hasNextLine())
		{
			String line = scan.nextLine();
			
			if(line.contains(user))
			{
				bool = true;
			}
		}
		
		return bool;
	}
	
	public User getSavedUser(String name)
	{
		User user = null;
		
		for(User myUser : users)
		{
			if(myUser.getName().equals(name))
			{
				user = myUser;
				break;
			}
		}
		
		return user;
	}
	
	public User getWorst()
	{	
		ArrayList<User> worst = new ArrayList<User>();
		
		for(int i = 0; i < users.size(); i++)
		{
			worst.add(users.get(i));
		}
			
		if(users.size() > 1)
		{
			for(int j = 0; j < worst.size() - 1; j++)
			{
				if(worst.get(j).getSuccess() < worst.get(j+1).getSuccess())
					worst.set(j+1, worst.get(j));
			}
		}
		
		else
			 worst.set(worst.size() - 1, users.get(0));
		
		return worst.get(worst.size() - 1);
	}
	
	public User getBest()
	{
		ArrayList<User> best = new ArrayList<User>();
		
		for(int i = 0; i < users.size(); i++)
		{
			best.add(users.get(i));
		}
		
		if(users.size() > 1)
		{
			for(int j = 0; j < best.size() - 1; j++)
			{
				if(best.get(j).getSuccess() > best.get(j+1).getSuccess())
					best.set(j+1, best.get(j));
			}
		}
		
		else
			 best.set(best.size() - 1, users.get(0));
		
		return best.get(best.size() - 1);
	}
	
	public ArrayList<User> getUserList()
	{
		return users;
	}
}
