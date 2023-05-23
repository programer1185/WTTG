package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Waves {
    WTTG wttg;
    Invader Invaders[];
    int invaders_width = 9, invaders_height, blackLenght, rows, yellowLength;
    int minX_inv, maxX_inv, minY_inv, maxY_inv;
    int direction_invaders = 1;
    int inv_lives =0 ;
    public Vector2 offset_aliens;
    public Player player;
    int speed_inv = 250, boost = 0;

    boolean extraWave = false;
    int wave = 0;
    Texture inv1 = new Texture("Enemy1.png"), inv2 = new Texture("Enemy2.png"), inv3 = new Texture("Enemy3.png");
    public Waves(Player player, WTTG wttg){
        this.player = player;
        this.wttg = wttg;
        invaders_height = wave;
        offset_aliens = new Vector2();
        this.generateWave();

    }
    public void update(SpriteBatch batch){
        float deltaTime = Gdx.graphics.getDeltaTime();
        for(int i = 0; i < Invaders.length; i++){
            if(Invaders[i].Alive()) {
                if (player.sprite_bullet.getBoundingRectangle().overlaps(Invaders[i].sprite.getBoundingRectangle())) {
                    player.position_bullet.y = 10000;
                    Invaders[i].lives --;
                    inv_lives--;
                    Invaders[i].sprite.setColor(1, 0, 0, 1);
                    if(Invaders[i].getClass() == Yellow.class){
                        Invaders[i].sprite.setColor(1,0,0,1);
                    }else if(Invaders[i].getClass() == Black.class){
                        if(Invaders[i].lives == 2)
                            Invaders[i].sprite.setColor(1, 1, 0, 1);
                        else if(Invaders[i].lives == 1)
                            Invaders[i].sprite.setColor(1, 0, 0, 1);
                    }else
                        Invaders[i].sprite.setColor(1,1,1,1);
                    break;
                }
            }

        }
        if(inv_lives == 0){
            if(wave < 3) {
                this.generateWave();
                this.update(batch);
                return;
            }
            else if(wave == 3){
                wttg.BossWave = true;
                return;
            }else{
                Gdx.app.exit();
            }
        }
        minX_inv = 10000;
        minY_inv = 10000;
        maxX_inv = 0;
        maxY_inv = 0;

        for(int i = 0; i < Invaders.length; i++){
            if(Invaders[i].Alive()) {
                int IndexX = i % invaders_width;
                int IndexY = i / invaders_width;
                if (IndexX > maxX_inv) maxX_inv = IndexX;
                if (IndexX < minX_inv) minX_inv = IndexX;
                if (IndexY > maxY_inv) maxY_inv = IndexY;
                if (IndexY < minY_inv) minY_inv = IndexY;
            }
        }
        offset_aliens.x += direction_invaders * deltaTime * speed_inv;
        if(Invaders[maxX_inv].position.x >= Gdx.graphics.getWidth()) {
            direction_invaders = -1;
            offset_aliens.y -= Invaders[0].sprite.getHeight() * Invaders[0].sprite.getScaleY() * 0.30f;
            speed_inv += 5;
            for(int i = 0; i < Invaders.length; i++){
                if(Invaders[i].getClass() == Yellow.class){
                    if(Invaders[i].lives < 2 && Invaders[i].Alive()) {
                        Invaders[i].lives++;
                        inv_lives++;
                        Invaders[i].sprite.setColor(1, 1, 1, 1);
                    }
                }
            }
        }
        if(Invaders[minX_inv].position.x <= 0){
            direction_invaders = 1;
            offset_aliens.y -= Invaders[0].sprite.getHeight() * Invaders[0].sprite.getScaleY() * 0.30f;
            speed_inv += 5;
            for(int i = 0; i < Invaders.length; i++){
                if(Invaders[i].getClass() == Yellow.class){
                    if(Invaders[i].lives < 2 && Invaders[i].Alive()) {
                        Invaders[i].lives++;
                        inv_lives++;
                        Invaders[i].sprite.setColor(1, 1, 1, 1);
                    }
                }
            }
        }
        if(Invaders[maxY_inv].position.y <= 0){
            wttg.playerLive = false;
            return;
        }
        for(int i = 0; i < Invaders.length; i++){
            Invaders[i].position = new Vector2(Invaders[i].position_initial.x + offset_aliens.x, Invaders[i].position_initial.y + offset_aliens.y);
            if(Invaders[i].Alive()){
                Invaders[i].draw(batch);
                if(Invaders[i].sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())){
                    wttg.playerLive = false;
                    return;
                }
            }
        }

    }
    public void generateWave(){
        yellowLength = invaders_width;
        blackLenght = invaders_width;
        offset_aliens = new Vector2(0, 0);
        rows = 0;
        speed_inv = 250 + boost;
        inv_lives = 0;
        int inavaderNum = 0;
        int spacing = 110;
        Invaders = new Invader[invaders_width * (++wave)];
        for(int i = 0; i < wave; i++){
            for(int j = 0; j < invaders_width; j++){
                Vector2 invPosition = new Vector2(j * spacing, i * spacing);
                invPosition.y += Gdx.graphics.getHeight();
                invPosition.x += Gdx.graphics.getWidth() / 2;
                invPosition.x -= invaders_width * spacing / 2;
                invPosition.y -= invaders_height * spacing;
                if(rows == 1 && yellowLength > 0){
                    Invaders[inavaderNum] = new Yellow(invPosition, inv2);
                    inv_lives += 2;
                    yellowLength--;
                }
                else if(rows == 2 && blackLenght > 0){
                    Invaders[inavaderNum] = new Black(invPosition, inv3);
                    inv_lives += 3;
                    blackLenght--;
                }else{
                    Invaders[inavaderNum] = new Red(invPosition, inv1);
                    inv_lives++;
                }
                inavaderNum++;
            }
            rows++;
        }
        boost += 75;
    }
}
