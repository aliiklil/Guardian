package logic;

import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import main.Main;
import manager.MobManager;
import manager.QuestManager;
import models.Player;

public class QuestLogic {
	
	public void updateQuestLogic(Dialogue currentDialogue) throws SlickException {
				
		
		updateHalroksRatProblem(currentDialogue);
		updateLostChrystal(currentDialogue);
		


	}
	
	private void updateHalroksRatProblem(Dialogue currentDialogue) throws SlickException {
				
		if(MobManager.getPlayer().isYPressed() && currentDialogue.getQuestTitle() != null && currentDialogue.getQuestTitle().equals("Halrok's Rat Problem") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().halroksRatProblem.setActive(true);
						
			Game.getQuestManager().halroksRatProblem.getNotes().add("Halrok said that he is very scared because of a rat which is currently south from here. He wants me too kill it.");
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			MobManager.getPlayer().getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);

		}
		System.out.println("aaaaaaaaaa");
		System.out.println(MobManager.filthyRat.isKillEvent());
		if(Game.getQuestManager().halroksRatProblem.isActive() && MobManager.filthyRat.isKillEvent()) {
			System.out.println("KKKKKKKKKKKKKKKK");
			MobManager.filthyRat.consumeKillEvent();
			
			Dialogue dialogue = new Dialogue();
			dialogue.addSentence("I killed the rat.", "Hero");
			dialogue.addSentence("Nice. Do you want 50 gold or a heath potion?", "Halrok");
			
			Dialogue dialogue2 = new Dialogue();
			dialogue2.addSentence("I want the gold.", "Hero");
			dialogue2.addSentence("Here take it.", "Halrok");
			
			Dialogue dialogue3 = new Dialogue();
			dialogue3.addSentence("I want the health potion.", "Hero");
			dialogue3.addSentence("Here, take the health potion. But watch out, it is pretty strong stuff.", "Halrok");
			
			dialogue.addChildDialogue(dialogue2);
			dialogue.addChildDialogue(dialogue3);
			
			MobManager.halrok.getStartingDialogues().add(dialogue);
						
		}
		
	}

	private void updateLostChrystal(Dialogue currentDialogue) throws SlickException {
				
		if(currentDialogue.getQuestTitle() != null && currentDialogue.getQuestTitle().equals("Lost Chrystal") && !currentDialogue.isQuestStarted()) {
			
			Game.getQuestManager().lostChrystal.setActive(true);
			
			Game.getQuestManager().lostChrystal.getNotes().add("Ogus lost his chrystal.");
			Game.getQuestManager().lostChrystal.getNotes().add("It should be east from here.");
			Game.getQuestManager().lostChrystal.getNotes().add("I will try to find it.");
			
			currentDialogue.setQuestStarted(true);
			
			String text = "New quest log entry";
			MobManager.getPlayer().getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
			
		}
		
	}
		
}
