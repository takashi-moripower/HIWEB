package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.PrefEntity;

public interface PrefDao {

	public List<PrefEntity> getAllPref(String createDt);
	
	public List<PrefEntity> getAllPrefList();
	
	public String getPreCdByPrefName(String prefName);
	
	public String getPreNameByPrefCd(String prefCd);
}
