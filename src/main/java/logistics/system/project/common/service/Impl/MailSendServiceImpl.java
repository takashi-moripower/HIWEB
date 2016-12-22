package logistics.system.project.common.service.Impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.Entity.MailSendEntity;
import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.common.dao.AnkenOrderDao;
import logistics.system.project.common.dao.MailSendDao;
import logistics.system.project.common.dao.MemberDao;
import logistics.system.project.common.dao.PersonalDao;
import logistics.system.project.common.service.MailSendService;
import logistics.system.project.utility.Constants;
import logistics.system.project.utility.EmailSendTool;
import logistics.system.project.utility.Freemarker;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service("mailSendService")
public class MailSendServiceImpl implements MailSendService {

	@Autowired
	@Qualifier("mailSendDao")
	private MailSendDao mailSendDao;

	@Autowired
	@Qualifier("ankenDao")
	private AnkenDao ankenDao;

	@Autowired
	@Qualifier("ankenOrderDao")
	private AnkenOrderDao ankenOrderDao;

	@Autowired
	@Qualifier("memberDao")
	private MemberDao memberDao;

	@Autowired
	@Qualifier("personalDao")
	private PersonalDao personalDao;

	@Value("#{configProperties['mail.sub']}")
	private String mailSub;

	@Value("#{configProperties['trail.company.cd']}")
	private String trailCompanyCd;

	@Value("#{configProperties['mail.smtp.addr']}")
	private String mailSmtpAddr;

	@Value("#{configProperties['mail.smtp.host']}")
	private String mailSmtpHost;

	@Value("#{configProperties['mail.smtp.port']}")
	private String mailSmtpPort;

	@Value("#{configProperties['mail.smtp.username']}")
	private String mailSmtpUsername;

	@Value("#{configProperties['mail.smtp.user.pass']}")
	private String mailSmtpUserPass;

	@Value("#{configProperties['mail.user.person.name']}")
	private String mailUserPersonName;

	@Value("#{configProperties['mail.sub']}")
	private String mailSubTemplate;

	@Value("#{configProperties['mail.send.mode']}")
	private String mailSendMode;

	@Override
	public void insertMailSend(MailSendEntity mailSendEntity) {
		mailSendDao.insertMailSend(mailSendEntity);
	}

	@Override
	public int updateMailSendStatus(MailSendEntity mailSendEntity) {
		return mailSendDao.updateMailSendStatus(mailSendEntity);
	}

	@Override
	public int updateMailSendKs(MailSendEntity mailSendEntity) {
		return mailSendDao.updateMailSendKs(mailSendEntity);
	}

	@Override
	public int updateMailSendStatusAndKs(MailSendEntity mailSendEntity) {
		return mailSendDao.updateMailSendStatusAndKs(mailSendEntity);
	}

	@Override
	public List<MailSendEntity> getMailSendList(MailSendEntity mailSendEntity) {
		return mailSendDao.getMailSendList(mailSendEntity);
	}

	@Override
	public void insertDoSendMail(MailSendEntity mailSendEntity) {
		mailSendEntity.setMailSendKs(mailSendEntity.getMailSendKs() + 1);

		EmailSendTool sendEmail = new EmailSendTool(mailSmtpHost,mailSmtpPort,
				mailSmtpUsername, mailSmtpUserPass, mailSmtpAddr, mailSendEntity.getMailSendTo(),
				mailSendEntity.getMailSendSub(), mailSendEntity.getMailSendText(), mailUserPersonName, "", "");
		try {
			if("0".equals(mailSendMode) || mailSendEntity.getMailSendTo().indexOf("@keel.co.jp") >=0 ){
				sendEmail.send();
			}
			mailSendEntity.setMailSendStatus(Constants.MAIL_SEND_SATUS_1);
			updateMailSendStatusAndKs(mailSendEntity);
		} catch (Exception ex) {
			if (mailSendEntity.getMailSendKs()>= Constants.MAX_MAIL_SEND_KS) {
				mailSendEntity.setMailSendStatus(Constants.MAIL_SEND_SATUS_9);
				updateMailSendStatusAndKs(mailSendEntity);
			} else {
				updateMailSendKs(mailSendEntity);
			}
			return;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void insertMailSend(String ankenNo, String prevUpdateUserId, String userId) throws Exception {

		Set<String> sendTo = new HashSet<String>();

		AnkenDetailEntity ankenDetail = this.ankenDao.getAnkenDetail(ankenNo);

		//   (1)《案件受注情报》的登録者ＩＤ -〉 《ユーザマスタ》.ログインＩＤ
		UserEntity user = this.personalDao.getUserByCd(ankenDetail.getCreateId());
		if (user != null) {
			sendTo.add(user.getLoginId());
		}

		// (2) 《案件情報》的登録者の会社コード -〉 《会社マスタ》.会社EMAIL
		String seikyuKsCd = user.getCompanyCd();

		if(StringUtils.isNotBlank(seikyuKsCd)) {
			MemberEntity seikyuKsCom = this.memberDao.getMemberByCd(seikyuKsCd);
			if (seikyuKsCom != null && StringUtils.isNotBlank(seikyuKsCom.getCompanyEmail())) {
				sendTo.add(seikyuKsCom.getCompanyEmail());
			}
		}

		// (3)キャンセルの場合、受注ユーザと会社にメール
		if (Constants.ANKEN_STATUS_90.equals(ankenDetail.getStatus())) {
			UserEntity upuser = this.personalDao.getUserByCd(prevUpdateUserId);
			if (upuser != null) {
				sendTo.add(upuser.getLoginId());
			}
			//(3-2) 更新ユーザの会社メール
			if(upuser != null) {
				MemberEntity member = this.memberDao.getMemberByCd(upuser.getCompanyCd());
				if (member != null && StringUtils.isNotBlank(member.getCompanyEmail())) {
					sendTo.add(member.getCompanyEmail());
				}
			}
		}

		//(4) 更新ユーザのメール
		UserEntity upuser = this.personalDao.getUserByCd(userId);
		if (upuser != null) {
			sendTo.add(upuser.getLoginId());
		}


		// (4-2) 更新ユーザの会社コード -〉 《会社マスタ》.会社EMAIL
		if(upuser != null) {
			MemberEntity member = this.memberDao.getMemberByCd(upuser.getCompanyCd());
			if (member != null && StringUtils.isNotBlank(member.getCompanyEmail())) {
				sendTo.add(member.getCompanyEmail());
			}
		}

		// (5) 管理会社のメールアドレス
		if (StringUtils.isNotBlank(trailCompanyCd)) {
			MemberEntity member = this.memberDao.getMemberByCd(trailCompanyCd);
			if (member != null && StringUtils.isNotBlank(member.getCompanyEmail())) {
				sendTo.add(member.getCompanyEmail());
			}
		}

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(E) HH:mm", Locale.JAPANESE);
		for (String maddr : sendTo) {
			if (StringUtils.isBlank(maddr)) {
				continue;
			}
			MailSendEntity mailSendEntity = new MailSendEntity();
			mailSendEntity.setMailSendTo(maddr);
			mailSendEntity.setAnkenNo(ankenNo);
			mailSendEntity.setAnkenStatus(ankenDetail.getStatus());
			mailSendEntity.setCreateId(userId);
			mailSendEntity.setUpdateId(userId);

			String subject = MessageFormat.format(mailSubTemplate, new String[]{ankenNo, ankenDetail.getStatusNm().replace("済み", "")});

			mailSendEntity.setMailSendSub(subject);

			Map dataMap = new HashMap();
			dataMap.put("ankenNo", ankenNo);
//			dataMap.put("updateDt", sdf.format(updateDt));
			dataMap.put("updateDt", ankenDetail.getUpdateDt());
			dataMap.put("statusNm", ankenDetail.getStatusNm().replace("済み", ""));
//			dataMap.put("ankenId", ankenDetail.getAnkenId());
//			dataMap.put("ankenNo", ankenNo); // TODO

			String text = Freemarker.getTemplateString(Constants.FTL_PACKAGE, Constants.FTL_MAIL_FILE_NAME, dataMap);
			mailSendEntity.setMailSendText(text);
			this.insertMailSend(mailSendEntity);
		}
	}
}
