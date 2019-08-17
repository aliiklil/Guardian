package logic;

import dialogue.Dialogue;
import main.Main;
import manager.CharacterManager;
import manager.DialogueManager;
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
					
					
					if(player.getAlchemySkill() == 1) {
						
						currentDialogue.getSentences().get(1).setText("You are now able to create simple potions.");
						
						DialogueManager.alchemyDeskDialogues.add(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(0).setPermanent(true);
						DialogueManager.alchemyDeskDialogues.get(0).setForLearning(true);
						DialogueManager.alchemyDeskDialogues.get(0).addSentence("Level 1 - Simple Potions", "Hero");
						DialogueManager.alchemyDeskDialogues.get(0).addSentence("", "Hero");
						
						DialogueManager.alchemyDeskDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(0).addSentence("Create Small HP Potion (Healberry, Small Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(0).addSentence("I have successfully created a Small HP Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(0).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(1).addSentence("Create Small Mana Potion (Manaberry, Small Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(1).addSentence("I have successfully created a Small Mana Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(1).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(2).addSentence("Create Medium HP Potion (Healplant, Medium Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(2).addSentence("I have successfully created a Medium HP Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(2).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(3).addSentence("Create Medium Mana Potion (Manaplant, Medium Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(3).addSentence("I have successfully created a Medium Mana Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(3).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(0).getChildDialogues().get(4).addSentence("Back", "Hero");
							
						
					}
					
					if(player.getAlchemySkill() == 2) {
						
						currentDialogue.getSentences().get(1).setText("You are now able to create strong potions.");
						
						DialogueManager.alchemyDeskDialogues.add(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(1).setPermanent(true);
						DialogueManager.alchemyDeskDialogues.get(1).setForLearning(true);
						DialogueManager.alchemyDeskDialogues.get(1).addSentence("Level 2 - Strong Potions", "Hero");
						DialogueManager.alchemyDeskDialogues.get(1).addSentence("", "Hero");
						
						DialogueManager.alchemyDeskDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(0).addSentence("Create Big HP Potion (Healroot, Big Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(0).addSentence("I have successfully created a Big HP Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(0).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(1).addSentence("Create Big Mana Potion (Manaroot, Big Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(1).addSentence("I have successfully created a Big Mana Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(1).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(2).addSentence("Create Speed Potion (Wolfnettel, Big Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(2).addSentence("I have successfully created a Speed Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(2).setPermanent(true);
	
						DialogueManager.alchemyDeskDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(1).getChildDialogues().get(3).addSentence("Back", "Hero");
						
					}
					
					if(player.getAlchemySkill() == 3) {
						
						currentDialogue.getSentences().get(1).setText("You are now able to create special potions.");
						
						
						DialogueManager.alchemyDeskDialogues.add(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(2).setPermanent(true);
						DialogueManager.alchemyDeskDialogues.get(2).setForLearning(true);
						DialogueManager.alchemyDeskDialogues.get(2).addSentence("Level 3 - Special Potions", "Hero");
						DialogueManager.alchemyDeskDialogues.get(2).addSentence("", "Hero");
						
						DialogueManager.alchemyDeskDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(0).addSentence("Create Max HP Bonus Potion (Healroot, Goldtruffle, Big Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(0).addSentence("I have successfully created a Permanent HP Bonus Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(0).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(1).addSentence("Create Max Mana Bonus Potion (Manaroot, Goldtruffle, Big Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(1).addSentence("I have successfully created a Permanent Mana Bonus Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(1).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(2).addSentence("Create Strength Potion (Dragonroot, Goldtruffle, Big Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(2).addSentence("I have successfully created a Strength Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(2).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(3).addSentence("Create Dexterity Potion (Goblinweed, Goldtruffle, Big Bottle)", "Hero");
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(3).addSentence("I have successfully created a Dexterity Potion.", "Hero");
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(3).setPermanent(true);
						
						DialogueManager.alchemyDeskDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.alchemyDeskDialogues.get(2).getChildDialogues().get(4).addSentence("Back", "Hero");
						
					}
					
					
					
					
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
					
					
					if(player.getBlacksmithingSkill() == 1) {
						
						currentDialogue.getSentences().get(1).setText("You are now able to create iron weapons and armor.");
						
						DialogueManager.anvilDialogues.add(new Dialogue());
						DialogueManager.anvilDialogues.get(0).setPermanent(true);
						DialogueManager.anvilDialogues.get(0).setForLearning(true);	
						DialogueManager.anvilDialogues.get(0).addSentence("Level 1 - Iron Blacksmithing", "Hero");
						DialogueManager.anvilDialogues.get(0).addSentence("", "Hero");
						
						DialogueManager.anvilDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(0).addSentence("Forge Iron Sword (3 Iron Bar)", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(0).addSentence("I have successfully forged an Iron Sword.", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(0).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(1).addSentence("Forge Longspear (1 Iron Bar, 3 Stick)", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(1).addSentence("I have successfully forged a Longspear.", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(1).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(2).addSentence("Forge Sabre (2 Iron Bar)", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(2).addSentence("I have successfully forged a Sabre.", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(2).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(3).addSentence("Forge Iron Helmet (3 Iron Bars)", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(3).addSentence("I have successfully forged an Iron Helmet.", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(3).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(4).addSentence("Forge Iron Chest (5 Iron Bars)", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(4).addSentence("I have successfully forged an Iron Chest.", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(4).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(5).addSentence("Forge Iron Gloves (2 Iron Bars)", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(5).addSentence("I have successfully forged Iron Gloves.", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(5).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(6).addSentence("Forge Iron Greaves (4 Iron Bars)", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(6).addSentence("I have successfully forged Iron Greaves.", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(6).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(7).addSentence("Forge Iron Boots (2 Iron Bars)", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(7).addSentence("I have successfully forged Iron Boots.", "Hero");
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(7).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(0).getChildDialogues().get(8).addSentence("Back", "Hero");
							
						
					}
					
					if(player.getBlacksmithingSkill() == 2) {
						
						currentDialogue.getSentences().get(1).setText("You are now able to create golden weapons and armor.");
						
						DialogueManager.anvilDialogues.add(new Dialogue());
						DialogueManager.anvilDialogues.get(1).setPermanent(true);
						DialogueManager.anvilDialogues.get(1).setForLearning(true);
						DialogueManager.anvilDialogues.get(1).addSentence("Level 2 - Gold Blacksmithing", "Hero");
						DialogueManager.anvilDialogues.get(1).addSentence("", "Hero");
						
						DialogueManager.anvilDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(0).addSentence("Forge Golden Sword (3 Golden Bars)", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(0).addSentence("I have successfully forged a Golden Sword.", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(0).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(1).addSentence("Forge Golden Spear (3 Golden Bars, 2 Iron Bars)", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(1).addSentence("I have successfully forged a Golden Spear.", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(1).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(2).addSentence("Forge Rapier (1 Golden Bar, 1 Iron Bar)", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(2).addSentence("I have successfully forged a Rapier.", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(2).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(3).addSentence("Forge Golden Helmet (3 Golden Bars)", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(3).addSentence("I have successfully forged an Golden Helmet.", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(3).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(4).addSentence("Forge Golden Chest (5 Golden Bars)", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(4).addSentence("I have successfully forged an Golden Chest.", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(4).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(5).addSentence("Forge Golden Gloves (2 Golden Bars)", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(5).addSentence("I have successfully forged Golden Gloves.", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(5).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(6).addSentence("Forge Golden Greaves (4 Golden Bars)", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(6).addSentence("I have successfully forged Golden Greaves.", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(6).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(7).addSentence("Forge Golden Boots (2 Golden Bars)", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(7).addSentence("I have successfully forged Golden Boots.", "Hero");
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(7).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(1).getChildDialogues().get(8).addSentence("Back", "Hero");
						
					}
					
					if(player.getBlacksmithingSkill() == 3) {
						
						currentDialogue.getSentences().get(1).setText("You are now able to create mithril weapons and armor.");
						
						
						DialogueManager.anvilDialogues.add(new Dialogue());
						DialogueManager.anvilDialogues.get(2).setPermanent(true);
						DialogueManager.anvilDialogues.get(2).setForLearning(true);
						DialogueManager.anvilDialogues.get(2).addSentence("Level 3 - Mithril Blacksmithing", "Hero");
						DialogueManager.anvilDialogues.get(2).addSentence("", "Hero");
						
						DialogueManager.anvilDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(0).addSentence("Forge Mithril Sword (3 Mithril Bars)", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(0).addSentence("I have successfully forged a Mithril Sword.", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(0).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(1).addSentence("Forge Mithril Spear (3 Mithril Bars, 2 Iron Bars)", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(1).addSentence("I have successfully forged a Mithril Spear.", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(1).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(2).addSentence("Forge Mithril Rapier (1 Mithril Bar, 1 Iron Bar)", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(2).addSentence("I have successfully forged a Mithril Rapier.", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(2).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(3).addSentence("Forge Mithril Helmet (3 Mithril Bars)", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(3).addSentence("I have successfully forged an Mithril Helmet.", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(3).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(4).addSentence("Forge Mithril Chest (5 Mithril Bars)", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(4).addSentence("I have successfully forged an Mithril Chest.", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(4).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(5).addSentence("Forge Mithril Gloves (2 Mithril Bars)", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(5).addSentence("I have successfully forged Mithril Gloves.", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(5).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(6).addSentence("Forge Mithril Greaves (4 Mithril Bars)", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(6).addSentence("I have successfully forged Mithril Greaves.", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(6).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(7).addSentence("Forge Mithril Boots (2 Mithril Bars)", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(7).addSentence("I have successfully forged Mithril Boots.", "Hero");
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(7).setPermanent(true);
						
						DialogueManager.anvilDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.anvilDialogues.get(2).getChildDialogues().get(8).addSentence("Back", "Hero");
						
					}
					
					
					
					
				} else {
					currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
				}
			
			} else {
				
				currentDialogue.getSentences().get(1).setText("You have already mastered blacksmithing.");
				
			}
		}
		
		
		
		
		
		if(currentDialogue.getSentences().get(0).getText().equals("Runeforging + 1 (Costs 10LP)")) {
			
			if(player.getRuneForgingSkill() < 3) {
			
				if(player.getLearningPoints() >= 10) {
					player.setRuneForgingSkill(player.getRuneForgingSkill() + 1);
					player.setLearningPoints(player.getLearningPoints() - 10);
					String text = "Runeforging + 1";
					player.getCenteredText().showText(text, Main.WIDTH/2 - (text.length() * 9)/2, Main.HEIGHT/2);
					
					
					if(player.getRuneForgingSkill() == 1) {
						
						currentDialogue.getSentences().get(1).setText("You are now able to create basic runes.");
						
						DialogueManager.runeForgingDialogues.add(new Dialogue());
						DialogueManager.runeForgingDialogues.get(0).setPermanent(true);
						DialogueManager.runeForgingDialogues.get(0).setForLearning(true);	
						DialogueManager.runeForgingDialogues.get(0).addSentence("Level 1 - Basic Runes", "Hero");
						DialogueManager.runeForgingDialogues.get(0).addSentence("", "Hero");
						
						DialogueManager.runeForgingDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(0).addSentence("Heal Light Wounds Rune (Healberry, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(0).addSentence("I have successfully forged a Heal Light Wounds Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(0).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(1).addSentence("Icelance Rune (Smaragd, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(1).addSentence("I have successfully forged an Icelance Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(1).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(2).addSentence("Iceblock Rune (Icecube, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(2).addSentence("I have successfully forged an Iceblock Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(2).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(3).addSentence("Summon Wolf Rune (Wolf Hide, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(3).addSentence("I have successfully forged a Summon Wolf Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(3).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(0).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(0).getChildDialogues().get(4).addSentence("Back", "Hero");
							
						
					}
					
					if(player.getRuneForgingSkill() == 2) {
						
						currentDialogue.getSentences().get(1).setText("You are now able to create intermediate runes.");
						
						DialogueManager.runeForgingDialogues.add(new Dialogue());
						DialogueManager.runeForgingDialogues.get(1).setPermanent(true);
						DialogueManager.runeForgingDialogues.get(1).setForLearning(true);
						DialogueManager.runeForgingDialogues.get(1).addSentence("Level 2 - Intermediate Runes", "Hero");
						DialogueManager.runeForgingDialogues.get(1).addSentence("", "Hero");
						
						DialogueManager.runeForgingDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(0).addSentence("Heal Medium Wounds Rune (Healplant, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(0).addSentence("I have successfully forged a Heal Medium Wounds Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(0).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(1).addSentence("Fireball Rune (Pitch, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(1).addSentence("I have successfully forged a Fireball Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(1).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(2).addSentence("Icewave Rune (Aquamarine, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(2).addSentence("I have successfully forged an Icewave Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(2).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(3).addSentence("Summon Skeleton Rune (Bone, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(3).addSentence("I have successfully forged a Summon Skeleton Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(3).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(1).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(1).getChildDialogues().get(4).addSentence("Back", "Hero");
						
					}
					
					if(player.getRuneForgingSkill() == 3) {
						
						currentDialogue.getSentences().get(1).setText("You are now able to create powerful runes.");
						
						
						DialogueManager.runeForgingDialogues.add(new Dialogue());
						DialogueManager.runeForgingDialogues.get(2).setPermanent(true);
						DialogueManager.runeForgingDialogues.get(2).setForLearning(true);
						DialogueManager.runeForgingDialogues.get(2).addSentence("Level 3 - Powerful Runes", "Hero");
						DialogueManager.runeForgingDialogues.get(2).addSentence("", "Hero");
						
						DialogueManager.runeForgingDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(0).addSentence("Heal Heavy Wounds Rune (Healroot, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(0).addSentence("I have successfully forged a Heal Heavy Wounds Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(0).setPermanent(true);

						DialogueManager.runeForgingDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(1).addSentence("Titanspear Rune (Chrystal, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(1).addSentence("I have successfully forged a Titanspear Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(1).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(2).addSentence("Firerain Rune (Sulfur, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(2).addSentence("I have successfully forged a Firerain Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(2).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(3).addSentence("Summon Orc Warrior Rune (Orc Tooth, Empty Rune)", "Hero");
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(3).addSentence("I have successfully forged a Summon Orc Warrior Rune.", "Hero");
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(3).setPermanent(true);
						
						DialogueManager.runeForgingDialogues.get(2).addChildDialogue(new Dialogue());
						DialogueManager.runeForgingDialogues.get(2).getChildDialogues().get(4).addSentence("Back", "Hero");
						
					}
					
					
					
					
				} else {
					currentDialogue.getSentences().get(1).setText("You don't have enough experience. Come back later.");
				}
			
			} else {
				
				currentDialogue.getSentences().get(1).setText("You have already mastered runeforging.");
				
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
