package main;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class ArrowManager {

	private ArrayList<Arrow> arrowList = new ArrayList<Arrow>();
	private ArrayList<Arrow> removeList = new ArrayList<Arrow>();
	
	public void update() {
		
		for(Arrow arrow : arrowList) {
			arrow.update();
		}
		
		arrowList.removeAll(removeList);
		
		removeList.clear();
		
	}

	public void render(Graphics g) {
	
		for(Arrow arrow : arrowList) {
			arrow.render(g);
		}
		
	}
	
	public void addArrow(Arrow arrow) {
		
		arrowList.add(arrow);
		
	}
	
	public void removeArrow(Arrow arrow) {
		
		removeList.add(arrow);
		
	}
	
}
