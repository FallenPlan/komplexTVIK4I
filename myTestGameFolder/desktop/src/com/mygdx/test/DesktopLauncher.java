package com.mygdx.test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.test.MyTest2;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		new Lwjgl3Application(new MyTest2(), config);
//		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.width = 720;
//		config.height = 480;
//		config.backgroundFPS = 60;
//		config.foregroundFPS = 60;

	}
}
