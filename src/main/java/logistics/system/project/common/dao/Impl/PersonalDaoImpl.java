package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.Entity.UserEntity.UpdateType;
import logistics.system.project.common.dao.PersonalDao;

@Repository("personalDao")
public class PersonalDaoImpl extends BaseDao implements PersonalDao {

	@Override
	public UserEntity getUserByCd(String userCd) {
		return (UserEntity) getSqlMapClientTemplate().queryForObject("getUserByCd", userCd);
	}

	@Override
	public UserEntity getUserLoginInfoDAO(String loginId, String loginPassword) {
		// UserEntity userEntity = new UserEntity();
		// userEntity.setLoginId(loginId);
		// userEntity.setLoginPassword(loginPassword);
		// userEntity.setUsername("荷主 一部");

		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("loginId", loginId);
		parameter.put("loginPassword", loginPassword);
		UserEntity userEntity = (UserEntity) getSqlMapClientTemplate().queryForObject(
				"getUserByLoginId", parameter);


		return userEntity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getUserByCompanyCd(String companyCd) {

		return (List<UserEntity>) getSqlMapClientTemplate().queryForList("getUserByCompanyCd",
				companyCd);
	}

	@Override
	public int kosinUser(List<UserEntity> userList) {
		int result = 0;

		for (UserEntity user : userList) {

			if(user.getType() == null){
				continue;
			}

			if (user.getType().equals(UpdateType.INSERT)) {
				getSqlMapClientTemplate().insert("insertUser", user);
				result++;
			} else if (user.getType().equals(UpdateType.UPDATE)) {
				result += getSqlMapClientTemplate().update("updateUser", user);
			} else if (user.getType().equals(UpdateType.DELETE)) {
				result += getSqlMapClientTemplate().delete("deleteUser", user);
			}
		}

		return result;
	}

	@Override
	public int insertUsers(List<UserEntity> userList) throws Exception {
		int result = 0;

		for (UserEntity user : userList) {
			if (!StringUtils.isBlank(user.getUsername())) {
				getSqlMapClientTemplate().insert("insertUser", user);
				result++;
			}
		}

		return result;
	}

	@Override
	public int getCountByEmail(UserEntity user) {

		return (int) getSqlMapClientTemplate().queryForObject("getCountByEmail", user);
	}

	@Override
	public int deleteUser(UserEntity user) {

		return (int) getSqlMapClientTemplate().delete("deleteUser", user);
	}

	@Override
	public int updatePassword(UserEntity user) {
		return (int) getSqlMapClientTemplate().update("updatePassword", user);
	}

	@Override
	public String getUserNmById(String UserId){
		return (String)getSqlMapClientTemplate().queryForObject("getUserNmByCd",UserId);
	}

}
