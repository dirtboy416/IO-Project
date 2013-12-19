package io.model;

import java.util.ArrayList;

/**
 * Runs the gameRules, funRanking, gameTitle.
 * @author jwah4895
 * 
 */
public class IOGame
{
	private ArrayList<String> gameRules;
	private int funRanking;
	private String gameTitle;
/**
 * Tells everything to start as a blank line.
 */
	public IOGame()
	{
		gameRules = new ArrayList<String>();
		funRanking = 0;
		gameTitle = "";

	}
/**
 * Makes an arrayList of gamesRules, funRanking, gameTitle.
 * @param gameRules
 * @param funRanking
 * @param gameTitle
 */
	public IOGame(ArrayList<String> gameRules, int funRanking, String gameTitle)
	{
		this.gameRules = gameRules;

	}
/**
 * Returns ArrayList.
 * @return
 */
	public ArrayList<String> getGameRules()
	{
		return gameRules;
	}
	/**
	 * This sets the gameRules as a new gameRule.
	 * @param gameRules
	 */

	public void setGameRules(ArrayList<String> gameRules)
	{
		this.gameRules = gameRules;
	}
/**
 * Returns funRanking.
 * @return
 */
	public int getFunRanking()
	{
		return funRanking;
	}
/**
 * Tells funRanking that it is this funRanking that you typed in.
 * @param funRanking
 */
	public void setFunRanking(int funRanking)
	{
		this.funRanking = funRanking;
	}
/**
 * This returns the gameTitle.
 * @return
 */
	public String getGameTitle()
	{
		return gameTitle;
	}
/**
 * It says that what you typed in is set as the title of the game, the game equals this game.
 * @param gameTitle
 */
	public void setGameTitle(String gameTitle)
	{
		this.gameTitle = gameTitle;
	}
}
