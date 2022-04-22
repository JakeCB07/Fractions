/**
 * 
 */
package fraction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IllegalArgument {

	@Test
	void testFraction() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
					Fraction test = new Fraction(1,0);});
		assertEquals("The denominator with the value of 0 is not permitted", exception.getMessage());
	}
	
	@Test
	void testAdd() {
		Fraction test = new Fraction(1,1);    
		Fraction test2 = null;    
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {test.add(test2);});    
		assertEquals("Cannot perform math operations on a null fraction object!",exception.getMessage());
	}
	
	@Test 
	void testMultiply() {
		Fraction test = new Fraction(1,1);    
		Fraction test2 = null;    
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {test.multiply(test2);});    
		assertEquals("Cannot perform math operations on a null fraction object!",exception.getMessage());
	}
}


