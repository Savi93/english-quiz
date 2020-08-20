import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Difficulty implements Comparable<Difficulty>
{
	private int point;
	private int id;
	private String question;
	private PrintStream print;
	private Scanner scan;
	private ArrayList<Difficulty> questions = new ArrayList<Difficulty>();
	
	public void addQuestion(int id, int point,String question) throws FileNotFoundException
	{
		this.point = point;
		this.id = id;
		this.question = question;
			
		if(questions.size() == 0)
			questions.add(this);
	}
	
	public void incrementError() throws FileNotFoundException
	{
		String same = null;
		
		for(int j = 0; j < questions.size(); j++)
		{
			if(this.getID() == questions.get(j).getID())
				same = String.valueOf(j);
		}
		
		if(same != null)
			questions.get(Integer.valueOf(same)).point += 1;
		
		else
		{
			point++;
			questions.add(this);
		}
		
		Collections.sort(questions);
	}
	
	public void readDifficult() throws FileNotFoundException
	{
		scan = new Scanner(new File("difficult.dat"));
		
		while(scan.hasNext())
		{
			Difficulty difficult = new Difficulty();
			difficult.addQuestion(Integer.valueOf(scan.nextLine()), Integer.valueOf(scan.nextLine()), scan.nextLine());
			questions.add(difficult);
		}
		
	}
	
	public void printDifficult() throws FileNotFoundException
	{
		print = new PrintStream(new File("difficult.dat"));
		
		for(int j = 0; j < questions.size(); j++)
		{
			print.println(questions.get(j).getID());
			print.println(questions.get(j).getPoint());
			print.println(questions.get(j).getQuestion());
		}
	}
	
	public String getWorstQuestions()
	{
		String difficul = "";
		
		for(int j = 0; j < 5; j++)
		{
			difficul = difficul.concat(questions.get(j).toString());
		}
		
		return difficul;
	}
	
	public int compareTo(Difficulty question) 
	{
		int point = question.getPoint();
		return point - this.getPoint();
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	public int getPoint()
	{
		return point;
	}
	
	public int getID()
	{
		return id;
	}
	
	public ArrayList<Difficulty> getDifficulty()
	{
		return questions;
	}
	
	public String toString()
	{
		return "TIMES WRONG: " + point + " && " + "QUESTION: " + question + "\n";
	}
}
