package manager;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import models.Player;
import models.Quest;

public class QuestManager {

	private static ArrayList<Quest> questList = new ArrayList<Quest>();
	
	public Quest halroksRatProblem;
	public Quest lostChrystal;
		
	public QuestManager() throws SlickException {
				
		halroksRatProblem = new Quest("Halrok's Rat Problem", 300);
		lostChrystal = new Quest("Lost Chrystal", 300);
		
		questList.add(halroksRatProblem);
		questList.add(lostChrystal);

	}
	
	public static ArrayList<Quest> getQuestList() {
		return questList;
	}
	
	public static ArrayList<Quest> getActiveQuestList() {
		
		ArrayList<Quest> activeQuestList = new ArrayList<Quest>();
		
		for(Quest quest : questList) {
			if(quest.isActive()) {
				activeQuestList.add(quest);
			}
		}
		
		return activeQuestList;
		
	}
	
	public static ArrayList<Quest> getFinishedQuestList() {
		
		ArrayList<Quest> finishedQuestList = new ArrayList<Quest>();
		
		for(Quest quest : questList) {
			if(quest.isFinished()) {
				finishedQuestList.add(quest);
			}
		}
		
		return finishedQuestList;
	}
	
	public static ArrayList<Quest> getFailedQuestList() {
		
		ArrayList<Quest> failedQuestList = new ArrayList<Quest>();
		
		for(Quest quest : questList) {
			if(quest.isFailed()) {
				failedQuestList.add(quest);
			}
		}
		
		return failedQuestList;
	}
	
}

