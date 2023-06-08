package com.example.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Tube {

    private Texture Tube_Up, Tube_Down;
    private Vector2 posTube_up, posBoTube;
    private RandomXS128 rand;
    private static final int Fluctuation = Gdx.graphics.getHeight()-300;
    private static final int Tube_Gap = 500;
    private static final int Lowest_Opening = 120;
    private Rectangle rect_up,rect_Down;
    public Tube(float x) {
        Tube_Up = new Texture(Gdx.files.internal("toptube.png"));
        Tube_Down = new Texture(Gdx.files.internal("bottomtube.png"));
        rand = new RandomXS128();
        posTube_up = new Vector2(x, rand.nextInt(Fluctuation) + Tube_Gap + Lowest_Opening);
        posBoTube = new Vector2(x, posTube_up.y - Tube_Gap - Tube_Down.getHeight());
        rect_up= new Rectangle(posTube_up.x,posTube_up.y,Tube_Up.getWidth(),Tube_Up.getHeight());
        rect_Down= new Rectangle(posBoTube.x,posBoTube.y,Tube_Down.getWidth(),Tube_Down.getHeight());
        
    }

    public Texture getTube_Up() {
        return this.Tube_Up;
    }

    public void setTube_Up(Texture Tube_Up) {
        this.Tube_Up = Tube_Up;
    }

    public Texture getTube_Down() {
        return this.Tube_Down;
    }

    public void setTube_Down(Texture Tube_Down) {
        this.Tube_Down = Tube_Down;
    }

    public Vector2 getPosTube_up() {
        return this.posTube_up;
    }

    public void setPosTube_up(Vector2 posTube_up) {
        this.posTube_up = posTube_up;
    }

    public Vector2 getPosBoTube() {
        return this.posBoTube;
    }

    public void setPosBoTube(Vector2 posBoTube) {
        this.posBoTube = posBoTube;
    }
    public void moveInseconds(float x){
        posTube_up.x-= x;
        posBoTube.x -= x;
        rect_up.setPosition(posTube_up.x,posTube_up.y);
        rect_Down.setPosition(posBoTube.x,posBoTube.y);
        if(getPosBoTube().x< -Tube_Up.getWidth()+10){
            reset(Gdx.graphics.getWidth());
        }
    }
    public void reset(int x){
        posTube_up = new Vector2(x, rand.nextInt(Fluctuation) + Tube_Gap + Lowest_Opening);
        posBoTube = new Vector2(x, posTube_up.y - Tube_Gap - Tube_Down.getHeight());
        rect_up.setPosition(posTube_up.x,posTube_up.y);
        rect_Down.setPosition(posBoTube.x,posBoTube.y);
        
    }
    
    public boolean isCollding(Rectangle Bird){
         return rect_up.overlaps(Bird) || rect_Down.overlaps(Bird);
    }
}
