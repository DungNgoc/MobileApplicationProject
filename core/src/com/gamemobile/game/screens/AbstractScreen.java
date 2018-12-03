package com.gamemobile.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gamemobile.game.Application;



public abstract class AbstractScreen implements Screen {

    public enum ScreenState{
        PAUSE,
        PLAY,
        FINISH
    }

    protected final Application app;

    private ScreenState screenState;

    private OrthographicCamera camera;
    private Stage stageGame;

    public AbstractScreen(final Application app) {
        this.app = app;
        screenState = ScreenState.PLAY;
    }

    public void createCamera(){
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Application.DESKTOP_WIDTH, Application.DESKTOP_HEIGHT);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Stage getStageGame() {
        return stageGame;
    }

    public void createStageGame(){
        stageGame = new Stage();
        stageGame.setViewport(new ScreenViewport(camera));
    }

    public ScreenState getScreenState() {
        return screenState;
    }

    public void setScreenState(ScreenState screenState) {
        this.screenState = screenState;
    }

    public abstract void update(float delta);

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
        stageGame.dispose();

    }

}
