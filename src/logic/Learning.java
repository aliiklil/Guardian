package logic;

import dialogue.Dialogue;
import main.Main;
import manager.CharacterManager;
import models.Player;

public class Learning {

	public void checkIfPlayerLearns(Dialogue currentDialogue) {
		
		Player player = CharacterManager.getPlayer();
		
		//If player wants to learn somethings, which he already has learned
		if(currentDialogue.getSentences().get(0).getText().contains("(Learned already)")) {
			currentDialogue.getSentences().get(1).setText("You have already learned it.");
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Strength + 5 (Costs 5LP)")) {
			if(player.getLearningPoints() >= 5) {
				player.setStrength(player.getStrength() + 5);
				player.setLearningPoints(player.getLearningPoints() - 5);
				String text = "Strength + 5";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Dexterity + 5 (Costs 5LP)")) {
			if(player.getLearningPoints() >= 5) {
				player.setDexterity(player.getDexterity() + 5);
				player.setLearningPoints(player.getLearningPoints() - 5);
				String text = "Dexterity + 5";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Wisdom + 5 (Costs 5LP)")) {
			if(player.getLearningPoints() >= 5) {
				player.setWisdom(player.getWisdom() + 5);
				player.setLearningPoints(player.getLearningPoints() - 5);
				String text = "Wisdom + 5";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}

		if(currentDialogue.getSentences().get(0).getText().equals("Health Points + 5 (Costs 5LP)")) {
			if(player.getLearningPoints() >= 5) {
				player.setHealthPoints(player.getHealthPoints() + 5);
				player.setLearningPoints(player.getLearningPoints() - 5);
				String text = "Health Points + 5";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Mana + 5 (Costs 5LP)")) {
			if(player.getLearningPoints() >= 5) {
				player.setMana(player.getMana() + 5);
				player.setLearningPoints(player.getLearningPoints() - 5);
				String text = "Mana + 5";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}

		if(currentDialogue.getSentences().get(0).getText().equals("Melee Skill + 5 (Costs 5LP)")) {
			if(player.getLearningPoints() >= 5) {
				player.setMeleeSkill(player.getMeleeSkill() + 5);
				player.setLearningPoints(player.getLearningPoints() - 5);
				String text = "Melee Skill + 5";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
				
		if(currentDialogue.getSentences().get(0).getText().equals("Bow Skill + 5 (Costs 5LP)")) {
			if(player.getLearningPoints() >= 5) {
				player.setBowSkill(player.getBowSkill() + 5);
				player.setLearningPoints(player.getLearningPoints() - 5);
				String text = "Bow Skill + 5";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Spell Skill + 5 (Costs 5LP)")) {
			if(player.getLearningPoints() >= 5) {
				player.setSpellSkill(player.getSpellSkill() + 5);
				player.setLearningPoints(player.getLearningPoints() - 5);
				String text = "Spell Skill + 5";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Lockpicking + 1 (Costs 10LP)")) {
			
			if(player.getLockPickingSkill() < 3) {
			
				if(player.getLearningPoints() >= 10) {
					player.setLockPickingSkill(player.getLockPickingSkill() + 1);
					player.setLearningPoints(player.getLearningPoints() - 10);
					String text = "Lockpicking + 1";
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					currentDialogue.getSentences().get(1).setText("You are a fast learner.");
				} else {
					currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
				}
			
			} else {
				
				currentDialogue.getSentences().get(1).setText("You have already mastered lockpicking.");
				
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Alchemy + 1 (Costs 10LP)")) {
					
					if(player.getAlchemySkill() < 3) {
					
						if(player.getLearningPoints() >= 10) {
							player.setAlchemySkill(player.getAlchemySkill() + 1);
							player.setLearningPoints(player.getLearningPoints() - 10);
							String text = "Alchemy + 1";
							player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
							currentDialogue.getSentences().get(1).setText("You are a fast learner.");
						} else {
							currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
						}
					
					} else {
						
						currentDialogue.getSentences().get(1).setText("You have already mastered alchemy.");
						
					}
				}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Blacksmithing + 1 (Costs 10LP)")) {
			
			if(player.getBlacksmithingSkill() < 3) {
			
				if(player.getLearningPoints() >= 10) {
					player.setBlacksmithingSkill(player.getBlacksmithingSkill() + 1);
					player.setLearningPoints(player.getLearningPoints() - 10);
					String text = "Blacksmithing + 1";
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					currentDialogue.getSentences().get(1).setText("You are a fast learner.");
				} else {
					currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
				}
			
			} else {
				
				currentDialogue.getSentences().get(1).setText("You have already mastered blacksmithing.");
				
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Take Furs (Costs 10LP)")) {
			if(player.getLearningPoints() >= 10) {
				currentDialogue.getSentences().get(0).setText("Take Furs (Learned already)");
				player.setTakeFurs(true);
				player.setLearningPoints(player.getLearningPoints() - 10);
				String text = "Learned: Take Furs";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Take Trophies (Costs 10LP)")) {
			if(player.getLearningPoints() >= 10) {
				currentDialogue.getSentences().get(0).setText("Take Trophies (Learned already)");
				player.setTakeTrophies(true);
				player.setLearningPoints(player.getLearningPoints() - 10);
				String text = "Learned: Take Trophies";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("HP Regeneration (Costs 10LP)")) {
			if(player.getLearningPoints() >= 10) {
				currentDialogue.getSentences().get(0).setText("HP Regeneration (Learned already)");
				player.setHpRegeneration(true);
				player.setLearningPoints(player.getLearningPoints() - 10);
				String text = "Learned: HP Regeneration";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
		
		if(currentDialogue.getSentences().get(0).getText().equals("Mana Regeneration (Costs 10LP)")) {
			if(player.getLearningPoints() >= 10) {
				currentDialogue.getSentences().get(0).setText("Mana Regeneration (Learned already)");
				player.setManaRegeneration(true);
				player.setLearningPoints(player.getLearningPoints() - 10);
				String text = "Learned: Mana Regeneration";
				player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
				currentDialogue.getSentences().get(1).setText("You are a fast learner.");
			} else {
				currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
			}
		}
			
				
	}

}
