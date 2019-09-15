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
import logic.QuestLogic;
import logic.RuneForging;
import manager.AlchemyDeskManager;
import manager.AnvilManager;
import manager.ChestManager;
import manager.DialogueManager;
import manager.ItemManager;
import manager.ItemTypeManager;
import manager.MobManager;
import manager.ProjectileManager;
import manager.QuestManager;
import manager.RuneTableManager;
import models.Map;

public class Game extends BasicGameState {

	private static Map world;
	private static Map currentMap;

	private static int notWalkableLayerIndex;
	private static int chestLayerIndex;
	private static TiledMap tiledMap;

	private static MobManager mobManager;
	private static ProjectileManager projectileManager;
	private static ItemTypeManager itemTypeManager;
	private static ItemManager itemManager;
	private static DialogueManager dialogueManager;
	private static QuestManager questManager;

	private static ChestManager chestManager;
	private static AnvilManager anvilManager;
	private static AlchemyDeskManager alchemyDeskManager;
	private static RuneTableManager runeTableManager;

	public static Blacksmithing blacksmithing;
	public static Alchemy alchemy;
	public static RuneForging runeForging;
	public static Learning learning;
	public static QuestLogic questLogic;

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

		world = new Map("resources/World.tmx");
		currentMap = world;

		notWalkableLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("NotWalkable");
		chestLayerIndex = Game.getCurrentMap().getTiledMap().getLayerIndex("Chest");
		tiledMap = Game.getCurrentMap().getTiledMap();

		itemTypeManager = new ItemTypeManager();
		mobManager = new MobManager();
		projectileManager = new ProjectileManager();
		itemManager = new ItemManager();
		dialogueManager = new DialogueManager();
		questManager = new QuestManager();
		
		chestManager = new ChestManager();
		anvilManager = new AnvilManager();
		alchemyDeskManager = new AlchemyDeskManager();
		runeTableManager = new RuneTableManager();
		
		alchemy = new Alchemy();
		blacksmithing = new Blacksmithing();
		runeForging = new RuneForging();
		learning = new Learning();
		questLogic = new QuestLogic();
		
		world.setOffsetX(MobManager.getPlayer().getCenterX() - 21 * 32 + 16);
		world.setOffsetY(MobManager.getPlayer().getCenterY() - 13 * 32 + 16);
		
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {

		mobManager.update();
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
		
		mobManager.render(g);
		
		runeTableManager.renderTop(g);
		projectileManager.render(g);
		
		world.renderUpperLayer(g);
		projectileManager.renderUpperLayer(g);

		MobManager.getPlayer().getInventoryWindow().render(g);
		MobManager.getPlayer().getHealthBar().render(g);
		MobManager.getPlayer().getManaBar().render(g);
		MobManager.getPlayer().getNewItemWindow().render(g);
		MobManager.getPlayer().getDialogueWindow().render(g);
		MobManager.getPlayer().getCenteredText().render(g);
		MobManager.getPlayer().getLevelUpText().render(g);
		MobManager.getPlayer().getTradingWindow().render(g);
		MobManager.getPlayer().getQuestLogWindow().render(g);
		MobManager.getPlayer().getReadingWindow().render(g);
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
	
	public static QuestManager getQuestManager() {
		return questManager;
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

	public static QuestLogic getQuestLogic() {
		return questLogic;
	}
	
}
