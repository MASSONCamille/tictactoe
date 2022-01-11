package tictactoe.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tictactoe.core.Board;

/**
 * @author Alexandre Blansché
 * Panneau d'affichage des messages
 */
public class MessagePanel extends JPanel implements Viewer
{
    private JLabel text;
    
    /**
     * Constructeur...
     */
    public MessagePanel ()
    {
        this.text = new JLabel ("Bienvenue !");
        this.add (this.text);
    }
    
    @Override
    public void displayBoard (Board board)
    {
    }

    @Override
    public void displayMessage (String string)
    {
        this.text.setText (string);
    }

}
