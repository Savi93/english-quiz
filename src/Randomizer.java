import java.util.Random;

public class Randomizer 
{
	private Random select;
	private int number;
	
	public Randomizer()
	{
		select = new Random();
	}
	
	public int getNextNumber()
	{
		number = select.nextInt(30) + 1;
		return number;
		
	}
}
