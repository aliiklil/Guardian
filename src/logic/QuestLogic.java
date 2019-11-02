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
		
		updatewolfHunt(currentDialogue);

	}
	
	private void updatewolfHunt(Dialogue currentDialogue) throws SlickException {
				
		if(player.isYPressed() && currentDialogue != null && currentDialogue.getQuestTitle() != null && Game.getQuestManager().wolfHunt.isInactive() && currentDialogue.getQuestTitle().equals("Wolf Hunt") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().wolfHunt.setActive();
						
			Game.getQuestManager().wolfHunt.getNotes().add("Halrok said that he is very scared because of a wolf which is currently east from here. He wants me too kill it.");
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);

		}

		if(Game.getQuestManager().wolfHunt.isActive() && MobManager.aggressiveWolf.isKillEvent()) {

			MobManager.aggressiveWolf.consumeKillEvent();
			
			Dialogue dialogue = new Dialogue();
			dialogue.addSentence("I killed the wolf.", "Hero");
			dialogue.addSentence("Nice, good job. Here you have 50 gold.", "Halrok");
			dialogue.addSentence("Thanks.", "Hero");
			
			MobManager.halrok.getStartingDialogues().add(dialogue);
						
		}
		
		if(player.isYPressed() && currentDialogue != null && Game.getQuestManager().wolfHunt.isActive() && currentDialogue.getSentences().get(0).getText().equals("I killed the wolf.")) {
			
			player.addExperience(Game.getQuestManager().wolfHunt.getExperienceReward());
			player.addGold(Game.getQuestManager().wolfHunt.getGoldReward());
			Game.getQuestManager().wolfHunt.setFinished();
			
			Game.getQuestManager().wolfHunt.getNotes().add("The wolf is dead and I got 50 gold as a reward.");

		}
		
	}
		
}
