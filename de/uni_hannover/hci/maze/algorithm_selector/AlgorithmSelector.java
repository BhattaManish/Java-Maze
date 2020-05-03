package de.uni_hannover.hci.maze.algorithm_selector;

import de.uni_hannover.hci.maze.algorithms.Algorithm;
import de.uni_hannover.hci.maze.random_kruskal_algorithm.*;
import de.uni_hannover.hci.maze.randomized_prim_algorithm.RandomizedPrimAlgorithm;
import de.uni_hannover.hci.maze.deep_search.DeepSearch;
import de.uni_hannover.hci.maze.deep_search.MazePrinter2;


public class AlgorithmSelector {
    
    // Zurzeit gibt es nur eine Algorithmusoption.
    // Später mehr Algorithmusmöglichkeiten hinzufügen.
    public static String getMazeFromAlgorithm(Algorithm x, int width, int height){
         String stringMaze = ""; 
         MazePrinter printer;
         if(x == Algorithm.RANDOM_KRUSKAL){
              RandomKruskalAlgorithm rka = new RandomKruskalAlgorithm(width, height);
              rka.createMaze();  
              printer = new MazePrinter();
              stringMaze = printer.convertMaze(rka.getMaze());
         }
         if(x == Algorithm.RANDOMIZED_PRIM_ALGORITHM){
             RandomizedPrimAlgorithm rpa = new RandomizedPrimAlgorithm(width, height);
             rpa.createMaze();
             printer = new MazePrinter();
             stringMaze = printer.convertMaze(rpa.getMaze());
         }
        if(x == Algorithm.DEEP_SEARCH){
            System.out.println("Entering the Algorithmusoption");
             DeepSearch rpa = new DeepSearch(width, height);
             rpa.createMaze();
             MazePrinter2 prt = new MazePrinter2();
             stringMaze = prt.convertMaze(rpa.getMaze());
         }
         return stringMaze;
    }

}