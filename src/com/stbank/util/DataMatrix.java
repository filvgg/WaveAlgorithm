package com.stbank.util; 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataMatrix {

	private int xS, yS; // (x,y) for the Start point
	private int xE, yE; // (x,y) for the End point
	private char matrix[][] = null; // this is the chat matrix and initialized
									// from file matr.txt

	private String fName;

	public DataMatrix(String fName) {
		super();
		this.fName = fName;
		matrix = readFile(fName);
	}

	public char[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(char[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * This method Detects the dimensions of the matrix and fills it
	 * 
	 * @param fName
	 */
	private char[][] readFile(String fName) {

		BufferedReader in = null;
		int n = 0;
		int m = 0;
		try {
			in = new BufferedReader(new FileReader(fName));
			String read = null;
			read = in.readLine();
			String[] splited = read.split("\\s+");
			m = splited.length;
			while ((read = in.readLine()) != null) {
				n++;
			}
		} catch (IOException e) {
			System.out.println("There was a problem: " + e);
			e.printStackTrace();

		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
		char matr[][] = new char[n + 1][m];

		n = 0;
		m = 0;

		try {
			in = new BufferedReader(new FileReader(fName));
			String read = null;
			while ((read = in.readLine()) != null) {
				String[] splited = read.split("\\s+");
				m = 0;
				for (String part : splited) {

					matr[n][m] = part.charAt(0);
					// Only symbols '.' , 'W' , 'S' and 'E' are allowed
					// separated by 'space'
					if (matr[n][m] == '.' || matr[n][m] == 'W' || matr[n][m] == 'S' || matr[n][m] == 'E') {
						m++;
					} else {
						System.err.println("The symbol '" + matr[n][m] + "' is not allowed! ");
						System.out.println(" ");
						System.err.println("Please correct your matrix! ");
						System.exit(0);
					}

				}
				n++;
			}
		} catch (IOException e) {
			System.err.println("There was a problem: " + e);
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}

		return matr;
	}

	/**
	 * This method Prints char[][] on the console
	 * 
	 * @param matr
	 */
	public void printMmatrix() {
		for (int a_i = 0; a_i < matrix.length; a_i++) {
			for (int a_j = 0; a_j < matrix[a_i].length; a_j++) {
				System.out.print(matrix[a_i][a_j] + " ");
			}
			System.out.println();
		}
	}

}
