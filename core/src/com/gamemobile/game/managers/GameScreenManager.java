package com.gamemobile.game.managers;

import com.gamemobile.game.Application;
import com.gamemobile.game.screens.AbstractScreen;
import com.gamemobile.game.screens.FinishScreen;
import com.gamemobile.game.screens.LoseScreen;
import com.gamemobile.game.screens.MainMenuScreen;
import com.gamemobile.game.screens.ScreenPlayLevel1;
import com.gamemobile.game.screens.ScreenPlayLevel10;
import com.gamemobile.game.screens.ScreenPlayLevel2;
import com.gamemobile.game.screens.ScreenPlayLevel3;
import com.gamemobile.game.screens.ScreenPlayLevel4;
import com.gamemobile.game.screens.ScreenPlayLevel5;
import com.gamemobile.game.screens.ScreenPlayLevel6;
import com.gamemobile.game.screens.ScreenPlayLevel7;
import com.gamemobile.game.screens.ScreenPlayLevel8;
import com.gamemobile.game.screens.ScreenPlayLevel9;
import com.gamemobile.game.screens.ShopScreen;
import com.gamemobile.game.screens.WinScreen;
import com.gamemobile.game.utils.ScreenConstants;



public class GameScreenManager {
    public final Application app;

    private AbstractScreen gameScreen;

    public GameScreenManager(final Application app) {
        this.app = app;

        setScreen(ScreenConstants.MAINMENU_SCREEN);

    }

    public AbstractScreen getNextScreen(int level) {
        gameScreen = null;
        if (level == ScreenConstants.WIN_SCREEN) {
           gameScreen = new WinScreen(this.app);
      }
        if (level == ScreenConstants.LOSE_SCREEN) {
            gameScreen = new LoseScreen(this.app);
        }
        if (level == ScreenConstants.FINISH_SCREEN) {
            gameScreen = new FinishScreen(this.app);
        }
        if (level == ScreenConstants.SHOP_SCREEN) {
            gameScreen = new ShopScreen(this.app);
        }
        if (level == ScreenConstants.MAINMENU_SCREEN) {
            gameScreen = new MainMenuScreen(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[0]) {
            gameScreen = new ScreenPlayLevel1(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[1]) {
            gameScreen = new ScreenPlayLevel2(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[2]) {
            gameScreen = new ScreenPlayLevel3(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[3]) {
            gameScreen = new ScreenPlayLevel4(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[4]) {
            gameScreen = new ScreenPlayLevel5(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[5]) {
            gameScreen = new ScreenPlayLevel6(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[6]) {
            gameScreen = new ScreenPlayLevel7(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[7]) {
            gameScreen = new ScreenPlayLevel8(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[8]) {
            gameScreen = new ScreenPlayLevel9(this.app);
        }
        if (level == ScreenConstants.PLAY_LEVEL_SCREEN[9]) {
            gameScreen = new ScreenPlayLevel10(this.app);
        }


        return gameScreen;
    }

    public void setScreen(int level) {
        app.setScreen(getNextScreen(level));
    }

    public void dispose() {

    }
}
