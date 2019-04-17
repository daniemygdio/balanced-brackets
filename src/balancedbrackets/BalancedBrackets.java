package balancedbrackets;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * This class allows the checking of whether a string contains only balanced brackets.
 * 
 * @author Danielle Emygdio
 */
public class BalancedBrackets {
	private static final char CLOSE_SQUARE_BRACKETS = ']';
	private static final char OPEN_SQUARE_BRACKETS = '[';
	private static final char OPEN_ROUND_BRACKETS = '(';
	private static final char CLOSE_ROUND_BRACKETS = ')';
	private static final char OPEN_CURLY_BRACKETS = '{';
	private static final char CLOSE_CURLY_BRACKETS = '}';
		
	private Stack<Character> openRoundBrackets = new Stack<>(); 
	private Stack<Character> openSquareBrackets = new Stack<>();
	private Stack<Character> openCurlyBrackets = new Stack<>();
	
	/**
	 * Takes a string of brackets as the input, and if the order of the brackets
	 * is valid. A bracket is considered to be any one of the following characters: 
	 * (, ), {, }, [, or ].
	 * 
	 * @param sequenceOfBrackets a string of brackets 
	 * @return true in case the sequence of brackets is valid and false otherwise
	 */
	public Boolean verifyBalancedBrackets(String sequenceOfBrackets) {
		if(sequenceOfBrackets == null) {
			return false;
		}
		
		verifyValidCharactersOnly(sequenceOfBrackets);
		
		List<Character> sequenceOfBracketsList = sequenceOfBrackets.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		
		try {
			sequenceOfBracketsList.forEach(bracket -> verifyCharacter(bracket));
		} catch (RuntimeException e) {
			return false;
		}
		return openCurlyBrackets.isEmpty() && openSquareBrackets.isEmpty() && openRoundBrackets.isEmpty();
	}

	/**
	 * Verifies if the string contains only brackets, e.g. '(', ')', '{', '}', '[', ']'.
	 * 
	 * @param sequenceOfBrackets string to be verified
	 */
	private void verifyValidCharactersOnly(String sequenceOfBrackets) {
		if(sequenceOfBrackets.matches("[^()\\[\\]\\{}]+")) { 
			throw new RuntimeException("Invalid charaters in the input.");
		}
	}

	/**
	 * Verify the character and takes action depending on the type of bracket.
	 * 
	 * @param bracket to be verified
	 */
	private void verifyCharacter(Character bracket) {
		switch (bracket) {
		case OPEN_ROUND_BRACKETS:
			openBrackets(openRoundBrackets, bracket);
			break;
		case CLOSE_ROUND_BRACKETS:
			closeBrackets(openRoundBrackets, bracket);
			break;
		case OPEN_SQUARE_BRACKETS:
			openBrackets(openSquareBrackets, bracket);
			break;
		case CLOSE_SQUARE_BRACKETS:
			closeBrackets(openSquareBrackets, bracket);
			break;
		case OPEN_CURLY_BRACKETS:
			openBrackets(openCurlyBrackets, bracket);
			break;
		case CLOSE_CURLY_BRACKETS:
			closeBrackets(openCurlyBrackets, bracket);
			break;
		default:
			break;
		}
	}

	/**
	 * Applies action for open brackets. 
	 * 
	 * @param openBrackets stack of open brackets of the specified type
	 * @param bracket to be saved
	 */
	private void openBrackets(Stack<Character> openBrackets, Character bracket) {
		openBrackets.push(bracket);		
	}

	/**
	 * Applies action for close brackets.
	 * 
	 * @param openBrackets
	 * @param bracket
	 */
	private void closeBrackets(Stack<Character> openBrackets, Character bracket) {
		if(!openBrackets.isEmpty()) {
			openBrackets.pop();
		} else {
			throw new RuntimeException("Invalid!");
		}
	}
}
