package io.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import io.model.IOGame;
import io.view.IOFrame;

/**
 * 
 * @author jwah4895
 * 
 */
public class IOController
{
	private IOFrame appFrame;
	
	private ArrayList<IOGame> projectGames;

	public IOController()
	{

	}
	
/**
 * 
 * @return
 */
	public ArrayList<IOGame> getProjectGames()
	{
		return projectGames;
	}
/**
 * 
 */
	public void start()
	{
		appFrame = new IOFrame(this);

	} 
	/**
	 * 
	 * @return
	 */
	public IOGame readGameInformaton()

	{
		String fileName = "save file.txt";//Without a path it will look to the directory of the project!
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		IOGame currentGame = null;
		int gameRanking = 0;
		String gameTitle = "";
		ArrayList<String> gameRules = new ArrayList<String>();
		
		
		
		
		/**
		 * Major Scanner methods!!!
		 * .next()
		 */
		try
		{
			fileReader = new Scanner(currentSaveFile);
			gameTitle = fileReader.nextLine();
			gameRanking = fileReader.nextInt();
			while(fileReader.hasNext())
			{
				gameRules.add(fileReader.nextLine());
			}
			currentGame = new IOGame(gameRules, gameRanking, gameTitle);
			fileReader.close();
		}
		catch(FileNotFoundException currentFileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, currentFileDoesNotExist.getMessage());
			
		}
		return currentGame;
		
	}
	/**
	 * Returns the save game file.0
	 * @return
	 */
	private String readAllGameInformation()
	{
		String fileContents = "";
		String fileName = "save file.txt";
		File currentSaveFile = new File(fileName);
		Scanner fileReader;
		
		try
		{
			fileReader = new Scanner (currentSaveFile);
			while(fileReader.hasNext());
			{
				fileContents += fileReader.nextLine();
			}
			fileReader.close();
		}
		catch(FileNotFoundException fileDoesNotExist)
		{
			JOptionPane.showMessageDialog(appFrame, fileDoesNotExist.getMessage());
		}
		return fileContents;
	}
	/**
	 * Converts text into a game.
	 * @param currentInfo
	 */
	private void convertTextToGames(String currentInfo)
	{
		String [] gameChunks = currentInfo.split(",");
		
		for(String currentBlock : gameChunks)
		{
			int currentIndex = currentBlock.indexOf("\n");
			String title = currentBlock.substring(0, currentIndex);
			int nextIndex = currentBlock.indexOf("\n", currentIndex);
			String ranking = currentBlock.substring(currentIndex+1, nextIndex);
			String rules = currentBlock.substring(nextIndex+1);
			IOGame currentGame = makeGameFromInput(title, ranking, rules);
			projectGames.add(currentGame);
			
		}
	}
	/**
	 * Returns a random file.
	 * @return
	 */
	public IOGame pickRandomGameFromSaveFile()
	{
		IOGame currentGame = null;

		String allInfo = readAllGameInformation();
		convertTextToGames(allInfo);
		int randomIndex = (int) (Math.random() * (double) projectGames.size());
		currentGame = projectGames.get(randomIndex);

		return currentGame;
	}
	 /**
	  * Make a game object from three strings so it brings everything together into one.
	  * @param gameTitle
	  * @param gameRanking
	  * @param gameRules
	  * @return
	  */
	public IOGame makeGameFromInput(String gameTitle, String gameRanking, String gameRules)
	{
		IOGame currentGame = new IOGame();
		currentGame.setGameTitle(gameTitle);
		
		if (checkNumberFormat(gameRanking))
		{
			currentGame.setFunRanking(Integer.parseInt(gameRanking));
			
		}
		else
		{
			return null;
		}
		
		String[] temp = gameRules.split("\n");
		ArrayList<String> tempRules = new ArrayList<String>();
		
		for (String tempWord : temp)
		{
			tempRules.add(tempWord);
		}
		currentGame.setGameRules(tempRules);
		
		return currentGame;
	}
	 /**
	  * This says that if you did'nt type a integer, it will give you a pop-up window saying to type a number.
	  * @param toBeParsed
	  * @return
	  */
	private boolean checkNumberFormat(String toBeParsed)
	{
		boolean isNumber = false;
		
		try
		{
			int valid = Integer.parseInt(toBeParsed);
			isNumber = true;
			
		}
		catch (NumberFormatException error)
		{
			JOptionPane.showMessageDialog(appFrame, "Please try again with an actual number for the ranking.");
		}
		
		return isNumber;
	}
/**
 * This method saves your input for the games on the hard drive.
 * @param currentGame
 */
	public void saveGameInformation(IOGame currentGame)
	{
		PrintWriter gameWriter;
		String saveFile = "save file.txt";
		projectGames.add(currentGame);
		
		try
		{
			gameWriter = new PrintWriter(saveFile); //Creates the save file;
			
			
			gameWriter.println(currentGame.getGameTitle());
			gameWriter.println(currentGame.getFunRanking());
			for(int count = 0; count < currentGame.getGameRules().size();count++)
			{
				gameWriter.println(currentGame.getGameRules().get(count));
			}
			gameWriter.close(); //Required to prevent corruption of data and maintain security of the file.
			
		}	
		catch(FileNotFoundException noFileExists)
		{
			JOptionPane.showMessageDialog(appFrame, "Could not create the save file. : (");
			JOptionPane.showMessageDialog(appFrame, noFileExists.getMessage());
		}
	}
	
}
