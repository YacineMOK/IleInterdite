package Vue;

import Controller.Mouse;
import Model.Grille;

import javax.swing.*;
import java.awt.*;

public class Tour extends JPanel {
    /***************** Attributs ******************/
    private ImageIcon[] imageTour = {
            new ImageIcon("./images/cest_a_toi_bleu.png"),
            new ImageIcon("./images/cest_a_toi_rouge.png"),
            new ImageIcon("./images/cest_a_toi_vert.png"),
            new ImageIcon("./images/cest_a_toi_orange.png")
    };
    private ImageIcon bandrol = new ImageIcon("./images/bandrol_gold.png");
    public static int TAILLE = 70;

    /***************** Constructeur ******************/
    public Tour(){
        Dimension dim = new Dimension(TAILLE * (Grille.LARGEUR + 4), 2 * TAILLE);
        this.setPreferredSize(dim);
    }

    /***************** Méthode ******************/
    @Override
    public void paintComponent(Graphics g){
        super.repaint();
        int bordure = 10;
        g.drawImage(bandrol.getImage(), 2 * TAILLE, bordure, TAILLE * 8, 2 * TAILLE - bordure, 0, 0,bandrol.getImage().getWidth(null), bandrol.getImage().getHeight(null), null);
        // Peut-etre le remplacer avec un grille.getTour() avec grille en @param
        ImageIcon im = imageTour[Mouse.getTour()];
        bordure = 50;
        g.drawImage(im.getImage(), 3 * TAILLE, bordure, TAILLE * 7, 2 * TAILLE - bordure, 0, 0,im.getImage().getWidth(null), im.getImage().getHeight(null), null);
    }
}