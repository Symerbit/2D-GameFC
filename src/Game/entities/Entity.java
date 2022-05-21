package Game.entities;

import Game.graphics.Animation;
import Game.graphics.Sprite;
import Game.util.AABB;
import Game.util.KeyHandler;
import Game.util.MouseHandler;
import Game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity { //voor alle bewegende characters

    private final int UP = 3;
    private final int DOWN = 2;
    private final int RIGHT = 0;
    private final int LEFT = 1;
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

    protected AABB hitBounds;
    protected AABB bounds;

    public Entity(Sprite sprite, Vector2f orgin, int size) {
        this.sprite = sprite;
        pos = orgin;
        this.size = size;

        bounds = new AABB(orgin, size, size);
        hitBounds = new AABB(new Vector2f(orgin.x + (size / 2), orgin.y), size, size);

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSize(int i) { size = i; }
    public void setMaxSpeed(float f) { maxSpeed = f; }
    public void setAcc(float f) { acc = f; }
    public void setDeacc(float f) {deacc = f; }

    public AABB getBounds() { return bounds; }

    public int getSize() { return size; }
    public Animation getAnimation() { return ani; }

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

    private void setHitBoxDirection() {
        if(up) {
            hitBounds.setYOffset(-size / 2);
            hitBounds.setXOffset(-size / 2);
        }
        else if(down) {
            hitBounds.setYOffset(size / 2);
            hitBounds.setXOffset(-size / 2);
        }
        else if(left) {
            hitBounds.setXOffset(-size);
            hitBounds.setYOffset(0);
        }
        else if(right) {
            hitBounds.setYOffset(0);
            hitBounds.setXOffset(0);
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
