public class Grille {
    private  Pawn [] [] board;
    private String name;
    private Pawn color;
    

    
    public Pawn getcolor(int x,int j){
        return this.board[x][j];
    }
    public void setcolor(int i,int j, Pawn player){
        this.board[i][j]= player;
    }

    public Grille(int taille){
        this.board= new Pawn[taille] [taille];
        for(int i=0; i<taille;i++){
            for(int j =0 ; j<taille; j++){
                this.board[i][j]=Pawn.VIDE;
            }
        }
        board[3][4] = board[4][3] = Pawn.NOIR;
		board[3][3] = board[4][4] = Pawn.BLANC;
    }
    
    //verifie si une case a cote de la case [x][y] est un ennemi
    public  boolean isEnnemi(int x , int y, Direction d){
        Pawn player = this.board[x][y];
        int i,j;
        switch (d){
            case NORDOUEST :
				i = x-1;
				j = y-1;
				break;
			case NORD : 
                i = x-1;
                j = y;
				break;
			case NORDEST : 
                i = x-1;
                j = y+1;
				break;
			case OUEST : 
                i = x;
                j = y-1;
				break;
			case EST : 
                i = x;
                j = y+1;
				break;
			case SUDOUEST : 
            i = x+1;
            j = y-1;
				break;
			case SUD : 
            i = x+1;
            j = y;
				break;
			case SUDEST : 
            i = x+1;
            j = y+1;
				break;
			default:
				i = 0;
				j = 0;
        }
        
        if(  (this.board[i][j]==Pawn.BLANC && player==Pawn.NOIR )|| (this.board[i][j]==Pawn.NOIR && player==Pawn.BLANC)) return true;
        return false;
    }
    public boolean isEmpty(int i, int j){  // verifie si la case est vide
        return this.board[i][j]== Pawn.VIDE;
    }

    public void show(){
        for (int i=0 ; i<8;i++){
            for(int j=0; j<8; j++){
                System.out.print(this.board[i][j].ordinal2());
            }
            System.out.println();
        }
    }
    
    public boolean coupPossible(int x , int y , Pawn player){ // verifie si un coup est possible pour le  joueur player
        int nbcoup=0;
        if(isEmpty(x,y)){
            for(Direction d : Direction.values()){
                if(this.coupPossible_aux2(x,y,player,d)) nbcoup++;
            }
            
            

        }
        return nbcoup!=0;

    }

    public void update(int x, int y ,Pawn player){// actualise le plateau avec le nouveau coup
        if(this.coupPossible(x,y,player)){
            this.board[x][y]=player;
            for(Direction d : Direction.values()){
                if(this.coupPossible_aux2(x,y,player,d)) changeLigne(x,y,player,d);
            }

        }

    }
    

    public void changeLigne(int x, int y,Pawn player, Direction direction){ //actualise la grille suite a un coup possible
        
        int incrX;
        int incrY;
        int l,c;
        Pawn p;

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
                
                p = board[l][c];
                while((p!=player) && (p!=Pawn.VIDE)){
                    this.board[l][c]= player;
                    l += incrX;
                    c += incrY;
                    
                    p = board[l][c];
                }
                
            }catch(ArrayIndexOutOfBoundsException e){ 
                //on se trouve en dehors de la grille
                
            }    

    }
    public boolean isFull(){  //verifie si la grille est pleine renvoie true si oui
        for(int i=0; i<this.board.length ; i++){
            for(int j =0 ; j<this.board.length ; j++){
                if(this.board[i][j]==Pawn.VIDE) return false;
            }
        }
        return true;
    }

    public boolean partie_fini(Pawn player){ // verifis si une partie est finie retourne vrai si il n'y a aucun coup possible 
                                            // ou si la grille est pleine
        return (nbcoupPossible(player)==0 && this.isFull());
    }


    public int nbPawn(Pawn player){ // compte le nombre de point du joueur player 
        int nb=0;
        for(int i=0; i<this.board.length;i++){
            for(int j=0;j<this.board.length;j++){
                if(this.board[i][j]==player) nb++;
            }
        }return nb;
    }
    //ceci est un test
    
    public Pawn gwinner(){ // renvoie le joueur qui a le plus de point aucun des deux si execo
        if(nbPawn(Pawn.BLANC)> nbPawn(Pawn.NOIR))return Pawn.BLANC;
        if(nbPawn(Pawn.BLANC)< nbPawn(Pawn.NOIR)) return Pawn.NOIR;
        return Pawn.VIDE;
        
    }
    public int nbcoupPossible(Pawn player){ // retourne le nombre de coup possible du joueur player
        int nb=0;
        for(int i= 0 ; i <this.board.length ; i++){
            for(int j =0 ; j<this.board.length;j++){
                if(coupPossible(i,j,player)) nb++;
            } 
        }
        return nb;
    }
    

    public boolean coupPossible_aux(int x, int y , Pawn player,Direction d){ // verifie si un coup est possible pour le joeuur player selon un direction
        if(this.board[x][y]==Pawn.VIDE) return false;
        if(this.board[x][y]==player) return true;
        int incrX,incrY;
        switch(d){

			case NORDOUEST :
				incrX = x-1;
				incrY = y-1;
				break;
			case NORD : 
				incrX = x-1;
				incrY = y;
				break;
			case NORDEST : 
				incrX = x-1;
				incrY = y+1;
				break;
			case OUEST : 
				incrX = x;
				incrY = y-1;
				break;
			case EST : 
				incrX = x;
				incrY = y+1;
				break;
			case SUDOUEST : 
				incrX = x+1;
				incrY = y-1;
				break;
			case SUD : 
				incrX = x+1;
				incrY = y;
				break;
			case SUDEST : 
				incrX = x+1;
				incrY = y+1;
				break;
			default:
				incrX = 0;
				incrY = 0;
		}
        return coupPossible_aux(incrX,incrY,player,d);
        

    }
    public boolean coupPossible_aux2(int x,int y, Pawn player,Direction d){ // verifie si un coup est possible selon une direction
        int incrX,incrY;
            switch(d){
                case NORDOUEST :
                    incrX = x-2;
                    incrY = y-2;
                    break;
                case NORD : 
                    incrX = x-2;
                    incrY = y;
                    break;
                case NORDEST : 
                    incrX = x-2;
                    incrY = y+2;
                    break;
                case OUEST : 
                    incrX = x;
                    incrY = y-2;
                    break;
                case EST : 
                    incrX = x;
                    incrY = y+2;
                    break;
                case SUDOUEST : 
                    incrX = x+2;
                    incrY = y-2;
                    break;
                case SUD : 
                    incrX = x+2;
                    incrY = y;
                    break;
                case SUDEST : 
                    incrX = x+2;
                    incrY = y+2;
                    break;
                default:
                    incrX = 0;
                    incrY = 0;
            }
            return coupPossible_aux(incrX,incrY,player,d);
        
    }
    
    


}
