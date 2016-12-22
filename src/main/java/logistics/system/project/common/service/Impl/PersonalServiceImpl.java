package logistics.system.project.common.service.Impl;

import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.dao.PersonalDao;
import logistics.system.project.common.service.PersonalService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("personalService")
public class PersonalServiceImpl implements PersonalService {

	@Value("#{configProperties['DefaultDISPDL']}")
	private String defaultDISPDL;
	
	@Override
	public UserEntity getUserLoginInfoService(String loginId, String loginPassword) {
		UserEntity userEntity = this.dao.getUserLoginInfoDAO(loginId, loginPassword);

		if (userEntity != null && StringUtils.isBlank(userEntity.getDispDl())) {
			userEntity.setDispDl(defaultDISPDL);
		}
		return userEntity;
	}
	
	@Override
	public UserEntity getUserByCd(String userCd) {
		return this.dao.getUserByCd(userCd);
	}

	@Override
	public int updatePassword(UserEntity user) {
		return this.dao.updatePassword(user);
	}
	
	@Autowired
	@Qualifier("personalDao")
	private PersonalDao dao;

}
