package logistics.system.project.utility;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtils {
	
	public static String getContextRealPath() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		
		HttpSession session = request.getSession();
		
		return session.getServletContext().getRealPath("/");
	}
	
	public static String getAppTmpRootDir(String tmpDirRoot) {
		if (tmpDirRoot == null) {
			tmpDirRoot = Constants.DEFAULT_PIC_TMP_DIR_ROOT;
		}
		
		return tmpDirRoot.replaceAll("\\\\", "\\" + File.separator).replaceAll("/", "\\" + File.separator);
	}
	
	public static String getAppTmpRootDirAbsPath(String tmpDirRoot) {
		return getSessionTmpDir(tmpDirRoot);
	}
	
	public static String getSessionTmpDir(String tmpDirRoot) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		
		HttpSession session = request.getSession();
		
		return getSessionTmpDir(session, tmpDirRoot);
	}
	
	public static String getSessionTmpDir(HttpSession session, String tmpDirRoot) {
		
		String sessionTmpDir = session.getServletContext().getRealPath("/") + getAppTmpRootDir(tmpDirRoot) + File.separator + session.getId();
		File dir = new File(sessionTmpDir);
		if (!dir.exists() || !dir.isDirectory()) {
			dir.mkdirs();
		}
		
		return sessionTmpDir;
	}
	
	public static String getSessionTmpDirUrl(String tmpDirRoot) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		
		HttpSession session = request.getSession();
		return (getAppTmpRootDir(tmpDirRoot) + File.separator + session.getId()).replaceAll("\\\\", "/");
	}
}