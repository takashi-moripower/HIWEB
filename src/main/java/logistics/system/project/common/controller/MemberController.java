package logistics.system.project.common.controller;

import java.util.HashMap;

import javax.validation.Valid;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.form.ChangePasswordForm;
import logistics.system.project.common.service.MemberListSearchService;
import logistics.system.project.common.service.PersonalService;
import logistics.system.project.utility.Constants;
import logistics.system.project.utility.MD5Util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController extends BaseController {
	@RequestMapping(value = "changePassword")
	public ModelAndView changePassword( String oldPassword, String password) {
		HashMap<String, Object> results = new HashMap<String, Object>();
		
		this.setHeader(new String[] {
				Constants.TRAIL_MEMBER_PASSWORD_TABTITLE,
				Constants.TRAIL_MEMBER_PASSWORD_PAGETITLE }, results);
		UserEntity theUser = personalService.getUserByCd(userSession.getUserId());
		
		ChangePasswordForm changePasswordForm = new ChangePasswordForm();
		changePasswordForm.setUpdateDt(theUser.getUpdateDt());
		
		results.put("changePasswordForm", changePasswordForm);
		
		return new ModelAndView("change_password", results);
	}
	
	@RequestMapping(value = "savePassword")
	public ModelAndView savePassword(
			@ModelAttribute("changePasswordForm") @Valid ChangePasswordForm changePasswordForm,
			BindingResult ankenerrors) {
		HashMap<String, Object> results = new HashMap<String, Object>();
		
		if (StringUtils.isNotBlank(changePasswordForm.getPassword())
				&& StringUtils.isNotBlank(changePasswordForm
						.getConfirmPassword())
				&& !changePasswordForm.getPassword().equals(
						changePasswordForm.getConfirmPassword())) {
			ankenerrors.rejectValue("password", "field.pass.not.same", new String[] {""}, "新パスワードが一致しません。");
		}
		
		if (!ankenerrors.hasErrors()) {
			UserEntity theUser = personalService.getUserLoginInfoService(userSession.getLoginId(), MD5Util.EncoderByMd5(changePasswordForm.getOldPassword()));
			
			if (theUser == null) {
				ankenerrors.rejectValue("oldPassword", "old.pass.is.not.correct", new String[] {""}, "パスワードが正しくない。");
			} else {
				UserEntity theUserNew = new UserEntity();
				theUserNew.setUserId(userSession.getUserId());
				theUserNew.setLoginPassword(MD5Util.EncoderByMd5(changePasswordForm.getPassword()));
				theUserNew.setUpdateId(userSession.getUserId());
				theUserNew.setUpdateDt(changePasswordForm.getUpdateDt());
				int count = this.personalService.updatePassword(theUserNew);
				
				if (count < 0) {
					ankenerrors.rejectValue("oldPassword", "request.is.failed", new String[] {""}, "操作に失敗し、再び試みて下さい。");
				}
			}
		}
		
		if (ankenerrors.hasErrors()) {
			this.setHeader(new String[] {
					Constants.TRAIL_MEMBER_PASSWORD_TABTITLE,
					Constants.TRAIL_MEMBER_PASSWORD_PAGETITLE }, results);
			
			results.put(Constants.SHOW_ERROR_MESS_FLAG, true);
			return new ModelAndView("change_password", results);
		}
		else {
			results.put(Constants.SHOW_ERROR_MESS_FLAG, false);
		}
		
		this.setHeader(new String[] {
				Constants.TRAIL_MEMBER_SEARCH_TABTITLE,
				Constants.TRAIL_MEMBER_SEARCH_PAGETITLE }, results);
		
		return new ModelAndView("change_password_result", results);
	}
	
	@Autowired
	@Qualifier("memberListSearchService")
	private MemberListSearchService memberListSearchService;

	@Autowired
	@Qualifier("personalService")
	private PersonalService personalService;
}
