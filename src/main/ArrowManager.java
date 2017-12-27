package main;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class ArrowManager {

	private ArrayList<Arrow> arrowList = new ArrayList<Arrow>();
	
	public void update() {
		
		for(Arrow arrow : arrowList) {
			arrow.update();
		}
		
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
		
		arrowList.remove(arrow);
		
	}
	
}
