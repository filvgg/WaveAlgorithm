package com.stbank; 

import java.util.Scanner;

import com.stbank.algorithm.WaveAlgorithm;
import com.stbank.util.DataMatrix;

public class ShortPaths {

	private  char matrix[][] = null;  //  this is the chat matrix and initialized  from  file matr.txt
	static DataMatrix  ddmatrix ;
	
	
	public static void waveMethod(char matrix[][]) {
		System.out.println("--------- Source matrix --------------");

		ddmatrix.printMmatrix();
		WaveAlgorithm waveAlgorithm =  new WaveAlgorithm(matrix); 

		int  LengthOfPath = waveAlgorithm.getLengthOfPath();
		if (LengthOfPath <= 0) {
			System.out.println("---------------------------------------");
			System.out.println("The length of the path in '*' is: " + (LengthOfPath-2));
			System.out.println(" ");
			System.err.println("No path from S to E ");
		} else {
			System.out.println("---------------------------------------");
			System.out.println("The length of the path in '*' is: " + (LengthOfPath-2) + "   (without  S and E)   "  );
			System.out.println("The numbers of moves from S to E are : " + (LengthOfPath-1)  );
			System.out.println("");
			System.out.print("The path is : '" );
			for (int i=0; i < LengthOfPath-2; i++) {
				System.out.print("*");
			}
			System.out.println("'");
			System.out.println("--------------------------------------");
			
			System.out.println("---------     Path      --------------");
			ddmatrix.printMmatrix();
		}
		
	}
	
	public static void method2() {
		System.out.println(" Dijkstra’s shortest path algorithm is Not Implemented ");
	}
	
	public static void method3() {
		System.out.println("Other  Algorithm  is Not Implemented ");
	}
	
	
	/**
	   * This method Prints  int [][] matrixC on the console
	   * 
	   * @param 
	 */
	
	public static int menu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/
        System.out.println("---------------------------------------------------------");
        System.out.println("Choose algorithm  ");
        System.out.println("---------------------------------------------------------");
        System.out.println("1 - Lee Algorithm shortest path algorithm (Implemented) ");
        System.out.println("2 - Dijkstra’s shortest path algorithm  (Not Implemented)");
        System.out.println("3 - Other  Algorithm is Not Implemented (Not Implemented)");
        System.out.println("4 - Quit");
        System.out.println(">");

        selection = input.nextInt();
        return selection;    
    }
	
	public static void main(String[] args) {
		
		ShortPaths  shortPaths  = new ShortPaths();
		shortPaths.ddmatrix = new DataMatrix("matr.txt");
		
		int userChoice;
        Scanner input = new Scanner(System.in);

        /*********************************************************/

        //userChoice = menu();

        while (true){
        	userChoice = menu();
            
             switch (userChoice) {
                 case 1: { 
                	 waveMethod(shortPaths.ddmatrix.getMatrix());
            		 break;
           		}
                 case 2: { method2(); break;}
                 case 3: { method3(); break;}
                 case 4: { 
                	 System.out.println(" === END === ");    
                	 System.exit(0); 
                 }
                 default: System.out.println("Wrong choice");
                 System.out.println(">");
             }
            
        }

	}

}
