package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Player {
    public Vector2 position;
    public Vector2 position_bullet;
    public Sprite sprite, sprite_bullet;

    public float speed = 125, speed_bullet = 1500;
    public Player(Texture img, Texture bullet){
        position_bullet = new Vector2(0, 10000);
        sprite_bullet = new Sprite(bullet);
        sprite_bullet.setScale(10);
        sprite = new Sprite(img);
        sprite.setScale(7);
        position = new Vector2(Gdx.graphics.getWidth() /2, sprite.getScaleY() * sprite.getHeight() / 2);
    }
    public void update(float deltaTime){

        if(Gdx.input.getX() <= WTTG.leftButton.getWidth() * WTTG.leftButton.getScaleX() && Gdx.input.justTouched() && Gdx.input.getY() >= WTTG.leftButton.getHeight() * 100){

            position.x -= speed;
        }
        if(Gdx.input.getX() >= WTTG.WIDTH - WTTG.rightButton.getWidth() * WTTG.rightButton.getScaleX() && Gdx.input.justTouched()  && Gdx.input.getY() >= WTTG.rightButton.getHeight() * 100){
            position.x += speed;
        }
        if (position.x <= sprite.getWidth() * sprite.getScaleX() / 2 + WTTG.leftButton.getWidth() * WTTG.leftButton.getScaleX()){
            position.x = sprite.getWidth() * sprite.getScaleX() / 2 + WTTG.leftButton.getWidth() * WTTG.leftButton.getScaleX();
        }
        if (position.x >= WTTG.WIDTH - (sprite.getWidth() * sprite.getScaleX() / 2 + WTTG.rightButton.getWidth() * WTTG.rightButton.getScaleX())){
            position.x = WTTG.WIDTH - (sprite.getWidth() * sprite.getScaleX() / 2 + WTTG.rightButton.getWidth() * WTTG.rightButton.getScaleX());
        }
        if(position_bullet.y > WTTG.HEIGTH) {
            position_bullet.x = position.x;
            position_bullet.y = 150;
        }
        position_bullet.y += deltaTime * speed_bullet;
    }
    public void draw(SpriteBatch batch){
        update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
        sprite_bullet.setPosition(position_bullet.x, position_bullet.y);
        sprite_bullet.draw(batch);
    }
}
