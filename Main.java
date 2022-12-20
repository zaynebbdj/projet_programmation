public class Main {
    

    public static void main(String[] args){

        
        Grille jeu = new Grille();
        jeu.affiche();
        jeu.update(3,2,1);
        System.out.println();
        jeu.affiche();
        jeu.update(5,4,-1);
        System.out.println();
        jeu.affiche();
        

    }
}
