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
public class MyMatheToolsTest
{

	public MyMatheToolsTest()
	{
	}


	@BeforeClass
	public static void setUpClass()
	{
	}


	@AfterClass
	public static void tearDownClass()
	{
	}


	@Before
	public void setUp()
	{
	}


	@After
	public void tearDown()
	{
	}


	
	@Test
	public void testKgv1()
	{
		int expResult = 120;
		int result = MyMatheTools.kgvn(8,6,5);
		assertEquals(expResult, result);
	}

	
	@Test
	public void testKgv2()
	{
		int expResult = 26;
		int result = MyMatheTools.kgv2(2,13);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGgt1()
	{
		int expResult = 2;
		int result = MyMatheTools.ggtn(8,10,12);
		assertEquals(expResult, result);
	}

	@Test
	public void testGgt2()
	{
		int expResult = 33;
		int result = MyMatheTools.ggt2(33,99);
		assertEquals(expResult, result);
	}
	@Test
	public void testGgt3()
	{
		int expResult = 1;
		int result = MyMatheTools.ggtn(12,16,20,23);
		assertEquals(expResult, result);
	}
	@Test
	public void testGgt4()
	{
		int expResult = 2;
		int result = MyMatheTools.ggtn(24,32,22);
		assertEquals(expResult, result);
	}
	@Test
	public void testGgt5()
	{
		int expResult = 25;
		int result = MyMatheTools.ggtn(100,150,175);
		assertEquals(expResult, result);
	}

}
