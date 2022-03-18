package edu.miracosta.cs113;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Deque;
import java.util.EmptyStackException;
import java.lang.StringBuilder;

public class ArrayListStack<E> implements StackInterface<E> {

	private List<E> theData;
	
	public ArrayListStack() {
		theData = new ArrayList<>();
	}
	
	@Override
	public boolean empty() {
		return theData.size() == 0;
	}
	
	@Override
	public E peek() {
		if (empty()) {
			throw new EmptyStackException();
		}
		return theData.get(theData.size() - 1);
	}
	
	@Override
	public E pop() {
		if (empty()) {
			throw new EmptyStackException();
		}

		return theData.remove(theData.size() - 1);
	}
	
	@Override
	public E push(E obj) {
		theData.add(obj);
		return obj;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null || this.getClass() != other.getClass()) {
			return false;
		} else {
			ArrayListStack<E> otherStack = (ArrayListStack)other;
			 return this.theData == otherStack.theData; 
		}
	}
	
	/**
	 * Returns a stack that is filled with the characters in input.
	 * 
	 * @param inputString   the string to be checked
	 * @return stack with characters in inputString
	 */
	private static Deque<Character> fillStack(String inputString) {
		Deque<Character> charStack = new ArrayDeque<>();
		for(int i = 0; i < inputString.length(); i++) {
			charStack.push(inputString.charAt(i));
		}
		return charStack;
	}
	
	/**
	 * Calls fillStack to fill the stack based on inputString and returns
	 * a new string formed by popping each character from this stack and 
	 * joining the characters. Empties the stack. 
	 * 
	 * @param inputString   the string to be checked
	 * @post the stack is empty
	 * @return the string containing the characters in the stack
	 */
	private static String buildReverse(String inputString) {
		Deque<Character> charStack = fillStack(inputString);
		StringBuilder result = new StringBuilder();
		while (!charStack.isEmpty()) {
			result.append(charStack.pop());
		}
		return result.toString();
	}
	
	/**
	 * Calls buildReverse and compares its result to inputString
	 * 
	 * @param inputString   the string to be checked
	 * @return true if inputString is a palindrome, false if not
	 */
	public static boolean isPalindrome(String inputString) {
		if (inputString.equalsIgnoreCase(buildReverse(inputString))) {
			return true;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
}
