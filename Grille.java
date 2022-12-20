public class Grille {
    private  int [] [] plateau;
    private String nom;
    private final static int NOIR = 1;
    private final static int BLANC = -1;
    private final static int VIDE = 0;

    public final static int NORD=0;
    public final static int NORDOUEST = 1;
    public final static int OUEST = 2;
    public final static int SUDOUEST = 3;
    public final static int SUD = 4;
    public final static int SUDEST = 5;
    public final static int EST = 6;
    public final static int NORDEST = 7;
    


    public int getcouleur(int x,int j){
        return this.plateau[x][j];
    }
    public void setcouleur(int i,int j, int joueur){
        this.plateau[i][j]= joueur;
    }

    public Grille(){
        this.plateau= new int[8] [8];
        plateau[3][4] = plateau[4][3] = this.NOIR;
		plateau[3][3] = plateau[4][4] = this.BLANC;
    }
    public void initialiser(){
        for(int i=0;i<8 ; i++){
            for(int j=0; j<8 ; j++){
                this.plateau[i][j]= this.VIDE;
            }
        }
        plateau[3][4] = plateau[4][3] = this.NOIR;
		plateau[3][3] = plateau[4][4] = this.BLANC;

    }
    //verifie si la case est un ennemi
    public  boolean est_ennemi(int x , int y, int joueur){
        if(this.plateau[x][y]==(-joueur)) return true;
        return false;
    }
    public boolean estvide(int i, int j){
        return this.plateau[i][j]== this.VIDE;
    }

    public void affiche(){
        for (int i=0 ; i<8;i++){
            for(int j=0; j<8; j++){
                System.out.print(this.plateau[i][j]+"  ");
            }
            System.out.println();
        }
    }
    
    public boolean coup_possible(int x , int y , int joueur){
        int nbcoup=0;
        if(estvide(x,y)){
            System.out.println("place vide");
            if(est_ennemi(x-1,y,joueur)){ 
                System.out.println("coup_possible _ estennemi nord") ; // on verifie si en position nord il y a un ennemi
                if(nord(x-2,y,joueur)) nbcoup++;
            }
            if(est_ennemi(x-1,y-1,joueur)){  // position nordouest
                if(nordouest(x-2,y-2,joueur)) nbcoup++;
            }
            if(est_ennemi(x,y-1,joueur)){    // position ouest
                if(ouest(x,y-2,joueur)) nbcoup++;
            }
            if(est_ennemi(x+1,y-1,joueur)){   // position sudouest
                if(sudouest(x+2,y-2,joueur)) nbcoup++;
            }
            if(est_ennemi(x+1,y,joueur)){    // position sud
                if(sud(x+2,y,joueur)) nbcoup++;
            }
            if(est_ennemi(x+1,y+1,joueur)){
                if(sudest(x+2,y+2,joueur)) nbcoup++;
            }
            if(est_ennemi(x,y+1,joueur)){   //position est
                if(est(x,y+2,joueur)) nbcoup++;
            }
            if(est_ennemi(x-1,y+1,joueur)){   //position nordest
                if(nordest(x-2,y+2,joueur)) nbcoup++;
            }
            

        }System.out.println(nbcoup);
        return nbcoup!=0;

    }

    public void update(int x, int y, int joueur){
        System.out.println("debut");
        if(coup_possible(x,y,joueur)){
            System.out.println("entré");
            this.plateau[x][y]=joueur;
            if(est_ennemi(x-1,y,joueur)){  
                System.out.println("nord") ;
                if(nord(x-2,y,joueur)){
                    changer_ligne(x,y,joueur , NORD);
                }
                
            }
            if(est_ennemi(x-1,y-1,joueur)){  
                System.out.println("norouest") ;
                if(nordouest(x-2,y-2,joueur)) changer_ligne(x,y,joueur ,NORDOUEST);
            }
            if(est_ennemi(x,y-1,joueur)){ 
                System.out.println("ouest") ;   
                if(ouest(x,y-2,joueur)) changer_ligne(x,y,joueur , OUEST);
            }
            if(est_ennemi(x+1,y-1,joueur)){   
                System.out.println("nsudouest") ;
                if(sudouest(x+2,y-2,joueur)) changer_ligne(x,y,joueur ,SUDOUEST);
            }
            if(est_ennemi(x+1,y,joueur)){   
                System.out.println("sud") ;
                if(sud(x+2,y,joueur)) changer_ligne(x,y,joueur , SUD);
            }
            if(est_ennemi(x+1,y+1,joueur)){
                System.out.println("sudest") ;
                if(sudest(x+2,y+2,joueur)) changer_ligne(x,y,joueur , SUDEST);
            }
            if(est_ennemi(x,y+1,joueur)){   
                System.out.println("est") ;
                if(est(x,y+2,joueur))  changer_ligne(x,y,joueur , EST);
            }
            if(est_ennemi(x-1,y+1,joueur)){ 
                System.out.println("nordest") ;  
                if(nordest(x-2,y+2,joueur)) changer_ligne(x,y,joueur , NORDEST);
            }
            
        }
        System.out.println("hors boucle coup possible");
    
    }


    public void changer_ligne(int x, int y,int joueur, int direction){
        
        int incrX;
        int incrY;
        int l,c,pion;

        switch(direction){
			case NORDOUEST :
				incrX = -1;
				incrY = -1;
				break;
			case NORD : 
				incrX = -1;
				incrY = 0;
				break;
			case NORDEST : 
				incrX = -1;
				incrY = +1;
				break;
			case OUEST : 
				incrX = 0;
				incrY = -1;
				break;
			case EST : 
				incrX = 0;
				incrY = +1;
				break;
			case SUDOUEST : 
				incrX = +1;
				incrY = -1;
				break;
			case SUD : 
				incrX = +1;
				incrY = 0;
				break;
			case SUDEST : 
				incrX = +1;
				incrY = +1;
				break;
			default:
				incrX = 0;
				incrY = 0;
		}
            l = x+incrX;
            c = y+incrY;

            try{
                System.out.println("ligne"+ l +"  colonne"+c);
                pion = plateau[l][c];
                while((pion!=joueur) && (pion!=VIDE)){
                    this.plateau[l][c]= joueur;
                    l += incrX;
                    c += incrY;
                    System.out.println("ligne"+ l +"  colonne"+c);
                    System.out.println("couleur du pion : " +pion);
                    pion = plateau[l][c];
                }
                
            }catch(ArrayIndexOutOfBoundsException e){ 
                //on se trouve en dehors de la grille
                
            }    

    }

    public boolean partie_fini(int joueur){
        return nbcoup_possible(joueur)==0;
    }


    public int nbpion(int joueur){
        int nb=0;
        for(int i=0; i<this.plateau.length;i++){
            for(int j=0;j<this.plateau.length;j++){
                if(this.plateau[i][j]==joueur) nb++;
            }
        }return nb;
    }
    
    public int gagnant(){
        if(nbpion(BLANC)> nbpion(NOIR))return BLANC;
        if(nbpion(BLANC)< nbpion(NOIR)) return NOIR;
        return VIDE;
        
    }
    public int nbcoup_possible(int joueur){
        int nb=0;
        for(int i= 0 ; i <this.plateau.length ; i++){
            for(int j =0 ; j<this.plateau.length;j++){
                if(coup_possible(i,j,joueur)) nb++;
            } 
        }
        return nb;
    }
    

    
    
    


    // verification qu'un coup est possible en direction de tous les cotés
    public boolean nord(int i, int j, int n){
        
        if(this.plateau[i][j]==this.VIDE ||i<0) return false ;
        if(this.plateau[i][j]==n) return true;
        else return nord(i-1,j,n);
    }
    public boolean sud(int i, int j, int n){
        
        if(this.plateau[i][j]==this.VIDE ||i>8) return false ;
        if(this.plateau[i][j]==n) return true;
        else return sud(i+1,j,n);
    }
    public boolean est(int i, int j, int n){
        
        if(this.plateau[i][j]==this.VIDE ||j>8) return false ;
        if(this.plateau[i][j]==n) return true;
        else return est(i,j+1,n);
    }
    public boolean ouest(int i, int j, int n){
        
        if(this.plateau[i][j]==this.VIDE ||j<0) return false ;
        if(this.plateau[i][j]==n) return true;
        else return ouest(i,j-1,n);
    }
    public boolean nordest(int i, int j, int n){
        
        if(this.plateau[i][j]==this.VIDE || j>8 || i<0) return false ;
        if(this.plateau[i][j]==n) return true;
        else return nordest(i-1,j+1,n);
    }
    public boolean nordouest(int i, int j, int n){
        
        if(this.plateau[i][j]==this.VIDE || j<0 || i<0) return false ;
        if(this.plateau[i][j]==n) return true;
        else return nordouest(i-1,j-1,n);
    }

    public boolean sudest(int i, int j, int n){
        
        if(this.plateau[i][j]==this.VIDE || j>8 || i>8) return false ;
        if(this.plateau[i][j]==n) return true;
        else return sudest(i+1,j+1,n);
    }
    public boolean sudouest(int i, int j, int n){
        
        if(this.plateau[i][j]==this.VIDE || j<0 || i>8) return false ;
        if(this.plateau[i][j]==n) return true;
        else return sudouest(i+1,j-1,n);
    }


    

    


}
