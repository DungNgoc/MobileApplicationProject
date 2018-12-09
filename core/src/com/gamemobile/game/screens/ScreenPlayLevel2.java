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



public class ScreenPlayLevel2 extends AbstractScreen {

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
    private ArrayList<ActorMouse>lstAcMouse;


    public ScreenPlayLevel2(Application app) {
        super(app);
    }

    @Override
    public void update(float delta) {

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
        lstAcMouse = new ArrayList<ActorMouse>();


        acHuman = new ActorHuman();
        shootButton = new ActorButton(128f,128f, ActorButton.ButtonTag.SHOOT);
        boomButton = new ActorButton(128f, 128f, ActorButton.ButtonTag.BOOM);
        settingButton = new ActorButton(100f, 100f, ActorButton.ButtonTag.SETTING);

        lstAcRod.add(new ActorRod(600f, 50f, 100f, 100f, ActorRod.RodTag.GOLD_500));
        lstAcRod.add(new ActorRod(150f, 50f, 100f, 100f, ActorRod.RodTag.GOLD_500));

        lstAcRod.add(new ActorRod(150f, 170f, 70f, 60f, ActorRod.RodTag.ROCK_20));
        lstAcRod.add(new ActorRod(170f, 330f, 70f, 60f, ActorRod.RodTag.ROCK_20));
        lstAcRod.add(new ActorRod(180f, 250f, 60f, 60f, ActorRod.RodTag.QUESTIONBAGTYPE1));
        lstAcRod.add(new ActorRod(270f, 130f, 70f, 60f, ActorRod.RodTag.ROCK_20));
        lstAcRod.add(new ActorRod(350f, 210f, 70f, 60f, ActorRod.RodTag.ROCK_20));

        lstAcRod.add(new ActorRod(220f, 200f, 60f, 60f, ActorRod.RodTag.ROCK_10));


        lstAcRod.add(new ActorRod(500f, 120f, 80f, 80f, ActorRod.RodTag.GOLD_250));
        lstAcRod.add(new ActorRod(650f, 120f, 80f, 80f, ActorRod.RodTag.GOLD_250));
        lstAcRod.add(new ActorRod(600f, 170f, 80f, 80f, ActorRod.RodTag.GOLD_250));
        lstAcRod.add(new ActorRod(600f, 270f, 80f, 80f, ActorRod.RodTag.GOLD_250));

        lstAcRod.add(new ActorRod(90f, 50f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(400f, 190f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(450f, 170f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(400f, 270f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(510f, 260f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(610f, 330f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(570f, 260f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(650f, 260f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(690f, 260f, 60f, 60f, ActorRod.RodTag.GOLD_100));

       GameMethods.createPlayScreenActorText(getStageGame(), lstAcRod, lstAcMouse, lstAcText);

        getStageGame().addActor(acHuman);
        getStageGame().addActor(acHuman.getAcPod());
        getStageGame().addActor(acHuman.getAcBomb());
        for (ActorRod rod: lstAcRod){
            if(rod != null){
                getStageGame().addActor(rod);
            }
        }
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

    @Override
    public void render(float delta) {
        super.render(delta);

        app.batch.begin();
        app.batch.draw(background,0,0,Application.DESKTOP_WIDTH,Application.DESKTOP_HEIGHT);
        SplashDoors.drawDoor(app.batch);
        app.batch.end();

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

            if (!GameMethods.checkTimePlay(lstAcText, startTime)
                    || GameMethods.isCatchedAllRod(lstAcRod)) {
                if(timeOutDisplay.getMiniScreenState().equals(MiniScreenState.HIDE)){
                    timeOutDisplay.showDisplay();
                    pause();
                }
            }

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
