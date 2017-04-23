package logistics.system.project.tuchi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.parameterClass.MemberListParameter;
import logistics.system.project.common.service.MemberListSearchService;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.component.DynamicPropertyComponent;
import logistics.system.project.tuchi.component.MailSendComponent;
import logistics.system.project.tuchi.dao.TuchiDao;
import logistics.system.project.tuchi.form.TuchiEditForm;
import logistics.system.project.tuchi.service.TuchiService;
import logistics.system.project.utility.Constants;

@Controller
public class TuchiController extends BaseController {

	HashMap<String, Object> results;

	protected void clearResults() {
		this.results = new HashMap<>();
	}

	@RequestMapping(value = "tuchi_list")
	public ModelAndView list() {

		clearResults();

		String userId;
		if (userSession.getGyomuSb().equals(Constants.GYOMU_SB_TRAIL)) {
			userId = null;
		} else {
			userId = userSession.getUserId();
		}

		List<TuchiEntity> l = tuchiService.getTuchiByUser(userId, true);

		results.put("list", l);

		return new ModelAndView("tuchi/list", results);
	}

	@RequestMapping(value = "tuchi_add")
	public ModelAndView Add() {

		TuchiEntity e = new TuchiEntity();
		e.setTitle("新規作成");
		e.setUserId(userSession.getUserId());
		e.initLinks();

		return editMain(e);
	}

	@RequestMapping(value = "tuchi_edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		String id = request.getParameter("tuchiId");

		TuchiEntity e = tuchiService.getTuchiById(Integer.parseInt(id), true);

		return editMain(e);
	}

	protected ModelAndView editMain(TuchiEntity e) {
		clearResults();

		TuchiEditForm form = new TuchiEditForm();
		form.initForm(e);

		results.put("form", form);

		MemberListParameter params = new MemberListParameter();
		params.setGyomuSb(Constants.GYOMU_SB_NINUSHI);
		results.put("ninushiList", memberListSearchService.getMemberList(params));

		return new ModelAndView("tuchi/edit", results);
	}

	@RequestMapping(value = "tuchi_edit", method = RequestMethod.POST)
	public ModelAndView editPost(@Valid @ModelAttribute("tuchiEditForm") TuchiEditForm form, BindingResult result) {
		clearResults();

		if (result.hasErrors()) {

			List<String> errors = new ArrayList<>();

			for (ObjectError e : result.getAllErrors()) {
				errors.add(e.getDefaultMessage());
			}
			results.put("form", form);
			results.put("errors", errors);

			MemberListParameter params = new MemberListParameter();
			params.setGyomuSb(Constants.GYOMU_SB_NINUSHI);
			results.put("ninushiList", memberListSearchService.getMemberList(params));

			return new ModelAndView("tuchi/edit", results);
		}

		TuchiEntity e = new TuchiEntity();
		form.updateEntity(e);
		tuchiService.save(e);

		return new ModelAndView("redirect:tuchi_list");
	}

	@RequestMapping(value = "tuchi_delete", method = RequestMethod.GET)
	public String delete() {
		int id = Integer.parseInt(request.getParameter("tuchiId"));

		tuchiService.delete(id);

		return "redirect:tuchi_list";
	}

	@RequestMapping(value = "tuchi_debug_search")
	public ModelAndView debug_tuchi_search() {
		clearResults();

		int checked = tuchiService.checkAll();

		results.put("data", checked);

		return new ModelAndView("tuchi/debug", results);
	}

	@RequestMapping(value = "tuchi_debug_send")
	public ModelAndView debugSendMail() {
		clearResults();

		tuchiService.sendMail();

		return new ModelAndView("tuchi/debug", results);
	}

	@RequestMapping(value = "tuchi_debug")
	public ModelAndView debug(HttpServletRequest request) {
		clearResults();

		results.put("data", baseUrl);
		return new ModelAndView("tuchi/debug", results);
	}

	@RequestMapping(value = "tuchi_init_base_url")
	public ModelAndView initBaseUrl(HttpServletRequest request) {

		String baseUrl = mailSendComponent.getBaseUrl(request);
		dynamicPropertyComponent.init(request);
		dynamicPropertyComponent.setProperty("base_url", baseUrl);
		dynamicPropertyComponent.store("");

		results.put("data", baseUrl);

		return new ModelAndView("tuchi/debug", results);
	}

	@Value("#{dynamicProperties['test_url']}")
	private String testUrl;

	@Value("#{dynamicProperties['base_url']}")
	private String baseUrl;

	@Autowired
	@Qualifier("mailSendComponent")
	private MailSendComponent mailSendComponent;

	@Autowired
	@Qualifier("dynamicPropertyComponent")
	private DynamicPropertyComponent dynamicPropertyComponent;

	@Autowired
	@Qualifier("tuchiService")
	private TuchiService tuchiService;

	@Autowired
	@Qualifier("tuchiDao")
	private TuchiDao tuchiDao;

	@Autowired
	@Qualifier("memberListSearchService")
	private MemberListSearchService memberListSearchService;

}
