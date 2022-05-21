package Game;

import Game.states.GameStateManager;
import Game.util.KeyHandler;
import Game.util.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gsm;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true); // laat JPanel input hebben als JFrame gemaakt is
        requestFocus(); // ^
    }

    public void addNotify() {
        super.addNotify();  // Keyboard & Mouse Inputs

        if(thread == null) {  // checkt dat de thread gemaakt is
            thread = new Thread(this, "gameThread");
            thread.start();
        }
    }

    public void init() {
        running = true;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); //
        g = (Graphics2D) img.getGraphics(); //Graphics image -> Buffer image

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gsm = new GameStateManager();
    }

    public void run() {
        init();

        final double GAME_HERTZ = 144.0; //constant, veranderd niet
        final double TBU = 1000000000 / GAME_HERTZ; //timeBeforeUpdate

        final int MUBR = 5; //Must Updates Before Render

        double lastUpdateTime = System.nanoTime(); // 1 * 10^-9 = 0.000000001
        double lastRenderTime;

        final double TARGET_FPS = 144; //MAX FPS
        final double TTBR = 1000000000 / TARGET_FPS; // Totale tijd voor de render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        while(running) { //Game Loop
            double now = System.nanoTime(); //Huidige Tijd
            int updateCount = 0;
            while((now - lastUpdateTime) > TBU && (updateCount < MUBR)) {//Checkt of GHZ op tijd is
                update();
                input(mouse, key);
                lastUpdateTime += TBU; //
                updateCount ++;
            }

            if(now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }

            input(mouse, key);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if(thisSecond > lastSecondTime) {
                if(frameCount != oldFrameCount){
                    System.out.println("NEW SECOND" + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while(now - lastRenderTime < TTBR && now - lastUpdateTime < TBU){
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch(Exception e) {
                    System.out.println("ERROR: YIELDING THREAD!");
                }
                now = System.nanoTime();
            }
        }
    }

    public void update() {
        gsm.update();
    }

    public void  input(MouseHandler mouse, KeyHandler key){
        gsm.input(mouse, key);
    }

    public void render () { //Image -> SCHERM
        if(g != null) {
            g.setColor(new Color(66, 134, 244)); //window kleur
            g.fillRect(0, 0, width, height); //afmetingen van window kleur
            gsm.render(g);
        }
    }
    public void draw () {
        Graphics g2 = (Graphics) this.getGraphics(); //roept de method van in JPanel Graphics
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
    }

    //end
}
