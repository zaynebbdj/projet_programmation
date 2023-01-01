
import static java.lang.System.out;

import java.awt.Graphics;
import java.awt.Color ;



public class Board {
    private  Pawn [] [] board;
    private String name; // nom de la partie
    private Pawn player =Pawn.BLACK;  // joueur en cours
    private int length;
    
   
    

    public Board(){}
    public Board(int taille){
        this.board= new Pawn[taille] [taille];
        for(int i=0; i<taille;i++){
            for(int j =0 ; j<taille; j++){
                this.board[i][j]=Pawn.EMPTY;
            }
        }
        board[3][4] = board[4][3] = Pawn.BLACK;
		board[3][3] = board[4][4] = Pawn.WHITE;
        this.length=taille;
    }

    public Board(int taille,String nom){
        this.board= new Pawn[taille] [taille];
        for(int i=0; i<taille;i++){
            for(int j =0 ; j<taille; j++){
                this.board[i][j]=Pawn.EMPTY;
            }
        }
        board[3][4] = board[4][3] = Pawn.BLACK;
		board[3][3] = board[4][4] = Pawn.WHITE;
        this.name=nom;
        this.length=taille;
    }
    public static Board copie(Board b){
        Board res = new Board(b.getLength(),b.getName());
        for(int i=0; i<b.getLength();i++){
            for(int j=0; j<b.getLength();j++){
                res.setPawn(i,j,b.getPawn(i,j));
            }
        }
        res.length = b.board.length;
        return res;
        

    }


    public void init(int taille){
        this.board= new Pawn[taille] [taille];
        for(int i=0; i<taille;i++){
            for(int j =0 ; j<taille; j++){
                this.board[i][j]=Pawn.EMPTY;
            }
        }
        board[this.board.length/2 -1][this.board.length/2] = board[this.board.length/2][this.board.length/2 -1] = Pawn.BLACK;
		board[this.board.length/2 -1][this.board.length/2 -1] = board[this.board.length/2][this.board.length/2] = Pawn.WHITE;
        this.length= this.board.length;
    }
    /**
     * methode qui retourne le plateau de Pion
     * @return le plateau de Pion
     */
    public Pawn[][] getBoard(){
        return this.board;
    }
    /**
     * Retourne la taille du plateau de jeu
     * @return la longueur du plateau de Pion
     */
    public int getlength(){
        return this.board.length;
    }
    /**
     * retourne le nom de la partie en cours
     * @return retourne le nom de la partie en cours
     */
    public String getName(){
        return this.name;
    }
    
    public Pawn getPawn(int x,int j){  //retourne la couleur d'une case
        return this.board[x][j];
        
    }
    public void setPawn(int i,int j, Pawn player){  //change la couleur d'une case
        this.board[i][j]= player;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPlayer(Pawn p){ // change le joeur en cour
        this.player = p;
    }
    public void setPlayer(String s){
        if(s=="BLACK") this.player = Pawn.BLACK;
        if(s=="WHITE") this.player = Pawn.WHITE ;

    }
    public void setLength(int taille){
        this.length=taille;
    }
    public Pawn getPlayer(){
        return this.player;
    }
    public int getLength(){
        return this.length;
    }
    public void showterminal(){                   //affiche le plateau dans le terminal
        for (int i=0 ; i<this.board.length;i++){
            for(int j=0; j<this.board.length; j++){
                int color =this.board[i][j].ordinal2() ;
                if(this.coupPossible(i, j)){
                    out.printf("|\033[43m   \033[0m");
                }
                else if(color==-1)out.printf("|\033[47m   \033[0m");
                else if(color == 1)out.printf("|\033[40m   \033[0m");
                else out.printf("|\033[42m   \033[0m");
                
            }
            
            System.out.println();out.println("---------------------------------");
        }
    }

    //methode qui dessine un plateau sur un objet graphics
    public void show(Graphics g){

        g.setColor(new Color(139, 69, 19));
        g.fillRect(25,25,50*(this.board.length+1),50*(this.board.length+1));

        g.setColor(Color.BLACK);
        g.drawRect(25,25,50*(this.board.length+1),50*(this.board.length+1));

        g.setColor(new Color(0,100,0));
        g.fillRect(50,50,50*(this.board.length),50*(this.board.length));
        
        for(int i=0; i<this.board.length ; i++){
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf((char) (65+i)), 50*i+75, 42);
            g.drawString(String.valueOf(i+1), 32, 50*i+80);
            g.setColor(Color.BLACK);
            g.drawLine(i * 50+50, 50,i * 50+50, 50*(this.board.length+1));
            g.drawLine(50, i * 50+50, 50*(this.board.length+1), i * 50+50) ;
            for(int j=0 ; j<this.board.length ; j++){
                if(this.board[i][j]==Pawn.BLACK){
                    g.setColor(Color.BLACK);
                    g.drawOval(55+50*i, 55+50*j, 40, 40);
                    g.fillOval(55+50*i, 55+50*j, 40, 40);
                }else if(this.board[i][j]==Pawn.WHITE){
                    g.setColor(Color.WHITE);
                    g.drawOval(55+50*i, 55+50*j, 40, 40);
                    g.fillOval(55+50*i, 55+50*j, 40, 40);
                }
            }
            g.setColor(Color.BLACK);
        }g.drawLine(this.board.length * 50+50, 50,this.board.length * 50+50, this.board.length*50+50);
        g.drawLine(50,this.board.length * 50+50, this.board.length * 50+50,this.board.length * 50+50);
    }
    

    public void changePlayer(){   // methode qui change le joueur courant
        if(this.player == Pawn.BLACK ){this.player=Pawn.WHITE;} 
        else if(this.player == Pawn.WHITE){this.player=Pawn.BLACK;} 
    }
    public static Pawn Pawn_of_String(String s) throws NotAPawn{
        
        if(s=="B") return  Pawn.BLACK;
        if(s=="W") return  Pawn.WHITE ;
        if(s=="E") return  Pawn.EMPTY;
        else{
            throw new NotAPawn("ce n'est pas un pion");
        }
        
        
    }


    
    //verifie si une case a cote de la case [x][y] est un ennemi
    /**
     * 
     * @param x abscisse du pion à placer
     * @param y ordonnée du pion à placer
     * @param d direction 
     * @return vrai si un pion voisin est ennemi dans une certaine direction d
     */
    public  boolean isEnnemi(int x , int y, Direction d){

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
        if(i<this.board.length && j<this.board.length && i>0 && j>0){
            if(  (this.board[i][j]==Pawn.WHITE && this.player==Pawn.BLACK )|| (this.board[i][j]==Pawn.BLACK && this.player==Pawn.WHITE)) return true;
        }
        
        return false;
    }
    public boolean isEmpty(int i, int j){  // verifie si la case est EMPTY
        return this.board[i][j]== Pawn.EMPTY;
    }

    
    
    

    public void update(int x, int y ){// actualise le plateau avec le nouveau coup
        if(this.coupPossible(x,y)){
            this.board[x][y]=this.player;
            for(Direction d : Direction.values()){
                if(this.coupPossible_aux(x,y,d)) changeLigne(x,y,d);
            }
            this.changePlayer();
        }
        

    }
    

    public void changeLigne(int x, int y, Direction direction){ //actualise la Board suite a un coup possible selon une seule direction
        
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
                while((p!=this.player) && (p!=Pawn.EMPTY)){
                    
                    this.board[l][c]= this.player;
                    l += incrX;
                    c += incrY;
                    
                    p = board[l][c];
                }
                
            }catch(ArrayIndexOutOfBoundsException e){ 
                //on se trouve en dehors de lu plateau
                
            }    

    }
    public boolean isFull(){  //verifie si le plateau est plein renvoie true si oui
        for(int i=0; i<this.board.length ; i++){
            for(int j =0 ; j<this.board.length ; j++){
                if(this.board[i][j]==Pawn.EMPTY) return false;
            }
        }
        return true;
    }

    public boolean isOver(){ // verifie si une partie est finie retourne vrai si il n'y a aucun coup possible 
                                            // ou si le plateau est plein
        return (nbcoupPossible()==0 || this.isFull());
    }


    public int nbPawn(Pawn p){ // compte le nombre de point du joueur p
        int nb=0;
        for(int i=0; i<this.board.length;i++){
            for(int j=0;j<this.board.length;j++){
                if(this.board[i][j]==p) nb++;
            }
        }return nb;
    }
    
    
    
    public int nbcoupPossible(){ // retourne le nombre de coup possible du joueur courant
        int nb=0;
        for(int i= 0 ; i <this.board.length ; i++){
            for(int j =0 ; j<this.board.length;j++){          
                if(coupPossible(i,j)){
                    nb++;
                } 
            } 
        }
        return nb;
    }

    ///montre les coups possible dans le terminal
    public void showCoupPossible(){ 
        System.out.println("Voici les coups possibles pour les :"+ this.player.name());
        for (int i=0 ; i<this.board.length;i++){
            for(int j=0; j<this.board.length; j++){
                if(coupPossible(i,j)){
                    System.out.println((i)+","+(j));
                }
                
            }
            System.out.println();
        }
    }

    // verifie si un coup est possible pour le  joueur player
    public boolean coupPossible(int x , int y ){ 
        
        if(isEmpty(x,y)){
            for(Direction d : Direction.values()){
                if(this.isEnnemi(x,y,d)){                //si il y a un ennemi a coté selon la direction d
                    if(this.coupPossible_aux(x,y,d)){   //on verifie que que le coup est possible selon cette direction
                        return true;
                        
                    }
                } 
            }
        }
        return false;

    }
    public boolean coupPossible_aux2(int x, int y ,Direction d){ // verifie si un coup est possible pour le joueur player selon un direction
        if(this.board[x][y]==Pawn.EMPTY) return false;
        if(this.board[x][y]==this.player) return true;
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
        if(incrX>=0 && incrX<this.board.length && incrY<this.board.length && incrY>=0){
            return coupPossible_aux2(incrX,incrY,d);
        }return false;
        

    }
    public boolean coupPossible_aux(int x,int y ,Direction d){ // verifie si un coup est possible selon une direction
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
            if(incrX>=0 && incrX<this.board.length && incrY<this.board.length && incrY>=0){
                return coupPossible_aux2(incrX,incrY,d);
            }return false;
            
        
    }
    public Pawn winner(){ // renvoie le joueur qui a le plus de point aucun des deux si execo
        if(nbPawn(Pawn.WHITE)> nbPawn(Pawn.BLACK))return Pawn.WHITE;
        if(nbPawn(Pawn.WHITE)< nbPawn(Pawn.BLACK)) return Pawn.BLACK;
        return Pawn.EMPTY;
        
    }
    
    


}
