package logistics.system.project.common.service.Impl;

import logistics.system.project.common.Entity.PostCodeEntity;
import logistics.system.project.common.dao.PostCodeDao;
import logistics.system.project.common.service.PostCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("postCodeService")
public class PostCodeServiceImpl implements PostCodeService{
	
	@Override
	public PostCodeEntity getPostCodeInfo(String postCode) {
		return postCodeDao.getPostCodeInfo(postCode);
	}
	
	@Autowired
	@Qualifier("postCodeDao")
	private PostCodeDao postCodeDao;

}
