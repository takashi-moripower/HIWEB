package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.ContactRRKEntity;
import logistics.system.project.common.dao.ContactRRKDao;

import org.springframework.stereotype.Repository;

@Repository("contactRRKDao")
public class ContactRRKDaoImpl extends BaseDao implements ContactRRKDao {
	
	@SuppressWarnings("unchecked")
	@Override
//	@Cacheable(value="data", key="#root.methodName + #root.args[0] + #root.args[1]")
	public List<ContactRRKEntity> getContactRRKList(String companyCd, String addressKbn){
		
		Map<String, String> contactPara = new HashMap<String, String>();
		contactPara.put("companyCd", companyCd);
		contactPara.put("addressKbn", addressKbn);
		List<ContactRRKEntity> contactResultList = getSqlMapClientTemplate().queryForList("getContactByAddrKBN", contactPara);
		return contactResultList;
	}
	
	@Override
	public int deleteContactById(String rrkId, String companyCd) {
		Map<String, String> deleteContactPara = new HashMap<String, String>();
		deleteContactPara.put("companyCd", companyCd);
		deleteContactPara.put("rrkId", rrkId);
		return getSqlMapClientTemplate().delete("deleteContactById", deleteContactPara);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContactRRKEntity> isExistOrNotInConRRK(ContactRRKEntity conRRKEntity){
		
		Map<String, String> contactPara = new HashMap<String, String>();
		contactPara.put("companyCd", conRRKEntity.getCompanyCd());
		contactPara.put("addressKbn", conRRKEntity.getAddressKbn());
		contactPara.put("companyNm", conRRKEntity.getCompanyNm());
		contactPara.put("tantoNm", conRRKEntity.getTantoNm());
		contactPara.put("tantoTel", conRRKEntity.getTantoTel());
		List<ContactRRKEntity> contactResultList = getSqlMapClientTemplate().queryForList("getContactByAddrKBN", contactPara);
		return contactResultList;
	}
	
	@Override
	public void insertContactRRK(ContactRRKEntity conRRKEntity) {
		getSqlMapClientTemplate().insert("insertContactRRK", conRRKEntity);
	}
}
