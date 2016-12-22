package logistics.system.project.common.service;

import java.util.List;

import logistics.system.project.common.Entity.MailSendEntity;

public interface MailSendService {
	void insertMailSend(MailSendEntity mailSendEntity);

	int updateMailSendStatus(MailSendEntity mailSendEntity);

	int updateMailSendKs(MailSendEntity mailSendEntity);

	int updateMailSendStatusAndKs(MailSendEntity mailSendEntity);

	List<MailSendEntity> getMailSendList(MailSendEntity mailSendEntity);

	void insertDoSendMail(MailSendEntity mailSendEntity);

	void insertMailSend(String ankenNo, String prevUpdateUserId, String userId) throws Exception;
}
