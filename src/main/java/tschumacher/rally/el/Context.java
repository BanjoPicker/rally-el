package tschumacher.rally.el;

/**
 *  <p>An expression language context.  This is a map-like structure for holding key-value pairs to be used in a template engine.</p>
 */
public interface Context {

    /**
     *  <p>Put an object on the context under the specified key.  If object is null, remove the key from the context.</p>
     */
    public void setAttribute(String key, Object value);

    /**
     *  <p>Retrieve an object from the context.  Returns null if no object present under the given key.</p>
     */
    public Object getAttribute(String key);

}
