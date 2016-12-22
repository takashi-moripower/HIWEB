package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.MailSendEntity;

public interface MailSendDao {
	void insertMailSend(MailSendEntity mailSendEntity);
	
	int updateMailSendStatus(MailSendEntity mailSendEntity);
	
	int updateMailSendKs(MailSendEntity mailSendEntity);
	
	int updateMailSendStatusAndKs(MailSendEntity mailSendEntity);
	
	List<MailSendEntity> getMailSendList(MailSendEntity mailSendEntity);
}
