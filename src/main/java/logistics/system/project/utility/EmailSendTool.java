package logistics.system.project.utility;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSendTool {
	// メールサーバ
	private String host;
	// メールSMPTポート
	private String port;
	// stmpユーザID
	private String username;
	// stmpユーザﾊﾟｽﾜｰﾄﾞ
	private String password;

	private String mailHeadName = "";

	private String mailHeadValue = "";

	private String mailTo;

	private String mailFrom;

	private String mailSubject = "";

	private String mailBody = "";

	private String personalName = "";

	public EmailSendTool() {
	}

	public EmailSendTool(String host, String port, String username, String password, String mailFrom,
			String mailTo, String subject, String text, String personalName,
			String headName, String headValue) {
		this.host = host;
		this.port =  port;
		this.username = username;
		this.mailFrom = mailFrom;
		this.password = password;
		this.mailTo = mailTo;
		this.mailSubject = subject;
		this.mailBody = text;
		this.personalName = personalName;
		this.mailHeadName = headName;
		this.mailHeadValue = headValue;
	}

	public void send() throws MessagingException, UnsupportedEncodingException {
		Properties props = new Properties();
		Authenticator auth = new Email_Autherticator();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, auth);
		MimeMessage message = new MimeMessage(session);
		message.setSubject(mailSubject);
		message.setText(mailBody);
		message.setHeader(mailHeadName, mailHeadValue);

		message.setSentDate(new Date());
		Address address = new InternetAddress(mailFrom, personalName);
		message.setFrom(address);
		Address toAddress = new InternetAddress(mailTo);
		message.addRecipient(Message.RecipientType.TO, toAddress);
		Transport.send(message);
	}

	public class Email_Autherticator extends Authenticator {
		public Email_Autherticator() {
			super();
		}

		public Email_Autherticator(String user, String pwd) {
			super();
			username = user;
			password = pwd;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail_head_name() {
		return mailHeadName;
	}

	public void setMail_head_name(String mail_head_name) {
		this.mailHeadName = mail_head_name;
	}

	public String getMail_head_value() {
		return mailHeadValue;
	}

	public void setMail_head_value(String mail_head_value) {
		this.mailHeadValue = mail_head_value;
	}

	public String getMail_to() {
		return mailTo;
	}

	public void setMail_to(String mail_to) {
		this.mailTo = mail_to;
	}

	public String getMail_from() {
		return mailFrom;
	}

	public void setMail_from(String mail_from) {
		this.mailFrom = mail_from;
	}

	public String getMail_subject() {
		return mailSubject;
	}

	public void setMail_subject(String mail_subject) {
		this.mailSubject = mail_subject;
	}

	public String getMail_body() {
		return mailBody;
	}

	public void setMail_body(String mail_body) {
		this.mailBody = mail_body;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}