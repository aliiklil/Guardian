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
		
		if(currentDialogue.getQuestTitle().equals("Halrok's Rat Problem") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().halroksRatProblem.setActive(true);
						
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He told me I should try not to get hit by hit, since it can poison me or give me diseases that would be very unpleasant. I should probably buy an antidote before trying to kill the rat.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("The rat is south from where Halrok stands.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Ogus lost his chrystal. It should be east from here. I will try to find it. It should be east from here. I will try to find it. The rat is south from where Halrok stands.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He told me I should try not to get hit by hit, since it can poison me or give me diseases that would be very unpleasant. I should probably buy an antidote before trying to kill the rat.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("The rat is south from where Halrok stands.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("The rat is south from where Halrok stands.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He told me I should try not to get hit by hit, since it can poison me or give me diseases that would be very unpleasant. I should probably buy an antidote before trying to kill the rat.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("The rat is south from where Halrok stands.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("The rat is south from where Halrok stands.");
			
			currentDialogue.setQuestStarted(true);
			
		}
		
	}
	
	private void updateLostChrystal(Dialogue currentDialogue) throws SlickException {
		
		Player player = MobManager.getPlayer();
		
		if(currentDialogue.getQuestTitle().equals("Lost Chrystal") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().lostChrystal.setActive(true);
			
			Game.getQuestManager().lostChrystal.getNotes().add("Ogus lost his chrystal.");
			Game.getQuestManager().lostChrystal.getNotes().add("It should be east from here.");
			Game.getQuestManager().lostChrystal.getNotes().add("I will try to find it.");
			
			currentDialogue.setQuestStarted(true);
			
		}
		
	}
		
}
