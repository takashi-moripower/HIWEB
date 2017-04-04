package logistics.system.project.tuchi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import logistics.system.project.common.dao.AnkenDao;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.dao.RelationDao;
import logistics.system.project.tuchi.dao.TuchiDao;
import logistics.system.project.tuchi.service.TuchiService;

@Service("tuchiService")
public class TuchiServiceImpl implements TuchiService {

	@Autowired
	@Qualifier("tuchiDao")
	private TuchiDao tuchiDao;

	@Autowired
	@Qualifier("ankenDao")
	private AnkenDao ankenDao;

	@Autowired
	@Qualifier("tuchiTruckOpRelationDao")
	private RelationDao<Integer, String> tuchiTruckOp;

	@Autowired
	@Qualifier("tuchiSyasyuRelationDao")
	private RelationDao<Integer, String> tuchiSyasyu;

	@Autowired
	@Qualifier("tuchiCityRelationDao")
	private RelationDao<Integer, String> tuchiCity;

	@Override
	public TuchiEntity getTuchiById(int tuchiId, boolean link) {
		TuchiEntity entity = tuchiDao.getTuchiById(tuchiId);

		if (link) {
			entity.setTruckOp(tuchiTruckOp.getValues(tuchiId));
			entity.setSyasyu(tuchiSyasyu.getValues(tuchiId));
			entity.setCity(tuchiCity.getValues(tuchiId));
		}

		return entity;
	}

	@Override
	public List<TuchiEntity> getTuchiByUser(String userId) {
		return tuchiDao.getTuchiByUser(userId);
	}

	@Override
	public void save(TuchiEntity entity) {
		tuchiDao.save(entity);

		tuchiTruckOp.save(entity.getTuchiId(), entity.getTruckOp());
		tuchiSyasyu.save(entity.getTuchiId(), entity.getSyasyu());
		tuchiCity.save(entity.getTuchiId(), entity.getCity());
	}

	@Override
	public void delete( int tuchiId){
		tuchiDao.delete( tuchiId );
	}

	@Override
	public void checkMatching(String ankenId) {
		System.out.println("anken:"+  ankenId);
		List<Integer> l = tuchiDao.getMatchTuchi(ankenId);
		if( l == null ){
			System.out.println("EMPTY");
		}

		for( Integer tuchiId : l ){
			System.out.println(tuchiId);
		}
	}
}
