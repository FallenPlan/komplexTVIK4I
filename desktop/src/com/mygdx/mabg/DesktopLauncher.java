package com.mygdx.mabg;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
//import com.mygdx.mabg.MyJavaGame;
import com.mygdx.mabg.controller.GameController;
import com.mygdx.mabg.controller.GamePhysics;
import com.mygdx.mabg.controller.MyAngryBirds;
import com.mygdx.mabg.controller.Runnable;
import com.mygdx.mabg.view.PlayScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);

		config.setWindowedMode(1280, 720);

//		new Lwjgl3Application(new MyJavaGame(), config);
//		new Lwjgl3Application(new GameController(), config);

//		new Lwjgl3Application(new MyAngryBirds(), config);
		new Lwjgl3Application(new Runnable(), config);

//		new Lwjgl3Application(new PlayScreen(), config);
//		new Lwjgl3Application(new GamePhysics(), config);
	}
}
