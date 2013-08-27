package tschumacher.rally.el;

/**
 *  <p>A templating engine provides the basic functionality to perform substitutions.</p>
 */

public interface Engine {

    /**
     *  <p>Apply the context to the given template and return the results.</p>
	 * 
	 * 	@param template The template to use for the substitution.  Throw exception if null.
	 * 	@param context  The context (key-value pairs) to be used to lookup variables during evaluation.  Throw exception if null.
     */
    public String evaluate(Template template, Context context);

}
