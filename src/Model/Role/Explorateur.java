package Model.Role;
import Model.Joueur;
import Model.Case;

public class Explorateur extends Joueur {

    /** Constructeur **/
    public Explorateur(String pseudo, Case newCase) {
        super(pseudo, Role.EXPLORER);
        this.setCase(newCase);
    }


    /** Methods **/
    /**
     * Surchage de la fonction qui gere les deplacements d'un joueur dans la classe joueur
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
        //diag HD
        if((mouseX==JX+1 ) && mouseY==JY+1){
            return true;
        }
        //diag HG
        if((mouseX==JX-1 ) && mouseY==JY+1){
            return true;
        }
        //diag BD
        if((mouseX==JX+1 ) && mouseY==JY-1){
            return true;
        }
        //diag BG
        if((mouseX==JX-1 ) && mouseY==JY-1){
            return true;
        }
        return false;
    }

}