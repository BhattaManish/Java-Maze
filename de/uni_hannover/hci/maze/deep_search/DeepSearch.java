package de.uni_hannover.hci.maze.deep_search;


import de.uni_hannover.hci.maze.random_kruskal_algorithm.Wall;
import de.uni_hannover.hci.maze.random_kruskal_algorithm.Side;



import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;


public class DeepSearch{

    private Cell[][] cells;
    private final int WIDTH;
    private final int HEIGHT;

    private  int [][] vectors ={{1,0},{-1,0},{0,1},{0,-1}};

    private Stack <Cell> stack = new Stack<Cell>();
    private Stack <Cell> visitedCellStack = new Stack<Cell>();
    private int countVisitedCell = 0;
    private final int totalCell;
    private Random random = new Random();

    
    
    /*
      TODO: 
           - Exceptions für die Dimensionen hinzufügen.
    */
    public DeepSearch(int width, int height){
        WIDTH = width;
        HEIGHT = height;
        int cellNumber;
        cells = new Cell[HEIGHT][WIDTH];
        for(int y= 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){
                boolean start, end;
                start = (x == 0 && y == 0) ? true : false;
                end = (x== WIDTH - 1) &&  (y== HEIGHT - 1) ? true : false;
                cells[x][y] = new Cell(x, y, start, end);
            }
        }
        totalCell = HEIGHT*WIDTH-1;
    }
    
    
    public void createMaze(){
    
        Cell oldCell = null, nextCell = null;
        stack.push(cells[0][0]); // intialize the Cell
        while(countVisitedCell != totalCell){
            if(!stack.isEmpty()){
                nextCell = stack.pop();
                if(cellVaild(nextCell)){
                    updateVisitedStack(nextCell);
                    
                    if(oldCell == null)  // breaking the wall
                        destroyCommonWalls(null,nextCell);
                    else
                        destroyCommonWalls(oldCell,nextCell);

                    ArrayList <Cell> neighbour = findAllNeighbour(nextCell);
                    addRandomllyToStack(neighbour);
                }

                oldCell = nextCell;
            }
            
        }
        System.out.println("Total cell counted "+countVisitedCell);

    }

    public Cell[][] getMaze(){
        return cells;
    }
    


    public void updateVisitedStack(Cell cell){
        visitedCellStack.add(cell);
        countVisitedCell++;
    }

    public boolean isInStack(Cell cell){
        if(visitedCellStack.isEmpty()) return false;
        return (visitedCellStack.search(cell) > 0) ? true : false;
    }

    public boolean cellVaild(Cell cell){
        if(isInStack(cell)) return false;
        return true;
    }

    public ArrayList<Cell> findAllNeighbour(Cell node){
        ArrayList<Cell> list = new ArrayList<Cell>();
        for (int [] vector   : vectors) {
            int newX = node.getPosX()+vector[0];
            int newY = node.getPosY()+vector[1];
            //  function just count the neighbour around the maze having value 1
            if(outOfBox(newX,newY)){
                list.add(cells[newX][newY]);
            }          
        }
        return list;
    }
    
    
    private void destroyCommonWalls(Cell oldCell, Cell newCell){

        if(oldCell != null){
            if(oldCell.getPosX() == newCell.getPosX()){ // the cells have the same x position.
                    if(oldCell.getPosY() > newCell.getPosY()){ // cell a is above cell b.
                        oldCell.destroyWall(Side.UP);
                        newCell.destroyWall(Side.DOWN);
                    } else {
                        oldCell.destroyWall(Side.DOWN);
                        newCell.destroyWall(Side.UP);
                    }
            } else { // the cells have the same y position. 
                    if(oldCell.getPosX() > newCell.getPosX()){ // cell a is at the right side of cell b.
                        oldCell.destroyWall(Side.LEFT);
                        newCell.destroyWall(Side.RIGHT);
                    } else {
                        oldCell.destroyWall(Side.RIGHT);
                        newCell.destroyWall(Side.LEFT);
                    }
            }
        }
    }


    public void addRandomllyToStack(ArrayList <Cell> list){
        int index;
        while(!list.isEmpty()){
            index = random.nextInt(list.size());
            Cell tmp = list.remove(index);
            stack.push(tmp);    
        }
    }


     public boolean outOfBox(int x, int y){
        return x < WIDTH && x >= 0 && y < HEIGHT && y >= 0;
    }
    

}
