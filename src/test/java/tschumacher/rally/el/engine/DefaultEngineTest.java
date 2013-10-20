/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tschumacher.rally.el.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tschumacher.rally.el.Context;
import tschumacher.rally.el.Engine;
import tschumacher.rally.el.Template;
import tschumacher.rally.el.context.DefaultContext;
import tschumacher.rally.el.template.DefaultTemplate;

/**
 *
 * @author Timothy Schumacher, Ph.D. <schumact@gmail.com>
 */
public class DefaultEngineTest {
	
	public DefaultEngineTest() {
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

	@Test(expected=tschumacher.rally.el.Exception.class)
	public void testNullEvaluateCall01() {
		Context context = new DefaultContext();
		Engine engine = new DefaultEngine();
		engine.evaluate(null, context);
	}
	
	@Test(expected=tschumacher.rally.el.Exception.class)
	public void testNullEvaluateCall02() {
		Template template = new DefaultTemplate("Hello, ${user}!");
		Engine engine = new DefaultEngine();
		engine.evaluate(template, null);
	}

	@Test(expected=tschumacher.rally.el.Exception.class)
	public void testNullEvaluateCall03() {
		Engine  engine = new DefaultEngine();
		engine.evaluate(null, null);
	}

	@Test(expected=tschumacher.rally.el.Exception.class)
	public void testAttributeNotFound() {
		final Template template = new DefaultTemplate("My name is ${firstName} ${lastName}");
        final Context context = new DefaultContext();     
        final Engine engine = new DefaultEngine();

        // missed an attribute, should trigger exception
        context.setAttribute("firstName", "Timothy");
        
		engine.evaluate(template, context);
	}
	

	@Test
	public void testSimpleEvaluationHelloWorld() {
        final Template template = new DefaultTemplate("Hello, ${user}!");
        final Context context   = new DefaultContext();     
        final Engine engine     = new DefaultEngine();
        context.setAttribute("user", "World");
        
        final String actualOutput  = engine.evaluate(template, context);
        final String desiredOutput = "Hello, World!";
        
        assertEquals(desiredOutput, actualOutput);
	}
	







    /** test to make sure templates that have various 
     *  combinations of whitespace around keys also work. */
	@Test
	public void testHelloWorldWhiteSpace() {

        // create the context, engine and templates:
        final Context context = new DefaultContext();     
        final Engine engine = new DefaultEngine();
        final Template[] templates = {   
            new DefaultTemplate("Hello, ${   user}!"), 
            new DefaultTemplate("Hello, ${user   }!"),
            new DefaultTemplate("Hello, ${ user }!"),
            new DefaultTemplate("Hello, ${  user }!"),
            new DefaultTemplate("Hello, ${ user  }!")   };

        // set up our context:
        context.setAttribute("user", "World");
        
        // what we expect to get out:
        final String desiredOutput = "Hello, World!";
        
        // now compare the actual results for each template:
        for(Template template : templates) {
            String actualOutput = engine.evaluate(template, context);
            assertEquals(desiredOutput, actualOutput);
        }
	}






	@Test
	public void testNestedEvaluation() {
        // create the context, engine and template:
        final Context context = new DefaultContext();     
        final Engine engine = new DefaultEngine();
        final Template template = new DefaultTemplate("Hello, ${ ${ var  }   }!");

        // populate the context 
        context.setAttribute("var","user");
        context.setAttribute("user","World");
        
        // what we expect to get out given the above:
        final String desiredOutput = "Hello, World!";

        // what we actually get out:
        final String actualOutput  = engine.evaluate(template, context);
        
        // compare results:
        assertEquals(desiredOutput, actualOutput);
	}
	












	@Test
	public void testKeysWithEscapeCharacter() {

        // create the context, engine and template:
        final Context context = new DefaultContext();     
        final Engine engine = new DefaultEngine();
        // note, since $ is escape, we have to write $$ 
        final Template template = new DefaultTemplate("${te$$t}");

        // populate the context 
        context.setAttribute("te$t","it works!");
        
        // what we expect to get out given the above:
        final String desiredOutput = "it works!";

        // what we actually get out:
        final String actualOutput  = engine.evaluate(template, context);
        
        // compare results:
        assertEquals(desiredOutput, actualOutput);
	}



	
	@Test
	public void testEscapeCharInOutput() {

        // set up the context, engine and template:
        final Context context = new DefaultContext();     
        final Engine engine = new DefaultEngine();
		final Template template = new DefaultTemplate(
                "I want to work at ${desiredCompany} and make $$${desiredSalary}/year!");
		
        // set up the context 
        context.setAttribute("desiredCompany", "Rally Software");
		context.setAttribute("desiredSalary", "250,000");

        final String desiredOutput   = "I want to work at Rally Software and make $250,000/year!";
		final String actualOutput = engine.evaluate(template, context);

		assertEquals(desiredOutput, actualOutput);
	}






	@Test
	public void testEscapeSequences() {
        // set up the context, engine and template:
        final Context context = new DefaultContext();     
        final Engine engine = new DefaultEngine();
        final Template template = new DefaultTemplate("$${firstName} = ${firstName}, $${lastName} = ${lastName}");

        // populate the context:
        context.setAttribute("firstName", "Timothy");
		context.setAttribute("lastName", "Schumacher");

        final String desiredOutput = "${firstName} = Timothy, ${lastName} = Schumacher";
		final String actualOutput = engine.evaluate(template, context);

        // compare the actual with the desired:
		assertEquals(desiredOutput, actualOutput);
	}













    /**
     *  Test a attribute set that has some values other than string, e.g. int
     */
	@Test
	public void testStringIntegerKVPairs() {

        // set up the context, engine and template:
        final Context context = new DefaultContext();     
        final Engine engine = new DefaultEngine();
		final Template template = new DefaultTemplate("test ${test}: an int = ${int}");
		
        // set up the context with non-string values:
        context.setAttribute("test", 11);
		context.setAttribute("int", 36);

        final String desiredOutput = "test 11: an int = 36";
		final String actualOutput = engine.evaluate(template, context);

		assertEquals(desiredOutput, actualOutput);
	}



	@Test(expected=tschumacher.rally.el.Exception.class)
	public void testDanglingEscapeCharAtEndOfTemplate() {

		final Engine engine = new DefaultEngine();
		final Context context = new DefaultContext();
        // bad template: dangling "$" at end of template string:
		final Template template = new DefaultTemplate("test ${test}: an int = ${int}$");
		
        // populate the context:
        context.setAttribute("test", 11);
		context.setAttribute("int", 36);
		
        final String desiredOutput = "test 11: an int = 36";
        final String actualOutput = engine.evaluate(template, context);
		
        assertEquals(desiredOutput, actualOutput);
	}

	@Test(expected=tschumacher.rally.el.Exception.class)
	public void testUnclosedBracesMalformedTemplate() {

		final Engine engine = new DefaultEngine();
		final Context context = new DefaultContext();
        // bad template: open a "${" without closing it:
		final Template template = new DefaultTemplate("test ${test}: an int = ${int");
		
        context.setAttribute("test", 11);
		context.setAttribute("int", 36);

        final String desiredOutput = "test 11: an int = 36";
        final String actualOutput = engine.evaluate(template, context);

        assertEquals(desiredOutput, actualOutput);
	}

}
