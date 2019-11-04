package snake;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import javax.imageio.ImageIO;

public class Snake { 
    
    private LinkedList<BodyPart> body = new LinkedList<>();
    private BufferedImage snakeHeadImg, snakeBodyImg, snakeDeadImg, headDeadImg; 
    
    private int x;
    private int y;

    private int dir = 1;
    
    private int pixel;
    
    public Snake(int x, int y, int  pixel) {
        this.x = x*pixel;
        this.y = y*pixel; 
        this.pixel = pixel;
        body.add(new BodyPart(this.x-pixel,this.y));
        body.add(new BodyPart(this.x-pixel*2,this.y));
        loadImg();
    }
    
    public void loadImg() {
        try {
            snakeHeadImg = ImageIO.read(new File("img/snake_head.png"));
            snakeBodyImg = ImageIO.read(new File("img/snake_body.png"));
            snakeDeadImg = ImageIO.read(new File("img/snake_dead.png"));
            headDeadImg = ImageIO.read(new File("img/head_dead.png"));
        } catch (Exception ex) {
            System.out.println("Failed to load snake image file");
        }
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int direction) {
        this.dir = direction;
    }
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void addBody() {
        BodyPart last = body.getLast();
        body.add(new BodyPart(last.getX(), last.getY()));
    }
    
    public void move(Board board) {       
        int nextX = x, nextY = y;
        if(dir == 0) {//left
            if(x - pixel >= pixel)
                x -= pixel;
            else x = (board.getWidth() - 2) * pixel;
        }
        if(dir == 1) {//right
            if(x + pixel <= (board.getWidth() - 2) * pixel)
                x += pixel;
            else x = pixel;
        }  
        if(dir == 3) {//down
            if(y + pixel <= (board.getHeight() - 2) * pixel)
                y += pixel; 
            else y = pixel;
        }
        if(dir == 2) {//up
            if(y - pixel >= pixel)
                y -= pixel; 
            else y = (board.getHeight() - 2) * pixel;
        }
        
        for(BodyPart bodyPart:body) {
            int tempX = bodyPart.getX();
            int tempY = bodyPart.getY();
            bodyPart.setX(nextX);
            bodyPart.setY(nextY);
            nextX = tempX;
            nextY = tempY;
        }  
    }
    
    public boolean isBitten() {
        for(BodyPart bodyPart:body) {
            if(x == bodyPart.getX() && y == bodyPart.getY())
                return true;
        }  
        return false;
    }
    
    public void paintDeadSnake(Graphics2D g) { 
        
        for(BodyPart bodyPart:body) {
            if(bodyPart == body.getLast())
                g.drawImage(snakeDeadImg, bodyPart.getX(), bodyPart.getY(), pixel, pixel, null);
            else
                g.drawImage(snakeDeadImg, bodyPart.getX(), bodyPart.getY(), pixel, pixel, null);
        }
        g.drawImage(headDeadImg, x, y, pixel, pixel, null);
    }
    
    public void paintSnake(Graphics2D g) { 
        for(BodyPart bodyPart:body) {
            if(bodyPart == body.getLast()) 
                g.drawImage(snakeBodyImg, bodyPart.getX(), bodyPart.getY(), pixel, pixel, null);
            else 
                g.drawImage(snakeBodyImg, bodyPart.getX(), bodyPart.getY(), pixel, pixel, null);
        }
        g.drawImage(snakeHeadImg, x, y, pixel, pixel, null);
    }
}