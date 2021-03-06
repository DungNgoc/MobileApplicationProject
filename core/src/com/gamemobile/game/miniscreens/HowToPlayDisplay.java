package com.gamemobile.game.miniscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;
import com.gamemobile.game.Application;
import com.gamemobile.game.actors.ActorButton;
import com.gamemobile.game.sounds.MusicEffect;
import com.gamemobile.game.utils.PlayerInfo;
import com.gamemobile.game.utils.ScreenConstants;
import com.gamemobile.game.utils.SplashDoors;

public class HowToPlayDisplay  extends Actor {

    private Texture background;
    private Texture board;
    private ActorButton btnPlay;
    private MiniScreenState miniScreenState;
    private BoardState boardState;
    private float boardDirectionY;
    private long startTime;
    private MusicEffect soundStartPlay;
    private BitmapFont font;
    private boolean isResume;
    public boolean ischeckbtn;

    public HowToPlayDisplay() {
        background = new Texture("images/backgrounds/minidisplay.png");
        board = new Texture("images/textureobjects/panelhowtoplay.png");
        btnPlay = new ActorButton(128f, 128f, ActorButton.ButtonTag.TARGET_PLAY);
        miniScreenState = MiniScreenState.HIDE;
        boardState = BoardState.MOVE_DOWN;
        boardDirectionY = Application.DESKTOP_HEIGHT;
       startTime = TimeUtils.millis() / 1000;
       // soundStartPlay = new MusicEffect("sounds/startplay.ogg");
      // soundStartPlay.setMusicKind(MusicEffect.MusicKind.ONE_TIME);
     //  soundStartPlay.playMusic();
      //  FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/luximb.ttf"));
      //  FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
      //  parameter.size = 40;
      // font = generator.generateFont(parameter); // font size 12 pixels
     // generator.dispose(); // don't forget to dispose to avoid memory leaks!
     //  font.setColor(Color.BROWN);
        isResume = false;
        ischeckbtn = false;
    }

    public ActorButton getBtnPlay() {
        return btnPlay;
    }

    public MiniScreenState getMiniScreenState() {
        return miniScreenState;
    }

    public void showDisplay(){
       // isResume = false;
        miniScreenState = MiniScreenState.SHOW;
    }
    public void setMiniScreenState(MiniScreenState miniScreenState) {
        this.miniScreenState = miniScreenState;
    }

    public void moveDownBoard() {
        if (boardState.equals(BoardState.MOVE_DOWN)) {
            if (boardDirectionY > Application.DESKTOP_HEIGHT - 390f) {
                boardDirectionY -= 3 * ScreenConstants.TRANSFORM_Y;
            } else {
                btnPlay.setButtonState(ActorButton.ButtonState.ENABLED);
                boardState = BoardState.FREEZE;
            }
        }
    }

    public void moveUpBoard() {
        if (boardState.equals(BoardState.MOVE_UP)) {
            if (boardDirectionY < Application.DESKTOP_HEIGHT) {
                boardDirectionY += 10 * ScreenConstants.TRANSFORM_Y;
            } else {
                miniScreenState = MiniScreenState.HIDE;
                btnPlay.setButtonState(ActorButton.ButtonState.DISABLED);
                boardState = BoardState.FREEZE;
            }
        }
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if(!miniScreenState.equals(MiniScreenState.FINISH)) {
            if (TimeUtils.millis() / 1000 - startTime > 1) {
                SplashDoors.openTheSplashDoor(3f);
            }
            if (miniScreenState.equals(MiniScreenState.FREEZE)) {
                if (TimeUtils.millis() / 1000 - startTime > 2) {
                    miniScreenState = MiniScreenState.SHOW;
                }
            }
            if (miniScreenState.equals(MiniScreenState.SHOW)) {
                btnPlay.updateButtonTouched();
                moveDownBoard();
                moveUpBoard();

                if (btnPlay.isTouched()) {
                    btnPlay.setButtonState(ActorButton.ButtonState.HIDE);
                    boardState = BoardState.MOVE_UP;
                    ischeckbtn = true;
                }
            }
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (miniScreenState.equals(MiniScreenState.FREEZE)) {
            batch.draw(background, 0f, 0f, Application.DESKTOP_WIDTH, Application.DESKTOP_HEIGHT);
            SplashDoors.drawDoor(batch);
        }
        if (miniScreenState.equals(MiniScreenState.SHOW)) {
            batch.draw(background, 0f, 0f, Application.DESKTOP_WIDTH, Application.DESKTOP_HEIGHT);
            batch.draw(board, Application.DESKTOP_WIDTH / 2 - 200f, boardDirectionY, 500f, 390f);
            btnPlay.draw(batch, parentAlpha);
            SplashDoors.drawDoor(batch);
        }
    }

    @Override
    public boolean remove() {
        background.dispose();
        board.dispose();
        btnPlay.remove();
        soundStartPlay.dispose();
        return super.remove();
    }
}


