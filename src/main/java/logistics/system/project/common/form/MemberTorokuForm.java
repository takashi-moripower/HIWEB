package logistics.system.project.common.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;

import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.Entity.UserEntity;

public class MemberTorokuForm {
	@Valid
	private MemberEntity member;
	
	// 担当者情報
	@Valid
	private List<UserEntity> userList;

	// 都道府県
	private List<PrefEntity> prefList;

	public MemberTorokuForm(){
		userList = new ArrayList<UserEntity>();
		for (int i = 0; i < 20; i++) {
			String s = String.valueOf(i + 1);
			userList.add(new UserEntity(StringUtils.leftPad(s, 2, '0')));
		}
	}
	
	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}

	public List<PrefEntity> getPrefList() {
		return prefList;
	}

	public void setPrefList(List<PrefEntity> prefList) {
		this.prefList = prefList;
	}

}
