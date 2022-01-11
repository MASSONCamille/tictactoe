package tictactoe.gui;

import tictactoe.core.Board;

/**
 * @author Alexandre Blansché
 * Sortie standard
 * Classe singleton
 */
public class StandardOutputViewer implements Viewer
{
    private static StandardOutputViewer instance = null;
    
    /**
     * @return Accès à l'instance
     */
    public static StandardOutputViewer getInstance ()
    {
        if (StandardOutputViewer.instance == null)
            StandardOutputViewer.instance = new StandardOutputViewer ();
        return StandardOutputViewer.instance;
    }
    
    @Override
    public void displayBoard (Board board)
    {
        System.out.println (board);
    }
    
    @Override
    public void displayMessage (String string)
    {
        System.out.println (string);
    }
}
