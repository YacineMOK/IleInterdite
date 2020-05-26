package Vue;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import Model.Grille;
import javax.swing.*;


public class Menu extends JPanel{
	/***************** Attributs ******************/
	    private static int TAILLE;
	    private ImageIcon bg = new ImageIcon("./images/Menu.PNG");

	/***************** Constructeur ******************/
	    /**
	     * Constructeur de RootPane
	     * @param taille : côté d'une zone
	     */
	    public Menu(int taille){
	        TAILLE = taille;
	        Dimension dim = new Dimension(TAILLE * (Grille.LARGEUR + 4), TAILLE * (Grille.HAUTEUR + 3));
	        this.setPreferredSize(dim);
	    };

	/***************** Méthode ******************/
	    @Override
	    public void paintComponent(Graphics g){	
	        super.repaint();
	        g.drawImage(bg.getImage(), 0, 0, TAILLE * (Grille.LARGEUR + 4), TAILLE * (Grille.HAUTEUR + 3), 0, 0,bg.getImage().getWidth(null), bg.getImage().getHeight(null), null);
	    }
}