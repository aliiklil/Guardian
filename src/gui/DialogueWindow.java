package gui;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import dialogue.Dialogue;
import main.Game;
import main.Main;
import manager.ItemTypeManager;
import manager.MobManager;
import models.Item;
import models.Player;

public class DialogueWindow {

	private Image image = new Image("resources/dialogue_window.png");
	private boolean windowOpen = false;
	private ArrayList<Dialogue> startingDialogues;
	private ArrayList<Dialogue> currentDialogues;
	
	private Font font = new Font(Font.MONOSPACED, Font.BOLD, 25);
	private TrueTypeFont ttf = new TrueTypeFont(font, true);
	
	private int selectedOption;
	private int selectedStartingOption;
	
	private Input input = Main.appGameContainer.getInput();
	
	private int sentenceCount = 0;
	
	private String currentSentence;
	private String currentSpeaker;
	
	private final int MAX_CHAR_AMOUNT_PER_LINE = 62;
	
	private String currentlyDisplayedText;
	private final int TIME_FOR_NEXT_CHARACTER = 20;
	private long timeLastCharacterWasAdded;
	private int addedCharactersCounter;
		
	public DialogueWindow() throws SlickException {
		
	}
	
	public void showWindow(ArrayList<Dialogue> startingDialogues) {
		
		windowOpen = true;
		this.startingDialogues = startingDialogues;
		currentDialogues = startingDialogues;
		
	}
	
	public void update() throws SlickException {
				
		if(windowOpen) {

			if(selectedOption != currentDialogues.size() && currentDialogues.get(selectedOption) != null) {
				Game.getQuestLogic().updateQuestLogic(currentDialogues.get(selectedOption));
			} else {
				Game.getQuestLogic().updateQuestLogic(null);
			}
			
			if(sentenceCount == 0) {
				if(MobManager.getPlayer().isKeyUpPressed()) {
					if(selectedOption > 0) {
						selectedOption--;
					} else if(selectedOption == 0) {
						if(currentDialogues == startingDialogues) {
							selectedOption = currentDialogues.size();
						} else {
							selectedOption = currentDialogues.size() - 1;
						}
					}
				}
								
				if(MobManager.getPlayer().isKeyDownPressed()) {
					if(currentDialogues == startingDialogues) {
						if(selectedOption < currentDialogues.size()) {
							selectedOption++;
						} else if(selectedOption == currentDialogues.size()) {
							selectedOption = 0;
						}
					} else {
						if (selectedOption < currentDialogues.size() - 1) {
							selectedOption++;
						} else if(selectedOption == currentDialogues.size() - 1) {
							selectedOption = 0;
						}
					}
				}
			}
			
			if(MobManager.getPlayer().isYPressed() || (selectedOption != currentDialogues.size() && currentDialogues.get(selectedOption).getSentences().get(sentenceCount).getText().isEmpty())) {
				
				if(sentenceCount > 0 && currentlyDisplayedText.length() != currentSentence.length()) {
					
					currentlyDisplayedText = currentSentence;
					addedCharactersCounter = currentSentence.length();
					timeLastCharacterWasAdded = System.currentTimeMillis();
					
				} else if(selectedOption != currentDialogues.size()) {

					if(sentenceCount < currentDialogues.get(selectedOption).getSentences().size() - 1) {
						
						Game.getLearning().checkIfPlayerLearns(currentDialogues.get(selectedOption));
						Game.getBlacksmithing().checkIfPlayerIsBlacksmithing(currentDialogues.get(selectedOption));
						Game.getAlchemy().checkIfPlayerIsUsingAlchemy(currentDialogues.get(selectedOption));
						Game.getRuneForging().checkIfPlayerIsUsingRuneTable(currentDialogues.get(selectedOption));
						
						sentenceCount++;
						
						currentSentence = currentDialogues.get(selectedOption).getSentences().get(sentenceCount).getText();						
						currentSpeaker = currentDialogues.get(selectedOption).getSentences().get(sentenceCount).getSpeakerName();
						
						timeLastCharacterWasAdded = System.currentTimeMillis();
						addedCharactersCounter = 0;
						currentlyDisplayedText = "";
						
						if(currentDialogues == startingDialogues) {
							selectedStartingOption = selectedOption;
						}
						
						checkIfPlayerTrades(currentDialogues.get(selectedOption));

					} else if(sentenceCount == currentDialogues.get(selectedOption).getSentences().size() - 1 && currentDialogues.get(selectedOption).hasChildDialogues()) {
						
						currentDialogues = currentDialogues.get(selectedOption).getChildDialogues();
						sentenceCount = 0;
						selectedOption = 0;
						
					} else if(sentenceCount == currentDialogues.get(selectedOption).getSentences().size() - 1 && !currentDialogues.get(selectedOption).hasChildDialogues()) {
						
						if(currentDialogues.get(selectedOption).hasNewStartingDialogues()) {

							for(Dialogue dialogue : currentDialogues.get(selectedOption).getNewStartingDialogues()) {
								startingDialogues.add(dialogue);
							}
							
						}
	
						int selectedStartingOptionBefore = selectedStartingOption;
						
						if(!startingDialogues.get(selectedStartingOption).isForLearning() || startingDialogues.get(selectedStartingOption).getChildDialogues().get(selectedOption).getSentences().get(0).getText().equals("Back")) {
							currentDialogues = startingDialogues;
							selectedStartingOption = 0;
						}
						
						if(!startingDialogues.isEmpty() && !startingDialogues.get(selectedStartingOptionBefore).isPermanent()) {
							startingDialogues.remove(selectedStartingOptionBefore);
						}

						selectedOption = 0;
						sentenceCount = 0;
						
					}
					
				} else if(selectedOption == currentDialogues.size() && currentDialogues == startingDialogues) {
					windowOpen = false;
					selectedOption = 0;
					sentenceCount = 0;
				}
			
			}
			
		}		
		
	}
	
	private void checkIfPlayerTrades(Dialogue currentDialogue) {
		
		if(currentDialogue.getSentences().get(0).getText().equals("Show me your goods.") && sentenceCount == 1) {
			
			Player player = MobManager.getPlayer();
			player.getTradingWindow().setWindowOpen(true);
			player.getTradingWindow().setTimestamp(System.currentTimeMillis());
			
			windowOpen = false;
			sentenceCount = 0;
			selectedStartingOption = 0;
					
		}
		
	}
	
	
	
	
	
	
	public void render(Graphics g) {
		if(windowOpen) {
			g.drawImage(image, 0, 0);
			
			if(sentenceCount == 0) {
			
				for(int i = 0; i <= currentDialogues.size(); i++) {
					Color fontColor;
					if(i == selectedOption) {
						fontColor = Color.black;
					} else {
						fontColor = Color.gray;
					}
					
					if(i == currentDialogues.size() && currentDialogues == startingDialogues) {
						ttf.drawString(495, 763 + i * 30, "End", fontColor);
					} 
					
					if(i < currentDialogues.size())  {
						ttf.drawString(495, 763 + i * 30, currentDialogues.get(i).getSentences().get(0).getText(), fontColor);
					}
					
				}	
				
			} else {
				Color fontColor;
				if(!currentSpeaker.equals("Hero")) {
					fontColor = Color.gray;
				} else {
					fontColor = Color.black;
				}
				ttf.drawString(Main.WIDTH/2 - ttf.getWidth(currentSpeaker)/2, 763, currentSpeaker, fontColor);
								
				
				
				if(System.currentTimeMillis() - timeLastCharacterWasAdded > TIME_FOR_NEXT_CHARACTER && addedCharactersCounter < currentSentence.length()) {
					currentlyDisplayedText = currentlyDisplayedText + currentSentence.substring(addedCharactersCounter, addedCharactersCounter + 1);
					timeLastCharacterWasAdded = System.currentTimeMillis();
					addedCharactersCounter++;
				}
				
				
				
				
				ArrayList<String> lines = new ArrayList<String>();
				
				String restText = currentlyDisplayedText;
				while(true) {
					
					if(restText.length() >= 62) {
						lines.add(restText.substring(0, restText.substring(0, 62).lastIndexOf(" ")));
						restText = restText.substring(restText.substring(0, 62).lastIndexOf(" ") + 1, restText.length());
					
					} else {
						lines.add(restText);
						break;
					}
					
				}
			
				for(int i = 0; i < lines.size(); i++) {
					ttf.drawString(495, 808 + i * 30, lines.get(i), fontColor);
				}
					
			}
		}
	}

	public boolean isWindowOpen() {
		return windowOpen;
	}

	public void setWindowOpen(boolean windowOpen) {
		this.windowOpen = windowOpen;
	}
	
}