package logistics.system.project.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * 
 * @version
 */
public class Freemarker {

	public static void printFile(String packageName, String templateName, Map<String,Object> dataMap, String outFilePath) throws Exception{
		try {
			File file = new File(outFilePath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("utf-8");
			cfg.setClassForTemplateLoading(new Freemarker().getClass(), packageName);
			Template template = cfg.getTemplate(templateName);
			template.setEncoding("utf-8");
			template.process(dataMap, out);
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printFile(String packageName, String templateName, Map<String,Object> dataMap, Writer writer) throws Exception{
		try {
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("utf-8");
			cfg.setClassForTemplateLoading(new Freemarker().getClass(), packageName);
			Template template = cfg.getTemplate(templateName);
			template.setEncoding("utf-8");
			template.process(dataMap, writer);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getTemplateString(String packageName, String templateName, Map<String,Object> dataMap) throws Exception{
		try {
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("utf-8");
			cfg.setClassForTemplateLoading(new Freemarker().getClass(), packageName);
			Template template = cfg.getTemplate(templateName);
			template.setEncoding("utf-8");
			Writer writer = new StringWriter(2048);
			template.process(dataMap, writer);
			
			return writer.toString();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
