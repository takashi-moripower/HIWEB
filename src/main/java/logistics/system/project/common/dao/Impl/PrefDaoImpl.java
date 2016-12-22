package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.dao.PrefDao;
import logistics.system.project.utility.ComUtils;

import org.springframework.stereotype.Repository;

@Repository("prefDao")
public class PrefDaoImpl extends BaseDao implements PrefDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PrefEntity> getAllPref(String createDt) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("createDt", createDt);
		param.put("sysDate", ComUtils.getSysDate());
		return (List<PrefEntity>) getSqlMapClientTemplate().queryForList("getAllPref", param);
	}

	@SuppressWarnings("unchecked")
	@Override
//	@Cacheable(value="data", key="#root.methodName")
	public List<PrefEntity> getAllPrefList() {

		return (List<PrefEntity>) getSqlMapClientTemplate().queryForList("getAllPrefList");
	}

	@Override
//	@Cacheable(value="data", key="#root.methodName  + #root.args[0]")
	public String getPreCdByPrefName(String prefName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("prefName", prefName);;
		return (String) getSqlMapClientTemplate().queryForObject("getPreCdByPrefName", map);
	}

	@Override
	public String getPreNameByPrefCd(String prefCd) {
		return (String) getSqlMapClientTemplate().queryForObject("getPreNameByPrefCd", prefCd);
	}

}
