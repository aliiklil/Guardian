package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.Chest;

public class ChestManager {

	private static ArrayList<Chest> chestList = new ArrayList<Chest>();
		
	private static Chest chest1;
	private static Chest chest2;
	private static Chest chest3;
	
	public ChestManager() throws SlickException {
		
		chest1 = new Chest(5, 2, null);
		chest2 = new Chest(3, 5, null);
		chest3 = new Chest(7, 8, null);
		
		chestList.add(chest1);
		chestList.add(chest2);
		chestList.add(chest3);
		
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

