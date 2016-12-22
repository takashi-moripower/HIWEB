package logistics.system.project.base.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDao extends SqlMapClientDaoSupport {

	@Resource(name = "sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClientTemplate(sqlMapClientTemplate);
	}
}
