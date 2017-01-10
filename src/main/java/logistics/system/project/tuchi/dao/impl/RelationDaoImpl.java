package logistics.system.project.tuchi.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logistics.system.project.base.dao.BaseDao;
import logistics.system.project.tuchi.dao.RelationDao;

public class RelationDaoImpl<KEY, VALUE> extends BaseDao implements RelationDao<KEY, VALUE> {

	protected String tableName;
	protected String keyColumn;
	protected String valueColumn;

	RelationDaoImpl(String tableName, String keyColumn, String valueColumn) {
		this.tableName = tableName;
		this.keyColumn = keyColumn;
		this.valueColumn = valueColumn;
	}

	@Override
	public List<VALUE> getValues(KEY key) {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("table", this.tableName);
		param.put("key1", this.keyColumn);
		param.put("key2", this.valueColumn);
		param.put("key1Value", key);

		@SuppressWarnings("unchecked")
		List<VALUE> result = getSqlMapClientTemplate().queryForList("getRelation", param);

		return result;
	}



	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void save(KEY key, List<VALUE> newList) {

		List<VALUE> currentList = getValues(key);
		List<VALUE> addList = new ArrayList<VALUE>();
		List<VALUE> delList = new ArrayList<VALUE>();

		for( VALUE newValue : newList ){
			if( !currentList.contains(newValue) ){
				addList.add(newValue);
			}
		}

		for( VALUE currentValue : currentList ){
			if( !newList.contains(currentValue)){
				delList.add(currentValue);
			}
		}

		link( key , addList );

		unLink( key , delList );

	}

	public void link( KEY key , List<VALUE> addList ){
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("table", this.tableName);
		param.put("key1", this.keyColumn);
		param.put("key2", this.valueColumn);
		param.put("key1Value", key);
		for( VALUE addValue : addList ){
			param.put("key2Value",addValue);

			getSqlMapClientTemplate().insert("link", param );
		}
	}

	public void unLink( KEY key , List<VALUE> delList ){

		if(delList.isEmpty()){
			return;
		}


		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("table", this.tableName);
		param.put("key1", this.keyColumn);
		param.put("key2", this.valueColumn);
		param.put("key1Value", key);

		String values = "";
		for (VALUE op : delList) {
			if( !values.isEmpty() ){
				values += ",";
			}

			values += op;
		}
		param.put("key2Values", values);

		getSqlMapClientTemplate().insert("unLink", param );
	}
}
