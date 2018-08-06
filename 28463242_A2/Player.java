
/**
 * Generate and control a Player
 * with his/her name and goals
 * @author Yiwei Chen
 * @version 3.0 15 May 2018
 */
public class Player
{
    private String name;
    private int goals;

    /**
     * Defualt constructor for objects of class Player
     */
    public Player()
    {
        name = "";
        goals = 0;
    }

    /**
     * Non-defualt constructor for objects of class Player
     */
    public Player(String name, int goals)
    {
        this.name = name;
        if (isValidGoals(goals))
            this.goals = goals;
        else
            this.goals = 0;          
    }

    /**
     * The method of getting the goals of preliminary
     */ 
    public int getGoals()
    {
        return goals;
    }

    /**
     * The method of getting a name.
     */
    public String getName()
    {
        return name;
    }

    /**
     *  This method is the validation for goals
     */
    private boolean isValidGoals(int goals)
    {
        if (goals < 0)
            return false;
        else
            return true;
    }
    
    /**
     * The method of setting the goals of preliminary
     */
    public void setGoals(int goals)
    {
        if (isValidGoals(goals))
            this.goals = goals;
        else
            System.out.println("Goal number should be greater than or equal to zero");
    }

    /**
     * The method of setting a name.
     */
    public void setName(String name)
    { 
        this.name = name;
    }

}
