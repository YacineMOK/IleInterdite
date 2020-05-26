package Model;
import java.util.ArrayList;


import static Model.EtatZone.*;

public class Case  {

    /***************** Attributs ******************/
    private boolean heliport; // vrai si la case est un heliport faux sinon
    private ArrayList<Artefact> artefact;
    private EtatZone level = NORMALE;
    private int posx, posy;
    private Grille grille;
    private ArrayList<Joueur> joueurs = new ArrayList<>();


    /**************** Constructeur ****************/
    public Case(int posx, int posy, Grille grille){
        this.artefact = new ArrayList<>();
        this.posx = posx;
        this.posy = posy;
        this.grille = grille;
        this.heliport = false;
    }

    public Case(int x,int y){
        this.posx = x;
        this.posy = y;
    }

    /**************** Getters *******************/
    public int getPosx(){
        return posx;
    }

    public int getPosy(){
        return posy;
    }

    public ArrayList<Artefact> getArtefact(){
        return artefact;
    }

    public EtatZone getLevel(){
        return level;
    }

    public Joueur getJoueur() throws java.lang.NullPointerException{
    	if(joueurs.size() == 0) {
    		return null;
    	}else {
    	return this.joueurs.get(0);
    	}
    }

    public Grille getGrille (){return this.grille;}

    public Boolean isHeliport(){ return this.heliport;}

    /**************** Setters  *******************/
    public void setHeliport() {
        this.heliport = true;
    }

    public void setArtefact(Element e) {
        this.artefact.add(new Artefact(e));
    }

    public void setSituationZone(EtatZone etat){
        this.level = etat;
    }

    public void setJoueurs(Joueur j){
        this.joueurs.add(j);
    }



    /****************Other methods *******************/

    /**
     * Méthode qui permet d'assecher une case inondee
     */
    public void downLevel() throws Exception {
        if(this.level == SUBMERGEE){
            System.out.println("La case ne peut pas être assechée");
        }
        else if(this.level == INONDEE) {
            this.level = NORMALE;
        }
        else {
            throw new Exception("Case sèche");
        }
    }

    /**
     * Méthode qui permet de monter le niveau d'eau d'une case,
     * Ne fait rien si cette dernière est déjà submergée
     */
    public void upLevel(){
        switch (this.level) {
            case NORMALE : this.level = INONDEE; break;
            default: this.level = SUBMERGEE;
                break;
        }
    }

    /**
     * Permet d'enlever l'artefacter associer à la case
     * @param o
     */
    public void removeArtefact(Artefact o){
        this.artefact.remove(o);
    }

    /**
     * Enlever le joueur s'il se trouve sur la case
     * @param j
     */
    public void removeJoueur(Joueur j){
    	joueurs.remove(j);
    }

    /**
     * Enlever tous les joueurs se trouvant sur la case
     */
    public void removeAllJoueur(){

    }




}
