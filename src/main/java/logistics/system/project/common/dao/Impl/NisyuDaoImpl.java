package logistics.system.project.common.dao.Impl;

import java.util.List;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.dao.NisyuDao;

import org.springframework.stereotype.Repository;

@Repository("nisyuDao")
public class NisyuDaoImpl extends BaseDao implements NisyuDao{

	@SuppressWarnings("unchecked")
	@Override
//	@Cacheable(value="data", key="#root.methodName")
	public List<NisyuEntity> getAllNisyuList() {

		return (List<NisyuEntity>) getSqlMapClientTemplate().queryForList("getAllNisyuList");
	}
}
