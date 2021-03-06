package com.gamemobile.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamemobile.game.animations.AnimationCustom;
import com.gamemobile.game.sounds.SoundEffect;
import com.gamemobile.game.utils.PlayerInfo;
import com.gamemobile.game.utils.ScreenConstants;
import com.gamemobile.game.utils.ShopConstants;
import com.gamemobile.game.utils.TextConstants;

import java.util.ArrayList;
import java.util.Random;


public class ActorRod extends Actor {

    public enum RodTag{
        GOLD_500, GOLD_250, GOLD_100, GOLD_50,
        ROCK_20, ROCK_10,
        DINAMOND_650,
        QUESTIONBAGTYPE1,
        TNTBOX,
        TNTBOX_BREAK,
        DOGBONE_5,
        SKULL_3
      //  MOUSERUNNING

    }

    public enum RodState{
        FREEZE,
        CATCHED,
        DISABLED,
        TRASH,
        EXPLODED
    }

    private RodTag tag;
    private float weight;
    private int money;
    private Sprite sprite;
    private RodState rodState;
    private float moveSpeed;
    private Texture textureRod;
    private SoundEffect rodSound;
    private AnimationCustom rodExplosionAnimation;
    private float[] explosionPos;
    private SoundEffect explosionSound;
    private SoundEffect eatBonusSound;
    public boolean ischeckboom;// check collision between tnt bound and object bound
    public float pos;

    public ActorRod(float x, float y, float width, float height, RodTag tag){

        this.tag = tag;

        this.rodState = RodState.FREEZE;

        createRodPhysical();

        setPosition(x, y);

        this.sprite = new Sprite(textureRod);


        sprite.setPosition(x,y);
        setSize(width, height);
        sprite.setSize(getWidth(), getHeight());

        scaleBound();
       // setScaleNumBoom();
        ischeckboom = false;
       // mouse = new AnimationCustom("animations/mouses/mouserunning.atlas", 7,
          //      x, y, width, height);

        rodExplosionAnimation = new AnimationCustom("animations/explosions/rodexplosion.atlas", 6, x, y, width, height);
        explosionPos = new float[4];
        explosionSound = new SoundEffect("sounds/explode.ogg");

        eatBonusSound = new SoundEffect("sounds/eatbonus.ogg");
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

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public Sprite getSprite() {
        return sprite;
    }

    private void createRodPhysical(){
        if(tag.equals(RodTag.GOLD_500)){
            money = 500;
            weight = 2.7f * ScreenConstants.TRANSFORM_Y;
            textureRod = new Texture("images/textureobjects/gold500.png");

            rodSound = new SoundEffect("sounds/bigmoney.ogg");
            return;
        }
        if(tag.equals(RodTag.GOLD_250)){
            money = 250;
            weight = 2.5f * ScreenConstants.TRANSFORM_Y;
            textureRod = new Texture("images/textureobjects/gold250.png");
            rodSound = new SoundEffect("sounds/bigmoney.ogg");
            return;
        }
        if(tag.equals(RodTag.GOLD_100)){
            money = 100;
            weight = 1.5f * ScreenConstants.TRANSFORM_Y;
            textureRod = new Texture("images/textureobjects/gold100.png");
            rodSound = new SoundEffect("sounds/gold.ogg");
            return;
        }
        if(tag.equals(RodTag.GOLD_50)){
            money = 50;
            weight = 1f * ScreenConstants.TRANSFORM_Y;
            textureRod = new Texture("images/textureobjects/gold50.png");
            rodSound = new SoundEffect("sounds/gold.ogg");
            return;
        }
        if(tag.equals(RodTag.DINAMOND_650)){
            money = 600;
            weight = 1f * ScreenConstants.TRANSFORM_Y;
            textureRod = new Texture("images/textureobjects/dinamond.png");
            rodSound = new SoundEffect("sounds/bigmoney.ogg");
            return;
        }
        if(tag.equals(RodTag.ROCK_20)){
            money = 20;
            weight = 2.7f * ScreenConstants.TRANSFORM_Y;
            textureRod = new Texture("images/textureobjects/rock20.png");
            rodSound = new SoundEffect("sounds/rock.ogg");
            return;
        }
        if(tag.equals(RodTag.ROCK_10)){
            money = 10;
            weight = 1.5f * ScreenConstants.TRANSFORM_Y;
            textureRod = new Texture("images/textureobjects/rock10.png");
            rodSound = new SoundEffect("sounds/rock.ogg");
            return;
        }
        if(tag.equals(RodTag.DOGBONE_5)){
            money = 5;
            weight = 1.7f *ScreenConstants.TRANSFORM_Y;
            textureRod = new Texture(("images/textureobjects/bonedog.png"));
            rodSound = new SoundEffect("sounds/rock.ogg");

        }
        if(tag.equals(RodTag.SKULL_3)){
            money = 3;
            weight = 1.5f *ScreenConstants.TRANSFORM_Y;
            textureRod = new Texture(("images/textureobjects/skull.png"));
            rodSound = new SoundEffect("sounds/rock.ogg");

        }
        if(tag.equals(RodTag.QUESTIONBAGTYPE1)){

            Random rand = new Random();
            this.money = rand.nextInt(500);
            this.weight = 1.5F * ScreenConstants.TRANSFORM_Y;
            this.textureRod = new Texture("images/textureobjects/questionbag.png");
            if (this.money < 50) {
                this.rodSound = new SoundEffect("sounds/rock.ogg");
            } else if(this.money >=350 && this.money < 400){
                this.money = 0;
                this.rodSound = new SoundEffect("sounds/bigmoney.ogg");
            } else
                this.rodSound = new SoundEffect("sounds/bigmoney.ogg");
           // this.money = 0;

        }
        if(tag.equals(RodTag.TNTBOX)){
            money = 1;
            this.weight = 1f *ScreenConstants.TRANSFORM_Y;
            this.textureRod = new Texture(("images/textureobjects/tntbox.png"));
            this.rodSound = new SoundEffect("sounds/explode.ogg");
            setPosition(getX(), getY());
            setScaleNumBoom();
        }
        if(tag.equals(RodTag.TNTBOX_BREAK)){
            money = 1;
            this.weight = 1f *ScreenConstants.TRANSFORM_Y;
            this.textureRod = new Texture(("images/textureobjects/boxbreak.png"));
            this.rodSound = new SoundEffect("sounds/explode.ogg");

            //setScaleNumBoom();
          //  setScaleNumBoom();
        }
    }

    private void moveRod() {
        setX(getX() - moveSpeed*(float) Math.cos(Math.toRadians(sprite.getRotation()-90)));
        setY(getY() - moveSpeed*(float) Math.sin(Math.toRadians(sprite.getRotation()-90)));
        sprite.setX(sprite.getX() - moveSpeed*(float) Math.cos(Math.toRadians(sprite.getRotation()-90)));
        sprite.setY(sprite.getY() - moveSpeed*(float) Math.sin(Math.toRadians(sprite.getRotation()-90)));
    }

    public void updateCollisionWithPod(ActorPod pod, ActorText talkingText) {

        eatBonusSound.playSound();
        if(rodState.equals(RodState.DISABLED)){
            return;
        }
        if(rodState.equals(RodState.TRASH)){
            rodState = RodState.DISABLED;
            remove();
            return;
        }
        if(checkCatchRod(pod)){
            if(rodState.equals(RodState.FREEZE) && pod.getPodState().equals(ActorPod.PodState.SHOOT)) {
                fixPosition(pod);
                pod.setMoveSpeed(pod.getMoveSpeed() - weight);
                pod.setPodState(ActorPod.PodState.REWIND);
                setMoveSpeed(pod.getMoveSpeed());
                if(this.tag.equals(RodTag.TNTBOX)){
                    rodState = RodState.EXPLODED;
                    explosionSound.setSoundKind(SoundEffect.SoundKind.ONE_TIME);
                    explosionSound.playSound();
                    //talkingText.setText("YOU GOT A BOOM");
                    pod.setMoveSpeed(30);

                    synchronizeExplosionWithRod(pod);

                    //return;
                   // ischeckboom = true;
                   // pos = this.getX();
                    if(checkCatchRodBoom()){
                        // return;
                        talkingText.setText("Oh yeah!$" + money + "...");
                        talkingText.setTextState(ActorText.TextState.FREEZE);
                        TextConstants.setTakingStartTimeShow(TimeUtils.millis()/1000);
                    }

                }
                else{
                rodState = RodState.CATCHED;
                rodSound.setSoundKind(SoundEffect.SoundKind.ONE_TIME);
                rodSound.playSound();
                return;}
            }




        }
        if(rodState.equals(RodState.CATCHED) && pod.getPodState().equals(ActorPod.PodState.ROTATION)){
            eatBonusSound.setSoundKind(SoundEffect.SoundKind.ONE_TIME);
            PlayerInfo.setCurrentMoney(PlayerInfo.getCurrentMoney() + money);


          //  if  (this.money==0){
            //    PlayerInfo.getBag().setSodaPower(ShopConstants.SODA_POWER);
           //     talkingText.setText("YOU GOT POWERFUL");
            if(this.money == 0) {

                    talkingText.setText("YOU GOT A BOOM");
                    PlayerInfo.setCurrentBombNum(PlayerInfo.getCurrentBombNum() + 1);

            }else
                talkingText.setText("Oh yeah!$" + money + "...");
            talkingText.setTextState(ActorText.TextState.FREEZE);
            TextConstants.setTakingStartTimeShow(TimeUtils.millis()/1000);
            setPosition(0 - getWidth(), 0 - getHeight());
            setRotation(0);
            rodState = RodState.TRASH;
            return;
        }
    }
   // private float scaleNumBoom = 2;
    public void updateCollisionWithBomb(ActorPod acPod, ActorBomb acBomb){

        acBomb.updateCollisionWithPod(acPod);
        if(rodState.equals(RodState.CATCHED) && acBomb.getBombState().equals(ActorBomb.BombState.BURST)){
            rodState = RodState.EXPLODED;
            explosionSound.setSoundKind(SoundEffect.SoundKind.ONE_TIME);
            explosionSound.playSound();
            acPod.setMoveSpeed(40);
            synchronizeExplosionWithRod(acPod);
            acBomb.setBombState(ActorBomb.BombState.FREEZE);
        }
    }

  //  public void checkCollisionTntBox(){

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
    private float left,l,t,r,b;
    private float right;
    private float bottom;
    private float top;

    public void scaleBound(){
        left = getX() + getWidth()*scaleNum;
        right = getX() + getWidth()*(1f - scaleNum);
        bottom = getY() + getHeight()*scaleNum;
        top = getY() + getHeight()*(1f - scaleNum);
    }

    public  void setScaleNumBoom(){
        l = getX() - getWidth();

        r= getX() + getWidth()*2;
        b = getY() - getHeight()*2;
        t = getY() + getHeight();
    }
    public boolean checkCatchRodBoom() {
        if(checkCatchPodA1() || checkCatchPodB1() || checkCatchPodC1() || checkCatchPodD1()){
            return true;
        }
        return false;
    }
    private boolean checkCatchPodA1() {
        if (left < l && l < right
                && bottom < b && b < top){
            return true;
        }
        return false;
    }
    private boolean checkCatchPodB1() {
        if (left < r && r < right
                && bottom < b && b < top){
            return true;
        }
        return false;
    }

    private boolean checkCatchPodC1() {
        if (left < l && l < right
                && bottom < t && t < top){
            return true;
        }
        return false;
    }

    private boolean checkCatchPodD1() {
        if (left < r && r < right
                && bottom < t && t < top){
            return true;
        }
        return false;
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
        sprite.setRotation(0);

        setX(pod.getX() + pod.getWidth()/2 - getWidth()/2);
        sprite.setX(pod.getX() + pod.getWidth()/2 - getWidth()/2);
        setY(pod.getY() - getHeight()*(1f-scaleNum));
        sprite.setY(pod.getY() - getHeight()*(1f-scaleNum));

        pod.setRotation(rotTemp);

        setOrigin(getOriginX() + getWidth()/2, getOriginY() + getHeight()*(1f-scaleNum) + pod.getHeight());
        sprite.setOrigin(getOriginX(), getOriginY());

        setRotation(rotTemp);
        sprite.setRotation(rotTemp);
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
        textureRod.dispose();
        explosionSound.dispose();
        return super.remove();
    }

    @Override
    public void act(float delta) {
        if (rodState.equals(RodState.CATCHED)) {
           moveRod();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(rodState.equals(RodState.FREEZE) || rodState.equals(RodState.CATCHED)) {
            sprite.draw(batch);
        }
        if(rodState.equals(RodState.EXPLODED)){
            explosionPos[0] -= 0.5;
            explosionPos[1] -= 0.5;
            explosionPos[2] += 1;
            explosionPos[3] += 1;
            rodExplosionAnimation.drawExplosion(batch, explosionPos[0], explosionPos[1], explosionPos[2], explosionPos[3]);
            if(rodExplosionAnimation.getTimePassed() > 1){
                rodState = RodState.TRASH;
                setPosition(0 - getWidth(), 0 - getHeight());
                setRotation(0);
            }
        }

    }
}
