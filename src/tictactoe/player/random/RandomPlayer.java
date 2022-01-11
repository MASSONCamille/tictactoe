package tictactoe.player.random;

import java.util.Random;

import tictactoe.core.Board;
import tictactoe.player.AIPlayer;

/**
 * @author Alexandre Blansché
 * IA jouant au hasard
 */
public class RandomPlayer extends AIPlayer
{
    private Random random;
    
    /**
     * Constructeur...
     */
    public RandomPlayer ()
    {
        this.setName ("Random");
        this.random = new Random (System.currentTimeMillis ());  
    }
    
    @Override
    public double [][] getDecision (Board board)
    {
        double [][] decision = new double [board.getN ()][board.getM ()];
        for (int i = 0; i < decision.length; i++)
            for (int j = 0; j < decision [i].length; j++)
                decision [i][j] = this.random.nextDouble ();
        return decision;
    }
}
