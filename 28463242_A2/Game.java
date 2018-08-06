import java.util.*;

/**
 * Generate a Game with an ArrayList of Team
 * and control all the teams
 * @author Yiwei Chen
 * @version 3.0 15 May 2018
 */

public class Game
{
    private ArrayList<Team> teams;

    /**
     * Default constructor for objects of class FootballWordCup
     */
    public Game()
    {
        teams = new ArrayList<Team>();
        importFile();
        setPlayersName();
    }

    /**
     * Non-default constructor for objects of class FootballWordCup
     */    
    public Game(ArrayList<Team> teams)
    {
        this.teams = teams;
    }

    /**
     * The method aims at record the result to an ArrayList
     */
    public ArrayList cupResult()
    {
        ArrayList<String> cupResult = new ArrayList();
        cupResult.add(finalWinner());
        cupResult.add(goldenBoot());
        cupResult.add(fairPlay());
        return cupResult;
    }

    /**
     * This method aims to format the result of all kinds of cards of a team.
     */      
    private void displayCards(Team whichTeam, int yellow, int red)
    {
        String cardsResult = whichTeam.getName();
        if (red == 0)
            System.out.println(cardsResult + displayOneCards(yellow, "yellow"));
        else if (yellow == 0)
            System.out.println(cardsResult + displayOneCards(red, "red"));
        else
        {
            System.out.println(cardsResult + displayOneCards(yellow, "yellow"));
            System.out.println("\t\t" + cardsResult + displayOneCards(red, "red"));
        }
    }

    /**
     * This method aims to display the result of a single match
     */    
    private void displayGameResult(Team team1, Team team2, int goals1, 
    int goals2, int[] cards)
    {
        System.out.println("Game result:\t" + team1.getName() + " " 
            + goals1 + " vs. " + team2.getName() + " " + goals2);
        System.out.print("Cards awarded:\t");
        if (cards[0] != 0 || cards[2] != 0)
        {
            displayCards(team1, cards[0], cards[2]);
            if (cards[1] != 0 || cards[3] != 0)
            {
                System.out.print("\t\t");
                displayCards(team2, cards[1], cards[3]);
            }
        }
        else if (cards[1] != 0 || cards[3] != 0)
            displayCards(team2, cards[1], cards[3]); 
        System.out.println("");
    }

    /**
     * This method aims to format the result of a single kind of cards.
     */    
    private String displayOneCards(int numberOfCards, String whichCard)
    {
        String cardsResult = " - " + numberOfCards + " " + whichCard ;
        if (numberOfCards == 1)
            cardsResult = cardsResult + " card.";
        else
            cardsResult = cardsResult + " cards.";
        return cardsResult;
    }

    /**
     * The method aims to display all players
     */
    public void displayPlayers()
    {
        ArrayList<String> playerState = new ArrayList();
        for (int i = 0; i < teams.size(); i++)
            for (int j = 0; j < teams.get(i).getTeamMemberSize(); j++)
                playerState.add(teams.get(i).getPlayer(j).getName() + " (" + teams.get(i).getName() + ")" 
                    + "-" + teams.get(i).getPlayer(j).getGoals());
        for (int i = 0; i < playerState.size(); i++)
            System.out.println(playerState.get(i));
        System.out.println("");
    }

    /**
     * The method aims to display the result table
     */
    public void displayTeams()
    {
        System.out.println();
        System.out.println("\t\tPlayed\tWon\tLost\tDrawn\tGoals\tPoints\tFair Play Score");
        System.out.println();
        for (int i = 0; i < teams.size(); i++)
            System.out.println(teams.get(i).getState());
        System.out.println("");
    }

    /**
     * This method aims to distrubute goals
     */    
    private void distrubuteGoals(Team team, int goals, boolean isFinal)
    {
        if (goals > 0 && isFinal == false)
        {
            ArrayList<Integer> goalsDistrubution = new ArrayList();
            for (int i = 0; i < goals; i++)
            {
                RandomNumber whoGetGoal = new RandomNumber(-1, team.getTeamMemberSize());
                int who = whoGetGoal.getRandomNumber();
                goalsDistrubution.add(who);
            } 
            team.setPlayersGoals(goalsDistrubution);
        }
    }

    /**
     * This method aims to return the result of fair play
     */
    private String fairPlay()
    {
        int minScore = teams.get(0).getFairScore();
        for (int i = 0; i < teams.size(); i++)
            if (minScore > teams.get(i).getFairScore())
                minScore = teams.get(i).getFairScore();
        ArrayList<Team> winners = new ArrayList<Team>();
        for (int i = 0; i < teams.size(); i++)
            if(teams.get(i).getFairScore() == minScore)
                winners.add(teams.get(i));
        String result = "Fair Play Award: " + winners.get(0).getName();
        for (int i = 1; i < winners.size(); i++)
            if(i == winners.size() - 1)
                result = result + " and " + winners.get(i).getName();
            else
                result = result + ", " + winners.get(i).getName();
        return result;
    }

    /**
     * This method aims to play the final
     */
    public void finalGame()
    {
        Team [] teamAfterMatch = match(teams.get(0), teams.get(1), true);
        teams.set(0, teamAfterMatch[0]);
        teams.set(1, teamAfterMatch[1]);
    }

    /**
     * This method aims to return the winner of cup
     */
    private String finalWinner()
    {
        Team winner = teams.get(0);
        for (int i = 0; i < teams.size(); i++)
            if (teams.get(i).getFinalWinner() == true)
                winner = teams.get(i);
        String result = "Football World Cup Winner: " + winner.getName();
        return result;
    }

    /**
     * The method aims to return the teams
     */
    public ArrayList getTeamList()
    {
        return teams;
    }

    /**
     * The method aims to return the result of golden boot
     */
    private String goldenBoot()
    {
        int maxScore = -1;
        for (int i = 0; i < teams.size(); i++)
            for (int j = 0; j < teams.get(i).getTeamMemberSize(); j++)
                if (maxScore < teams.get(i).getPlayer(j).getGoals())
                    maxScore = teams.get(i).getPlayer(j).getGoals();
        ArrayList<String> winners = new ArrayList();
        for (int i = 0; i < teams.size(); i++)
            for (int j = 0; j < teams.get(i).getTeamMemberSize(); j++)
                if (maxScore == teams.get(i).getPlayer(j).getGoals())
                    winners.add(teams.get(i).getPlayer(j).getName() + " from " + teams.get(i).getName());
        String result = "Golden Boot Award: ";
        for (int i = 0; i < winners.size(); i++)
            if (i == winners.size() - 1)
                result = result + winners.get(i);
            else
                result = result + winners.get(i) + "\n\t\t   ";
        return result;
    }

    /**
     * The method aims at loading the teams.txt document from File.
     */
    public void importFile()
    {
        IO input = new IO();
        ArrayList<String> teamInfo = new ArrayList();
        teamInfo = input.importFile("teams.txt");
        for (int i = 0; i < teamInfo.size(); i++)
        {
            String[] part = new String[2];
            part = teamInfo.get(i).split(",");
            Team team = new Team(part[0], Integer.valueOf(part[1]));
            teams.add(team); 
        }
    }

    /**
     * This method aims to generate card(s) with given probability and limt
     */
    private int makeCards(int probability, int limit)
    {
        boolean hasCard = false;
        int cards = 0;
        for (int count = 0; count < limit; count++)
        {
            RandomNumber randomCards = new RandomNumber(0, 101);
            int getCard = randomCards.getRandomNumber();
            if (getCard <= probability)
            {
                hasCard = true;
                cards++;
            }
            if (hasCard = false)
                break;
        }
        return cards;
    }

    /**
     * This method aims to play a single match between two given teams
     */    
    private Team[] match(Team team1, Team team2, boolean isFinal)
    {
        int goals1, goals2, goals3, difference;
        difference = team1.getRanking() - team2.getRanking(); 

        RandomNumber randomGoals; 
        randomGoals = new RandomNumber(-1, 6 + randomUpset());
        goals1 = randomGoals.getRandomNumber();
        randomGoals = new RandomNumber(-1, 6 - Math.abs(difference) + randomUpset());
        goals2 = randomGoals.getRandomNumber(); 
        int[] cards = teamCards(team1, team2, isFinal);

        if (difference > 0)
        {
            goals3 = goals1;
            goals1 = goals2;
            goals2 = goals3;
        }
        distrubuteGoals(team1, goals1, isFinal);
        distrubuteGoals(team2, goals2, isFinal);
        recordResult(team1, team2, goals1, goals2, isFinal, cards);
        displayGameResult(team1, team2, goals1, goals2, cards);
        Team [] teamAfterMatch = {team1, team2};
        return teamAfterMatch;
    }

    /**
     * This method aims to play one single penalty
     */    
    private int onePenaltyShoot(int goals)
    {
        RandomNumber shot = new RandomNumber(0, 3);
        if (shot.getRandomNumber() == 1)
            goals++;
        return goals;
    }

    /**
     * The method aims at outporting the cup result to statistics.txt
     */
    public void outportFile(boolean canResult)
    {
        IO output = new IO();
        String filename = "statistics.txt";
        if (canResult)
            output.outportFile(cupResult(), filename);
        else 
        {
            ArrayList<String> finalNotPlayed = new ArrayList();
            finalNotPlayed.add("Final is not palyed.");
            output.outportFile(finalNotPlayed, filename);
        }
    }

    /**
     * This method aims to play a penalty match
     */
    private int[] playPenaltyShootOut(Team team1, Team team2, int[] cards)
    {
        int goals1 = 0;
        int goals2 = 0;
        for (int i = 0; i < 5; i++)
        {
            if (team1.getTeamMemberSize() - cards[2] > 0)
                goals1 = onePenaltyShoot(goals1);
            if (team2.getTeamMemberSize() - cards[3] > 0)
                goals2 = onePenaltyShoot(goals2);
        }
        while (goals1 == goals2)
        {
            for (int i = 0; i < team1.getTeamMemberSize() - cards[2]; i++)
                goals1 = onePenaltyShoot(goals1);
            for (int i = 0; i < team2.getTeamMemberSize() - cards[3]; i++)    
                goals2 = onePenaltyShoot(goals2);            
        }
        int[] result = {goals1, goals2};
        return result;
    }

    /**
     * This method aims to play the preliminary
     */
    public void preliminary()
    {
        for (int i = 0; i < teams.size() - 1; i++)
            for (int j = teams.size() - 1; j > i; j--)
            {
                Team [] teamAfterMatch = match(teams.get(i), teams.get(j), false);
                teams.set(i, teamAfterMatch[0]);
                teams.set(j, teamAfterMatch[1]);
            }
    }

    /**
     * This method aims to generate a random upset
     */
    private int randomUpset()
    {
        RandomNumber random = new RandomNumber(-1, 3);
        int randomUpset = random.getRandomNumber();
        return randomUpset;
    }

    /**
     * This method aims to record the result of a single match
     */    
    private void recordResult(Team team1, Team team2, int goals1, int goals2, boolean isFinal, int[] cards)
    {
        if (isFinal == false && goals1 == goals2)
        {
            team1.setDrawn(team1.getDrawn() + 1);
            team2.setDrawn(team2.getDrawn() + 1);         
        }
        else if (isFinal == true && goals1 == goals2)
        {
            int[] result = playPenaltyShootOut(team1, team2, cards);
            goals1 = result[0];
            goals2 = result[1];
        }
        if (goals1 < goals2)
        {
            if (isFinal == true)
                team2.setFinalWinner(true);
            else
            {
                team1.setLost(team1.getLost() + 1);
                team2.setWon(team2.getWon() + 1); 
            }
        }
        else if (goals1 > goals2)
        {
            if (isFinal == true)
                team1.setFinalWinner(true);
            else
            {
                team1.setWon(team1.getWon() + 1);
                team2.setLost(team2.getLost() + 1); 
            }
        }
    }

    /**
     * The method aims to reset all the teams.
     */
    public void restart()
    {
        for (int i = 0; i < teams.size(); i ++)       
            teams.get(i).restart();
    }

    /**
     * The method aims set all players name
     */
    private void setPlayersName()
    {
        for (int i = 0; i < teams.size(); i++)
            teams.get(i).setPlayersName();
    }

    /**
     * The method aims to sort teams by goals and points
     */
    public void sortList()
    {
        for (int i = 1; i < teams.size(); i++)
            for (int j = i; j > 0 && teams.get(j).getGoals() >= teams.get(j - 1).getGoals(); j--)
            {
                RandomNumber sort = new RandomNumber(0, 3);
                if (teams.get(j).getGoals() > teams.get(j - 1).getGoals() || sort.getRandomNumber() == 1)
                {
                    Team swap = teams.get(j);
                    teams.set(j, teams.get(j - 1));
                    teams.set(j - 1, swap);
                } 
            }
        for (int i = 1; i < teams.size(); i++)
            for (int j = i; j > 0 && teams.get(j).getPoints() > teams.get(j - 1).getPoints(); j--)
            {
                Team swap = teams.get(j);
                teams.set(j, teams.get(j - 1));
                teams.set(j - 1, swap);                
            }
    }

    /**
     * This method aims to generate and return the card(s) result of two teams in a match.
     */    
    private int[] teamCards(Team team1, Team team2, boolean isFinal)
    {
        int fairScore[] = new int[2];
        int cards[] = new int[4];
        Team[] twoTeams = {team1, team2};
        for (int i = 0; i < 2; i++)
        {
            cards[i] = makeCards(28, twoTeams[i].getTeamMemberSize() * 2);
            cards[i + 2] = makeCards(7, twoTeams[i].getTeamMemberSize());
            if (!isFinal)
                fairScore[i] = cards[i] + 2 * cards[i + 2];
        }
        team1.setFairScore(team1.getFairScore() + fairScore[0]);
        team2.setFairScore(team2.getFairScore() + fairScore[1]); 
        return cards;
    }

}
