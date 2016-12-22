package logistics.system.project.common.service;

import java.util.List;

import logistics.system.project.common.Entity.ContactRRKEntity;

public interface ContactRRKService {
	public List<ContactRRKEntity> getContactRRKList(String companyCd, String addressKbn);
	
	public int deleteContactById(String rrkId, String companyCd);
}
