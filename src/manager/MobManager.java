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
import models.NPC;
import models.Player;
import models.Wolf;

public class MobManager {

	private static ArrayList<Mob> mobList = new ArrayList<Mob>();
	
	private static Player player;

	private static NPC ogus;
	private static NPC halrok;
	
	private static Wolf wolf1;
	
	private ItemTypeManager itemTypeManager = Game.getItemTypeManager();	
	
	public MobManager() throws SlickException {
		
		player = new Player(480, 416, true);
		
		Item stick = new Item(0, 0, itemTypeManager.stick);
		Item shirt = new Item(0, 0, itemTypeManager.shirt);
		Item skirt = new Item(0, 0, itemTypeManager.skirt);
		Item boots = new Item(0, 0, itemTypeManager.boots);
		
		stick.setEquipped(true);
		shirt.setEquipped(true);
		skirt.setEquipped(true);
		boots.setEquipped(true);
		
		player.addItem(stick);
		player.addItem(shirt);
		player.addItem(skirt);
		player.addItem(boots);
		
		player.setEquippedMelee(stick.getItemType());
		player.setEquippedTorso(shirt.getItemType());
		player.setEquippedLegs(skirt.getItemType());
		player.setEquippedBoots(boots.getItemType());
		
		player.setCurrentMeleeAnimation(stick.getItemType().getLookDownAnimation());
		player.setCurrentChestAnimation(shirt.getItemType().getLookDownAnimation());
		player.setCurrentLegsAnimation(skirt.getItemType().getLookDownAnimation());
		player.setCurrentFeetAnimation(boots.getItemType().getLookDownAnimation());

			
		ogus = new NPC(256, 128, 2000, 2000, "resources/OrcSpriteSheet.png", false, null, DialogueManager.ogusDialogues, 300, 10, 0.1, true);
		ogus.addItem(new Item(0, 0, itemTypeManager.dagger));
		ogus.addItem(new Item(0, 0, itemTypeManager.apple));
		ogus.addItem(new Item(0, 0, itemTypeManager.apple));
		ogus.addItem(new Item(0, 0, itemTypeManager.arrow));
		ogus.addItem(new Item(0, 0, itemTypeManager.chainhat));
		ogus.addItem(new Item(0, 0, itemTypeManager.shortspear));
		ogus.addItem(new Item(0, 0, itemTypeManager.chainhelmet));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenspear));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenboots));
		ogus.addItem(new Item(0, 0, itemTypeManager.mediumHpPotion));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenspear));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenboots));
		ogus.addItem(new Item(0, 0, itemTypeManager.mediumHpPotion));
		ogus.addItem(new Item(0, 0, itemTypeManager.clothhood));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenhelmet));
		
		ogus.setEquippedMelee(new Item(0, 0, itemTypeManager.goldenspear).getItemType());
		
		
		
		halrok = new NPC(256, 64, 300, 300, "resources/SkeletonSpriteSheet.png", false, null, DialogueManager.halrokDialogues, 400, 20, 0.3, true);
		ogus.setEquippedMelee(new Item(0, 0, itemTypeManager.ironsword).getItemType());
		
		
		mobList.add(player);
		mobList.add(ogus);
		mobList.add(halrok);
		
		wolf1 = new Wolf(1024, 768, "resources/WolfSpriteSheet.png", 1000, null, 100, 5, true, true);		
		mobList.add(wolf1);
		
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

