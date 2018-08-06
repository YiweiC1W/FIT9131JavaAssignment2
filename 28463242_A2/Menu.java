import java.util.ArrayList;
import java.util.Scanner;

/**
 * passed menu options as an array of strings 
 * and it returns the option chosen as a char value
 * @author Yiwei Chen
 * @version 1.0 15 May 2018
 */
public class Menu
{
    private ArrayList<String> menu;
    private char option;

    /**
     * Non-default Constructor for objects of class Menu with a given ArrayList of options
     */
    public Menu(ArrayList<String> menu)
    {
        this.menu = menu;
    }

    /**
     * Default Constructor for objects of class Menu with a given ArrayList of options
     */
    public Menu()
    {
    }

    /**
     * This method aims to display all options of a menu
     */
    public void displayMenu()
    {
        for (String option : menu)
            System.out.println(option);
        System.out.println();
    }

    /**
     * This method aims to return the choice of the user
     */
    public char getOption()
    {
        return option;
    }

    /**
     * This method aims to check and record user's choice
     */
    public void menuControl()
    {
        System.out.println("Choose an option: ");
        Scanner input = new Scanner(System.in);
        String userType = input.nextLine();
        while (userType.length() != 1)
        {
            System.out.println("Please press a right buttom!");
            userType = input.nextLine();  
        }
        option = userType.toUpperCase().charAt(0);
    }
}
