package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import logic.Alchemy;
import logic.Blacksmithing;
import logic.Learning;
import logic.RuneForging;
import manager.AlchemyDeskManager;
import manager.AnvilManager;
import manager.CharacterManager;
import manager.ChestManager;
import manager.DialogueManager;
import manager.ItemManager;
import manager.ItemTypeManager;
import manager.ProjectileManager;
import manager.RuneTableManager;
import models.Map;

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
	private static DialogueManager dialogueManager;
	
	private static ChestManager chestManager;
	private static AnvilManager anvilManager;
	private static AlchemyDeskManager alchemyDeskManager;
	private static RuneTableManager runeTableManager;
	
	public static Blacksmithing blacksmithing;
	public static Alchemy alchemy;
	public static RuneForging runeForging;
	public static Learning learning;

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
		dialogueManager = new DialogueManager();
		
		chestManager = new ChestManager();
		anvilManager = new AnvilManager();
		alchemyDeskManager = new AlchemyDeskManager();
		runeTableManager = new RuneTableManager();
		
		alchemy = new Alchemy();
		blacksmithing = new Blacksmithing();
		runeForging = new RuneForging();
		learning = new Learning();
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {

		characterManager.update();
		projectileManager.update();
		itemManager.update();
		
		chestManager.update();
		anvilManager.update();
		alchemyDeskManager.update();
		runeTableManager.update();
		
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		world.render(g);
		
		chestManager.render(g);
		anvilManager.render(g);
		alchemyDeskManager.render(g);
		runeTableManager.renderBottom(g);
		itemManager.render(g);
		
		characterManager.render(g);
		runeTableManager.renderTop(g);
		projectileManager.render(g);
		
		world.renderUpperLayer(g);
		projectileManager.renderUpperLayer(g);

		CharacterManager.getPlayer().getInventoryWindow().render(g);
		CharacterManager.getPlayer().getHealthBar().render(g);
		CharacterManager.getPlayer().getManaBar().render(g);
		CharacterManager.getPlayer().getNewItemWindow().render(g);
		CharacterManager.getPlayer().getDialogueWindow().render(g);
		CharacterManager.getPlayer().getCenteredText().render(g);
		CharacterManager.getPlayer().getLevelUpText().render(g);
		CharacterManager.getPlayer().getTradingWindow().render(g);
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

	public static AnvilManager getAnvilManager() {
		return anvilManager;
	}

	public static AlchemyDeskManager getAlchemyDeskManager() {
		return alchemyDeskManager;
	}

	public static Alchemy getAlchemy() {
		return alchemy;
	}

	public static Blacksmithing getBlacksmithing() {
		return blacksmithing;
	}
	
	public static RuneForging getRuneForging() {
		return runeForging;
	}

	public static Learning getLearning() {
		return learning;
	}
	
}
