package com.example.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class Splash implements Screen {

    private GameSuperClass game;
    private Texture texture;
    
    public Splash(GameSuperClass s){
        this.game = s;
        texture = new Texture(Gdx.files.internal("greetings.png"));
    }
    
    @Override
    public void show() {}

    @Override
    public void render(float arg0) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
       /* game.batch.begin();
        game.batch.draw(texture,(Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 2f, 0,
                Gdx.graphics.getHeight(), Gdx.graphics.getHeight());
        game.batch.end(); */
    }

    @Override
    public void resize(int arg0, int arg1) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
