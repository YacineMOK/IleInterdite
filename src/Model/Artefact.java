package Model;

import java.util.Set;

public class Artefact {

	/******attributs**********/
	private Element e;

	/**
	 * Constructeur
	 * @param e
	 */
	public Artefact(Element e) {
		this.e = e;
	}


	/************ Getters **********/

	public Element getElement() {
		return e;
	}

	/*******Other methods *********/

	/**
	 * permet de recuperer un artefact depuis un coffre fort
	 * @param c
	 * @return
	 */
	public boolean getArtefact(CoffreFort c) {
		Set<Cle> s = c.getCles();
		for(Cle cle: s) {
			if(cle.getElementCle() == this.e) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Artefact{" +
				"Artefactelement=" + e +
				'}';
	}
}