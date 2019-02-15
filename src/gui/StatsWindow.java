package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.Main;
import manager.CharacterManager;
import models.Player;

public class StatsWindow {

	private Image windowImage = new Image("resources/player_stats.png");
	private Input input = Main.appGameContainer.getInput();
	private Player player;
	private boolean windowOpen = false;
	
	public StatsWindow() throws SlickException {
		
	}
	
	public void update() throws SlickException {
		
		player = CharacterManager.getPlayer();
		
		if(input.isKeyPressed(Input.KEY_C) && !player.getDialogueWindow().isWindowOpen() && !player.getInventoryWindow().isWindowOpen()) {
			if(!windowOpen) {
				windowOpen = true;
			} else {
				windowOpen = false;
			}
		}	
		System.out.println(windowOpen);
		
	}
			
	public void render(Graphics g) {
				
		if(windowOpen) {
			g.drawImage(windowImage, 0, 0);
		}	
						
	}

	public boolean isWindowOpen() {
		return windowOpen;
	}
		
}