package logistics.system.project.common.dao.Impl;

import java.util.List;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.MailSendEntity;
import logistics.system.project.common.dao.MailSendDao;

import org.springframework.stereotype.Repository;

@Repository("mailSendDao")
public class MailSendDaoImpl extends BaseDao implements MailSendDao {
	@Override
	public void insertMailSend(MailSendEntity mailSendEntity) {
		getSqlMapClientTemplate().insert("insertMailSend", mailSendEntity);
	}

	@Override
	public int updateMailSendStatus(MailSendEntity mailSendEntity) {
		return getSqlMapClientTemplate().update("updateMailSendStatus", mailSendEntity);
	}

	@Override
	public int updateMailSendKs(MailSendEntity mailSendEntity) {
		return getSqlMapClientTemplate().update("updateMailSendKs", mailSendEntity);
	}

	@Override
	public int updateMailSendStatusAndKs(MailSendEntity mailSendEntity) {
		return getSqlMapClientTemplate().update("updateMailSendStatusAndKs", mailSendEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MailSendEntity> getMailSendList(MailSendEntity mailSendEntity) {
		return getSqlMapClientTemplate().queryForList("getMailSendList", mailSendEntity);
	}
}
