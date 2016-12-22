package logistics.system.project.common.dao.Impl;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.PostCodeEntity;
import logistics.system.project.common.dao.PostCodeDao;

import org.springframework.stereotype.Repository;

@Repository("postCodeDao")
public class PostCodeDaoImpl extends BaseDao implements PostCodeDao {

	@Override
	public PostCodeEntity getPostCodeInfo(String postCode) {

		return (PostCodeEntity) getSqlMapClientTemplate().queryForObject("getPostCodeInfo", postCode);
	}

}
