package Model;

public class Cle {

    /*** Attributs ***/
    private Element elementCle;
    // Elles servent à quoi exactement? c'est pas redodant ?
    public static Cle CLEAIR=new Cle(Element.AIR);
    public static Cle CLEFEU=new Cle(Element.FEU);
    public static  Cle CLEEAU =new Cle(Element.EAU);
    public static Cle CLETERRE=new Cle(Element.TERRE);

    /**
     * Constructeur
     * @param elementArtefact
     */
    public Cle(Element elementArtefact){
        this.elementCle=elementArtefact;
    }

    /*** Getters ***/
    public Element getElementCle() {
        return elementCle;
    }



    @Override
    public String toString() {
        return "Cle{" +
                "elementCle=" + elementCle +
                '}';
    }


}