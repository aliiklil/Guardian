package logic;

import org.newdawn.slick.SlickException;

import dialogue.Dialogue;
import main.Game;
import manager.CharacterManager;
import models.Item;
import models.Player;

public class RuneForging {

	public void checkIfPlayerIsUsingRuneTable(Dialogue currentDialogue) throws SlickException {
				
		createHealLightWoundsRune(currentDialogue);
		createIceLanceRune(currentDialogue);
		createIceblockRune(currentDialogue);
		createSummonWolfRune(currentDialogue);
		
		createHealMediumWoundsRune(currentDialogue);
		createFireballRune(currentDialogue);
		createIcewaveRune(currentDialogue);
		createSummonSkeletonRune(currentDialogue);
		
		createHealHeavyWoundsRune(currentDialogue);
		createTitanspearRune(currentDialogue);
		createFirerainRune(currentDialogue);
		createSummonOrcWarriorRune(currentDialogue);
	}
	
	private void createHealLightWoundsRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Heal Light Wounds Rune (Healberry, Empty Rune)")) {
			
			int healberriesCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Healberry")) {
					healberriesCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(healberriesCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().healberry);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().healLightWoundsRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Heal Light Wounds Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createTitanspearRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Titanspear Rune (Chrystal, Empty Rune)")) {
			
			int chrystalCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Chrystal")) {
					chrystalCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(chrystalCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().chrystal);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().titanspearRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Titanspear Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createIceblockRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Iceblock Rune (Icecube, Empty Rune)")) {
			
			int icecubeCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Icecube")) {
					icecubeCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(icecubeCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().icecube);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().iceblockRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged an Iceblock Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createSummonWolfRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Summon Wolf Rune (Wolf Hide, Empty Rune)")) {
			
			int wolfHideCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Wolf Hide")) {
					wolfHideCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(wolfHideCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().wolfHide);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().summonWolfRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Summon Wolf Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createHealMediumWoundsRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Heal Medium Wounds Rune (Healplant, Empty Rune)")) {
			
			int healplantCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Healplant")) {
					healplantCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(healplantCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().healplant);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().healMediumWoundsRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Heal Medium Wounds Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createIceLanceRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Icelance Rune (Smaragd, Empty Rune)")) {
			
			int smaragdCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Smaragd")) {
					smaragdCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(smaragdCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().smaragd);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().icelanceRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged an Icelance Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createIcewaveRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Icewave Rune (Aquamarine, Empty Rune)")) {
			
			int aquamarineCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Aquamarine")) {
					aquamarineCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(aquamarineCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().aquamarine);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().icewaveRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged an Icewave Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createSummonSkeletonRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Summon Skeleton Rune (Bone, Empty Rune)")) {
			
			int boneCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Bone")) {
					boneCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(boneCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().bone);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().summonSkeletonRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Summon Skeleton Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createHealHeavyWoundsRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Heal Heavy Wounds Rune (Healroot, Empty Rune)")) {
			
			int healrootCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Healroot")) {
					healrootCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(healrootCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().healroot);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().healHeavyWoundsRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Heal Heavy Wounds Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createFireballRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Fireball Rune (Pitch, Empty Rune)")) {
			
			int pitchCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Pitch")) {
					pitchCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(pitchCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().pitch);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().fireballRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Fireball Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createFirerainRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Firerain Rune (Sulfur, Empty Rune)")) {
			
			int sulfurCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Sulfur")) {
					sulfurCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(sulfurCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().sulfur);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().firerainRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Firerain Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
	private void createSummonOrcWarriorRune(Dialogue currentDialogue) throws SlickException {
		
		Player player = CharacterManager.getPlayer();
		
		if(currentDialogue.getSentences().get(0).getText().equals("Summon Orc Warrior Rune (Orc Tooth, Empty Rune)")) {
			
			int orcToothCount = 0;
			int emptyRuneCount = 0;
			
			for(int i = 0; i < player.getInventoryList().size(); i++) {
				if(player.getInventoryList().get(i).getItemType().getName().equals("Orc Tooth")) {
					orcToothCount = player.getItemCountList().get(i);
				}
				if(player.getInventoryList().get(i).getItemType().getName().equals("Empty Rune")) {
					emptyRuneCount = player.getItemCountList().get(i);
				}
			}
			
			if(orcToothCount >= 1 && emptyRuneCount >= 1) {
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().orcTooth);
				player.getInventoryWindow().removeItem(Game.getItemTypeManager().emptyRune);
				
				player.addItem(new Item(0, 0, Game.getItemTypeManager().summonOrcWarriorRune));
				currentDialogue.getSentences().get(1).setText("I have successfully forged a Summon Orc Warrior Rune.");
			} else {
				currentDialogue.getSentences().get(1).setText("I don't have the resources.");
			}
			
		}
		
	}
	
		
}
