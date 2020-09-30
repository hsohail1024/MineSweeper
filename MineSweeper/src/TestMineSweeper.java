/**
 * This file contains testing methods for the MineSweeper project.
 * These methods are intended to serve several objectives:
 * 1) provide an example of a way to incrementally test your code
 * 2) provide example method calls for the MineSweeper methods
 * 3) provide examples of creating, accessing and modifying arrays
 *    
 * Toward these objectives, the expectation is that part of the 
 * grade for the MineSweeper project is to write some tests and
 * write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments 
 * you feel would be useful.
 * 
 * Some of the provided comments within this file explain
 * Java code as they are intended to help you learn Java.  However,
 * your comments and comments in professional code, should
 * summarize the purpose of the code, not explain the meaning
 * of the specific Java constructs.
 *    
 */

import java.util.Random;
import java.util.Scanner;


/**
 * This class contains a few methods for testing methods in the MineSweeper
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Jim Williams
 * @author Rahul Mital and Huzaifa Sohail
 *
 */
public class TestMineSweeper {

    /**
     * This is the main method that runs the various tests. Uncomment the tests
     * when you are ready for them to run.
     * 
     * @param args  (unused)
     */
    public static void main(String [] args) {

        // Milestone 1
        //testing the main loop, promptUser and simplePrintMap, since they have
        //a variety of output, is probably easiest using a tool such as diffchecker.com
        //and comparing to the examples provided.
        //testEraseMap();
        
        // Milestone 2
        //testPlaceMines();
        //testNumNearbyMines();
        //testShowMines();
        //testAllSafeLocationsSwept();
        
        // Milestone 3
        //testSweepLocation();
        //testSweepAllNeighbors();
        //testing printMap, due to printed output is probably easiest using a 
        //tool such as diffchecker.com and comparing to the examples provided.
    }
    
    /**
     * This is intended to run some tests on the eraseMap method. 
     * Checks eraseMap() to make sure all elements of the map array 
     * (size determined by user) are the Config.UNSWEPT character
     */
    private static void testEraseMap() {
        //Review the eraseMap method header carefully and write
        //tests to check whether the method is working correctly.
       boolean error = false;
       final int ROW_NUM = 8;
       final int COL_NUM = 9;
       
       char[][] map = new char[ROW_NUM][COL_NUM];
       
       MineSweeper.eraseMap(map);
       
       for (int i = 0; i < map.length; i++) {
           for (int j = 0; j < map[i].length; j++) {
               if (map[i][j] != Config.UNSWEPT) {
                   error = true;
               }
           }
       }
       
       
        if (error) {
            System.out.println("testEraseMap: failed");
        } else {
            System.out.println("testEraseMap: passed");
        }
        
        
    }
    /*
     * Calculate the number of elements in array1 with different values from 
     * those in array2
     */
    private static int getDiffCells(boolean[][] array1, boolean[][] array2) {
        int counter = 0;
        for(int i = 0 ; i < array1.length; i++){
            for(int j = 0 ; j < array1[i].length; j++){
                if(array1[i][j] != array2[i][j]) 
                    counter++;
            }
        }
        return counter;
    }    
    
    /**
     * This runs some tests on the placeMines method. 
     * Checks to see if program uses randGen setSeed function and MINE_PROBABILITY 
     * to place the mines at their respective locations within the map by comparing it 
     * to a pre-set boolean map
     */
    private static void testPlaceMines() {
        boolean error = false;
        
        //These expected values were generated with a Random instance set with
        //seed of 123 and MINE_PROBABILITY is 0.2.
        boolean [][] expectedMap = new boolean[][]{
            {false,false,false,false,false},
            {false,false,false,false,false},
            {false,false,false,true,true},
            {false,false,false,false,false},
            {false,false,true,false,false}};
        int expectedNumMines = 3;
            
        Random studentRandGen = new Random( 123);
        boolean [][] studentMap = new boolean[5][5];
        int studentNumMines = MineSweeper.placeMines( studentMap, studentRandGen);
        
        if ( studentNumMines != expectedNumMines) {
            error = true;
            System.out.println("testPlaceMines 1: expectedNumMines=" + expectedNumMines + " studentNumMines=" + studentNumMines);
        }
        int diffCells = getDiffCells( expectedMap, studentMap);
        if ( diffCells != 0) {
            error = true;
            System.out.println("testPlaceMines 2: mine map differs.");
        }

        // Can you think of other tests that would make sure your method works correctly?
        // if so, add them.

        if (error) {
            System.out.println("testPlaceMines: failed");
        } else {
            System.out.println("testPlaceMines: passed");
        }        
        
    }
    
    /**
     * This runs some tests on the numNearbyMines method. 
     * 1. Makes sure that numNearbyMines accurately counts the mines around the
     * user input, starting from an index array position
     * 
     */
    private static void testNumNearbyMines() {
        boolean error = false;

        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,true,true},
            {false,false,false,false,false},
            {false,false,true,false,false}};
        int numNearbyMines = MineSweeper.numNearbyMines( mines, 1, 1);
        
        if ( numNearbyMines != 2) {
            error = true;
            System.out.println("testNumNearbyMines 1: numNearbyMines=" + numNearbyMines + " expected mines=2");
        }
        
       numNearbyMines = MineSweeper.numNearbyMines( mines, 2, 1);
        
        if ( numNearbyMines != 0) {
            error = true;
            System.out.println("testNumNearbyMines 2: numNearbyMines=" + numNearbyMines + " expected mines=0");
        }        
        
        // Can you think of other tests that would make sure your method works correctly?
        // if so, add them.

        if (error) {
            System.out.println("testNumNearbyMines: failed");
        } else {
            System.out.println("testNumNearbyMines: passed");
        }
    }
    
    /**
     * This runs some tests on the showMines method. 
     * 1. This test makes sure that all mines are shown, and only shows a swept mine if
     * the user swept that location. Program output is tested using a known map and known mine locations. 
     * 2.
     */
    private static void testShowMines() {
        boolean error = false;
        

        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,false,false},
            {false,false,false,false,false},
            {false,false,true,false,false}};
            
        char [][] map = new char[mines.length][mines[0].length];
        map[0][2] = Config.UNSWEPT;
        map[2][1] = Config.UNSWEPT;
        map[4][2] = Config.UNSWEPT;
        
        MineSweeper.showMines( map, mines);
        if ( !(map[0][2] == Config.HIDDEN_MINE && map[2][1] == Config.HIDDEN_MINE && map[4][2] == Config.HIDDEN_MINE)) {
            error = true;
            System.out.println("testShowMines 1: a mine not mapped");
        }
        if ( map[0][0] == Config.HIDDEN_MINE || map[0][4] == Config.HIDDEN_MINE || map[4][4] == Config.HIDDEN_MINE) {
            error = true;
            System.out.println("testShowMines 2: unexpected showing of mine.");
        }
        
        // Can you think of other tests that would make sure your method works correctly?
        // if so, add them.

        if (error) {
            System.out.println("testShowMines: failed");
        } else {
            System.out.println("testShowMines: passed");
        }        
    }    
    
    /**
     * This is intended to run some tests on the allSafeLocationsSwept method.
     * 
     * This test check to make sure that allSafeLocationsSwept finds any unswept locations without mines
     * to see if the user should continue entering in locations.
     */
    private static void testAllSafeLocationsSwept() {
        //Review the allSafeLocationsSwept method header carefully and write
        //tests to check whether the method is working correctly.
        boolean error = false;
        boolean [][] mines1 = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,false,false},
            {false,false,false,false,false},
            {false,false,true,false,false}};
            
            char[][] map1 = new char[mines1.length][mines1[0].length];
            
            for(int i = 0; i < map1.length; i++ ) {
                for (int j = 0; j < map1[i].length; j++ ) {
                    map1[i][j] = '0';
                }
                }
            map1[0][2] = Config.UNSWEPT;
            map1[2][1] = Config.UNSWEPT;
            map1[4][2] = Config.UNSWEPT;
            
        error = MineSweeper.allSafeLocationsSwept(map1, mines1);
        
        
        if (!error) {
            System.out.println("testAllSafeLocationsSwept: failed");
        } else {
            System.out.println("testAllSafeLocationsSwept: passed");
        }
    }      

    
    /**
     * This is intended to run some tests on the sweepLocation method. 
     * 1. This tests ensures that sweepLocation() accurately returns the correct numNearbyMines and
     * assigns the right value to map based on the mines boolean map
     * 2.
     */
    private static void testSweepLocation() {
        //Review the sweepLocation method header carefully and write
        //tests to check whether the method is working correctly.
        boolean error = false;
        
        boolean [][] mines2 = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,false,false},
            {false,false,false,false,false},
            {false,false,true,false,false}};
            
            char[][] map2 = new char[mines2.length][mines2[0].length];
            for(int i = 0; i < map2.length; i++ ) {
                for (int j = 0; j < map2[i].length; j++) {
                    map2[i][j] = Config.UNSWEPT;
                }
                }
        
        
        int a = MineSweeper.sweepLocation(map2, mines2, 1, map2[1].length);
        
        if (a != -3) {
            error = true;
            System.out.println("Works");
        }
        
        a = MineSweeper.sweepLocation(map2, mines2, 0, 2);
        
        if (a != -1) {
            error = true;
            System.out.println("Works");
        }
        map2[0][1] = '1';
        a = MineSweeper.sweepLocation(map2, mines2, 0, 1);
        if (a != -2) {
            error = true;
            System.out.println("Works");
        }
        a = MineSweeper.sweepLocation(map2, mines2, 0, 3);
        if (a < 0) {
            error = true;
            System.out.println("Works");
        }
        
        
        
        if (error) {
            System.out.println("testSweepLocation: failed");
         } else {
            System.out.println("testSweepLocation: passed");
         }
    }      
    
    /**
     * This is intended to run some tests on the sweepAllNeighbors method. 
     * 1. This test ensures that sweepAllNeighbors sweeps the surrounding values if the chosen
     * location has no mines nearby. Also ensures correct calling of sweepLocation method.
     * 2.
     */
    private static void testSweepAllNeighbors() {
        //Review the sweepAllNeighbors method header carefully and write
        //tests to check whether the method is working correctly.
        boolean error = false;
        boolean [][] mines = new boolean[][]{
            {false,false,true,false,false},
            {false,false,false,false,false},
            {false,true,false,false,false},
            {false,false,false,false,false},
            {false,false,true,false,false}};
            
            char[][] map = new char[mines.length][mines[0].length];
            
           for(int i = 0; i < map.length; i++ ) {
                for (int j = 0; j < map[i].length; j++ ) {
                    map[i][j] = Config.UNSWEPT;
                }
                }
                
            
        MineSweeper.sweepAllNeighbors(map, mines, 0, 0);
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (!(i == 0 && j == 0)) {
                    if (map[i][j] != '1') {
                        error = false;
                    }
                }
                }
            }
        if (error) {
            System.out.println("testSweepAllNeighbors: failed");
            
        } else {
            System.out.println("testSweepAllNeighbors: passed");
            
        }
    }      
}
