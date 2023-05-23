package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BossWave {
    public int BossLives = 150;
    Texture texture = new Texture("BOSS.png");
    Texture punch = new Texture("BossPanch.png");
    Sprite BossSp = new Sprite(texture);
    Sprite punchSp = new Sprite(punch);
    public Vector2 position, offset_boss, punch_position;
    int direction = 1, speedBoss = 200, punch_speed = 200;
    Player player;
    WTTG wttg;
    public BossWave(Player player,WTTG wttg){
        this.player = player;
        this.wttg = wttg;
        punch_position = new Vector2(0, -10000);
        punchSp.setScale(4);
        offset_boss = new Vector2();
        BossSp.setScale(5);
        position = new Vector2(Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() - BossSp.getHeight() * BossSp.getScaleY());
        BossSp.setPosition(position.x, position.y);

    }
    public void update(SpriteBatch batch){
        float deltaTime = Gdx.graphics.getDeltaTime();
        BossSp.draw(batch);
        if(punch_position.x > Gdx.graphics.getWidth() / 2) {
            punchSp.flip(false, false);
        }
            punchSp.draw(batch);

        if(BossLives > 0) {
            if (player.sprite_bullet.getBoundingRectangle().overlaps(BossSp.getBoundingRectangle())) {
                player.position_bullet.y = 10000;
                BossLives --;
                if(BossLives <= 100 && BossLives > 50)
                    BossSp.setColor(1, 1, 0, 1);
                else if (BossLives <= 50) {
                    BossSp.setColor(1,0,0,1);
                }
            }
        }
        if(BossLives <= 0){
            wttg.finish = true;
            return;
        }
        offset_boss.x = direction * deltaTime * speedBoss;

        if(position.x >= Gdx.graphics.getWidth()) {
            direction = -1;
            punch_speed += 50;
        }
        if(position.x <= 0) {
            direction = 1;
            punch_speed += 50;
        }
        if(BossLives > 0){
            position.x += offset_boss.x;
            BossSp.setPosition(position.x, position.y);
        }
        if(punch_position.y < 0) {
            punch_position.x = position.x;
            punch_position.y = Gdx.graphics.getHeight() - BossSp.getHeight();
        }
        punch_position.y -= deltaTime * punch_speed;
        punchSp.setPosition(punch_position.x,punch_position.y);
        if(player.sprite.getBoundingRectangle().overlaps(punchSp.getBoundingRectangle())){
            wttg.playerLive = false;
            return;
        }
    }
}
