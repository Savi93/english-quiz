import java.text.DecimalFormat;

public class User 
{
	private String name;
	private int nrquestions, correct, wrong;
	private double time, averagetime, success;
	private DecimalFormat format;
	
	public User(String name)
	{
		format = new DecimalFormat("#.##");
		this.name = name;
		this.nrquestions = 0;
		this.correct = 0;
		this.wrong = 0;
		this.time = 0;
		this.averagetime = 0;
		this.success = 0;
	}
	
	public User()
	{
		this.name = "";
	}
	
	public void incrementTime()
	{
		time += 0.5;
		averagetime = time / nrquestions;
	}
	
	public void incrementWrong()
	{
		wrong++;
		success = ((double)correct / (double)(correct + wrong)) * 100;
	}
	
	public void incrementCorrect()
	{
		correct++;
		success = ((double)correct / (double)(correct + wrong)) * 100;
	}
	
	public void incrementNrOfQuestions()
	{
		nrquestions++;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getNrOfQuestions()
	{
		return nrquestions;
	}
	
	public int getCorrectQuestions()
	{
		return correct;
	}
	
	public int getWrongQuestions()
	{
		return wrong;
	}
	
	public double getTime()
	{
		return time;
	}
	
	public double getAverageTime()
	{
		return averagetime;
	}
	
	public double getSuccess()
	{
		return success;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public void setNrquestions(int nrquestions) 
	{
		this.nrquestions = nrquestions;
	}

	public void setCorrect(int correct) 
	{
		this.correct = correct;
	}

	public void setWrong(int wrong) 
	{
		this.wrong = wrong;
	}

	public void setTime(double time) 
	{
		this.time = time;
	}

	public void setAveragetime(double averagetime) 
	{
		this.averagetime = averagetime;
	}

	public void setSuccess(double success) 
	{
		this.success = success;
	}
	
	public String getInformationText()
	{
		return "USER: " + name + "\n" + "Nr. of questions visualized: " + nrquestions + "\n" + "Correct answers: " + correct + "\n" + "Wrong answers: " + wrong + "\n" + "Seconds per answers: " + format.format(averagetime) + "\n" + "% of success: " + format.format(success);
	}

	public String toString()
	{
		return name + " " + nrquestions + " " + correct + " " + wrong + " " + time + " " + format.format(averagetime) + " " + format.format(success);
	}
}
