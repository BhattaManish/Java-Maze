package de.uni_hannover.hci.maze.deep_search;


import de.uni_hannover.hci.maze.random_kruskal_algorithm.Wall;
import de.uni_hannover.hci.maze.random_kruskal_algorithm.Side;



public class MazePrinter2 {
   
    public String convertMaze(Cell[][] maze){
        String result = "";;
        for(int y = 0; y < maze.length; y++){
            result += "#"; // die linke Wand (der Rand).
            // erster Durchlauf: obere Wand checken
            if(y == 0 || hasGeneralRow(maze, y - 1)){ 
            for(int firstX = 0; firstX < maze[0].length; firstX++){
                if(!maze[firstX][y].getWall(Side.UP).isDestroyed()){
                    result += "#";
                } else {
                    if(firstX == 0 && y == 0) {
                        result += "E";
                    } else {
                        result += " ";
                    }
                }
                if(hasGeneralColumn(maze, firstX)){
                    result += "#";
                }
            }
            
            result += "\n#";
            }
            // zweiter Durchlauf: rechte Wände checken.
            for(int secondX = 0; secondX < maze[0].length; secondX++){
                if(!maze[secondX][y].getWall(Side.RIGHT).isDestroyed()){
                    result += " #"; //  ein Stück Pfad und Wand.
                } else if(hasGeneralColumn(maze, secondX)){
                    result += "  ";//  zwei Stücke Pfad.
                } else { // nur ein Stück Pfad.
                    result += " "; 
                }
            }
            result += "\n";
        }
        // letzte Zeile
        for(int i = 0; i < findWidth(maze); i++){
            if(i == findWidth(maze) - 2) { 
                result += "A";

            } else {    
                result += "#";
            }    
        }
        return result;
    }
    

    
    //checks for right walls in a certain x-position of the cell matrix.
    private boolean hasGeneralColumn(Cell[][] maze, int x){
        for(int i = 0; i < maze.length; i++){
            if(!maze[x][i].getWall(Side.RIGHT).isDestroyed()) return true;
        }
        return false;
    }
    
    
    // checks for roof Wall in a certain y-position of the cell matrix.
    private boolean hasGeneralRow(Cell[][] maze, int y){
        for(int i = 0; i < maze[0].length; i++){
            if(!maze[i][y+1].getWall(Side.UP).isDestroyed()) return true;
        }
        return false;
    }
    
    
    
  
    private int findWidth(Cell[][] maze){
        int width = 0;
        int maxWidth = maze[0].length * 2 + 1;
        int currentWidth = width;
        for(int y = 0; y < maze.length; y++){
            width = 0;
            for(int x = 0; x < maze[0].length; x++){
                if(!maze[x][y ].getWall(Side.RIGHT).isDestroyed() || hasGeneralColumn(maze, x)){
                    width += 2;
                } else {
                    width++;
                }
                if(width > currentWidth){
                    currentWidth = width;
                }
            }
        }
        return currentWidth + 1; // plus linker Wand (Rand).
    }


}
