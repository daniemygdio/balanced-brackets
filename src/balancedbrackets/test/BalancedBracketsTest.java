package balancedbrackets.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import balancedbrackets.BalancedBrackets2;

public class BalancedBracketsTest {

	private BalancedBrackets2 balancedBrackets;
	
	@Before
	public void setup() {
		balancedBrackets = new BalancedBrackets2();
	}
	
	@Test
	public void testInvalidInput() {
		assertFalse(balancedBrackets.verifyBalancedBrackets(")))"));
	}
	
	@Test
	public void testValidInputWithParenthesisOnly() {
		assertTrue(balancedBrackets.verifyBalancedBrackets("()"));
	}

	@Test 
	public void testValidEntry() {
		assertTrue(balancedBrackets.verifyBalancedBrackets("[](){}"));
	}
	
	@Test 
	public void testValidEntry2() {
		assertTrue(balancedBrackets.verifyBalancedBrackets("[{()}](){}"));
	}
	
	@Test 
	public void testInvalidEntry() {
		assertFalse(balancedBrackets.verifyBalancedBrackets("[]({}"));
	}
	
	@Test 
	public void testInvalidEntry2() {
		assertFalse(balancedBrackets.verifyBalancedBrackets("[{)]"));
	}
}
