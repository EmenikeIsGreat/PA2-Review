package edu.wit.cs.comp1050;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The solution to the third part  
 * 
 * Not as many comments as previous PA 
 * Due to straight forward program
 * But also
 * 
 * @author Edgar Romero
 *
 */
public class PA2c {
	
	/**
	 * Error to output if can't open any files
	 */
	public static final String ERR_FILE = "Error opening file(s)";
	
	/**
	 * Error to output if files open but matrices
	 * are of incompatible dimensions for multiplication
	 */
	public static final String ERR_DIMS = "Bad matrix dimensions";
	
	private static void _outputMatrix(PrintWriter out, int[][] m, boolean includeDimensions) {		
		for (int r=0; r<m.length; r++) {
			if (includeDimensions && r==0) {
				out.printf("%d%n%d%n", m.length, m[0].length);
			}
			for (int c=0; c<m[r].length; c++) {
				out.printf("%d", m[r][c]);
				out.printf((c<m[r].length-1)?" ":"%n");
			}
		}
	}
	
	/**
	 * Prints a matrix to the terminal
	 * without dimensions
	 * 
	 * @param m matrix to print
	 */
	public static void printMatrix(int[][] m) {
		_outputMatrix(new PrintWriter(System.out, true), m, false);
	}
	
	/**
	 * Prints a matrix to a file
	 * with associated dimensions
	 * 
	 * @param m matrix to print
	 * @param pw open file
	 */
	public static void printMatrix(int[][] m, PrintWriter pw) {
		_outputMatrix(pw, m, true);
	}
	
	/**
	 * Checks if two matrices can be multiplied
	 * (i.e. the columns of the first match
	 * the rows of the second)
	 * 
	 * @param m1 matrix 1
	 * @param m2 matrix 2
	 * @return true if m1 x m2 is legal
	 */
	public static boolean canMultiply(int[][] m1, int[][] m2) {
		//If array is empty multiplication not possible
		if(m1.length == 0 || m2.length == 0 ){ 
	    	
			return false;
	    }
		int m1Col = m1[0].length; // m1 columns length
	    int m2Row = m2.length;    // m2 rows length
	
	    /**
	    * can multiply if the number of columns in m1 == m2.length.
	    * Otherwise, the product of two matrices is undefined.
	    */

	    if(m1Col == m2Row) return true; 
		
	    return false;
    }
	
	/**
	 * Reads and returns a matrix from a scanner
	 * Format:
	 * m (# rows)
	 * n (# #cols)
	 * r0c0 r0c1 ... r0cn (values in row 0, column-by-column)
	 * r1c0 r1c1 ... r1cn (values in row 1, column-by-column)
	 * ...
	 * rmc0 rmc1 ... rmcn (values in last row, column-by-column)
	 * 
	 * Results in...
	 * int[][] {
	 * 	{r0c0, r0c1, ... r0cn},
	 *  {r1c0, r1c1, ... r1cn},
	 *  ...
	 *  {rmc0, rmc1, ... rmcn}
	 * }
	 * 
	 * @param s input source
	 * @return resulting matrix
	 */
	public static int[][] readMatrix(Scanner s) {
		// reads the rows entered 
		int rowsInM = s.nextInt();
		// reads the columns entered 
		int columnsInM = s.nextInt();
		
		// creates a matrix from values
		int[][] a = new int[rowsInM][columnsInM];
		
		/**
		 * Matrix values read from previous dimensions input 
		 */
		for (int i = 0; i < a.length; i++) { 
	           for (int j = 0; j < a[0].length; j++) { 
	               a[i][j] = s.nextInt();
	           }       
		}

		return a;
	}
	
	/**
	 * Multiply two matrices and
	 * return the result
	 * 
	 * @param m1 matrix 1
	 * @param m2 matrix 2
	 * @return result of m1 x m2
	 */
	public static int[][] matrixMultiply(int[][] m1, int[][] m2) {		
		
		int m1Col = m1[0].length; // m1 columns length
		int m3Row = m1.length;    // the resulted matrix's rows length
		int m3Col = m2[0].length; // the resulted matrix's columns length
		
		int[][] m3 = new int[m3Row][m3Col];
		
		/**
		 * performs the adding of every element in
		 * a row of m1 multiplied by the corresponding element in the
		 * corresponding column of m2, for all columns in m1 and all rows in m1
		 */
		
		for(int i = 0; i < m3Row; i++) {         		// rows from m1
			for(int j = 0; j < m3Col; j++) {     		// columns from m2
				for(int k = 0; k < m1Col; k++) { 		// columns from m1
					m3[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		
		return m3;	
	}
	
	/**
	 * Program to multiply matrices:
	 * 1. Ask for paths for 3 files (2 input, 1 output)
	 * 2. Check if inputs can be multiplied
	 * 3. If so, multiply!
	 *    - Output the full problem to the console
	 *    - Output only the result matrix to the file
	 * 
	 * @param args command-line arguments, ignored
	 */
	public static void main(String[] args) {
		// Hint: paths should be read as an entire line!
		Scanner input = new Scanner(System.in);

		// matrix 1 read
		System.out.print("Enter path for matrix 1: ");
		String matrix1= input.nextLine();
		
		// matrix 2 read
		System.out.print("Enter path for matrix 2: ");
		String matrix2 = input.nextLine();
		
		// finds file for result 
		System.out.print("Enter path for result: ");
		String matrixResult = input.nextLine();;
		
		
		
		File m1 = new File(matrix1);
		File m2 = new File(matrix2);
		File m3 = new File(matrixResult);
			try {
				// all files must exist first 
				if (!m1.exists() || !m2.exists() || !m3.exists() ) {
					throw new FileNotFoundException();
				}
				
				
				/**
				 * Even with an extra day, I was left a bit confused 
				 * Print statements were supplied but
				 * I was using incorrectly as it did not print out both
				 */
				
				// Scanner will read file m1.txt
				Scanner FirstMatrix= new Scanner(m1);
				
				// Scanner will read file m2.txt
				Scanner SecondMatrix = new Scanner(m2);
				
			
				//Checks first if the matrixes can be multiplied 
				if (canMultiply(readMatrix(FirstMatrix), readMatrix(SecondMatrix)) == false) {
					System.exit(0);
					// If they can't IT SHOULD (?) print out the matrices
					printMatrix(readMatrix(FirstMatrix));
					System.out.println("X");
					printMatrix(readMatrix(SecondMatrix)); 
					System.out.println("=");
					System.out.println(ERR_DIMS);
					//printMatrix(int[][] m, PrintWriter pw)
					System.exit(0);
				
				} else {
//					matrixMultiply(readMatrix(FirstMatrix), readMatrix(SecondMatrix));
//					printMatrix(readMatrix(FirstMatrix));
//					System.out.println("X");
//					printMatrix(readMatrix(SecondMatrix)); 
//					System.out.println("=");
					
					System.exit(0);
					//printMatrix(int[][] m, PrintWriter pw)
					
				}
			 // Realize this catch is giving me trouble.. not sure what the exception is though 
			} catch (Exception ex) {
				System.out.printf(ERR_FILE + "%n");
			}
		} 
		
}
	


