package testpack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import calci_test.Calculator;

public class CalculatorTest {
	@Test
    public void testAddition() {

        Calculator calc = new Calculator();

        int result = calc.add(2, 3);

        assertEquals(5, result);
    }
}