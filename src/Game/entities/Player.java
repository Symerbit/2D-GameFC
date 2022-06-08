package Game.entities;

import Game.graphics.Sprite;
import Game.states.PlayState;
import Game.util.KeyHandler;
import Game.util.MouseHandler;
import Game.util.Vector2f;

import java.awt.*;

public class Player extends Entity {
    public Player(Sprite sprite, Vector2f orgin, int size) {
        super(sprite, orgin, size);
        acc = 3f;
        maxSpeed = 3f;
        bounds.setWidth(42);
        bounds.setHeight(25);
        bounds.setXOffset(12);
        bounds.setYOffset(40);

    }

    public void move() {

        /// ------------ UP
        if (up) {
            dy -= acc;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        /// ------------ DOWN
        if (down) {
            dy -= acc;
            if (dy < maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        /// ------------ LEFT
        if (left) {
            dx -= acc;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        /// ------------ RIGHT
        if (right) {
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dy < 0) {
                    dx = 0;
                }
            }
        }
    }

    public void update() {
        super.update();
        move();
        if(!bounds.collisionTile(dx, 0)) {
            PlayState.map.x += dx;
            pos.x += dx;
        }
        if(!bounds.collisionTile(0, dy)) {
            PlayState.map.y += dy;
            pos.y += dy;
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.blue);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }

    public void input(MouseHandler mouse, KeyHandler key) {

        if (mouse.getButton() == 1) {
            System.out.println("Player: " + pos.x + ", " + pos.y);
        }

        if (key.up.down) {
            up = true;
        } else {
            up = false;
        }
        if (key.down.down) {
            down = true;
        } else {
            down = false;
        }
        if (key.left.down) {
            left = true;
        } else {
            left = false;
        }
        if (key.right.down) {
            right = true;
        } else {
            right = false;
        }
        if(key.attack.down){
            attack = true;
                } else {
            attack = false;
        }
    }
}
