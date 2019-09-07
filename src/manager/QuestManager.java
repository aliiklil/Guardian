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
	
	public Quest halroksRatProblem1;
	public Quest lostChrystal1;
	
	public Quest halroksRatProblem2;
	public Quest lostChrystal2;
	
	public Quest halroksRatProblem3;
	public Quest lostChrystal3;
	
	public Quest halroksRatProblem4;
	public Quest lostChrystal4;
	
	public Quest halroksRatProblem5;
	public Quest lostChrystal5;
	
	public Quest halroksRatProblem6;
	public Quest lostChrystal6;
	
	public Quest halroksRatProblem7;
	public Quest lostChrystal7;
	
	public Quest halroksRatProblem8;
	public Quest lostChrystal8;
	
	public Quest halroksRatProblem9;
	public Quest lostChrystal9;
	
	public Quest halroksRatProblem10;
	public Quest lostChrystal10;
	
	public Quest halroksRatProblem11;
	public Quest lostChrystal11;
	
	public Quest halroksRatProblem12;
	public Quest lostChrystal12;
	
	public Quest halroksRatProblem13;
	public Quest lostChrystal13;
	
	public Quest halroksRatProblem14;
	public Quest lostChrystal14;
		
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

