package com.example.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;

public class GameSuperClass extends Game {
    public SpriteBatch spriteBatch;
    private Texture background;
    private Texture[] FlappyState = new Texture[2];

    private int state = 0;
    private float birdy = 0;
    private float velocity;
    private int timer = 0;
    private int GameState = 0;
    private int Velocity = 0;
    private BitmapFont font;
    private float Gap = 500;
    private Tube tube;
    private Rectangle bird;
    private Texture GameOver;
    private Image Play;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("background.png"));
        FlappyState[0] = new Texture(Gdx.files.internal("flappybirdup.png"));
        FlappyState[1] = new Texture(Gdx.files.internal("flappybirddown.png"));
        tube = new Tube(Gdx.graphics.getWidth());
        GameOver = new Texture(Gdx.files.internal("gameover.png"));
        birdy = ((Gdx.graphics.getHeight()) / 2f) - (FlappyState[state].getHeight() / 2f);
        font = new BitmapFont();
        Texture Btn_Image = new Texture(Gdx.files.internal("play.png"));
        Play = new Image(Btn_Image);
        Play.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-100,200,200);
    }

    @Override
    public void render() {
        if (Gdx.input.justTouched()) {
            GameState = 1;
        }
        bird = new Rectangle(
                        (Gdx.graphics.getWidth() / 2) - (FlappyState[state].getWidth() / 2),
                        birdy,
                        FlappyState[state].getWidth(),
                        FlappyState[state].getWidth());

        if (tube.isCollding(bird)) {
            GameState = -1;
            tube.reset(Gdx.graphics.getWidth());
        }

        if (GameState == 1) {
            tube.moveInseconds(2);
            timer++;
            if (timer > 20) {
                if (state == 0) {
                    state = 1;
                } else {
                    state = 0;
                }
                timer = 0;
            }

            if (!Gdx.input.justTouched()) {
                if (birdy > 0) {
                    velocity += 2;
                    birdy = birdy - velocity;
                }
            } else {
               if(birdy<Gdx.graphics.getHeight()-100){
                velocity = -30;
                birdy = birdy - velocity;
               }
            }
        }
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, Gdx.graphics.getHeight(), Gdx.graphics.getHeight());
        if (GameState == 1) {
            spriteBatch.draw(tube.getTube_Up(), tube.getPosTube_up().x, tube.getPosTube_up().y);
            spriteBatch.draw(tube.getTube_Down(), tube.getPosBoTube().x, tube.getPosBoTube().y);
            spriteBatch.draw(
                    FlappyState[state],
                    ((Gdx.graphics.getWidth()) / 2f) - (FlappyState[state].getWidth() / 2f),
                    birdy);
            font.draw(spriteBatch, "birdy:-" + birdy + " Velocity:-" + Velocity, 50, 50);

        } else if (GameState == -1) {

            //  spriteBatch.draw(background,0, 0,Gdx.graphics.getHeight(),
            // Gdx.graphics.getHeight());
            spriteBatch.draw(
                    GameOver,
                    (Gdx.graphics.getWidth() / 2) - (GameOver.getWidth() / 2),
                    Gdx.graphics.getHeight() / 2);
        }else if(GameState==0){
            Play.draw(spriteBatch,100);
        }
        spriteBatch.end();

        Play.addListener( new ClickListener() {
             @Override
             public void clicked(InputEvent arg0, float arg1, float arg2) {
                 // super.clicked(arg0, arg1, arg2);
                    GameState=1;
             }
        });
    }

    @Override
    public void dispose() {
        background.dispose();
        spriteBatch.dispose();
        FlappyState[state].dispose();
    }
}
