package logic;

import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import manager.MobManager;
import manager.QuestManager;
import models.Player;

public class QuestLogic {

	public void updateQuestLogic(Dialogue currentDialogue) throws SlickException {
				
		if(currentDialogue.getQuestTitle() != null) {
		
			updateHalroksRatProblem(currentDialogue);
			updateLostChrystal(currentDialogue);
		
		}

	}
	
	private void updateHalroksRatProblem(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getQuestTitle().equals("Halrok's Rat Problem")) {
			
			Game.getQuestManager().halroksRatProblem.setActive(true);
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok told me he wants me to kill the black rat.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("The rat is south from where Halrok stands.");
			
		}
		
	}
	
	private void updateLostChrystal(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getQuestTitle().equals("Lost Chrystal")) {
			
			Game.getQuestManager().lostChrystal.setActive(true);
			Game.getQuestManager().lostChrystal.getNotes().add("Ogus lost his chrystal.");
			Game.getQuestManager().lostChrystal.getNotes().add("It should be east from here.");
			Game.getQuestManager().lostChrystal.getNotes().add("I will try to find it.");
			
		}
		
	}
		
}
