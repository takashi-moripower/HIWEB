package logistics.system.project.common.service;

import java.util.List;

import logistics.system.project.common.Entity.AddressRRKEntity;

public interface AddressRRKService {
	public List<AddressRRKEntity> getAddressRRKList(String companyCd, String addressKbn);
	
	public int deleteAddrById(String rrkId, String companyCd);
}
