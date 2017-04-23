package logistics.system.project.tuchi.dao.impl;

import org.springframework.stereotype.Repository;

@Repository("tuchiPrefRelationDao")
public class TuchiPrefRelationDaoImpl extends RelationDaoImpl<Integer, String> {
	TuchiPrefRelationDaoImpl(){
		super("t_pref_tuchi" , "TUCHI_ID" , "PREF_CD" );
	}
}
