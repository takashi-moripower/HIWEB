package logistics.system.project.common.task;

import java.util.List;

import logistics.system.project.common.Entity.MailSendEntity;
import logistics.system.project.common.service.MailSendService;
import logistics.system.project.utility.Constants;

import org.springframework.beans.factory.annotation.Value;

public class MainTask {
	
	@Value("#{configProperties['mail.batch.count.max']}")
	public String mailBatchCountMax;
	
	public static boolean locked = false;

	// @Scheduled(cron="0 * * * * *")
	public void sendMailBatch() {
		if (locked) {
			return;
		}
		
		locked = true;
		
		try {
			MailSendService mailSendService = (MailSendService) Constants.WEB_APP_CONTEXT
					.getBean("mailSendService");
	
			Integer maxCount = Integer.parseInt(mailBatchCountMax);
			MailSendEntity mailSendEntity = new MailSendEntity();
			mailSendEntity.setMailSendStatus(Constants.MAIL_SEND_SATUS_0);
			List<MailSendEntity> mailSendList = mailSendService
					.getMailSendList(mailSendEntity);
	
			if (mailSendList != null && mailSendList.size() > 0) {
				int i = 0;
				for (MailSendEntity mailSend : mailSendList) {
					mailSendService.insertDoSendMail(mailSend);
	
					if (i > maxCount) {
						break;
					}
	
					i++;
				}
			}
		} finally {
			locked = false;
		}
	}
}