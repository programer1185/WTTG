package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Black extends Invader{
    public Black(Vector2 _position, Texture invader) {
        super(_position, invader);
        lives = 3;
    }
}
