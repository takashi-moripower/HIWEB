package logistics.system.project.common.service.Impl;

import java.util.List;

import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.Entity.UserEntity.UpdateType;
import logistics.system.project.common.dao.MemberDao;
import logistics.system.project.common.dao.PersonalDao;
import logistics.system.project.common.form.MemberTorokuForm;
import logistics.system.project.common.service.MemberTorokuService;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.MD5Util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("memberTorokuService")
public class MemberTorokuServiceImpl implements MemberTorokuService {

	@Autowired
	@Qualifier("memberDao")
	private MemberDao memberDao;

	@Autowired
	@Qualifier("personalDao")
	private PersonalDao personalDao;

	@Override
	public MemberTorokuForm getMember(String companyCd) {
		MemberTorokuForm memberTorokuForm = new MemberTorokuForm();
		MemberEntity member = memberDao.getMemberByCd(companyCd);
		if (member != null) {
			member.setKeiyakuDayDis(ComUtils.editDate(member.getKeiyakuDay(), "yyyyMMdd",
					"yyyy/MM/dd (E)"));
			member.setKeiyakuKgDayDis(ComUtils.editDate(member.getKeiyakuKgDay(), "yyyyMMdd",
					"yyyy/MM/dd (E)"));
			member.setPostCode(ComUtils.formatPostCode(member.getPostCode()));
		}
		memberTorokuForm.setMember(member);
		List<UserEntity> userList = personalDao.getUserByCompanyCd(companyCd);

		int size = 0;
		if (memberTorokuForm.getUserList() != null) {
			size = memberTorokuForm.getUserList().size();
			for (UserEntity user : userList) {
				for (int i = 0; i < size; i++) {
					UserEntity user1 = memberTorokuForm.getUserList().get(i);
					if (StringUtils.right(user.getUserId(), 2).equals(user1.getIndex())) {
						user.setIndex(user1.getIndex());
						memberTorokuForm.getUserList().set(i, user);
						break;
					}
				}
			}
		}

		return memberTorokuForm;
	}

	@Override
	public boolean insertMember(MemberTorokuForm form, String updateId) throws Exception {
		boolean result = false;
		MemberEntity member = form.getMember();
		if (member.getOfficeCd().length() == 1) {
			member.setOfficeCd("0" + member.getOfficeCd());
		}
		member.setCompanyCd(member.getCustomCd() + member.getOfficeCd());
		member.setKeiyakuDay(ComUtils.editDate(member.getKeiyakuDayDis(),
				"yyyy/MM/dd (E)", "yyyyMMdd"));
		member.setKeiyakuKgDay(ComUtils.editDate(member.getKeiyakuKgDayDis(),
				"yyyy/MM/dd (E)", "yyyyMMdd"));
		if (StringUtils.isNotBlank(member.getPostCode())) {
			member.setPostCode(member.getPostCode().replaceAll("-", ""));
		}
		member.setCreateId(updateId);
		member.setUpdateId(updateId);
		memberDao.insertMember(member);
		
		for (UserEntity user : form.getUserList()) {
			if (!StringUtils.isBlank(user.getUsername())) {
				user.setCompanyCd(form.getMember().getCompanyCd());
				// 新規場合
				user.setUserId(user.getCompanyCd() + "00" +user.getIndex());
				user.setLoginPassword(MD5Util.EncoderByMd5(user.getUserId()));
				user.setCreateId(updateId);
				user.setUpdateId(updateId);
			}
		}
		personalDao.insertUsers(form.getUserList());
		result =  true;

		return result;
	}

	@Override
	public boolean updateMember(MemberTorokuForm form, String updateId) throws Exception {
		MemberEntity member = form.getMember();
		member.setUpdateId(updateId);
		member.setKeiyakuDay(ComUtils.editDate(member.getKeiyakuDayDis(),
				"yyyy/MM/dd (E)", "yyyyMMdd"));
		member.setKeiyakuKgDay(ComUtils.editDate(member.getKeiyakuKgDayDis(),
				"yyyy/MM/dd (E)", "yyyyMMdd"));
		if (StringUtils.isNotBlank(member.getPostCode())) {
			member.setPostCode(member.getPostCode().replaceAll("-", ""));
		}
		if (memberDao.updateMember(member) == 0) {
			throw new Exception();
		};

		int kosinKensu = 0; 
		for (UserEntity user : form.getUserList()) {
			if (!StringUtils.isBlank(user.getUsername())) {
				user.setCompanyCd(form.getMember().getCompanyCd());
				if (!StringUtils.isBlank(user.getUserId())) {
					user.setType(UpdateType.UPDATE);
					kosinKensu++;
				} else {
					// 新規場合
					user.setUserId(user.getCompanyCd() + "00" +user.getIndex());
					user.setLoginPassword(MD5Util.EncoderByMd5(user.getUserId()));
					user.setType(UpdateType.INSERT);
					kosinKensu++;
				}
				user.setUpdateId(updateId);
			} else {
				if (!StringUtils.isBlank(user.getUserId())) {
					user.setType(UpdateType.DELETE);
					kosinKensu++;
				}
			}
		}
		if (personalDao.kosinUser(form.getUserList()) != kosinKensu) {
			throw new Exception();
		}

		return true;
	}

	@Override
	public boolean isEmailExist(UserEntity user) {
		return personalDao.getCountByEmail(user) > 0;
	}

}
