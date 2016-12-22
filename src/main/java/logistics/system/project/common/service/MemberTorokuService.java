package logistics.system.project.common.service;

import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.form.MemberTorokuForm;

public interface MemberTorokuService {
	
	public MemberTorokuForm getMember(String companyCd);
	
	public boolean insertMember(MemberTorokuForm form, String updateId) throws Exception;
	
	public boolean updateMember(MemberTorokuForm form, String updateId) throws Exception;
	
	public boolean isEmailExist(UserEntity user);

}
	