package snake;

import javax.swing.JFrame;


public class GameConsole { 
    
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        GameState gw = new GameState();
        Board board = new Board();
        window.setBounds(10,10, 890, 940);
        window.setTitle("◉SNAKE︿GAME◉");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
       
        window.add(gw);  
        
        //board.printInConsole();
    } 
}
    

