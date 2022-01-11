package tictactoe.core;

/**
 * @author Alexandre Blansché
 * L'état de la grille de morpion
 */
public class Board
{
    private int n, m, k;
    int nbEmpty;
    int currentPlayer;
    private int [][] grid;

    /**
     * @param n Le nombre de lignes
     * @param m Le nombre de colonnes
     * @param k Le nombre de symboles à aligner
     */
    public Board (int n, int m, int k)
    {
        this.n = n;
        this.m = m;
        this.k = k;
        this.nbEmpty = n * m;
        this.currentPlayer = 0;
        this.grid = new int [this.n][this.m];
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.m; j++)
                this.grid [i][j] = -1;        
    }

    /**
     * @return Le nombre de lignes
     */
    public int getN ()
    {
        return this.n;
    }
    
    /**
     * @return Le nombre de colonnes
     */
    public int getM ()
    {
        return this.m;
    }
    
    /**
     * @param row La ligne du dernier coup joué
     * @param col La colonne du dernier coup joué
     * @return Retourne si le dernier coup joué est gagnant ou pas
     */
    public boolean win (int row, int col)
    {
        int currentPlayer = this.grid [row][col];
        boolean win = false;
        int nbSucc = 0;
        for (int j = 0; j < this.m; j++)
            if (this.grid [row][j] == currentPlayer)
            {
                nbSucc++;
                if (nbSucc >= this.k)
                    win = true;
            }
            else
                nbSucc = 0;
        nbSucc = 0;
        for (int i = 0; i < this.n; i++)
            if (this.grid [i][col] == currentPlayer)
            {
                nbSucc++;
                if (nbSucc >= this.k)
                    win = true;
            }
            else
                nbSucc = 0;
        int offset = Math.min (row, col);
        int ki = row - offset;
        int kj = col - offset;
        nbSucc = 0;
        while ((ki < this.n) && (kj < this.m))
        {
            if (this.grid [ki][kj] == currentPlayer)
            {
                nbSucc++;
                if (nbSucc >= this.k)
                    win = true;
            }
            else
                nbSucc = 0;
            ki++;
            kj++;
        }
        offset = Math.min (this.n - row - 1, col);
        ki = row + offset;
        kj = col - offset;
        nbSucc = 0;
        while ((ki >= 0) && (kj < this.m))
        {
            if (this.grid [ki][kj] == currentPlayer)
            {
                nbSucc++;
                if (nbSucc >= this.k)
                    win = true;
            }
            else
                nbSucc = 0;
            ki--;
            kj++;
        }
        return win;
    }
    
    /**
     * Joue un coup
     * @param row La ligne du coup joué
     * @param col La colonne du coup joué
     */
    public void play (int row, int col)
    {
        this.grid [row][col] = this.currentPlayer;
        this.nbEmpty--;
        this.changePlayer ();
    }
    
    /**
     * @return Retourne true si la grille est pleine, false sinon
     */
    public boolean isFull ()
    {
        return (this.nbEmpty == 0);
    }

    /**
     * @return La grille de jeu sous la forme d'un tableau à deux dimensions
     */
    public int [][] getGrid ()
    {
        return this.grid;
    }
    
    /**
     * @param row Une ligne
     * @param col Une colonne
     * @return Retourne si la case est vide ou non
     */
    public boolean isEmpty (int row, int col)
    {
        return this.grid [row][col] < 0;
    }

    /**
     * @return Retourne l'indice du joueur courant
     */
    public int getCurrentPlayer ()
    {
        return this.currentPlayer;
    }
    
    /**
     * @return Retourne un index numérique permettant d'identifier l'état du jeu
     */
    public long getIndex ()
    {
        long index = Long.MAX_VALUE;
        
        long sum = 0;
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.m; j++)
            {
                sum *= 3;
                sum += this.grid [i][j] + 1;
            }
        if (sum < index)
            index = sum;
        
        sum = 0;
        for (int i = this.n - 1; i >= 0; i--)
            for (int j = this.m - 1; j >= 0; j--)
            {
                sum *= 3;
                sum += this.grid [i][j] + 1;
            }
        if (sum < index)
            index = sum;
        
        sum = 0;
        for (int i = this.n - 1; i >= 0; i--)
            for (int j = 0; j < this.m; j++)
            {
                sum *= 3;
                sum += this.grid [i][j] + 1;
            }
        if (sum < index)
            index = sum;
        
        sum = 0;
        for (int i = 0; i < this.n; i++)
            for (int j = this.m - 1; j >= 0; j--)
            {
                sum *= 3;
                sum += this.grid [i][j] + 1;
            }
        if (sum < index)
            index = sum;
        
        return index;
    }
    
    private void changePlayer ()
    {
        this.currentPlayer = 1 - this.currentPlayer;
    }
    
    @Override
    public String toString ()
    {
        String string = "";
        for (int i = 0; i < this.n; i++)
        {
            string += "|";
            for (int j = 0; j < this.m; j++)
            {
                switch (this.grid [i][j])
                {
                    case 0: string += "o";
                    break;
                    case 1: string += "x";
                    break;
                    default: string += " ";
                }
                string += "|";
            }
            string += "\n";
        }
        return string;
    }

    @Override
    public Object clone ()
    {
        Board clone = new Board (this.n, this.m, this.k);

        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.m; j++)
                clone.grid [i][j] = this.grid [i][j];
        clone.nbEmpty = this.nbEmpty;
        clone.currentPlayer = this.currentPlayer;
        
        return clone;
    }
}
