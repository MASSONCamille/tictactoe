package tictactoe.player;

import tictactoe.core.Game;

/**
 * @author Alexandre Blansché
 * Classe abstraite pour représenter un joueur
 */
public abstract class Player
{
    private String name;
    
    /**
     * Fonction pour choisir et jouer un coup
     * @param game la partie en cours
     */
    public abstract void play (Game game);

    /**
     * @return Le nom du joueur
     */
    public String getName ()
    {
        return this.name;
    }

    protected void setName (String name)
    {
        this.name = name;
    }
    
    @Override
    public String toString ()
    {
        return this.getName ();
    }
}
