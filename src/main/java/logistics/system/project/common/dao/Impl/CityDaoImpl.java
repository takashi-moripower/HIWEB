package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.CityEntity;
import logistics.system.project.common.dao.CityDao;
import logistics.system.project.utility.ComUtils;

import org.springframework.stereotype.Repository;

@Repository("cityDao")
public class CityDaoImpl extends BaseDao implements CityDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CityEntity> getCitiesByPrefCd(String prefCd, String createDt) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("prefCd", prefCd);
		param.put("createDt", createDt);
		param.put("sysDate", ComUtils.getSysDate());
		return (List<CityEntity>) getSqlMapClientTemplate().queryForList("getCitiesByPrefCd", param);
	}

	public List<CityEntity> getAllCities(){
		return (List<CityEntity>) getSqlMapClientTemplate().queryForList("getAllCities");
	}

}
