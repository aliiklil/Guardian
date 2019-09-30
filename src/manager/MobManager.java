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
	public static NPC rico;
	
	public static Monster filthyRat;
	
	public static Monster aggressiveWolf1;
	public static Monster aggressiveWolf2;
	public static Monster aggressiveWolf3;
	
	private ItemTypeManager itemTypeManager = Game.getItemTypeManager();	
	
	public MobManager() throws SlickException {
		
		player = new Player(254, 37, true);
		
		Item stick = new Item(0, 0, itemTypeManager.stick);

		stick.setEquipped(true);
		
		player.addItem(stick);
		
		player.setEquippedMelee(stick.getItemType());
		
		player.setCurrentMeleeAnimation(stick.getItemType().getLookDownAnimation());
			
		ogus = new NPC(48, 37, 2000, 2000, "resources/mob_sprites/npc/OrcSpriteSheet.png", false, null, DialogueManager.ogusDialogues, 300, 10, 0.1, true);
		ogus.addItem(new Item(0, 0, itemTypeManager.dagger));
		ogus.addItem(new Item(0, 0, itemTypeManager.apple));
		ogus.addItem(new Item(0, 0, itemTypeManager.apple));
		ogus.addItem(new Item(0, 0, itemTypeManager.arrow));
		ogus.addItem(new Item(0, 0, itemTypeManager.shortspear));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenspear));
		ogus.addItem(new Item(0, 0, itemTypeManager.mediumHpPotion));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenspear));
		ogus.addItem(new Item(0, 0, itemTypeManager.mediumHpPotion));
		
		ogus.addItem(new Item(0, 0, itemTypeManager.healroot));
		ogus.addItem(new Item(0, 0, itemTypeManager.healroot));
		
		ogus.setEquippedMelee(new Item(0, 0, itemTypeManager.goldenspear).getItemType());
		
		
		
		halrok = new NPC(50, 37, 300, 300, "resources/mob_sprites/npc/SkeletonSpriteSheet.png", false, null, DialogueManager.halrokDialogues, 400, 20, 0.3, true);
		halrok.setEquippedMelee(new Item(0, 0, itemTypeManager.ironsword).getItemType());
		
		jorgen = new NPC(52, 39, 300, 300, "resources/mob_sprites/npc/Jorgen.png", false, null, DialogueManager.jorgenDialogues, 400, 20, 0.3, true);
		jorgen.setEquippedMelee(new Item(0, 0, itemTypeManager.rapier).getItemType());
		
		rico = new NPC(49, 36, 300, 300, "resources/mob_sprites/npc/Rico.png", false, null, DialogueManager.ricoDialogues, 400, 20, 0.3, true);
		rico.setEquippedMelee(new Item(0, 0, itemTypeManager.ironsword).getItemType());
		
		
		
		mobList.add(player);
		
		
		/*
		mobList.add(ogus);
		mobList.add(halrok);
		
		mobList.add(jorgen);
		mobList.add(rico);
		
		filthyRat = new Monster(90, 80, "resources/mob_sprites/monster/RatSpriteSheet.png", 1000, null, 100, 5, true, true);		
		mobList.add(filthyRat);
		
		aggressiveWolf1 = new Monster(70, 75, "resources/mob_sprites/monster/WolfSpriteSheet.png", 500, null, 100, 5, true, true);		
		mobList.add(aggressiveWolf1);
		
		aggressiveWolf2 = new Monster(49, 61, "resources/mob_sprites/monster/WolfSpriteSheet.png", 500, null, 100, 5, true, true);		
		mobList.add(aggressiveWolf2);
		
		aggressiveWolf3 = new Monster(60, 60, "resources/mob_sprites/monster/WolfSpriteSheet.png", 500, null, 100, 5, true, true);		
		mobList.add(aggressiveWolf3);
		*/
		
		
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

