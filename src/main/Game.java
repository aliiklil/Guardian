package main;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import manager.ItemManager;
import manager.ProjectileManager;
import models.Map;
import models.NPC;
import models.Player;

public class Game extends BasicGameState {
	
	private static Map world;
	private static Map currentMap;
	private static Player player;
	private static NPC npc1;
	private static NPC npc2;
	
	private static ArrayList<NPC> npcList = new ArrayList<NPC>();
	
	private static ProjectileManager projectileManager;
	private static ItemManager itemManager;
			
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		world = new Map("resources/World.tmx");
		currentMap = world;
		player = new Player();
		npc1 = new NPC(96, 96, 35, "resources/OrcSpriteSheet.png");
		npc2 = new NPC(192, 160, 30, "resources/SkeletonSpriteSheet.png");
		
		npcList.add(npc1);
		npcList.add(npc2);
		
		projectileManager = new ProjectileManager();
		itemManager = new ItemManager();
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {

		player.update();
		
		for(NPC npc: npcList)
			npc.update();
		
		projectileManager.update();
		itemManager.update();

	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		world.render(g);
		
		itemManager.render(g);
		
		for(NPC npc: npcList)
			npc.render(g);
		
		projectileManager.render(g);
		
		player.render(g);
		
		world.renderOverPlayer(g);
		
		player.getInventory().render(g);
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
		return npcList;
	}
	
	public static ProjectileManager getProjectileManager() {
		return projectileManager;
	}

	public static ItemManager getItemManager() {
		return itemManager;
	}
	
}



