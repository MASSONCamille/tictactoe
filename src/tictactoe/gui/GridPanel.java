package tictactoe.gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import tictactoe.core.Board;
import tictactoe.player.human.HumanPlayer;

/**
 * @author Alexandre Blansché
 * Panneau pour la grille de morpion
 */
public class GridPanel extends JPanel implements Viewer
{
    private MarkButton [][] grid;
    private static ImageIcon o, x;
    
    /**
     * @param n Le nombre de lignes
     * @param m Le nombre de colonnes
     * @param player Le joueur humain à attacher à l'interface graphique
     */
    public GridPanel (int n, int m, HumanPlayer player)
    {
        this.grid = new MarkButton [n][m];
        JPanel panel = new JPanel ();
        panel.setLayout (new GridLayout (n, m));
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
            {
                this.grid [i][j] = new MarkButton (i, j);
                panel.add (this.grid [i][j]);
                this.grid [i][j].addActionListener (player);
            }
        this.add (panel);
    }
    
    /**
     * Chargement des icônes pour les symboles de morpion
     */
    public static void loadImages ()
    {
        GridPanel.o = null;
        GridPanel.x = null;
        try
        {
            GridPanel.o = new ImageIcon ("resources/o.png");
            GridPanel.x = new ImageIcon ("resources/x.png");
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }        
    }

    @Override
    public void displayBoard (Board board)
    {
        int [][] grid = board.getGrid ();
        for (int i = 0; i < board.getN (); i++)
            for (int j = 0; j < board.getM (); j++)
            {
                switch (grid [i][j])
                {
                    case -1:
                        this.grid [i][j].setIcon (null);
                        break;
                    case 0:
                        this.grid [i][j].setIcon (GridPanel.o);
                        break;
                    case 1:
                        this.grid [i][j].setIcon (GridPanel.x);
                        break;                        
                }
            }
        this.paintImmediately (this.getVisibleRect ());
    }

    @Override
    public void displayMessage (String string)
    {
    }
}
