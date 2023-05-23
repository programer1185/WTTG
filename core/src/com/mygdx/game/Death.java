package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Death {
    Texture logo, text1, text2;
    Sprite logoSp, text1Sp, text2Sp;
    boolean reStarted = false;
    public Death(){
        logo = new Texture("EngGameLogo.png");
        text1 = new Texture("Text1.png");
        text2 = new Texture("Text4.png");
        logoSp = new Sprite(logo);
        logoSp.setScale(5);
        text1Sp = new Sprite(text1);
        text1Sp.setScale(2);
        text2Sp = new Sprite(text2);
        text2Sp.setScale(2);
    }
    public boolean update(Batch batch){
        logoSp.setPosition(Gdx.graphics.getWidth() / 2 - logoSp.getWidth() / 2, Gdx.graphics.getHeight() - logoSp.getHeight() * 3);
        logoSp.draw(batch);
        text1Sp.setPosition(Gdx.graphics.getWidth() / 2 - text1Sp.getWidth(), Gdx.graphics.getHeight() / 2 - text1Sp.getHeight());
        text1Sp.draw(batch);
        text2Sp.setPosition(Gdx.graphics.getWidth() / 2 - text2Sp.getWidth() / 2, Gdx.graphics.getHeight() / 2 - text1Sp.getHeight());
        text2Sp.draw(batch);
        if(Gdx.input.justTouched()) {
            reStarted = true;
        }
        return reStarted;
    }
}
