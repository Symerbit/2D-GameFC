package Game.entities;

import Game.graphics.Animation;
import Game.graphics.Sprite;
import Game.util.KeyHandler;
import Game.util.MouseHandler;
import Game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity { //voor alle bewegende characters

    private final int UP = 0;
    private final int DOWN = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;
    protected int currentAnimation;

    protected Animation ani; //protected = elke class die entity extend (niet toegang voor alles)
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up; // naar boven bewegen
    protected boolean down; // naar beneden bewegen
    protected boolean right; // rechts bewegen
    protected boolean left; // links bewegen
    protected boolean attack; // aanval
    protected int attackSpeed; // aanvalsnelheid
    protected int attackDuration; // duur van aanval

    protected float dx; // direction x
    protected float dy; // direction y

    protected float maxSpeed; //max snelheid
    protected float acc; // acceleratie
    protected float deacc; //deactivatie

    public Entity(Sprite sprite, Vector2f orgin, int size) {
        this.sprite = sprite;
        pos = orgin;
        this.size = size;

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public int getSize() { return size; }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void animate() {
        if(up) {
            if(currentAnimation != UP || ani.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        }
        else if(down) {
            if(currentAnimation != DOWN || ani.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        }
        else if(left) {
            if(currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        }
        else if(right) {
            if(currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        } else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    public void update() {
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public abstract void render(Graphics2D g);
    public void input(KeyHandler key, MouseHandler mouse) {}

}
