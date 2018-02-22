package models;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Game;
import util.CollisionBox;

public class NPC extends Character {
		
	private float screenRelativeX;
	private float screenRelativeY;
	
	public NPC(float relativeToMapX, float relativeToMapY, int currentHealth, int maxHealth, String spriteSheetPath) throws SlickException {
		
		super(relativeToMapX, relativeToMapY, spriteSheetPath);
		
		super.setCharacterCollisionBox(new CollisionBox(super.getRelativeToMapX(),  super.getRelativeToMapY(), super.getSpriteSize()/2, super.getSpriteSize()/2));
		
		super.setHealthBar(new HealthBar(Game.getCurrentMap().getX() + relativeToMapX - 16, Game.getCurrentMap().getY() + relativeToMapY - 32, 64, 5, 1, currentHealth, maxHealth));
		
		this.screenRelativeX = Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getSpriteSize() / 4;
		this.screenRelativeY = Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getSpriteSize() / 2;
					
	}
	
	public void update() {
				
		screenRelativeX = (int) Game.getCurrentMap().getX() + super.getRelativeToMapX() - super.getSpriteSize() / 4;		
		screenRelativeY = (int) Game.getCurrentMap().getY() + super.getRelativeToMapY()  - super.getSpriteSize() / 2;
		
		super.getHealthBar().setX(screenRelativeX);
		super.getHealthBar().setY(screenRelativeY);
																			
	}

	public void render(Graphics g) {
		
		super.getCurrentAnimation().draw(screenRelativeX, screenRelativeY);

		if(super.getHealthBar().getCurrentHealth() > 0) {
			super.getHealthBar().render(g);
		}
		
		if(super.isDrawBlood()) {
			super.drawBlood(screenRelativeX, screenRelativeY);
		}
				
	}
					
}
