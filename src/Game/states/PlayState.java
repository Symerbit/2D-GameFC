package Game.states;

import Game.entities.Player;
import Game.graphics.Font;
import Game.graphics.Sprite;
import Game.util.KeyHandler;
import Game.util.MouseHandler;
import Game.util.Vector2f;

import java.awt.*;

public class PlayState extends GameState {

    private Font font;
    private Player player;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        font = new Font("font/ZeldaFont.png", 16, 16);
        player = new Player(new Sprite("entity/linkFormatted.png"), new Vector2f(300, 300), 64);
    }

    public void update(){
        player.update();
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }


    public void render(Graphics2D g) {
        Sprite.drawArray(g, font, "WAJO DAN DIKKE BEWEGING ENZO NIET BEST MAN", new Vector2f(100, 100), 32, 32, 32, 0);
        player.render(g);
    }
}
