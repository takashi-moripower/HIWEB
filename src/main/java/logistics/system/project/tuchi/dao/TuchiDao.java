package logistics.system.project.tuchi.dao;

import java.util.List;

import logistics.system.project.tuchi.Entity.TuchiEntity;


public interface TuchiDao {
	public TuchiEntity getTuchiByCd( int tuchiCd );
	public List<TuchiEntity> getTuchiByUser( String userId );
	public int save( TuchiEntity entity );
}
