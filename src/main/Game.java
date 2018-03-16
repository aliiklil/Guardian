package main;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import manager.ItemManager;
import manager.ProjectileManager;
import models.Character;
import models.Map;
import models.NPC;
import models.Player;

public class Game extends BasicGameState {
	
	private static Map world;
	private static Map currentMap;
	private static Character player;
	private static Character npc1;
	private static Character npc2;
	
	private static ArrayList<Character> characterList = new ArrayList<Character>();
	
	private static ProjectileManager projectileManager;
	private static ItemManager itemManager;
			
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		world = new Map("resources/World.tmx");
		currentMap = world;
		
		player = new Player();
		npc1 = new NPC(192, 160, 35, 35, "resources/OrcSpriteSheet.png");
		npc2 = new NPC(256, 160, 25, 30, "resources/SkeletonSpriteSheet.png");
		
		characterList.add(player);
		characterList.add(npc1);
		characterList.add(npc2);
		
		projectileManager = new ProjectileManager();
		itemManager = new ItemManager();

	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
					
		for(Character character : characterList)
			character.update();
		
		projectileManager.update();
		itemManager.update();
		
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		world.render(g);
		itemManager.render(g);
		projectileManager.render(g);
				
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
		
		for(Character character : characterDrawOrderList)
			character.render(g);
						
		world.renderUpperLayer(g);
		
		((Player) player).getInventory().render(g);
		player.getHealthBar().render(g);
		
	}
	
	@Override
	public int getID() {

		return 0;
		
	}
	
	public static Map getCurrentMap() {
		
		return currentMap;
		
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
	
	public static ProjectileManager getProjectileManager() {
		return projectileManager;
	}

	public static ItemManager getItemManager() {
		return itemManager;
	}
	
}


