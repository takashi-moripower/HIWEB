package logistics.system.project.common.dao.Impl;

import java.util.List;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.dao.MemberDao;
import logistics.system.project.common.parameterClass.MemberListParameter;

import org.springframework.stereotype.Repository;

@Repository("memberDao")
public class MemberDaoImpl extends BaseDao implements MemberDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberEntity> getMemberList(MemberListParameter params) {
		return getSqlMapClientTemplate().queryForList("getMemberList", params);
	}

	@Override
	public MemberEntity getMemberByCd(String cd) {
		return (MemberEntity) getSqlMapClientTemplate().queryForObject("getMemberByCd", cd);
	}

	@Override
	public int updateMember(MemberEntity member) {

		return getSqlMapClientTemplate().update("updateMember", member);
	}

	@Override
	public void insertMember(MemberEntity member) {
		getSqlMapClientTemplate().insert("insertMember", member);
	}
}
