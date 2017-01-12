package logistics.system.project.listener;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.service.AnkenListSearchService;
import logistics.system.project.common.service.ShukaAreaService;
import logistics.system.project.utility.Constants;

import org.apache.commons.io.FileUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
* WebAppContextListener.java
*
* sun.zhi
* @version 1.0
 */
public class WebAppContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {

		Constants.initMasterList(event);


		Properties prop = (Properties) Constants.WEB_APP_CONTEXT.getBean("configProperties");
		String ankenPicWebrootPathTmp = prop.getProperty("anken.pic.webroot.path.tmp");

		try {
			FileUtils.deleteDirectory(new File(ankenPicWebrootPathTmp));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
