public class Main {
    

    public static void main(String[] args){

        
        Grille jeu = new Grille(8);
        jeu.show();
        jeu.update(3,2,Pawn.NOIR);
        System.out.println();
        jeu.show();
        jeu.update(2,2,Pawn.BLANC);
        System.out.println();
        jeu.show();
        

    }
}
