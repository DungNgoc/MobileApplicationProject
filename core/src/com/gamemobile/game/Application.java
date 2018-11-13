package com.gamemobile.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gamemobile.game.managers.GameScreenManager;
import com.gamemobile.game.utils.ShopConstants;
import com.gamemobile.game.utils.SplashDoors;

public class Application extends Game {
	public static int ANDROID_NUM = 60;
	public static int DESKTOP_WIDTH = ANDROID_NUM*16;
	public static int DESKTOP_HEIGHT = ANDROID_NUM*9;

	public AssetManager asset;
	public SpriteBatch batch;
	public ShapeRenderer shapeBatch;
	public GameScreenManager gsm;

	@Override
	public void create () {
		asset = new AssetManager();
		shapeBatch = new ShapeRenderer();
		batch = new SpriteBatch();
		gsm = new GameScreenManager(this);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeBatch.dispose();
		SplashDoors.dispose();
		ShopConstants.disposeAllItem();
		asset.dispose();
		gsm.dispose();
	}
}
