package tictactoe.gui;

import javax.swing.JRadioButton;

import tictactoe.player.Player;

/**
 * @author Alexandre Blansché
 * Bouton de sélection du moteur d'IA
 */
public class AIRadioButton extends JRadioButton implements Comparable <AIRadioButton>
{
    private Class <? extends Player> playerClass;
    
    /**
     * @param name Label du bouton
     * @param playerClass Classe d'IA à instancier
     */
    public AIRadioButton (String name, Class <? extends Player> playerClass)
    {
        super (name);
        this.playerClass = playerClass;
    }
    
    /**
     * @return Retourne l'instance d'une IA
     */
    public Player getPlayer ()
    {
        Player player = null;
        try
        {
            player = (Player) this.playerClass.getConstructors () [0].newInstance ();
        }
        catch (Exception e)
        {
        }
        return player;
    }

    @Override
    public int compareTo (AIRadioButton arg0)
    {
        return this.getText ().compareTo (arg0.getText ());
    }
}
