package logistics.system.project.utility;

import java.net.URL;

public class ResourceLoader {
	public static String CLASS_PATH_PREFIX = "classpath:";

	public static URL getResource(String resource) {
		ClassLoader classLoader = null;
		classLoader = Thread.currentThread().getContextClassLoader();
		return classLoader.getResource(resource);
	}

	public static String getPath(String resource) {
		if (resource != null) {
			if (resource.startsWith(CLASS_PATH_PREFIX)) {
				resource = getPath("")
						+ resource.replaceAll(CLASS_PATH_PREFIX, "");
			}
		}

		URL url = getResource(resource);
		if (url == null)
			return null;
		return url.getPath().replaceAll("%20", " ");
	}

	public static String getPath(String resource, Class clazz) {
		URL url = getResource(resource, clazz);
		if (url == null)
			return null;
		return url.getPath().replaceAll("%20", " ");
	}

	public static URL getResource(String resource, Class clazz) {
		return clazz.getResource(resource);
	}

	/**
	 * If running under JDK 1.2 load the specified class using the
	 * <code>Thread</code> <code>contextClassLoader</code> if that fails try
	 * Class.forname. Under JDK 1.1 only Class.forName is used.
	 * 
	 */
	public static Class loadClass(String clazz) throws ClassNotFoundException {
		return Class.forName(clazz);
	}
}
