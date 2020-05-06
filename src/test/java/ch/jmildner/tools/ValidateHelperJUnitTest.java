/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.jmildner.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author johann
 */
public class ValidateHelperJUnitTest
{
    
    public ValidateHelperJUnitTest()
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

    /**
     * Test of isEmpty method, of class ValidateHelper.
     */
    @Test
    public void testIsEmpty()
    {
        System.out.println("isEmpty");
        String string = "";
        boolean expResult = true;
        boolean result = ValidateHelper.isEmpty(string);
        assertEquals(expResult, result);
     }

    /**
     * Test of isInteger method, of class ValidateHelper.
     */
    @Test
    public void testIsInteger()
    {
        System.out.println("isInteger");
        String string = "";
        boolean expResult = false;
        boolean result = ValidateHelper.isInteger(string);
        assertEquals(expResult, result);
      }

    /**
     * Test of isDouble method, of class ValidateHelper.
     */
    @Test
    public void testIsDouble()
    {
        System.out.println("isDouble");
        String string = "";
        boolean expResult = false;
        boolean result = ValidateHelper.isDouble(string);
        assertEquals(expResult, result);
     }

    /**
     * Test of isLong method, of class ValidateHelper.
     */
    @Test
    public void testIsLong()
    {
        System.out.println("isLong");
        String string = "";
        boolean expResult = false;
        boolean result = ValidateHelper.isLong(string);
        assertEquals(expResult, result);
     }

    /**
     * Test of isFloat method, of class ValidateHelper.
     */
    @Test
    public void testIsFloat()
    {
        System.out.println("isFloat");
        String string = "";
        boolean expResult = false;
        boolean result = ValidateHelper.isFloat(string);
        assertEquals(expResult, result);
     }

    /**
     * Test of isNumeric method, of class ValidateHelper.
     */
    @Test
    public void testIsNumeric()
    {
        System.out.println("isNumeric");
        String string = "";
        String type = "";
        boolean expResult = false;
        boolean result = ValidateHelper.isNumeric(string, type);
        assertEquals(expResult, result);
     }
    
}
