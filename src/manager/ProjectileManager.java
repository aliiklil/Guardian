package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import models.Projectile;

public class ProjectileManager {

	private ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
	private ArrayList<Projectile> removeList = new ArrayList<Projectile>();
	
	public void update() throws SlickException {
		
		for(Projectile projectile : projectileList) {
			projectile.update();
		}
		
		projectileList.removeAll(removeList);
		
		removeList.clear();
		
	}

	public void render(Graphics g) {
	
		for(Projectile projectile : projectileList) {
			projectile.render(g);
		}
		
	}
	
	public void renderUpperLayer(Graphics g) { //Firerain needs to be rendered over the upper layer, so the projectiles are not drawn behind trees for example
		
		for(Projectile projectile : projectileList) {
			if(projectile.isFirerainProjectile()) {
				projectile.render(g);
			}
		}
		
	}
	
	public void addProjectile(Projectile projectile) {
		
		projectileList.add(projectile);
		
	}
	
	public void removeProjectile(Projectile projectile) {
		
		removeList.add(projectile);
		
	}
	
}
