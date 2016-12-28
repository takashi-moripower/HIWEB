package logistics.system.project.tuchi.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.dao.TuchiDao;
import logistics.system.project.tuchi.service.TuchiService;

@Service("tuchiService")
public class TuchiServiceImpl implements TuchiService{

	@Autowired
	@Qualifier("tuchiDao")
	private TuchiDao tuchiDao;

	@Override
	public TuchiEntity getTuchiByCd(int tuchiCd) {
		return tuchiDao.getTuchiByCd(tuchiCd);
	}

	@Override
	public List<TuchiEntity> getTuchiByUser(String userId) {
		return tuchiDao.getTuchiByUser(userId);
	}

	@Override
	public int save(TuchiEntity entity) {
		return tuchiDao.save( entity );
	}
}
