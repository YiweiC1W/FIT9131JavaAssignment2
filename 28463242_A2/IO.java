import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Generate a IO to import each line of 
 * a file with a given file name
 * to an ArryaList of Srtring or to outport
 * each String of an ArrayList to a file 
 * with a given file name.  
 * @author Yiwei Chen
 * @version 3.0 15 May 2018
 */
public class IO
{

    /**
     * Constructor for objects of class IO
     */
    public IO()
    {

    }

    /**
     * This method aim to import each line of a file with a given file name to an ArryaList of Srtring.   
     */
    public ArrayList importFile(String filename)
    {
        ArrayList<String> input = new ArrayList();
        try
        {
            FileReader teamFile = new FileReader(filename);
            try
            {
                Scanner addTeam = new Scanner(teamFile);
                for (int i = 0; addTeam.hasNextLine(); i++)
                {
                    String line = addTeam.nextLine();
                    input.add(line);
                }
            }
            finally
            {
                System.out.println("Closing file");
                teamFile.close();
            }
        }
        catch(FileNotFoundException exception) 
        {
            System.out.println(filename + " not found"); 
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        return input;
    }

    /**
     * This method aim to outport each String of an ArrayList to a file with a given file name.  
     */
    public void outportFile(ArrayList<String> file, String filename)
    {
        try
        {
            PrintWriter outputFile = new PrintWriter(filename);
            try
            {
                for (String result : file)
                    outputFile.println(result);
            }
            finally
            {
                System.out.println("Closing file");
                outputFile.close();
            }            
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }
}
