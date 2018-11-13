package com.gamemobile.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gamemobile.game.Application;
import com.gamemobile.game.actors.ActorButton;
import com.gamemobile.game.actors.ActorHuman;
import com.gamemobile.game.actors.ActorMouse;
import com.gamemobile.game.actors.ActorRod;
import com.gamemobile.game.actors.ActorText;
import com.gamemobile.game.miniscreens.MiniScreenState;
import com.gamemobile.game.miniscreens.SettingDisplay;
import com.gamemobile.game.miniscreens.TargetDisplay;
import com.gamemobile.game.miniscreens.TimeOutDisplay;
import com.gamemobile.game.utils.GameMethods;
import com.gamemobile.game.utils.PlayerInfo;
import com.gamemobile.game.utils.ScreenConstants;
import com.gamemobile.game.utils.SplashDoors;
import com.gamemobile.game.utils.TextConstants;

import java.util.ArrayList;
import java.util.HashMap;



public class ScreenPlayLevel1 extends AbstractScreen{

    private ActorHuman acHuman;
    private ArrayList<ActorRod> lstAcRod;
    private ActorButton shootButton;
    private ActorButton boomButton;
    private Texture background;
    private long startTime;
    private long pauseTempTime[];
    private HashMap<ActorText.TextTag,ActorText> lstAcText;
    private Stage stageSetting;
    private TargetDisplay targetDisplay;
    private SettingDisplay settingDisplay;
    private ActorButton settingButton;
    private TimeOutDisplay timeOutDisplay;
    private ActorMouse actorMouse;

    public ScreenPlayLevel1(Application app) {
        super(app);
    }

    @Override
    public void update(float delta) {
        //   makeHumanRun();
        //makeHumanRun();
        //if( actorMouse.moveRange == 0)
        // actorMouse.setMoveRight(0.5f, Application.DESKTOP_WIDTH);
    }

    @Override
    public void show() {
        this.createCamera();
        createStageGame();
        //stage.setDebugAll(true);

        app.batch.setProjectionMatrix(getCamera().combined);
        app.shapeBatch.setProjectionMatrix(getCamera().combined);
        background = new Texture("images/backgrounds/map_day.jpg");

        startTime = TimeUtils.millis()/1000;
        pauseTempTime = new long[2];

        lstAcRod = new ArrayList<ActorRod>();
        lstAcText = new HashMap<ActorText.TextTag, ActorText>();


        acHuman = new ActorHuman();
        actorMouse = new ActorMouse(560f, 200f, 50f, 50f);
        //makeHumanRun();
        shootButton = new ActorButton(128f,128f, ActorButton.ButtonTag.SHOOT);
        boomButton = new ActorButton(128f, 128f, ActorButton.ButtonTag.BOOM);
        settingButton = new ActorButton(100f, 100f, ActorButton.ButtonTag.SETTING);

        lstAcRod.add(new ActorRod(700f, 200f, 60f, 50f, ActorRod.RodTag.ROCK_20));
        lstAcRod.add(new ActorRod(500f, 200f, 60f, 50f, ActorRod.RodTag.ROCK_20));
        lstAcRod.add(new ActorRod(400f, 150f, 60f, 50f, ActorRod.RodTag.ROCK_20));
        lstAcRod.add(new ActorRod(300f, 50f, 60f, 50f, ActorRod.RodTag.ROCK_20));
        lstAcRod.add(new ActorRod(650f, 90f, 60f, 50f, ActorRod.RodTag.ROCK_20));
        lstAcRod.add(new ActorRod(100f, 120f, 60f, 50f, ActorRod.RodTag.ROCK_20));
        lstAcRod.add(new ActorRod(750f, 100f, 40f, 40f, ActorRod.RodTag.ROCK_10));

        lstAcRod.add(new ActorRod(200f, 100f, 80f, 80f, ActorRod.RodTag.GOLD_500));
        lstAcRod.add(new ActorRod(90f, 50f, 40f, 40f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(600f, 100f, 60f, 60f, ActorRod.RodTag.GOLD_250));

        lstAcRod.add(new ActorRod(700f, 300f, 40f, 30f,ActorRod.RodTag.DINAMOND_650));
        lstAcRod.add(new ActorRod(10f, 300f, 60f, 60f, ActorRod.RodTag.GOLD_250));
        lstAcRod.add(new ActorRod(10f, 200f, 80f, 80f, ActorRod.RodTag.GOLD_500));

        //lstAcRod.add(new ActorRod(650f, 200f, 50f, 50f, ActorRod.RodTag.QUESTIONBAGTYPE1));
        //lstAcRod.add(new ActorRod( 600f, 200f, 80f, 80f, ActorRod.RodTag.MOUSERUNNING));

        GameMethods.createPlayScreenActorText(getStageGame(), lstAcRod, lstAcText);

        getStageGame().addActor(acHuman);
        getStageGame().addActor(acHuman.getAcPod());
        getStageGame().addActor(acHuman.getAcBomb());
        for (ActorRod rod: lstAcRod){
            if(rod != null){
                getStageGame().addActor(rod);
            }
        }

        getStageGame().addActor(actorMouse);
        //  getStageGame().addActor(actorMouse.getAcBomb());
        //getStageGame().addActor(actorMouse.getAcPod());


        getStageGame().addActor(shootButton);
        getStageGame().addActor(boomButton);
        getStageGame().addActor(settingButton);
        // stage.addActor(textTime);


        stageSetting = new Stage();
        stageSetting.setViewport(new ScreenViewport(getCamera()));
        targetDisplay = new TargetDisplay();

        settingDisplay = new SettingDisplay();

        timeOutDisplay = new TimeOutDisplay();

        stageSetting.addActor(targetDisplay);
        stageSetting.addActor(settingDisplay);
        stageSetting.addActor(timeOutDisplay);

        Gdx.input.setInputProcessor(getStageGame());
        Gdx.input.setInputProcessor(stageSetting);

        setScreenState(ScreenState.PAUSE);
    }
    private void makeHumanRun() {
        //if(TimeUtils.millis() - startTime < 9) {

        actorMouse.setMoveLeft(0.5F, (float) 400f);
        //  actorMouse.setMoveRight(0.5f, 400f);
        //if( actorMouse.getX() == 960)
        //   actorMouse.setMoveRight(0.5f, Application.DESKTOP_WIDTH);

        //}
        //if(TimeUtils.millis() - startTime >= 5 && TimeUtils.millis() - startTime <= 9.9){
        //  actorMouse.setMoveRight(0.5f, Application.DESKTOP_WIDTH);
        // }

    }
    private void makeHumanRun1(){
        // if((TimeUtils.millis()/1000) - startTime < 100) {
        if(actorMouse.isCheck== false)
            actorMouse.setMoveLeft(0.5f, 400);
        else actorMouse.setMoveRight(0.5f, 400);
        //  }
        //  else actorMouse.setMoveRight(1.5f, 400);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        app.batch.begin();
        app.batch.draw(background,0,0,Application.DESKTOP_WIDTH,Application.DESKTOP_HEIGHT);
        SplashDoors.drawDoor(app.batch);
        app.batch.end();


        // makeHumanRun1();
        if(getScreenState().equals(ScreenState.PAUSE)){
            pauseTempTime[1] = TimeUtils.millis()/1000;
            if(pauseTempTime[1] - pauseTempTime[0] >=1){
                pauseTempTime[0] = pauseTempTime[1];
                startTime += 1;
                TextConstants.setTakingStartTimeShow(TextConstants.getTakingStartTimeShow() + 1);
            }
            //targetDisplay
            if(targetDisplay.getMiniScreenState().equals(MiniScreenState.HIDE)){
                resumeCustom();
                targetDisplay.setMiniScreenState(MiniScreenState.FINISH);
            }
            //settingDisplay
            settingButton.updateButtonTouched();
            if(settingDisplay.isResume()){
                resumeCustom();
                settingDisplay.setResume(false);
            }
            if(settingDisplay.isBackMainMenu()){
                if(SplashDoors.checkDoorClose()){
                    app.gsm.setScreen(ScreenConstants.MAINMENU_SCREEN);
                    dispose();
                }
            }

            if(timeOutDisplay.getMiniScreenState().equals(MiniScreenState.FINISH)){
                GameMethods.goWinOrLoseScreen(this, app.gsm);
            }
            stageSetting.act();
            stageSetting.draw();
        }



        if(getScreenState().equals(ScreenState.PLAY)) {

            getStageGame().act();
            makeHumanRun1();
            getStageGame().draw();
            if (!GameMethods.checkTimePlay(lstAcText, startTime)
                    || GameMethods.isCatchedAllRod(lstAcRod)) {
                if(timeOutDisplay.getMiniScreenState().equals(MiniScreenState.HIDE)){
                    timeOutDisplay.showDisplay();
                    pause();
                }
            }
            //makeHumanRun1();
            GameMethods.updateBombNumber(lstAcText);

            GameMethods.updateRodCollisionEvent(acHuman.getAcPod(), acHuman.getAcBomb(), lstAcRod, lstAcText, boomButton);

            GameMethods.updatePodToShoot(acHuman.getAcPod(), shootButton, boomButton);

            GameMethods.updateSoundTargetSuccess(lstAcText);

            settingButton.updateButtonTouched();
            if(settingButton.isTouched()){
                settingDisplay.showDisplay();
                pause();
            }

            acHuman.updateAnimationState();
            getStageGame().draw();
        }
    }

    @Override
    public void pause() {
        System.out.println(TimeUtils.millis()/1000);
        pauseTempTime[0] = TimeUtils.millis()/1000;
        acHuman.getAcPod().muteAllSound();
        setScreenState(ScreenState.PAUSE);
        if(targetDisplay.getMiniScreenState().equals(MiniScreenState.FINISH)
                && timeOutDisplay.getMiniScreenState().equals(MiniScreenState.HIDE)){
            settingDisplay.showDisplay();
        }
        GameMethods.pauseTimerSound(lstAcText);
    }

    private void resumeCustom(){
        acHuman.getAcPod().unmuteAllSound();
        setScreenState(ScreenState.PLAY);
        GameMethods.resumeTimerSound(lstAcText);
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        PlayerInfo.getBag().resetBag();
        background.dispose();
        if(acHuman != null) {
            acHuman.remove();
        }
        settingButton.remove();
        settingDisplay.remove();
        timeOutDisplay.remove();
        targetDisplay.remove();
        boomButton.remove();
        shootButton.remove();
        GameMethods.disposeAllText(lstAcText);
        GameMethods.disposeAllRod(lstAcRod);
        stageSetting.dispose();
        super.dispose();
    }
}
