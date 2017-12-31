package models;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class HealthBar {

	private float x;
	private float y;
	private int width;
	private int height;
	
	private int outlineThickness;
	
	private final int maxHealth;
	private int currentHealth;
	
	public HealthBar(float x, float y, int width, int height, int outlineThickness, int maxHealth) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.outlineThickness = outlineThickness;

		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		
	}
		
	public void render(Graphics g) {
				
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.red);
		g.fillRect(x + outlineThickness, y + outlineThickness, (float) currentHealth/maxHealth * (width - outlineThickness * 2), height - outlineThickness * 2);
					
	}
	
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
