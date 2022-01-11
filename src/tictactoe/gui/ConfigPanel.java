package tictactoe.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.reflections.Reflections;

import tictactoe.player.AIPlayer;
import tictactoe.player.Player;
import tictactoe.run.TicTacToe;

/**
 * @author Alexandre Blansché
 * Panneau de configuration
 */
public class ConfigPanel extends JPanel
{
    private static int MARGIN = 5;
    
    private JSpinner nSpinner, mSpinner, kSpinner;
    private ArrayList <AIRadioButton> aiButtons;
    private ArrayList <JRadioButton> firstButtons;

    /**
     * Constructeur...
     */
    public ConfigPanel ()
    {
        this.setBorder (BorderFactory.createEmptyBorder (ConfigPanel.MARGIN, ConfigPanel.MARGIN, ConfigPanel.MARGIN, ConfigPanel.MARGIN));
        this.setLayout (new GridLayout (6, 2));
        
        JButton newGameButton = new JButton ("Nouvelle partie");
        newGameButton.addActionListener (TicTacToe.getInstance ());
        this.add (newGameButton);
        JButton resetButton = new JButton ("Réinitialisation");
        resetButton.addActionListener (TicTacToe.getInstance ());
        this.add (resetButton);
        
        this.add (new JLabel ("n: "));
        this.nSpinner = new JSpinner (new SpinnerNumberModel (3, 3, 5, 1));
        this.nSpinner.addChangeListener (TicTacToe.getInstance ());
        this.add (this.nSpinner);
        this.add (new JLabel ("m: "));
        this.mSpinner = new JSpinner (new SpinnerNumberModel (3, 3, 5, 1));
        this.mSpinner.addChangeListener (TicTacToe.getInstance ());
        this.add (this.mSpinner);
        this.add (new JLabel ("k: "));
        this.kSpinner = new JSpinner (new SpinnerNumberModel (3, 3, 5, 1));
        this.kSpinner.addChangeListener (TicTacToe.getInstance ());
        this.add (this.kSpinner);

        this.add (new JLabel ("Moteur d'IA: "));
        this.add (this.getAiEnginePanel ());

        this.add (new JLabel ("Premier joueur: "));
        this.add (this.getFirstPlayerPanel ());
    }
    
    /**
     * @return Le premier joueur
     */
    public int getFirstPlayer ()
    {
        int firstPlayer = 0;
        if (this.firstButtons.get (1).isSelected ())
            firstPlayer = 1;
        return firstPlayer;
    }

    /**
     * @return Le moteur d'IA
     */
    public Player getAiPlayer ()
    {
        Player ai = null;
        int index = -1;
        for (int i = 0; i < this.aiButtons.size (); i++)
            if (this.aiButtons.get (i).isSelected ())
                index = i;
        if (index >= 0)
        {
           ai = this.aiButtons.get (index).getPlayer ();
        }
        return ai;
    }

    /**
     * @return Le nombre de lignes
     */
    public int getN ()
    {
        return (Integer) this.nSpinner.getValue ();
    }

    /**
     * @return Le nombre de colonnes
     */
    public int getM ()
    {
        return (Integer) this.mSpinner.getValue ();
    }

    /**
     * @return Le nombre de symboles à aligner
     */
    public int getK ()
    {
        int n = (Integer) this.nSpinner.getValue ();
        int m = (Integer) this.mSpinner.getValue ();
        int k = (Integer) this.kSpinner.getValue ();
        if ((k > n) && (k > m))
            k = Math.max (n, m);
        this.kSpinner.setValue (k);
        return k;
    }

    private JPanel getAiEnginePanel ()
    {
        JPanel aiEnginePanel = new JPanel ();
        Reflections reflections = new Reflections ("tictactoe.player");
        Set <Class <? extends AIPlayer>> subClasses = reflections.getSubTypesOf (AIPlayer.class);

        this.aiButtons = new ArrayList <AIRadioButton> ();
        for (Class <? extends Player> subClass : subClasses)
        {
            try
            {
                Player ai = (Player) subClass.getConstructors () [0].newInstance ();
                if (ai != null)
                    this.aiButtons.add (new AIRadioButton (ai.getName (), subClass));
            }
            catch (Exception e)
            {
            }
        }
        Collections.sort (this.aiButtons);
        this.aiButtons.get (0).setSelected (true);

        aiEnginePanel.setLayout (new GridLayout (1, this.aiButtons.size ()));
        ButtonGroup group = new ButtonGroup ();
        for (int i = 0; i < this.aiButtons.size (); i++)
        {
            group.add (this.aiButtons.get (i));
            aiEnginePanel.add (this.aiButtons.get (i));
            this.aiButtons.get (i).addActionListener (TicTacToe.getInstance ());
        }

        return aiEnginePanel;
    }

    private JPanel getFirstPlayerPanel ()
    {
        JPanel firstEnginePanel = new JPanel ();

        this.firstButtons = new ArrayList <JRadioButton> ();
        firstEnginePanel.setLayout (new GridLayout (1, 2));
        ButtonGroup group = new ButtonGroup ();
        JRadioButton youButton = new JRadioButton ("Joueur");
        youButton.setSelected (true);
        group.add (youButton);
        firstEnginePanel.add (youButton);
        this.firstButtons.add (youButton);
        youButton.addActionListener (TicTacToe.getInstance ());
        JRadioButton aiButton = new JRadioButton ("IA");
        aiButton.setSelected (true);
        group.add (aiButton);
        firstEnginePanel.add (aiButton);
        this.firstButtons.add (aiButton);
        aiButton.addActionListener (TicTacToe.getInstance ());

        return firstEnginePanel;
    }
}
