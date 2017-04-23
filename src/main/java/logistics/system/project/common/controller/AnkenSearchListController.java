package logistics.system.project.common.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.AnkenListEntity;
import logistics.system.project.common.form.AnkenSearchForm;
import logistics.system.project.common.parameterClass.AnkenListParameter;
import logistics.system.project.common.service.AnkenListSearchService;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;

@Controller
public class AnkenSearchListController extends BaseController {

	@Autowired
	@Qualifier("ankenListSearchService")
	private AnkenListSearchService ankenListSearchService;

	@Value("#{configProperties['AnkenCountsPerPage']}")
	private String ankenCountsPerPage;

	@Value("#{configProperties['DefaultDISPDL']}")
	private String defaultDISPDL;

	@RequestMapping(value = "initAnkenSearch", method = RequestMethod.GET)
	public ModelAndView initAnkenSearch(
			@RequestParam(value = "prePage", required = false) String prePage) {
		HashMap<String, Object> results = new HashMap<String, Object>();

		if (session.getAttribute("syasyuList") == null) {
			session.setAttribute("syasyuList", Constants.getSyasyuList());
		}
		if (session.getAttribute("truckOpList") == null) {
			session.setAttribute("truckOpList", Constants.getTruckOpList());
		}

		if ("index".equals(prePage)) {
			session.removeAttribute("ankenSearchForm");
		}
		session.setAttribute("panelClosed", "0");

		this.setHeader(new String[] {Constants.ANKEN_SEARCH_TABTITLE,
				Constants.ANKEN_SEARCH_PAGETITLE }, results);
		return new ModelAndView("anken_search", results);
	}

	@RequestMapping(value = "ankenSearch", method = RequestMethod.POST)
	public ModelAndView ankenSearch(
			@ModelAttribute("ankenSearchForm") AnkenSearchForm ankenSearchForm,
			BindingResult errors, RedirectAttributes attr) {

		if (StringUtils.isBlank(ankenSearchForm.getPanelClosed())) {
			String panelClosed = (String) session.getAttribute("panelClosed");

			if (StringUtils.isBlank(panelClosed)) {
				panelClosed = "1";
			}

			ankenSearchForm.setPanelClosed(panelClosed);
		}
		session.removeAttribute("panelClosed");

		session.setAttribute("ankenSearchForm", ankenSearchForm);

		if (!StringUtils.isEmpty(ankenSearchForm.getSyukaDayFr()) && !StringUtils.isEmpty(ankenSearchForm.getSyukaDayTo())) {
			String syukaDayFr = StringUtils.substring(ankenSearchForm.getSyukaDayFr(), 0, 10)
					.replaceAll("/", "");
			String syukaDayTo = StringUtils.substring(ankenSearchForm.getSyukaDayTo(), 0, 10)
					.replaceAll("/", "");
			if (syukaDayFr.compareTo(syukaDayTo) > 0) {

				attr.addFlashAttribute(Constants.SHOW_ERROR_MESS_FLAG, true);
				attr.addFlashAttribute(Constants.ERROR_MESSAGE, "集荷開始日付が集荷終了日付より後の日付は指定できません。");
				return new ModelAndView("redirect:initAnkenSearch", null);
			}
		}
		session.setAttribute("ankenSearchForm", ankenSearchForm);

		return new ModelAndView("redirect:ankenList", null);

	}

	@RequestMapping(value = "ankenList", method = RequestMethod.GET)
	public ModelAndView ankenList(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "ankenNo", required = false) String ankenNo) {

		HashMap<String, Object> results = new HashMap<String, Object>();

		this.setHeader(
				new String[] { Constants.ANKEN_LIST_TABTITLE, Constants.ANKEN_LIST_PAGETITLE },
				results);

		if (session.getAttribute("syasyuList") == null) {
			session.setAttribute("syasyuList", Constants.getSyasyuList());
		}
		if (session.getAttribute("truckOpList") == null) {
			session.setAttribute("truckOpList", Constants.getTruckOpList());
		}

		AnkenSearchForm ankenSearchForm = null;
		AnkenListParameter ankenListParameter = null;
		if (session.getAttribute("ankenSearchForm") != null) {
			ankenSearchForm = (AnkenSearchForm) session.getAttribute("ankenSearchForm");

		} else {
			ankenSearchForm = new AnkenSearchForm();
			ankenSearchForm.setAnkenNo(ankenNo);
			session.setAttribute("ankenSearchForm", ankenSearchForm);
		}

		ankenListParameter = this.setAnkenSearchParameter(ankenSearchForm);


		pageNo = (pageNo == null) ? 1 : pageNo;
		session.setAttribute("pageNo", pageNo);

		int ankenCountsPerPage = Integer.valueOf(this.ankenCountsPerPage);
		int ankenListSize = this.ankenListSearchService.getAnkenListCount(ankenListParameter);
		int startIndex = (pageNo - 1) * ankenCountsPerPage + 1;
		int endIndex = ((startIndex + ankenCountsPerPage - 1) <= ankenListSize ? (startIndex
				+ ankenCountsPerPage - 1) : ankenListSize);
		// DB start index
		ankenListParameter.setStartIndex(startIndex - 1);
		ankenListParameter.setCountPerPage(ankenCountsPerPage);

		List<AnkenListEntity> ankenListByPage =  this.ankenListSearchService.getAnkenList(ankenListParameter);


		results.put("startIndex", startIndex);
		results.put("endIndex", endIndex);

		results.put("pageSize", ankenCountsPerPage);
		results.put("totalRecordCount", ankenListSize);
		results.put("ankenListByPage", ankenListByPage);

		results.put("panelClosed", request.getParameter("panelClosed"));
		if (StringUtils.isNotBlank(request.getParameter("panelClosed"))) {
			ankenSearchForm.setPanelClosed(request.getParameter("panelClosed"));
		}
		if (StringUtils.isBlank(ankenSearchForm.getPanelClosed())) {
			ankenSearchForm.setPanelClosed("1");
		}


		return new ModelAndView("anken_list", results);
	}

	private AnkenListParameter setAnkenSearchParameter(AnkenSearchForm ankenSearchForm) {
		AnkenListParameter ankenListParameter = new AnkenListParameter();
		ankenListParameter.setGyomuSb(userSession.getGyomuSb());
		ankenListParameter.setCompanyCd(userSession.getCompanyCd());

		if (ankenSearchForm.getPrefNameList() != null) {
			ankenListParameter.setPrefNameList(Arrays.asList(StringUtils.split(
					ankenSearchForm.getTodohuen(), ",")));
		}
		if (ankenSearchForm.getCityNameList() != null) {
			ankenListParameter.setCityNameList(Arrays.asList(StringUtils.split(
					ankenSearchForm.getSikutyoson(), ",")));
		}
		if (ankenSearchForm.getSyasyuList() != null) {
			ankenListParameter.setSyasyuList(Arrays.asList(ankenSearchForm.getSyasyuList()));
		}

		if (!StringUtils.isEmpty(ankenSearchForm.getSyukaDayFr())) {
			String syukaDayFr = StringUtils.substring(ankenSearchForm.getSyukaDayFr(), 0, 10)
					.replace("/", "");
			ankenListParameter.setSyukaDayFr(syukaDayFr);
		}

		if (!StringUtils.isEmpty(ankenSearchForm.getSyukaDayTo())) {
			String ayukaDayTo = StringUtils.substring(ankenSearchForm.getSyukaDayTo(), 0, 10)
					.replace("/", "");
			ankenListParameter.setSyukaDayTo(ayukaDayTo);
		}
		if (ankenSearchForm.getOpList() != null) {
			ankenListParameter.setOpList(Arrays.asList(ankenSearchForm.getOpList()));
		}
		ankenListParameter.setAnkenNo(StringUtils.trimToEmpty(ankenSearchForm.getAnkenNo()).replaceAll("-", ""));

		if (ankenSearchForm.getAnkenStatusList() != null) {
			ankenListParameter.setAnkenStatusList(Arrays.asList(ankenSearchForm
					.getAnkenStatusList()));
		}

		ankenListParameter.setSortId(StringUtils.isEmpty(ankenSearchForm.getSortId()) ? "0"
				: ankenSearchForm.getSortId());

		if (Constants.GYOMU_SB_TRAIL.equals(userSession.getGyomuSb())) {
			ankenListParameter.setSeikyuKsCd(ankenSearchForm.getSeikyuKsCd());
			ankenListParameter.setShihraiKsCd(ankenSearchForm.getShihraiKsCd());
		} else if (Constants.GYOMU_SB_NINUSHI.equals(userSession.getGyomuSb())) {
			//ankenListParameter.setSeikyuKsCd(userSession.getCompanyCd());
		} else if (Constants.GYOMU_SB_UNSO.equals(userSession.getGyomuSb())) {
			ankenListParameter.setCreateDt(ComUtils.getDelayDate(userSession.getDispDl()));
		}

		ankenListParameter.setJutuKg(ankenSearchForm.getJutuKg());

		return ankenListParameter;
	}
}
