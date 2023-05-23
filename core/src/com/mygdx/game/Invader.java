package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Invader {
    public Vector2 position;
    public Vector2 position_initial;
    public Sprite sprite;
    public int lives;

    public Invader(Vector2 _position, Texture invader){
        lives = 1;
        position = _position;
        position_initial = position;
        sprite = new Sprite(invader);
        sprite.setScale(3);
    }
    public boolean Alive(){
        if(lives > 0)
            return true;
        else
            return false;
    }
    public void draw(SpriteBatch batch){
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
