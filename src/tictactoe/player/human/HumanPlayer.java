package tictactoe.player.human;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tictactoe.core.Game;
import tictactoe.gui.MarkButton;
import tictactoe.player.Player;

/**
 * @author Alexandre Blansché
 * Joueur humain via l'interface graphique
 */
public class HumanPlayer extends Player implements ActionListener
{
    private boolean ready;
    private Game game;
    
    /**
     * Constructeur...
     */
    public HumanPlayer ()
    {
        this.setName ("Joueur");
        this.reset ();
    }
    
    /**
     * Réinitialisation du joueur
     */
    public void reset ()
    {
        this.ready = false;
    }

    @Override
    public void play (Game game)
    {
        this.ready = true;
        this.game = game;
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (this.ready)
        {
            MarkButton button = (MarkButton) e.getSource ();
            int row = button.getRow ();
            int col = button.getCol ();
            if (this.game.getBoard ().isEmpty (row, col))
            {
                this.ready = false;
                this.game.play (row, col);
            }
        }
    }
}
