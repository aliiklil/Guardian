package gui;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Main;
import manager.MobManager;
import models.Item;
import models.ItemType;
import models.Player;
import models.Character;

public class QuestLogWindow {

	private boolean windowOpen = false;
	private Image questLogWindow = new Image("resources/quest_log1.png");
	private Image questWindow = new Image("resources/quest_log2.png");
	
	private int scrollOffset = 0;
		
	private Input input = Main.appGameContainer.getInput();

	private boolean holdUpKey = false;
	private boolean holdDownKey = false;
	private boolean holdLeftKey = false;
	private boolean holdRightKey = false;
		
	private SpriteSheet arrowUpSpriteSheet = new SpriteSheet("resources/arrowUpImage.png", 44, 44);
	private SpriteSheet arrowDownSpriteSheet = new SpriteSheet("resources/arrowDownImage.png", 44, 44);
	
	private Animation arrowUpAnimation = new Animation(arrowUpSpriteSheet, 0, 0, 1, 0, true, 750, true);
	private Animation arrowDownAnimation = new Animation(arrowDownSpriteSheet, 0, 0, 1, 0, true, 750, true);
	
	private Player player;
	
	
	//Selected option (either on current, finished or failed quests)
	private int selectedOptionLeftSide = 0;
	
	
	public QuestLogWindow() throws SlickException {
		
	}
	
	public void update() throws SlickException {
		
		player = MobManager.getPlayer();
		
		if(player.isQPressed() && !player.getDialogueWindow().isWindowOpen() && !player.getTradingWindow().isWindowOpen()) {
			if(!windowOpen) {
				windowOpen = true;
			} else {
				windowOpen = false;
			}
		}	
				
		if(player.isEscapePressed() && windowOpen) {
			windowOpen = false;
		}
		
		if(windowOpen) {
			
			if(player.isKeyUpPressed() && selectedOptionLeftSide > 0) {
				selectedOptionLeftSide--;
			}
			
			if(player.isKeyDownPressed() && selectedOptionLeftSide < 2) {
				selectedOptionLeftSide++;
			}
			
		}
		
		System.out.println(selectedOptionLeftSide);
		
	}
		
	public void render(Graphics g) {
				
		if(windowOpen) {			
			g.drawImage(questLogWindow, 0, 0);
			
			g.setColor(Color.black);
						
			String currentQuests = "Current Quests";
			String finishedQuests = "Finished Quests";
			String failedQuests = "Failed Quests";
			
			if(selectedOptionLeftSide == 0) {
				g.setColor(Color.black);
			} else {
				g.setColor(Color.gray);
			}
			
			g.drawString(currentQuests, 783 - currentQuests.length() * 9, 303);
			
			if(selectedOptionLeftSide == 1) {
				g.setColor(Color.black);
			} else {
				g.setColor(Color.gray);
			}
			
			g.drawString(finishedQuests, 787 - finishedQuests.length() * 9, 385);
			
			if(selectedOptionLeftSide == 2) {
				g.setColor(Color.black);
			} else {
				g.setColor(Color.gray);
			}
			g.drawString(failedQuests, 778 - failedQuests.length() * 9, 467);
			
		}
							
	}
	

	public boolean isWindowOpen() {
		return windowOpen;
	}
			
}