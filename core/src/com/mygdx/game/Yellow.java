package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Yellow extends Invader{
    public Yellow(Vector2 _position, Texture invader) {
        super(_position, invader);
        lives = 2;
    }
}
