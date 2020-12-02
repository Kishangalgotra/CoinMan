package com.androidgames.coinman.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;


public class Coin_Man extends ApplicationAdapter implements Lev{

	public Coin_Man() {
	}

	SpriteBatch batch;
	Texture background;
	Texture[] man;
	static int Gamelevel =1;
	Texture dizzy;
	int manstate = 0;
	int pause =0;
	float gravity = 0.2f;
	float velocity = 0;
	int manY =0;
	BitmapFont font;
	ArrayList<Integer> coinX = new ArrayList<>();
	ArrayList<Integer> coinY = new ArrayList<>();
	ArrayList<Integer> bombX = new ArrayList<>();
	ArrayList<Integer> bombY = new ArrayList<>();
	ArrayList<Rectangle> coinRectangl = new ArrayList<>();
	ArrayList<Rectangle> bombract = new ArrayList<>();
	Texture coin;
	Texture gameover;
	Texture bomb;
	int coincount = 0;
	int bombcount = 0;
	Random random;
	Rectangle ract;
	int score=0;
	int gamestate =0;

	//Rectangle bombract;

	//Initialize every instance inside this method
	@Override
	public void create () {
		batch = new SpriteBatch();
		background =  new Texture("bg.png");
		Gamelevel =1;
		if(Gamelevel ==1) {
			man = Lev.Level1(man);
		}
		if(Gamelevel ==2) {

			man = Lev.Level2(man);
		}
		if(Gamelevel ==3) {
			man = Lev.Level3(man);
		}
		if(Gamelevel ==4) {
			man = Lev.Level4(man);
		}
		if(Gamelevel ==5) {
			man = Lev.Level5(man);
		}
		if(Gamelevel ==6) {
			man = Lev.Level6(man);
		}
		if(Gamelevel ==7) {
			man = Lev.Level7(man);
		}
		if(Gamelevel ==8) {
			man = Lev.Level8(man);
		}
		if(Gamelevel ==9) {
			man = Lev.Level9(man);
		}
		if(Gamelevel ==10) {
			man = Lev.Level10(man);
		}
		manY = Gdx.graphics.getHeight() / 5 - man[manstate].getHeight();
		coin  = new Texture("coin.png");
		bomb  = new Texture("bomb.png");
		dizzy = new Texture("dizzy-1.png");
		gameover = new Texture(("gameover.png"));
		random = new Random();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(5);
	}

	public void makecoin(){
		float height = random.nextFloat() * Gdx.graphics.getHeight();
		coinY.add((int)height);
		coinX.add(Gdx.graphics.getWidth());
	}
	public void makebomb(){
		float height = random.nextFloat() * Gdx.graphics.getHeight();
		bombY.add((int)height);
		bombX.add(Gdx.graphics.getWidth());
	}

	@Override
	public void render () {
        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //Game State for START,PAUSE AND GAME OVER
        if(gamestate ==1){
			//COIN
			if(coincount <100){
				coincount++;
			}
			else{
				coincount = 0;
				makecoin();
			}
			coinRectangl.clear();
			for(int i =0;  i<coinX.size(); i++){
				batch.draw(coin,coinX.get(i),coinY.get(i));
				coinX.set(i,coinX.get(i) - 4);
				coinRectangl.add(new Rectangle(coinX.get(i),coinY.get(i),coin.getWidth(),coin.getHeight()));
			}

			//BOMB
			if(bombcount <=300){
				bombcount++;
			}else{
				bombcount=0;
				makebomb();
			}
			bombract.clear();
			for(int i =0;  i<bombX.size(); i++) {
				batch.draw(bomb, bombX.get(i), bombY.get(i));
				bombX.set(i, bombX.get(i) - 8);
				bombract.add(new Rectangle(bombX.get(i), bombY.get(i), bomb.getWidth(), bomb.getHeight()));
			}

				if(Gdx.input.isTouched()){
					velocity = -10;
				}
				if(pause < 8){
					pause++;
				}else {
					pause =0;
					if (manstate < 3) {
						manstate++;
					} else {
						manstate = 0;
					}
				}
				velocity += gravity;
				manY -= velocity;
				if(manY <= 0){
					manY = 0;
				}

		}else if(gamestate==0){
           if(Gdx.input.isTouched()) {
           	gamestate =1;
		   }
		}else if(gamestate==2){
			if(Gdx.input.isTouched()) {
				gamestate =1;
				score =0;
				manY = Gdx.graphics.getHeight() / 5
						- man[manstate].getHeight();
				coinY.clear();
				coinX.clear();
				velocity=0;
				coinRectangl.clear();
				coincount=0;

				//for bomb
				bombY.clear();
				bombX.clear();
				bombract.clear();
				bombcount=0;
			}
		}

        if(gamestate  ==2) {
				batch.draw(dizzy,Gdx.graphics.getWidth() / 5 - man[manstate].getWidth() / 2, manY);
			}else{
				batch.draw(man[manstate], Gdx.graphics.getWidth() / 5 - man[manstate].getWidth() / 2, manY);
			}
			ract = new Rectangle(Gdx.graphics.getWidth() /5 - man[manstate].getWidth()/2,manY , man[manstate].getWidth(), man[manstate].getHeight());

			// FOR COIN COLLISION
			for(int i=0; i<coinRectangl.size(); i++){
				if(Intersector.overlaps(ract,coinRectangl.get(i))){
					score++;

					coinRectangl.remove(i);
					coinX.remove(i);
					coinY.remove(i);
					break;
				}
		}
			if (score==2){
				Gamelevel=2;
			}

		// FOR BOMB COLLISION
		for(int i=0; i<bombract.size(); i++){
			if(Intersector.overlaps(ract,bombract.get(i))){
				batch.draw(gameover,Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/2);
				gamestate = 2;
				break;
			}
		}
       font.draw(batch,String.valueOf(score),100,2100);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
