package logistics.system.project.common.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.NinushiKensuEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.service.MenuService;
import logistics.system.project.common.service.PersonalService;
import logistics.system.project.ninushi.form.LoginForm;
import logistics.system.project.utility.AnkenPicUtils;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;
import logistics.system.project.utility.MD5Util;
import logistics.system.project.utility.WebUtils;

@Controller
public class LoginController extends BaseController {

	// @RequestMapping("ninushi/login")
	// public ModelAndView shipperIndex(){
	// HashMap<String, Object> results = new HashMap<String, Object>();
	// this.setHeader(new String[]{Constants.NINUSHI_INDEX_TABTITLE,
	// Constants.NINUSHI_INDEX_PAGETITLE}, results);
	// return new ModelAndView("ninushi/login_ninushi", results);
	// }

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView shipperLogin(Model model,
			@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult errors) {

		HashMap<String, Object> results = new HashMap<String, Object>();
		if (errors.hasErrors()) {
			this.setHeader(new String[] { Constants.NINUSHI_LOGIN_TABTITLE,
					Constants.NINUSHI_LOGIN_PAGETITLE }, results);
			results.put(Constants.SHOW_ERROR_MESS_FLAG, true);
			return new ModelAndView("../../login", results);
		}
		else {
			results.put(Constants.SHOW_ERROR_MESS_FLAG, false);
		}

		UserEntity userEntity = this.personalService.getUserLoginInfoService(loginForm.getUserId(),
				MD5Util.EncoderByMd5(loginForm.getPassword()));
		if (userEntity == null) {
			this.setHeader(new String[] { Constants.NINUSHI_INDEX_TABTITLE,
					Constants.NINUSHI_INDEX_PAGETITLE }, results);
			results.put(Constants.SHOW_ERROR_MESS_FLAG, true);
			results.put(Constants.ERROR_MESSAGE, Constants.USER_NOT_EXIST);
			return new ModelAndView("../../login", results);
		}
		else {
			results.put(Constants.SHOW_ERROR_MESS_FLAG, false);
		}

		model.addAttribute("user", userEntity);
		session.setAttribute("user", userEntity);
		session.setAttribute("anken_pic_url_tmp", WebUtils.getSessionTmpDirUrl(AnkenPicUtils.getAnkenPicTmpDir()));

		String backUrl = "index";

		if(Constants.GYOMU_SB_NINUSHI.equals(userEntity.getGyomuSb())){
			backUrl = "check_ca_ninushi";
		}
		else if(Constants.GYOMU_SB_UNSO.equals(userEntity.getGyomuSb())){
			backUrl = "check_ca_unso";
		}
		else if(Constants.GYOMU_SB_TRAIL.equals(userEntity.getGyomuSb())){
			backUrl = "check_ca_trail";
		}

		return new ModelAndView("redirect:" + backUrl, null);
	}

	//CAチェックのため、中間パス経由でメニューに行く
	@RequestMapping(value = {"check_ca_ninushi", "check_ca_unso", "check_ca_trail"}, method = RequestMethod.GET)
	public ModelAndView checkCACert() {
		String backUrl = "topPage";
		if (StringUtils.isNotBlank((String) session.getAttribute("backUrl"))) {
			backUrl = (String) session.getAttribute("backUrl");
			session.removeAttribute("backUrl");

			if (backUrl.indexOf("initAnkenDetail") != -1) {
				backUrl +=  "&prePage=index";
			}
		}

		return new ModelAndView("redirect:" + backUrl, null);
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView indexPage() {

		if (Constants.GYOMU_SB_NINUSHI.equals(userSession.getGyomuSb())) {
			//荷主
			return new ModelAndView("redirect:check_ca_ninushi", null);
		} else if (Constants.GYOMU_SB_UNSO.equals(userSession.getGyomuSb())) {
			// 運送
			return new ModelAndView("redirect:check_ca_unso", null);
		} else if (Constants.GYOMU_SB_TRAIL.equals(userSession.getGyomuSb())) {
			// trail
			return new ModelAndView("redirect:check_ca_trail", null);
		}

		return new ModelAndView("redirect:check_ca_unknown", null);
	}

	@RequestMapping(value = "topPage", method = RequestMethod.GET)
	private ModelAndView initTopPage() {
		HashMap<String, Object> results = new HashMap<String, Object>();

		this.removeSession();

		results.put(Constants.SHOW_LOGOUT_FLAG, true);

		String sysDate = ComUtils.getSysDate();
		if (Constants.GYOMU_SB_NINUSHI.equals(userSession.getGyomuSb())) {
			NinushiKensuEntity ninushiKensuEntity = menuService.getNinushiKensu(
					userSession.getCompanyCd(), sysDate);
			results.put("ninushiKensuEntity", ninushiKensuEntity);

			Integer kakuinZumiKensu = menuService.getKakuinZumiKensu(userSession.getCompanyCd(),
					sysDate, userSession.getGyomuSb());
			results.put("kakuinZumiKensu", kakuinZumiKensu);

			this.setHeader(new String[] { Constants.NINUSHI_LOGIN_TABTITLE,
					Constants.NINUSHI_LOGIN_PAGETITLE, userSession.getUsername() }, results);
			results.put(Constants.SHOW_LOGOUT_FLAG, true);

			return new ModelAndView("ninushi/index_ninushi", results);
		} else if (Constants.GYOMU_SB_UNSO.equals(userSession.getGyomuSb())) {
			// 運送
			Integer kakuinZumiKensu = menuService.getKakuinZumiKensu(userSession.getCompanyCd(),
					sysDate, userSession.getGyomuSb());
			results.put("kakuinZumiKensu", kakuinZumiKensu);

			Integer shabanMinyuRyokuKensu = menuService.getShabanMinyuRyokuKensu(
					userSession.getCompanyCd(), sysDate);
			results.put("shabanMinyuRyokuKensu", shabanMinyuRyokuKensu);

			this.setHeader(new String[] { Constants.UNSO_INDEX_TABTITLE,
					Constants.UNSO_INDEX_PAGETITLE, userSession.getUsername() }, results);
			return new ModelAndView("unso/index_unso", results);
		} else if (Constants.GYOMU_SB_TRAIL.equals(userSession.getGyomuSb())) {
			// trail
			this.setHeader(new String[] { Constants.TRAIL_INDEX_TABTITLE,
					Constants.TRAIL_INDEX_PAGETITLE, userSession.getUsername() }, results);

			return new ModelAndView("trail/index_trail", results);
		}

		return new ModelAndView("", results);
	}


	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		session.removeAttribute("user");
		session.invalidate();

		return new ModelAndView("redirect:/");
	}

	@Autowired
	@Qualifier("personalService")
	private PersonalService personalService;

	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;

	private void removeSession() {
		session.removeAttribute("ankenSearchForm");
		session.removeAttribute("ankenListParameter");
		session.removeAttribute("ankenList");
		session.removeAttribute("syasyuList");
		session.removeAttribute("truckOpList");
		session.removeAttribute("pageNo");
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.EncoderByMd5("111"));
	}
}
