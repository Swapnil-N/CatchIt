package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class GloveActor extends Actor{

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("glove.png")));
    double accelx;

    public GloveActor(){

        setY(1000);
        setX((float)(Math.random()*1000));

        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);

        accelx = Gdx.input.getAccelerometerX();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accelx = Gdx.input.getAccelerometerX();

        if (accelx > 0 && accelx <= 3 && sprite.getX() > 10){
            setX(getX()-5);
        }else if (accelx > 3 && sprite.getX() > 10){
            setX(getX()-10);
        }else if (accelx < 0 && accelx >= -3 && sprite.getX() < 900){
            setX(getX()+5);
        }else if (accelx < -3 && sprite.getX() < 900){
            setX(getX()+10);
        }

        sprite.setPosition(getX(),getY());
    }

    public Rectangle getRectangle(){
        return sprite.getBoundingRectangle();
    }

}
