package tschumacher.rally.el.template;

import tschumacher.rally.el.Template;

/**
 *
 * @author Timothy Schumacher, Ph.D. <schumact@gmail.com>
 */
public class DefaultTemplate implements Template {

	private final String template;
	
	public DefaultTemplate(String template) {
		if(template==null) {
			throw new tschumacher.rally.el.Exception("templates cannot be null");
		} 
		this.template = template;
	}
	
	public int length() {
		return this.template.length();
	}

	public char charAt(int index) {
		return this.template.charAt(index);
	}
}
