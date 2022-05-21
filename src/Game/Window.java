package Game;

import javax.swing.JFrame;

public class Window extends JFrame{

    public Window() { //dit is de window
        setTitle("Maar-Koos Geen-Zin"); //de titel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sluit af bij sluiten
        setContentPane(new GamePanel(1920, 1080)); //de window regio
        pack();
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH); //Maximize de window
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
