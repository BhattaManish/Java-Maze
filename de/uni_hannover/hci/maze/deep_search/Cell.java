package de.uni_hannover.hci.maze.deep_search;

import de.uni_hannover.hci.maze.random_kruskal_algorithm.Wall;
import de.uni_hannover.hci.maze.random_kruskal_algorithm.Side;


public class Cell {
    
    private Wall[] wall;
    private final int posX;
    private final int posY;

    private final boolean isStart;
    private final boolean isEnd;
    
    
    public Cell( int x, int y, boolean s, boolean e){
         posX = x;
         posY = y;
         isStart = s;
         isEnd = e;
         wall = new Wall[4];
         wall[0] = new Wall(Side.UP);
         wall[1] = new Wall(Side.RIGHT); 
         wall[2] = new Wall(Side.DOWN);
         wall[3] = new Wall(Side.LEFT);
         if(s == true) wall[0].setIsDestroyed(true);
      }                    
    
    
    public int getPosX(){
        return posX;
    }
    
    
    public int getPosY(){
        return posY;
    }
    
    
    public Wall getWall(Side s){
        switch(s){
            case UP:
                return wall[0];
            case RIGHT:
                return wall[1];
            case DOWN:
                return wall[2];
            default: // LEFT
                return wall[3];    
        }
    }
    
    
    public void destroyWall(Side s){
        switch(s){
            case UP:
                wall[0].setIsDestroyed(true);
                break;
            case RIGHT:
                wall[1].setIsDestroyed(true);
                break;
            case DOWN:
                wall[2].setIsDestroyed(true);
                break;
            default: // LEFT
                wall[3].setIsDestroyed(true);    
        }
    }
    

    
  
}
