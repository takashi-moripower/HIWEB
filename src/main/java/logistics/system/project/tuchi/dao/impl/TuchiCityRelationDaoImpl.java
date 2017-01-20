package logistics.system.project.tuchi.dao.impl;

import org.springframework.stereotype.Repository;

@Repository("tuchiCityRelationDao")
public class TuchiCityRelationDaoImpl extends RelationDaoImpl<Integer, String> {
	TuchiCityRelationDaoImpl(){
		super("t_city_tuchi" , "TUCHI_ID" , "CITY_CD" );
	}
}
