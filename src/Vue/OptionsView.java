package Vue;

import Model.*;
import Utile.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Set;

public class OptionsView extends JPanel implements Observer {
    /***************** Attributs ******************/
    public static int TAILLE = 70;
    private ImageIcon im = new ImageIcon("./images/inventaire.png");
    private static boolean show = false;
    private static boolean arte = false;
    private HashMap<String, Joueur> listJoueur;
    private ImageIcon[] players = {
            new ImageIcon("./images/joueur.png"),
            new ImageIcon("./images/joueur2.png"),
            new ImageIcon("./images/joueur3.png"),
            new ImageIcon("./images/joueur4.png")
    };
    private Grille grille;
    private HashMap<String, ImageIcon> artefacts_images = new HashMap<>();

    /***************** Constructeur ******************/
    public OptionsView(Grille grille){
    	this.grille = grille;
        this.listJoueur = grille.getListJoueur();
        Dimension dim = new Dimension(2 * TAILLE, TAILLE * Grille.LARGEUR);
        this.setPreferredSize(dim);
        this.artefacts_images.put("AIR", new ImageIcon("./images/AIR.png"));
        this.artefacts_images.put("EAU", new ImageIcon("./images/EAU.png"));
        this.artefacts_images.put("FEU", new ImageIcon("./images/FEU.png"));
        this.artefacts_images.put("TERRE", new ImageIcon("./images/TERRE.png"));
    }
    /***************** Méthode ******************/
    /**
     * Méthode qui affiche/cache l'inventaire (ensemble des clés que les joueurs posèdent)
     */
    public static void switchShow(){
        show = !show ;
        arte = false;
    }

    /**
     * Méthode qui affiche/cache les artefacts ramassés durant la partie
     */
    public static void switchArte(){
        arte = !arte ;
        show = false;
    }

    /**
     * Méthode qui permet de déssiner et d'afficher l'inventaire (ensemble des clés que les joueurs posèdent
     * @param g Objet graphique fourni par le programme
     */
    private void inventaire(Graphics g){
        int i; int num_joueur = -1;
        int ligne = 0;
        for (Joueur j: this.listJoueur.values()) {
            num_joueur++;
            ligne += 38;
            i = 36;
            g.drawImage(players[num_joueur].getImage(), i, ligne , i + 26, ligne + 26, 0, 0, players[num_joueur].getImage().getWidth(null), players[num_joueur].getImage().getHeight(null), null );
            ligne += 38;
            for (Cle key: j.getCoffreFort().getCles()) {
                switch (key.getElementCle()) {
                    case AIR: {
                        g.setColor(Color.ORANGE);
                        g.drawString("A", i, ligne);
                        break;
                    }
                    case EAU: {
                        g.setColor(Color.CYAN);
                        g.drawString("E", i, ligne);
                        break;
                    }
                    case FEU: {
                        g.setColor(Color.RED);
                        g.drawString("F", i, ligne);
                        break;
                    }
                    case TERRE: {
                        g.setColor(Color.GRAY);
                        g.drawString("T", i, ligne);
                        break;
                    }
                }
                i += 10 ;
            }
        }
    }

    /**
     * Méthode qui permet de déssiner et d'afficher l'ensemble des artefacts colléctés par les joueurs
     * @param g Objet graphique fourni par le programme
     */
    private void artefact(Graphics g){
        Set<Artefact> arte = CoffreFort.getArtefacts();
        ImageIcon im; int i = 0; int dec = 65;
        int deb = 25; int w = 25;
        for (Artefact a: arte) {
            i++;
            switch (a.getElement()){
                case TERRE: im = this.artefacts_images.get("TERRE"); break;
                case FEU: im = this.artefacts_images.get("FEU"); break ;
                case EAU: im = this.artefacts_images.get("EAU"); break;
                default: im = this.artefacts_images.get("AIR"); break;
            }
            g.drawImage(im.getImage(), w, deb,25 + w, 2 * deb, 0, 0, im.getImage().getWidth(null), im.getImage().getHeight(null),null);
            w += 25;
            if( w >= 110 ) {deb += 20; w = 10;}
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.repaint();
        if(show){
            g.drawImage(im.getImage(), 0, 0, 2 * TAILLE, TAILLE * Grille.LARGEUR, 0, 0,im.getImage().getWidth(null), im.getImage().getHeight(null), null);
            inventaire(g);
        }
        if(arte){
            g.drawImage(im.getImage(), 0, 0, 2 * TAILLE, TAILLE * Grille.LARGEUR, 0, 0,im.getImage().getWidth(null), im.getImage().getHeight(null), null);
            artefact(g);
        }
    }

    /**
     * Design Pattern
     */
	@Override
	public void update() {
		this.listJoueur = this.grille.getListJoueur();
		
	}
}