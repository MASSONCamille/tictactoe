package tictactoe.core;

import java.util.ArrayList;

import tictactoe.gui.Viewer;
import tictactoe.player.Player;

/**
 * @author Alexandre Blansché
 * Classe représentant une partie de morpion entre deux joueurs
 */
public class Game
{
    private Board board;
    private Player [] players;
    private ArrayList <Viewer> viewer;
    private int winner;
    
    /**
     * @param player1 Le premier joueur
     * @param player2 Le second joueur
     * @param board La grille de morpion
     */
    public Game (Player player1, Player player2, Board board)
    {
        this.board = board;
        this.players = new Player [2];
        this.players [0] = player1;
        this.players [1] = player2;
        this.viewer = new ArrayList <Viewer> ();
        this.winner = -2;
    }
    
    /**
     * Ajoute une interface d'affichage
     * @param viewer Une interface d'affichage
     */
    public void addViewer (Viewer viewer)
    {
        this.viewer.add (viewer);
    }
    
    /**
     * Début d'une partie
     */
    public void play ()
    {
        this.displayBoard ();
        this.nextTurn ();
    }
    
    /**
     * Coup du joueur actif
     * @param row La ligne
     * @param col La colonne
     */
    public void play (int row, int col)
    {
        boolean end = false;
        int winner = -1;
        int currentPlayer = this.board.getCurrentPlayer ();
        this.board.play (row, col);
        if (this.board.win (row, col))
        {
            winner = currentPlayer;
            end = true;
        }
        else if (this.board.isFull ())
            end = true;
        this.displayBoard ();
        if (end)
        {
            this.winner = winner;
            this.displayWinner ();
        }
        else
            this.nextTurn ();
    }

    /**
     * @return L'état courant de la grille de morpion
     */
    public Board getBoard ()
    {
        return this.board;
    }
    
    private void nextTurn ()
    {
        this.displayMessage ("Au tour de " + this.players [this.board.getCurrentPlayer ()].getName ());
        this.players [this.board.getCurrentPlayer ()].play (this);
    }

    private void displayBoard ()
    {
        for (Viewer viewer: this.viewer)
            viewer.displayBoard (this.board);
    }
    
    private void displayMessage (String string)
    {
        for (Viewer viewer: this.viewer)
            viewer.displayMessage (string);
    }

    private void displayWinner ()
    {
        String string = "";
        switch (this.getWinner ())
        {
            case -2: string = "En cours";
            break;
            case -1: string = "Égalité";
            break;
            default: string = this.getPlayer (this.getWinner ()) + " a gagné";
        }
        this.displayMessage (string);
    }
    
    private Player getPlayer (int index)
    {
        return this.players [index];
    }
    
    private int getWinner ()
    {
        return this.winner;
    }
}
