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



		Constants.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

		ShukaAreaService shukaAreaService = (ShukaAreaService) Constants.WEB_APP_CONTEXT.getBean("areaService");
		AnkenListSearchService ankenListSearchService = (AnkenListSearchService) Constants.WEB_APP_CONTEXT.getBean("ankenListSearchService");




		List<NisyuEntity> nisyuList = shukaAreaService.getAllNisyuList();

		List<NisugateEntity> nisugateList = shukaAreaService.getAllNisugateList();

		List<PrefEntity> prefList = shukaAreaService.getAllPrefList();

		List<SyasyuEntity> syasyuList = ankenListSearchService.getSyasyuList();

		List<TruckOpEntity> truckOpList = ankenListSearchService.getTruckOpList();





//		event.getServletContext().setAttribute("nisyuList", nisyuList);
//		event.getServletContext().setAttribute("nisugateList", nisugateList);
//		event.getServletContext().setAttribute("prefList", prefList);
//		event.getServletContext().setAttribute("syasyuList", syasyuList);
//		event.getServletContext().setAttribute("truckOpList", truckOpList);

		Constants.MAST_NISYU_LIST = nisyuList;
		Constants.MAST_NISUGATE_LIST = nisugateList;
		Constants.MAST_PREF_LIST = prefList;
		Constants.MAST_SYASYU_LIST = syasyuList;
		Constants.MAST_TRUCKOP_LIST = truckOpList;

		Properties prop = (Properties) Constants.WEB_APP_CONTEXT.getBean("configProperties");
		String ankenPicWebrootPathTmp = prop.getProperty("anken.pic.webroot.path.tmp");

		try {
			FileUtils.deleteDirectory(new File(ankenPicWebrootPathTmp));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
