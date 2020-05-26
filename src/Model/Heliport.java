package Model;

public class Heliport extends Case {

    /** Constructeur **/
    public Heliport(int x, int y, Grille g){
        super(x,y,g);
        this.setHeliport();
    }

    public String toStringHeliport(){
        return "H";
    }

}