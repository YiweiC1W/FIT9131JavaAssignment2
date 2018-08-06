import java.util.Random;

/**
* Generate a random number between
* the maximum （exclusive） and 
* minimum （exclusive） you give
* @author Yiwei Chen
* @version 1.0 15 April 2018
*/

public class RandomNumber
{
    private int random;

    /**
      * Creat a RandomNumber
      */
    public RandomNumber(int min, int max)
    {
        random = -1;   
        setRandomNumber(min, max);    
    }

    /**
      * return the randum number
      */    
    public int getRandomNumber()
    {
        return random;
    }

    /**
      * Generate a random number between
      * the maximum （exclusive） and 
      * minimum （exclusive） you give
      */
    public void setRandomNumber(int min, int max)
    {
        Random r1 = new Random();
        //Returns a pseudorandom, uniformly distributed int value between [0, max - min -1), then +1 + min
        random = r1.nextInt(max - min -1) + 1 + min; 
    }
}