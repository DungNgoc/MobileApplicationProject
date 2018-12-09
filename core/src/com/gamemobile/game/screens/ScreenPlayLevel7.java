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



public class ScreenPlayLevel7 extends AbstractScreen{

    private ActorHuman acHuman;
    private ArrayList<ActorRod> lstAcRod;
    private ArrayList<ActorMouse>lstAcMouse;
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
    // private float rotation;

    //   private ActorMouse actorMouse;// actor mouse

    public ScreenPlayLevel7(Application app) {
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
        lstAcMouse = new ArrayList<ActorMouse>();
        lstAcText = new HashMap<ActorText.TextTag, ActorText>();


        acHuman = new ActorHuman();

        shootButton = new ActorButton(128f,128f, ActorButton.ButtonTag.SHOOT);
        boomButton = new ActorButton(128f, 128f, ActorButton.ButtonTag.BOOM);
        settingButton = new ActorButton(100f, 100f, ActorButton.ButtonTag.SETTING);

        lstAcRod.add(new ActorRod(580f, 240f, 60f, 60f, ActorRod.RodTag.TNTBOX));
        lstAcRod.add(new ActorRod(380f, 240f, 60f, 60f, ActorRod.RodTag.TNTBOX));

        lstAcRod.add(new ActorRod(10f, 5f, 120f, 120f, ActorRod.RodTag.GOLD_500));
        lstAcRod.add(new ActorRod(480f, -40f, 120f, 120f, ActorRod.RodTag.GOLD_500));
        lstAcRod.add(new ActorRod(550f, 80f, 120f, 120f, ActorRod.RodTag.GOLD_500));

        lstAcRod.add(new ActorRod(10f, 150f, 60f, 60f, ActorRod.RodTag.GOLD_50));
        lstAcRod.add(new ActorRod(510f, 120f, 60f, 60f, ActorRod.RodTag.GOLD_50));

        lstAcRod.add(new ActorRod(70f, 300f, 40f,40f, ActorRod.RodTag.DOGBONE_5));
        lstAcRod.add(new ActorRod(450f, 230f, 40f,40f, ActorRod.RodTag.DOGBONE_5));

        lstAcRod.add(new ActorRod(90f, 90f, 50f, 50f, ActorRod.RodTag.SKULL_3));
        lstAcRod.add(new ActorRod(800f, 300f, 50f, 50f, ActorRod.RodTag.SKULL_3));
        // lstAcRod.add(new ActorRod(290f, 130f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        //lstAcRod.add(new ActorRod(330f, 20f, 60f, 60f, ActorRod.RodTag.GOLD_100));
        lstAcRod.add(new ActorRod(320f, 250f, 60f, 60f, ActorRod.RodTag.QUESTIONBAGTYPE1));

        lstAcRod.add(new ActorRod(350f, 80f, 90f, 90f, ActorRod.RodTag.GOLD_250));
        lstAcRod.add(new ActorRod(850f, 300f, 90f, 90f, ActorRod.RodTag.GOLD_250));

        lstAcMouse.add(new ActorMouse("animations/mouses/mouse1/mouserunningleft.atlas","animations/mouses/mouse1/mouserunningright.atlas",
                60f, 280f, 45f, 45f, ActorMouse.RodTag.MOUSERUNNING1, 490f, 790f));
        lstAcMouse.add(new ActorMouse("animations/mouses/mouse1/mouserunningleft.atlas","animations/mouses/mouse1/mouserunningright.atlas",
                120f, 300f, 45f, 45f, ActorMouse.RodTag.MOUSERUNNING1, 50f, 700f));
        lstAcMouse.add(new ActorMouse("animations/mouses/mouse1/mouserunningleft.atlas","animations/mouses/mouse1/mouserunningright.atlas",
                460f, 320f, 45f, 45f, ActorMouse.RodTag.MOUSERUNNING1, 270f, 600f));
        lstAcMouse.add(new ActorMouse("animations/mouses/mouse1/mouserunningleft.atlas","animations/mouses/mouse1/mouserunningright.atlas",
                400f, 180f, 45f, 45f, ActorMouse.RodTag.MOUSERUNNING1, 300f, 500f));
        lstAcMouse.add(new ActorMouse("animations/mouses/mouse1/mouserunningleft.atlas","animations/mouses/mouse1/mouserunningright.atlas",
                680f, 180f, 45f, 45f, ActorMouse.RodTag.MOUSERUNNING1, 480f, 700f));
        lstAcMouse.add(new ActorMouse("animations/mouses/mouse1/mouserunningleft.atlas","animations/mouses/mouse1/mouserunningright.atlas",
                500f, 60f, 45f, 45f, ActorMouse.RodTag.MOUSERUNNING1, 300f, 700f));



        GameMethods.createPlayScreenActorText(getStageGame(), lstAcRod, lstAcMouse, lstAcText);

        getStageGame().addActor(acHuman);
        getStageGame().addActor(acHuman.getAcPod());
        getStageGame().addActor(acHuman.getAcBomb());
        for (ActorRod rod: lstAcRod){
            if(rod != null){
                getStageGame().addActor(rod);
            }
        }

        for(ActorMouse actor :lstAcMouse){
            if(actor != null)
                getStageGame().addActor(actor);
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

    private void makeMouseRun(){


        for(ActorMouse actor :lstAcMouse){
            if(actor != null)
                if(actor.isCheck==false){
                    actor.setMoveLeft(0.65f, 900);
                }
                else actor.setMoveRight(0.65f, 900);
        }

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
            makeMouseRun();// let mouse move
            getStageGame().draw();
            if (!GameMethods.checkTimePlay(lstAcText, startTime)
                    || GameMethods.isCatchedAllRod(lstAcRod) ){//|| GameMethods.isCatchedAllRod1(lstAcMouse)) {
                if(timeOutDisplay.getMiniScreenState().equals(MiniScreenState.HIDE)){
                    timeOutDisplay.showDisplay();
                    pause();
                }
            }

            //makeHumanRun1();
            GameMethods.updateBombNumber(lstAcText);

            GameMethods.updateRodCollisionEvent(acHuman.getAcPod(), acHuman.getAcBomb(), lstAcRod, lstAcText, boomButton);

            GameMethods.updateRodCollisionEvent1(acHuman.getAcPod(), acHuman.getAcBomb(), lstAcMouse, lstAcText, boomButton);

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
        GameMethods.disposeAllRod1(lstAcMouse);
        stageSetting.dispose();
        super.dispose();
    }
}
