package logistics.system.project.common.service;

import logistics.system.project.common.Entity.UserEntity;

public interface PersonalService {
	
	public UserEntity getUserLoginInfoService(String loginId, String loginPassword);

	UserEntity getUserByCd(String userCd);

	int updatePassword(UserEntity user);

}
