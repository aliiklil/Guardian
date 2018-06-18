package manager;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.Character;
import models.NPC;
import models.Player;

public class CharacterManager {

	private static ArrayList<Character> characterList = new ArrayList<Character>();
	
	private static Character player;

	private static Character npc1;
	private static Character npc2;
	
	public CharacterManager() throws SlickException {
		
		player = new Player();
		//npc1 = new NPC(640, 960, 100, 100, "resources/OrcSpriteSheet.png");
		npc2 = new NPC(32, 32, 100, 100, "resources/SkeletonSpriteSheet.png");
		
		characterList.add(player);
		//characterList.add(npc1);
		characterList.add(npc2);
		
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
		return (Player) player;
	}
	
}

