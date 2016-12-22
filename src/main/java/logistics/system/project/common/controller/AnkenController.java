package logistics.system.project.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.AnkenOrderEntity;
import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.Entity.PostCodeEntity;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.exception.CustomException;
import logistics.system.project.common.form.AnkenTorokuForm;
import logistics.system.project.common.service.AnkenDetailService;
import logistics.system.project.common.service.AnkenModifyService;
import logistics.system.project.common.service.PostCodeService;
import logistics.system.project.ninushi.form.NohinForm;
import logistics.system.project.ninushi.form.SyukaForm;
import logistics.system.project.ninushi.form.TruckForm;
import logistics.system.project.utility.AnkenPicUtils;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AnkenController extends BaseController {

	@Value("#{configProperties['anken_not_editable']}")
	private String anken_not_editable;
	//
	@Value("#{configProperties['anken.pic.webroot.path.tmp']}")
	private String anken_pic_webroot_path_tmp;
	@Value("#{configProperties['anken.pic.upload.path.tmp']}")
	private String anken_pic_upload_path_tmp;
	@Value("#{configProperties['anken.pic.root.path']}")
	private String anken_pic_root_path;
	@Value("#{configProperties['anken.pic.allowed.ext']}")
	private String anken_pic_upload_allowd_ext;
	@Value("#{configProperties['anken.pic.size.max']}")
	private String anken_pic_upload_size_max;

	@Value("#{configProperties['syuka.time']}")
	private String syuka_time;

	private static boolean getPicLock = false;

	@RequestMapping(value = "ankenToroku")
	public ModelAndView inputCaseInfo(
			// @RequestBody AnkenTorokuForm ankenTorokuForm,
			@ModelAttribute("ankenTorokuForm") @Valid AnkenTorokuForm ankenTorokuForm,
			BindingResult ankenerrors, HttpServletRequest request) {
		HashMap<String, Object> results = new HashMap<String, Object>();

		boolean updateFlag = request.getParameter("updateFlag") == null ? false
				: "true".equalsIgnoreCase(request.getParameter("updateFlag"));
		request.setAttribute("updateFlag", updateFlag);

		if (Constants.GYOMU_SB_TRAIL.equals(userSession.getGyomuSb())) {
			if (StringUtils.isBlank(ankenTorokuForm.getSeikyuKsCd())) {
				ankenerrors.rejectValue("seikyuKsCd", "field.not.selected",
						new String[] { "荷主" }, "荷主が選択されていません。");
			}

//			if (StringUtils.isBlank(ankenTorokuForm.getShihraiKsCd())) {
//				ankenerrors.rejectValue("shihraiKsCd", "field.not.selected",
//						new String[] { "運送" }, "運送が選択されていません。");
//			}
		}

		if (Constants.GYOMU_SB_NINUSHI.equals(userSession.getGyomuSb()) &&
				!ankenerrors.hasErrors()) {

			String minSyukaDate = "";
			String minNohinDate = "";

			String orderDate = ComUtils.convertJapanDate(
					StringUtils.trimToEmpty(ankenTorokuForm.getOrderDate()), "yyyy/MM/dd (E)",
					"yyyyMMdd");
			if (ComUtils.getSysDate().compareTo(orderDate) > 0) {
				ankenerrors.rejectValue("orderDate",
						"date.before.more.than.after", new String[] { "受注期限" },
						"受注期限に過去の日付を指定しています。");
			}

			for (SyukaForm syukaForm : ankenTorokuForm.getSyukaList()) {
				String syukaDay = ComUtils.convertJapanDate(
						StringUtils.trimToEmpty(syukaForm.getSyukaDay()), "yyyy/MM/dd (E)", "yyyyMMdd");
				if (orderDate.compareTo(syukaDay) > 0) {
					ankenerrors.rejectValue("orderDate",
							"date.before.more.than.after",
							new String[] { "集荷日" }, "受注期限が集荷日より以前の日付になっています。");
				}

				String syukaTime = StringUtils
						.isEmpty(syukaForm.getSyukaTime()) ? "" : syukaForm
						.getSyukaTime().replace(":", "");
				String syukaDate = ComUtils.convertJapanDate(
						StringUtils.trimToEmpty(syukaForm.getSyukaDay()) + syukaTime,
						"yyyy/MM/dd (E)HHmm", "yyyyMMddHHmm");

//				String syukaDate = syukaDay + syukaTime;

				minSyukaDate = "".equals(minSyukaDate) ? syukaDate
						: (minSyukaDate.compareTo(syukaDate) > 0 ? syukaDate
								: minSyukaDate);

				syuka_time = StringUtils.isEmpty(syuka_time) ? "" : syuka_time
						.replace(":", "");
				String sysDate = ComUtils.convertDate(ComUtils.getSysDate()
						+ syuka_time, "yyyyMMddHHmm", "yyyyMMddHHmm");

//				String sysDate = ComUtils.getSysDate()+ syuka_time;

				if (ComUtils.getSysDate().compareTo(syukaDay) > 0) {
					ankenerrors.rejectValue("orderDate",
							"date.before.more.than.after",
							new String[] { "集荷日" }, "集荷日が過去の日付になっています。");
				} else if (sysDate.compareTo(syukaDate) > 0) {
					ankenerrors.rejectValue("orderDate",
							"date.before.more.than.after",
							new String[] { "集荷日" }, "集荷日時が過去の時間になっています。");
				}
			}

			for (NohinForm nohinForm : ankenTorokuForm.getNohinList()) {
				String nohinDay = ComUtils.convertJapanDate(
						nohinForm.getNohinDay(), "yyyy/MM/dd (E)", "yyyyMMdd");
				String nohinTime = StringUtils
						.isEmpty(nohinForm.getNohinTime()) ? "" : nohinForm
						.getNohinTime().replace(":", "");
				String nohinDate = ComUtils.convertJapanDate(
						nohinForm.getNohinDay() + nohinTime,
						"yyyy/MM/dd (E)HHmm", "yyyyMMddHHmm");

//				String nohinDate = nohinDay + nohinTime;

				minNohinDate = "".equals(minNohinDate) ? nohinDate
						: (minNohinDate.compareTo(nohinDate) > 0 ? nohinDate
								: minNohinDate);

				syuka_time = StringUtils.isEmpty(syuka_time) ? "" : syuka_time
						.replace(":", "");
				String sysDate = ComUtils.convertDate(ComUtils.getSysDate()
						+ syuka_time, "yyyyMMddHHmm", "yyyyMMddHHmm");

//				String sysDate = ComUtils.getSysDate()+ syuka_time;

				if (ComUtils.getSysDate().compareTo(nohinDay) > 0) {
					ankenerrors.rejectValue("orderDate",
							"date.before.more.than.after",
							new String[] { "納品日" }, "納品日が過去の日付になっています。");
				} else if (sysDate.compareTo(nohinDate) > 0) {
					ankenerrors.rejectValue("orderDate",
							"date.before.more.than.after",
							new String[] { "納品日" }, "納品日時が過去の時間になっています。");
				}
			}

			if (minSyukaDate.compareTo(minNohinDate) > 0) {
				ankenerrors.rejectValue("orderDate",
						"date.before.more.than.after", new String[] { "集荷日" },
						"納品日時が集荷日時より以前の日付になっています。");
			}
		}

		if (ankenerrors.hasErrors()) {
			this.setHeader(new String[] {
					Constants.NINUSHI_ANKEN_TOROKU_TABTITLE,
					Constants.NINUSHI_ANKEN_TOROKU_PAGETITLE }, results);

			// List<NisyuEntity> nisyuList = shukaAreaService.getAllNisyuList();
			// List<NisugateEntity> nisugateList =
			// shukaAreaService.getAllNisugateList();
			// List<PrefEntity> prefList = shukaAreaService.getAllPrefList();
			// List<SyasyuEntity> syasyuList =
			// ankenListSearchService.getSyasyuList();
			// List<TruckOpEntity> truckOpList =
			// ankenListSearchService.getTruckOpList();
			//
			// ankenTorokuForm.setPrefList(prefList);
			// ankenTorokuForm.setNisyuList(nisyuList);
			// ankenTorokuForm.setNisugateList(nisugateList);
			// ankenTorokuForm.setSyasyuList(syasyuList);
			// ankenTorokuForm.setTruckOpList(truckOpList);

			ankenTorokuForm.setPrefList(Constants.MAST_PREF_LIST);
			ankenTorokuForm.setNisyuList(Constants.MAST_NISYU_LIST);
			ankenTorokuForm.setNisugateList(Constants.MAST_NISUGATE_LIST);
			ankenTorokuForm.setSyasyuList(Constants.MAST_SYASYU_LIST);
			ankenTorokuForm.setTruckOpList(Constants.MAST_TRUCKOP_LIST);

			results.put(Constants.SHOW_ERROR_MESS_FLAG, true);
			return new ModelAndView("anken_edit", results);
		} else {
			results.put(Constants.SHOW_ERROR_MESS_FLAG, false);
		}

		this.setHeader(new String[] {
				Constants.NINUSHI_ANKEN_TOROKU_CONFIRM_TABTITLE,
				Constants.NINUSHI_ANKEN_TOROKU_CONFIRM_PAGETITLE }, results);
		return new ModelAndView("anken_confirm", results);
	}

	@RequestMapping(value = "ankenTorokuConfirm")
	public ModelAndView caseInfoConfirm(
			@ModelAttribute("ankenTorokuForm") AnkenTorokuForm ankenTorokuForm,
			HttpServletRequest request) {

		HashMap<String, Object> results = new HashMap<String, Object>();
		this.setHeader(new String[] {
				Constants.NINUSHI_ANKEN_TOROKU_COMPLETE_TABTITLE,
				Constants.NINUSHI_ANKEN_TOROKU_COMPLETE_PAGETITLE }, results);

		boolean updateFlag = request.getParameter("updateFlag") == null ? false
				: "true".equalsIgnoreCase(request.getParameter("updateFlag"));

		request.setAttribute("updateFlag", updateFlag);
		results.put("updateFlg", updateFlag);

		UserEntity userEntity = (UserEntity) session.getAttribute("user");


		if(Constants.GYOMU_SB_NINUSHI.equals(this.userSession.getGyomuSb())) {
			for(TruckForm truckForm : ankenTorokuForm.getTruckList()) {
				truckForm.setOrderMn(truckForm.getYosanMn());
			}
		}

		if(Constants.GYOMU_SB_NINUSHI.equals(userEntity.getGyomuSb())) {
			ankenTorokuForm.setSeikyuKsCd(userEntity.getCompanyCd());
		}

		String userId = userEntity.getUserId();
//		String ankenId = ankenTorokuForm.getAnkenId();
//		String ankenNo = ankenTorokuForm.getAnkenNo();

		List<AnkenOrderEntity> ankenOrderList = null;
		List<String> ankenNoList = new ArrayList<String>();
		if (updateFlag) {

			try {
				ankenOrderList = ankenModifyService.updateAnken(ankenTorokuForm, userSession);
				for(AnkenOrderEntity ankenOrder : ankenOrderList) {
					ankenNoList.add(ComUtils.formatAnkenNo(ankenOrder.getAnkenNo()));
				}

//				HashMap<String, List<String>> ankenNoMap = new HashMap<String, List<String>>();
//				ankenNoMap.put("ankenNoList", ankenNoList);
//
//				results.put("result", ankenNoMap);
//				return new ModelAndView("anken_complete", results);
			}
			catch(CustomException e) {
				results.put("showErrorMessFlag", true);
				results.put("errormessage", e.getMessage());
				return new ModelAndView("anken_confirm", results);
			}
			catch(Exception ex){
				results.put("showErrorMessFlag", true);
				results.put("errormessage", "案件更新に失敗しました。");
				return new ModelAndView("anken_confirm", results);
			}
			// int count = ankenModifyService.getCountNot10(ankenId, updateDt);
			// if(count == 1) {
			// try {
			//
			// ankenModifyService.deleteAnken(ankenId, updateDt);
			// }
			// catch (Exception e) {
			// results.put("showErrorMessFlag", true);
			// results.put("errormessage", "案件削除失敗しました。");
			// return new ModelAndView("anken_confirm", results);
			// }
			// }
			// else {
			// results.put("showErrorMessFlag", true);
			// results.put("errormessage", ComUtils.formatAnkenNo(ankenNo) +
			// this.anken_not_editable);
			// return new ModelAndView("anken_confirm", results);
			// }

		}

		else {

			String current = ComUtils.getSysDateTime();
			ankenTorokuForm.setCreateId(userId);
			ankenTorokuForm.setUpdateId(userId);
			ankenTorokuForm.setCreateDt(current);
			ankenTorokuForm.setUpdateDt(current);

//			if (Constants.GYOMU_SB_NINUSHI.equals(this.userSession.getGyomuSb())) {
//				for (TruckForm truckForm : ankenTorokuForm.getTruckList()) {
//					truckForm.setOrderMn(truckForm.getYosanMn());
//				}
//			}
//
//			if (Constants.GYOMU_SB_NINUSHI.equals(userEntity.getGyomuSb())) {
//				ankenTorokuForm.setSeikyuKsCd(userEntity.getCompanyCd());
//			}

			StringBuffer startSeq = new StringBuffer();
			startSeq = startSeq.append(this.userSession.getCompanyCd());

			String sysData = ComUtils.getSysDate().substring(2);
			startSeq = startSeq.append(sysData);

			String maxSeq = ankenDetailService.getMaxSeqNo(startSeq.toString());
			int nowSeq = maxSeq == null ? 1 : Integer.valueOf(maxSeq) + 1;
			ankenTorokuForm.setAnkenNo(userEntity.getCompanyCd()+ Constants.CONNECT_DASH + sysData + String.format("%03d", nowSeq) + "1"+ Constants.CONNECT_DASH + "01");
			ankenTorokuForm.setAnkenId(userEntity.getCompanyCd() + sysData+ String.format("%03d", nowSeq));

			ankenOrderList = ankenModifyService.insertAnken(ankenTorokuForm, userSession);
			for (AnkenOrderEntity ankenOrder : ankenOrderList) {
				ankenNoList.add(ComUtils.formatAnkenNo(ankenOrder.getAnkenNo()));
			}

//			HashMap<String, List<String>> ankenNoMap = new HashMap<String, List<String>>();
//			ankenNoMap.put("ankenNoList", ankenNoList);
//
//			results.put("result", ankenNoMap);
//			return new ModelAndView("anken_complete", results);
		}

		AnkenPicUtils.dealPic(ankenTorokuForm.getPicTmpNm1(), ankenTorokuForm.getAnkenId(), 1,
				ankenTorokuForm.getPicChg1());
		AnkenPicUtils.dealPic(ankenTorokuForm.getPicTmpNm2(), ankenTorokuForm.getAnkenId(), 2,
				ankenTorokuForm.getPicChg2());
		AnkenPicUtils.dealPic(ankenTorokuForm.getPicTmpNm3(), ankenTorokuForm.getAnkenId(), 3,
				ankenTorokuForm.getPicChg3());

		HashMap<String, List<String>> ankenNoMap = new HashMap<String, List<String>>();
		ankenNoMap.put("ankenNoList", ankenNoList);

		results.put("result", ankenNoMap);

		return new ModelAndView("anken_complete", results);
	}

	@RequestMapping(value = "beforeUpdate", method = RequestMethod.GET)
	public ModelAndView beforeUpdate(@RequestParam("ankenNo") String ankenNo,
			@RequestParam("updateDt") String updateDt,
			RedirectAttributes redirectAttributes) {

		ankenNo = ankenNo.replaceAll("-", "");

		String ankenId = StringUtils.substring(ankenNo, 0, 15);
		// String truckNo = StringUtils.substring(ankenNo, 15, 16);
		// String renban = StringUtils.substring(ankenNo, 16, 18);

		int count = ankenModifyService.getCountNot10(ankenId, updateDt);

		if (count == 1) {
			AnkenTorokuForm ankenTorokuForm = ankenModifyService
					.getAnkenEditable(ankenNo);

			if (StringUtils.isNotBlank(ankenTorokuForm.getPicNm1())) {
				String tmpName = new Date().getTime() + "1.jpg";
				AnkenPicUtils.copyPicToTmp(ankenTorokuForm.getAnkenId(), tmpName, 1);
				ankenTorokuForm.setPicTmpNm1(tmpName);
			}

			if (StringUtils.isNotBlank(ankenTorokuForm.getPicNm2())) {
				String tmpName = new Date().getTime() + "2.jpg";
				AnkenPicUtils.copyPicToTmp(ankenTorokuForm.getAnkenId(), tmpName, 2);
				ankenTorokuForm.setPicTmpNm2(tmpName);
			}

			if (StringUtils.isNotBlank(ankenTorokuForm.getPicNm3())) {
				String tmpName = new Date().getTime() + "3.jpg";
				AnkenPicUtils.copyPicToTmp(ankenTorokuForm.getAnkenId(), tmpName, 3);
				ankenTorokuForm.setPicTmpNm3(tmpName);
			}

			redirectAttributes.addAttribute("updateFlag", true);
			redirectAttributes.addFlashAttribute("ankenTorokuForm",
					ankenTorokuForm);
			return new ModelAndView("redirect:createcase");
		} else {
			redirectAttributes.addFlashAttribute("showErrorMessFlag", true);
			redirectAttributes.addFlashAttribute("errormessage",
					ComUtils.formatAnkenNo(ankenNo) + this.anken_not_editable);
			return new ModelAndView("redirect:ankenList");
		}
	}

	@RequestMapping(value = "ankenback", method = RequestMethod.POST)
	public ModelAndView back(
			@ModelAttribute("ankenTorokuForm") AnkenTorokuForm ankenTorokuForm,
			Boolean updateFlag, RedirectAttributes redirectAttributes) {
		HashMap<String, Object> results = new HashMap<String, Object>();
		this.setHeader(new String[] { Constants.NINUSHI_ANKEN_TOROKU_TABTITLE,
				Constants.NINUSHI_ANKEN_TOROKU_PAGETITLE }, results);

		results.put("updateFlag", updateFlag);
		results.put("ankenTorokuForm", ankenTorokuForm);

		List<NisyuEntity> nisyuList = Constants.MAST_NISYU_LIST;
		List<NisugateEntity> nisugateList = Constants.MAST_NISUGATE_LIST;
		List<PrefEntity> prefList = Constants.MAST_PREF_LIST;
		List<SyasyuEntity> syasyuList = Constants.MAST_SYASYU_LIST;
		List<TruckOpEntity> truckOpList = Constants.MAST_TRUCKOP_LIST;

		ankenTorokuForm.setPrefList(prefList);
		ankenTorokuForm.setNisyuList(nisyuList);
		ankenTorokuForm.setNisugateList(nisugateList);
		ankenTorokuForm.setSyasyuList(syasyuList);
		ankenTorokuForm.setTruckOpList(truckOpList);

		return new ModelAndView("anken_edit", results);
	}

	@RequestMapping(value = "ankenUploadPic", method = RequestMethod.POST)
	public ModelAndView ankenUploadPic(
			@RequestParam("picFile") MultipartFile file,
			@ModelAttribute("ankenTorokuForm") AnkenTorokuForm ankenTorokuForm,
			@RequestParam("currPic") String currPic) {

		String path = AnkenPicUtils.getTmpFileRootAbs();

		File rootDirFile = new File(path);
		if (!rootDirFile.exists()) {
			rootDirFile.mkdirs();
		}

		String picUrl = "";

		String picNm = "";

		String resCd = "1";

		String picTmpNm = new Date().getTime() + ".jpg";

		if (!file.isEmpty()) {
			try {

				Integer maxSize = Integer.parseInt(anken_pic_upload_size_max);

				if (file.getSize() > maxSize) {
					resCd = "2";
				}

				String orgName = file.getOriginalFilename();
				String fileExt = orgName
						.substring(orgName.lastIndexOf(".") + 1);

				String[] exts = anken_pic_upload_allowd_ext.split(",");

				boolean find = false;
				for (String ext : exts) {
					if (ext.equalsIgnoreCase(fileExt)) {
						find = true;
						break;
					}
				}

				if (!find) {
					resCd = "3";
				}

				if ("1".equals(resCd)) {

					File tmpFile = new File(AnkenPicUtils.getTmpFilePath(picTmpNm));

					file.transferTo(tmpFile);

					// picUrl = thePath.replaceAll("\\" + File.separator, "/") +
					// fileNewName;
					picNm = file.getOriginalFilename();
				}
			} catch (Exception e) {
				resCd = "0";
			}
		}
		HashMap<String, Object> results = new HashMap<String, Object>();

		results.put("resCd", resCd);

		results.put("currPic", currPic);

		results.put("picNm", picNm);

		results.put("picTmpNm", picTmpNm);

		results.put("picPath", picUrl);
		return new ModelAndView("pic_upload_result", results);
	}

	@RequestMapping(value = "ankenTmpPic/{picTmpNm}_{idx}", method = RequestMethod.GET)
	public ModelAndView getAnkenUploadPicTmp(
			@PathVariable("picTmpNm") String picTmpNm,
			@PathVariable("idx") String idx) throws IOException {
		String tmpFilePath = AnkenPicUtils.getTmpFilePath(picTmpNm);
		showPic(tmpFilePath);

		return null;
	}

	@RequestMapping(value = "ankenPic/{ankenId}_{idx}", method = RequestMethod.GET)
	public ModelAndView getAnkenPic(@PathVariable("ankenId") String ankenId,
			@PathVariable("idx") String idx) throws IOException {
		String filePath = AnkenPicUtils.getDestFilePath(ankenId, Integer.parseInt(idx));
		showPic(filePath);

		return null;
	}

	private void showPic(String filePath) throws IOException {
		try {
			while (getPicLock) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			getPicLock = true;

			File file = new File(filePath);

			if (!file.exists()) {
				filePath = request.getServletContext().getRealPath("/")
						+ "resources" + File.separator + "image"
						+ File.separator + "noPicture.jpg";
			}

			request.setCharacterEncoding("UTF-8");
			java.io.InputStream bis = null;
			java.io.OutputStream bos = null;

			try {
				long fileLength = new File(filePath).length();
				response.setContentType("octec-stream");
				response.setHeader("Content-Length", String.valueOf(fileLength));
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "No-cache");
				response.setDateHeader("Expires", 0);
				bis = new FileInputStream(filePath);
				bos = response.getOutputStream();
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.flush();
				bos.close();
			}

		} finally {
			getPicLock = false;
		}
	}

	@RequestMapping(value = "getPostCode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView getPostCodeInfo() {
		String postCode = request.getParameter("postCode");
		PostCodeEntity postCodeEntity = postCodeService
				.getPostCodeInfo(postCode);

		JSONObject json;
		synchronized(JSONObject.class){
			 json = JSONObject.fromObject(postCodeEntity);
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

	@Autowired
	@Qualifier("ankenModifyService")
	private AnkenModifyService ankenModifyService;

	@Autowired
	@Qualifier("postCodeService")
	private PostCodeService postCodeService;

	@Autowired
	@Qualifier("ankenDetailService")
	private AnkenDetailService ankenDetailService;

	// @Autowired
	// @Qualifier("ankenDetailService")
	// private AnkenDetailService ankenDetailService;

	// @Autowired
	// @Qualifier("areaService")
	// private ShukaAreaService shukaAreaService;

	// @Autowired
	// @Qualifier("ankenListSearchService")
	// private AnkenListSearchService ankenListSearchService;

}
