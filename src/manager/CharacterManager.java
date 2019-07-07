package manager;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dialogue.Sentence;
import main.Game;
import models.Character;
import models.Item;
import models.NPC;
import models.Player;

public class CharacterManager {

	private static ArrayList<Character> characterList = new ArrayList<Character>();
	
	private static Player player;

	private static NPC ogus;
	private static NPC halrok;
	
	private ItemTypeManager itemTypeManager = Game.getItemTypeManager();	
	
	public CharacterManager() throws SlickException {
		
		player = new Player();
			
		ogus = new NPC(256, 128, 200, 200, "resources/OrcSpriteSheet.png", false, null, DialogueManager.ogusDialogues, 300, 10, 0.1);
		halrok = new NPC(640, 768, 300, 300, "resources/SkeletonSpriteSheet.png", false, null, DialogueManager.halrokDialogues, 400, 20, 0.3);
		ogus.addItem(new Item(0, 0, itemTypeManager.dagger));
		ogus.addItem(new Item(0, 0, itemTypeManager.apple));
		ogus.addItem(new Item(0, 0, itemTypeManager.apple));
		ogus.addItem(new Item(0, 0, itemTypeManager.arrow));
		ogus.addItem(new Item(0, 0, itemTypeManager.chainhat));
		ogus.addItem(new Item(0, 0, itemTypeManager.shortspear));
		ogus.addItem(new Item(0, 0, itemTypeManager.chainhelmet));
		ogus.addItem(new Item(0, 0, itemTypeManager.copperBar));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldspear));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenboots));
		ogus.addItem(new Item(0, 0, itemTypeManager.mediumHealingPotion));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldspear));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenboots));
		ogus.addItem(new Item(0, 0, itemTypeManager.mediumHealingPotion));
		ogus.addItem(new Item(0, 0, itemTypeManager.clothhood));
		ogus.addItem(new Item(0, 0, itemTypeManager.copperBar));
		ogus.addItem(new Item(0, 0, itemTypeManager.goldenhelmet));
		
		
		characterList.add(player);
		characterList.add(ogus);
		characterList.add(halrok);
		
	}
	
	public void update() throws SlickException {
		
		for(Character character : characterList)
			character.update();
		
	}

	public void render(Graphics g) {
	
		ArrayList<Character> characterDrawOrderList = new ArrayList<Character>(characterList);
		
		for (int i = 0; i < characterDrawOrderList.size() - 1; i++) {
			int index = i;
			for (int j = i + 1; j < characterDrawOrderList.size(); j++) {
				if (characterDrawOrderList.get(j).getRelativeToMapY() < characterDrawOrderList.get(index).getRelativeToMapY()) {
					index = j;
				}
			}
			Collections.swap(characterDrawOrderList, index, i);
		}
		
		for(Character character : characterDrawOrderList) {
			if (!character.isAlive()) {
				character.render(g);
			}
		}
			
		for(Character character : characterDrawOrderList) {
			if (character.isAlive()) {
				character.render(g);
			}
		}
		
	}
	
	public static ArrayList<NPC> getNpcList() {
		
		ArrayList<NPC> npcList = new ArrayList<NPC>();
		
		for(Character character : characterList) {
			if(character instanceof NPC) {
				npcList.add((NPC) character);
			}
		}

		return npcList;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
}

