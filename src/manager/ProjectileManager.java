package manager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import models.Projectile;

public class ProjectileManager {

	private ArrayList<Projectile> projectileList = new ArrayList<Projectile>();
	private ArrayList<Projectile> removeList = new ArrayList<Projectile>();
	
	public void update() {
		
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
	
	public void addProjectile(Projectile projectile) {
		
		projectileList.add(projectile);
		
	}
	
	public void removeProjectile(Projectile projectile) {
		
		removeList.add(projectile);
		
	}
	
}
