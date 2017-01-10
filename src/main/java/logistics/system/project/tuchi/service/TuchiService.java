package logistics.system.project.tuchi.service;

import java.util.List;

import logistics.system.project.tuchi.Entity.TuchiEntity;

public interface TuchiService {
	public  TuchiEntity getTuchiById(int tuchiId ,boolean link );
	public List<TuchiEntity> getTuchiByUser(String userId);

	public void save( TuchiEntity entity );
}
