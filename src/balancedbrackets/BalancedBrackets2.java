package balancedbrackets;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class BalancedBrackets2 {
	private static final char CLOSE_BRACKETS = ']';
	private static final char OPEN_BRACKETS = '[';
	private static final char OPEN_PARENTHESIS = '(';
	private static final char CLOSE_PARENTHESIS = ')';
	private static final char OPEN_BRACES = '{';
	private static final char CLOSE_BRACES = '}';
		
	Stack<Character> openParenthesis = null; 
	Stack<Character> openBrackets = null;
	Stack<Character> openBraces = null;

	private boolean closedBrackets;
	private boolean closedBraces;
	private boolean closedParenthesis;	
	
	/***
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
		
		sequenceOfBracketsList.forEach(bracket -> verifyCharacter(bracket));
		
		verifyStacks();
		
		return closedBraces &&
				closedBrackets && 
				closedParenthesis;
	}
	
	private boolean resultParenthesis(String sequenceOfBrackets) {
		return !sequenceOfBrackets.matches("[^()]+") && closedParenthesis;
	}
	
	private boolean resultBrackets(String sequenceOfBrackets) {
		return !sequenceOfBrackets.matches("[^\\[\\]]+") && closedBrackets;
	}
	
	private boolean resultBraces(String sequenceOfBrackets) {
		return !sequenceOfBrackets.matches("[^{}]+") && closedBraces;
	}
	
	private void verifyStacks() {
		if(openBraces == null) {
			closedBraces = true;
		}
		
		if(openParenthesis == null) {
			closedParenthesis = true;
		}
		
		if(openBrackets == null) {
			closedBrackets = true;
		}
	}

	private void verifyValidCharactersOnly(String sequenceOfBrackets) {
		if(sequenceOfBrackets.matches("[^()\\[\\]\\{}]+")) { 
			throw new RuntimeException("Invalid charaters in the input.");
		}
	}

	private void verifyCharacter(Character bracket) {
		switch (bracket) {
		case OPEN_PARENTHESIS:
			openParenthesis(bracket);
			break;
		case CLOSE_PARENTHESIS:
			closeParenthesis(bracket);
			break;
		case OPEN_BRACKETS:
			openBrackets(bracket);
			break;
		case CLOSE_BRACKETS:
			closeBrackets(bracket);
			break;
		case OPEN_BRACES:
			openBraces(bracket);
			break;
		case CLOSE_BRACES:
			closeBraces(bracket);
			break;
		default:
			break;
		}
	}

	private void openParenthesis(Character bracket) {
		if(openParenthesis == null) {
			openParenthesis = new Stack<>();
		}
		openParenthesis.push(bracket);		
	}

	private void closeParenthesis(Character bracket) {
		if(openParenthesis != null && !openParenthesis.isEmpty()) {
			openParenthesis.pop();
			closedParenthesis = true;
		} else {
			closedParenthesis = false;
		}
	}

	private void openBraces(Character bracket) {
		if(openBraces == null) {
			openBraces = new Stack<>();
		}
		openBraces.push(bracket);		
	}

	private void closeBraces(Character bracket) {
		if(openBraces != null && !openBraces.isEmpty()) {
			openBraces.pop();
			closedBraces = true;
		} else {
			openBraces = new Stack<>();
			closedBraces = false;
		}
	}

	private void openBrackets(Character bracket) {
		if(openBrackets == null) {
			openBrackets = new Stack<>();
		}
		openBrackets.push(bracket);		
	}
	
	private void closeBrackets(Character bracket) {
		if(openBrackets != null && !openBrackets.isEmpty()) {
			openBrackets.pop();
			closedBrackets = true;
		} else {
			closedBrackets = false;
		}
	}
}
