package ch.jmildner.tools;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author johann
 */
public class AgileTestTest {

	public AgileTestTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of add method, of class AgileTest.
	 */
	@Test
	public void testAdd1() {
		System.out.println("add1");
		String s1 = "2";
		String s2 = "2";
		AgileTest instance = new AgileTest();
		int expResult = 4;
		int result = instance.add(s1, s2);
		assertEquals(expResult, result);
	}

	/**
	 * Test of add method, of class AgileTest.
	 */
	@Test
	public void testAdd2() {
		System.out.println("add2");
		String s1 = "3";
		String s2 = "2";
		AgileTest instance = new AgileTest();
		int expResult = 5;
		int result = instance.add(s1, s2);
		assertEquals(expResult, result);
	}

	/**
	 * Test of add method, of class AgileTest.
	 */
	@Test(expected = NumberFormatException.class)
	public void testAdd3() {
		System.out.println("add3");
		String s1 = "foo1";
		String s2 = "2";
		AgileTest instance = new AgileTest();
		int result = instance.add(s1, s2);
		System.out.println(result);
	}

}
