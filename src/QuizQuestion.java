import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizQuestion
{
	private Randomizer random;
	private Scanner read;
	private int randomnr;
	private String question;
	private String rightanswer;
	private String first;
	private String second;
	private String third;
	private String four;
	private boolean found;
	
	private static ArrayList<QuizQuestion> array = new ArrayList<QuizQuestion>();
	private boolean marked;
	
	public QuizQuestion() throws FileNotFoundException
	{
		read = new Scanner(new File("quizQuestions.dat"));
		random = new Randomizer();
		randomnr = random.getNextNumber();
		
		while(read.hasNextLine() && !found)
		{
			String line = read.nextLine();
			
			if(line.startsWith(String.valueOf(randomnr)))
			{
				question = read.nextLine();
				first = read.nextLine();
				second = read.nextLine();
				third = read.nextLine();
				four = read.nextLine();
				
				if(first.contains("*"))
				{
					rightanswer = first.substring(1);
					first = rightanswer;
				}
				
				else if(second.contains("*"))
				{
					rightanswer = second.substring(1);
					second = rightanswer;
				}
				
				else if(third.contains("*"))
				{
					rightanswer = third.substring(1);
					third = rightanswer;
				}
				
				else if(four.contains("*"))
				{
					rightanswer = four.substring(1);
					four = rightanswer;
				}
				
				found = true;
			}
		}
	}
	
	public void isWrong()
	{
		if(marked)
			array.add(this);
	}
	
	public boolean checkIfDone()
	{
		boolean present = false;
		
		for(QuizQuestion question : array)
			if(question.getQuestion().equals(this.getQuestion()))
				present = true;
		
		return present;
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	public String getFirst()
	{
		return first;
	}
	
	public String getSecond()
	{
		return second;
	}
	
	public String getThird()
	{
		return third;
	}
	
	public String getFour()
	{
		return four;
	}
	
	public String getRightAnswer()
	{
		return rightanswer;
	}
	
	public int getRandomNr()
	{
		return randomnr;
	}
	
	public ArrayList<QuizQuestion> getArrayList()
	{
		return array;
	}
	
	public void setMarked(boolean marked)
	{
		this.marked = marked;
	}
}
