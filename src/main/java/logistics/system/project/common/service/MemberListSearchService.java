package logistics.system.project.common.service;

import java.util.List;

import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.parameterClass.MemberListParameter;

public interface MemberListSearchService {
	public List<MemberEntity> getMemberList(MemberListParameter params);
//	public Integer getMemberCount();

	MemberEntity getMemberByCd(String companyCd);
}
