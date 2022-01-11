package tictactoe.gui;

import tictactoe.core.Board;

/**
 * @author Alexandre Blansché
 * Interface d'affichage des messages et de la grille de morpion
 */
public interface Viewer
{
    /**
     * Affichage de la grille
     * @param board La grille de morpion
     */
    public void displayBoard (Board board);
    
    /**
     * Affichage d'un message
     * @param string le message
     */
    public void displayMessage (String string);
}
