package balancedbrackets;

import java.util.List;
import java.util.stream.Collectors;

public class BalancedBrackets {
	private static final char OPEN_PARENTHESIS = '(';
	private static final char CLOSE_PARENTHESIS = ')';
	private int parenthesis = 0;
		
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
		
		List<Character> sequenceOfBracketsList = sequenceOfBrackets.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
					
		verifyOpen(sequenceOfBracketsList, 0);
		return parenthesis > 0;		
	}
	
	private void verifyOpen(List<Character> sequenceOfBracketsList, int index) {
		if(sequenceOfBracketsList.size() <= index) {
			return;
		}
		
		if(isIndexValid(sequenceOfBracketsList.size(), index) 
				&& sequenceOfBracketsList.get(index).equals(OPEN_PARENTHESIS)) {
			verifyClose(sequenceOfBracketsList, index);
		} else {
			verifyOpen(sequenceOfBracketsList, index);
		}
	}
	
	private void verifyClose(List<Character> sequenceOfBracketsList, int index) {
		if(isIndexValid(sequenceOfBracketsList.size(), index) 
				&& sequenceOfBracketsList.get(++index).equals(CLOSE_PARENTHESIS)) {
			parenthesis++;
		} else {
			verifyOpen(sequenceOfBracketsList, ++index);
		}
	}
	
	private boolean isIndexValid(int listSize, int index) {
		return listSize > index;
	}

	public static void main(String[] args) {
		BalancedBrackets b = new BalancedBrackets();
		System.out.println(b.verifyBalancedBrackets("()"));
	}
}
