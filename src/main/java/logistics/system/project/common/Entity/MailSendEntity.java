package logistics.system.project.common.Entity;


public class MailSendEntity extends BaseEntity {
	
	// メール送信ＩＤ
	private Integer mailSendId;
	
	// メール送信宛先
	private String mailSendTo;
	
	// メール送信件名
	private String mailSendSub;
	
	// メール送信本文
	private String mailSendText;
	
	// 案件番号
	private String ankenNo;
	
	// 案件ステータス
	private String ankenStatus;;
	
	// メール送信ステータス
	private String mailSendStatus;
	
	// メール送信回数
	private Integer mailSendKs;

	public Integer getMailSendId() {
		return mailSendId;
	}

	public void setMailSendId(Integer mailSendId) {
		this.mailSendId = mailSendId;
	}

	public String getMailSendTo() {
		return mailSendTo;
	}

	public void setMailSendTo(String mailSendTo) {
		this.mailSendTo = mailSendTo;
	}

	public String getMailSendSub() {
		return mailSendSub;
	}

	public void setMailSendSub(String mailSendSub) {
		this.mailSendSub = mailSendSub;
	}

	public String getMailSendText() {
		return mailSendText;
	}

	public void setMailSendText(String mailSendText) {
		this.mailSendText = mailSendText;
	}

	public String getAnkenNo() {
		return ankenNo;
	}

	public void setAnkenNo(String ankenNo) {
		this.ankenNo = ankenNo;
	}

	public String getAnkenStatus() {
		return ankenStatus;
	}

	public void setAnkenStatus(String ankenStatus) {
		this.ankenStatus = ankenStatus;
	}

	public String getMailSendStatus() {
		return mailSendStatus;
	}

	public void setMailSendStatus(String mailSendStatus) {
		this.mailSendStatus = mailSendStatus;
	}

	public Integer getMailSendKs() {
		return mailSendKs;
	}

	public void setMailSendKs(Integer mailSendKs) {
		this.mailSendKs = mailSendKs;
	}
}
