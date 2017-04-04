package logistics.system.project.tuchi.dao;

import java.util.List;

import logistics.system.project.tuchi.Entity.TuchiEntity;


public interface TuchiDao {
	public TuchiEntity getTuchiById( int tuchiId );
	public List<TuchiEntity> getTuchiByUser( String userId );
	public int save( TuchiEntity entity );

	public String[] getTruckOp( int tuchiId );
	public void delete( int tuchiId );

	public List<Integer> getMatchTuchi( String ankenId );
}
