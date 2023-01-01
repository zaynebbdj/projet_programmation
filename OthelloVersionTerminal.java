import java.util.Scanner ;
public class Main {
    


    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Board nouvelle = new Board(8,"partie 1");

        while(!(nouvelle.isOver())==true){
            nouvelle.showterminal();
            nouvelle.showCoupPossible();
            int coupX = input.nextInt();
            int coupy = input.nextInt();
            nouvelle.update(coupX, coupy);
            



        }
        input.close();
    }
}
