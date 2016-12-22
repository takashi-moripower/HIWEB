package logistics.system.project.common.dao.Impl;

import java.util.List;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.AreaEntity;
import logistics.system.project.common.dao.AreaDao;

import org.springframework.stereotype.Repository;

@Repository("areaDao")
public class AreaDaoImpl extends BaseDao implements AreaDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaEntity> getAllArea() {
		return (List<AreaEntity>) getSqlMapClientTemplate().queryForList("getAllArea");
	}

}
