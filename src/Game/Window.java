package Game;

import javax.swing.JFrame;

public class Window extends JFrame{

    public Window() { //dit is de window
        setTitle("Game Title!"); //de titel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sluit af bij sluiten
        setContentPane(new GamePanel(1280, 720)); //de window regio
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
