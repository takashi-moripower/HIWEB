package logistics.system.project.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.form.MemberSearchForm;
import logistics.system.project.common.parameterClass.MemberListParameter;
import logistics.system.project.common.service.MemberListSearchService;
import logistics.system.project.utility.Constants;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberSearchController extends BaseController {
	@RequestMapping(value = "memberList")
	public ModelAndView memberList(
			@ModelAttribute("memberSearchForm") MemberSearchForm memberSearchForm, String selType,
			BindingResult ankenerrors) {
		HashMap<String, Object> results = new HashMap<String, Object>();

		this.setHeader(new String[] {
				Constants.TRAIL_MEMBER_SEARCH_TABTITLE,
				Constants.TRAIL_MEMBER_SEARCH_PAGETITLE }, results);

		if (memberSearchForm == null) {
			memberSearchForm = new MemberSearchForm();
		}

		if (StringUtils.isNotBlank(selType)) {
			if ("0".equals(selType)) {
				memberSearchForm.setGyomuSb0(Constants.GYOMU_SB_NINUSHI);
				memberSearchForm.setGyomuSb1(null);
			} else if ("1".equals(selType)) {
				memberSearchForm.setGyomuSb1(Constants.GYOMU_SB_UNSO);
				memberSearchForm.setGyomuSb0(null);
			} else if ("2".equals(selType)) {
				memberSearchForm.setGyomuSb0(Constants.GYOMU_SB_NINUSHI);
				memberSearchForm.setGyomuSb1(null);
				memberSearchForm.setGyomuSb9(Constants.GYOMU_SB_TRAIL);
			} else {
				memberSearchForm.setGyomuSb0(null);
				memberSearchForm.setGyomuSb1(null);
			}
		}

		MemberListParameter params = new MemberListParameter();
//		params.setGyomuSb(memberSearchForm.getGyomuSb());
		params.setGyomuSb0(memberSearchForm.getGyomuSb0());
		params.setGyomuSb1(memberSearchForm.getGyomuSb1());
		params.setGyomuSb9(memberSearchForm.getGyomuSb9());
		String gyomuSb = "";
		if (StringUtils.isNotBlank(memberSearchForm.getGyomuSb())) {
			gyomuSb += memberSearchForm.getGyomuSb() + ",";
		}
		if (StringUtils.isNotBlank(memberSearchForm.getGyomuSb0())) {
			gyomuSb += memberSearchForm.getGyomuSb0() + ",";
		}
		if (StringUtils.isNotBlank(memberSearchForm.getGyomuSb1())) {
			gyomuSb += memberSearchForm.getGyomuSb1() + ",";
		}
		if (StringUtils.isNotBlank(memberSearchForm.getGyomuSb9())) {
			gyomuSb += memberSearchForm.getGyomuSb9() + ",";
		}
		gyomuSb = gyomuSb.replaceAll(",$", "");
		params.setGyomuSb(gyomuSb);

		params.setPrefNm(memberSearchForm.getPrefNm());
		params.setCityNm(memberSearchForm.getCityNm());
		params.setKeyWord(memberSearchForm.getKeyWord());

		List<MemberEntity> memberList = this.memberListSearchService.getMemberList(params);

		results.put("memberList", memberList);
		results.put("selType", selType);

		return new ModelAndView("member_list",results);
	}

	@RequestMapping(value = "getMember", method = RequestMethod.GET)
	public ModelAndView ryokinToroku(@RequestParam("companyCd") String companyCd) {

		MemberEntity member = memberListSearchService.getMemberByCd(companyCd);
		JSONObject json = JSONObject.fromObject(member);

		String jsonStr = json.toString();
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Autowired
	@Qualifier("memberListSearchService")
	private MemberListSearchService memberListSearchService;
}
