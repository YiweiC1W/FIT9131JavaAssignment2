import java.util.*;
/**
 * Generate a World Cup 
 * and control the menu
 * @author Yiwei Chen
 * @version 3.0 15 May 2018
 */
public class WorldCup
{
    private Game game;
    private boolean canFinal;
    private boolean canResult;
    private Menu menu;

    /**
     * Defualt constructor for objects of class WroldCup
     */
    public WorldCup()
    {
        ArrayList<String> menuList = new ArrayList();
        menuList.add("A. Play Preliminary Stage");
        menuList.add("B. Play Final");
        menuList.add("C. Display Teams");
        menuList.add("D. Display Players");
        menuList.add("E. Display Cup Result");
        menuList.add("X. Close");
        menu = new Menu(menuList);
        game = new Game();
        canFinal = false;
        canResult = false;
        displayMenu();
        menuControl();
    }

    /**
     *Non-defualt constructor for objects of class WroldCup
     */    
    public WorldCup(Game game, boolean canFinal, boolean canResult, Menu menu)
    {
        this.game = game;
        this.canFinal = canFinal;
        this.canResult = canResult;
        this.menu = menu;
    }

    /**
     * This method aim to display the cup result
     */
    private void displayCupResult()
    {
        if (canResult)
        {
            ArrayList<String> cupResult = game.cupResult();
            for (String result : cupResult)
                System.out.println(result);
        }
        else
            System.out.println("Please play the game first");
    }

    /**
     * This method aim to display the menu
     */
    private void displayMenu()
    {
        System.out.println();
        menu.displayMenu();
    }

    /**
     * This method aim to display all players
     */
    private void displayPlayers()
    {
        game.displayPlayers();
    }

    /**
     * This method aim to display all teams
     */
    private void displayTeams()
    {
        game.displayTeams();
    }

    /**
     * This method aim to control the menu
     */
    private void menuControl()
    {
        boolean exit = false;
        while (!exit)
        {
            System.out.println();
            menu.menuControl();
            char option = menu.getOption();
            switch(option)
            {
                case 'A': playPreliminaryStage();
                break;
                case 'B': playFinal();
                break;
                case 'C': displayTeams();
                break;
                case 'D': displayPlayers();
                break;
                case 'E': displayCupResult();
                break;
                case 'X': game.outportFile(canResult);
                exit = true;
                break;
                default:
                System.out.println("Please press a right buttom!");
            }
            displayMenu();
        }
    }

    /**
     * This method aim to play the final
     */
    private void playFinal()
    {
        if (canFinal == true)
        {
            game.finalGame();   
            canResult = true;
            canFinal = false;
            displayCupResult();
        }
        else
            System.out.println("Please play preliminary stage first");
    }

    /**
     * This method aim to play the Preliminary
     */
    private void playPreliminaryStage()
    {
        game.restart();
        game.preliminary();
        game.sortList();
        game.displayTeams();
        canFinal = true;
        canResult = false;
    }
}
