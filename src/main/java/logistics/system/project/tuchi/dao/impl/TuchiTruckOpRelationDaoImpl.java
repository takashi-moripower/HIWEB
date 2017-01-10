package logistics.system.project.tuchi.dao.impl;

import org.springframework.stereotype.Repository;

@Repository("tuchiTruckOpRelationDao")
public class TuchiTruckOpRelationDaoImpl extends RelationDaoImpl<Integer, String> {
	TuchiTruckOpRelationDaoImpl(){
		super("t_truck_op_tuchi" , "TUCHI_ID" , "OP_CD" );
	}
}
