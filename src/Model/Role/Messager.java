package Model.Role;
import Model.Joueur;
import Model.Case;
import Model.Cle;


public class Messager extends Joueur {
	private Joueur j = null;

	/*********** Constructeur *********/
    public Messager(String pseudo, Case caseJoueur) {
        super(pseudo, Role.MESSAGER);
        this.setCase(caseJoueur);
    }

    /****** Getters *********/
    public Joueur getJdeplace() {
        return this.j;
    }

    /********* Setters *********/
	public void setJdeplace(Joueur j) {
    	this.j =j;
    }

    /**
     * permet de donner une clé donnée à un joueur
     * @param c la clé à donner
     */
    public void giveKey( Cle c) {
        if (this.getCoffreFort().hadCle(c)) {
            this.getCoffreFort().retirerCle(c.getElementCle());
            j.getCoffreFort().ajouterCle(c);
            this.decreaseAction();
        }
    }


}