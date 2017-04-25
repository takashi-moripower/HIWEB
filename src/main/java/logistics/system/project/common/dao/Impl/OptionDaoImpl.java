package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.dao.OptionDao;

@Repository("optionDao")
public class OptionDaoImpl extends BaseDao implements OptionDao {

	@Override
	public String get(String key) {
		return (String) getSqlMapClientTemplate().queryForObject("getOption",key);
	}

	@Override
	public void set(String key , String value) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("key", key);
		param.put("value", value);

		getSqlMapClientTemplate().update("updateOption",param);
	}
}
