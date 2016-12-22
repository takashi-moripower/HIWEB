package logistics.system.project.unso.controller;

import java.util.HashMap;

import javax.validation.Valid;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.form.AnkenDetailForm;
import logistics.system.project.common.service.AnkenDetailService;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;

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

@Controller
public class SyabanController extends BaseController {

	@Value("#{configProperties['update_anken_zero']}")
	private String update_anken_zero;

	@Autowired
	@Qualifier("ankenDetailService")
	private AnkenDetailService ankenDetailService;

	/**
	 * 案件検索から
	 *
	 * @param ankenSearchForm
	 * @return
	 */
	@RequestMapping(value = "initSyaban", method = RequestMethod.GET)
	public ModelAndView initSyaban(@RequestParam("ankenNo") String ankenNo,
			@RequestParam("updateDt") String updateDt,
			@RequestParam("prePage") String prePage, RedirectAttributes attr) {
		HashMap<String, Object> results = new HashMap<String, Object>();

		AnkenDetailForm ankenDetailForm = ankenDetailService.getAnkenDetail(ankenNo);

		if (!updateDt.equals(ankenDetailForm.getAnkenDetail().getUpdateDt())) {
			if ("ankenDetail".equals(prePage)){
				attr.addFlashAttribute("message", update_anken_zero);
				attr.addAttribute("ankenNo", ankenNo);
				return new ModelAndView("redirect:initAnkenDetail", results);
			} else if ("ankenList".equals(prePage)) {
				attr.addFlashAttribute("message", update_anken_zero);
				return new ModelAndView("redirect:ankenList", results);
			}
		}

		results.put("ankenDetailForm", ankenDetailForm);
		return new ModelAndView("anken_detail_syaban", results);
	}

	/**
	 * 案件検索から
	 *
	 * @param ankenSearchForm
	 * @return
	 */
	@RequestMapping(value = "submitSyaban", method = RequestMethod.POST)
	public ModelAndView submitSyaban(@ModelAttribute("ankenDetailForm") @Valid AnkenDetailForm ankenDetailForm,
			BindingResult errors, RedirectAttributes attr) {

		HashMap<String, Object> results = new HashMap<String, Object>();

		if (errors.hasErrors()) {
			results.put(Constants.SHOW_ERROR_MESS_FLAG, true);
			return new ModelAndView("anken_detail_syaban", results);
		}

		AnkenDetailEntity ankenDetail =  ankenDetailForm.getAnkenDetail();

		ankenDetailForm.getAnkenDetail().setStatus(Constants.ANKEN_STATUS_30);
		boolean success = ankenDetailService.updateSyaban(ankenDetail, this.userSession);
		attr.addAttribute("ankenNo", ankenDetail.getAnkenNo());

		if (success) {
			try {
				ComUtils.sendMail(ankenDetail.getAnkenNo(), "", userSession.getUserId());
			} catch(Exception e) {

			}

			attr.addFlashAttribute("message", "車番入力が成功しました。");
		} else {
			attr.addFlashAttribute("message", update_anken_zero);
		}
		return new ModelAndView("redirect:initAnkenDetail", null);
	}

}
