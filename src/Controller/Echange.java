package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Cle;
import Model.Grille;

public class Echange implements KeyListener {
    
	/***************** Attributs ******************/
	private Grille grille;
	
	
    /**************** Constructeur ****************/
	public Echange(Grille grille) {
		this.grille = grille;
	}
	
	
	/********Touche pour Echange E(EAU), A(AIR), F(FEU), T(TERRE)*********/	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int i = arg0.getKeyCode();
		Cle c = null; 
		switch(i) {
		case KeyEvent.VK_E: c = Cle.CLEEAU;break;
		case KeyEvent.VK_F: c = Cle.CLEFEU;break;
		case KeyEvent.VK_T: c = Cle.CLETERRE;break;
		case KeyEvent.VK_A: c = Cle.CLEAIR;break;
		default : ;
		}
		if(c != null) {
			this.grille.echangeCle(c);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
