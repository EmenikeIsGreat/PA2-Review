package edu.wit.cs.comp1050;
import java.util.Scanner;
/**
 * The solution to the second part 
 * 
 * includes step by step comments of each line of code.
 * This is to show how I approached this program 
 * in the most logical way I could.
 * 
 * 
 * @author Edgar Romero
 *
 */
public class PA2b {
	
	/**
	 * Error to supply if input is not positive
	 */
	public static final String ERR_VALUES = "Number of values must be positive.";
	
	/**
	 * Returns true if the supplied array has a
	 * sequence of k consecutive values
	 * 
	 * @param values input array
	 * @param k sequence length for which to search
	 * @return true if values has a consecutive sequence of at least k
	 */
	public static boolean hasConsecutive(int[] values, int k) {
		/**
		 * Invalid returns approach first
		 * If negative value or array is empty 
		 * or array is less than value search for, no consecutive numbers
		 */
		if(k <= 0 || values.length == 0 || values.length < k){
			return false;
		}
		/** 
		 * This will return the max consecutive values for the next method
		 * apart from returning a boolean expression for this method 
		 * Probably a cleaner solution, but failed to determine one
		 */
		// the MAX consecutive numbers 
		int max = 0;
		// consecutive will keep count of consecutive numbers
	    int consecutive = 1;
	    // loop through the array, starts at second value of array
	    for (int i = 1; i < values.length; i++) {
	    	// if the current value matches the value before 
	    	if (values[i] == values[i-1]) {
	    		//count as consecutive 
	    		consecutive++;
	    		// if it doesn't 
	        } else {
	        	// checks if current consecutive is bigger than the max
	            if (consecutive > max) {
	            	// if so that becomes the maximum consecutive 
	                max = consecutive;
	            }
	            //consecutive resets to 1 for next loop 
	            consecutive = 1;
	        }
	    }
	    // If there was at least the amount of consecutive numbers asked for
	    if (consecutive >= k || max >= k){
	    	// return true
	    	return true;
	    } 
	    
		  return false;
	}


	/**
	 * Returns the length of the longest
	 * consecutive sequence in the supplied
	 * array
	 * 
	 * @param values input array
	 * @return length of the longest consecutive value sequence in values
	 */
	public static int maxConsecutive(int[] values) {
		// Hint: hasConsecutive could
		// be very useful here
		
		// If array is empty, return 0

		if (values.length == 0){
		  return 0;
				  
		}
		// the MAX consecutive numbers 
		int max = 0;
		// consecutive will keep count of consecutive numbers
	    int consecutive = 1;
	    // loop through the array, starts at second value of array
	    for (int i = 1; i < values.length; i++) {
	    	// if the current value matches the value before 
	    	if (values[i] == values[i-1]) {
	    		//count as consecutive 
	    		consecutive++;
	    		// if it doesn't 
	        } else {
	        	// checks if current consecutive is bigger than the max
	            if (consecutive > max) {
	            	// if so that becomes the maximum consecutive 
	                max = consecutive;
	            }
	            //consecutive resets to 1 for next loop 
	            consecutive = 1;
	        }
	    }
	    // by the end of the loop is the consecutive count is great than max
	    if (consecutive > max) {
	    	// return the consecutive 
	        return consecutive;
	      // if its not
	    } else {
	    	// return the max
	        return max;
	    }
	}

	/**
	 * Inputs an array of numbers
	 * and outputs the longest consecutive
	 * sequence of values
	 * 
	 * @param args command-line arguments, ignored
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int length;
		System.out.print("Enter the number of values: ");
		length = sc.nextInt();
		//Catches negative values
		if (length <= 0){
			System.out.println(ERR_VALUES);
			return;
		}
		
		int array[] = new int[length];
		System.out.print("Enter the values: ");
		//a foor loop so that the values entered matches with previous condition 
		for (int i = 0 ; i < array.length; i++ ) {
	           array[i] = sc.nextInt();
	        }
		// A simple print f directly calling the maxConsecutive, to return correct value
		System.out.printf("The maximum length of consecutive values is %d",maxConsecutive(array));
		System.out.printf(".\n");

		
		// Hint: useful methods and constants here
		// - maxConsecutive
		// - ERR_VALUES
	}

}
