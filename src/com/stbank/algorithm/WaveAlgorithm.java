package com.stbank.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.stbank.model.Cell;

public class WaveAlgorithm {
	private  int xS, yS;       			// (x,y) for the Start point
	private  int xE, yE;       			// (x,y) for the End point
	private  List<Cell> path;
	private  char matrix[][] = null;  	//  this is the chat matrix and initialized  from  file matr.txt
	
	private int  LengthOfPath =0;
	
	



	public WaveAlgorithm(char[][] matrix) {
		super();
		this.matrix = matrix;
		coordSandE(matrix);
		LengthOfPath = getLengthOfPath(matrix, matrix.length, matrix[0].length);
		fillInTheVisited();
		makePath();
		
	}

	public int getLengthOfPath() {
		return LengthOfPath;
	}
	public void setLengthOfPath(int lengthOfPath) {
		LengthOfPath = lengthOfPath;
	}

	private  int[][] grid = null;      //  this is itn matrix for  calculations
	

//	/**
//	   * This method Prints  int [][] matrixC on the console
//	   * 
//	   * @param matrix2
//	 */
//	public  void printMmatrix(char[][] matrix2 ){
//		for(int a_i=0; a_i < matrix2.length; a_i++){
//  		for(int a_j=0; a_j < matrix2[a_i].length; a_j++){
//  			//System.out.print(   ( "("+a_i + "," + a_j + ")=" + matr[a_i][a_j] + "          ").substring(0,10)   )   ;
//  			System.out.print(   ( matrix2[a_i][a_j] + "      ").substring(0,2)   )   ;
//  		}
//  		System.out.println();
//  	}
//	}
	

	
	/**
	   * This method Creates a list of  the neighbors of  cell p  in  the  working area 
	   * 
	   * @params Cell p, int row, int col
	 */
	public   List<Cell> getNeighbours(Cell p, int row, int col) {
		List<Cell> neighours = new ArrayList<Cell>();

		Cell posRight = p.getRight();
		if (posRight.row >= 0 && posRight.row < row && posRight.col >= 0 && posRight.col < col)
			neighours.add(posRight);
		
		Cell posLeft = p.getLeft();
		if (posLeft.row >= 0 && posLeft.row < row && posLeft.col >= 0 && posLeft.col < col)
			neighours.add(posLeft);

		Cell posUp = p.getUp();
		if (posUp.row >= 0 && posUp.row < row && posUp.col >= 0 && posUp.col < col)
			neighours.add(posUp);

		Cell posDown = p.getBottom();
		if (posDown.row >= 0 && posDown.row < row && posDown.col >= 0 && posDown.col < col)
			neighours.add(posDown);
		return neighours;
	}
	
	/**
	   * Method to check if it is possible to go to position (row, col)
	   * from current position. The method returns false if the cell
	   * not a valid position or has value 0 or it is already visited i.e  value is >0
	   * @params int d, int row, int col
	 */
	private  boolean isValid(int d, int row, int col) {
		return (row >= 0) && (row < grid.length) && (col >= 0) && (col < grid[0].length) && grid[row][col] == d - 1;
	}
	
	/**
	   * Finds Shortest Possible Route in a matrix matrix from source
	   * cell S(xS, yS) to destination cell E(xE, yE)
	   * 
	   * @params char[][] arr, int row, int col
	 */
	public  int getLengthOfPath(char[][] arr, int row, int col) {
	
		int dist = 0;
		grid = new int[row][col];
	
		for (int a_i = 0; a_i < arr.length; a_i++) {
			for (int a_j = 0; a_j < arr[a_i].length; a_j++) {
				if (arr[a_i][a_j] == 'W')
					grid[a_i][a_j] = -1;
			}
			// System.out.println();
		}
	
		//for PriorityQueue  we must implement Comparator
		PriorityQueue<Cell> queue = new PriorityQueue<Cell>(col * row, new Comparator<Cell>() {
	
			@Override
			public int compare(Cell o1, Cell o2) {
				if (grid[o1.row][o1.col] < grid[o2.row][o2.col])
					return -1;
				else if (grid[o1.row][o1.col] > grid[o2.row][o2.col])
					return 1;
				else
					return 0;
			}
		});
	
		queue.offer(new Cell(xS, yS));
		grid[xS][yS] = 1;
		
		// run till queue is not empty	
		while (!queue.isEmpty()) {
	
			Cell curC = queue.poll();
			List<Cell> ns = getNeighbours(curC, row, col);
	
			for (Cell n : ns) {
	
				if (!(arr[n.row][n.col] == 'W') && grid[n.row][n.col] == 0 ) {
	
					grid[n.row][n.col] = grid[curC.row][curC.col] + 1;
					queue.offer(n);
				}
				//for debugging
	            //System.out.println("________________________________"); 
	            //printMmatrix(arr);
				//printMmatrix(grid);
				//System.out.println("________________________________");
				
				if (arr[n.row][n.col] == 'E') {
					dist = grid[curC.row][curC.col] + 1;
					path = backToS(dist, xE, yE);
					return dist;
				}
	
			}
	
		}
		return 0;
	}



	/**
	   * Finds Shortest Possible Route in a matrix matrixC from source
	   * cell S(xS, yS) to destination cell E(xE, yE)
	   * 
	   * @params char[][] arr, int row, int col
	 */
//	public  int getLengthOfPath(char[][] arr, int row, int col) {
//
//		int dist = 0;
//		grid = new int[row][col];
//
//		for (int a_i = 0; a_i < arr.length; a_i++) {
//			for (int a_j = 0; a_j < arr[a_i].length; a_j++) {
//				if (arr[a_i][a_j] == 'W')
//					grid[a_i][a_j] = -1;
//			}
//			// System.out.println();
//		}
//
//		//for PriorityQueue  we must implement Comparator
//		PriorityQueue<Cell> queue = new PriorityQueue<Cell>(col * row, new Comparator<Cell>() {
//
//			@Override
//			public int compare(Cell o1, Cell o2) {
//				if (grid[o1.row][o1.col] < grid[o2.row][o2.col])
//					return -1;
//				else if (grid[o1.row][o1.col] > grid[o2.row][o2.col])
//					return 1;
//				else
//					return 0;
//			}
//		});
//
//		queue.offer(new Cell(xS, yS));
//		grid[xS][yS] = 1;
//		
//		// run till queue is not empty	
//		while (!queue.isEmpty()) {
//
//			Cell curC = queue.poll();
//			List<Cell> ns = getNeighbours(curC, row, col);
//
//			for (Cell n : ns) {
//
//				if (!(arr[n.row][n.col] == 'W') && grid[n.row][n.col] == 0 ) {
//
//					grid[n.row][n.col] = grid[curC.row][curC.col] + 1;
//					queue.offer(n);
//				}
//				//for debugging
//              //System.out.println("________________________________"); 
//              //printMmatrix(arr);
//				//printMmatrix(grid);
//				//System.out.println("________________________________");
//				if (arr[n.row][n.col] == 'E') {
//					dist = grid[curC.row][curC.col] + 1;
//					path = backToS(dist, xE, yE);
//					//
//					return dist;
//				}
//
//			}
//
//		}
//		return 0;
//	}

	public  List<Cell> backToS(int d, int row, int col) {
		//printMmatrix(grid );
		int cx = row;
		int cy = col;
		path = new ArrayList<Cell>();
		int c = d - 1;
		// printMmatrix(grid );

		while (c != 1) {
			Cell curr = new Cell(cx, cy);
			List<Cell> ns = getNeighbours(curr, matrix.length, matrix[0].length);
			for (Cell n : ns) {
				boolean b = isValid(grid[cx][cy], n.row, n.col);
				if (b) {
					cx = n.row;
					cy = n.col;
					path.add(new Cell(cx, cy));
					break;
				}
			}
			c--;
		}

		return path;
	}
	
  //We are filling in the visited 
	public   void fillInTheVisited() {
		for (int a_i = 0; a_i < grid.length; a_i++) {
			for (int a_j = 0; a_j < grid[a_i].length; a_j++) {
				if ((grid[a_i][a_j] > 0) & (matrix[a_i][a_j] != 'W') & (matrix[a_i][a_j] != 'E') & (matrix[a_i][a_j] !='S'))      {
					matrix[a_i][a_j]='"';
				} 
				//System.out.print(matr[a_i][a_j] + " ");
			}
			//System.out.println();
		}
	}
	
	/**
	   * This method finds the coordinates of S and E
	   * if S or/and E do not  exist message "Please correct your input matrix! "
	   * @param matr
	 */
	public   void coordSandE(char[][] matr) {
		boolean foundS = false;
		boolean foundE = false;
		for (int a_i = 0; a_i < matr.length; a_i++) {
			for (int a_j = 0; a_j < matr[a_i].length; a_j++) {
				if (matr[a_i][a_j] == 'S') {
					xS = a_i;
					yS = a_j;
					foundS=true;
				}
				if (matr[a_i][a_j] == 'E') {
					xE = a_i;
					yE = a_j;
					foundE=true;
				}
			}
		}
		
		if (!(foundS&&foundE)) {
			System.err.println("No Starting or/and Ending points! ");
			System.err.println("Please correct your input matrix! ");
			System.exit(0);
		}
		
		
	}
	
	//mark the path with '*'
	public   void makePath() {
		for (Cell n : path) {
			matrix[n.row][n.col] = '*';
		}
	}


	
}
