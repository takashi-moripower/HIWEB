package logistics.system.project.common.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.AddressRRKEntity;
import logistics.system.project.common.dao.AddressRRKDao;

import org.springframework.stereotype.Repository;

@Repository("addressRRKDao")
public class AddressRRKDaoImpl extends BaseDao implements AddressRRKDao {
	
	@SuppressWarnings("unchecked")
	@Override
//	@Cacheable(value="data", key="#root.methodName")
	public List<AddressRRKEntity> getAddressRRKList(String companyCd, String addressKbn){
		
		Map<String, String> addressPara = new HashMap<String, String>();
		addressPara.put("companyCd", companyCd);
		addressPara.put("addressKbn", addressKbn);
		List<AddressRRKEntity> addrResultList = getSqlMapClientTemplate().queryForList("getAddrByAddrKBN", addressPara);
		return addrResultList;
	}
	
	@Override
	public int deleteAddrById(String rrkId, String companyCd) {	
		Map<String, String> deleteAddressPara = new HashMap<String, String>();
		deleteAddressPara.put("companyCd", companyCd);
		deleteAddressPara.put("rrkId", rrkId);
		return getSqlMapClientTemplate().delete("deleteAddrById", deleteAddressPara);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AddressRRKEntity> isExistOrNotInAddRRK(AddressRRKEntity addRRKEntity){
		
		Map<String, String> addressPara = new HashMap<String, String>();
		addressPara.put("companyCd", addRRKEntity.getCompanyCd());
		addressPara.put("addressKbn", addRRKEntity.getAddressKbn());
		addressPara.put("addrNm", addRRKEntity.getAddrNm());
		addressPara.put("postCode", addRRKEntity.getPostCode());
		addressPara.put("prefNm", addRRKEntity.getPrefNm());
		addressPara.put("cityNm", addRRKEntity.getCityNm());
		addressPara.put("addrOther", addRRKEntity.getAddrOther());
		List<AddressRRKEntity> addressResultList = getSqlMapClientTemplate().queryForList("getAddrByAddrKBN", addressPara);
		return addressResultList;
	}
	
	@Override
	public void insertAddressRRK(AddressRRKEntity addRRKEntity) {
		getSqlMapClientTemplate().insert("insertAdressRRK", addRRKEntity);
	}
}
