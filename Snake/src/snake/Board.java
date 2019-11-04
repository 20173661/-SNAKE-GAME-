package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;

public class Board {        
    
    private final int empty = 0;
    private final int wall = 1;
    private final int food = 3;
    private final int portal = 4;
    
    private final int pixel = 32;
    
    private BufferedImage grassImg, wallImg, foodImg; 
    
    private int [][] MAP = {
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			{4,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
			{4,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			};
   
    public Board() {
        loadImg();
    }
    
    public void loadImg() {
        try {
            grassImg = ImageIO.read(new File("img/grass.png"));
            wallImg = ImageIO.read(new File("img/wall.png"));
            foodImg = ImageIO.read(new File("img/food.png"));
        } catch (IOException ex) {
            System.out.println("Failed to load board image file");
        }
    }

    public int getHeight() {
	return MAP[0].length;
    }

    public int getWidth() {
	return MAP.length;
    }

    public int getEmpty() {
        return empty;
    }

    public int getWall() {
        return wall;
    }

    public int getFood() {
        return food;
    }

    public int getPixel() {
        return pixel;
    }
    
    public int getCoordinate(int x, int y) {
	return MAP[y][x];
    }
    
    public void setCoordinate(int x, int y, int type){
        MAP[y][x] = type;
    }
    
    public void addFood() {   
        while(true)
        {
            int xMax = getWidth()-2; 
            int yMax = getHeight()-2;
            int min = 2;
            int x, y;

            Random r = new Random();
            x = r.nextInt((xMax - min) + 1) + min;
            y = r.nextInt((yMax - min) + 1) + min;
            
            if(getCoordinate(x, y) == empty) {
                setCoordinate(x, y, food);
                break;
            }   
            //else System.out.println("No Food For You M8");
        }
    }
   
    public void drawMap(Graphics2D draw) {
        for(int i = 0; i < getWidth(); i++) {
            for(int j = 0; j < getHeight(); j++) {
                if(getCoordinate(i, j) == empty) {
                    draw.drawImage(grassImg, i*pixel, j*pixel, pixel, pixel, null);
                }
                if(getCoordinate(i, j) == wall) {
                    draw.drawImage(wallImg, i*pixel, j*pixel, pixel, pixel, null);
                }
                if(getCoordinate(i, j) == food) {
                    draw.drawImage(grassImg, i*pixel, j*pixel, pixel, pixel, null);
                    draw.drawImage(foodImg, i*pixel, j*pixel, pixel, pixel, null);   
                }
                if(getCoordinate(i, j) == portal) {
                    draw.setColor(Color.black);
                    draw.fillRect(i*pixel, j*pixel, pixel, pixel);
                }
            }
        }  
    }
    /*
    public void printInConsole() {
         for(int i = 0; i < getWidth(); i++) {
            for(int j = 0; j < getHeight(); j++) {
                System.out.print(getCoordinate(i, j));  
            }
            System.out.println();
        } 
    }*/
}