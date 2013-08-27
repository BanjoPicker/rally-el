/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tschumacher.rally.el.template;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tschumacher.rally.el.Template;

/**
 *
 * @author Timothy Schumacher, Ph.D. <schumact@gmail.com>
 */
public class DefaultTemplateTest {
	
	public DefaultTemplateTest() {
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
	 * Test of length method, of class DefaultTemplate.
	 */
	@Test
	public void testLength() {
		Template template = new DefaultTemplate("${foo}");
		assertEquals("${foo}".length(), template.length());
	}

	/**
	 * Test of charAt method, of class DefaultTemplate.
	 */
	@Test
	public void testCharAt() {
		Template template = new DefaultTemplate("${foo}");
		assertEquals(template.charAt(0), '$');
		assertEquals(template.charAt(1), '{');
		assertEquals(template.charAt(2), 'f');
		
	}
}
