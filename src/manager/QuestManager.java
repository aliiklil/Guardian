package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

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
	
}

