package models;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Bar {

	private float x;
	private float y;
	private int width;
	private int height;
	
	private int outlineThickness;
	
	private final int maxValue;
	private int currentValue;
	
	private Color color;
	
	public Bar(float x, float y, int width, int height, int outlineThickness, int currentValue, int maxValue, Color color) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.outlineThickness = outlineThickness;

		this.currentValue = currentValue;
		this.maxValue = maxValue;
		
		this.color = color;
		
	}

	public void render(Graphics g) {
				
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		
		g.setColor(color);
		g.fillRect(x + outlineThickness, y + outlineThickness, (float) currentValue/maxValue * (width - outlineThickness * 2), height - outlineThickness * 2);
					
	}
	
	
	public int getMaxValue() {
		return maxValue;
	}
	
	public int getCurrentValue() {
		return currentValue;
	}
	
	public void setCurrentValue(int currentValue) {
		if(currentValue > maxValue) {
			this.currentValue = maxValue;
		} else {
			this.currentValue = currentValue;
		}
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
