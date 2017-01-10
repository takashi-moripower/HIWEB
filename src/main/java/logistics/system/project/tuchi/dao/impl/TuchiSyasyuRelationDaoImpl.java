package logistics.system.project.tuchi.dao.impl;

import org.springframework.stereotype.Repository;

@Repository("tuchiSyasyuRelationDao")
public class TuchiSyasyuRelationDaoImpl extends RelationDaoImpl<Integer, String> {
	TuchiSyasyuRelationDaoImpl(){
		super("t_syasyu_tuchi" , "TUCHI_ID" , "SYASYU_CD" );
	}
}
