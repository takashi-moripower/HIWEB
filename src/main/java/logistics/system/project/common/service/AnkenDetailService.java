package logistics.system.project.common.service;

import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.Entity.TruckEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.form.AnkenDetailForm;

public interface AnkenDetailService {
	public AnkenDetailForm getAnkenDetail(String ankenNo);

	public String getMaxSeqNo(String startSeq);

	public boolean updateAnkenStatus(AnkenDetailEntity ankenDetailEntity);

	public boolean updateSyaban(AnkenDetailEntity ankenDetailEntity, UserEntity userEntity);
	
	public AnkenDetailEntity getAnkenDetailByNo(String ankenNo);
	
	public TruckEntity getTruckByPK(String ankenId, String truckNo);
}
