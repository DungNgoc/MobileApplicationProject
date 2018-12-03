package com.gamemobile.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamemobile.game.animations.AnimationCustom;
import com.gamemobile.game.sounds.SoundEffect;
import com.gamemobile.game.utils.PlayerInfo;
import com.gamemobile.game.utils.ScreenConstants;
import com.gamemobile.game.utils.TextConstants;

import java.util.Random;


public class ActorMouse extends Actor {

    public enum RodTag{
        MOUSERUNNING1,
        MOUSERUNNING2
    }

    public   enum RodState{
        FREEZE,
        CATCHED,
        DISABLED,
        TRASH,
        EXPLODED
    }
    private RodTag tag;
    private float weight;
    private int money;
    private RodState rodState;
    private float moveSpeedRod;
   // private Texture textureRod;
    private SoundEffect rodSound;
    private AnimationCustom rodExplosionAnimation;
    private float[] explosionPos;
    private SoundEffect explosionSound;
    private SoundEffect eatBonusSound;


    private float moveSpeed;
    private float moveRange;
    private AnimationCustom mouse;
    private float[] firstShape;
    private boolean isMoveLeft;
    private boolean isMoveRight;
    public  boolean isCheck;
    private String pathFileLeft;
    private String pathFileRight;
    private float xRightStop, xLeftStop;
    //private float rotation;

    public ActorMouse(String pathFileLeft,String pathFileRight, float x, float y, float width, float height, RodTag tag, float xLeftStop, float xRightStop) {

        this.tag = tag;

        this.rodState = RodState.FREEZE;

        createRodPhysical();

        this.pathFileLeft = pathFileLeft;
        this.pathFileRight = pathFileRight;
        this.xLeftStop =  xLeftStop;
        this.xRightStop = xRightStop;
       // mouse = new AnimationCustom("animations/mouses/mouserunning.atlas", 3,
         //      x, y, width, height);
        mouse = new AnimationCustom(pathFileLeft, 3, x, y, width, height);
        setPosition(x, y);
        mouse.setPosition(x, y);
        setSize(width, height);
        mouse.setSize(width, height);
        setOrigin(getOriginX() + getWidth() / 2, getOriginY() + getHeight() / 2);
        scaleBound();

        isCheck=false;
        moveSpeed = 0;
        moveRange = 0;
        //rodState = mouse.rotation;
        firstShape = new float[4];
        firstShape[0] = x;
        firstShape[1] = y;
        firstShape[2] = width;
        firstShape[3] = height;


        rodExplosionAnimation = new AnimationCustom("animations/explosions/rodexplosion.atlas", 6, x, y, width, height);
        explosionPos = new float[4];
        explosionSound = new SoundEffect("sounds/explode.ogg");

        eatBonusSound = new SoundEffect("sounds/eatbonus.ogg");


    }

    public void setNewFirstShape(float x, float y, float width, float height) {
        firstShape[0] = x;
        firstShape[1] = y;
        firstShape[2] = width;
        firstShape[3] = height;
    }


    public void setMoveRight(float moveSpeed, float moveRange) {// make move right
        this.moveSpeed = moveSpeed * ScreenConstants.TRANSFORM_X;
        this.moveRange = moveRange;// * ScreenConstants.TRANSFORM_X;
        this.isMoveRight = true;
        this.isMoveLeft = false;
        isCheck = true;

    }

    public void setMoveLeft(float moveSpeed, float moveRange) {//makemoveleft
        this.moveSpeed = moveSpeed * ScreenConstants.TRANSFORM_X;
        this.moveRange = moveRange;// * ScreenConstants.TRANSFORM_X;
        this.isMoveRight = false;
        this.isMoveLeft = true;
        isCheck=false;
    }
    public void setNonMoveLefRight(){
        this.isMoveRight = false;
        this.isMoveLeft = false;
    }

    private void updateMoveLeftRight() { // check move left or right
        if (moveSpeed <= 0 || moveRange <= 0) {
            return;

        }
        if (isMoveLeft) {
            if (Math.abs(getX() - firstShape[0]) < moveRange) {
                mouse.setX(mouse.getX() - moveSpeed);
                setX(getX() - moveSpeed);

            }
            if (Math.abs(getX() - firstShape[0]) > moveRange) {
                mouse.setX(firstShape[0] - moveRange);
                setX(firstShape[0] - moveRange);

            }

            if (mouse.getX() <= xLeftStop) {
               isCheck = true;
                mouse = new AnimationCustom(pathFileRight, 3,
                        getX(),  firstShape[1], firstShape[2], firstShape[3]);
                //mouse = new AnimationCustom("animations/mouses/mouse1/mouserunningright/mouserunningright.atlas", 3,
                       // getX() , firstShape[1], firstShape[2], firstShape[3]);

            }

        }
        if (isMoveRight) {
            if (Math.abs(getX() - firstShape[0]) < moveRange) {
                mouse.setX(mouse.getX() + moveSpeed);
                setX(getX() + moveSpeed);
            }
            if (Math.abs(getX() - firstShape[0]) > moveRange) {
                mouse.setX(firstShape[0] + moveRange);
                setX(firstShape[0] + moveRange);
            }
            if (mouse.getX() >= xRightStop) {
               // setMoveLeft(1.5f, 400f);
                isCheck = false;
                mouse = new AnimationCustom(pathFileLeft, 3, getX(), firstShape[1], firstShape[2], firstShape[3]);
               // mouse = new AnimationCustom("animations/mouses/mouse1/mouserunningleft/mouserunningleft.atlas", 3,
                      //  getX(), firstShape[1], firstShape[2], firstShape[3]);
            }
        }
        setPosition(getX(), getY());
        mouse.setPosition(getX(), getY());
        setSize(getWidth(), getHeight());
        mouse.setSize(getWidth(), getHeight());

        setOrigin(getOriginX() + getWidth() / 2, getOriginY() + getHeight() / 2);
        scaleBound();

    }


    public RodState getRodState() {
        return rodState;
    }

    public RodTag getTag() {
        return tag;
    }

    public float getWeight() {
        return weight * ScreenConstants.TRANSFORM_Y;
    }

    public int getMoney() {
        return money;
    }

    public void setMoveSpeed(float moveSpeedRod) {
        this.moveSpeedRod = moveSpeedRod;
    }

  //  public Sprite getSprite() {
     //   return sprite;
    //}

    private void createRodPhysical(){

        if(tag.equals(RodTag.MOUSERUNNING1)){
            money = 2;
            weight = 1.5f *ScreenConstants.TRANSFORM_Y;
          // mouse = new AnimationCustom("animations/mouses/mouserunning.atlas", 3, 150f, 40f, 12f, 24f);
          //  mouse = new ActorMouse(100f, 100f, 30f, 30f, RodTag.MOUSERUNNING);
            //textureRod = new Texture("animations/mouses/mouserunning.png");
            //actorMouse = new ActorMouse(560, 200, 960f, 540f);
            //scaleBound();

            rodSound = new SoundEffect("sounds/rock.ogg");
            return;
        }
        if(tag.equals(RodTag.MOUSERUNNING2)){
            money = 602;
            weight = 1.5f * ScreenConstants.TRANSFORM_Y;
            //mouse = new AnimationCustom("animations/mouses/mouse2/mouserunningleft", 3f, 150f, 40f, 12f, 24f);
            rodSound = new SoundEffect("sounds/bigmoney.ogg");
            return;
        }
    }

    private void moveRod() {
        setX(getX() - moveSpeedRod*(float) Math.cos(Math.toRadians(mouse.getRotation()-90)));
        setY(getY() - moveSpeedRod*(float) Math.sin(Math.toRadians(mouse.getRotation()-90)));
        mouse.setX1(mouse.getX() - moveSpeedRod*(float) Math.cos(Math.toRadians(mouse.getRotation()-90)));
        mouse.setY1(mouse.getY() - moveSpeedRod*(float) Math.sin(Math.toRadians(mouse.getRotation()-90)));

     /*   setX(getX()+0.4f - moveSpeedRod*(float) Math.cos(Math.toRadians(mouse.getRotation()-90-10)));
        setY(getY()+0.4f - moveSpeedRod*(float) Math.sin(Math.toRadians(mouse.getRotation()-90-10)));
        mouse.setX1(mouse.getX()+0.4f - moveSpeedRod*(float) Math.cos(Math.toRadians(mouse.getRotation()-90-10)));
        mouse.setY1(mouse.getY()+0.4f - moveSpeedRod*(float) Math.sin(Math.toRadians(mouse.getRotation()-90-10)));
    */
    }

    public void updateCollisionWithPod(ActorPod pod, ActorText talkingText) {

        eatBonusSound.playSound();
        if(rodState.equals(ActorMouse.RodState.DISABLED)){
            return;
        }
        if(rodState.equals(ActorMouse.RodState.TRASH)){
            rodState = RodState.DISABLED;
            remove();
            return;
        }
        if(checkCatchRod(pod)){
            if(rodState.equals(ActorMouse.RodState.FREEZE) && pod.getPodState().equals(ActorPod.PodState.SHOOT)) {
                fixPosition(pod);
                pod.setMoveSpeed(pod.getMoveSpeed() - weight);
                pod.setPodState(ActorPod.PodState.REWIND);
                setMoveSpeed(pod.getMoveSpeed());
                rodState = ActorMouse.RodState.CATCHED;
                rodSound.setSoundKind(SoundEffect.SoundKind.ONE_TIME);
                rodSound.playSound();
                return;
            }


        }
        if(rodState.equals(ActorMouse.RodState.CATCHED) && pod.getPodState().equals(ActorPod.PodState.ROTATION)){
            eatBonusSound.setSoundKind(SoundEffect.SoundKind.ONE_TIME);
            PlayerInfo.setCurrentMoney(PlayerInfo.getCurrentMoney() + money);

            talkingText.setText("Oh yeah!$" + money + "...");
            talkingText.setTextState(ActorText.TextState.FREEZE);
            TextConstants.setTakingStartTimeShow(TimeUtils.millis()/1000);
            setPosition(0 - getWidth(), 0 - getHeight());
            setRotation(0);
            rodState = ActorMouse.RodState.TRASH;
            return;
        }
    }

    public void updateCollisionWithBomb(ActorPod acPod, ActorBomb acBomb){

        acBomb.updateCollisionWithPod(acPod);
        if(rodState.equals(ActorMouse.RodState.CATCHED) && acBomb.getBombState().equals(ActorBomb.BombState.BURST)){
            rodState = ActorMouse.RodState.EXPLODED;
            explosionSound.setSoundKind(SoundEffect.SoundKind.ONE_TIME);
            explosionSound.playSound();
            acPod.setMoveSpeed(40);
            synchronizeExplosionWithRod(acPod);
            acBomb.setBombState(ActorBomb.BombState.FREEZE);
        }
    }

    //       |O|
    // pod_A------pod_B
    //  | ********* |
    //  | * Catch * |
    //  | ********* |
    // pod_C------pod_D
    //
    // bound < real.
    // scale number must be < 50% (< 0.5f).
    // if scale number > 50% then top,bottom, left, right will be reversed.

    private float scaleNum = 0.25f;
    private float left;
    private float right;
    private float bottom;
    private float top;

    public void scaleBound(){
        left = getX() + getWidth()*scaleNum;
        right = getX() + getWidth()*(1f - scaleNum);
        bottom = getY() + getHeight()*scaleNum;
        top = getY() + getHeight()*(1f - scaleNum);
    }

    private boolean checkCatchRod(ActorPod pod) {
        if(checkCatchPodA(pod) || checkCatchPodB(pod) || checkCatchPodC(pod) || checkCatchPodD(pod)){
            return true;
        }
        return false;
    }

    private boolean checkCatchPodA(ActorPod pod) {
        if (left < pod.getX() && pod.getX() < right
                && bottom < pod.getY() + pod.getHeight() && pod.getY() + pod.getHeight() < top){
            return true;
        }
        return false;
    }

    private boolean checkCatchPodB(ActorPod pod) {
        if (left < pod.getX() + pod.getWidth() && pod.getX() + pod.getWidth() < right
                && bottom < pod.getY() + pod.getHeight() && pod.getY() + pod.getHeight() < top){
            return true;
        }
        return false;
    }

    private boolean checkCatchPodC(ActorPod pod) {
        if (left < pod.getX() && pod.getX() < right
                && bottom < pod.getY() && pod.getY() < top){
            return true;
        }
        return false;
    }

    private boolean checkCatchPodD(ActorPod pod) {
        if (left < pod.getX() + pod.getWidth() && pod.getX() + pod.getWidth() < right
                && bottom < pod.getY() && pod.getY() < top){
            return true;
        }
        return false;
    }


    private void fixPosition(ActorPod pod) {
        float rotTemp = pod.getRotation();
        pod.setRotation(0);
        setRotation(0);
        mouse.setRotation(0);

        setX(pod.getX() + pod.getWidth()/2 - getWidth()/2);
        mouse.setX1(pod.getX() + pod.getWidth()/2 - getWidth()/2-12f);
        setY(pod.getY() - getHeight()*(1f-scaleNum));
        mouse.setY1(pod.getY() - getHeight()*(1f-scaleNum)+7f);

        pod.setRotation(rotTemp);

        setOrigin(getOriginX() + getWidth()/2, getOriginY() + getHeight()*(1f-scaleNum) + pod.getHeight());
        mouse.setOrigin(getOriginX(), getOriginY());

        setRotation(rotTemp);
        mouse.setRotation(rotTemp);

    }

    private void synchronizeExplosionWithRod(ActorPod acPod){

        explosionPos[2] = getWidth();
        explosionPos[3] = getHeight();

        explosionPos[0] = acPod.getX() + acPod.getWidth()/2 - getWidth()/2;
        explosionPos[1] = acPod.getY() + acPod.getHeight() - getHeight()/2;

        float avgTemp = scaleNum*(getWidth() + getHeight());

        explosionPos[0] = explosionPos[0] + avgTemp*(float) Math.cos(Math.toRadians(getRotation()-90));
        explosionPos[1] = explosionPos[1] + avgTemp*(float) Math.sin(Math.toRadians(getRotation()-90));
    }

    @Override
    public boolean remove() {
        rodSound.dispose();
        rodExplosionAnimation.dispose();
//        textureRod.dispose();
        explosionSound.dispose();
        return super.remove();
    }

    @Override
    public void act(float delta) {
     // if(rodState.equals(ActorMouse.RodState.values())!= rodState.equals(RodState.CATCHED))

       if (rodState.equals(ActorMouse.RodState.CATCHED)) {
            moveSpeed =0;
            setNonMoveLefRight();
            moveRod();
        }
        updateMoveLeftRight();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(rodState.equals(ActorMouse.RodState.FREEZE) || rodState.equals(ActorMouse.RodState.CATCHED)) {
            mouse.draw(batch, true, true);
        }
        if(rodState.equals(ActorMouse.RodState.EXPLODED)){
            explosionPos[0] -= 0.5;
            explosionPos[1] -= 0.5;
            explosionPos[2] += 1;
            explosionPos[3] += 1;
            rodExplosionAnimation.drawExplosion(batch, explosionPos[0], explosionPos[1], explosionPos[2], explosionPos[3]);
            if(rodExplosionAnimation.getTimePassed() > 1){
                rodState = ActorMouse.RodState.TRASH;
                setPosition(0 - getWidth(), 0 - getHeight());
                setRotation(0);
            }
        }

    }
}