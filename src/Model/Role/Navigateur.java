package Model.Role;
import Model.Joueur;
import Model.Case;

public class Navigateur extends Joueur {
    /**attribut**/
	private Joueur playerToMove = null;

	/**Constructeur**/
    public Navigateur(String pseudo,Case c ) {
        super(pseudo, Role.NAVIGATEUR);
        this.setCase(c);
    }

    /** Getters **/
    public Joueur getJdeplace() {
    	return playerToMove;
    }

    /** Setters**/
    public void setJdeplace(Joueur j) {
    	this.playerToMove =j;
    }


}