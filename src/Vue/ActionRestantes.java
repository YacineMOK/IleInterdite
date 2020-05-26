package Vue;

import Controller.Mouse;
import Model.Grille;
import Model.Joueur;
import Model.Role.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ActionRestantes extends JPanel {
    /***************** Attributs ******************/
    private ImageIcon[] imageTour = {
            new ImageIcon("./images/tours/0.png"),
            new ImageIcon("./images/tours/1.png"),
            new ImageIcon("./images/tours/2.png"),
            new ImageIcon("./images/tours/3.png")
    };
    public static int TAILLE = 70;
    private static Grille GRILLE;
    private ArrayList<Role> roles_joueurs = new ArrayList<>();

    /***************** Constructeur ******************/
    /**
     * @param grille (L'ensemble des zones du plateau)
     */
    public ActionRestantes(Grille grille){
        GRILLE = grille;
        HashMap<String, Joueur> lis = grille.getListJoueur();
        for (Joueur j: lis.values()) {
            this.roles_joueurs.add(j.getRole());
        }
        Dimension dim = new Dimension(TAILLE * (Grille.LARGEUR + 4), TAILLE);
        this.setPreferredSize(dim);
    }

    /***************** Méthode ******************/
    /**
     * Fonctions qui charge l'image correspondant au rôle de chaque joueur
     * @param g Objet Graphique
     * @return l'image du role du joueur dont c'est le tour !
     */
    public ImageIcon role(Graphics g){
        ImageIcon im = new ImageIcon();
        switch(this.roles_joueurs.get(Mouse.getTour())){
            case INGENIEUR: im = new ImageIcon("./images/roles/ingenieur.png"); break;
            case EXPLORER: im = new ImageIcon("./images/roles/explorateur.png"); break;
            case NAVIGATEUR: im = new ImageIcon("./images/roles/navigateur.png"); break;
            case MESSAGER: im = new ImageIcon("./images/roles/messager.png"); break;
            case PILOTE: im = new ImageIcon("./images/roles/pilote.png"); break;
            case PLONGEUR: im = new ImageIcon("./images/roles/plongeur.png"); break;
        }
        return im;
    }

    @Override
    public void paintComponent(Graphics g){
        super.repaint();
        ImageIcon im = imageTour[Mouse.getActionsRestantes()];
        g.drawImage(im.getImage(), TAILLE * (Grille.LARGEUR + 3), 0, TAILLE * (Grille.LARGEUR + 4), TAILLE, 0, 0,im.getImage().getWidth(null), im.getImage().getHeight(null), null);
        ImageIcon im2 = role(g);
        g.drawImage(im2.getImage(), 2*TAILLE, 0, 8*TAILLE, TAILLE, 0,0, im2.getImage().getWidth(null), im2.getImage().getHeight(null), null);
    }
}