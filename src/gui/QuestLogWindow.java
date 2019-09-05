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

import main.Game;
import main.Main;
import manager.MobManager;
import manager.QuestManager;
import models.Item;
import models.ItemType;
import models.Player;
import models.Quest;
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
	
	//Selected option on the right side where the quests are
	private int selectedOptionRightSide = 0;
	
	
	//If cursor (or selected option) is right now left or right side
	private boolean leftSideSelected = true;
	private boolean rightSideSelected = false;
	
	private int numberOfCurrentQuests;
	private int numberOfFinishedQuests;
	private int numberOfFailedQuests;
	
	
	public QuestLogWindow() throws SlickException {
		
	}
	
	public void update() throws SlickException {
		
		player = MobManager.getPlayer();
		
		if(player.isQPressed() && !player.getDialogueWindow().isWindowOpen() && !player.getTradingWindow().isWindowOpen()) {
			if(!windowOpen) {
				windowOpen = true;
				countNumberOfQuests();
			} else {
				windowOpen = false;
			}
		}	
				
		if(player.isEscapePressed() && windowOpen && leftSideSelected) {
			windowOpen = false;
		}
		
		if(windowOpen) {

			if(leftSideSelected) {
				if(player.isKeyUpPressed() && selectedOptionLeftSide > 0) {
					selectedOptionLeftSide--;
				}
				
				if(player.isKeyDownPressed() && selectedOptionLeftSide < 2) {
					selectedOptionLeftSide++;
				}
			}
			
			if(rightSideSelected) {
				if(player.isKeyUpPressed() && selectedOptionRightSide > 0) {
					selectedOptionRightSide--;
				}
				
				if(player.isKeyDownPressed()) {
					if(selectedOptionLeftSide == 0 && selectedOptionRightSide < numberOfCurrentQuests - 1 || selectedOptionLeftSide == 1 && selectedOptionRightSide < numberOfFinishedQuests - 1 || selectedOptionLeftSide == 2 && selectedOptionRightSide < numberOfFailedQuests - 1) {
						selectedOptionRightSide++;
					}
				}
			}
			
			if(player.isYPressed() && leftSideSelected) {
				if(selectedOptionLeftSide == 0 && numberOfCurrentQuests > 0 || selectedOptionLeftSide == 1 && numberOfFinishedQuests > 0 || selectedOptionLeftSide == 2 && numberOfFailedQuests > 0) {
					leftSideSelected = false;
					rightSideSelected = true;
				}
			}
			
			if(player.isEscapePressed() && rightSideSelected) {
				leftSideSelected = true;
				rightSideSelected = false;
				selectedOptionRightSide = 0;
			}
			
		}
		
	}
		
	public void render(Graphics g) {
				
		if(windowOpen) {			
			g.drawImage(questLogWindow, 0, 0);
			
			g.setColor(Color.black);
						
			String currentQuests = "Current Quests";
			String finishedQuests = "Finished Quests";
			String failedQuests = "Failed Quests";
			
		
			if(selectedOptionLeftSide == 0 && leftSideSelected) {
				g.setColor(Color.black);
			} else {
				g.setColor(Color.gray);
			}
			
			g.drawString(currentQuests, 783 - currentQuests.length() * 9, 303);
			
			if(selectedOptionLeftSide == 1 && leftSideSelected) {
				g.setColor(Color.black);
			} else {
				g.setColor(Color.gray);
			}
			
			g.drawString(finishedQuests, 787 - finishedQuests.length() * 9, 385);
			
			if(selectedOptionLeftSide == 2 && leftSideSelected) {
				g.setColor(Color.black);
			} else {
				g.setColor(Color.gray);
			}
			g.drawString(failedQuests, 778 - failedQuests.length() * 9, 467);
		

			

			
			if(selectedOptionLeftSide == 0) {
				int k = 0;
				for(Quest quest : QuestManager.getQuestList()) {
					if(quest.isActive()) {
						
						if(selectedOptionRightSide == k && rightSideSelected) {
							g.setColor(Color.black);
						} else {
							g.setColor(Color.gray);
						}
						
						g.drawString(quest.getQuestTitle(), 810, 280 + k * 20);
						k++;
					}
				}
			}
			
			if(selectedOptionLeftSide == 1) {
				int k = 0;
				for(Quest quest : QuestManager.getQuestList()) {
					if(quest.isFinished()) {
						
						if(selectedOptionRightSide == k && rightSideSelected) {
							g.setColor(Color.black);
						} else {
							g.setColor(Color.gray);
						}
						
						g.drawString(quest.getQuestTitle(), 810, 280 + k * 20);
						k++;
					}
				}
			}
			
			if(selectedOptionLeftSide == 2) {
				int k = 0;
				for(Quest quest : QuestManager.getQuestList()) {
					if(quest.isFailed()) {
						
						if(selectedOptionRightSide == k && rightSideSelected) {
							g.setColor(Color.black);
						} else {
							g.setColor(Color.gray);
						}
						
						g.drawString(quest.getQuestTitle(), 810, 280 + k * 20);
						k++;
					}
				}
			}
			
			
		}
							
	}
	
	private void countNumberOfQuests() {
		numberOfCurrentQuests = 0;
		numberOfFinishedQuests = 0;
		numberOfFailedQuests = 0;
		
		for(Quest quest : QuestManager.getQuestList()) {
			if(quest.isActive()) {
				numberOfCurrentQuests++;
			}
		}
		
		for(Quest quest : QuestManager.getQuestList()) {
			if(quest.isFinished()) {
				numberOfFinishedQuests++;
			}
		}
		
		for(Quest quest : QuestManager.getQuestList()) {
			if(quest.isFailed()) {
				numberOfFailedQuests++;
			}
		}
	}

	public boolean isWindowOpen() {
		return windowOpen;
	}
			
}