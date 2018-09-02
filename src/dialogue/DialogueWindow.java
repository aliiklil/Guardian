package dialogue;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import main.Main;
import manager.CharacterManager;

public class DialogueWindow {

	private Image image = new Image("resources/dialogue_window.png");
	private boolean active = false;
	private ArrayList<Dialogue> startingDialogues;
	private ArrayList<Dialogue> currentDialogues;
	
	private Font font = new Font(Font.MONOSPACED, Font.BOLD, 25);
	private TrueTypeFont ttf = new TrueTypeFont(font, true);
	
	private int selectedOption = 0;
	
	private Input input = Main.appGameContainer.getInput();
	
	private int sentenceCount = 0;
	
	private String currentSentence;
	private String currentSpeaker;
	
	public DialogueWindow() throws SlickException {
		
	}
	
	public void showWindow(ArrayList<Dialogue> startingDialogues) {
		
		active = true;
		this.startingDialogues = startingDialogues;
		currentDialogues = startingDialogues;
		
	}
	
	public void update() throws SlickException {
		
		if(active) {
			if(sentenceCount == 0) {
				if(input.isKeyPressed(Input.KEY_UP)) {
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
								
				if(input.isKeyPressed(Input.KEY_DOWN)) {
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
			
			if(CharacterManager.getPlayer().isYPressed()) {
				
				if(selectedOption != currentDialogues.size()) {
			
					if(sentenceCount < currentDialogues.get(selectedOption).getSentences().size() - 1) {
						
						sentenceCount++;
						
						currentSentence = currentDialogues.get(selectedOption).getSentences().get(sentenceCount).getText();
						currentSpeaker = currentDialogues.get(selectedOption).getSentences().get(sentenceCount).getSpeakerName();
		
					} else if(sentenceCount == currentDialogues.get(selectedOption).getSentences().size() - 1 && currentDialogues.get(selectedOption).hasChildDialogues()) {
						
						currentDialogues = currentDialogues.get(selectedOption).getChildDialogues();
						sentenceCount = 0;
						selectedOption = 0;
						
					} else if(sentenceCount == currentDialogues.get(selectedOption).getSentences().size() - 1 && !currentDialogues.get(selectedOption).hasChildDialogues()) {
						
						currentDialogues = startingDialogues;
						selectedOption = 0;
						sentenceCount = 0;
						
					} 
					
				} else if(selectedOption == currentDialogues.size() && currentDialogues == startingDialogues) {
					active = false;
					selectedOption = 0;
					sentenceCount = 0;
				}
			
			}
			
		}		

		
	}
	
	public void render(Graphics g) {
		if(active) {
			g.drawImage(image, 0, 0);
			
			if(sentenceCount == 0) {
			
				for(int i = 0; i <= currentDialogues.size(); i++) {
					Color fontColor;
					if(i == selectedOption) {
						fontColor = Color.white;
					} else {
						fontColor = Color.gray;
					}
					
					if(i == currentDialogues.size() && currentDialogues == startingDialogues) {
						ttf.drawString(492, 763 + i * 30, "End", fontColor);
					} 
					
					if(i < currentDialogues.size())  {
						ttf.drawString(492, 763 + i * 30, currentDialogues.get(i).getSentences().get(0).getText(), fontColor);
					}
					
				}	
				
			} else {
				Color fontColor;
				if(!currentSpeaker.equals("Hero")) {
					fontColor = Color.yellow;
				} else {
					fontColor = Color.white;
				}
				ttf.drawString(Main.WIDTH/2 - ttf.getWidth(currentSpeaker)/2, 763, currentSpeaker, fontColor);
				ttf.drawString(492, 808, currentSentence, fontColor);
				
			}
		}
	}

	public boolean isActive() {
		return active;
	}
}