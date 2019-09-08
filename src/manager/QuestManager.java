package manager;

import java.util.ArrayList;
import java.util.Collections;

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
		halroksRatProblem.setGoldReward(50);
		
		lostChrystal = new Quest("Lost Chrystal", 400);
		lostChrystal.setGoldReward(100);
		
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
		
		return sortAfterTimestamp(activeQuestList);
		
	}
	
	public static ArrayList<Quest> getFinishedQuestList() {
		
		ArrayList<Quest> finishedQuestList = new ArrayList<Quest>();
		
		for(Quest quest : questList) {
			if(quest.isFinished()) {
				finishedQuestList.add(quest);
			}
		}
		
		return sortAfterTimestamp(finishedQuestList);
	}
	
	public static ArrayList<Quest> getFailedQuestList() {
		
		ArrayList<Quest> failedQuestList = new ArrayList<Quest>();
		
		for(Quest quest : questList) {
			if(quest.isFailed()) {
				failedQuestList.add(quest);
			}
		}
		
		return sortAfterTimestamp(failedQuestList);
	}
	
	//Sort after timestamp (for the ordering of quests in the questlog)
	private static ArrayList<Quest> sortAfterTimestamp(ArrayList<Quest> questListToSort) {
		for (int i = 0; i < questListToSort.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < questListToSort.size(); j++) {
				if (questListToSort.get(j).getTimestamp() < questListToSort.get(index).getTimestamp()) {
					index = j;
				}
			}
			Collections.swap(questListToSort, index, i);
		}
		return questListToSort;
	}
	
}

