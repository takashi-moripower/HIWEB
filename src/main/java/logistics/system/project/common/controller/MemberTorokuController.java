package logistics.system.project.common.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.form.MemberTorokuForm;
import logistics.system.project.common.service.MemberListSearchService;
import logistics.system.project.common.service.MemberTorokuService;
import logistics.system.project.utility.Constants;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberTorokuController extends BaseController {
	@RequestMapping(value = "initUpdateMember", method = RequestMethod.GET)
	public ModelAndView initUpdateMember() {
		HashMap<String, Object> results = new HashMap<String, Object>();

		this.setHeader(new String[] { Constants.MEMBER_TOROKU_TABTITLE,
				Constants.MEMBER_TOROKU_PAGETITLE }, results);

		String companyCd = userSession.getCompanyCd();

		if (Constants.GYOMU_SB_TRAIL.equals(userSession.getGyomuSb())) {
			if (StringUtils.isNotBlank(request.getParameter("companyCd"))) {
				companyCd = request.getParameter("companyCd");
			}
		}

		MemberTorokuForm memberTorokuForm = memberTorokuService.getMember(companyCd);
		memberTorokuForm.setPrefList(Constants.getPrefList());

		results.put("memberTorokuForm", memberTorokuForm);

		return new ModelAndView("member_toroku", results);
	}

	@RequestMapping(value = "initRegisterMember", method = RequestMethod.GET)
	public ModelAndView initRegisterMember() {
		HashMap<String, Object> results = new HashMap<String, Object>();

		this.setHeader(new String[] { Constants.MEMBER_TOROKU_TABTITLE,
				Constants.MEMBER_TOROKU_PAGETITLE }, results);

		MemberTorokuForm memberTorokuForm = new MemberTorokuForm();
		MemberEntity member = new MemberEntity();
		member.setGyomuSb(Constants.GYOMU_SB_NINUSHI);
		member.setRyoritu("10");
		memberTorokuForm.setMember(member);
		memberTorokuForm.setPrefList(Constants.getPrefList());

		results.put("memberTorokuForm", memberTorokuForm);

		return new ModelAndView("member_toroku", results);
	}

	@RequestMapping(value = "submitMember", method = RequestMethod.POST)
	public ModelAndView updateMember(
			@ModelAttribute("memberTorokuForm") @Valid MemberTorokuForm memberTorokuForm,
			BindingResult errors, RedirectAttributes attr) {

		if (StringUtils.isBlank(memberTorokuForm.getMember().getCompanyCd())) {
			MemberEntity theMember = memberListSearchService.getMemberByCd(memberTorokuForm.getMember().getCustomCd() + (memberTorokuForm.getMember().getOfficeCd().length() == 1 ? "0" + memberTorokuForm.getMember().getOfficeCd() : memberTorokuForm.getMember().getOfficeCd()));
			if(theMember != null) {
				errors.rejectValue("member.customCd", "", null, "得意先コードと事業所コードが既に登録されています。");
			}
		}

		HashMap<String, Object> results = new HashMap<String, Object>();
		List<UserEntity> userList = memberTorokuForm.getUserList();
		for (int i = 0; i < userList.size(); i++) {
			UserEntity user = userList.get(i);
			if (StringUtils.isBlank(user.getUsername())) {
				if (!(StringUtils.isBlank(user.getUserBS())
						&& StringUtils.isBlank(user.getUserYK()) && StringUtils.isBlank(user.getLoginId())
						&& StringUtils.isBlank(user.getRenrakuTel()))) {
					errors.rejectValue("userList[" + i + "].username", "", new String[] {"氏名"}, "部署/役職/TEL/E-Mailのいずれかが入力された場合、氏名" + user.getIndex() +"が必須入力です");
				}
			} else {
				if (StringUtils.isBlank(user.getRenrakuTel())){
					errors.rejectValue("userList[" + i + "].renrakuTel", "", new String[] {"TEL"}, "氏名が入力された場合、TEL" + user.getIndex() +"が必須入力です");
				}
				if (StringUtils.isBlank(user.getLoginId())){
					errors.rejectValue("userList[" + i + "].loginId", "", new String[] {"E-mail"}, "氏名が入力された場合、E-mail" + user.getIndex() +"が必須入力です。");
				}
			}

			if (!StringUtils.isBlank(user.getLoginId())){
				if (memberTorokuService.isEmailExist(user)) {
					errors.rejectValue("userList[" + i + "].loginId", "", new String[] {"E-mail"}, "E-mail" + user.getIndex() +"は既に登録されています。");
				}

				for (int j = 0; j < userList.size(); j++) {
					UserEntity user1 = userList.get(j);
					if (!user.getIndex().equals(user1.getIndex()) && user.getLoginId().equals(user1.getLoginId())) {
						errors.rejectValue("userList[" + i + "].loginId", "", new String[] {"E-mail"}, "E-mail" + user.getIndex() +"とE-mail" + user1.getIndex() +"が重複です。");
						break;
					}
				}
			}
		}

		if (errors.hasErrors()) {
			results.put(Constants.SHOW_ERROR_MESS_FLAG, true);
		} else {
			boolean success = false;
			if (StringUtils.isNotBlank(memberTorokuForm.getMember().getCompanyCd())) {
				try {
					MemberTorokuForm memberForm = memberTorokuService.getMember(memberTorokuForm.getMember().getCompanyCd());
					memberTorokuForm.getMember().setGyomuSb(memberForm.getMember().getGyomuSb());
					if(!Constants.GYOMU_SB_TRAIL.equals(userSession.getGyomuSb())) {
						memberTorokuForm.getMember().setCompanyNm(memberForm.getMember().getCompanyNm());
						memberTorokuForm.getMember().setOfficeNm(memberForm.getMember().getOfficeNm());
						memberTorokuForm.getMember().setKeiyakuDay(memberForm.getMember().getKeiyakuDay());
						memberTorokuForm.getMember().setKeiyakuKgDay(memberForm.getMember().getKeiyakuKgDay());
						memberTorokuForm.getMember().setKeiyakuDayDis(memberForm.getMember().getKeiyakuDayDis());
						memberTorokuForm.getMember().setKeiyakuKgDayDis(memberForm.getMember().getKeiyakuKgDayDis());
						memberTorokuForm.getMember().setRyoritu(memberForm.getMember().getRyoritu());
						memberTorokuForm.getMember().setHokenMn(memberForm.getMember().getHokenMn());
						memberTorokuForm.getMember().setPostCode(memberForm.getMember().getPostCode());
					}

					success = memberTorokuService.updateMember(memberTorokuForm, userSession.getCompanyCd());
				} catch (Exception e) {
					attr.addFlashAttribute("message", "会員登録が失敗しました。");
					return new ModelAndView("redirect:initUpdateMember", results);
				}
			} else {
				try {
					success = memberTorokuService.insertMember(memberTorokuForm, userSession.getCompanyCd());
				} catch (Exception e) {
					attr.addFlashAttribute("message", "会員登録が失敗しました。");
					return new ModelAndView("redirect:initUpdateMember", results);
				}

				if (!success) {
					memberTorokuForm.getMember().setCompanyCd(null);
				}
			}
			results.put(Constants.SHOW_ERROR_MESS_FLAG, false);
			results.put("success", success);
		}

		this.setHeader(new String[] { Constants.MEMBER_TOROKU_TABTITLE,
				Constants.MEMBER_TOROKU_PAGETITLE }, results);

		memberTorokuForm.setPrefList(Constants.getPrefList());
		return new ModelAndView("member_toroku", results);
	}

	@Autowired
	@Qualifier("memberTorokuService")
	private MemberTorokuService memberTorokuService;

	@Autowired
	@Qualifier("memberListSearchService")
	private MemberListSearchService memberListSearchService;
}
