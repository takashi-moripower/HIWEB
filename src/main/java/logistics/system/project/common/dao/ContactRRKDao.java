package logistics.system.project.common.dao;

import java.util.List;

import logistics.system.project.common.Entity.ContactRRKEntity;

public interface ContactRRKDao {
	public List<ContactRRKEntity> getContactRRKList(String companyCd, String addressKbn);

	public int deleteContactById(String rrkId, String companyCd);

	public List<ContactRRKEntity> isExistOrNotInConRRK(ContactRRKEntity contactRRKEntity);
	
	public void insertContactRRK(ContactRRKEntity conRRKEntity);

}
