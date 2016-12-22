package logistics.system.project.common.dao.Impl;

import java.util.List;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.dao.SyasyuDao;

import org.springframework.stereotype.Repository;

@Repository("syasyuDao")
public class SyasyuDaoImpl extends BaseDao implements SyasyuDao {

	@SuppressWarnings("unchecked")
	@Override
//	@Cacheable(value="data", key="#root.methodName")
	public List<SyasyuEntity> getAllSyasyu() {
		
		return (List<SyasyuEntity>) getSqlMapClientTemplate().queryForList("getAllSyasyu");
	}



}
