import java.util.Scanner;
import java.util.ArrayList;

/**
 * Generate a Team with an ArrayList of Player
 * and control all the team members(players)
 * @author Yiwei Chen
 * @version 3.0 15 May 2018
 */
public class Team
{
    private String name;
    private int ranking;
    private int won;
    private int lost;
    private int drawn;
    private int fairScore;
    private boolean isFinalWinner;
    private ArrayList<Player> teamMember;
    private int howManyMember;

    /**
     * Non-defualt constructor for objects of class Team
     */
    public Team(String name, int ranking)
    {
        teamMember = new ArrayList();
        howManyMember = 2;
        this.name = name;
        if (ranking > 0)
            this.ranking = ranking;
        else
            System.out.println("Invalid ranking");
        won = 0;
        lost = 0;
        drawn = 0;
        fairScore = 0;
        isFinalWinner = false;
        addPlayer();
    }

    /**
     * Defualt constructor for objects of class Team
     */
    public Team()
    {
    }

    /**
     * This method aims to add new players to a team until the number of members is suitable
     */    
    private void addPlayer()
    {
        Player player;
        int i = teamMember.size();
        for ( ; i < howManyMember; i++)
        {
            player = new Player();
            teamMember.add(player);    
        }
    }

    /**
     * This method aims to check whether player name is in right format, especially about the alphabet and length
     */  
    public boolean checkAlphabet(String string, int count)
    {
        if (string.length() > 20)
        {
            System.out.println("It is too long");
            return false;
        }
        else
        {
            int countLetter = 0;
            string = string.toUpperCase();
            for (int i = 0; i < string.length(); i++)
                if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z')
                    countLetter ++;
            if (countLetter < 2)
            {
                System.out.println("There must be a minimum of two alphabetic characters");
                return false;
            }
            if (count + countLetter == string.length())
                return true;
            else
            {
                System.out.println("It should be alphabets");
                return false;
            }
        }
    }

    /**
     * This method aims to check whether has same player name in a team
     */      
    private boolean checkStringEqual(String newName)
    {
        for (int i = 0; i < teamMember.size(); i ++)
            if (newName.equals(teamMember.get(i).getName()))
            {
                System.out.println("Name should be unique in a team");
                return false;
            }
        return true;
    }

    /**
     * This method aims to check whether player name is in right format, especially about the '-'
     */  
    private boolean checkStringHasChar(String string, char check)
    {
        int count = 0;        
        for (int i = 0; i < string.length(); i++)
        {
            if (string.charAt(0) == check || string.charAt(string.length() - 1) == check)
            {
                System.out.println("The first and the last character cannot be '" + check + "'");
                return false;
            }
            else if (string.charAt(i) == check)
                count ++;
        }
        if (count > 1)
        {
            System.out.println("No more than one '" + check + "'");
            return false;
        }
        else
            return checkAlphabet(string, count);
    }

    /**
     * This method aims to get how many game this team drawn
     */   
    public int getDrawn()
    {
        return drawn;
    }

    /**
     * This method aims to return the fair score
     */   
    public int getFairScore()
    {
        return fairScore;
    }

    /**
     * This method aims to get whether this team won the final
     */   
    public boolean getFinalWinner()
    {
        return isFinalWinner;
    }

    /**
     * This method aims to return the goals of a team (Without the goals of the final)
     */  
    public int getGoals()
    {
        int goals = 0;
        for (int i = 0; i < teamMember.size(); i++)
            goals = goals + teamMember.get(i).getGoals();
        return goals;
    }

    /**
     * This method aims to get how many game this team lost
     */   
    public int getLost()
    {
        return lost;
    }

    /**
     * This method aims to return the team name
     */   
    public String getName()
    {
        return name;
    }

    /**
     * This method aims to return a certain player
     */      
    public Player getPlayer(int i)
    {
        return teamMember.get(i);
    }

    /**
     * This method aims to get how points this team got
     */   
    public int getPoints()
    {
        return won * 3 + drawn;
    }

    /**
     * This method aims to return the ranking
     */   
    public int getRanking()
    {
        return ranking;
    }

    /**
     * This method aims to return the state of a team (Without the result of the final)
     */  
    public String getState()
    {
        int goals = getGoals();
        if (name.length() >= 16)
            return name.substring(0,15)+"\t"+(won+lost+drawn)+"\t"+won+
            "\t"+lost+"\t"+drawn+"\t"+goals+"\t"+(3*won+drawn)+"\t"+fairScore; 
        else if (name.length() >= 8)
            return name+"\t"+(won+lost+drawn)+"\t"+won+
            "\t"+lost+"\t"+drawn+"\t"+goals+"\t"+(3*won+drawn)+"\t"+fairScore; 
        else            
            return name+"\t\t"+(won+lost+drawn)+"\t"+won+
            "\t"+lost+"\t"+drawn+"\t"+goals+"\t"+(3*won+drawn)+"\t"+fairScore; 
    }

    /**
     * This method aims to return how many players in a team
     */  
    public int getTeamMemberSize()
    {
        return teamMember.size();
    }

    /**
     * This method aims to get how many game this team won
     */   
    public int getWon()
    {
        return won;
    }

    /**
     * This method aims to reset a team and it's players
     */      
    public void restart()
    {
        won = 0;
        lost = 0;
        drawn = 0;
        fairScore = 0;
        isFinalWinner = false;
        for (int i = 0; i < teamMember.size(); i++)
            teamMember.get(i).setGoals(0);
    }

    /**
     * This method aims to set how many game this team drawn
     */   
    public void setDrawn(int drawn)
    {
        this.drawn = drawn;
    }

    /**
     * This method aims to set fair socre
     */       
    public void setFairScore(int fairScore)
    {
        this.fairScore = fairScore;
    }

    /**
     * This method aims to set whether this team won the final
     */   
    public void setFinalWinner(boolean isFinalWinner)
    {
        this.isFinalWinner = isFinalWinner;
    }

    /**
     * This method aims to set how many game this team lost
     */   
    public void setLost(int lost)
    {
        this.lost = lost;
    }

    /**
     * This method aims to set the team name
     */   
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method aims to distrubute goals between(or among) all team members
     */  
    public void setPlayersGoals(ArrayList<Integer> goalsDistrubution)
    {
        for (int i = 0; i < goalsDistrubution.size(); i++)
        {
            int who = goalsDistrubution.get(i);
            teamMember.get(who).setGoals(teamMember.get(who).getGoals() + 1);
        }
    }

    /**
     * This method aims to set all players' name of a team
     */      
    public void setPlayersName()
    {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < teamMember.size(); i ++)
        {
            String newName = "";
            for (int count = 0; count < 2;)
            {
                System.out.println("Name the player" + (i + 1) + " from " + name + " :");
                newName = input.nextLine().trim();
                if (!checkStringHasChar(newName, '-'))
                    count ++;
                else if (!checkStringEqual(newName))
                    count ++;
                else
                    break;
                if (count > 1)
                {
                    newName = "player" + (i+1) + "-" + name;
                    System.out.println("System allocated a defualt name: " + newName);
                }
                System.out.println();
            }
            teamMember.get(i).setName(newName);
        }
        System.out.println();
    }

    /**
     * This method aims to set the ranking
     */   
    public void setRanking(int ranking)
    {
        this.ranking = ranking;
    }

    /**
     * This method aims to set how many game this team won
     */   
    public void setWon(int won)
    {
        this.won = won;
    }
}
