package tictactoe.run;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tictactoe.core.Board;
import tictactoe.core.Game;
import tictactoe.gui.ConfigPanel;
import tictactoe.gui.GridPanel;
import tictactoe.gui.MessagePanel;
import tictactoe.gui.StandardOutputViewer;
import tictactoe.player.Player;
import tictactoe.player.human.HumanPlayer;

/**
 * @author Alexandre Blansché
 * Programme principal et fenêtre principale
 * Classe singleton
 */
public class TicTacToe extends JFrame implements ActionListener, ChangeListener
{
    private static TicTacToe instance = null;

    private Board board;
    private HumanPlayer humanPlayer;
    private Player aiPlayer;
    private int firstPlayer;
    private Game ttt;

    private JPanel mainPanel;
    private ConfigPanel configPanel;
    private GridPanel gridPanel;
    private MessagePanel messagePanel;

    private TicTacToe ()
    {
        TicTacToe.instance = this;
        GridPanel.loadImages ();
        this.humanPlayer = new HumanPlayer ();
        this.setTitle ("Tic-tac-toe");
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.initialize ();
        this.reset ();
        this.setResizable (false);
    }

    /**
     * @return Accès à l'instance
     */
    public static TicTacToe getInstance ()
    {
        if (TicTacToe.instance == null)
            TicTacToe.instance = new TicTacToe ();
        return TicTacToe.instance;
    }

    /**
     * Modification de la grille
     * @param n Le nombre de lignes
     * @param m Le nombre de colonnes
     * @param k Le nombre de symboles à aligner
     */
    public void changeBoard (int n, int m, int k)
    {
        this.board = new Board (n, m, k);
        Component grid = ((BorderLayout) this.mainPanel.getLayout ()).getLayoutComponent (BorderLayout.CENTER);
        if (grid != null)
            this.mainPanel.remove (grid);
        this.gridPanel = new GridPanel (n, m, this.humanPlayer);
        this.mainPanel.add (this.gridPanel, BorderLayout.CENTER);
        this.pack ();
    }

    private void view ()
    {
        this.setVisible (true);
    }

    private void initialize ()
    {
        this.mainPanel = new JPanel ();
        this.configPanel = new ConfigPanel ();
        this.messagePanel = new MessagePanel ();
        this.mainPanel.setLayout (new BorderLayout ());
        this.mainPanel.add (this.configPanel, BorderLayout.PAGE_START);
        this.mainPanel.add (this.messagePanel, BorderLayout.PAGE_END);
        this.setContentPane (this.mainPanel);
    }

    private void reset ()
    {
        this.configPanel.paintImmediately (this.configPanel.getVisibleRect ());
        this.humanPlayer.reset ();
        this.aiPlayer = this.configPanel.getAiPlayer ();
        this.firstPlayer = this.configPanel.getFirstPlayer ();
        this.changeBoard (this.configPanel.getN (), this.configPanel.getM (), this.configPanel.getK ());
        Player firstPlayer = this.humanPlayer;
        Player secondPlayer = this.aiPlayer;
        if (this.firstPlayer != 0)
        {
            firstPlayer = this.aiPlayer;
            secondPlayer = this.humanPlayer;
        }
        this.ttt = new Game (firstPlayer, secondPlayer, this.board);
        this.ttt.addViewer (StandardOutputViewer.getInstance ());
        this.ttt.addViewer (this.messagePanel);
        this.ttt.addViewer (this.gridPanel);
        this.ttt.play ();
    }

    @Override
    public void stateChanged (ChangeEvent e)
    {
        this.reset ();
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        Object source = e.getSource ();
        if ((source.getClass () == JButton.class) && ((JButton) source).getText ().equals ("Réinitialisation"))
        {
            this.mainPanel.removeAll ();
            this.initialize ();
        }
        this.reset ();
    }

    /**
     * @param args
     */
    public static void main (String [] args)
    {
        SwingUtilities.invokeLater (new Runnable ()
        {
            @Override
            public void run()
            {
                TicTacToe ttt = TicTacToe.getInstance ();
                ttt.view ();                
            }
        });
    }
}
