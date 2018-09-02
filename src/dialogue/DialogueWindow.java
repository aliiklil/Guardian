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
	private ArrayList<Dialogue> dialogues;
	
	private Font font = new Font(Font.MONOSPACED, Font.BOLD, 25);
	private TrueTypeFont ttf = new TrueTypeFont(font, true);
	
	private int selectedOption = 0;
	
	private Input input = Main.appGameContainer.getInput();
	
	public DialogueWindow() throws SlickException {
		
	}
	
	public void showWindow(ArrayList<Dialogue> dialogues) {
		
		active = true;
		this.dialogues = dialogues;
		
		
	}
	
	public void update() throws SlickException {
		
		if(active) {
			
			if(input.isKeyPressed(Input.KEY_UP) && selectedOption > 0) {
				selectedOption--;
			}
			
			if(input.isKeyPressed(Input.KEY_DOWN) && selectedOption < dialogues.size()) {
				selectedOption++;
			}

			if(CharacterManager.getPlayer().isYPressed() && selectedOption == dialogues.size()) {
				active = false;
				selectedOption = 0;
			}
			
		}
		
		
	}
	
	public void render(Graphics g) {
		if(active) {
			g.drawImage(image, 0, 0);
			
			Color fontColor;
			for(int i = 0; i <= dialogues.size(); i++) {
				
				if(i == selectedOption) {
					fontColor = Color.white;
				} else {
					fontColor = Color.gray;
				}
				
				if(i == dialogues.size()) {
					ttf.drawString(492, 763 + i * 30, "End", fontColor);
				} else {
					ttf.drawString(492, 763 + i * 30, dialogues.get(i).getSentences().get(0).getSentence(), fontColor);
				}
				
			}	
			
		}
	}

	public boolean isActive() {
		return active;
	}
}