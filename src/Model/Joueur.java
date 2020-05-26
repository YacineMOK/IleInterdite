package Model;

import java.util.ArrayList;

import java.util.Random;
import Model.Role.*;
import static Model.Cle.*;

public class Joueur {
    private String pseudo;
    private Case caseJoueur;
    private Role role;
    private boolean tour ;
    private CoffreFort coffreFort = new CoffreFort();
    private int actionsRestantes = 3;

    /**
     * Constructeur
     * @param pseudo : nom du joueur
     * @param role : le role que porte pendant la partie
     * @param caseJoueur : la case où doit se trouver le joueur
     * @param tour : indique si c'est le tour du joueur
     */
    public Joueur(String pseudo, Role role, Case caseJoueur, boolean tour){
        this.pseudo = pseudo;
        this.caseJoueur = caseJoueur;
        this.role = role;
        this.tour = tour;
        this.coffreFort = new CoffreFort();
    }

    /**
     * Constructeur 2
     * @param pseudo : nom du joueur
     * @param role  : son role
     */
    public Joueur(String pseudo, Role role){
        this.pseudo = pseudo;
        this.role = role;
    }


    /*** Getters ***/

    public int getPosx(){
        return this.caseJoueur.getPosx();
    }

    public int getPosy(){
        return this.caseJoueur.getPosy();
    }

    public String getPseudo(){
        return this.pseudo;
    }

    public Case getCaseJoueur(){return this.caseJoueur;}

    public Role getRole() { return this.role; }

    public boolean isTour() {
        return tour;
    }

    public CoffreFort getCoffreFort(){
        return this.coffreFort;
    }

    public int getActionsRestantes() {
        return actionsRestantes;
    }


    /*** Setters **/

    public void actionsRestantesReInit(){
        actionsRestantes = 3;
    }
    public void decreaseAction() {
        this.actionsRestantes--;
    }
    public void setRole(Role role){
        if (role!=Role.NAVIGATEUR || role!= Role.EXPLORER || role!=Role.INGENIEUR || role!=Role.MESSAGER || role!=Role.PILOTE || role!=Role.PLONGEUR){ return;}
        this.role = role;
    }

    public void setTour(boolean tour){
        this.tour=tour;
    }

    public void setCase(Case newCase){ this.caseJoueur = newCase; }




    /*** Other methods ***/

    /**
     * Permet d'innonde une case donnée
     * @param c la case à innonder
     */
    public void InnondeCase(Case c){   c.upLevel(); }

    /**
     * Permet d'assecher une case donnée
     * @param c la case à assecher
     * @throws Exception
     */
    public void AssecherCase(Case c) throws Exception {
        if(c.getLevel()!=EtatZone.SUBMERGEE){
            c.downLevel();
            this.decreaseAction();
        }
        else{
            return;
        }
    }


    /**
     * AJouter une cle au coffre fort du joueur
     * @param nouvelleCle
     */
    public void ajouterCle(Cle nouvelleCle) {
        this.coffreFort.ajouterCle(nouvelleCle);
    }

    /**
     * Retirer une clé associée à un element donnée au coffre fort du joueur
     * @param elementArtefact
     */
    public void retirerCle(Element elementArtefact) {
        this.coffreFort.retirerCle(elementArtefact);
    }


    /**
     * renvoit le nombre de clé qu'un joueur a et qui correspond à un element donné
     * @param element
     * @return
     */
    public int avoirToutesCles(Element element) {
        int cpt=0;
        for(Cle cle:coffreFort.getCles()   ){
            if(cle.getElementCle().equals(element))
            { cpt++;}
        }
        return cpt;
    }


    /**
     * permet de deplacer la case du joueur vers une case donnée
     * @param c
     */
    public void deplacer(Case c) {
        if(c.getLevel()!=EtatZone.SUBMERGEE){this.setCase(c); this.decreaseAction();}
        else{ return;}
    }


    /**
     * ajouter ou pas une clé aléatoirement au coffre fort du joueur
     */
    public  void recevoirCle(){
        Random r = new Random();
        int cle = r.nextInt(5);
        switch(cle){
            case 1 : ajouterCle(CLEAIR); break;
            case 2 : ajouterCle(CLEEAU); break;
            case 3 : ajouterCle(CLEFEU); break;
            case 4 : ajouterCle(CLETERRE); break;
            default:
                System.out.println("No key to add");
        }
    }

    /**
     * Permet de ramasser un artefact
     */
    public void ramasseArtefact() {
        Case c = this.caseJoueur;
        ArrayList<Artefact> listArte = c.getArtefact();
        CoffreFort coffre = this.coffreFort;
        ArrayList<Artefact> recupArte = new ArrayList<>(); // Evite les exceptions du type java.util.ConcurrentModificationException!!!
        for(Artefact a : listArte) {
            if(coffre.retirerCle(a.getElement())) {
            	CoffreFort.ajouterArte(a.getElement());
                recupArte.add(a);
            }
        }
        for(Artefact a : recupArte) {
            c.removeArtefact(a);
        }
    }


    /**
     * controle les deplacements autorisés au joueur
     * @param mouseX coordonnée en x du click
     * @param mouseY coordonnée en y du click
     * @param JX coordonnée en x du joueur
     * @param JY coordonnée en y du joueur
     * @return
     */
    public boolean clicUtile(int mouseX, int mouseY, int JX, int JY) {
        // Un pas suivant l'axe des X
        if((mouseX == JX + 1|mouseX == JX - 1)&(mouseY == JY)){
            return true;
        }
        // Un pas suivant l'axe des Y
        if((mouseY == JY + 1|mouseY == JY - 1)&(mouseX == JX)){
            return true;
        }
        if((mouseX==JX) && (mouseY==JY)){
            return true;
        }
        return false;
    }


    /**
     * Permet de deplacer un joueur qui sera fonctionnelle lorsque le joueur est un navigateur
     * @param newCase la nouvelle case du joueur à deplacer
     * @param j le joueur à deplacer
     */
    public void deplacerJoueur(Case newCase,Joueur j){
        System.out.println("nothing to do");
    }

    /**
     * Augmente les actions restantes d'une unité
     */
    public void addActionRestante(){
        this.actionsRestantes++;
    }

    /**
     * Methode de sechage de case, fonctionnelle lorsque le joueur est un ingenieur
     * @param i
     */
    public void methIngenieur(Ingenieur i){
        i.setAsseche();
    }

}