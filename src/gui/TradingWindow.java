package gui;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Main;
import manager.CharacterManager;
import models.Player;

public class TradingWindow {

	private boolean windowOpen = false;
	private Image inventoryImage = new Image("resources/trading_window.png");
	private Image selectedCellImage = new Image("resources/inventory_selected_cell.png");
	private Image equippedItemImage = new Image("resources/inventory_equipped_item.png");
	private int selectedCellX = 0;
	private int selectedCellY = 0;
	
	private final int amountRows = 6;
	private final int amountColumns = 5;
	private final int amountCells = 30;
	
	private int scrollOffset = 0;
	
	private int goldCounter = 0;
	
	private Input input = Main.appGameContainer.getInput();

	private long timestamp = 0;

	private boolean holdUpKey = false;
	private boolean holdDownKey = false;
	private boolean holdLeftKey = false;
	private boolean holdRightKey = false;
		
	private SpriteSheet arrowUpSpriteSheet = new SpriteSheet("resources/arrowUpImage.png", 44, 44);
	private SpriteSheet arrowDownSpriteSheet = new SpriteSheet("resources/arrowDownImage.png", 44, 44);
	
	private Animation arrowUpAnimation = new Animation(arrowUpSpriteSheet, 0, 0, 1, 0, true, 750, true);
	private Animation arrowDownAnimation = new Animation(arrowDownSpriteSheet, 0, 0, 1, 0, true, 750, true);
	
	private Player player;
	
	public TradingWindow() throws SlickException {
		
	}
	
	public void update() throws SlickException {
		
		player = CharacterManager.getPlayer();
		
		if(input.isKeyPressed(Input.KEY_TAB) && !player.getDialogueWindow().isWindowOpen()) {
			if(!windowOpen) {
				windowOpen = true;
			} else {
				windowOpen = false;
			}
		}	
		
		if(windowOpen) {
			
			
	
			}
			
			if(input.isKeyPressed(Input.KEY_UP) || holdUpKey && System.currentTimeMillis() - timestamp > 100) {
				
				if(scrollOffset > 0 && selectedCellY == 0) {
					scrollOffset--;
					timestamp = System.currentTimeMillis();
				} 

				if(selectedCellY > 0) {
					selectedCellY--;
					timestamp = System.currentTimeMillis();
				}
				
			}
						


	}				
	
		
	
	public void render(Graphics g) {
				
		if(windowOpen) {			
			g.drawImage(inventoryImage, 0, 0);
			g.drawImage(selectedCellImage, 1484 + selectedCellX * 78, 305 + selectedCellY * 78);
			
			g.setColor(Color.white);
			
			g.drawString("Gold: ", 1733, 239);
			g.drawString(String.valueOf(goldCounter), 1853 - Integer.toString(goldCounter).length() * 9, 239);
			
			int row = 0;
			int column = 0;

		}
							
	}
		
	public boolean isWindowOpen() {
		return windowOpen;
	}
	
}