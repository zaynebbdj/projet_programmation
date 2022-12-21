public class Jeu {
    
    private  Grille[] parties;
    private static int nb_partie;


    public Jeu(){
        this.parties = new Grille[10];
        nb_partie=0;
    }
    public void nouvelle_partie(){
        parties[this.nb_partie]= new Grille(8);
        this.nb_partie ++;
    }
    public void supprimer_partie(){
        
    }

}
