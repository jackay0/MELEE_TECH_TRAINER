package com.game.src.main.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class SaveFile
{   
   static int[] scores = new int[10];
   File save = new File("./savefile.dat");
   //"C:/Users/0001081009/workspace/misha MTT/savefile.dat"
   
	public SaveFile() throws FileNotFoundException
	{
		
		
		Scanner sc = new Scanner(save);
		for(int i = 0; sc.hasNextInt(); i++) 
    	{	
    	scores[i] = sc.nextInt();
    	
    	}
		
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
            System.out.println(newContent);
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
	return scores[i];
	
}



}
