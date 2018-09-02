package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import manager.CharacterManager;
import manager.ChestManager;
import manager.DialogueManager;
import manager.ItemManager;
import manager.ItemTypeManager;
import manager.ProjectileManager;
import models.Map;
import models.Player;

public class Game extends BasicGameState {

	private static Map world;
	private static Map currentMap;

	private static int notWalkableLayerIndex;
	private static int chestLayerIndex;
	private static TiledMap tiledMap;

	private static CharacterManager characterManager;
	private static ProjectileManager projectileManager;
	private static ItemTypeManager itemTypeManager;
	private static ItemManager itemManager;
	private static ChestManager chestManager;
	private static DialogueManager dialogueManager;

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

		world = new Map("resources/World.tmx");
		currentMap = world;

		notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		chestLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("Chest");
		tiledMap = Game.getCurrentMap().getTiledMap();

		itemTypeManager = new ItemTypeManager();
		characterManager = new CharacterManager();
		projectileManager = new ProjectileManager();
		itemManager = new ItemManager();
		chestManager = new ChestManager();
		dialogueManager = new DialogueManager();

	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {

		characterManager.update();
		projectileManager.update();
		itemManager.update();
		chestManager.update();
		
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		world.render(g);
		chestManager.render(g);
		
		itemManager.render(g);
		characterManager.render(g);
		projectileManager.render(g);

		world.renderUpperLayer(g);

		CharacterManager.getPlayer().getInventory().render(g);
		CharacterManager.getPlayer().getHealthBar().render(g);
		CharacterManager.getPlayer().getNewItemWindow().render(g);
		CharacterManager.getPlayer().getDialogueWindow().render(g);

	}

	@Override
	public int getID() {

		return 0;

	}

	public static Map getCurrentMap() {

		return currentMap;

	}

	public static int getNotWalkableLayerIndex() {
		return notWalkableLayerIndex;
	}

	public static int getChestLayerIndex() {
		return chestLayerIndex;
	}

	public static TiledMap getTiledMap() {
		return tiledMap;
	}

	public static ProjectileManager getProjectileManager() {
		return projectileManager;
	}

	public static ItemTypeManager getItemTypeManager() {
		return itemTypeManager;
	}
	
	public static ItemManager getItemManager() {
		return itemManager;
	}
	
	public static ChestManager getChestManager() {
		return chestManager;
	}

}
