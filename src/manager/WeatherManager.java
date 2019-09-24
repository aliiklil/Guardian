package manager;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dialogue.Sentence;
import main.Game;
import models.Character;
import models.Item;
import models.ItemType;
import models.Mob;
import models.Monster;
import models.NPC;
import models.Player;
import models.Weather;
import models.WeatherParticle;

public class WeatherManager {
	
	private ArrayList<Weather> weatherList = new ArrayList<>();
	
	private Weather rain;
	private Weather night;
	
	public WeatherManager() throws SlickException {

		rain = new Weather();
		rain.setWeatherParticlePath("resources/weather/particle/rain.png");
		rain.setAmountToSpawn(20);
		rain.setTimeBetweenNewWeatherParticle(500);
		rain.setLightningProbablity(0.002);
		rain.setFilter(new Image("resources/weather/filter/rain.png"));
		
		night = new Weather();
		night.setFilter(new Image ("resources/weather/filter/night.png"));
		
		
		
		weatherList.add(rain);
		weatherList.add(night);
		
		
		
		rain.setActive(true);
		//night.setActive(true);
	}
	
	public void update() throws SlickException {
		for(Weather w : weatherList) {
			if(w.isActive()) {
				w.update();
			}
		}
	}

	public void render(Graphics g) throws SlickException {
		for(Weather w : weatherList) {
			if(w.isActive()) {
				w.render(g);
			}
		}
	}

}

