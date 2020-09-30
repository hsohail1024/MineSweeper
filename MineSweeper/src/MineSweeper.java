
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Text based Mine-Sweeper Game
// Files:           UTF-8
// Course:          CS 200, Fall 2017
//
// Author:          Huzaifa Sohail
// Email:           hsohail@wisc.edu
// Lecturer's Name: Jim Williams
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Rahul Mital
// Partner Email:   rmital@wisc.edu
// Lecturer's Name: Jim Williams
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X__ Write-up states that pair programming is allowed for this assignment.
//   _X__ We have both read and understand the course Pair Programming Policy.
//   _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;
import java.util.Scanner;

/**
 * This class contains the code for the text based Mine-sweeper game. For
 * example, the following is a frame of size height 6 and width 5.
 * 
 * Mines: 8 
 *    1------ 5 
 *  1 . . . . .
 *  | . . . . . 
 *  | . . . . . 
 *  | . . . . . 
 *  5 . . . . . 
 *  6 . . . . .
 * 
 * Bugs: none known
 *
 * @author Huzaifa Sohail and Rahul Mital
 */

public class MineSweeper {

    /**
     * This is the main method for Mine Sweeper game! This method contains the
     * within game and play again loops and calls the various supporting methods.
     */
    public static void main(String[] args) {

        System.out.println("Welcome to Mine Sweeper!");
        Scanner in = new Scanner(System.in);
        Random randGen = new Random(Config.SEED);

        boolean flag = false;
        while (!flag) { // loops the game until flag is true

            String hd = "height";
            String wd = "width";
            int w = 0;
            int h = 0;
            String row = "row";
            String column = "column";

            System.out.print("What width of map would you like" + " (" + Config.MIN_SIZE + " - " 
                    + Config.MAX_SIZE + "): ");
            h = promptUser(in, wd, Config.MIN_SIZE, Config.MAX_SIZE); // stores
                                                                      // width of map (user input)
                                                                      // into h by using promptUser
                                                                      // to make sure userInput is 
                                                                      //within pre-set conditions

            System.out.print("What height of map would you like" + " (" + Config.MIN_SIZE + " - " 
                    + Config.MAX_SIZE + "): ");
            w = promptUser(in, hd, Config.MIN_SIZE, Config.MAX_SIZE); // stores height of map
                                                                      //(user input) into w by using
                                                                      // promptUser to make sure 
                                                                      //userInput is
                                                                      // within pre-set conditions

            boolean[][] mines = new boolean[w][h]; // two dimension array for mines location
            char[][] map = new char[w][h]; // user map that prints out and is visible to players

            int totalMines = placeMines(mines, randGen);
            eraseMap(map);
            boolean gameResult = true;

            while (gameResult) {

                System.out.println("\nMines: " + totalMines);
                printMap(map);

                System.out.print("row: ");
                int r = promptUser(in, column, 1, w); // row coordinate that user inputs
                System.out.print("column: ");
                int c = promptUser(in, row, 1, h); // column coordinate that user inputs

                c -= 1; // allows for the user to put in any number between 1 and the max column and
                        // subtracts one so that it fits with the array bounds
                r -= 1; // allows for the user to put in any number between 1
                        // and the max row and subtracts one so that it fits with the array bounds

                int sweepVal = sweepLocation(map, mines, r, c);
                if (sweepVal == 0) {
                    sweepAllNeighbors(map, mines, r, c);
                }
                if (sweepVal == -1) { // location swept contains a mine, if statement executes
                    showMines(map, mines);
                    printMap(map);
                    System.out.println("Sorry, you lost.");
                    gameResult = false;
                } else if (sweepVal == -3) {
                } else if (sweepVal == -2) {
                } else if (sweepVal >= 0) {
                    if (allSafeLocationsSwept(map, mines)) {
                        gameResult = false;
                        showMines(map, mines);
                        printMap(map);
                        System.out.println("You Win!");
                    }
                }
            }

            Character letter = ' ';
            String playAgain;

            System.out.print("Would you like to play again (y/n)? ");
            playAgain = in.next();
            letter = playAgain.charAt(0); // takes in the first character of user input to determine
                                          // if game will be played again or not

            if (letter.equals('Y') || letter.equals('y')) {
                flag = false; // game is restarted
            } else {
                System.out.println("Thank you for playing Mine Sweeper!");
                flag = true; // while loop is false and ends the program
            }

        }
    }

    /**
     * This method prompts the user for a number, verifies that it is between min
     * and max, inclusive, before returning the number.
     * 
     * If the number entered is not between min and max then the user is shown an
     * error message and given another opportunity to enter a number. If min is 1
     * and max is 5 the error message is: Expected a number from 1 to 5.
     * 
     * @param in  The reference to the instance of Scanner created in main.
     * @param prompt  The text prompt that is shown once to the user.
     * @param min  The minimum value that the user must enter.
     * @param max  The maximum value that the user must enter.
     * @return The integer that the user entered that is between min and max,
     *         inclusive.
     */
    public static int promptUser(Scanner in, String prompt, int min, int max) {

        int numberIn = 0;

        while (in.hasNext()) { // checks if user has given an input
            if (in.hasNextInt()) { // checks if user input is an integer
                numberIn = in.nextInt(); // assigns input to numberIn
                if (numberIn >= min && numberIn <= max) { // checks to see if user input is within
                                                          // the min and max range
                    break;
                }
                System.out.println(
                    "Expected a number from " + min + " to " + max + "."); // if number is not within
                                                                           // the range, prints 
                                                                           //this statement
            } else {
                System.out.println(
                    "Expected a number from " + min + " to " + max + "."); // if user inputs 
                                                                           //anything other than an
                                                                           // integer, this prints 
                                                                           //the statement
                in.next();
                in.nextLine();
            }
        }
        return numberIn;
    }

    /**
     * This initializes the map char array passed in such that all elements have the
     * Config.UNSWEPT character. Within this method only use the actual size of the
     * array. Don't assume the size of the array. This method does not print out
     * anything. This method does not return anything.
     * 
     * @param map  An allocated array. After this method call all elements in the
     *            array have the same character, Config.UNSWEPT.
     */
    public static void eraseMap(char[][] map) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Config.UNSWEPT; // Iterates through map and assigns each coordinate 
                                            //with Config.UNSWEPT
            }
        }
        return;
    }

    /**
     * This prints out a version of the map without the row and column numbers. A
     * map with width 4 and height 6 would look like the following:
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . . 
     * For each location in the map a space is
     * printed followed by the character in the map location.
     * 
     * @param map  The map to print out.
     */
    public static void simplePrintMap(char[][] map) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(" " + map[i][j]); // prints out the map with the UNSWEPT character
            }
            System.out.println();
        }
        return;
    }

    /**
     * This prints out the map. This shows numbers of the columns and rows on the
     * top and left side, respectively. map[0][0] is row 1, column 1 when shown to
     * the user. The first column, last column and every multiple of 5 are shown.
     * 
     * To print out a 2 digit number with a leading space if the number is less than
     * 10, you may use: System.out.printf("%2d", 1);
     * 
     * @param map  The map to print out.
     */
    public static void printMap(char[][] map) {
        System.out.print("  ");

        for (int i = 1; i <= map[0].length; i++) {
            if (i % 5 == 0 || i == map[0].length || i == 1) {
                System.out.printf("%2d", i); // prints out integer for first column, multiples of 
                                             // 5 columns and last column
            } else {
                System.out.print("--");
            }
        }
        System.out.println();

        for (int i = 1; i <= map.length; i++) {
            if (i % 5 == 0 || i == map.length || i == 1) {
                System.out.printf("%2d", i); // prints out integer for first row, multiples of 5 
                                             //rows, and last row
            } else {
                System.out.print(" |");
            }
            for (int j = 1; j <= map[i - 1].length; j++) {
                System.out.print(" " + map[i - 1][j - 1]);
            }
            System.out.println();
        }
        return;
    }

    /**
     * This method initializes the boolean mines array passed in. A true value for
     * an element in the mines array means that location has a mine, false means the
     * location does not have a mine. The MINE_PROBABILITY is used to determine
     * whether a particular location has a mine. The randGen parameter contains the
     * reference to the instance of Random created in the main method.
     * 
     * Access the elements in the mines array with row then column (e.g.,
     * mines[row][col]).
     * 
     * Access the elements in the array solely using the actual size of the mines
     * array passed in, do not use constants.
     * 
     * A MINE_PROBABILITY of 0.3 indicates that a particular location has a 30%
     * chance of having a mine. For each location the result of randGen.nextFloat()
     * < Config.MINE_PROBABILITY determines whether that location has a mine.
     * 
     * This method does not print out anything.
     * 
     * @param mines  The array of boolean that tracks the locations of the mines.
     * @param randGen  The reference to the instance of the Random number generator
     *            created in the main method.
     * @return The number of mines in the mines array.
     */
    public static int placeMines(boolean[][] mines, Random randGen) {
        int minesNum = 0;

        for (int i = 0; i < mines.length; i++) {
            for (int j = 0; j < mines[i].length; j++) {
                if (randGen.nextFloat() <= Config.MINE_PROBABILITY) {
                    mines[i][j] = true; // if randGen is less than or equal to MINE_PROBABILITY,
                                        //mine[i][j] is assigned with true which means there 
                                        //is a mine at that location
                    ++minesNum;
                }
            }
        }
        return minesNum;
    }

    /**
     * This method returns the number of mines in the 8 neighboring locations. For
     * locations along an edge of the array, neighboring locations outside of the
     * mines array do not contain mines. This method does not print out anything.
     * 
     * If the row or col arguments are outside the mines array, then return -1. This
     * method (or any part of this program) should not use exception handling.
     * 
     * @param mines  The array showing where the mines are located.
     * @param row  The row, 0-based, of a location.
     * @param col  The col, 0-based, of a location.
     * @return  The number of mines in the 8 surrounding locations or -1 if row or
     *         col are invalid.
     */
    public static int numNearbyMines(boolean[][] mines, int row, int col) {
        int i = 0;
        int j = 0;
        int counter = 0;
        if (row < 0 || row >= mines.length || col < 0 || col >= mines[0].length) { // if user row 
                                                                                   //and col is less
                                                                                   // than 0 or 
                                                                                   //greater than
                                                                                   // mine map length
                                                                                   // return -1
            counter = -1;
        } else {
            for (i = row - 1; i <= row + 1; i++) { // checks the previous and next row of the selected
                                                   // user row and column
                for (j = col - 1; j <= col + 1; j++) { // checks the previous and next column of the
                                                       // selected user row and column
                    if (i == row && j == col) { // when iteration reaches the user input row/column
                    } else if (i < 0 || i >= mines.length || j < 0 || j >= mines[0].length) {
                    } else if (mines[i][j] == true) { // if a mine is present in the neighboring
                                                      // locations, increment counter by 1 and
                                                      // return counter
                        ++counter;
                    }
                }
            }
        }
        return counter;
    }

    /**
     * This updates the map with each unswept mine shown with the Config.HIDDEN_MINE
     * character. Swept mines will already be mapped and so should not be changed.
     * This method does not print out anything.
     * 
     * @param map An array containing the map. On return the map shows unswept
     *            mines.
     * @param mines  An array indicating which locations have mines. No changes are
     *            made to the mines array.
     */
    public static void showMines(char[][] map, boolean[][] mines) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == Config.SWEPT_MINE) {
                } else if (mines[i][j]) {
                    map[i][j] = Config.HIDDEN_MINE; // once user hits a mine or wins, all mines
                                                    // present on map will be shown to user
                }
            }
        }

        return;
    }

    /**
     * Returns whether all the safe (non-mine) locations have been swept. In other
     * words, whether all unswept locations have mines. This method does not print
     * out anything.
     * 
     * @param map  The map showing touched locations that is unchanged by this
     *            method.
     * @param mines  The mines array that is unchanged by this method.
     * @return whether all non-mine locations are swept.
     */
    public static boolean allSafeLocationsSwept(char[][] map, boolean[][] mines) {
        boolean ret = true;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == Config.UNSWEPT) { // checks to see if the remaining locations on
                                                   // map are of Config.UNSWEPT
                    if (!mines[i][j]) { // if map contains a Config.UNSWEPT and there's no mine,
                                        // returns false
                        ret = false;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * This method sweeps the specified row and col. 
     *   - If the row and col specify a location outside the map array then 
     *     return -3 without changing the map. 
     *   - If the location has already been swept then return -2 without changing
     *     the map.
     *   - If there is a mine in the location then the map for the corresponding
     *     location is updated with Config.SWEPT_MINE and return -1. 
     *   - If there is not a mine then the number of nearby mines is determined 
     *     by calling the numNearbyMines method. 
     *        - If there are 1 to 8 nearby mines then the map is updated with 
     *          the characters '1'..'8' indicating the number of nearby mines. 
     *        - If the location has 0 nearby mines then the map is updated with the
     *          Config.NO_NEARBY_MINE character. 
     *        - Return the number of nearbyMines.
     * 
     * @param map  The map showing swept locations.
     * @param mines  The array showing where the mines are located.
     * @param row  The row, 0-based, of a location.
     * @param col  The col, 0-based, of a location.
     * @return the number of nearby mines, -1 if the location is a mine, -2 if the
     *         location has already been swept, -3 if the location is off the map.
     */
    public static int sweepLocation(char[][] map, boolean[][] mines, int row, int col) {
        int minesNearby = 0;
        if (row < 0 || row >= map.length || col < 0 || col >= map[row].length) {
            minesNearby = -3; // if row and column are outside the map array
        } else if (map[row][col] != Config.UNSWEPT) {
            minesNearby = -2; // if location has already been swept
        } else if (mines[row][col]) {
            map[row][col] = Config.SWEPT_MINE; // swept mine is assigned to map if mines[row][col]
                                               // is true
            minesNearby = -1; // if location has a mine
        } else if (!mines[row][col]) { // no mines exist
            minesNearby = numNearbyMines(mines, row, col); // calls numNearByMines method to
                                                           // determine neighboring mines
            map[row][col] = (char) (minesNearby + '0');
            if (map[row][col] == '0') {
                map[row][col] = Config.NO_NEARBY_MINE; // when char 0 is assigned to map, map
                                                       // is updated with NO_NEARBY_MINES
            }
        }
        return minesNearby;
    }

    /**
     * This method iterates through all 8 neighboring locations and calls
     * sweepLocation for each. It does not call sweepLocation for its own location,
     * just the neighboring locations.
     * 
     * @param map  The map showing touched locations.
     * @param mines  The array showing where the mines are located.
     * @param row  The row, 0-based, of a location.
     * @param col  The col, 0-based, of a location.
     */
    public static void sweepAllNeighbors(char[][] map, boolean[][] mines, int row, int col) {
        int i;
        int j;

        for (i = row - 1; i <= row + 1; i++) {
            for (j = col - 1; j <= col + 1; j++) {
                if (!(i == row && j == col)) {
                    sweepLocation(map, mines, i, j); // calls sweepLocation for all surrounding
                                                     // locations except user row and col input
                }
            }
        }
        return;
    }
}
