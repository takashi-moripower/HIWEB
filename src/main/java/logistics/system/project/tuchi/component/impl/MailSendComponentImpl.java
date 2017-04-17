package logistics.system.project.tuchi.component.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import logistics.system.project.tuchi.component.MailSendComponent;
import logistics.system.project.utility.Constants;
import logistics.system.project.utility.EmailSendTool;

@Component("mailSendComponent")
public class MailSendComponentImpl implements MailSendComponent {
	@Value("#{configProperties['mail.smtp.addr']}")
	private String smtpAddr;

	@Value("#{configProperties['mail.smtp.host']}")
	private String smtpHost;

	@Value("#{configProperties['mail.smtp.port']}")
	private String smtpPort;

	@Value("#{configProperties['mail.smtp.username']}")
	private String smtpUsername;

	@Value("#{configProperties['mail.smtp.user.pass']}")
	private String smtpUserPass;

	@Value("#{configProperties['mail.user.person.name']}")
	private String UserPersonName;

	@Value("#{configProperties['mail.send.mode']}")
	private int sendMode;

	@Value("#{configProperties['mail.send.filter']}")
	private String sendFilter;

	private final static Logger logger = Logger.getLogger(MailSendComponentImpl.class);

	@Override
	public int send(String subject, String toAddr, String content) {

		if (sendMode == Constants.MAIL_SEND_MODE_FILTER && toAddr.indexOf(sendFilter) < 0) {
			return SEND_FILTERED;
		}

		EmailSendTool sendEmail = new EmailSendTool(smtpHost, smtpPort, smtpUsername, smtpUserPass, smtpAddr, toAddr,
				subject, content, UserPersonName, "", "");

		sendEmail.setContextFormat(EmailSendTool.CONTEXT_FORMAT_HTML);

		try {
			sendEmail.send();
		} catch (Exception e) {

			logger.error("tuchi mail send failed:" + e.getMessage());
			return SEND_FAILED;
		}

		return SEND_SUCCESS;
	}

	@Override
	public String getBaseUrl(HttpServletRequest request) {
		String baseUrl = String.format("%s://%s:%d%s/", request.getScheme(), request.getServerName(),
				request.getServerPort(), request.getContextPath());
		return baseUrl;
	}
}
