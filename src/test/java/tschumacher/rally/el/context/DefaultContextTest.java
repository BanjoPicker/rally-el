/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tschumacher.rally.el.context;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tschumacher.rally.el.Context;

/**
 *
 * @author Timothy Schumacher, Ph.D. <schumact@gmail.com>
 */
public class DefaultContextTest {
	
	public DefaultContextTest() {
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

	@Test
	public void test01() {
		Context context = new DefaultContext();
		context.setAttribute("foo", "bar");
		assertEquals("bar", context.getAttribute("foo"));
	}
	
	@Test
	public void test02() {
		Context context = new DefaultContext();
		context.setAttribute("foo", "bar");
		assertEquals("bar", context.getAttribute("foo"));
		context.setAttribute("foo", null);
		assertNull( context.getAttribute("foo") );
	}
	
	@Test
	public void test03() {
		Context context = new DefaultContext();
		context.setAttribute("foo", "bar");
		assertEquals("bar", context.getAttribute("foo"));
		context.setAttribute("foo", "baz");
		assertEquals("baz", context.getAttribute("foo"));
	}
}
