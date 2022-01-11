package tictactoe.gui;

import java.awt.Dimension;

import javax.swing.JButton;

/**
 * @author Alexandre Blansché
 * Case de la grille de morpion
 */
public class MarkButton extends JButton
{
    /**
     * Taille des boutons
     */
    public static int MARK_SIZE = 75;
    
    private int row, col;

    /**
     * @param row La ligne
     * @param col La colonne
     */
    public MarkButton (int row, int col)
    {
        this.row = row;
        this.col = col;
        this.setContentAreaFilled (false);
        this.setPreferredSize (new Dimension (MarkButton.MARK_SIZE, MarkButton.MARK_SIZE));
        this.setMinimumSize (new Dimension (MarkButton.MARK_SIZE, MarkButton.MARK_SIZE));
        this.setMaximumSize (new Dimension (MarkButton.MARK_SIZE, MarkButton.MARK_SIZE));
    }

    /**
     * @return La ligne
     */
    public int getRow ()
    {
        return this.row;
    }

    /**
     * @return La colonne
     */
    public int getCol ()
    {
        return this.col;
    }

    @Override
    public Dimension getPreferredSize ()
    {
        Dimension d = super.getPreferredSize ();
        int s = (int) (d.getWidth () < d.getHeight () ? d.getHeight () : d.getWidth ());
        return new Dimension (s,s);
    }

    @Override
    public Dimension getMinimumSize ()
    {
        Dimension d = super.getMinimumSize ();
        int s = (int) (d.getWidth () < d.getHeight () ? d.getHeight () : d.getWidth ());
        return new Dimension (s,s);
    }

    @Override
    public Dimension getMaximumSize ()
    {
        Dimension d = super.getMaximumSize ();
        int s = (int) (d.getWidth () < d.getHeight () ? d.getHeight () : d.getWidth ());
        return new Dimension (s,s);
    }
}
