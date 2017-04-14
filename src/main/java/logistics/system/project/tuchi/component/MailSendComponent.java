package logistics.system.project.tuchi.component;

public interface MailSendComponent {
	public static final int SEND_FILTERED = 10;
	public static final int SEND_FAILED = 1;
	public static final int SEND_SUCCESS = 0;

	public int send(String subject, String toAddr, String content);
}
