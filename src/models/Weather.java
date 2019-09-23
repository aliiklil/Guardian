package models;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Game;
import manager.MobManager;

public class Weather {
	
	//If this weather is currently active
	private boolean active;
	
	//Time between new rain or snow or other particle spawns
	private long timeBetweenNewWeatherParticle = 500;
	private long timeLastParticleSpawned;
	
	private ArrayList<WeatherParticle> particleList = new ArrayList<>();
	
	private String weatherParticlePath;
	
	//How many particles should spawn each time
	private int amountToSpawn = 20;

	public Weather(String weatherParticlePath) throws SlickException {
		this.weatherParticlePath = weatherParticlePath;
	}
	
	public void update() throws SlickException {
				
		if(System.currentTimeMillis() - timeLastParticleSpawned > timeBetweenNewWeatherParticle) {
			for(int i = 0; i < amountToSpawn; i++) {
				WeatherParticle weatherParticle = new WeatherParticle(MobManager.getPlayer().getRelativeToMapX() + new Random().nextInt(2000) - 1000, -new Random().nextInt(2000) + 1000 - Game.getCurrentMap().getY(), weatherParticlePath, 10);
				particleList.add(weatherParticle);
			}
		}
		
		ArrayList<WeatherParticle> particlesToRemove = new ArrayList<>();
		
		for(WeatherParticle wp : particleList) {
			if(wp.isDisappeared()) {
				particlesToRemove.add(wp);
			}
		}
		
		particleList.removeAll(particlesToRemove);

		for(WeatherParticle wp : particleList) {
			wp.update();
		}
		
	}
	
	public void render(Graphics g) {
		for(WeatherParticle wp : particleList) {
			wp.render(g);
		}
	}
	
}
