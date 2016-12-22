
package logistics.system.project.utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;

public class AnkenPicUtils {
	public static String getAnkenPicTmpDir() {
		ApplicationContext ctx = Constants.WEB_APP_CONTEXT;
		Properties prop = (Properties) ctx.getBean("configProperties");
		String ankenPicWebrootPathTmp = prop.getProperty("anken.pic.webroot.path.tmp");
		
		return ankenPicWebrootPathTmp;
	}

	public static String getAnkenPicDir() {
		ApplicationContext ctx = Constants.WEB_APP_CONTEXT;
		Properties prop = (Properties) ctx.getBean("configProperties");
		String ankenPicWebrootPath = prop.getProperty("anken.pic.root.path");
		
		return ankenPicWebrootPath;
	}

	public static String getTmpFileRoot() {
		return getAnkenPicTmpDir();
	}

	public static String getTmpFileRootAbs() {
		return WebUtils.getAppTmpRootDirAbsPath(getTmpFileRoot())/* + File.separator + ankenId*/;
	}

	public static String getTmpFilePath(String picTmpNm) {
		return WebUtils.getAppTmpRootDirAbsPath(getTmpFileRoot()) + File.separator + picTmpNm;
	}

	public static String getTmpFileName() {
		return new Date().getTime() + "";
	}

	public static String getDestFileRoot(String ankenId, int index) {
		return getAnkenPicDir() + File.separator + ankenId;
	}

	public static String getDestFilePath(String ankenId, int index) {
		return getDestFileRoot(ankenId, index) + File.separator + ankenId
				+ index;
	}

	public static String getDestFileName(String ankenId, int index) {
		return ankenId + index;
	}

	public static boolean dealPic(String picTmpNm, String ankenId, int index, String picChg) {
		try {
			if ("1".equals(picChg)) { // update
				copyTmpFileToDest(picTmpNm, ankenId, index);
			} else if ("2".equals(picChg)) { // delete
				File file = new File(getDestFilePath(ankenId, index));
				file.delete();
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static boolean copyTmpFileToDest(String picTmpNm, String ankenId, int index) {
		try {
			String tmpPicPath = getTmpFilePath(picTmpNm);
			String rootPath = getDestFileRoot(ankenId, index);
			File root = new File(rootPath);
			if (!root.exists()) {
				root.mkdirs();
			}
			String destFilePath = getDestFilePath(ankenId, index);
			FileUtils.copyFile(new File(tmpPicPath), new File(destFilePath));
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	
	public static boolean copyPicToTmp(String ankenId, String picTmpNm, int idx) {
		try {
			FileUtils.copyFile(new File(getDestFilePath(ankenId, idx)), new File(getTmpFilePath(picTmpNm)));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
