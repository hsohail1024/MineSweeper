//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (descriptive title of the program making use of this file)
// Files:           (a list of all source files used by that program)
// Course:          (course number, term, and year)
//
// Author:          Huzaifa Sohail
// Email:           hsohail@wisc.edu
// Lecturer's Name: Jim Williams
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Freddy
// Partner Email:   
// Lecturer's Name: Jim Williams
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X__ Write-up states that pair programming is allowed for this assignment.
//   _X__ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;
    
    public class MineSweeper1 {
    
        public static void main(String[] args) {
            boolean flag = false;
            while (!flag) {
            
            Scanner scnr = new Scanner(System.in);
            
            int width = 0;
            int height = 0;
            String hd = "height";
            String wd = "width";
            int w;
            int h;
            String row = "row";
            String column = "column";
            
            System.out.println("Welcome to Mine Sweeper!");
            System.out.print("What width of map would you like" + " (" + Config.MIN_SIZE + " - " +  Config.MAX_SIZE + "): ");   
            
            w = promptUser(scnr, wd, Config.MIN_SIZE, Config.MAX_SIZE);
            System.out.print("What height of map would you like" + " (" + Config.MIN_SIZE + " - " +  Config.MAX_SIZE + "): ");   

            h = promptUser(scnr, hd, Config.MIN_SIZE, Config.MAX_SIZE);
            char[][] map = new char[w][h]; 
            eraseMap(map);
            simplePrintMap(map);
            System.out.print("row: ");
            int r = promptUser(scnr, row , 1, w);
            System.out.print("column: ");
            int c = promptUser(scnr, column , 1, h);
            map[r-1][c-1] = Config.NO_NEARBY_MINE;
            simplePrintMap(map);
            
            System.out.print("Would you like to play again (y/n)?");
            String a = scnr.next();
            if(a.equals("n")) {
                System.out.print("Thank you for playing Mine Sweeper!");
                flag = true;
            }
            else if(a.equals("y")) {}
            }
            
        }
            
            
            public static int promptUser(Scanner in, String prompt, int min, int max) {
                int w = 0;
                
                //System.out.println("Welcome to Mine Sweeper!");
                
                
                
                
                while (in.hasNext()) {
                    
                    if (in.hasNextInt()) {
                        w = in.nextInt();
                    
                        if (w >= min && w <= max) {
                           // return w;
                            break;
                        }
                        System.out.println("Expected a number from " + min + " to " +  max );
                        System.out.print("What " + prompt + " of map would you like" + " (" + min + " - " +  max + "): ");
                    }
                    else {
                        System.out.println("Expected a number from " + min + " to " +  max);
                    System.out.print("What " + prompt + " of map would you like " + " (" + min + " - " +  max + "): " );
                    in.next();
                    }
                }
               return w;
                
            }
            
            public static void eraseMap(char[][] map) {
                int i =1;
                int p = 1;
                
                
                for(i=0; i<map[0].length; i++) {
                    for(p=1 ; p<= map.length ;p++ ) {
                       map[p-1][i]  = Config.UNSWEPT;
                    }
                   
                }

                return; 
            }
            
            public static void simplePrintMap(char[][] map) {
                int i =1;
                int p = 1;
                
                
                for(i=0; i<map[0].length; i++) {
                    for(p=1 ; p<= map.length ;p++ ) {
                        System.out.print(map[p-1][i]);
                    //   map[p][i]  = Config.UNSWEPT;
                    }
                   System.out.println("");
                }
                
                return; //FIXME
            }
            
            
            
            
    
    
    

       
    }

