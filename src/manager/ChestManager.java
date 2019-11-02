package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Game;
import models.Chest;
import models.Item;

public class ChestManager {

	private static ArrayList<Chest> chestList = new ArrayList<Chest>();
	
	private ItemTypeManager itemTypeManager = Game.getItemTypeManager();	
	
	public ChestManager() throws SlickException {
		
		Item arrow = new Item(0, 0, itemTypeManager.arrow);
		Item ironsword = new Item(0, 0, itemTypeManager.ironsword);
		Item trophy = new Item(0, 0, itemTypeManager.trophy);
		Item apple = new Item(0, 0, itemTypeManager.apple);
		
		Chest chest1 = new Chest(94, 6, arrow, 1);
		Chest chest2 = new Chest(133, 7, ironsword, 1);
		Chest chest3 = new Chest(94, 138, trophy, 1);
		Chest chest4 = new Chest(56, 124, apple, 1);
		
		chestList.add(chest1);
		chestList.add(chest2);
		chestList.add(chest3);
		chestList.add(chest4);
	}
	
	public void update() throws SlickException {
		
		for(Chest chest : chestList)
			chest.update();
		
	}

	public void render(Graphics g) {
			
		for(Chest chest : chestList)
			chest.render(g);
		
	}
	
	public static ArrayList<Chest> getChestList() {
		return chestList;
	}

}

