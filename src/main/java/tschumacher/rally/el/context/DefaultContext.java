package tschumacher.rally.el.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Timothy Schumacher, Ph.D. <schumact@gmail.com>
 */
public class DefaultContext implements tschumacher.rally.el.Context {
	
	private final Map<String,Object> map;

	/**
	 * 	<p>Create a new empty context.</p>
	 */
	public DefaultContext() {
		map = new HashMap<String,Object>();
	}

	/**
	 * 	<p>Create a new context initialized with the properties given.</p>
	 * 	@param properties 
	 */
	public DefaultContext(Properties properties) {
		map = new HashMap<String,Object>();
		for(String key : properties.stringPropertyNames()) {
			map.put(key,properties.getProperty(key));
		}
	}

	
	public void setAttribute(String key, Object value) {
		if(value == null) {
			map.remove(key);
		} else {
			map.put(key, value);
		}
	}

	public Object getAttribute(String key) {
		return map.get(key);
	}

}
