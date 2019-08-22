package manager;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dialogue.Sentence;
import main.Game;
import models.Character;
import models.Item;
import models.ItemType;
import models.NPC;
import models.Player;
import models.Wolf;

public class WolfManager {

	private static ArrayList<Wolf> wolfList = new ArrayList<Wolf>();
	
	private static Wolf wolf1;
	
	private ItemTypeManager itemTypeManager = Game.getItemTypeManager();	
	
	public WolfManager() throws SlickException {
		wolf1 = new Wolf(512, 256, 100, null, 100, 50);		
		wolfList.add(wolf1);
	}
	
	public void update() throws SlickException {
		for(Wolf wolf : wolfList) {
			wolf.update();
		}
	}

	public void render(Graphics g) {
		for(Wolf wolf : wolfList) {
			wolf.render(g);
		}
	}
	
	public void renderUpperLayer(Graphics g) { ////To draw tail above player for example
		for(Wolf wolf : wolfList) {
			wolf.renderUpperLayer(g);
		}
	}
	
	public static ArrayList<Wolf> getWolfList() {
		return wolfList;
	}
		
}
