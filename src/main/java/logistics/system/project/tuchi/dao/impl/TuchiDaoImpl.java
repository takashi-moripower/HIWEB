package logistics.system.project.tuchi.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.dao.TuchiDao;
import logistics.system.project.utility.Constants;

@Repository("tuchiDao")
public class TuchiDaoImpl extends BaseDao implements TuchiDao {

	@SuppressWarnings("unchecked")
	@Override
	public String[] getTruckOp(int tuchiCd) {
		List<Object> l = getSqlMapClientTemplate().queryForList("getTruckOpByTuchiCd", tuchiCd);

		String[] s = l.toArray(new String[l.size()]);

		return s;
	}

	@Override
	public TuchiEntity getTuchiById(int tuchiId) {
		return (TuchiEntity) getSqlMapClientTemplate().queryForObject("getTuchiByCd", tuchiId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TuchiEntity> getTuchiByUser(String userId) {
		return getSqlMapClientTemplate().queryForList("getTuchiByUser", userId);
	}

	@Override
	public int save(TuchiEntity entity) {
		if (entity.getTuchiId() == 0) {
			int newId = (int) getSqlMapClientTemplate().queryForObject("getNextTuchiId");
			entity.setTuchiId(newId);

			getSqlMapClientTemplate().insert("insertTuchi", entity);

			return 1;
		}

		getSqlMapClientTemplate().update("updateTuchi", entity);

		return 0;
	}

	@Override
	public int saveTruckOp(int tuchiId, String[] newValue) {

		String[] oldValue = getTruckOp(tuchiId);

		List<String> newValueList = Arrays.asList(newValue);
		List<String> oldValueList = Arrays.asList(oldValue);

		List<String> addList = new ArrayList<String>();
		List<String> delList = new ArrayList<String>();

		for (TruckOpEntity op : Constants.MAST_TRUCKOP_LIST) {
			int flag = 0;
			String cd = op.getOpCd();

			if (newValueList.contains(cd)) {
				flag |= 1;
			}
			if (oldValueList.contains(cd)) {
				flag |= 2;
			}

			switch (flag) {
			case 1:
				addList.add(cd);
				break;

			case 2:
				delList.add(cd);
				break;
			}
		}

		linkTruckOp( tuchiId , addList );
		unlinkTruckOp( tuchiId , delList );

		return 0;
	}

	protected void linkTruckOp(int tuchiId, List<String> ops) {

		if( ops.isEmpty() ){
			return;
		}

		for (String op : ops) {
			HashMap<String, Object> param = new HashMap<>();
			param.put("tuchiId", tuchiId);
			param.put("opCd", op);

			getSqlMapClientTemplate().insert("linkTruckOpTuchi", param);
		}
	}

	protected void unlinkTruckOp(int tuchiId, List<String> ops) {

		if( ops.isEmpty() ){
			return;
		}

		String op_list = "";

		for (String op : ops) {
			if (!op_list.isEmpty()) {
				op_list += ",";
			}
			op_list += op;
		}
		HashMap<String, Object> param = new HashMap<>();

		param.put("tuchiId", tuchiId);
		param.put("opList", op_list);

		getSqlMapClientTemplate().delete("unlinkTruckOpTuchi", param);
	}

}
