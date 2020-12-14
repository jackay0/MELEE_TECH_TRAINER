import java.util.Scanner;
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
public class Save {
	String saves;
	File save;
	public Save(File save) throws FileNotFoundException
	{
	this.save = save;
	Scanner sc = new Scanner(save);
	int[] scores = new int[1000];
	for(int i = 0; sc.hasNextInt(); i++) 
	{
		
	scores[i] = sc.nextInt();
	
	
	}
	saves = Arrays.toString(scores);
	}
	
	

	public String getSaves()
	{
		return saves;
	}








}
