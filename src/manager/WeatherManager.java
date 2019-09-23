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

public class WeatherManager {

	private Weather rain = new Weather("resources/weather/rain.png");
	
	public WeatherManager() throws SlickException {

	}
	
	public void update() throws SlickException {
		rain.update();
	}

	public void render(Graphics g) throws SlickException {
	
		rain.render(g);
		
	}
	

	
}

