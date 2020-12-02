package com.androidgames.coinman.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class Menu implements Screen {

    Base game;
    Texture play ;
    Texture end ;
    public Menu(Base game){
        this.game = game;
        play = new Texture("play.png");
        end = new Texture("exit.png");
    }

    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_NICEST);
        game.batch.begin();
        game.batch.draw(play,Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/2);
        game.batch.draw(end,Gdx.graphics.getWidth()/3,(Gdx.graphics.getHeight()/2)-150);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
