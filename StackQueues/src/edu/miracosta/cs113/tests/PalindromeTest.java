package edu.miracosta.cs113;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

/**
 * PalindromeTest : a test class for isPalindrome, a method intended to utilize stacks to evaluate if a given
 * string is a palindrome.
 *
 * A palindrome is a word, phrase, number, or other sequence of characters which reads the same backwards as it does
 * forwards. Such sequences include "madam," "top spot," or "no lemon, no melon".
 */
public class PalindromeTest {

    /** True test cases which include spaces and symbols */
    private static final String[] SIMPLE_TRUE = { "", " ", "A", "7", "%", "  ", "BB", "33", "**" };
    /** False test cases which include spaces and symbols */
    private static final String[] SIMPLE_FALSE = { "AC", "71", "@+" };

    /** True test cases which include spaces */
    private static final String[] WHITE_SPACE_TRUE = { " x ", " t   t  ", " 5 5", " #      # " };
    /** False test cases which include spaces */
    private static final String[] WHITE_SPACE_FALSE = { "m   n  ", "   8  7 ", "  ^      &  "};

    /** Case-sensitive palindromes */
    private static final String[] CASE_SENSITIVE_TRUE = { "ABba", "roTOR", "rAceCaR" };

    /** Complex palindromes which include spaces, symbols, and varied capitalization */
    private static final String[] COMPLEX_TRUE = { "fOO race CAR oof", "AbBa ZaBba", "1 3 3 7  331",
                                                "N0 LEm0n, n0 Mel0n",
                                                "sT RJKLEeE R#@ $A$ @# REeEL K  JRT s" };
 
    /**
     * Utilizes stacks to determine if the given string is a palindrome. This method ignores whitespace and case
     * sensitivity, but does not ignore digits or symbols.
     *
     * @param s a string comprised of any character
     * @return returns true if a palindrome (ignoring whitespace and case sensitivity), false otherwise
     */ 
    
    /*
     private boolean isPalindrome(String inputString) {
    	Deque<Character> charStack = new ArrayDeque<>();
    	StringBuilder result = new StringBuilder();
    	
    	if (inputString == null) {
    		throw new IllegalArgumentException();
    	}
    	else {
    		inputString = inputString.replaceAll("\\s", "");
			
    		for(int i = 0; i < inputString.length(); i++) {
				charStack.push(inputString.charAt(i));
			}	
			
			while (!charStack.isEmpty()) {
				result.append(charStack.pop());
			}
    	}
    	
    	if (inputString.replaceAll("\\s", "").equalsIgnoreCase(result.toString())) {
    	    return true;
    	}
    	else {
    	    return false;
    	}

    } */
    
    //Initial method commented out above. Updated code May 2022 to better follow instructions.
    private boolean isPalindrome(String input) {
    	ArrayListStack<Character> charStack = new ArrayListStack<>();
    	ArrayListStack<Character> reverseStack = new ArrayListStack<>();
    	int total = 0;
    	
    	if (input == null) {
    		throw new IllegalArgumentException();
    	}
    	else {
    		input = input.replaceAll("\\s", "");
    		for (int i = 0; i < input.length(); i++) {
    			charStack.push(input.charAt(i));
    			total++;
    		}
    		
    		
    		for (int i = 0; i < total/2; i++) {
    			reverseStack.push(charStack.pop());
    		}
    		
    		if (total % 2 != 0) {
    			charStack.pop();
    		} 
    	}
    	
    	while (!charStack.empty()) {
    		if (Character.toLowerCase(charStack.pop()) != Character.toLowerCase(reverseStack.pop())) {
    			return false;
    		}	   
    	}
    	return true;
  	
    } // End of method isPalindrome

    @Test
    public void testErrors() {
        try {
            isPalindrome(null);
            fail("Checking null to see if it's a palindrome should throw IllegalArgumentException!");
        } catch (IllegalArgumentException iae) { /* Test Passed! */ }
    }

    @Test
    public void testSimpleTrueCases() {
        for (int i = 0; i < SIMPLE_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(SIMPLE_TRUE[i]));
        }
    }

    @Test
    public void testSimpleFalseCases() {
        for (int i = 0; i < SIMPLE_FALSE.length; i ++) {
            assertFalse((i + " This test is NOT a palindrome"), isPalindrome(SIMPLE_FALSE[i]));
        }
    }

    @Test
    public void testWhitespaceTrueCases() {
        for (int i = 0; i < WHITE_SPACE_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(WHITE_SPACE_TRUE[i]));
        }
    }

    @Test
    public void testWhitespaceFalseCases() {
        for (int i = 0; i < WHITE_SPACE_FALSE.length; i ++) {
            assertFalse((i + " This test is NOT a palindrome"), isPalindrome(WHITE_SPACE_FALSE[i]));
        }
    }

    @Test
    public void testCaseSensitivityCases() {
        for (int i = 0; i < CASE_SENSITIVE_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(CASE_SENSITIVE_TRUE[i]));
        }
    }

    @Test
    public void testComplexCases() {
        for (int i = 0; i < COMPLEX_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(COMPLEX_TRUE[i]));
        }
    }

} // End of class PalindromeTest