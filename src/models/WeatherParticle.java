package models;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Game;
import main.Main;

public class WeatherParticle {

	private float relativeToMapX;
	private float relativeToMapY;
	
	private float relativeToScreenX;
	private float relativeToScreenY;
			
	private Animation animation;
	
	private final int velocity;
		
	private int travelledDistance = 0;
	private final int travelledDistanceRemove = Main.TILE_SIZE * 33;
	
	private final int width;
	private final int height;
		
	//If particle is near the end and its ending animation should be drawn
	private boolean disappearing = false;
	
	//If particle is gone and can't be seen anymore
	private boolean disappeared = false;
	
	public WeatherParticle(float x, float y, String path, int velocity) throws SlickException {
	
		animation = new Animation(new SpriteSheet(path, 64, 64), 0, 0, 5, 0, true, 50, true);
		
		animation.stop();
		animation.setLooping(false);
		
		width = animation.getWidth();
		height = animation.getHeight();
						
		relativeToMapX = x - width/2;
		relativeToMapY = y - height/2;
				
		relativeToScreenX = Game.getCurrentMap().getX() + relativeToMapX;
		relativeToScreenY = Game.getCurrentMap().getY() + relativeToMapY;
			
		this.velocity = velocity;
				
	}

	public void update() throws SlickException {

		if(!disappearing) {
			relativeToMapX = relativeToMapX - velocity/2;
			relativeToMapY = relativeToMapY + velocity;
			travelledDistance = travelledDistance + velocity;
		}
		
		relativeToScreenX = (int) Game.getCurrentMap().getX() + relativeToMapX;		
		relativeToScreenY = (int) Game.getCurrentMap().getY() + relativeToMapY;
		

		
		if(travelledDistance > travelledDistanceRemove - 256 && !disappearing) {
	
			animation.start();
			disappearing = true;
			
		}
		
		if(disappearing && animation.isStopped()) {
			disappeared = true;
		}
	
	}
		
	public void render(Graphics g) {
		animation.draw(relativeToScreenX, relativeToScreenY);
	}

	public boolean isDisappearing() {
		return disappearing;
	}

	public void setDisappearing(boolean disappearing) {
		this.disappearing = disappearing;
	}

	public boolean isDisappeared() {
		return disappeared;
	}

	public void setDisappeared(boolean disappeared) {
		this.disappeared = disappeared;
	}
	
}