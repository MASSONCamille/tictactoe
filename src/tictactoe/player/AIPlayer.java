package tictactoe.player;

import java.util.Random;

import tictactoe.core.Board;
import tictactoe.core.Game;

/**
 * @author Alexandre Blansché
 * Classe abstraite pour représenter un joueur artificiel (IA)
 */
public abstract class AIPlayer extends Player
{
    private Random random;

    protected AIPlayer ()
    {
        this.random = new Random (1 + System.currentTimeMillis ());
    }

    /**
     * L'IA jouera le coup valide qui a le meilleur score
     * @param board État courant de la grille de morpion
     * @return Le score de chacun des coups possibles
     */
    public abstract double [][] getDecision (Board board);

    @Override
    public void play (Game game)
    {
        Board board = game.getBoard ();
        double [][] decision = this.getDecision (board);
        int row = -1, col = -1;
        double bestDecision = -Double.MAX_VALUE;
        int nbBest = 0;
        for (int i = 0; i < board.getN (); i++)
            for (int j = 0; j < board.getM (); j++)
                if (board.getGrid () [i][j] == -1)
                    if ((decision [i][j] > bestDecision))
                    {
                        bestDecision = decision [i][j];
                        nbBest = 1;
                    }
                    else if (decision [i][j] == bestDecision)
                        nbBest++;
        int select = this.random.nextInt (nbBest);
        loop:
            for (int i = 0; i < board.getN (); i++)
                for (int j = 0; j < board.getM (); j++)
                    if ((board.getGrid () [i][j] == -1) && (decision [i][j] == bestDecision))
                        if (select == 0)
                        {
                            row = i;
                            col = j;
                            break loop;
                        }
                        else
                            select--;
        game.play (row, col);
    }
}
