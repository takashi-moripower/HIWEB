package logistics.system.project.common.service.Impl;

import java.util.List;

import logistics.system.project.common.Entity.AddressRRKEntity;
import logistics.system.project.common.dao.AddressRRKDao;
import logistics.system.project.common.service.AddressRRKService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("adressRRKService")
public class AddressRRKServiceImpl implements AddressRRKService{
	
	public List<AddressRRKEntity> getAddressRRKList(String companyCd, String addressKbn) {
			List<AddressRRKEntity> addrRRKList = dao.getAddressRRKList(companyCd, addressKbn);
			return addrRRKList;
	}
	
	public int deleteAddrById(String rrkId, String companyCd) {
		return dao.deleteAddrById(rrkId, companyCd);
	}
	
	@Autowired
	@Qualifier("addressRRKDao")
	private AddressRRKDao dao;

}
