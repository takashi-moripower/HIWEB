package logistics.system.project.common.service.Impl;

import java.util.List;

import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.dao.MemberDao;
import logistics.system.project.common.parameterClass.MemberListParameter;
import logistics.system.project.common.service.MemberListSearchService;
import logistics.system.project.utility.ComUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("memberListSearchService")
public class MemberListSearchServiceImpl implements MemberListSearchService {

	@Autowired
	@Qualifier("memberDao")
	private MemberDao memberDao;

	@Override
	public List<MemberEntity> getMemberList(MemberListParameter params) {
		List<MemberEntity> list = memberDao.getMemberList(params);
		if (list != null) {
			for (MemberEntity member : list) {
				member.setKeiyakuDayDis(ComUtils.editDate(member.getKeiyakuDay(), "yyyyMMdd",
						"yyyy/MM/dd (E)"));
				member.setKeiyakuKgDayDis(ComUtils.editDate(member.getKeiyakuKgDay(), "yyyyMMdd",
						"yyyy/MM/dd (E)"));
				member.setPostCode(ComUtils.formatPostCode(member.getPostCode()));
			}
		}
		return list;
	}

	@Override
	public MemberEntity getMemberByCd(String companyCd) {
		MemberEntity member = memberDao.getMemberByCd(companyCd);
		if (member != null) {
			member.setKeiyakuDayDis(ComUtils.editDate(member.getKeiyakuDay(), "yyyyMMdd",
					"yyyy/MM/dd (E)"));
			member.setKeiyakuKgDayDis(ComUtils.editDate(member.getKeiyakuKgDay(), "yyyyMMdd",
					"yyyy/MM/dd (E)"));
			member.setPostCode(ComUtils.formatPostCode(member.getPostCode()));
		}
		return member;
	}
}
