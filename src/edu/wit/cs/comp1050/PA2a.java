package edu.wit.cs.comp1050;

import java.util.Scanner;

/**
 * The solution to the first part. 
 * 
 * As many comments provided to shows process  
 * Program fails one (presumably two) test provided by JUNIT
 * Error possibly from how scanner,
 * works when I input it 
 * 
 * @author Edgar Romero
 *
 */
public class PA2a {

	/**
	 * Error to display if input contains non-numeric characters
	 */
	public static final String ERR_NON_NUMERIC = "Non-numeric input";

	/**
	 * Error to display if input is too short
	 */
	public static final String ERR_SHORT = "Input is too short";

	/**
	 * Error to display if input is too long
	 */
	public static final String ERR_LONG = "Input is too long";

	/**
	 * Error to display if input contains an invalid prefix
	 */
	public static final String ERR_BAD_PREFIX = "Invalid prefix";

	/**
	 * Array of valid single-digit prefixes
	 */
	public static final int[] PREFIXES_ONE_DIGIT = { 4, 5, 6 };

	/**
	 * Array of valid double-digit prefixes
	 */
	public static final int[] PREFIXES_TWO_DIGIT = { 37 };

	/**
	 * Minimum input length
	 */
	public static final int LENGTH_MIN = 13;

	/**
	 * Maximum input length
	 */
	public static final int LENGTH_MAX = 16;

	/**
	 * Returns true if the supplied argument
	 * contains only numeric digits (0-9)
	 * 
	 * @param s input string
	 * @return true if input contains only numeric digits
	 */
	public static boolean isOnlyNumbers(String s) {
		// First deal with empty stings
		if(s.equals(""))
			return true;		
		//In a quick & simple try catch, verify if it's only numbers
		try{
			// A parseDouble is used to hold the length of string input
			Double.parseDouble(s);
			//If the string is not be able to return a numeric value, there must be non numeric values
		}catch (Exception e) {
			// Prints string provided when non numeric values caught 
			/**
			 * Line 81 is where the program crashed IF There is not space before Status
			 * Which is why there is a space before "Status."
			 * yet to be solved reason
			 * 
			 * Possible fix is to use switch statements for all statuses...
			 */
			System.out.println(" Status: "+ERR_NON_NUMERIC);
			return false;
		}
		return true;
	}

	/**
	 * Return the numeric value that
	 * the supplied character represents
	 * '0' -> 0
	 * '1' -> 1
	 * ...
	 * '9' -> 9
	 * 
	 * @param c input character (assumed to be a digit)
	 * @return integer represented by the input
	 */
	public static int digitCharToInt(char c) {
		// getNumericValue allows for a char to turn in ASCII value
		int i = Character.getNumericValue(c);
		return i;
	}

	/**
	 * Returns the numeric value that
	 * the supplied characters represent
	 * in sequence (c1 is tens place, c2 is ones)
	 * '00' -> 0
	 * '01' -> 1
	 * ...
	 * '10' -> 10
	 * '11' -> 11
	 * ...
	 * '90' -> 90
	 * '91' -> 91
	 * '99' -> 99
	 * 
	 * @param c1 tens place digit (assumed to be a digit)
	 * @param c2 ones place digit (assumed to be a digit)
	 * @return integer represented by the inputs
	 */
	public static int digitCharToInt(char c1, char c2) {
		// Hint: digitCharToInt could be useful here...
		
		//gets value from first char
		int a = digitCharToInt(c1);
		//get value from second char
		int b = digitCharToInt(c2);
		
		/**
		 * One of my cleaner solutions, 
		 * BEFORE adding the int values of c1 and c2
		 * An empty string will "add" c1 and c2 as a string
		 * ALLOWING the string to be parsed and return the numeric value of string
		 * allowing both ints to be returned as a single int c
		 */
		int c = Integer.parseInt("" + a + b);

		return c;
	}

	/**
	 * Returns true if the supplied integer
	 * is contained within the array of integers
	 * 
	 * @param needle item to search for
	 * @param haystack valid list in which to search
	 * @return true if needle is in haystack
	 */
	public static boolean inArray(int needle, int[] haystack) {
		/**
		 * Originally was
		 * for (int i = 0; i < haystack.length; i++) 
		 * till friend mocked my java for not using for each 
		 * much neater
		 */
		for (int i : haystack) {
			if (i == needle) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the supplied string
	 * begins with a valid prefix
	 * 
	 * @param s credit card number (assumed to be comprised only of digits)
	 * @return true if begins with a valid CC prefix
	 */
	public static boolean validPrefix(String s) {
		// Hint: digitCharToInt and inArray
		// could be useful here
		
		/**
		 * With a try-catch block
		 * FIRST the string will obtain the char at the beginning
		 * SECOND the char(s) is put through digitCharToInt to obtain numeric value
		 * LASTLY the numeric value will cycle through the valid prefixs 
		 * 
		 *If not found in valid prefix, return provided String 
		 */
		try {
			// for MasterCard,  Visa, and Discovery
			if (inArray(digitCharToInt(s.charAt(0)), PREFIXES_ONE_DIGIT)) {
				return true;
			// for American Express
			} else if (inArray(digitCharToInt(s.charAt(0), s.charAt(1)), PREFIXES_TWO_DIGIT)) {
				return true;
			} else {
				System.out.println("Status: " + ERR_BAD_PREFIX);
			}
			// catches any other exception to return false
		} catch (Exception e) {	
		}
		return false;
	}

	/**
	 * Returns the number of digits
	 * in an integer
	 * 
	 * @param num number (assumed to be non-negative)
	 * @return number of digits in the number
	 */
	public static int numDigits(int num) {
		/**
		 * FIRST the num is turned into a string 
		 * SECOND the string's length is taken
		 * Lastly the length is stored and returned 
		 */
		int length = String.valueOf(num).length();
		return length;
	}

	/**
	 * Returns the digit in the specified "place"
	 * of an input number, where 0 is the ones,
	 * 1 is the ten's, ...
	 * 
	 * f(1234, 0) = 4
	 * f(1234, 1) = 3
	 * f(1234, 2) = 2
	 * f(1234, 3) = 1
	 * 
	 * @param num input number (assumed to be non-negative)
	 * @param place place from which to extract the digit (assumed to be [0, #digits-1])
	 * @return digit extracted
	 */
	public static int getDigitInPlace(int num, int place) {
		/**
		 * Essentially a log10 formula
		 * 
		 * FIRST "int place" is used as an exponent for 10 casted as an int
		 * SECOND the answer can be used to divide "int num"
		 * LASTLY mod 10 will return the remainder which should always be correct 
		 * 
		 */
		int digit = (num / (int) (Math.pow(10, place))) % 10;
		return digit;
	}

	/**
	 * Returns a single digit number resulting
	 * from repeatedly adding the digits of a
	 * number until it is reduced to a single digit...
	 * 5678 => 5 + 6 + 7 + 8 = 26 => 2 + 6 = 8 
	 * 
	 * @param num number (assumed to be non-negative)
	 * @return single-digit from repeated sums
	 */
	public static int reduceToDigit(int num) {
		// Hint: numDigits and getDigitInPlace
		// could be useful here
		
		//vvv My thought process on how to implement methods essentially giving up vvv
		
		// numDigits(num); Number of digits 0....9
		// getDigitInplace(int num, int place) Will find digit in place given
		// getDigitInplace(int num, numDigits(num) Will find the 
		
		// reduceToDigit(getDigitInPlace(num, numDigits(num)) ...
		

		int result  = 0;
		
		while (num > 0) {
			// int a will make sure num is single digit by mod 10
			int a = num % 10;
			//single digit will be the result 
			result = result + a;
			
			//checks that the number is divisible by 10 wh9le result greater than 0
			num = num / 10;
			// ends loop
			if (num == 0 && result > 9) {
				num = result;

				result = 0;
			}
		}
		return result;
	}

	/**
	 * Sums every second digit, right to left.
	 * If doubling results in a double-digit,
	 * add up the digits to produce a single.
	 * 4388576018402626 =>
	 *  2*2 = 4
	 * +2*2 = 4
	 * +2*4 = 8
	 * +2*1 = 2
	 * +2*6 = 12 => 3
	 * +2*5 = 10 => 1
	 * +2*8 = 16 => 7
	 * +2*4 = 8
	 * = 37
	 * 
	 * @param s input string (assumed to be only digits)
	 * @return sum of doubled evens
	 */
	public static int sumOfDoubleEvens(String s) {
		// Hint: digitCharToInt and reduceToDigit
		// could be useful here
		
		int result = 0;
		//i initialized as length -2 for even, always cycling even
		for (int i = s.length() - 2; i > -1; i -= 2) {
			//cycling through each char in string to get value and multiply by two 
			char c = s.charAt(i);
			int digit = digitCharToInt(c) * 2;
			//adds all values till single value left
			result += reduceToDigit(digit);
		}
		return result;
	}

	/**
	 * Sums every odd digit, right to left.
	 * 4388576018402626 =>
	 * 6+6+0+8+0+7+8+3=38
	 * 
	 * @param s input string (assumed to be only digits)
	 * @return sum of odds
	 */
	public static int sumOfOdds(String s) {
		// Hint: digitCharToInt and reduceToDigit
		// could be useful here
		int result = 0;
		
		//i initialized as length -1 for odd, always cycling odd
		for (int i = s.length() -1; i >-1; i -= 2) {
			// cycles through the string getting each value 
			char c = s.charAt(i);
			int digit = digitCharToInt(c);
			// adds all values till single value left
			result += reduceToDigit(digit);
		}
		return result;
	}

	/**
	 * Returns true if the sum of the
	 * doubled sum of even digits +
	 * sum of odd digits is divisible
	 * by 10.
	 * 
	 * 4388576018402626: 37 + 38 = 75 % 10 != 0 => false
	 * 5117275325077359: 18 + 42 = 60 % 10 == 0 => true
	 * 
	 * @param s input string (assumed to be only digits)
	 * @return true if passes the Luhn check
	 */
	public static boolean luhnCheck(String s) {
		// Hint: sumOfDoubleEvens and sumOfOdds
		// could be useful here
		// straight fowards luhn check
		long total = sumOfOdds(s)+sumOfDoubleEvens(s);
		if (total %10 == 0){
			return true;
		}
		return false;
	}

	/**
	 * Inputs a credit card number
	 * and outputs either input-validation
	 * error or validation status of the
	 * card number
	 * 
	 * @param args command-line arguments, ignored
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String creditCard;

		System.out.printf("Enter a credit card number: ");
		creditCard = sc.nextLine();
		//First checks to have only numbers in string 
		if(!isOnlyNumbers(creditCard)){
			return;
		}
		// checks lengths 
		if (creditCard.length() < 13 ){
			System.out.println("Status: " + ERR_SHORT);
			System.exit(0);
		}
		else if (creditCard.length() > 16){
			System.out.println("Status: " + ERR_LONG);
			System.exit(0);
		}
		// and finally checks prefix
		if (validPrefix(creditCard) != true){
			System.exit(0);
		};


		String check = (luhnCheck(creditCard))?("valid"):("invalid");
		System.out.printf("Status: card is %s%n" , check);
		

		// Hint: MANY useful methods and constants here
		// - isOnlyNumbers()
		// - validPrefix()
		// - luhnCheck()
		// - ERR_ constants
		// - LENGTH_ constants
	}

}
