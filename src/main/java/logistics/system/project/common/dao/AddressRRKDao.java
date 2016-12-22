package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.AddressRRKEntity;

public interface AddressRRKDao {
	public List<AddressRRKEntity> getAddressRRKList(String companyCd, String addressKbn);

	public int deleteAddrById(String rrkId, String companyCd);
	
	public List<AddressRRKEntity> isExistOrNotInAddRRK(AddressRRKEntity addRRKEntity);

	public void insertAddressRRK(AddressRRKEntity addRRKEntity);
}
