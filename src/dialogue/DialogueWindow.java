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
			
			if(input.isKeyPressed(Input.KEY_UP) && selectedOption > 0) {
				selectedOption--;
			}
			
			if(input.isKeyPressed(Input.KEY_DOWN)) {
				if(selectedOption < currentDialogues.size() && currentDialogues == startingDialogues) {
					selectedOption++;
				}
				
				if (selectedOption < currentDialogues.size() - 1 && currentDialogues != startingDialogues) {
					selectedOption++;
				}
			}
			
			if(CharacterManager.getPlayer().isYPressed() && selectedOption != currentDialogues.size() && sentenceCount < currentDialogues.get(selectedOption).getSentences().size() - 1) {
				
				sentenceCount++;
				
				currentSentence = currentDialogues.get(selectedOption).getSentences().get(sentenceCount).getText();
				currentSpeaker = currentDialogues.get(selectedOption).getSentences().get(sentenceCount).getSpeakerName();

			} else if(CharacterManager.getPlayer().isYPressed() && selectedOption != currentDialogues.size() && sentenceCount == currentDialogues.get(selectedOption).getSentences().size() - 1 && currentDialogues.get(selectedOption).getChildDialogues().size() != 0) {
				
				currentDialogues = currentDialogues.get(selectedOption).getChildDialogues();
				sentenceCount = 0;
				selectedOption = 0;
				
			} else if(CharacterManager.getPlayer().isYPressed() &&  selectedOption != currentDialogues.size() && sentenceCount == currentDialogues.get(selectedOption).getSentences().size() - 1 && currentDialogues.get(selectedOption).getChildDialogues().size() == 0) {
				
				currentDialogues = startingDialogues;
				selectedOption = 0;
				sentenceCount = 0;
				
			}
			
			if(CharacterManager.getPlayer().isYPressed() && selectedOption == currentDialogues.size() && currentDialogues == startingDialogues) {
				active = false;
				selectedOption = 0;
				sentenceCount = 0;
			}
			
		}		

		
	}
	
	public void render(Graphics g) {
		if(active) {
			g.drawImage(image, 0, 0);
			
			Color fontColor;
			
			if(sentenceCount == 0) {
			
				for(int i = 0; i <= currentDialogues.size(); i++) {
					
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
				
				ttf.drawString(492, 763, currentSpeaker, Color.white);
				ttf.drawString(492, 793, currentSentence, Color.white);
				
			}
		}
	}

	public boolean isActive() {
		return active;
	}
}