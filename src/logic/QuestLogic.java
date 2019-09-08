package logic;

import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import main.Main;
import manager.MobManager;
import manager.QuestManager;
import models.Item;
import models.Player;

public class QuestLogic {
	
	public void updateQuestLogic(Dialogue currentDialogue) throws SlickException {
				
		updateHalroksRatProblem(currentDialogue);
		updateLostChrystal(currentDialogue);

	}
	
	private void updateHalroksRatProblem(Dialogue currentDialogue) throws SlickException {
				
		if(MobManager.getPlayer().isYPressed() && currentDialogue.getQuestTitle() != null && Game.getQuestManager().halroksRatProblem.isInactive() && currentDialogue.getQuestTitle().equals("Halrok's Rat Problem") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().halroksRatProblem.setActive();
						
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			MobManager.getPlayer().getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);

		}

		if(Game.getQuestManager().halroksRatProblem.isActive() && MobManager.filthyRat.isKillEvent()) {

			MobManager.filthyRat.consumeKillEvent();
			
			Dialogue dialogue = new Dialogue();
			dialogue.addSentence("I killed the rat.", "Hero");
			dialogue.addSentence("Nice, good job. Here you have 50 gold.", "Halrok");
			dialogue.addSentence("Thanks.", "Hero");
			
			
			MobManager.halrok.getStartingDialogues().add(dialogue);
						
		}
		
		if(MobManager.getPlayer().isYPressed() && Game.getQuestManager().halroksRatProblem.isActive() && currentDialogue.getSentences().get(0).getText().equals("I killed the rat.")) {
			
			MobManager.getPlayer().addExperience(Game.getQuestManager().halroksRatProblem.getExperienceReward());
			MobManager.getPlayer().addGold(Game.getQuestManager().halroksRatProblem.getGoldReward());
			System.out.println("KSSA");
			Game.getQuestManager().halroksRatProblem.setFinished();
			
		}
		
	}

	private void updateLostChrystal(Dialogue currentDialogue) throws SlickException {
				
		if(currentDialogue.getQuestTitle() != null && currentDialogue.getQuestTitle().equals("Lost Chrystal") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().lostChrystal.setActive();
			
			Game.getQuestManager().lostChrystal.getNotes().add("Ogus lost his chrystal.");
			Game.getQuestManager().lostChrystal.getNotes().add("It should be east from here.");
			Game.getQuestManager().lostChrystal.getNotes().add("I will try to find it.");
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			MobManager.getPlayer().getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
			
		}
		
	}
		
}
