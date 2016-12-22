package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.parameterClass.MemberListParameter;

public interface MemberDao {
	
	public List<MemberEntity> getMemberList(MemberListParameter params);
	
	public MemberEntity getMemberByCd(String cd);
	
	public int updateMember(MemberEntity member);
	
	public void insertMember(MemberEntity member);
}
