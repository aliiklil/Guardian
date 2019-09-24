package models;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Game;
import manager.MobManager;

public class Weather {
	
	//Image which should be drawn over the game (for example dark blue for night or grey for rain)
	private Image filter;
	
	//If this weather is currently active
	private boolean active;
	
	//Time between new rain or snow or other particle spawns
	private long timeBetweenNewWeatherParticle;
	private long timeLastParticleSpawned;
	
	private ArrayList<WeatherParticle> particleList = new ArrayList<>();
	
	private String weatherParticlePath;
	
	//How many particles should spawn each time
	private int amountToSpawn;
		
	//From 0 to 1 how probable it is that lightning occurs when timeBetweenLightning is over
	private double lightningProbablity;
	
	//True when there currently is a lightning
	private boolean lightningOccuring;
	
	private long timeLastLightningOccured;
	
	private Image lightning = new Image("resources/weather/effect/lightning.png");

	public Weather() throws SlickException {

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
		
		updateLightning();
		
	}
	
	public void render(Graphics g) {

		filter.draw(0, 0);
		
		for(WeatherParticle wp : particleList) {
			wp.render(g);
		}
		
		renderLightning(g);
	}

	private void updateLightning() {
		if(!lightningOccuring && lightningProbablity > new Random().nextFloat()) {
			lightningOccuring = true;
			timeLastLightningOccured = System.currentTimeMillis();
		} 
				
		if(lightningOccuring && System.currentTimeMillis() - timeLastLightningOccured > 300) {
			lightningOccuring = false;
		}
	}
	
	private void renderLightning(Graphics g) {
		if(lightningOccuring) {
			long timeDiffernece = System.currentTimeMillis() - timeLastLightningOccured;
			if(timeDiffernece < 100 || (timeDiffernece > 200 && timeDiffernece < 300)) {
				lightning.draw(0, 0);
			}
		}	
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getTimeBetweenNewWeatherParticle() {
		return timeBetweenNewWeatherParticle;
	}

	public void setTimeBetweenNewWeatherParticle(long timeBetweenNewWeatherParticle) {
		this.timeBetweenNewWeatherParticle = timeBetweenNewWeatherParticle;
	}

	public int getAmountToSpawn() {
		return amountToSpawn;
	}

	public void setAmountToSpawn(int amountToSpawn) {
		this.amountToSpawn = amountToSpawn;
	}

	public double getLightningProbablity() {
		return lightningProbablity;
	}

	public void setLightningProbablity(double lightningProbablity) {
		this.lightningProbablity = lightningProbablity;
	}

	public Image getFilter() {
		return filter;
	}

	public void setFilter(Image filter) {
		this.filter = filter;
	}

	public String getWeatherParticlePath() {
		return weatherParticlePath;
	}

	public void setWeatherParticlePath(String weatherParticlePath) {
		this.weatherParticlePath = weatherParticlePath;
	}
	
}
