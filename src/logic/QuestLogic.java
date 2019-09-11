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
	
	private Player player = MobManager.getPlayer();
	
	public void updateQuestLogic(Dialogue currentDialogue) throws SlickException {
				
		updateHalroksRatProblem(currentDialogue);
		updateLostChrystal(currentDialogue);
		updateWolfHunt(currentDialogue);
		updatePlantsForAPotion(currentDialogue);
	}
	
	private void updateHalroksRatProblem(Dialogue currentDialogue) throws SlickException {
				
		if(player.isYPressed() && currentDialogue != null && currentDialogue.getQuestTitle() != null && Game.getQuestManager().halroksRatProblem.isInactive() && currentDialogue.getQuestTitle().equals("Halrok's Rat Problem") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().halroksRatProblem.setActive();
						
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);

		}

		if(Game.getQuestManager().halroksRatProblem.isActive() && MobManager.filthyRat.isKillEvent()) {

			MobManager.filthyRat.consumeKillEvent();
			
			Dialogue dialogue = new Dialogue();
			dialogue.addSentence("I killed the rat.", "Hero");
			dialogue.addSentence("Nice, good job. Here you have 50 gold.", "Halrok");
			dialogue.addSentence("Thanks.", "Hero");
			
			MobManager.halrok.getStartingDialogues().add(dialogue);
						
		}
		
		if(player.isYPressed() && currentDialogue != null && Game.getQuestManager().halroksRatProblem.isActive() && currentDialogue.getSentences().get(0).getText().equals("I killed the rat.")) {
			
			player.addExperience(Game.getQuestManager().halroksRatProblem.getExperienceReward());
			player.addGold(Game.getQuestManager().halroksRatProblem.getGoldReward());
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
			player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
			
		}
		
		if(Game.getQuestManager().lostChrystal.isActive() && Game.getItemTypeManager().ogusChrystal.isPickedUpEvent()) {

			Game.getItemTypeManager().ogusChrystal.consumePickUpEvent();
			
			Dialogue dialogue = new Dialogue();
			dialogue.addSentence("I found the chrystal.", "Hero");
			dialogue.addSentence("Finally I have it again. My master needs it. Here you have 100 gold.", "Ogus");
			dialogue.addSentence("Thanks.", "Hero");
			
			MobManager.ogus.getStartingDialogues().add(dialogue);
						
		}
				
		if(player.isYPressed() && currentDialogue != null && Game.getQuestManager().lostChrystal.isActive() && currentDialogue.getSentences().get(0).getText().equals("I found the chrystal.")) {
			
			player.addExperience(Game.getQuestManager().lostChrystal.getExperienceReward());
			player.addGold(Game.getQuestManager().lostChrystal.getGoldReward());
			Game.getQuestManager().lostChrystal.setFinished();
			
			Game.getQuestManager().lostChrystal.getNotes().add("I gave him the chrystal and I got 100 gold as a reward.");

			player.getInventoryWindow().removeItem(Game.getItemTypeManager().ogusChrystal);
			
		}
		
	}
	
	
	private void updateWolfHunt(Dialogue currentDialogue) throws SlickException {
		
		if(player.isYPressed() && currentDialogue != null && currentDialogue.getQuestTitle() != null && currentDialogue.getQuestTitle().equals("Wolf Hunt") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().wolfHunt.setActive();
			
			Game.getQuestManager().wolfHunt.getNotes().add("Jorgen needs to kill three aggressive wolfes, that are currently south from here.");
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
			
		}
		
		if(Game.getQuestManager().wolfHunt.isActive() && MobManager.aggressiveWolf1.isKillEvent() && MobManager.aggressiveWolf2.isKillEvent() && MobManager.aggressiveWolf3.isKillEvent()) {

			MobManager.aggressiveWolf1.consumeKillEvent();
			MobManager.aggressiveWolf2.consumeKillEvent();
			MobManager.aggressiveWolf3.consumeKillEvent();
			
			Dialogue dialogue = new Dialogue();
			dialogue.addSentence("The wolfes are dead now.", "Hero");
			dialogue.addSentence("Good job. Here take 200 gold.", "Jorgen");
			dialogue.addSentence("Thanks.", "Hero");
			
			MobManager.jorgen.getStartingDialogues().add(dialogue);
						
		}
				
		if(player.isYPressed() && currentDialogue != null && Game.getQuestManager().wolfHunt.isActive() && currentDialogue.getSentences().get(0).getText().equals("The wolfes are dead now.")) {
			
			player.addExperience(Game.getQuestManager().wolfHunt.getExperienceReward());
			player.addGold(Game.getQuestManager().wolfHunt.getGoldReward());
			Game.getQuestManager().wolfHunt.setFinished();
			
			Game.getQuestManager().wolfHunt.getNotes().add("He gave me 200 gold as a reward.");
			
		}
		
	}
	
	
	
	private void updatePlantsForAPotion(Dialogue currentDialogue) throws SlickException {
		
		if(player.isYPressed() && currentDialogue != null && currentDialogue.getQuestTitle() != null && currentDialogue.getQuestTitle().equals("Plants For A Potion") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().plantsForAPotion.setActive();
			
			Game.getQuestManager().plantsForAPotion.getNotes().add("Rico needs 3 healroots to create a new potion.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
			
		}
		
		if(Game.getQuestManager().plantsForAPotion.isActive()) {
			
			int healrootCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Healroot")) {
					healrootCount = player.getItemCountList().get(i);
				}
			}
			
			Dialogue dialogue = new Dialogue();
			dialogue.addSentence("I have three healroots for you.", "Hero");
			dialogue.addSentence("Good job. Wait I'll create the potion.", "Rico");
			dialogue.addSentence("...", "Rico");
			dialogue.addSentence("Im done. Try it. Here.", "Rico");
			dialogue.addSentence("Thanks.", "Hero");
			
			boolean dialogueAlreadyAdded = false;
			Dialogue alreadyAddedDialogue = null;
			for(Dialogue startingDialogue : MobManager.rico.getStartingDialogues()) {
				if(startingDialogue.isSame(dialogue)) {
					dialogueAlreadyAdded = true;
					alreadyAddedDialogue = startingDialogue;
					break;
				}
			}
			
			if(healrootCount >= 3) {
				if(!dialogueAlreadyAdded) {
					MobManager.rico.getStartingDialogues().add(dialogue);
				}
			} else {
				if(dialogueAlreadyAdded) {
					MobManager.rico.getStartingDialogues().remove(alreadyAddedDialogue);
				}
			}
		
		}
				
		if(player.isYPressed() && currentDialogue != null && Game.getQuestManager().plantsForAPotion.isActive() && currentDialogue.getSentences().get(0).getText().equals("I have three healroots for you.")) {
			
			player.addExperience(Game.getQuestManager().plantsForAPotion.getExperienceReward());
			player.addItem(Game.getQuestManager().plantsForAPotion.getItemReward());
			Game.getQuestManager().plantsForAPotion.setFinished();
			
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			Game.getQuestManager().plantsForAPotion.getNotes().add("He gave me the potion as a reward.");
			
			player.getInventoryWindow().removeItem(Game.getItemTypeManager().healroot);
			player.getInventoryWindow().removeItem(Game.getItemTypeManager().healroot);
			player.getInventoryWindow().removeItem(Game.getItemTypeManager().healroot);
			
		}
		
	}
		
}
