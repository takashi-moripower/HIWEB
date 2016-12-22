package logistics.system.project.common.service;

import java.util.List;

import logistics.system.project.common.Entity.AnkenOrderEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.exception.CustomException;
import logistics.system.project.common.form.AnkenTorokuForm;

public interface AnkenModifyService {

	public List<AnkenOrderEntity> insertAnken(AnkenTorokuForm ankenTorokuForm, UserEntity user);

	public int getCountNot10(String ankenId, String updateDt);

	public AnkenTorokuForm getAnkenEditable(String ankenNo);

	public void deleteAnken(String ankenId, String udpateDt) throws Exception ;

	public List<AnkenOrderEntity> updateAnken(AnkenTorokuForm ankenTorokuForm, UserEntity user) throws CustomException, Exception;

	public void deleteAnkenOther(String ankenId, String udpateDt) throws Exception;

	public List<AnkenOrderEntity> insertAnkenOther(AnkenTorokuForm ankenTorokuForm, UserEntity user);
}
