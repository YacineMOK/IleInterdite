package Controller;

import Model.Grille;
import Model.Joueur;
import Utile.Jeu;
import Vue.ContentPane;
import Vue.Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Bouton extends JPanel{
	
	/**************** Constructeur ****************/	
    public Bouton(Grille grille){
         /*Initialise le Bouton Fin de Tour*/
        JButton bouton = new JButton("Fin de tour"); 
        bouton.setPreferredSize(new Dimension(150,40));
        this.add(bouton);

        bouton.addActionListener(e -> {
            try {
                grille.tourCaseI();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Mouse.boutonEffect();
            grille.printCoffre();
            if(grille.partiePerdue()) {
                JOptionPane.showMessageDialog(this, "Game Over, you lost :) ", "", JOptionPane.YES_NO_OPTION);
            }
        }
        );
        
    	/**************** Touche pour fin de tour ENTER ****************/	
        InputMap im = bouton.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = bouton.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "clickMe");
        am.put("clickMe", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.doClick();
            }
        });
        

        /*Initialise le Bouton Ramasser*/
        JButton ramasse = new JButton("Tresor");
        ramasse.setPreferredSize(new Dimension(150,40));
        this.add(ramasse);
        
        ramasse.addActionListener(e->{
            grille.ramasseArtefact();
        });

        /**************** Touche pour ramasser P ****************/	

        InputMap im2 = ramasse.getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am2 = ramasse.getActionMap();
        im2.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "clickMe");
        am2.put("clickMe", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.doClick();
            }
        });

        

        /************* Rejouer la partie **************/
        JButton rejouer = new JButton("Rejouer");
        ramasse.setPreferredSize(new Dimension(150,40));
        rejouer.setBackground(Color.RED);


        rejouer.addActionListener(e -> {
            for(Frame jf : Vue.getFrames()){
                jf.dispose();
            }
            Utile.Jeu.lancerJeu();
        }
        );
        /**************************** tuto ************/
        JButton tuto = new JButton("tuto");
        bouton.setPreferredSize(new Dimension(150,40));
        tuto.setBackground(Color.PINK);
        tuto.addActionListener(e->{
            Jeu.pdfReader();});

        this.add(bouton);
        this.add(rejouer);
        this.add(tuto);
        setVisible(true);
        
        
    	
    }


   



}