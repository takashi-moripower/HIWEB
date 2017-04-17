package logistics.system.project.tuchi.component;

import javax.servlet.http.HttpServletRequest;

public interface DynamicPropertyComponent {

	public void init( HttpServletRequest request);
	public void load();
	public void store( String comment );

	public void setProperty(String key,String value);
	public String getProperty(String key);
}
