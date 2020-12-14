package com.game.src.main.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;


public class SaveFile
{   
   //static int[] scores = new int[10];
	String[] names = new String[10];
   File save = new File("./savefile.dat");
   Scanner sc = new Scanner(save);
   //"C:/Users/0001081009/workspace/misha MTT/savefile.dat"
   
	public SaveFile() throws FileNotFoundException
	{
		
		
		int place = 0;
		while(sc.hasNextLine()) 
    	{	
			String s = sc.nextLine();
			names[place] = s;
			
			place++;
    	}
		System.out.print(Arrays.toString(names));
	}
	
	
	
	
	
	
	//Modifies the save file
	public void modifyFile(String oldString, String newString) 
    {
        
        
        String oldContent = "";
         
        BufferedReader reader = null;
         
        FileWriter writer = null;
        
        
       
        try
        {
            reader = new BufferedReader(new FileReader(save));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
           {
                oldContent = oldContent + line + System.lineSeparator();
                 
                line = reader.readLine();
                
            }
             
            //Replacing oldString with newString in the oldContent
             
            String newContent = oldContent.replaceAll(oldString, newString);
            
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(save);
            writer.write(newContent);
            writer.close();
           
           
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


public int hScore(int i)
{
	String current = names[i];
	
	
	
	try {
		return ((Number)NumberFormat.getInstance().parse(current)).intValue() ;
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return -1;
}

public String current(int i ) {
	
	String current = names[i];
	
	
	
	return current;
}

}
