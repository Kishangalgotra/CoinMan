package com.androidgames.coinman.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Base extends Game {

   public SpriteBatch batch;
    @Override
    public void create() {
         batch = new SpriteBatch();
        this.setScreen( new Menu(this));
    }
}
