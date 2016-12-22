package logistics.system.project.common.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.form.AnkenDetailForm;
import logistics.system.project.common.form.UpdateInfo;
import logistics.system.project.common.service.AnkenDetailService;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;
import net.sf.json.JSONObject;

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
public class AnkenDetailController extends BaseController {

	@Value("#{configProperties['update_anken_zero']}")
	private String update_anken_zero;

	@Autowired
	@Qualifier("ankenDetailService")
	private AnkenDetailService ankenDetailService;

	@RequestMapping(value = "initAnkenDetail", method = RequestMethod.GET)
	public ModelAndView initAnkenDetail(@RequestParam("ankenNo") String ankenNo,
			@RequestParam(value = "prePage", required = false) String prePage) {
		HashMap<String, Object> results = new HashMap<String, Object>();

		AnkenDetailForm ankenDetailForm = ankenDetailService
				.getAnkenDetail(ankenNo);
		results.put("ankenDetailForm", ankenDetailForm);
		results.put("prePage", prePage);

		this.setHeader(new String[] {Constants.ANKEN_DETAIL_TABTITLE,
				Constants.ANKEN_DETAIL_PAGETITLE }, results);
		return new ModelAndView("anken_detail", results);
	}

	@RequestMapping(value = "ankenListZyutyu", method = RequestMethod.POST)
	public ModelAndView ankenListZyutyu(@ModelAttribute("ankenDetailEntity") AnkenDetailEntity ankenDetailEntity, HttpServletResponse response) {

		boolean success = this.zyutyu(ankenDetailEntity);

		AnkenDetailEntity ankenDetail = ankenDetailService.getAnkenDetailByNo(ankenDetailEntity.getAnkenNo());

		JSONObject json;
		synchronized(JSONObject.class){
			 json = JSONObject.fromObject(new UpdateInfo(success, ankenDetail.getUpdateDt()));
	    }
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

	@RequestMapping(value = "ankenDetailZyutyu", method = RequestMethod.POST)
	public ModelAndView ankenDetailZyutyu(
			@ModelAttribute("ankenDetailEntity") AnkenDetailEntity ankenDetailEntity, RedirectAttributes attr) {

		boolean success = this.zyutyu(ankenDetailEntity);

		attr.addAttribute("ankenNo", ankenDetailEntity.getAnkenNo());
		if (success) {
			attr.addFlashAttribute("message", "案件を受注しました。");
		} else {
			attr.addFlashAttribute("message", update_anken_zero);

		}
		return new ModelAndView("redirect:initAnkenDetail", null);

	}

	private boolean zyutyu(AnkenDetailEntity ankenDetailEntity) {

		ankenDetailEntity.setStatus(Constants.ANKEN_STATUS_20);

		ankenDetailEntity.setUnsoCd(userSession.getCompanyCd());
		ankenDetailEntity.setShihraiKsCd(userSession.getCompanyCd());
		ankenDetailEntity.setKinkyuCorp(userSession.getCompanyNm());
		ankenDetailEntity.setKinkyuTant(userSession.getUsername());
		ankenDetailEntity.setKinkyuTel(userSession.getRenrakuTel());
		ankenDetailEntity.setUpdateId(userSession.getUserId());
		boolean res = ankenDetailService.updateAnkenStatus(ankenDetailEntity);

		if (res) {
			try {
				ComUtils.sendMail(ankenDetailEntity.getAnkenNo(), "", userSession.getUserId());
			} catch(Exception e) {

			}
		}

		return res;
	}

	@RequestMapping(value = "ankenListShuka", method = RequestMethod.POST)
	public ModelAndView ankenListShuka(@ModelAttribute("ankenDetailEntity") AnkenDetailEntity ankenDetailEntity) {

		boolean success = this.Shuka(ankenDetailEntity);

		JSONObject json;
		synchronized(JSONObject.class){
			 json = JSONObject.fromObject(new UpdateInfo(success));
	    }

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

	@RequestMapping(value = "ankenDetailShuka", method = RequestMethod.POST)
	public ModelAndView ankenDetailShuka(
			@ModelAttribute("ankenDetailEntity") AnkenDetailEntity ankenDetailEntity, RedirectAttributes attr) {

		boolean success = this.Shuka(ankenDetailEntity);

		attr.addAttribute("ankenNo", ankenDetailEntity.getAnkenNo());
		if (success) {
			attr.addFlashAttribute("message", "案件集荷が完了しました。");
		} else {
			attr.addFlashAttribute("message", update_anken_zero);
		}
		return new ModelAndView("redirect:initAnkenDetail", null);

	}

	private boolean Shuka(AnkenDetailEntity ankenDetailEntity) {
		ankenDetailEntity.setStatus(Constants.ANKEN_STATUS_40);
		ankenDetailEntity.setUpdateId(userSession.getUserId());
		return ankenDetailService.updateAnkenStatus(ankenDetailEntity);
	}

	@RequestMapping(value = "ankenDetailCancel", method = RequestMethod.POST)
	public ModelAndView ankenDetailCancel(
			@ModelAttribute("ankenDetailEntity") AnkenDetailEntity ankenDetailEntity, RedirectAttributes attr) {

		String prevUpdateUserId = ankenDetailEntity.getUpdateId();

		ankenDetailEntity.setStatus(Constants.ANKEN_STATUS_90);
		ankenDetailEntity.setUpdateId(userSession.getUserId());
		boolean success = ankenDetailService
				.updateAnkenStatus(ankenDetailEntity);

		attr.addAttribute("ankenNo", ankenDetailEntity.getAnkenNo());
		if (success) {
			try {
				ComUtils.sendMail(ankenDetailEntity.getAnkenNo(), prevUpdateUserId, userSession.getUserId());
			} catch(Exception e) {

			}
			attr.addFlashAttribute("message", "案件をキャンセルしました。");
		} else {
			attr.addFlashAttribute("message", update_anken_zero);
		}
		return new ModelAndView("redirect:initAnkenDetail", null);

	}

	@RequestMapping(value = "ankenDetailDelete", method = RequestMethod.POST)
	public ModelAndView ankenDetailDelete(
			@ModelAttribute("ankenDetailEntity") AnkenDetailEntity ankenDetailEntity, RedirectAttributes attr) {

		ankenDetailEntity.setStatus(Constants.ANKEN_STATUS_00);
		ankenDetailEntity.setUpdateId(userSession.getUserId());
		boolean success = ankenDetailService
				.updateAnkenStatus(ankenDetailEntity);

		if (success) {
			attr.addFlashAttribute("message", "案件を削除しました。");
		} else {
			attr.addFlashAttribute("message", update_anken_zero);
		}
		return new ModelAndView("redirect:ankenList", null);

	}

	@Override
	public boolean checkUser(String ankenNo) {
		AnkenDetailForm ankenDetailForm = ankenDetailService
				.getAnkenDetail(ankenNo);

		if(Constants.GYOMU_SB_NINUSHI.equals(userSession.getGyomuSb())) {
			if (!userSession.getCompanyCd().equals(ankenDetailForm.getAnkenDetail().getNinushiCd())) {
				return false;
			}

		} else if (Constants.GYOMU_SB_UNSO.equals(userSession.getGyomuSb())) {
			if (Constants.ANKEN_STATUS_10.equals(ankenDetailForm.getAnkenDetail().getStatus())) {
				return true;
			}
			if (!userSession.getCompanyCd().equals(ankenDetailForm.getAnkenDetail().getUnsoCd())) {
				return false;
			}
		}
		return true;
	}

}
