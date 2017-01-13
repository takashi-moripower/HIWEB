package logistics.system.project.common.service.Impl;

import java.util.Iterator;
import java.util.List;

import logistics.system.project.common.Entity.AnkenListEntity;
import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.common.dao.MemberDao;
import logistics.system.project.common.dao.SyasyuDao;
import logistics.system.project.common.dao.TruckOpDao;
import logistics.system.project.common.parameterClass.AnkenListParameter;
import logistics.system.project.common.service.AnkenListSearchService;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service("ankenListSearchService")
public class AnkenListSearchServiceImpl implements AnkenListSearchService {

	@Autowired
	@Qualifier("ankenDao")
	private AnkenDao dao;

	@Autowired
	@Qualifier("memberDao")
	private MemberDao memberDao;

	@Autowired
	@Qualifier("truckOpDao")
	private TruckOpDao truckOpDao;

	@Autowired
	@Qualifier("syasyuDao")
	private SyasyuDao syasyuDao;

	@Value("#{configProperties['AnkenCountsPerPage']}")
	private String ankenCountsPerPage;

	@Override
	public MemberEntity getMember(String companyCd) {

		return memberDao.getMemberByCd(companyCd);
	}

	@Override
	public int getAnkenListCount(AnkenListParameter ankenListParameter) {

		return dao.getAnkenListCount(ankenListParameter);
	}

	@Override
	public List<AnkenListEntity> getAnkenList(AnkenListParameter ankenListParameter) {


		List<AnkenListEntity> ankenList = dao.getAnkenList(ankenListParameter);
		this.editAnkenList(ankenList, Constants.getTruckOpList(), ankenListParameter);

		return ankenList;
	}

	private void editAnkenList(List<AnkenListEntity> ankenList, List<TruckOpEntity> truckOpList,
			AnkenListParameter ankenListParameter) {

		if (ankenList != null) {
			Iterator<AnkenListEntity> iterator = ankenList.iterator();
			// SQL に検索条件追加した。
//			String[] options = ankenListParameter.getOpList() != null ? ankenListParameter
//					.getOpList().split(",") : null;
			while (iterator.hasNext()) {
				AnkenListEntity anken = iterator.next();
//
//				if (options != null) {
//					boolean deleteFlag = true;
//					for (String option : options) {
//						if (anken.getOpList().contains(option)) {
//							deleteFlag = false;
//							break;
//						}
//					}
//					if (deleteFlag) {
//						iterator.remove();
//						continue;
//					}
//				}

				if (!StringUtils.isEmpty(anken.getSyukaDay())) {
					anken.setSyukaDay(ComUtils.editDate(anken.getSyukaDay(), "yyyyMMddHHmm",
							"yyyy/MM/dd(E) HH:mm"));
				}
				if (!StringUtils.isEmpty(anken.getNohinDay())) {
					anken.setNohinDay(ComUtils.editDate(anken.getNohinDay(), "yyyyMMddHHmm",
							"yyyy/MM/dd(E) HH:mm"));
				}
				if (!StringUtils.isEmpty(anken.getOpList())) {
					anken.setOpList(ComUtils.editOpList(anken.getOpList(), truckOpList));
				}
			}
		}

	}

	@Override
	public List<TruckOpEntity> getTruckOpList() {
		return truckOpDao.getAllOption();
	}

	@Override
	public List<SyasyuEntity> getSyasyuList() {
		return syasyuDao.getAllSyasyu();
	}

}
