package Game.states;

import Game.util.KeyHandler;
import Game.util.MouseHandler;

import javax.swing.plaf.ColorUIResource;
import java.awt.Graphics2D;

public class PlayState extends GameState {

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void update(){

    }

    public void input(MouseHandler mouse, KeyHandler key) {

    }

    public void render(Graphics2D g){
        g.setColor(ColorUIResource.red);
        g.fillRect(100, 100, 200, 200);
    }
}
