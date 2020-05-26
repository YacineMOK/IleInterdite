package Vue;

import Controller.OptionsActions;
import Model.Grille;

import javax.swing.*;
import java.awt.*;

public class Options extends JPanel {
    /***************** Attributs ******************/
    public static int TAILLE = 70;
    private ImageIcon coffrefort = new ImageIcon("./images/safe.png");
    private ImageIcon artefact = new ImageIcon("./images/artefact_element.png");

    /***************** Constructeur ******************/
    public Options(){
        Dimension dim = new Dimension(2 * TAILLE, TAILLE * Grille.LARGEUR);
        this.setPreferredSize(dim);
        addMouseListener(new OptionsActions());
    }

    /***************** Méthode ******************/
    @Override
    public void paintComponent(Graphics g){
        super.repaint();
        int dec = 20;
        g.drawImage(coffrefort.getImage(), 0, 0, TAILLE, TAILLE, 0, 0,coffrefort.getImage().getWidth(null) - dec, coffrefort.getImage().getHeight(null), null);
        g.drawImage(artefact.getImage(), 0, 2* TAILLE, TAILLE, 3*TAILLE, 0, 0,artefact.getImage().getWidth(null), artefact.getImage().getHeight(null), null);
    }
}