package Game;

import javax.swing.JFrame;

public class Window extends JFrame{

    public Window() { //dit is de window
        setTitle("TheZeroDocterTutorial2D"); //de titel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sluit af bij sluiten
        setContentPane(new GamePanel(1080, 720)); //de window regio
        pack();
        // this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH); //Maximize de window
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
