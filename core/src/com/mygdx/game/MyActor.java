package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import static com.mygdx.game.MyGdxGame.score;

public class MyActor extends Actor{

    private float timeForMove=0;
    private float timeForHit;

    private TextureAtlas textureAtlas;
    private Sprite sprite = new Sprite(new Texture((Gdx.files.internal("BallImgs/baseball.png"))));
    private Animation<TextureRegion> animation;

    private TextureAtlas collideAtlas;

    private Animation<TextureRegion> collisionAni;

    private float x;
    private boolean ifMove;
    private boolean hit = false;


    public MyActor(){
        x = (int) (Math.random()*900);

        textureAtlas = new TextureAtlas(Gdx.files.internal("Spritesheets/mySprites.atlas"));
        animation = new Animation<TextureRegion>(1/5f,textureAtlas.getRegions());

        setY(Gdx.graphics.getHeight()-100);

        setBounds(x, getY(),sprite.getWidth(),sprite.getHeight());
        sprite.setPosition(x, getY());

        collideAtlas = new TextureAtlas(Gdx.files.internal("Spritesheets/collisionSprite.atlas"));
        collisionAni = new Animation<TextureRegion>(1/5f, collideAtlas.getRegions());

        setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(!hit ) {
            timeForMove += Gdx.graphics.getDeltaTime();
            batch.draw(animation.getKeyFrame(timeForMove, true), sprite.getX(), sprite.getY());
        }
        else{
            timeForHit += Gdx.graphics.getDeltaTime();
            batch.draw(collisionAni.getKeyFrame(timeForHit, true), sprite.getX(), sprite.getY());
            if(collisionAni.isAnimationFinished(timeForHit)){
                addAction(Actions.removeActor());
            }
        }

    }

    public void act(float delta) {
        super.act(delta);

        if(!ifMove) {
            moveSequence();
            ifMove = true;
        }

        if (getStage() != null){
            for (Actor a: getStage().getActors()){
                if (a instanceof GloveActor){
                    if (sprite.getBoundingRectangle().overlaps(((GloveActor)a).getRectangle()) && hit  == false){
                        hit  = true;
                        score++;
                    }
                }
            }
        }
        sprite.setPosition(getX(), getY());
    }

    private void moveSequence() {
        SequenceAction sequenceAction = new SequenceAction();
        MoveToAction move = new MoveToAction();
        move.setPosition(x, 0);
        move.setDuration(5f);
        sequenceAction.addAction(move);
        sequenceAction.addAction(Actions.removeActor());
        addAction(sequenceAction);
    }

}
