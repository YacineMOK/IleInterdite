package Model.Role;
import Model.Joueur;
import Model.Case;
import Model.EtatZone;


public class Pilote extends Joueur {

    /** Constructeur **/
    public Pilote(String pseudo,Case c) {
        super(pseudo, Role.PILOTE);
        this.setCase(c);
    }

    /**
     * Permet de deplacer le pilote (surchage de la methode de joueur)
     * @param mouseX coordonnée en x du click
     * @param mouseY coordonnée en y du click
     * @param JX coordonnée en x du joueur
     * @param JY coordonnée en y du joueur
     * @return
     */
    public boolean clicUtile(int mouseX, int mouseY,int JX,int JY) {
        return true;
    }


    /**
     * Permet de gerer le sechage de case associé à pilote
     * @param c la case à assecher
     * @throws Exception
     */
    public void AssecherCase(Case c) throws Exception {
    	if(AssecheUtile(c.getPosx(),c.getPosy(),this.getPosx(),this.getPosy())) {
	        if(c.getLevel()!=EtatZone.SUBMERGEE){
	            c.downLevel();
	            this.decreaseAction();
	        }
    	}
      
    }

    /**
     * Permet de gerer le sechage de case de pilote au niveau des clicks
     * @param mouseX coordonnée en x du click
     * @param mouseY coordonnée en y du click
     * @param JX coordonnée en x du joueur
     * @param JY coordonnée en y du joueur
     * @return
     */
    public boolean AssecheUtile(int mouseX, int mouseY, int JX, int JY) {
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


}