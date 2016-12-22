package logistics.system.project.common.service.Impl;

import java.util.List;

import logistics.system.project.common.Entity.ContactRRKEntity;
import logistics.system.project.common.dao.ContactRRKDao;
import logistics.system.project.common.service.ContactRRKService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("contactRRKService")
public class ContactRRKServiceImpl implements ContactRRKService{
	
	public List<ContactRRKEntity> getContactRRKList(String companyCd, String addressKbn) {
			List<ContactRRKEntity> contactRRKList = dao.getContactRRKList(companyCd, addressKbn);
			return contactRRKList;
	}
	
	public int deleteContactById(String rrkId, String companyCd) {
		return dao.deleteContactById(rrkId, companyCd);
	}
	
	@Autowired
	@Qualifier("contactRRKDao")
	private ContactRRKDao dao;

}
