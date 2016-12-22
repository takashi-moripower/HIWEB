package logistics.system.project.common.service;

import java.util.List;

import logistics.system.project.common.Entity.SeikyuAnkenEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.parameterClass.SeikyuAnkenListParameter;

public interface SeikyuAnkenListService {

	public List<SeikyuAnkenEntity> getSeikyuAnkenList(
			SeikyuAnkenListParameter seikyuAnkenListParameter, UserEntity user);

	public boolean updateRyokin(List<SeikyuAnkenEntity> seikyuAnkenList, String sysDatetime, UserEntity user) throws Exception;

}
