package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import manager.CharacterManager;
import manager.ItemManager;
import manager.ProjectileManager;
import models.Map;

public class Game extends BasicGameState {

	private static Map world;
	private static Map currentMap;

	private static int notWalkableLayerIndex;
	private static TiledMap tiledMap;

	private static CharacterManager characterManager;
	private static ProjectileManager projectileManager;
	private static ItemManager itemManager;

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

		world = new Map("resources/World.tmx");
		currentMap = world;

		notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		tiledMap = Game.getCurrentMap().getTiledMap();

		characterManager = new CharacterManager();
		projectileManager = new ProjectileManager();
		itemManager = new ItemManager();

	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {

		characterManager.update();
		projectileManager.update();
		itemManager.update();

	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

		world.render(g);

		itemManager.render(g);
		characterManager.render(g);
		projectileManager.render(g);

		world.renderUpperLayer(g);

		CharacterManager.getPlayer().getInventory().render(g);
		CharacterManager.getPlayer().getHealthBar().render(g);

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

	public static TiledMap getTiledMap() {
		return tiledMap;
	}

	public static ProjectileManager getProjectileManager() {
		return projectileManager;
	}

	public static ItemManager getItemManager() {
		return itemManager;
	}

}
