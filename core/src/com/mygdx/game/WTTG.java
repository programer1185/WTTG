package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class WTTG extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, lb, rb, bullet, inv, can;
	Player player;
	Invader Invaders[];
	public static Sprite leftButton, rightButton, canvas;
	public static float HEIGTH, WIDTH;
	public Waves waves;
	public Start start;
	public BossWave bossWave;
	public Death death;
	public Win win;
	public boolean playerLive, BossWave, finish;
	@Override
	public void create () {
		HEIGTH = Gdx.graphics.getHeight();
		WIDTH = Gdx.graphics.getWidth();
		batch = new SpriteBatch();
		inv = new Texture("Enemy1.png");
		bullet = new Texture("Shoot.png");
		img = new Texture("Player.png");
		lb = new Texture("button-left.png");
		rb = new Texture("button-right.png");
		leftButton = new Sprite(lb);
		leftButton.setScale(30);
		leftButton.setPosition(leftButton.getWidth() * leftButton.getScaleX() / 2,leftButton.getHeight() * leftButton.getScaleY() / 2);
		rightButton = new Sprite(rb);
		rightButton.setScale(30);
		rightButton.setPosition(WIDTH - rightButton.getWidth() * rightButton.getScaleX() / 2, rightButton.getHeight() * rightButton.getScaleY() / 2);
		player = new Player(img, bullet);
		can = new Texture("Canvas.png");
		canvas = new Sprite(can);
		canvas.setPosition(0, 0);
		canvas.setScale(1);
		finish = false;
		playerLive = true;
		BossWave = false;
		bossWave = new BossWave(player, this);
		waves = new Waves(player, this);
		start = new Start(false);
		death = new Death();
		win = new Win();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0,0,0,1);
		batch.begin();
		canvas.draw(batch);
		if(start.update(batch)) {
			if(!finish) {
				if (playerLive) {
					leftButton.draw(batch);
					rightButton.draw(batch);
					player.draw(batch);
					if (!BossWave)
						waves.update(batch);
					else{
						bossWave.update(batch);
					}
				}else{
					if (death.update(batch)) {
						Gdx.app.exit();
					}
				}

			}else {
				win.update(batch);
			}
		}
		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
