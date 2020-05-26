package Utile;
import Model.*;
import Vue.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;


public class Jeu {
	public static int nbJoueur = 0;

	public static void main(String[] args){
	 	ClassFrame cl = new ClassFrame("Entrer");
	 }
	public static void lancerJeu() {
		   Grille grille = new Grille(nbJoueur);
		   Vue vue = new Vue(grille);
		   music();
	}
	public static void music(){

		try{
			File mymusic = new File("IleInterdite.wav");
			if(mymusic.exists()){
				AudioInputStream ai = AudioSystem.getAudioInputStream(mymusic);
				Clip clip = AudioSystem.getClip();
				clip.open(ai);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else{
				System.out.println("can't find file");
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void pdfReader() {

		try {

			File pdfFile = new File("tuto.pdf");
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					System.out.println("file not supported!");
				}

			} else {
				System.out.println("File doesn't exist!");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}