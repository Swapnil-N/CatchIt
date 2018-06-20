package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyGdxGame extends ApplicationAdapter{

	Stage stage;

	SpriteBatch spriteBatch;
	BitmapFont font;

	public static int score;

	@Override
	public void create () {
	    
        stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(5f, 5f);

		MyActor actor = new MyActor();
		stage.addActor(actor);

		GloveActor gloveActor = new GloveActor();
		stage.addActor(gloveActor);

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        font.draw(spriteBatch, "SCORE: " + score, 50, 1900);
        spriteBatch.end();

		if(stage.getActors().size < 8){
			MyActor actor = new MyActor();
			stage.addActor(actor);
		}

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose () {
		stage.dispose();
		spriteBatch.dispose();
	}

}

