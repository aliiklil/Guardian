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
	private Image questLogWindow = new Image("resources/gui/quest_log.png");
	private Image questNotesWindow = new Image("resources/gui/quest_notes.png");
			
	private Input input = Main.appGameContainer.getInput();

	private boolean holdUpKey = false;
	private boolean holdDownKey = false;
		
	private SpriteSheet arrowUpSpriteSheet = new SpriteSheet("resources/gui/arrowUpImage.png", 22, 22);
	private SpriteSheet arrowDownSpriteSheet = new SpriteSheet("resources/gui/arrowDownImage.png", 22, 22);
	
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
	private boolean questNotesSelected = false;
	
	
	private int rightSideScrollOffset = 0;
	private int questNotesScrollOffset = 0;
	
	//Number of either current, finished or failed quests, depending on which category is selected
	private int numberOfQuests;
	
	private Quest selectedQuest;
	
	//Relevant for quest notes, so they can be wrapped do multiple lines if needed
	private final int MAX_CHAR_AMOUNT_PER_LINE = 59;
	
	//Maximum of lines for notes
	private final int MAX_LINES = 23;
		
	private ArrayList<String> notesOfSelectedQuest;
	
	//Relevant for, when player holds down key down or key up key (to scroll faster)
	private long timestamp = 0;
	
	public QuestLogWindow() throws SlickException {
		
	}
	
	public void update() throws SlickException {
		
		player = MobManager.getPlayer();
		
		countNumberOfQuests();
		
		if(player.isQPressed() && !player.getDialogueWindow().isWindowOpen() && !player.getTradingWindow().isWindowOpen() && !player.getInventoryWindow().isWindowOpen() && !player.getReadingWindow().isWindowOpen() &&leftSideSelected) {
			if(!windowOpen) {
				windowOpen = true;
				rightSideScrollOffset = 0;
				selectedOptionRightSide = 0;
			} else {
				windowOpen = false;
			}
		}	
				
		if(player.isEscapePressed() && windowOpen && leftSideSelected) {
			windowOpen = false;
			rightSideScrollOffset = 0;
		}
		
		if(windowOpen) {
			
			if(leftSideSelected) {
				if(player.isKeyUpPressed() && selectedOptionLeftSide > 0) {
					selectedOptionLeftSide--;
					rightSideScrollOffset = 0;
					selectedOptionRightSide = 0;
				}
				
				if(player.isKeyDownPressed() && selectedOptionLeftSide < 2) {
					selectedOptionLeftSide++;
					rightSideScrollOffset = 0;
					selectedOptionRightSide = 0;
				}
			}

			if(rightSideSelected) {
				
				if((player.isKeyUpPressed() || holdUpKey && System.currentTimeMillis() - timestamp > 50)) {
					if(selectedOptionRightSide > 0) {
						selectedOptionRightSide--;
						timestamp = System.currentTimeMillis();
					} else if(selectedOptionRightSide == 0 && rightSideScrollOffset > 0) {
						rightSideScrollOffset--;
						timestamp = System.currentTimeMillis();
					}
				}
				
				
				if((player.isKeyDownPressed() || holdDownKey && System.currentTimeMillis() - timestamp > 50)) {
					if(selectedOptionRightSide < numberOfQuests - 1 && selectedOptionRightSide != MAX_LINES - 1) {
						
						selectedOptionRightSide++;
						timestamp = System.currentTimeMillis();
						
					} else if (selectedOptionRightSide == MAX_LINES - 1 && rightSideScrollOffset + MAX_LINES < numberOfQuests) {
							
						rightSideScrollOffset++;
						timestamp = System.currentTimeMillis();
									
					}
				}
			}
			
			if(rightSideSelected && player.isYPressed()) {
				leftSideSelected = false;
				rightSideSelected = false;
				questNotesSelected = true;
				
				notesOfSelectedQuest = new ArrayList<String>();
				
				for(int i = 0; i < selectedQuest.getNotes().size(); i++) {
						String restNote = selectedQuest.getNotes().get(i);
						while(true) {
							if(restNote.length() >= MAX_CHAR_AMOUNT_PER_LINE) {
								notesOfSelectedQuest.add(restNote.substring(0, restNote.substring(0, MAX_CHAR_AMOUNT_PER_LINE).lastIndexOf(" ")));
								restNote = restNote.substring(restNote.substring(0, MAX_CHAR_AMOUNT_PER_LINE).lastIndexOf(" ") + 1, restNote.length());
							} else {
								notesOfSelectedQuest.add(restNote);
								break;
							}
						}
						
						if(!(i == selectedQuest.getNotes().size() - 1)) {
							notesOfSelectedQuest.add("-----");
						}
					}
			}
			
			if(leftSideSelected && player.isYPressed() && numberOfQuests > 0) {
				leftSideSelected = false;
				rightSideSelected = true;
			}
			
			if(rightSideSelected && player.isEscapePressed()) {
				leftSideSelected = true;
				rightSideSelected = false;
			}
			
			

			
			if(questNotesSelected && player.isEscapePressed()) {
				leftSideSelected = false;
				rightSideSelected = true;
				questNotesSelected = false;
				questNotesScrollOffset = 0;
			}
			
			if(questNotesSelected && questNotesScrollOffset + MAX_LINES < notesOfSelectedQuest.size() && (player.isKeyDownPressed() || holdDownKey && System.currentTimeMillis() - timestamp > 50)) {
				questNotesScrollOffset++;
				timestamp = System.currentTimeMillis();
			}
			
			if(questNotesSelected && questNotesScrollOffset > 0  && (player.isKeyUpPressed() || holdUpKey && System.currentTimeMillis() - timestamp > 50)) {
				questNotesScrollOffset--;
				timestamp = System.currentTimeMillis();
			}
			
			

			
		}
		
		checkIfKeyDown();
		
	}
		
	public void render(Graphics g) {
		
		if(windowOpen) {
			if(leftSideSelected || rightSideSelected) {
				drawArrow(g);
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
				
				g.drawString(currentQuests, 500 - currentQuests.length() * 9, 151);
				
				if(selectedOptionLeftSide == 1 && leftSideSelected) {
					g.setColor(Color.black);
				} else {
					g.setColor(Color.gray);
				}
				
				g.drawString(finishedQuests, 505 - finishedQuests.length() * 9, 229);
				
				if(selectedOptionLeftSide == 2 && leftSideSelected) {
					g.setColor(Color.black);
				} else {
					g.setColor(Color.gray);
				}
				g.drawString(failedQuests, 497 - failedQuests.length() * 9, 307);
			
				
				ArrayList<Quest> questList = null;
				
				if(selectedOptionLeftSide == 0) {
					questList = QuestManager.getActiveQuestList();
				} else if (selectedOptionLeftSide == 1) {
					questList = QuestManager.getFinishedQuestList();
				} else if (selectedOptionLeftSide == 2) {
					questList = QuestManager.getFailedQuestList();
				}
					
				int k = 0;
				for(int i = rightSideScrollOffset; i < questList.size(); i++) {
						
					if(selectedOptionRightSide == k && rightSideSelected) {
						g.setColor(Color.black);
						selectedQuest = questList.get(i);
					} else {
						g.setColor(Color.gray);
					}
					
					g.drawString(questList.get(i).getQuestTitle(), 526, 129 + k * 20);
					k++;
					
					if(k >= MAX_LINES) {
						break;
					}	
					
				}
				

			
			} else if(questNotesSelected) {
				g.drawImage(questNotesWindow, 0, 0);
				drawArrow(g);
				g.setColor(Color.black);
				
					int k = 0;
					for(int i = questNotesScrollOffset; i < notesOfSelectedQuest.size(); i++) {
						g.drawString(notesOfSelectedQuest.get(i), 373, 129 + k * 20);
						k++;
						
						if(k >= MAX_LINES) {
							break;
						}
						
					}
					
				}
				
			}
		
	}			
	
	private void drawArrow(Graphics g) {
		
		arrowUpAnimation.updateNoDraw();
		arrowDownAnimation.updateNoDraw();
		
		if(questNotesSelected && questNotesScrollOffset > 0) { 
			arrowUpAnimation.draw(921, 118);
		}
		
		if(questNotesSelected && questNotesScrollOffset + MAX_LINES < notesOfSelectedQuest.size()) {
			arrowDownAnimation.draw(921, 580);
		}
		
		
		if(!questNotesSelected && rightSideScrollOffset > 0) { 
			arrowUpAnimation.draw(921, 118);
		}
		
		if(!questNotesSelected && rightSideScrollOffset + MAX_LINES < numberOfQuests) {
			arrowDownAnimation.draw(921, 580);
		}
				
	}
	
	
	private void checkIfKeyDown() {

		if(input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_DOWN)) {
			if(System.currentTimeMillis() - timestamp > 300 && timestamp != 0) {
				holdUpKey = true;
				timestamp = System.currentTimeMillis();
			}
		} else {
			holdUpKey = false;
		}
		
		if(input.isKeyDown(Input.KEY_DOWN) && !input.isKeyDown(Input.KEY_UP)) {
			if(System.currentTimeMillis() - timestamp > 300 && timestamp != 0) {
				holdDownKey = true;
				timestamp = System.currentTimeMillis();
			}
		} else {
			holdDownKey = false;
		}
		
	}
	
	private void countNumberOfQuests() {
		
		if(selectedOptionLeftSide == 0) {
			numberOfQuests = QuestManager.getActiveQuestList().size();
		} else if(selectedOptionLeftSide == 1) {
			numberOfQuests = QuestManager.getFinishedQuestList().size();
		} else if(selectedOptionLeftSide == 2) {
			numberOfQuests = QuestManager.getFailedQuestList().size();
		}
		
		
	}

	public boolean isWindowOpen() {
		return windowOpen;
	}
			
}