package logistics.system.project.tuchi.service;

import java.util.List;

import logistics.system.project.tuchi.Entity.TuchiEntity;

public interface TuchiService {
	public  TuchiEntity getTuchiByCd(int tuchiCd);
	public List<TuchiEntity> getTuchiByUser(String userId);
}
