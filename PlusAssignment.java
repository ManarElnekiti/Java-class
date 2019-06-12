
/* a Java program which implements a "plus" operator for integer numbers in
	String representation (with no size limitation)
*/
// Developed By: Elnekiti,Manar  

/*Start*/

//In Java, we take input from user with the help of Scanner class. 
//This Scanner class is found in java.util package. 
// So to use Scanner class,we first need to include java.util package in our program.
import java.util.Scanner;

public class PlusAssignment {

	public static void main(String[] args) {

		// we are declaring input as an object of Scanner class.
		// System.in within the round brackets tells Java an input will be given to the
		// system.
		Scanner input = new Scanner(System.in);

		// input first number...
		System.out.print("Please input the first number:");
		String op1 = input.next();

		// input second number...
		System.out.print("Please input the second number:");
		String op2 = input.next();

		// input third number...
		System.out.print("Please input the third number:");
		String op3 = input.next();

		// check strings..
		boolean op1Check = checkInput(op1);
		boolean op2Check = checkInput(op2);
		boolean op3Check = checkInput(op3);

		if (op1Check == true && op2Check == true && op3Check == true) {

			/*
			 * calling the plus method and assign it's return value to a variable called
			 * result..
			 */
			String result = plus(op3, plus(op1, op2));

			// print out the result...
			if (result.charAt(0) == '0') {

				result = result.substring(1);

			} else if (result.charAt(1) == '0') {

				result = "-" + result.substring(2);
			}

			System.out.print("The result is: " + result);

		} else {
			System.out.print("Invalid Input...");
		}

	}

	public static String plus(String op1, String op2) {

		// define variables.....
		String result = "";
		char op1Sign = op1.charAt(0);
		char op2Sign = op2.charAt(0);

		// if the first string not have sign add positive sign...
		if ((op1Sign != '+') && (op1Sign != '-')) {

			op1 = "+" + op1;
		}
		// if the second string not have sign add positive sign...
		if ((op2Sign != '+') && (op2Sign != '-')) {

			op2 = "+" + op2;
		}

		// update the string sign...
		op1Sign = op1.charAt(0);
		op2Sign = op2.charAt(0);

		// if the two strings have the same positive sign we add them....
		if (op1Sign == '+' && op2Sign == '+') {

			// remove the sign and we add them
			op1 = op1.substring(1);
			op2 = op2.substring(1);

			result = summation(op1, op2);

			// if the two strings have the same negative sign we add them and return result
			// with negative sign....
		} else if (op1Sign == '-' && op2Sign == '-') {

			// remove the sign and we add them
			op1 = op1.substring(1);
			op2 = op2.substring(1);

			result = "-" + summation(op1, op2);

			// if the two strings have different signs we subtract them....
		} else if ((op1Sign == '-' && op2Sign == '+') || (op1Sign == '+' && op2Sign == '-')) {

			// save strings signs into variables....
			char firstStrSign = op1Sign;
			char secondStrSign = op2Sign;

			// remove the sign
			op1 = op1.substring(1);
			op2 = op2.substring(1);

			// check string length...
			int firstStringLength = op1.length();
			int secondStringLength = op2.length();

			if (firstStringLength > secondStringLength) {

				int theDifference = firstStringLength - secondStringLength;

				for (int i = 0; i < theDifference; i++) {

					op2 = "0" + op2;
				}

			} else if (firstStringLength < secondStringLength) {

				int theDifference = secondStringLength - firstStringLength;

				for (int i = 0; i < theDifference; i++) {

					op1 = "0" + op1;
				}

			} // End of check length....

			// check if the two strings have the same number with different signs result is
			// zero..
			// if they are not the same number subtract...
			int i = 0;
			int j = 0;

			while (i <= op1.length() - 1 || j <= op2.length() - 1) {

				char str1Char = op1.charAt(i);
				char str2Char = op2.charAt(j);

				if (str1Char == str2Char) {
					i++;
					j++;
					result = "00";
				} else {

					char op1Num = op1.charAt(i);
					char op2Num = op2.charAt(j);

					/*
					 * if first string is the bigger number with negative sign, then subtract the
					 * second string from the first one and return the result with negative
					 * sign........
					 */
					if ((op1Num > op2Num) && (firstStrSign == '-')) {

						result = "-" + subtraction(op1, op2);

						/*
						 * if first string is the smaller number with negative sign, then subtract the
						 * first string from the second one and return the result with positive
						 * sign........
						 */
					} else if ((op1Num < op2Num) && (firstStrSign == '-')) {

						result = subtraction(op2, op1);

						/*
						 * if second string is the bigger number with negative sign, then subtract the
						 * first string from the second one and return the result with negative
						 * sign........
						 */
					} else if ((op2Num > op1Num) && (secondStrSign == '-')) {

						result = "-" + subtraction(op2, op1);

						/*
						 * if second string is the smaller number with negative sign, then subtract the
						 * second string from the first one and return the result with positive
						 * sign........
						 */
					} else if ((op2Num < op1Num) && (secondStrSign == '-')) {

						result = subtraction(op1, op2);
					}

					return result;
				}
			}

		}

		return (result);
	}// End of plus method....

	// Method to check that the string contain only numbers or signed numbers....
	public static boolean checkInput(String str) {

		char str1Sign = str.charAt(0);
		boolean check = true;

		if ((str1Sign != '+') && (str1Sign != '-')) {

			str = "+" + str;

		}

		for (int i = 1; i <= str.length() - 1; i++) {

			char str1Char = str.charAt(i);

			if (str1Char >= '0' && str1Char <= '9') {

				check = true;

			} else {
				check = false;
				return check;// return stops the loop when find false...
			}
		}
		return check;
	}// End of check method...

	// Method to sum two string....
	public static String summation(String frStr, String secStr) {

		int sum = 0;
		int carry = 0;
		String result = "";
		String reverse = "";
		int firstStrLength = frStr.length();
		int secondStrLength = secStr.length();

		// check the string length.....
		// if there is a difference between them we make them equal
		if (firstStrLength > secondStrLength) {

			int theDifference = firstStrLength - secondStrLength;

			for (int i = 0; i <= theDifference; i++) {

				secStr = "0" + secStr;
			}
			frStr = "0" + frStr; // string concatenation..

		} else if (firstStrLength < secondStrLength) {

			int theDifference = secondStrLength - firstStrLength;

			for (int i = 0; i <= theDifference; i++) {

				frStr = "0" + frStr;
			}
			secStr = "0" + secStr;
		} else {
			frStr = "0" + frStr;
			secStr = "0" + secStr;
		}

		// the loop is iterating backwards through the strings
		for (int i = frStr.length() - 1, j = secStr.length() - 1; i >= 0 || j >= 0; i--, j--) {

			char op1char = frStr.charAt(i);
			char op2char = secStr.charAt(j);

			// now we need to obtain integers from characters.
			// When we work with characters, we are working with ASCII values, we will get
			// 48 as 0.
			// so we need to subtract 48.
			int op1Number = op1char - 48;
			int op2Number = op2char - 48;

			sum = op1Number + op2Number + carry;

			if (sum > 9) {

				sum -= 10;
				carry = 1;

			} else {
				carry = 0;
			}
			// insert the sum of each element into string...
			result += sum;

		} // End of the loop...

		// reverse the result....
		for (int i = result.length() - 1; i >= 0; i--) {
			reverse += result.charAt(i);
		}

		return (reverse);
	}// End of summation....

	// Method to subtract two strings....
	public static String subtraction(String theFirstStr, String theSecondStr) {

		int subtract = 0;
		int borrow = 0;
		String result = "";
		String reverse = "";

		// the loop is iterating backwards through the strings
		for (int i = theFirstStr.length() - 1, j = theSecondStr.length() - 1; i >= 0 || j >= 0; i--, j--) {

			char op1char = theFirstStr.charAt(i);
			char op2char = theSecondStr.charAt(j);

			// now we need to obtain integers from characters.
			// When we work with characters, we are working with ASCII values, we will get
			// 48 as 0.
			// so we need to subtract 48.
			int op1Number = op1char - 48;
			int op2Number = op2char - 48;

			subtract = op1Number - op2Number - borrow;

			if (subtract < 0) {
				subtract += 10;
				borrow = 1;

			} else {
				borrow = 0;
			}
			// insert the subtract of each element into string...
			result += subtract;

		} // End of the loop...

		// reverse the result....
		for (int i = result.length() - 1; i >= 0; i--) {
			reverse += result.charAt(i);
		}

		return (reverse);
	}// End of subtract method...

}
