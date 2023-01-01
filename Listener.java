import java.awt.event.*;

import javax.swing.JOptionPane;

public class Listener implements MouseListener {

    private Partie partie ;

    public Listener(Partie p){
        this.partie = p;
    }
    public Listener(){

    }
    public void setPartie(Partie p){
        this.partie = p;
    }
    @Override
	public void mouseClicked(MouseEvent e) throws NullPointerException  {
        if(partie == null){throw new NullPointerException("la partie est null");}
        if(this.partie.getState()==gameState.INPROGRESS){
            try{
                System.out.println(e.getX() + " " + e.getY());
            
            if(this.partie.getEnnemi().name()=="HUMAIN"){
                    partie.repaint();
                    if  ( (e.getX())/50-1>0 || (e.getX())/50-1<this.partie.getBoard().getLength() || (e.getY())/50-1>0  || (e.getY())/50-1 <this.partie.getBoard().getLength()){
                        
                        partie.update((e.getX())/50-1,(e.getY())/50-1);
                        partie.repaint();
                    }
                    
                    
                    System.out.println(partie.getBoard().nbcoupPossible());
            }else if(this.partie.getEnnemi().name()== "IABEGINNER"){
                System.out.println("boucle de l'iabeginner");
                if  ( (e.getX())/50-1>0 || (e.getX())/50-1<this.partie.getBoard().getLength() || (e.getY())/50-1>0  || (e.getY())/50-1 <this.partie.getBoard().getLength()){
                    if(this.partie.getBoard().getPlayer()== Pawn.BLACK){
                        partie.update((e.getX())/50-1,(e.getY())/50-1);
                        partie.repaint();
                    }if(this.partie.getBoard().getPlayer() == Pawn.WHITE){
                        if(!(this.partie.isOver())){
                            this.partie.getAmouv();
                        }
                    }
                    
                }
                System.out.println("nombre de coups possibles : "+partie.getBoard().nbcoupPossible());
                
                    
                    

            }if(this.partie.isOver() ){ 
                if(this.partie.winner().name() == "EMPTY"){
                    JOptionPane.showMessageDialog(null, "IL Y A EGALITE ");
                    
                }else {
                    JOptionPane.showMessageDialog(null, "Le joueur "+this.partie.winner().name()+" a gagné");
                }
                this.partie.init();
                this.partie.repaint();
                        
            } 

            }catch ( ArrayIndexOutOfBoundsException o){
                System.out.println("Vous clicker en dehors du plateau");
            }    
        }
        
        
                    
                    
    }
        
        
		            
                    
    

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

                

}
