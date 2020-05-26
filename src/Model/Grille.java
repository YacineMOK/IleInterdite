package Model;
import Vue.RootPane;

import java.util.*;
import java.util.List;
import Utile.Observable;
import static Model.EtatZone.*;
import static Model.Role.Role.*;
import Model.Role.*;


public class Grille extends Observable {
    /** Attributs **/
    public static final int HAUTEUR = 6, LARGEUR = 6, nbArte = 3;
    private Case[][] tabCase;
    private HashMap<String,Joueur> listJoueur;
    private Random random = new Random();
    private int tour;
    private int nbJoueur;
    // stockera la liste des personnages, comme ça leur du replay les utilisateurs auront les mêmes personnages
    public ArrayList<Joueur>  personnages = new ArrayList<>(); 


    /**
     * Constructeur.
     * @param nbJoueur  : le nombre de joueurs contenus dans la grille
     **/
    public Grille(int nbJoueur) {
        tabCase = new Case[LARGEUR][HAUTEUR];
        this.nbJoueur = nbJoueur;

        this.listJoueur = new HashMap<>();

        for (int i = 0; i < LARGEUR ; i++) {
            for (int j = 0; j < HAUTEUR ; j++) {
                tabCase[i][j] = new Case(i, j, this);
            }
        }
     
        //ajout aléatoire des personnages
        List<Integer> l = new ArrayList<Integer>();
        l.add(1);l.add(2); l.add(3); l.add(4);l.add(5);
        for(int i=0; i<nbJoueur; i++) {
            Case caseJoueur = tabCase[random.nextInt(LARGEUR-1)][random.nextInt(HAUTEUR-1)];
            int per = l.get(random.nextInt(l.size()));
            setPersonnage("P"+i,per,caseJoueur);
            
            l.remove(l.indexOf(per));
        }
        addPersonnage();
        
        tabCase[3][5].setHeliport();
        int x,y;
        Element e = null;

        for(int i=0; i<4; i++) {
            switch(i) {
                case 0: e=Element.FEU;break;
                case 1: e=Element.AIR;break;
                case 2: e=Element.EAU;break;
                case 3: e=Element.TERRE;break;
            }
            for(int j=0; j<Grille.nbArte; j++) {
                x= this.random.nextInt(LARGEUR);
                y= this.random.nextInt(HAUTEUR);
                tabCase[x][y].setArtefact(e);

            }
        }
        return;
    }


    /***Getters***/

    public HashMap<String,Joueur> getListJoueur(){
        return this.listJoueur;
    }
    /*
     * Get un joueur de la liste des joueurs
     * @param p
     * @return
     */
    public Joueur getJoueur(String p){
        return this.listJoueur.get(p);
    }
    public Case getHeliport(){ return tabCase[3][5];}
    public Case getCase(int x,int y) {
        for (int i = 0; i < LARGEUR ; i++) {
            for (int j = 0; j < HAUTEUR ; j++) {
                if (i==x && j==y) {
                    return tabCase[i][j];
                }
            }
        }
        System.out.println("Zone introuvable...");
        return null;
    }
    public int getTour(){
        return this.tour;
    }




    /*** Others Methods ***/
    /**
     * Permet de reinitialiser la grille afin de rejouer à la partie
     */
    public void reset() {
    	if(!RootPane.win) {
    		 tabCase = new Case[LARGEUR][HAUTEUR];
    		 this.listJoueur = new HashMap<>();

	        for (int i = 0; i < LARGEUR ; i++) {
	            for (int j = 0; j < HAUTEUR ; j++) {
	                tabCase[i][j] = new Case(i, j, this);
	            }
	        }
    	     
    	        //ajout aléatoire des personnages
    	        List<Integer> l = new ArrayList<Integer>();
                 l.add(0); l.add(1);l.add(2); l.add(3); l.add(4);l.add(5);
    	        for(int i=0; i<nbJoueur; i++) {
    	            Case caseJoueur = tabCase[random.nextInt(LARGEUR-1)][random.nextInt(HAUTEUR-1)];
    	            int per = l.get(random.nextInt(l.size()));
    	            setPersonnage("P"+i,per,caseJoueur);
    	            l.remove(l.indexOf(per));
    	        }
    	        addPersonnage();
    	        
    	        tabCase[3][5].setHeliport();
    	        int x,y;
    	        Element e = null;

    	        for(int i=0; i<4; i++) {
    	            switch(i) {
    	                case 0: e=Element.FEU;break;
    	                case 1: e=Element.AIR;break;
    	                case 2: e=Element.EAU;break;
    	                case 3: e=Element.TERRE;break;
    	            }
    	            for(int j=0; j<Grille.nbArte; j++) {
    	                x= this.random.nextInt(LARGEUR);
    	                y= this.random.nextInt(HAUTEUR);
    	                tabCase[x][y].setArtefact(e);

    	            }
    	        }
	        this.notifyObservers();
    	}
    }
    

    /**
     * Avoir les cases non submergées de la grille
     * @return
     */
    public List<Case> zoneNonSubmergees() {
        List<Case> result = new ArrayList<>();
        Case c;
        for (int i = 0; i < LARGEUR ; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                c = this.tabCase[i][j];
                if (c.getLevel() != SUBMERGEE) {
                    result.add(c);
                }
            }
        }
        return result;
    }

    /**
     * Test si les joueurs sont tous à l'heliport
     * @return
     */
    private boolean allAtHeliport() {
    	for(Joueur j : this.listJoueur.values()) {
    		if(j.getCaseJoueur() != this.getHeliport()) {
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Test si la partie est gagnée
     */
    public void endGame() {
    	if(CoffreFort.endGame() && this.allAtHeliport()) { 
            RootPane.win = true;
    	}
    }
    

    /**
     * Gerer un tour
     * @throws Exception
     */
    public void tourCaseI() throws Exception {
        List<Case> zoneNonSub = zoneNonSubmergees();
        if(!zoneNonSub.isEmpty()) {
            Case c;
            int x, y;
            for (int i = 0; i < 3; i++) {
                c = zoneNonSub.get(random.nextInt(zoneNonSub.size()));
                c.upLevel();
                x = c.getPosx();
                y = c.getPosy();
                this.tabCase[x][y] = c;
                if(c.getLevel() == EtatZone.SUBMERGEE) {
                    c.removeAllJoueur();
                }
                // On enlève la zone de la liste si elle est submergée
                zoneNonSub.remove(c);

            }
        }
        Joueur joueur = this.getJoueur("P"+this.tour);
        joueur.recevoirCle();
        for(Joueur j : this.listJoueur.values()){
            if(j.getRole()==INGENIEUR){
                  j.methIngenieur((Ingenieur) j);
            }
        }
        nextTour();
        this.notifyObservers();
    }

    /**
     * reinitialiser les actions restantes d'un joueur
     */
    private void actionsRestantesReInit(){
        Joueur j=this.getJoueur("P"+this.tour);
        j.actionsRestantesReInit();
    }

    /**
     * gere l'effet du bouton "tour"
     */
    public void boutonEffect() {
        this.actionsRestantesReInit();
        this.getTour();
    }

    /**
     * gere les clicks souris
     * @param mouseX  position en x du click
     * @param mouseY  position y du click
     * @param leftClick  determine si le click est un click gauche
     * @param rightClick determine si le click est un click droit
     */
    public void mousePressed(int mouseX, int mouseY,boolean leftClick, boolean rightClick) {
        Joueur j =  this.getJoueur("P"+this.tour);
		int joueurX = j.getPosx();
		int joueurY = j.getPosy();
		if(j.getActionsRestantes() > 0) {
		     if(j.clicUtile(mouseX,mouseY,joueurX,joueurY)) {
		        if(rightClick && !(mouseX==joueurX && mouseY == joueurY)) {
		            this.movePlayer(mouseX, mouseY, j);
		            this.endGame();
		
		        }else {
                    if(leftClick) {
                        try {
                        	
                                 j.AssecherCase(this.getCase(mouseX, mouseY));
                            }
                            	
                            catch(Exception ex) {
                            	System.out.println("case sèche");
                            }
		            }
		        }
		    }
		}
		if(j.getActionsRestantes() == 0 && j.getRole()==INGENIEUR && leftClick) {
			if(((Ingenieur)j).getAssseche()) {
				try{
					j.AssecherCase(this.getCase(mouseX, mouseY));
				}catch(Exception ex){
					System.out.println("case ingé prob");
				}
			}
		}
    }


    /**
     * Gere les doubles click souris
     * @param mouseX  position en x du click
     * @param mouseY  position en y du click
     * @param joueurDeplace  position du joueur à deplacer
     * @param navigateur  le navigateur qui se charge de deplacer le joueur
     */
    private void doubleClicDeplace(int mouseX, int mouseY, Joueur joueurDeplace, Navigateur navigateur) {
		int joueurX = joueurDeplace.getPosx();
		int joueurY = joueurDeplace.getPosy();
	     if(joueurDeplace.clicUtile(mouseX,mouseY,joueurX,joueurY)) {
	        if(!(mouseX==joueurX && mouseY == joueurY)) {
	            this.movePlayer(mouseX, mouseY, joueurDeplace);
	            this.endGame();
	            navigateur.decreaseAction();
	        }
		    
		}
    }

    /**
     * deselctionne le joueur à deplacer
     */
    public void removeJoueurDeplace() {
    	Joueur j = this.getJoueur("P"+this.tour);
    	if(j.getRole() == NAVIGATEUR  ) {
          	Navigateur navigateur = (Navigateur) j;
          	navigateur.setJdeplace(null);
    	}
    }

    /**
     * gerer le deplacement d'un joueur en click
     * @param mouseX position en x du click
     * @param mouseY postiion en y du click
     */
    public void mouseDoublePressed(int mouseX, int mouseY) {
        Joueur j =  this.getJoueur("P"+this.tour); 
        if(j.getRole() == MESSAGER) {
            Messager messager = (Messager) j;
            try {
                Joueur jDeplace=this.getCase(mouseX, mouseY).getJoueur();
                messager.setJdeplace(jDeplace);
                    System.out.println(messager.getPseudo() + "pick"+jDeplace.getPseudo());
            }catch(NullPointerException e) {
                    System.out.println("Personne ici");

            }
        }
        if(j.getRole() == NAVIGATEUR && j.getActionsRestantes() > 0 ) {
              Navigateur navigateur = (Navigateur) j;
              if(navigateur.getJdeplace() != null) {
                  doubleClicDeplace(mouseX,mouseY,navigateur.getJdeplace(),navigateur);
              }else {
                  try {
                      Joueur jDeplace=this.getCase(mouseX, mouseY).getJoueur();
                      if(j != jDeplace) {
                          navigateur.setJdeplace(jDeplace);
                          System.out.println(navigateur.getPseudo() + "pick"+jDeplace.getPseudo());
                      }
                  }catch(NullPointerException e) {
                      System.out.println("Personne ici");
                  }
              }
        }
    }


    /**
     * Permet de gerer la fonctionnalité de donner une clé passée en param
     * @param c la clé à donner
     */
    public void echangeCle(Cle c) {
    	Joueur j = this.getJoueur("P"+this.tour);
    	if(j.getRole() == MESSAGER) {
    		Messager joueur = (Messager) j;
	    	Joueur autreJoueur= joueur.getJdeplace();
	    	joueur.setJdeplace(autreJoueur);
	    	if(autreJoueur != null) {
	    		joueur.giveKey(c);
	    	}
	    	this.notifyObservers();
    	}
    }


     /**
     * avoir les cases adjacentes d'une case donnée dans la grille
     * @param c
     * @return
     */
    public ArrayList<Case> getZoneAdjacentes(Case c) {
        ArrayList<Case> zonesAdjacentes = new ArrayList<>();
        if( c.getPosx()>0 && this.tabCase[c.getPosx()-1][c.getPosx()]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()-1][c.getPosx()]);
        if( c.getPosx()<LARGEUR+2 &&this.tabCase[c.getPosx()+1][c.getPosx()]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()+1][c.getPosx()]);
        if(c.getPosx()>0 &&this.tabCase[c.getPosx()][c.getPosx()-1]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()][c.getPosx()-1]);
        if( c.getPosx()<HAUTEUR+2 && this.tabCase[c.getPosx()][c.getPosx()+1]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()][c.getPosx()+1]);

        for (Case casee: zonesAdjacentes){
            if (casee.getLevel().equals(SUBMERGEE)){
                zonesAdjacentes.remove(casee);
            }
        }
        return zonesAdjacentes;
    }


    /**Test si la partie jouée est perdue
     * @return
     **/
    public boolean partiePerdue(){
        if(this.getHeliport().getLevel()==EtatZone.SUBMERGEE){
            return true;
        }
        for(Joueur j : this.listJoueur.values()){
            if(j.getCaseJoueur().getLevel().equals(EtatZone.SUBMERGEE) && j.getRole()!= PLONGEUR){
                removeJoueur(j);
                return true;
            }
        }
        return false;
    }

    /**
     * supprimer un joueur de la liste des joueurs
     * @param j
     */
    public void removeJoueur(Joueur j){
        this.listJoueur.remove(j);
        notifyObservers();
    }

    /**
     * supprimer tous les joueurs de la grille
     */
    public void removeAllJoueur(){
        this.listJoueur.clear();
        notifyObservers();
    }

    /**
     * permet d'ajouter un joueur à la liste des joueurs
     * @param jr
     */
    public void ajouterJoueur(Joueur jr){
        this.listJoueur.put(jr.getPseudo(),jr);
        for(int i=0;i<LARGEUR;i++){
            for(int j=0; j<HAUTEUR;j++){
                tabCase[i][j] = jr.getCaseJoueur();
            }
        }
        notifyObservers();

    }

    /**
     * gerer la succession de tour
     */
    private void nextTour() {
        tour+=1;
        if(tour>nbJoueur-1) {
            tour=0;
        }
    }

    @Override
    public String toString() {
        String s = new String();
        Case c;
        for (int i = 0; i < LARGEUR ; i++) {
            for (int j = 0; j < HAUTEUR ; j++) {
                c = this.tabCase[i][j];
                s += c.toString() + "\t ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * teste si une case donnée est un héliport
     * @param c la case à tester
     * @return
     */
    public boolean isHeliport(Case c) {
        return c.isHeliport();
    }


    /**
     * Permet de deplacer un joueur
     * @param x la coordonnée en x de la nouvelle case du joueur
     * @param y la coordonnée en y de la nouvelle de case du joueur
     * @param j le joueur à deplacer
     */
    private void movePlayer(int x, int y, Joueur j){
        try {
        	this.getCase(j.getPosx(), j.getPosy()).removeJoueur(j);
            j.deplacer(tabCase[x][y]);
        	this.getCase(x, y).setJoueurs(j);

        }catch (Exception e) {
            System.out.println("la case n'existe pas");
        }
        notifyObservers();
    }


    /**
     * Permet d'afficher un coffre
     */
    public void printCoffre() {
        Joueur j = this.getJoueur("P"+this.tour);
        CoffreFort coffre = j.getCoffreFort();
        System.out.println(j.getPseudo()+" "+j.getRole()+coffre.toString());
    }

    /**
     * permet à un joueur de ramasser un artefact
     */
    public void ramasseArtefact() {
        Joueur j = this.listJoueur.get("P"+this.tour);
        j.ramasseArtefact();
        this.endGame();
        this.notifyObservers();
    }


    /**
     * Teste si une case est contenue dans la grille
     * @param c la case à tester
     * @return
     */
    public boolean contains( Case c){
        for(int i=0;i<LARGEUR;i++){
            for(int j=0;j<HAUTEUR;j++){
                if(tabCase[i][j].equals(c)){
                    return true;
                }
            }
        }return false;
    }

    /**
     * Permet de distribuer les personnages dans la grille
     * @param p le pseudo
     * @param r
     * @param c
     */
    private void setPersonnage(String p,int r, Case c){
        Joueur j = null;
        switch (r){
            case 0 :   j=new Explorateur(p,c);   break;
            case 1 :   j=new Ingenieur(p,c); break;
            case 2 :   j=new Messager(p,c); break;
            case 3 :   j=new Navigateur(p,c); break;
            case 4 :   j=new Pilote(p,c); break;
            case 5 :   j=new Plongeur(p,c); break;
        }
        c.setJoueurs(j);
        this.listJoueur.put(p,j);
        System.out.println(p+" "+j.getRole());
    }

    /**
     * Ajouter les personnages attribués aux joueurs dans la liste personnages
     */
    private void addPersonnage(){
        for(Joueur j : this.listJoueur.values()){
            personnages.add(j);
        }
    }



    public static void main(String args[]) {
        Grille grille = new Grille(2);
        System.out.print(grille.toString());
        //System.out.println(grille.tabCase.length);
        Case macase = grille.getCase(3,2);
        Case centre = grille.getCase(0,0);
        ArrayList<Case> adj = grille.getZoneAdjacentes(macase);
        ArrayList<Case> adj2 = grille.getZoneAdjacentes(centre);

        int i =0;
        for( Case c : adj ){ i++;
            int posx = c.getPosx();
            int posy = c.getPosy();
            System.out.println("Case adj à (3,2) "+i+" a comme coord "+posx+", "+posy);
            /** devra renvoyer : (4,2) (3,1) (3,3) (2,2)**/
        }
        for( Case c : adj2 ){ i++;
            int posx = c.getPosx();
            int posy = c.getPosy();
            System.out.println("Case adj (0,0) "+i+" a comme coord "+posx+", "+posy);
            /** devra renvoyer (1,0) (0,1)**/
        }
    }


}
