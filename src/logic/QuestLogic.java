package logic;

import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import main.Main;
import manager.ItemManager;
import manager.MobManager;
import manager.QuestManager;
import models.Item;
import models.Player;

public class QuestLogic {
	
	public void updateQuestLogic(Dialogue currentDialogue) throws SlickException {
				
		updateHalroksRatProblem(currentDialogue);
		updateLostChrystal(currentDialogue);
		updateWolfHunt(currentDialogue);

	}
	
	private void updateHalroksRatProblem(Dialogue currentDialogue) throws SlickException {
				
		if(MobManager.getPlayer().isYPressed() && currentDialogue != null && currentDialogue.getQuestTitle() != null && Game.getQuestManager().halroksRatProblem.isInactive() && currentDialogue.getQuestTitle().equals("Halrok's Rat Problem") && !currentDialogue.isQuestStarted()) {
			
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
		
		if(MobManager.getPlayer().isYPressed() && currentDialogue != null && Game.getQuestManager().halroksRatProblem.isActive() && currentDialogue.getSentences().get(0).getText().equals("I killed the rat.")) {
			
			MobManager.getPlayer().addExperience(Game.getQuestManager().halroksRatProblem.getExperienceReward());
			MobManager.getPlayer().addGold(Game.getQuestManager().halroksRatProblem.getGoldReward());
			Game.getQuestManager().halroksRatProblem.setFinished();
			
			Game.getQuestManager().halroksRatProblem.getNotes().add("The rat is dead and I got 50 gold as a reward.");

		}
		
	}

	private void updateLostChrystal(Dialogue currentDialogue) throws SlickException {
				
		if(currentDialogue != null && currentDialogue.getQuestTitle() != null && currentDialogue.getQuestTitle().equals("Lost Chrystal") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().lostChrystal.setActive();
			
			Game.getQuestManager().lostChrystal.getNotes().add("Ogus lost his chrystal.");
			Game.getQuestManager().lostChrystal.getNotes().add("It should be east from here.");
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			MobManager.getPlayer().getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
			
		}
		
		if(Game.getQuestManager().lostChrystal.isActive() && Game.getItemTypeManager().ogusChrystal.isPickedUpEvent()) {

			Game.getItemTypeManager().ogusChrystal.consumePickUpEvent();
			
			Dialogue dialogue = new Dialogue();
			dialogue.addSentence("I found the chrystal.", "Hero");
			dialogue.addSentence("Finally I have it again. My master needs it. Here you have 100 gold.", "Ogus");
			dialogue.addSentence("Thanks.", "Hero");
			
			MobManager.ogus.getStartingDialogues().add(dialogue);
						
		}
				
		if(MobManager.getPlayer().isYPressed() && currentDialogue != null && Game.getQuestManager().lostChrystal.isActive() && currentDialogue.getSentences().get(0).getText().equals("I found the chrystal.")) {
			
			MobManager.getPlayer().addExperience(Game.getQuestManager().lostChrystal.getExperienceReward());
			MobManager.getPlayer().addGold(Game.getQuestManager().lostChrystal.getGoldReward());
			Game.getQuestManager().lostChrystal.setFinished();
			
			Game.getQuestManager().lostChrystal.getNotes().add("I gave him the chrystal and I got 100 gold as a reward.");

			MobManager.getPlayer().getInventoryWindow().removeItem(Game.getItemTypeManager().ogusChrystal);
			
		}
		
	}
	
	
	private void updateWolfHunt(Dialogue currentDialogue) throws SlickException {
		
		if(MobManager.getPlayer().isYPressed() && currentDialogue != null && currentDialogue.getQuestTitle() != null && currentDialogue.getQuestTitle().equals("Wolf Hunt") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().wolfHunt.setActive();
			
			Game.getQuestManager().wolfHunt.getNotes().add("Jorgen needs to kill three aggressive wolfes, that are currently south from here.");
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			MobManager.getPlayer().getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
			
		}
		
		if(Game.getQuestManager().wolfHunt.isActive() && MobManager.aggressiveWolf1.isKillEvent() && MobManager.aggressiveWolf2.isKillEvent() && MobManager.aggressiveWolf3.isKillEvent()) {
			System.out.println("XXXXXXXXXXXXXX");
			MobManager.aggressiveWolf1.consumeKillEvent();
			MobManager.aggressiveWolf2.consumeKillEvent();
			MobManager.aggressiveWolf3.consumeKillEvent();
			
			Dialogue dialogue = new Dialogue();
			dialogue.addSentence("The wolfes are dead now.", "Hero");
			dialogue.addSentence("Good job. Here take 200 gold.", "Jorgen");
			dialogue.addSentence("Thanks.", "Hero");
			
			MobManager.jorgen.getStartingDialogues().add(dialogue);
						
		}
				
		if(MobManager.getPlayer().isYPressed() && currentDialogue != null && Game.getQuestManager().wolfHunt.isActive() && currentDialogue.getSentences().get(0).getText().equals("The wolfes are dead now.")) {
			
			MobManager.getPlayer().addExperience(Game.getQuestManager().wolfHunt.getExperienceReward());
			MobManager.getPlayer().addGold(Game.getQuestManager().wolfHunt.getGoldReward());
			Game.getQuestManager().wolfHunt.setFinished();
			
			Game.getQuestManager().wolfHunt.getNotes().add("He gave me 200 gold as a reward.");
			
		}
		
	}
		
}
