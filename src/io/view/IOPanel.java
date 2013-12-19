package io.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.controller.IOController;
import io.model.IOGame;

import javax.swing.*;
/**
 * Makes what will be inside your panel when you run the program.
 * @author jwah4895
 *
 */
public class IOPanel extends JPanel
{

	private IOController baseController;
	private JButton saveButton;
	private JButton loadButton;
	private JTextField titleField;
	private JTextField rankingField;
	private JTextArea rulesArea;
	private JLabel rulesLabel;
	private JLabel rankingLabel;
	private JLabel titleLabel;
	private JLabel gameCountLabel;
	private SpringLayout baseLayout;
	private SpringLayout baseLayout_1;
/**
 * Places text inside your panel.
 * @param baseController
 */
	public IOPanel(IOController baseController)
	{
		this.baseController = baseController;

		saveButton = new JButton("save the game stuff");
		loadButton = new JButton("load the game stuff");
		titleField = new JTextField(15);
		titleLabel = new JLabel("Game title:");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("Game Ranking:");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("Game Rules:");
		gameCountLabel = new JLabel("Current game count:");
		
		baseLayout = new SpringLayout();
		
		baseLayout_1 = new SpringLayout();

		setupPanel();
		setupLayout();
		setupListeners();

	}
/**
 * This is how the panel looks and whats inside it.
 */
	public void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.MAGENTA);
		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(saveButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(loadButton);

	}
/**
 * This tells where everything will be placed inside the panel.
 */
	public void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 42, SpringLayout.EAST, saveButton);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, loadButton);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 33, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 29, SpringLayout.SOUTH, rulesArea);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesArea, 31, SpringLayout.SOUTH, rankingField);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 28, SpringLayout.EAST, rulesLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesLabel, 21, SpringLayout.SOUTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.EAST, rulesLabel, 0, SpringLayout.EAST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingField, -3, SpringLayout.NORTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingField, 22, SpringLayout.EAST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingLabel, 17, SpringLayout.SOUTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 3, SpringLayout.NORTH, titleField);
		baseLayout.putConstraint(SpringLayout.NORTH, titleField, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, titleField, 84, SpringLayout.WEST, this);
		baseLayout_1.putConstraint(SpringLayout.NORTH, rulesLabel, 24,
				SpringLayout.SOUTH, rankingLabel);
		baseLayout_1.putConstraint(SpringLayout.NORTH, rankingLabel, 3,
				SpringLayout.NORTH, rankingField);
		baseLayout_1.putConstraint(SpringLayout.WEST, rankingLabel, 0,
				SpringLayout.WEST, rulesLabel);
		baseLayout_1.putConstraint(SpringLayout.WEST, rankingField, 128,
				SpringLayout.WEST, this);
		baseLayout_1.putConstraint(SpringLayout.NORTH, rankingField, 18,
				SpringLayout.SOUTH, titleField);
		baseLayout_1.putConstraint(SpringLayout.NORTH, titleField, -3,
				SpringLayout.NORTH, titleLabel);
		baseLayout_1.putConstraint(SpringLayout.EAST, titleField, 0,
				SpringLayout.EAST, rulesArea);
		baseLayout_1.putConstraint(SpringLayout.NORTH, rulesArea, 6,
				SpringLayout.SOUTH, rulesLabel);
		baseLayout_1.putConstraint(SpringLayout.WEST, rulesArea, 72,
				SpringLayout.WEST, this);
		baseLayout_1.putConstraint(SpringLayout.WEST, rulesLabel, 22,
				SpringLayout.WEST, this);
		baseLayout_1.putConstraint(SpringLayout.NORTH, saveButton, 266,
				SpringLayout.NORTH, this);
		baseLayout_1.putConstraint(SpringLayout.NORTH, titleLabel, 39,
				SpringLayout.NORTH, this);
		baseLayout_1.putConstraint(SpringLayout.WEST, titleLabel, 20,
				SpringLayout.WEST, this);
		baseLayout_1.putConstraint(SpringLayout.WEST, saveButton, 77,
				SpringLayout.WEST, this);

	}
	/**
	 * This is a listener.
	 */

	private void setupListeners()
	{

		saveButton.addActionListener(new ActionListener()
		{
			/**
			 * This checks if what you typed in is actually an integer.
			 */
			public void actionPerformed(ActionEvent click)
			{
				IOGame tempGame = baseController.makeGameFromInput(
						titleField.getText(), rankingField.getText(),
						rulesArea.getText());
				if (tempGame != null)
				{
					baseController.saveGameInformation(tempGame);

				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Try again with a valid number");
				}
			}
		});

		loadButton.addActionListener(new ActionListener()
		{
			/**
			 * This will pull up the last game saved.
			 */
			public void actionPerformed(ActionEvent click)
			{
				IOGame tempGame = baseController.readGameInformaton();
				if(tempGame != null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame.getFunRanking()));
					String tempRules = "";
					for(String currentRule : tempGame.getGameRules() )
					{
						tempRules += currentRule + "\n";
					}
					rulesArea.setText(tempRules);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check the save file make sure it is in order.");
				}
			}
				

		});
	}

}
