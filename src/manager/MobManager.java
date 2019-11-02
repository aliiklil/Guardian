package manager;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dialogue.Sentence;
import main.Game;
import models.Character;
import models.Item;
import models.ItemType;
import models.Mob;
import models.Monster;
import models.NPC;
import models.Player;

public class MobManager {

	private static ArrayList<Mob> mobList = new ArrayList<Mob>();
	
	public static Player player;

	public static NPC ogus;
	public static NPC halrok;
	
	public static NPC jorgen;
		
	public static Monster aggressiveWolf;
	
	public static Monster wolf1;
	public static Monster wolf2;
	public static Monster wolf3;
	
	private ItemTypeManager itemTypeManager = Game.getItemTypeManager();	
	
	public MobManager() throws SlickException {
		
		player = new Player(89, 11, true);
		
		Item longspear = new Item(0, 0, itemTypeManager.longspear);
		longspear.setEquipped(true);
		player.addItem(longspear);
		player.setEquippedMelee(longspear.getItemType());
		player.setCurrentMeleeAnimation(longspear.getItemType().getLookDownAnimation());
		
		Item ironArmor = new Item(0, 0, itemTypeManager.ironArmor);
		ironArmor.setEquipped(true);
		player.addItem(ironArmor);
		player.setEquippedArmor(ironArmor.getItemType());
		player.setCurrentArmorAnimation(ironArmor.getItemType().getLookDownAnimation());
			
		Item bow = new Item(0, 0, itemTypeManager.bow);
		bow.setEquipped(true);
		player.addItem(bow);
		player.setEquippedBow(bow.getItemType());
		player.setCurrentBowAnimation(bow.getItemType().getLookDownAnimation());
		
		Item fireballRune = new Item(0, 0, itemTypeManager.fireballRune);
		player.addItem(fireballRune);
		player.setEquippedSpell(fireballRune.getItemType());
		
		
		Item arrow = new Item(0, 0, itemTypeManager.arrow);
		player.addItem(arrow);
		player.addItem(arrow);
		player.addItem(arrow);
		
		
		Item smallHpPotion = new Item(0, 0, itemTypeManager.smallHpPotion);
		player.addItem(smallHpPotion);
		
		Item smallManaPotion = new Item(0, 0, itemTypeManager.smallManaPotion);
		player.addItem(smallManaPotion);
		
		Item gold = new Item(0, 0, itemTypeManager.gold);
		player.addItem(gold);
		
		
		ogus = new NPC(91, 111, 2000, 2000, "resources/mob_sprites/npc/Ogus.png", false, null, DialogueManager.ogusDialogues, 300, 10, 0.1, true);
		ogus.setEquippedMelee(new Item(0, 0, itemTypeManager.goldenspear).getItemType());
		

		halrok = new NPC(112, 2, 300, 300, "resources/mob_sprites/npc/Halrok.png", false, null, DialogueManager.halrokDialogues, 400, 20, 0.3, true);
		halrok.setEquippedMelee(new Item(0, 0, itemTypeManager.ironsword).getItemType());
		
		
		jorgen = new NPC(98, 24, 300, 300, "resources/mob_sprites/npc/Jorgen.png", false, null, DialogueManager.jorgenDialogues, 400, 20, 0.3, true);
		jorgen.setEquippedMelee(new Item(0, 0, itemTypeManager.rapier).getItemType());
		jorgen.addItem(new Item(0, 0, itemTypeManager.dagger));
		jorgen.addItem(new Item(0, 0, itemTypeManager.apple));
		jorgen.addItem(new Item(0, 0, itemTypeManager.arrow));
		jorgen.addItem(new Item(0, 0, itemTypeManager.arrow));
		jorgen.addItem(new Item(0, 0, itemTypeManager.arrow));
		jorgen.addItem(new Item(0, 0, itemTypeManager.arrow));
		jorgen.addItem(new Item(0, 0, itemTypeManager.shortspear));
		jorgen.addItem(new Item(0, 0, itemTypeManager.smallHpPotion));
		jorgen.addItem(new Item(0, 0, itemTypeManager.leatherArmor));
		jorgen.addItem(new Item(0, 0, itemTypeManager.healroot));
		jorgen.addItem(new Item(0, 0, itemTypeManager.healroot));

		
		aggressiveWolf = new Monster(133, 5, "resources/mob_sprites/monster/WolfSpriteSheet.png", 500, null, 100, 2, true, true);		
		
		
		wolf1 = new Monster(74, 126, "resources/mob_sprites/monster/WolfSpriteSheet.png", 500, null, 100, 2, true, true);		
		wolf2 = new Monster(49, 116, "resources/mob_sprites/monster/WolfSpriteSheet.png", 500, null, 100, 2, true, true);		
		wolf3 = new Monster(85, 142, "resources/mob_sprites/monster/WolfSpriteSheet.png", 500, null, 100, 2, true, true);		
		
		mobList.add(player);
		mobList.add(ogus);
		mobList.add(halrok);
		mobList.add(jorgen);

		mobList.add(aggressiveWolf);
		
		mobList.add(wolf1);
		mobList.add(wolf2);
		mobList.add(wolf3);
	
	}
	
	public void update() throws SlickException {
		
		for(Mob mob : mobList)
			mob.update();

	}

	public void render(Graphics g) throws SlickException {
	
		ArrayList<Mob> mobDrawOrderList = new ArrayList<Mob>(mobList);
		
		for (int i = 0; i < mobDrawOrderList.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < mobDrawOrderList.size(); j++) {
				if (mobDrawOrderList.get(j).getRelativeToMapY() < mobDrawOrderList.get(index).getRelativeToMapY()) {
					index = j;
				}
			}
			Collections.swap(mobDrawOrderList, index, i);
		}
		
		for(Mob mob : mobDrawOrderList) {
			if (!mob.isAlive()) {
				mob.render(g);
			}
		}
			
		for(Mob mob : mobDrawOrderList) {
			if (mob.isAlive()) {
				mob.render(g);
			}
		}
		
	}
	
	public static ArrayList<Mob> getMobList() {
		return mobList;
	}
	
	public static ArrayList<Mob> getMobListWithoutPlayer() {
			
		ArrayList<Mob> mobListWithoutPlayer = new ArrayList<Mob>();
		
		for(Mob mob : mobList) {
			if (!(mob instanceof Player)) {
				mobListWithoutPlayer.add(mob);
			}
		}

		return mobListWithoutPlayer;
	}
	
	public static ArrayList<NPC> getNpcList() {
		
		ArrayList<NPC> npcList = new ArrayList<NPC>();
		
		for(Mob mob : mobList) {
			if (mob instanceof NPC) {
				npcList.add((NPC)mob);
			}
		}

		return npcList;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
}

