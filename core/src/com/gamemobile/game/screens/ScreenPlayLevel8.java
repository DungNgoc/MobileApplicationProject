package com.gamemobile.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gamemobile.game.Application;
import com.gamemobile.game.actors.ActorButton;
import com.gamemobile.game.actors.ActorHuman;
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



public class ScreenPlayLevel8 extends  AbstractScreen {
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

    public ScreenPlayLevel8(Application app) {
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


        acHuman = new ActorHuman();
        shootButton = new ActorButton(128f,128f, ActorButton.ButtonTag.SHOOT);
        boomButton = new ActorButton(128f, 128f, ActorButton.ButtonTag.BOOM);
        settingButton = new ActorButton(100f, 100f, ActorButton.ButtonTag.SETTING);

        this.lstAcRod.add(new ActorRod(370.0F, 80.0F, 40.0F, 30.0F, ActorRod.RodTag.DINAMOND_650));
        this.lstAcRod.add(new ActorRod(600.0F, 80.0F, 40.0F, 30.0F, ActorRod.RodTag.DINAMOND_650));
        this.lstAcRod.add(new ActorRod(705.0F, 340.0F, 40.0F, 30.0F, ActorRod.RodTag.DINAMOND_650));
        this.lstAcRod.add(new ActorRod(125.0F, 70.0F, 40.0F, 30.0F, ActorRod.RodTag.DINAMOND_650));
        this.lstAcRod.add(new ActorRod(400.0F, 100.0F, 80.0F, 80.0F, ActorRod.RodTag.GOLD_500));
        this.lstAcRod.add(new ActorRod(550.0F, 100.0F, 80.0F, 80.0F, ActorRod.RodTag.GOLD_500));
        this.lstAcRod.add(new ActorRod(405.0F, 2500.0F, 60.0F, 50.0F, ActorRod.RodTag.ROCK_20));
        this.lstAcRod.add(new ActorRod(650.0F, 260.0F, 60.0F, 50.0F, ActorRod.RodTag.ROCK_20));
        this.lstAcRod.add(new ActorRod(120.0F, 200.0F, 60.0F, 50.0F, ActorRod.RodTag.ROCK_20));
        this.lstAcRod.add(new ActorRod(400.0F, 200.0F, 60.0F, 50.0F, ActorRod.RodTag.ROCK_20));
        this.lstAcRod.add(new ActorRod(650.0F, 300.0F, 40.0F, 40.0F, ActorRod.RodTag.ROCK_10));
        this.lstAcRod.add(new ActorRod(320.0F, 275.0F, 40.0F, 40.0F, ActorRod.RodTag.ROCK_10));
        this.lstAcRod.add(new ActorRod(335.0F, 165.0F, 40.0F, 40.0F, ActorRod.RodTag.ROCK_10));
        this.lstAcRod.add(new ActorRod(685.0F, 350.0F, 40.0F, 40.0F, ActorRod.RodTag.ROCK_10));
        this.lstAcRod.add(new ActorRod(715.0F, 355.0F, 40.0F, 40.0F, ActorRod.RodTag.ROCK_10));
        this.lstAcRod.add(new ActorRod(400.0F, 300.0F, 60.0F, 60.0F, ActorRod.RodTag.GOLD_250));
        this.lstAcRod.add(new ActorRod(480.0F, 150.0F, 60.0F, 60.0F, ActorRod.RodTag.GOLD_250));
        this.lstAcRod.add(new ActorRod(700.0F, 300.0F, 40.0F, 40.0F, ActorRod.RodTag.GOLD_100));
        this.lstAcRod.add(new ActorRod(750.0F, 330.0F, 40.0F, 40.0F, ActorRod.RodTag.GOLD_100));
        this.lstAcRod.add(new ActorRod(480.0F, 340.0F, 40.0F, 40.0F, ActorRod.RodTag.GOLD_100));
        this.lstAcRod.add(new ActorRod(480.0F, 260.0F, 40.0F, 40.0F, ActorRod.RodTag.GOLD_100));
        this.lstAcRod.add(new ActorRod(500.0F, 275.0F, 40.0F, 40.0F, ActorRod.RodTag.GOLD_100));
        this.lstAcRod.add(new ActorRod(150.0F, 290.0F, 40.0F, 40.0F, ActorRod.RodTag.GOLD_100));
        this.lstAcRod.add(new ActorRod(590.0F, 200.0F, 60.0F, 60.0F, ActorRod.RodTag.GOLD_250));
        this.lstAcRod.add(new ActorRod(650.0F, 300.0F, 60.0F, 60.0F, ActorRod.RodTag.GOLD_250));
        this.lstAcRod.add(new ActorRod(715.0F, 200.0F, 60.0F, 60.0F, ActorRod.RodTag.GOLD_250));
        //*GameMethods.createPlayScreenActorText(getStageGame(), lstAcRod, lstAcText);

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

        setScreenState(AbstractScreen.ScreenState.PAUSE);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        app.batch.begin();
        app.batch.draw(background,0,0,Application.DESKTOP_WIDTH,Application.DESKTOP_HEIGHT);
        SplashDoors.drawDoor(app.batch);
        app.batch.end();

        if(getScreenState().equals(AbstractScreen.ScreenState.PAUSE)){
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



        if(getScreenState().equals(AbstractScreen.ScreenState.PLAY)) {

            getStageGame().act();

            if (!GameMethods.checkTimePlay(lstAcText, startTime)
                    || GameMethods.isCatchedAllRod(lstAcRod)) {
                if(timeOutDisplay.getMiniScreenState().equals(MiniScreenState.HIDE)){
                    timeOutDisplay.showDisplay();
                    pause();
                }
            }

            GameMethods.updateBombNumber(lstAcText);

           // GameMethods.updateRodCollisionEvent(acHuman.getAcPod(), acHuman.getAcBomb(), lstAcRod, lstAcText, boomButton);

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
        setScreenState(AbstractScreen.ScreenState.PAUSE);
        if(targetDisplay.getMiniScreenState().equals(MiniScreenState.FINISH)
                && timeOutDisplay.getMiniScreenState().equals(MiniScreenState.HIDE)){
            settingDisplay.showDisplay();
        }
        GameMethods.pauseTimerSound(lstAcText);
    }

    private void resumeCustom(){
        acHuman.getAcPod().unmuteAllSound();
        setScreenState(AbstractScreen.ScreenState.PLAY);
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
