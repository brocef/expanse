package core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Queue;

import core.ai.Action;
import core.ai.Day;
import core.graphics.Viewport;

public class Expanse {
	public static void main(String[] args) {
		Expanse e = new Expanse(30, 30);
		e.start();
	}
	private Viewport view;
	private int map_width, map_height;
	private Tile[][] map;
	private static char SC = File.separatorChar;
	private static String AI_LOCATION = "file://C:"+SC+"Users"+SC+"Brian"+SC+"Dropbox"+SC+"Workspace"+SC+"Expanse_Testing"+SC+"bin"+SC;

	protected Expanse(int width, int height) {
		map_width = width;
		map_height = height;
		prepareMap();
		try {
			
			Class<?> ai = loadAI(new URL[] {new URL(Expanse.AI_LOCATION)});
			System.out.println("AI Loaded");
			Populace.createPopulace(map[10][10], 10000, ai);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		view = new Viewport(map);
		view.getClass();
	}

	private Class<?> loadAI(URL[] urls) {
		URLClassLoader ucl = new URLClassLoader(urls, this.getClass().getClassLoader());
		System.out.println("Loading AI...");
		Class<?> ai = null;
		try {
			ai = ucl.loadClass("TestAI");
		} catch (ClassNotFoundException e) {
			System.err.println("Failed to load AI. Exiting.");
			//e.printStackTrace();
			System.exit(1);
		}
		try {
			ucl.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ai;
	}

	private void prepareMap() {
		map = new Tile[map_height][map_width];
		for (int j=0; j<map_height; j++) {
			for (int i=0; i<map_width; i++) {
				if ((j%2 == 0 && i%2 == 1) || (j%2 == 1 && i%2 == 0)) {
					map[i][j] = null;
					continue;
				}
				map[i][j] = new Tile(i, j);
			}
		}
	}

	protected void start() {
		for (int i=0; i<10; i++) {
			Action[][] actions = getAllActions();
			processActions(actions);
			advanceDay();
		}
	}
	
	private void advanceDay() {
		for (int i=0; i<map.length; i++) {
			for (int j=0; j<map[i].length; j++) {
				Tile t = map[i][j];
				if (t != null) {
					//Decay/increase resources
					//Increase population
					//Etc
				}
			}
		}
		Day.incrementDate();
	}
	
	private void processActions(Action[][] actions) {
		for (int i=0; i<actions.length; i++) {
			//Do the actions
		}
	}
	
	private Action[][] getAllActions() {
		Action[][] actions = new Action[Populace.master_pop_list.size()][];
		for (int j=0; j<actions.length; j++) {
			Populace p = Populace.master_pop_list.get(j);
			Day d = new Day(p);
			Queue<Action> act = p.nextDay(d);
			actions[j] = (act==null?null:act.toArray(new Action[act.size()]));
		}
		return actions;
	}
}
