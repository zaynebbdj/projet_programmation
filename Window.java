import javax.swing.*;


public class Window extends JFrame{
    private Partie gamePanel ;
    
    public void setPanel(Partie p){
        this.gamePanel = p;
    }

    public Window(){
    	
        setTitle("OTHELLO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        
    // Création du menu
    JMenuBar menuBar = new JMenuBar();

    // Création des éléments de menu
    JMenu fileMenu = new JMenu("Fichier");
    JMenuItem newGame = new JMenuItem("Nouvelle partie");
    JMenuItem saveItem = new JMenuItem("sauvegarder");
    JMenuItem chargeItem = new JMenuItem("charger une partie");
    JMenuItem exitItem = new JMenuItem("Quitter");

    JMenu infos = new JMenu("partie sauvegardée");

    // Ajout des éléments de menu au menu
    fileMenu.add(newGame);
    fileMenu.add(saveItem);
    fileMenu.add(chargeItem);
    fileMenu.addSeparator(); // barre de séparation
    fileMenu.add(exitItem);

    

    // Ajout du menu à la barre de menu
    menuBar.add(fileMenu);
    
    menuBar.add(infos);

    // Ajout de la barre de menu à la fenêtre
    setJMenuBar(menuBar);
    
   
    // Ajout du Panel à la fenêtre
    this.gamePanel = new Partie(8,"first");
   
    this.add(gamePanel);

    //ajout des actions aux menu

    saveItem.addActionListener( event -> {  //sauvegarde la partie en cours
        if(this.gamePanel.getState()==gameState.INPROGRESS){

            this.gamePanel.save();     //sauvegarde de la partie

            JMenuItem partie = new JMenuItem(gamePanel.getName());
            infos.add(partie);    // on l'ajoute dans la liste "partie sauvegardée" pour quelle soit directement visible par l'utilisateur
            this.startwindow();       //on reinitialise la fenetre à son "etat d'ouverture"
            
        }
        
        
    });
    newGame.addActionListener( event -> {
        //on commence une nouvelle partie
        gamePanel.setNewGame();
        this.setNewSize();  // on actualise la taille de la fenêtre selon la taille du plateau de la partie 
        

    });

    chargeItem.addActionListener(event -> {
        String name = (String)JOptionPane.showInputDialog("saisissez le nom de la partie à charger");
        gamePanel.restore(name);
        this.setNewSize(); // on actualise la taille de la fenêtre selon la taille du plateau de la partie chargée
    });
    
    
        
    
    // Affichage de la fenêtre
    this.pack();
    SwingUtilities.updateComponentTreeUI(this);
    setLocationRelativeTo(null);
    setVisible(true);
    }

    public void startwindow(){
        gamePanel.init();    
        this.setNewSize();  
    }
    public void setNewSize(){
        if(gamePanel.getState()== gameState.INIT){
            this.setSize(50*(this.gamePanel.getLength()+3),50*(this.gamePanel.getLength()+4));
        }else {
            this.setSize(50*(this.gamePanel.getLength()+2),50*(this.gamePanel.getLength()+5));

        }
        
    }

    
}
