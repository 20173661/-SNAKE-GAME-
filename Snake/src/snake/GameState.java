package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameState extends JPanel implements KeyListener, ActionListener {
    
    Board board = new Board();
    Snake snake = new Snake(15, 10, board.getPixel());
    Timer timer;
    
    private boolean gameOver = false;
    
    private int gameSpeed = 300;
    private float foodTimer = 0;
    
    private int score = 0;
    
    public GameState() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer = new Timer(gameSpeed, this);
        timer.start();
    }
    
    @Override
    public void paint(Graphics g) {
        requestFocus(true);
        //background
        g.setColor(Color.black);
        g.fillRect(0,0,900,950);
        
        board.drawMap((Graphics2D) g);
        
        Font scoreFont = new Font("Verdana", Font.BOLD, 25);
        g.setColor(new Color(0, 200, 0));
        g.setFont(scoreFont);
        
        g.drawString("SCORE: "+ score, 2*board.getPixel(), 27*board.getPixel());
        
        if(gameOver) {
            snake.paintDeadSnake((Graphics2D) g);
            Font large = new Font("Verdana", Font.BOLD, 60);
            Font small = new Font("Verdana", Font.BOLD, 30);
            g.setColor(new Color(0, 0, 0));
            g.setFont(large);
        
            g.drawString("GAME OVER", 8*board.getPixel(), 14*board.getPixel());
            g.setFont(small);
            g.drawString("Press SPACE to play again !", 7*board.getPixel(), 15*board.getPixel());
        }
        else snake.paintSnake((Graphics2D) g);
        
        g.dispose();
    }
    
    public void foodSpawner() {
        board.addFood();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
       
        if(gameOver == false)
           snake.move(board); 
       
        if(board.getCoordinate(snake.getX()/board.getPixel(), snake.getY()/board.getPixel()) == board.getFood()) {
           board.setCoordinate(snake.getX()/board.getPixel(), snake.getY()/board.getPixel(), board.getEmpty());
           //System.out.println("Niam Niam...");
           snake.addBody();
           score += 5;
        }
        if(board.getCoordinate(snake.getX()/board.getPixel(), snake.getY()/board.getPixel()) == board.getWall() || snake.isBitten()) {
            
            gameOver = true;
            //System.out.println("Game Over");
        }
        else 
        {
            foodTimer += gameSpeed/1000f;
        }
        if(foodTimer >= 10) {
           board.addFood(); 
           foodTimer = 0;
        }
        repaint();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
            if(snake.getDir() != 1) 
                snake.setDir(0);
            //System.out.println("<-<-<-");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if(snake.getDir() != 3) 
                snake.setDir(2); 
            //System.out.println("up");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(snake.getDir() != 0) 
                snake.setDir(1); 
            //System.out.println("->->->");
        } 
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(snake.getDir() != 2) 
                snake.setDir(3); 
            //System.out.println("down");
        }
        else if (e.getKeyCode() == KeyEvent.VK_X) {
            board.addFood();
            //System.out.println("hack");
        }
        if(gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameOver = false;
            score = 0; 
            board = new Board();
            snake = new Snake(15, 10, board.getPixel());
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    } 
    @Override
    public void keyReleased(KeyEvent e) {
    } 
}