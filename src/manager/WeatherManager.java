package manager;

import java.util.ArrayList;
import java.util.Collections;

import org.newdawn.slick.Graphics;
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
	
	public WeatherManager() throws SlickException {

		rain = new Weather("resources/weather/rain.png");
		rain.setActive(true);
		rain.setAmountToSpawn(20);
		rain.setTimeBetweenNewWeatherParticle(500);
		rain.setLightningProbablity(0.002);
		
		weatherList.add(rain);
		
		
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

