package com.gamemobile.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gamemobile.game.Application;
import com.gamemobile.game.actors.ActorButton;
import com.gamemobile.game.actors.ActorHuman;
import com.gamemobile.game.actors.ActorImage;
import com.gamemobile.game.miniscreens.ExitDisplay;
import com.gamemobile.game.miniscreens.HowToPlayDisplay;
import com.gamemobile.game.miniscreens.MiniScreenState;
import com.gamemobile.game.miniscreens.SettingDisplay;
import com.gamemobile.game.sounds.MusicEffect;
import com.gamemobile.game.sounds.SoundEffect;
import com.gamemobile.game.utils.GameMethods;
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
    private Texture panel;
    private ActorButton btnHowToPlay;
    private ActorButton btnExit;
    private HowToPlayDisplay howToPlayDisplay;
    private SettingDisplay settingDisplay;
    private ExitDisplay exitDisplay;
    private Stage stageSetting;
    private boolean ischeck;// check draw display
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
        panel = new Texture("images/textureobjects/panel.png");

        soundCloseDoor = new SoundEffect("sounds/closedoor.ogg");
        soundCloseDoor.setSoundKind(SoundEffect.SoundKind.ONE_TIME);
        isCloseDoor = false;


        musicMenu = new MusicEffect("sounds/mainmenu_2.ogg");
        musicMenu.setMusicKind(MusicEffect.MusicKind.DURING);
        musicMenu.playMusicLoopOnAndroid();

        btnPlayGame = new ActorButton(211f, 222f, ActorButton.ButtonTag.MAINMENU_PLAY);
        getStageGame().addActor(btnPlayGame);

        btnHowToPlay = new ActorButton(199f, 57f, ActorButton.ButtonTag.HOW_TO_PLAY);
        getStageGame().addActor(btnHowToPlay);

        btnExit = new ActorButton(190f, 57f, ActorButton.ButtonTag.EXIT);
        getStageGame().addActor(btnExit);

        acHumanImage = new ActorImage("images/textureobjects/humanbarrow.png", 170f, 30f, 170.0F, 200.0F);
        getStageGame().addActor(acHumanImage);
        Gdx.input.setInputProcessor(getStageGame());


        stageSetting = new Stage();
        stageSetting.setViewport(new ScreenViewport(getCamera()));

        settingDisplay = new SettingDisplay();
        howToPlayDisplay = new HowToPlayDisplay();
        exitDisplay = new ExitDisplay();

        stageSetting.addActor(howToPlayDisplay);
        stageSetting.addActor(settingDisplay);
        stageSetting.addActor(exitDisplay);

        Gdx.input.setInputProcessor(getStageGame());
        Gdx.input.setInputProcessor(stageSetting);

        PlayerInfo.setCurrentBombNum(3);
        PlayerInfo.setCurrentMoney(0);
        PlayerInfo.setCurrentTarget(0);
        PlayerInfo.setCurrentLevel(1);

        ischeck= false;
        setScreenState(ScreenState.PLAY);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        app.batch.begin();
        app.batch.draw(background,0,0,Application.DESKTOP_WIDTH,Application.DESKTOP_HEIGHT);
        //app.batch.draw(panel, 0f,0f, 158f, 158f );
        app.batch.end();
        app.batch.begin();

        app.batch.draw(panel, 730f,355f, 182f, 157f );
        app.batch.end();

        if(getScreenState().equals(ScreenState.PAUSE)){
            btnHowToPlay.updateButtonTouched();
           if(howToPlayDisplay.ischeckbtn == true) {
                setScreenState(ScreenState.PLAY);

            }
            stageSetting.act();
            stageSetting.draw();
        }
        if(getScreenState().equals(ScreenState.PLAY)){
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

            getStageGame().act();
            btnHowToPlay.updateButtonTouched();
            if(btnHowToPlay.isTouched()){
                howToPlayDisplay.showDisplay();
                setScreenState(ScreenState.PAUSE);
            }
            getStageGame().draw();

            getStageGame().act();
            btnExit.updateButtonTouched();;
            if (btnExit.isTouched()){
                exitDisplay.showDisplay();
                setScreenState(ScreenState.PAUSE);
            }
            getStageGame().draw();

        }


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
            acHumanImage.setMoveRight(0.5F, 400f);
        }
       else {
        //    acHumanImage = new ActorImage("images/textureobjects/humanbarrowright.png", 170f, 30f, 170.0F, 200.0F);
           acHumanImage.setMoveLeft(0.5F, 400f);
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
        if(btnHowToPlay != null) {
            btnHowToPlay.remove();
        }
        if(btnExit != null) {
            btnExit.remove();
        }
        acHumanImage.remove();
        background.dispose();
        panel.dispose();
        musicMenu.dispose();
        soundCloseDoor.dispose();
        super.dispose();
    }

    @Override
    public void hide() {

    }
}
