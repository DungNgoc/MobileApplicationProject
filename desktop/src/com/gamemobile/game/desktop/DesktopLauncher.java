package com.gamemobile.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gamemobile.game.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Gold Miner";
		config.width = Application.DESKTOP_WIDTH;
		config.height = Application.DESKTOP_HEIGHT;
		config.addIcon("images/icons/gameicon.png", Files.FileType.Internal);
		new LwjglApplication(new Application(), config);
	}
}
