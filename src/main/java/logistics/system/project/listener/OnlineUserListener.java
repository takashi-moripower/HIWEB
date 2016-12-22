package logistics.system.project.listener;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import logistics.system.project.utility.Constants;
import logistics.system.project.utility.WebUtils;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;

public class OnlineUserListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		ApplicationContext ctx = Constants.WEB_APP_CONTEXT;
		Properties prop = (Properties) ctx.getBean("configProperties");
		String ankenPicWebrootPathTmp = prop.getProperty("anken.pic.webroot.path.tmp");
		
		String sessionTmpDir = WebUtils.getSessionTmpDir(event.getSession(), ankenPicWebrootPathTmp);
		
		File dir = new File(sessionTmpDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		// copy default pic to root dir
		try {
			FileUtils.copyFile(new File(event.getSession().getServletContext().getRealPath("/")
					+ File.separator + "resources" + File.separator + "image"
					+ File.separator + Constants.DEFAULT_PIC_NAME), new File(sessionTmpDir
					+ File.separator + Constants.DEFAULT_PIC_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		ApplicationContext ctx = Constants.WEB_APP_CONTEXT;
		Properties prop = (Properties) ctx.getBean("configProperties");
		String ankenPicWebrootPathTmp = prop.getProperty("anken.pic.webroot.path.tmp");
		try {
			FileUtils.deleteDirectory(new File(WebUtils.getSessionTmpDir(
					event.getSession(), ankenPicWebrootPathTmp)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}