package com.gamemobile.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamemobile.game.Application;
import com.gamemobile.game.actors.ActorButton;
import com.gamemobile.game.actors.ActorHuman;
import com.gamemobile.game.actors.ActorImage;
import com.gamemobile.game.sounds.MusicEffect;
import com.gamemobile.game.sounds.SoundEffect;
import com.gamemobile.game.utils.PlayerInfo;
import com.gamemobile.game.utils.SplashDoors;


public class MainMenuScreen extends AbstractScreen {

    private ActorHuman acHuman;
    private Texture background;
    private ActorButton btnPlayGame;
    private MusicEffect musicMenu;
    private SoundEffect soundCloseDoor;
    private boolean isCloseDoor;
    private long startTime;
    private ActorImage acHumanImage;

    public MainMenuScreen(Application app) {
        super(app);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        SplashDoors.setDoorOpen();
        createCamera();
        createStageGame();
        app.batch.setProjectionMatrix(getCamera().combined);
        app.shapeBatch.setProjectionMatrix(getCamera().combined);
        background = new Texture("images/backgrounds/mainmenu.png");

        soundCloseDoor = new SoundEffect("sounds/closedoor.ogg");
        soundCloseDoor.setSoundKind(SoundEffect.SoundKind.ONE_TIME);
        isCloseDoor = false;


        musicMenu = new MusicEffect("sounds/mainmenu_2.ogg");
        musicMenu.setMusicKind(MusicEffect.MusicKind.DURING);
        musicMenu.playMusicLoopOnAndroid();

        btnPlayGame = new ActorButton(211f, 222f, ActorButton.ButtonTag.MAINMENU_PLAY);
        getStageGame().addActor(btnPlayGame);

        acHumanImage = new ActorImage("images/textureobjects/humanbarrow.png", 170f, 30f, 170.0F, 200.0F);
        getStageGame().addActor(acHumanImage);
        Gdx.input.setInputProcessor(getStageGame());

        PlayerInfo.setCurrentBombNum(3);
        PlayerInfo.setCurrentMoney(0);
        PlayerInfo.setCurrentTarget(0);
        PlayerInfo.setCurrentLevel(1);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        app.batch.begin();
        app.batch.draw(background,0,0,Application.DESKTOP_WIDTH,Application.DESKTOP_HEIGHT);
        app.batch.end();

        getStageGame().act();
        makeHumanRun();
        getStageGame().draw();

        getStageGame().act();
        btnPlayGame.updateButtonTouched();
        if(btnPlayGame.isTouched()){
            updateMenuSound();
            startTime = TimeUtils.millis()/1000;
            isCloseDoor = true;
        }
        getStageGame().draw();

        app.batch.begin();
        if(isCloseDoor) {
            SplashDoors.closeTheSplashDoor(10f);
            SplashDoors.drawDoor(app.batch);
        }
        app.batch.end();

        if(SplashDoors.checkDoorClose()){
            if(TimeUtils.millis()/1000 - startTime >= 1) {
                app.gsm.setScreen(1);
                dispose();
            }
        }
    }

    private void updateMenuSound(){
        musicMenu.pausePlay();
        musicMenu.stopPlay();
        soundCloseDoor.playSound();
    }
    private void makeHumanRun() {
        //acHumanImage.setMoveLeft(0.2F, 400f);
        if(acHumanImage.isCheck == false){

//            acHumanImage = new ActorImage("images/textureobjects/humanbarrow.png", 170f, 30f, 170.0F, 200.0F);
            acHumanImage.setMoveRight(0.2F, 400f);
        }
       else {
        //    acHumanImage = new ActorImage("images/textureobjects/humanbarrowright.png", 170f, 30f, 170.0F, 200.0F);
           acHumanImage.setMoveLeft(0.2F, 400f);
       }// acHumanImage.setRotationWave(0.2F, 4.0F);
        // acHumanImage.setNonRotation();
    }

    @Override
    public void pause() {
        musicMenu.pausePlay();
    }

    @Override
    public void resume() {
        musicMenu.resumePlay();
    }

    @Override
    public void dispose() {
        if(btnPlayGame != null) {
            btnPlayGame.remove();
        }
        acHumanImage.remove();
        background.dispose();
        musicMenu.dispose();
        soundCloseDoor.dispose();
        super.dispose();
    }

    @Override
    public void hide() {

    }
}
