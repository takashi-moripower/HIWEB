package logistics.system.project.tuchi.dao;

import java.util.HashMap;
import java.util.List;

import logistics.system.project.tuchi.Entity.TuchiEntity;


public interface TuchiDao {
	public TuchiEntity getTuchiById( int tuchiId );
	public List<TuchiEntity> getTuchiByUser( String userId );
	public int save( TuchiEntity entity );

	public String[] getTruckOp( int tuchiId );
	public void delete( int tuchiId );

	public List<Integer> getMatchTuchi(String ankenId);

	public int addQueue( List<Integer> tuchiIds , String ankenId );
	public void removeQueue( int QueueId );
	public List<String> getDestEmails( int limit );

	public void increaseCount( List<Integer> tuchiIds );

	public List< HashMap<String,Object> > getQueues( String email );
	public void setQueueStatus( int id , int status );
	public HashMap<String,Object> getAnkenForTuchi( String ankenId );

	public void clearDaylyCount();

	public List<String> debug( String sql );
}
