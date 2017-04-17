package logistics.system.project.tuchi.component.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import logistics.system.project.tuchi.component.DynamicPropertyComponent;

@Component("dynamicPropertyComponent")
public class DynamicPropertyComponentImpl implements DynamicPropertyComponent {

	Properties properties;
	File file;

	public void init(HttpServletRequest request) {

		try {
			setFile(request);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		load();
	}

	public void load() {
		properties = new Properties();
		try {
			InputStream IS = new FileInputStream(file);
			properties.load(IS);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void store(String comments) {
		try {
			OutputStream OS = new FileOutputStream(file);
			properties.store(OS, comments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}

	protected void setFile(HttpServletRequest request) throws IOException {
		ServletContext sc = request.getSession().getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		Resource r = wac.getResource("classpath:parameter2.properties");

		file = r.getFile();
	}
}
