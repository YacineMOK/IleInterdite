package Vue;

import Model.Grille;

import javax.swing.*;
import java.awt.*;

public class RootPane extends JPanel{
    /***************** Attributs ******************/
    private static int TAILLE;
    private ImageIcon bg = new ImageIcon("./images/background.png");
    public static boolean win ;
    private ImageIcon winningScreen = new ImageIcon("./images/Win.png");

    /***************** Constructeur ******************/
    /**
     * Constructeur de RootPane
     * @param taille : côté d'une zone
     */
    public RootPane(int taille){
        TAILLE = taille;
        Dimension dim = new Dimension(TAILLE * (Grille.LARGEUR + 4), TAILLE * (Grille.HAUTEUR + 3));
        this.setPreferredSize(dim);
        this.win = false;

    }

    /***************** Méthode ******************/
    @Override
    public void paintComponent(Graphics g){
        super.repaint();
        g.drawImage(bg.getImage(), 0, 0, TAILLE * (Grille.LARGEUR + 4), TAILLE * (Grille.HAUTEUR + 3), 0, 0,bg.getImage().getWidth(null), bg.getImage().getHeight(null), null);
        if(win) {
        	this.removeAll();
            g.drawImage(winningScreen.getImage(), TAILLE*2,2* TAILLE, TAILLE *2* (Grille.LARGEUR + 4), TAILLE *2* (Grille.HAUTEUR + 3), 0, 0,bg.getImage().getWidth(null), bg.getImage().getHeight(null), null);
        }
    }
}