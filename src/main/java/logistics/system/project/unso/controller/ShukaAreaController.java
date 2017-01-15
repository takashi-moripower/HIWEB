package logistics.system.project.unso.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.CityEntity;
import logistics.system.project.common.dao.TruckOpDao;
import logistics.system.project.common.form.AnkenSearchForm;
import logistics.system.project.common.service.AnkenListSearchService;
import logistics.system.project.common.service.ShukaAreaService;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShukaAreaController extends BaseController {

	@Autowired
	@Qualifier("areaService")
	private ShukaAreaService shukaAreaService;

	@Autowired
	@Qualifier("ankenListSearchService")
	private AnkenListSearchService ankenListSearchService;

	@Autowired
	@Qualifier("truckOpDao")
	private TruckOpDao truckOpDao;

	@Value("#{configProperties['MaxTodohuenSentakuSu']}")
	private String maxTodohuenSentakuSu;

	@Value("#{configProperties['MaxSikutyosonSentakuSu']}")
	private String maxSikutyosonSentakuSu;

	/**
	 * 案件検索から
	 *
	 * @param ankenSearchForm
	 * @return
	 */
	@RequestMapping(value = "submit_todohuen", method = RequestMethod.POST)
	public ModelAndView submit_todohuen(
			@ModelAttribute("ankenSearchForm") AnkenSearchForm ankenSearchForm,
			RedirectAttributes attr) {

		session.setAttribute("ankenSearchForm", ankenSearchForm);

		attr.addAttribute("func", ankenSearchForm.getFunc());
		return new ModelAndView("redirect:init_todohuen", null);
	}

	/**
	 * 案件検索から
	 *
	 * @param ankenSearchForm
	 * @return
	 */
	@RequestMapping(value = "submit_sikutyoson", method = RequestMethod.POST)
	public ModelAndView submit_sikutyoson(
			@ModelAttribute("ankenSearchForm") AnkenSearchForm ankenSearchForm,
			RedirectAttributes attr) {

		session.setAttribute("ankenSearchForm", ankenSearchForm);

		String func = ankenSearchForm.getFunc();
		String prefName = ankenSearchForm.getPrefName();
		String prefCd = shukaAreaService.getPreCdByPrefName(prefName);

		attr.addAttribute("func", func);
		attr.addAttribute("prefCd", prefCd);
		return new ModelAndView("redirect:init_sikutyoson", null);
	}

	@RequestMapping(value = "init_todohuen", method = RequestMethod.GET)
	public ModelAndView init_todohuen(@RequestParam("func") String func) {
		HashMap<String, Object> results = new HashMap<String, Object>();
		this.setHeader(new String[] { Constants.UNSO_TODOHUEN_SENTAKU_TABTITLE,
				Constants.UNSO_TODOHUEN_SENTAKU_PAGETITLE }, results);

		results.put("prefs", shukaAreaService.getAllPref(ComUtils.getDelayDate(userSession.getDispDl())));
		results.put("maxTodohuenSentakuSu", maxTodohuenSentakuSu);
		results.put("func", func);

		if ("shuka".equals(func)) {
			session.removeAttribute("ankenSearchForm");
		}

		return new ModelAndView("anken_search_todohuen", results);
	}

	@RequestMapping(value = "init_sikutyoson", method = RequestMethod.GET)
	public ModelAndView init_sikutyoson(@RequestParam("func") String func,
			@RequestParam("prefCd") String prefCd) {
		HashMap<String, Object> results = new HashMap<String, Object>();

		String prefName = shukaAreaService.getPreNameByPrefCd(prefCd);
		this.setHeader(new String[] { Constants.UNSO_SIKUTYOSON_SENTAKU_TABTITLE,
				Constants.UNSO_SIKUTYOSON_SENTAKU_PAGETITLE + "（" + prefName + "）" }, results);

		final List<CityEntity> cites = shukaAreaService.getCitiesByPrefCd(prefCd, ComUtils.getDelayDate(userSession.getDispDl()));
		Map<String, List<CityEntity>> dispCategs = new HashMap<String, List<CityEntity>>();

		String tmpDispCateg = null;
		List<CityEntity> tmpList = null;

		for (CityEntity city : cites) {
			if (!city.getDispCateg().equals(tmpDispCateg)) {
				tmpDispCateg = city.getDispCateg();
				tmpList = new ArrayList<CityEntity>();
				dispCategs.put(tmpDispCateg, tmpList);
			}
			tmpList.add(city);
		}

		results.put("dispCategs", dispCategs);
		results.put("maxSikutyosonSentakuSu", maxSikutyosonSentakuSu);
		results.put("func", func);
		results.put("prefName", prefName);

		return new ModelAndView("anken_search_sikutyoson", results);
	}

	@RequestMapping(value = "todohuenSentaku", method = RequestMethod.POST)
	public ModelAndView todohuenSentaku(
			@ModelAttribute("ankenSearchForm") AnkenSearchForm ankenSearchForm,
			RedirectAttributes attr) {

		if ("shuka".equals(ankenSearchForm.getFunc())) {
			session.setAttribute("ankenSearchForm", ankenSearchForm);
			ankenSearchForm.setAnkenStatusList(new String[]{Constants.ANKEN_STATUS_10});
			ankenSearchForm.setSyukaDayFr(ComUtils.getSysJapanDate());
		} else {
			AnkenSearchForm sessionAnkenSearchForm = (AnkenSearchForm) session
					.getAttribute("ankenSearchForm");
			sessionAnkenSearchForm.setPrefNameList(ankenSearchForm.getPrefNameList());
			sessionAnkenSearchForm.setCityNameList(new String[] {});
		}

		if (ankenSearchForm.getPrefNameList() != null
				&& ankenSearchForm.getPrefNameList().length > Integer.valueOf(maxTodohuenSentakuSu)) {

			attr.addFlashAttribute("showErrorMessFlag", true);
			attr.addFlashAttribute("errormessage", "都道府県は最大(" + maxTodohuenSentakuSu + ")件まで選択できます。");

			attr.addAttribute("func", ankenSearchForm.getFunc());

			return new ModelAndView("redirect:init_todohuen", null);
		}

		if ("shuka".equals(ankenSearchForm.getFunc())) {
			this.buildSession();

			return new ModelAndView("redirect:ankenList", null);
		} else {
			return new ModelAndView("redirect:initAnkenSearch", null);
		}
	}

	@RequestMapping(value = "sikutyosonSentaku", method = RequestMethod.POST)
	public ModelAndView sikutyosonSentaku(
			@ModelAttribute("ankenSearchForm") AnkenSearchForm ankenSearchForm,
			RedirectAttributes attr) {

		String prefCd = shukaAreaService.getPreCdByPrefName(ankenSearchForm.getPrefName());

		if ("shuka".equals(ankenSearchForm.getFunc())) {
			ankenSearchForm.setPrefNameList(new String[]{ankenSearchForm.getPrefName()});
			ankenSearchForm.setAnkenStatusList(new String[]{Constants.ANKEN_STATUS_10});
			ankenSearchForm.setSyukaDayFr(ComUtils.getSysJapanDate());

			session.setAttribute("ankenSearchForm", ankenSearchForm);
		} else {
			AnkenSearchForm sessionAnkenSearchForm = (AnkenSearchForm) session
					.getAttribute("ankenSearchForm");
			sessionAnkenSearchForm.setCityNameList(ankenSearchForm.getCityNameList());
		}

		if (ankenSearchForm.getCityNameList() != null
				&& ankenSearchForm.getCityNameList().length > Integer.valueOf(maxSikutyosonSentakuSu)) {

			attr.addFlashAttribute("showErrorMessFlag", true);
			attr.addFlashAttribute("errormessage", "市区町村は最大(" + maxTodohuenSentakuSu + ")件まで選択できます。");

			attr.addAttribute("func", ankenSearchForm.getFunc());
			attr.addAttribute("prefCd", prefCd);
			return new ModelAndView("redirect:init_sikutyoson", null);
		}

		if ("shuka".equals(ankenSearchForm.getFunc())) {
			this.buildSession();

			return new ModelAndView("redirect:ankenList", null);
		} else {
			return new ModelAndView("redirect:initAnkenSearch", null);
		}
	}

	private void buildSession() {
		if (session.getAttribute("syasyuList") == null) {
			session.setAttribute("syasyuList", Constants.getSyasyuList());
		}
		if (session.getAttribute("truckOpList") == null) {
			session.setAttribute("truckOpList",  Constants.getTruckOpList());
		}
	}

}
