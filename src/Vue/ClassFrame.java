package Vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import Utile.Jeu;

public class ClassFrame extends JFrame {
    /***************** Attributs ******************/
    public static JButton fourPlayer = new JButton("4 Joueurs");
    public static JButton threePlayer = new JButton("3 Joueurs");
    public static JButton twoPlayer = new JButton("2 Joueurs");
    public static JButton onePlayer = new JButton("1 Joueur");
    private static JFrame frame;
    // Mon ContentPane
    private static ContentPane panel;
    // Mon RootPane
    private static RootPane rootPane;
    // Icone de l'application
    private static ImageIcon icon = new ImageIcon("./images/icon.jpeg");

    /***************** Constructeur ******************/
    public ClassFrame(String title) {
    	super();
    	 // Déclarer une fenêtre
        frame = new JFrame();
        // Nom de l'application
        frame.setTitle("Ile Interdite");
        fourPlayer.setPreferredSize(new Dimension(100,100));
        threePlayer.setPreferredSize(new Dimension(100,100));
        twoPlayer.setPreferredSize(new Dimension(100,100));
        onePlayer.setPreferredSize(new Dimension(100,100));
        // Icon
        frame.setIconImage(icon.getImage());
        // Le Layout
        // Mon background (rootpane)
        frame.setLayout(new BorderLayout());
        Menu rootPane = new Menu(50);
        rootPane.setLayout(new BorderLayout());
        frame.add(rootPane);
        onePlayer.setBackground(new Color(59,212,249));
        twoPlayer.setBackground(new Color(232,68,68));
        threePlayer.setBackground(new Color(75,231,87));
        fourPlayer.setBackground(new Color(248,162,55));
        //ADD COMPONENTS TO CONTENT PANE        
        frame.add(fourPlayer, BorderLayout.SOUTH);
        frame.add(threePlayer,BorderLayout.EAST);
        frame.add(twoPlayer,BorderLayout.WEST);
        frame.add(onePlayer,BorderLayout.NORTH);
        frame.pack();
        // Pour pouvoir fermer la fenêtre
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Afficher la fenêtre
        frame.setVisible(true);
        ClassFrame.fourPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Jeu.nbJoueur = 4;
                	Jeu.lancerJeu();
                	frame.removeAll();
            }
        });
	 ClassFrame.threePlayer.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
                    Jeu.nbJoueur = 3;
                    Jeu.lancerJeu();
                    frame.removeAll();
	            }
	        });
	 ClassFrame.twoPlayer.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
                    Jeu.nbJoueur = 2;
                    Jeu.lancerJeu();
                    frame.removeAll();
	            }
	        });
	 ClassFrame.onePlayer.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
                    Jeu.nbJoueur = 1;
                    Jeu.lancerJeu();
                    frame.removeAll();
                }
	        });
	    }
}