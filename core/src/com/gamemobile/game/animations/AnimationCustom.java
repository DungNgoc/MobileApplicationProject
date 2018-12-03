package com.gamemobile.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.graphics.g2d.Batch.X1;
import static com.badlogic.gdx.graphics.g2d.Batch.X2;
import static com.badlogic.gdx.graphics.g2d.Batch.X3;
import static com.badlogic.gdx.graphics.g2d.Batch.X4;
import static com.badlogic.gdx.graphics.g2d.Batch.Y1;
import static com.badlogic.gdx.graphics.g2d.Batch.Y2;
import static com.badlogic.gdx.graphics.g2d.Batch.Y3;
import static com.badlogic.gdx.graphics.g2d.Batch.Y4;

/**
 * setting animation for game.
 */
public class AnimationCustom {

    /**
     * Animation component.
     */
    private TextureAtlas atlas;
    private Animation<TextureRegion> animation;
    private float timePassed;

    /**
     * Location.
     */
    private float x;
    private float y;
    private float width;
    private float height;

    static final int VERTEX_SIZE = 2 + 1 + 2;
    static final int SPRITE_SIZE = 4 * VERTEX_SIZE;

    final float[] vertices = new float[SPRITE_SIZE];
    private final Color color = new Color(1, 1, 1, 1);

    private float originX, originY;
    private float rotation;
    private float scaleX = 1, scaleY = 1;
    private boolean dirty = true;
    private Rectangle bounds;
    /**
     * Constructor.
     * @param atlasPath path of atlas.
     * @param fps frame per second.
     * @param x position x.
     * @param y position y.
     * @param width texture width.
     * @param height texture height.
     */
    public AnimationCustom(String atlasPath, float fps, float x, float y, float width, float height){

        //Animation component setting.
        atlas = new TextureAtlas(Gdx.files.internal(atlasPath));
        animation = new Animation(1/(fps*1.0f), atlas.getRegions());

        timePassed = 0;

        //Location setting.
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Get current X.
     * @return current X.
     */
    public float getX() {
        return x;
    }

    /**
     * Change X.
     * @param x new X position.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Get current Y.
     * @return current Y;
     */
    public float getY() {
        return y;
    }

    /**
     * Change Y;
     * @param y new Y position.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Get current Width.
     * @return current Width.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Change Width.
     * @param width new Width.
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Get current height.
     * @return current height.
     */
    public float getHeight() {
        return height;
    }

    /**
     * Change height.
     * @param height new height.
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Change Fps.
     * @param fps new fps.
     */
    public void setFPS(float fps) {
        animation.setFrameDuration(fps);
    }

    public void setSize (float width, float height) {
        this.width = width;
        this.height = height;

        if (dirty) return;

        float x2 = x + width;
        float y2 = y + height;
        float[] vertices = this.vertices;
        vertices[X1] = x;
        vertices[Y1] = y;

        vertices[X2] = x;
        vertices[Y2] = y2;

        vertices[X3] = x2;
        vertices[Y3] = y2;

        vertices[X4] = x2;
        vertices[Y4] = y;

        if (rotation != 0 || scaleX != 1 || scaleY != 1) dirty = true;
    }

    public void setPosition (float x, float y) {
        translate(x - this.x, y - this.y);
    }

    public void setRotation (float degrees) {
        this.rotation = degrees;
        dirty = true;
    }
    public float getRotation () {
        return rotation;
    }
    public void setOrigin (float originX, float originY) {
        this.originX = originX;
        this.originY = originY;
        dirty = true;
    }

    public void translate (float xAmount, float yAmount) {
        x += xAmount;
        y += yAmount;

        if (dirty) return;

        float[] vertices = this.vertices;
        vertices[X1] += xAmount;
        vertices[Y1] += yAmount;

        vertices[X2] += xAmount;
        vertices[Y2] += yAmount;

        vertices[X3] += xAmount;
        vertices[Y3] += yAmount;

        vertices[X4] += xAmount;
        vertices[Y4] += yAmount;
    }
    public void setX1 (float x) {
        translateX(x - this.x);
    }

    /** Sets the y position where the sprite will be drawn. If origin, rotation, or scale are changed, it is slightly more efficient
     * to set the position after those operations. If both position and size are to be changed, it is better to use
     * {@link #setBounds(float, float, float, float)}. */
    public void setY1 (float y) {
        translateY(y - this.y);
    }
    public void translateX (float xAmount) {
        this.x += xAmount;

        if (dirty) return;

        float[] vertices = this.vertices;
        vertices[X1] += xAmount;
        vertices[X2] += xAmount;
        vertices[X3] += xAmount;
        vertices[X4] += xAmount;
    }

    /** Sets the y position relative to the current position where the sprite will be drawn. If origin, rotation, or scale are
     * changed, it is slightly more efficient to translate after those operations. */
    public void translateY (float yAmount) {
        y += yAmount;

        if (dirty) return;

        float[] vertices = this.vertices;
        vertices[Y1] += yAmount;
        vertices[Y2] += yAmount;
        vertices[Y3] += yAmount;
        vertices[Y4] += yAmount;
    }
    /**
     * Get current Fps.
     * @return current Fps.
     */
    public float getFPS() {
        return animation.getFrameDuration();
    }

    public float getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(float timePassed) {
        this.timePassed = timePassed;
    }

    public void set(AnimationCustom animationCustom){
        rotation = animationCustom.rotation;
    }

    /**
     * Display animation.
     * @param batch to draw frame.
     * @param isLooping looping frame or not.
     */
    public void draw(Batch batch, boolean isDoAnimation, boolean isLooping) {
        if(isDoAnimation) {
            timePassed += Gdx.graphics.getDeltaTime();
        }
        if(!isDoAnimation && !isLooping) {
            timePassed = 0;
        }
        batch.draw(animation.getKeyFrame(timePassed, isLooping), x, y, width, height);
    }

    public void drawButton(Batch batch, boolean isTouched){
        timePassed = 0;
        if(isTouched){
            timePassed = 1;
        }
        batch.draw(animation.getKeyFrame(timePassed), x, y, width, height);
    }

    public void drawExplosion(Batch batch, float x, float y, float width, float height){
        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timePassed), x, y, width, height);
    }

    /**
     * Dispose atlas to release memory.
     */
    public void dispose() {
        atlas.dispose();
    }
}
