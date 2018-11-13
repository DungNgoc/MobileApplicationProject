package com.gamemobile.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gamemobile.game.animations.AnimationCustom;
import com.gamemobile.game.utils.ScreenConstants;


public class ActorMouse extends Actor {

    private Texture texture;
    private float moveSpeed;
    private float moveRange;
    private AnimationCustom mouse;
    private float[] firstShape;
    private boolean isMoveLeft;
    private boolean isMoveRight;
    public  boolean isCheck;

    public ActorMouse(float x, float y, float width, float height) {
        mouse = new AnimationCustom("animations/mouses/mouserunning.atlas", 7,
               x, y, width, height);


        isCheck=false;
        moveSpeed = 0;
        moveRange = 0;

        setPosition(x, y);
        setSize(width, height);
        setOrigin(getOriginX() + getWidth() / 2, getOriginY() + getHeight() / 2);

        firstShape = new float[4];
        firstShape[0] = x;
        firstShape[1] = y;
        firstShape[2] = width;
        firstShape[3] = height;

    }

    public void setNewFirstShape(float x, float y, float width, float height) {
        firstShape[0] = x;
        firstShape[1] = y;
        firstShape[2] = width;
        firstShape[3] = height;
    }


    public void setMoveRight(float moveSpeed, float moveRange) {
        this.moveSpeed = moveSpeed * ScreenConstants.TRANSFORM_X;
        this.moveRange = moveRange;// * ScreenConstants.TRANSFORM_X;
        this.isMoveRight = true;
        this.isMoveLeft = false;
        isCheck = true;

    }

    public void setMoveLeft(float moveSpeed, float moveRange) {
        this.moveSpeed = moveSpeed * ScreenConstants.TRANSFORM_X;
        this.moveRange = moveRange;// * ScreenConstants.TRANSFORM_X;
        this.isMoveRight = false;
        this.isMoveLeft = true;
        isCheck=false;
    }

    public void setNonMoveLefRight() {
        this.isMoveRight = false;
        this.isMoveLeft = false;
    }


    private void updateMoveLeftRight() {
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

            if (mouse.getX() <= 170f) {
               isCheck = true;
                //mouse = new AnimationCustom("animations/mouses/mouserunning.atlas", 7,
                       // x, y, width, height);
                mouse = new AnimationCustom("animations/mouses/mouserunningright/mouserunningright.atlas", 7,getX() , firstShape[1], firstShape[2], firstShape[3]);

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
            if (mouse.getX() >= 700) {
               // setMoveLeft(1.5f, 400f);
                isCheck = false;
                mouse = new AnimationCustom("animations/mouses/mouserunning.atlas", 7,
                        getX(), firstShape[1], firstShape[2], firstShape[3]);
            }
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateMoveLeftRight();

    }


    /**
     * Get current animation.
     *
     * @return current animation.
     */
    public AnimationCustom getHumanAnimation() {
        return mouse;
    }


    public void updateAnimationState() {

    }

    @Override
    public boolean remove() {
        mouse.dispose();

        return super.remove();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        mouse.draw(batch, true, true);
    }
}

