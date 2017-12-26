package main;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	
	private static Map world;
	private static Map currentMap;
	private static Player player;
	private static NPC npc1;
	private static NPC npc2;
	
	private static ArrayList<NPC> npcList = new ArrayList<NPC>();
		
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		
		world = new Map("resources/World.tmx");
		currentMap = world;
		player = new Player();
		npc1 = new NPC(150, 150, 200, "resources/OrcSpriteSheet.png");
		npc2 = new NPC(300, 350, 500, "resources/SkeletonSpriteSheet.png");
		
		npcList.add(npc1);
		npcList.add(npc2);
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {

		player.update();
		
		for(NPC npc: npcList)
			npc.update();
		
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		world.render(g);
		
		for(NPC npc: npcList)
			npc.render(g);
		player.render(g);
		

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

}



