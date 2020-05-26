package Vue;

import Controller.Bouton;
import Controller.Echange;
import Controller.Exit;
import Model.Grille;

import javax.swing.*;

import java.awt.*;

public class Vue extends JFrame {
    /***************** Attributs ******************/
    // Ma fenêtre JFrame
    private JFrame frame;
    // Mon ContentPane (Mon plateau)
    private ContentPane panel;
    // Mon RootPane (Le fond qui va contenir tous les éléments graphique)
    private RootPane rootPane;
    // Icone de l'application
    private static ImageIcon icon = new ImageIcon("./images/icon.jpeg");

    /***************** Constructeur ******************/
    public Vue(Grille maGrille) {
        super();
        // Déclarer une fenêtre
        frame = new JFrame();
        // Nom de l'application
        frame.setTitle("Ile Interdite");
        // Icon
        frame.setIconImage(icon.getImage());
        // Le Layout
        frame.setLayout(new FlowLayout());
        // Mon background (rootpane)
        rootPane = new RootPane(70);

        panel = new ContentPane(maGrille);
        maGrille.addObserver(panel);
        // On met le layout en BorderLayout
        rootPane.setLayout(new BorderLayout());
        // On l'ajoute à notre fenêtre
        frame.add(rootPane);
        OptionsView optview = new OptionsView(maGrille);
        maGrille.addObserver(optview);
        // On ajoute tous les éléments graphique à notre rootPane
        rootPane.addKeyListener(new Exit());
        rootPane.addKeyListener(new Echange(maGrille));
        rootPane.setFocusable(true);
        rootPane.add(panel, BorderLayout.CENTER);
        rootPane.add(new Tour(), BorderLayout.NORTH);
        rootPane.add(optview, BorderLayout.EAST);
        rootPane.add(new Options(), BorderLayout.WEST);
        rootPane.add(new ActionRestantes(maGrille), BorderLayout.SOUTH);
        // Un bouton
        frame.add(new Bouton(maGrille));
        frame.pack();
        // Pour pouvoir fermer la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Afficher la fenêtre
        frame.setVisible(true);
    }

}