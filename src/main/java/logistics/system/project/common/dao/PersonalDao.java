package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.UserEntity;

public interface PersonalDao {

	UserEntity getUserByCd(String userCd);

	public UserEntity getUserLoginInfoDAO(String loginId, String loginPassword);

	public List<UserEntity> getUserByCompanyCd(String companyCd);

	public int getCountByEmail(UserEntity user);

	public int deleteUser(UserEntity user);

	public int insertUsers(List<UserEntity> userList) throws Exception;

	public int kosinUser(List<UserEntity> userList) throws Exception;

	int updatePassword(UserEntity user);

	public String getUserNmById( String UserId );
}
