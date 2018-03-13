package com.gastc_main.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gastc_main.game.Gastc;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Gastc";
		config.width = 1000;
		config.height = 680;
		
		new LwjglApplication(new Gastc(), config);
	}
}
