package logistics.system.project.common.service;

import java.util.List;

import logistics.system.project.common.Entity.AnkenListEntity;
import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.parameterClass.AnkenListParameter;

public interface AnkenListSearchService {
	
	public MemberEntity getMember(String companyCd);
	
	public int getAnkenListCount(AnkenListParameter ankenListParameter);
	
	public List<AnkenListEntity> getAnkenList(AnkenListParameter ankenListParameter);
	
	public List<TruckOpEntity> getTruckOpList();
	
	public List<SyasyuEntity> getSyasyuList();
}
