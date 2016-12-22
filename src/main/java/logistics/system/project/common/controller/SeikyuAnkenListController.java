package logistics.system.project.common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.Entity.SeikyuAnkenEntity;
import logistics.system.project.common.form.SeikyuAnkenListForm;
import logistics.system.project.common.form.UpdateInfo;
import logistics.system.project.common.parameterClass.SeikyuAnkenListParameter;
import logistics.system.project.common.service.MemberListSearchService;
import logistics.system.project.common.service.SeikyuAnkenListService;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;
import logistics.system.project.utility.DateUtils;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SeikyuAnkenListController extends BaseController {

	@Autowired
	@Qualifier("seikyuAnkenListService")
	private SeikyuAnkenListService seikyuAnkenListService;

	@Autowired
	@Qualifier("memberListSearchService")
	private MemberListSearchService memberListSearchService;

	@Value("#{configProperties['SeikyuKikanJogen']}")
	private int seikyuKikanJogen;

	@Value("#{configProperties['trail.company.cd']}")
	private String trailCompanyCd;

	@RequestMapping(value = "initSeikyuAnkenList", method = RequestMethod.GET)
	public ModelAndView initSeikyuAnkenList() {
		HashMap<String, Object> results = new HashMap<String, Object>();

		SeikyuAnkenListForm seikyuAnkenListForm = null;
		if (!Constants.GYOMU_SB_TRAIL.equals(userSession.getGyomuSb())) {
			seikyuAnkenListForm = new SeikyuAnkenListForm();
			seikyuAnkenListForm.setKigyo(userSession.getCompanyCd());
			seikyuAnkenListForm.setRyoritu(userSession.getRyoritu());
			seikyuAnkenListForm.setSimbSb(userSession.getSimbSb());
			seikyuAnkenListForm.setSihrSt(userSession.getSihrSt());
		}

		session.setAttribute("seikyuAnkenListForm", seikyuAnkenListForm);

		this.setHeader(new String[] { Constants.SEIKYU_KANRI_TABTITLE,
				Constants.SEIKYU_KANRI_PAGETITLE }, results);
		return new ModelAndView("seikyu_anken_list", results);
	}

	@RequestMapping(value = "searchSeikyuAnkenList", method = RequestMethod.POST)
	public ModelAndView searchSeikyuAnkenList(
			@ModelAttribute("seikyuAnkenListForm") @Valid SeikyuAnkenListForm seikyuAnkenListForm,
			BindingResult errors, RedirectAttributes attr) {

		HashMap<String, Object> results = new HashMap<String, Object>();
		seikyuAnkenListForm.setSeikyuAnkenList(new ArrayList<SeikyuAnkenEntity>());

		String syukaDayFr = "";
		String syukaDayTo = "";
		if (!StringUtils.isBlank(seikyuAnkenListForm.getSyukaDayFr()) && !StringUtils.isBlank(seikyuAnkenListForm.getSyukaDayTo())) {

			syukaDayFr = StringUtils.substring(seikyuAnkenListForm.getSyukaDayFr(), 0, 10)
					.replaceAll("/", "");
			syukaDayTo = StringUtils.substring(seikyuAnkenListForm.getSyukaDayTo(), 0, 10)
					.replaceAll("/", "");

			if (syukaDayFr.compareTo(syukaDayTo) > 0) {
				errors.rejectValue("syukaDayTo", "", new String[] {"対象期間（TO)"}, "対象期間（TO)より対象期間（FROM)は大きいです。");
			}

			Calendar calFr = Calendar.getInstance();
			calFr.setTime(DateUtils.strToDate(syukaDayFr, "yyyyMMdd"));
			Calendar calTo = Calendar.getInstance();
			calTo.setTime(DateUtils.strToDate(syukaDayTo, "yyyyMMdd"));
			calFr.add(Calendar.DATE, seikyuKikanJogen);

			if (calFr.before(calTo)) {
				errors.rejectValue("syukaDayTo", "", new String[] {"対象期間（TO)"}, "請求対象期間は上限値(" + seikyuKikanJogen + ")を超えます。");
			}
		}

		if (errors.hasErrors()) {
			results.put(Constants.SHOW_ERROR_MESS_FLAG, true);
		} else {
			SeikyuAnkenListParameter seikyuAnkenListParameter = new SeikyuAnkenListParameter();
			seikyuAnkenListParameter.setKigyo(seikyuAnkenListForm.getKigyo().replaceAll("-", ""));
			seikyuAnkenListParameter.setSyukaDayFr(String.valueOf(syukaDayFr));
			seikyuAnkenListParameter.setSyukaDayTo(String.valueOf(syukaDayTo));
			seikyuAnkenListParameter.setGyomuSb(userSession.getGyomuSb());
			seikyuAnkenListParameter.setRyoritu(seikyuAnkenListForm.getRyoritu());
			if (seikyuAnkenListForm.getStatus() != null) {
				List<String> statusList = new ArrayList<String>();
				statusList.addAll(Arrays.asList(seikyuAnkenListForm.getStatus()));
				if (statusList.contains(Constants.ANKEN_STATUS_20)) {
					statusList.add(Constants.ANKEN_STATUS_30);
					statusList.add(Constants.ANKEN_STATUS_40);
				}
				seikyuAnkenListParameter.setStatusList(statusList);
			} else {
				List<String> statusList = new ArrayList<String>();
				statusList.add(Constants.ANKEN_STATUS_20);
				statusList.add(Constants.ANKEN_STATUS_30);
				statusList.add(Constants.ANKEN_STATUS_40);
				statusList.add(Constants.ANKEN_STATUS_90);
				seikyuAnkenListParameter.setStatusList(statusList);
			}

			List<SeikyuAnkenEntity> seikyuAnkenList = seikyuAnkenListService.getSeikyuAnkenList(seikyuAnkenListParameter, userSession);
			seikyuAnkenListForm.setSeikyuAnkenList(seikyuAnkenList);

			results.put("seikyuAnkenListForm", seikyuAnkenListForm);
		}

		this.setHeader(new String[] { Constants.SEIKYU_KANRI_TABTITLE,
				Constants.SEIKYU_KANRI_PAGETITLE }, results);
		return new ModelAndView("seikyu_anken_list", results);
	}

	@RequestMapping(value = "ryokinToroku", method = RequestMethod.POST)
	public ModelAndView ryokinToroku(
			@ModelAttribute("seikyuAnkenListForm") SeikyuAnkenListForm seikyuAnkenListForm) {

		boolean success = false;
		String sysDateTime = ComUtils.getSysDateTime() + ".0";
		try {
			success = seikyuAnkenListService.updateRyokin(seikyuAnkenListForm.getSeikyuAnkenList(), sysDateTime, userSession);
		}
		catch (Exception e) {
		}

		JSONObject json = JSONObject.fromObject(new UpdateInfo(success, sysDateTime));

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "seikyuAnkenDwl", method = RequestMethod.POST)
	public ModelAndView seikyuAnkenDownload(
			@ModelAttribute("seikyuAnkenListForm") @Valid SeikyuAnkenListForm seikyuAnkenListForm,
			BindingResult errors, RedirectAttributes attr) {

		HashMap<String, Object> results = new HashMap<String, Object>();
		seikyuAnkenListForm.setSeikyuAnkenList(new ArrayList<SeikyuAnkenEntity>());

		String syukaDayFr = "";
		String syukaDayTo = "";
		if (!StringUtils.isBlank(seikyuAnkenListForm.getSyukaDayFr()) && !StringUtils.isBlank(seikyuAnkenListForm.getSyukaDayTo())) {

			syukaDayFr = StringUtils.substring(seikyuAnkenListForm.getSyukaDayFr(), 0, 10)
					.replaceAll("/", "");
			syukaDayTo = StringUtils.substring(seikyuAnkenListForm.getSyukaDayTo(), 0, 10)
					.replaceAll("/", "");

			if (syukaDayFr.compareTo(syukaDayTo) > 0) {
				errors.rejectValue("syukaDayTo", "", new String[] {"対象期間（TO)"}, "対象期間（TO)より対象期間（FROM)は大きいです。");
			}

			Calendar calFr = Calendar.getInstance();
			calFr.setTime(DateUtils.strToDate(syukaDayFr, "yyyyMMdd"));
			Calendar calTo = Calendar.getInstance();
			calTo.setTime(DateUtils.strToDate(syukaDayTo, "yyyyMMdd"));
			calFr.add(Calendar.DATE, seikyuKikanJogen);

			if (calFr.before(calTo)) {
				errors.rejectValue("syukaDayTo", "", new String[] {"対象期間（TO)"}, "請求対象期間は上限値(" + seikyuKikanJogen + ")を超えます。");
			}
		}

		if (errors.hasErrors()) {
			results.put(Constants.SHOW_ERROR_MESS_FLAG, true);
		} else {
			SeikyuAnkenListParameter seikyuAnkenListParameter = new SeikyuAnkenListParameter();
			seikyuAnkenListParameter.setKigyo(seikyuAnkenListForm.getKigyo().replaceAll("-", ""));
			seikyuAnkenListParameter.setSyukaDayFr(String.valueOf(syukaDayFr));
			seikyuAnkenListParameter.setSyukaDayTo(String.valueOf(syukaDayTo));
			seikyuAnkenListParameter.setGyomuSb(userSession.getGyomuSb());
			seikyuAnkenListParameter.setRyoritu(seikyuAnkenListForm.getRyoritu());
			if (seikyuAnkenListForm.getStatus() != null) {
				List<String> statusList = new ArrayList<String>();
				statusList.addAll(Arrays.asList(seikyuAnkenListForm.getStatus()));
				if (statusList.contains(Constants.ANKEN_STATUS_20)) {
					statusList.add(Constants.ANKEN_STATUS_30);
					statusList.add(Constants.ANKEN_STATUS_40);
				}
				seikyuAnkenListParameter.setStatusList(statusList);
			} else {
				List<String> statusList = new ArrayList<String>();
				statusList.add(Constants.ANKEN_STATUS_20);
				statusList.add(Constants.ANKEN_STATUS_30);
				statusList.add(Constants.ANKEN_STATUS_40);
				statusList.add(Constants.ANKEN_STATUS_90);
				seikyuAnkenListParameter.setStatusList(statusList);
			}

			List<SeikyuAnkenEntity> seikyuAnkenList = seikyuAnkenListService.getSeikyuAnkenList(seikyuAnkenListParameter, userSession);
			seikyuAnkenListForm.setSeikyuAnkenList(seikyuAnkenList);

			String companyCd = seikyuAnkenListForm.getKigyo();
			if (Constants.GYOMU_SB_UNSO.equals(userSession.getGyomuSb())) {
				companyCd = trailCompanyCd;
			}

			MemberEntity member = memberListSearchService.getMemberByCd(companyCd);

			MemberEntity myMember = memberListSearchService.getMemberByCd(userSession.getCompanyCd());

			// 運賃
			Integer fareTotal = 0;
			// 高速
			Integer kosokuKbnTotal = 0;
			// サーチャージ
			Integer nenryoscKbnTotal = 0;
			// total
			Integer totalMn = 0;

			Map<String, String> totalMap = new HashMap<String, String>();

			int totalCount = 0;

			List<List<SeikyuAnkenEntity>> pages = new ArrayList<List<SeikyuAnkenEntity>>();

			Integer pageTotal = 0;
			if (seikyuAnkenList != null && seikyuAnkenList.size() > 0) {
				int index = 0 ;
				List<SeikyuAnkenEntity> pageList = null;
				for (SeikyuAnkenEntity seikyuAnken : seikyuAnkenList) {
					if (index % 21 == 0) {
						pageList = new ArrayList<SeikyuAnkenEntity>();
						pages.add(pageList);
						totalCount += 6;
					}

					index ++;

					pageList.add(seikyuAnken);

					/*【TRAIL様】の場合
						①案件ステータス＜＞キャンセルの場合：
							・荷主コード=TRAIL：　運賃＝≪トラック≫.予算金額
							・荷主コード<>TRAIL：　運賃＝≪トラック≫.予算金額*(1+請求先会社の料率)
						②案件ステータス＝キャンセルの場合：
							・運賃＝≪案件受注情報≫.キャンセル料金
					【運送】の場合
						①案件ステータス＜＞キャンセルの場合：
							・≪案件情報≫支払先会社コード＜＞NULL:：運賃＝≪トラック≫.受注金額
							・≪案件情報≫支払先会社コード＝NULL：　運賃＝≪トラック≫.受注金額*(1-運送会社の料率)
						②キャンセルの場合：≪案件受注情報≫.キャンセル料金*/

					BigDecimal fare = BigDecimal.ZERO;
					if (Constants.GYOMU_SB_TRAIL.equals(userSession.getGyomuSb())) {
						if (!Constants.ANKEN_STATUS_90.equals(seikyuAnken.getStatus())) {
							if (userSession.getCompanyCd().equals(seikyuAnken.getNinushiCd())) {
								fare = new BigDecimal(StringUtils.isBlank(seikyuAnken.getYosan())? "0" : seikyuAnken.getYosan());
							} else {
								BigDecimal yosan = new BigDecimal(StringUtils.isBlank(seikyuAnken.getYosan())? "0" : seikyuAnken.getYosan());
								fare = yosan.multiply(new BigDecimal(seikyuAnkenListParameter.getRyoritu()).add(new BigDecimal("100")).divide(new BigDecimal("100")));
							}
						} else {
							fare = new BigDecimal(StringUtils.isBlank(seikyuAnken.getCancelMn())? "0" : seikyuAnken.getCancelMn());
						}
					} else {
						if (!Constants.ANKEN_STATUS_90.equals(seikyuAnken.getStatus())) {
							if (StringUtils.isNotBlank(seikyuAnken.getShihraiKsCd())) {
								fare = new BigDecimal(StringUtils.isBlank(seikyuAnken.getYosan())? "0" : seikyuAnken.getYosan());
							} else {
								BigDecimal yosan = new BigDecimal(StringUtils.isBlank(seikyuAnken.getYosan())? "0" : seikyuAnken.getYosan());
								
								fare = yosan.multiply(new BigDecimal("100").subtract(new BigDecimal(StringUtils.isBlank(userSession.getRyoritu())? "0" : userSession.getRyoritu())).divide(new BigDecimal("100")));
							}
						} else {
							fare = new BigDecimal(StringUtils.isBlank(seikyuAnken.getCancelMn())? "0" : seikyuAnken.getCancelMn());
						}
					}
					seikyuAnken.setFare(fare.intValue() + "");
					fareTotal += fare.intValue();

					// 高速使用料金
					kosokuKbnTotal += Integer.parseInt(StringUtils.isBlank(seikyuAnken.getKosokuMn())? "0" : seikyuAnken.getKosokuMn());

					// サーチャージ
					nenryoscKbnTotal = Integer.parseInt(StringUtils.isBlank(seikyuAnken.getNenryoscMn())? "0" : seikyuAnken.getNenryoscMn());

					if (StringUtils.isNotBlank(seikyuAnken.getSyasyuCd())) {
						String scountStr = totalMap.get("SA_" + seikyuAnken.getSyasyuCd());

						if (StringUtils.isNotBlank(scountStr)) {
							scountStr = String.valueOf(Integer.parseInt(scountStr) + 1 /*Integer.parseInt(StringUtils.isNotBlank(seikyuAnken.getDaisu()) ? seikyuAnken.getDaisu() : "0")*/);
						} else {
							scountStr = "1" /*StringUtils.isNotBlank(seikyuAnken.getDaisu()) ? seikyuAnken.getDaisu() : "0"*/;
						}

						totalMap.put("SA_" + seikyuAnken.getSyasyuCd(), scountStr);
					}

					if (StringUtils.isNotBlank(seikyuAnken.getSyukaNisugataCd())) {
						String scountStr = totalMap.get("SU_" + seikyuAnken.getSyukaNisugataCd());

						if (StringUtils.isNotBlank(scountStr)) {
							scountStr = String.valueOf(Integer.parseInt(scountStr) + 1 /*Integer.parseInt(StringUtils.isNotBlank(seikyuAnken.getDaisu()) ? seikyuAnken.getDaisu() : "0")*/);
						} else {
							scountStr = "1" /*StringUtils.isNotBlank(seikyuAnken.getDaisu()) ? seikyuAnken.getDaisu() : "0"*/;
						}

						totalMap.put("SU_" + seikyuAnken.getSyukaNisugataCd(), scountStr);
					}

					totalCount += 2;
				}

//				pageTotal = seikyuAnkenList.size() / 21 + (seikyuAnkenList.size() % 21 > 20 ? 1 : 0);
			}

			if (seikyuAnkenList.size() % 21 <= 20) {
				List<SeikyuAnkenEntity> lastPageList = null;
				if (seikyuAnkenList.size() % 21 == 0) {
					lastPageList = new ArrayList<SeikyuAnkenEntity>();
				} else {
					lastPageList = pages.get(pages.size() - 1);
				}
				int count = 20 - seikyuAnkenList.size() % 21;
				for (int i = 0; i < count; i ++) {
					lastPageList.add(new SeikyuAnkenEntity());
				}

				totalCount += 20 * 2;
			}

			totalCount += 4;


			String customerNo = "";
			if (Constants.GYOMU_SB_TRAIL.equals(userSession.getGyomuSb())) {
				customerNo = seikyuAnkenListForm.getKigyo();
			}

			totalMn = fareTotal + kosokuKbnTotal + nenryoscKbnTotal;

			pageTotal = pages.size();

			Map resultMap = new HashMap();

			resultMap.put("pages", pages);
			resultMap.put("seikyuAnkenListForm", seikyuAnkenListForm);
			resultMap.put("member", member);
			resultMap.put("myMember", myMember);
			resultMap.put("sysDate", new Date());
			resultMap.put("fareTotal", fareTotal.toString());
			resultMap.put("kosokuKbnTotal", kosokuKbnTotal.toString());
			resultMap.put("nenryoscKbnTotal", nenryoscKbnTotal.toString());
			resultMap.put("totalMn", totalMn.toString());
			resultMap.put("customerNo", customerNo);
			resultMap.put("pageTotal", pageTotal);
			resultMap.put("totalMap", totalMap);
			resultMap.put("totalCount", totalCount);

			//String fileName = "請求一覧_" + seikyuAnkenListForm.getKigyo() + "_" + seikyuAnkenListForm.getSyukaDayFr() + "_" + seikyuAnkenListForm.getSyukaDayTo();
			String fileName = seikyuAnkenListForm.getKigyo() + "_" + seikyuAnkenListForm.getSyukaDayFr().substring(0, 10).replaceAll("-", "") + "_" + seikyuAnkenListForm.getSyukaDayTo().substring(0, 10).replaceAll("-", "") ;

			try {
				String templatePath = SeikyuAnkenListController.class.getClassLoader().getResource(Constants.TEPLATE_SEIKYU_ANKEN_LIST).getPath();

				POIFSFileSystem filein = new POIFSFileSystem(new FileInputStream(
						templatePath));

				HSSFWorkbook wb = new HSSFWorkbook(filein);

				HSSFSheet sheet0 = wb.getSheet("請求書");
				fillSheet0(sheet0, 0, seikyuAnkenListForm, member, myMember, totalMn);
				fillSheet0(sheet0, 1, seikyuAnkenListForm, member, myMember, totalMn);


				Sheet sheet = wb.getSheet("請求明細");

				// fill first header
				sheet.getRow(2).getCell(cd_col_idx).setCellValue(customerNo);
				sheet.getRow(1).getCell(p_col_idx + 2).setCellValue("0");
				sheet.getRow(2).getCell(p_col_idx + 2).setCellValue("1/" + pages.size());


				// fill footer
				Row row46 = sheet.getRow(46);
				Row row47 = sheet.getRow(47);
				Row row48 = sheet.getRow(48);
				Row row49 = sheet.getRow(49);
				Row[] footerRow = new Row[]{row46, row47, row48, row49};

				for (int i = 0; i < 10; i ++) {
					String index = i + 1 > 9 ? (i + 1) + "" : "0" + (i + 1);
					footerRow[1].getCell(7 + i * 2).setCellValue(StringUtils.isBlank(totalMap.get("SA_" + index)) ? 0 : Integer.parseInt(totalMap.get("SA_" + index)));
				}
				footerRow[3].getCell(7).setCellValue(StringUtils.isBlank(totalMap.get("SU_01")) ? 0 : Integer.parseInt(totalMap.get("SU_01")));
				footerRow[3].getCell(9).setCellValue(StringUtils.isBlank(totalMap.get("SU_02")) ? 0 : Integer.parseInt(totalMap.get("SU_02")));
				footerRow[3].getCell(11).setCellValue(StringUtils.isBlank(totalMap.get("SU_03")) ? 0 : Integer.parseInt(totalMap.get("SU_03")));

				footerRow[0].getCell(32).setCellValue(fareTotal);
				footerRow[1].getCell(32).setCellValue(kosokuKbnTotal);
				footerRow[2].getCell(32).setCellValue(nenryoscKbnTotal);
				footerRow[3].getCell(32).setCellValue(totalMn);

				Row row6 = sheet.getRow(6);
				Row row7 = sheet.getRow(7);
				Row[] dataRow = new Row[]{row6, row7};

				// shift footer
				if (pageTotal > 1) {
					sheet.shiftRows(46, 49, (pageTotal - 1) * 48);

//					copyRow(dataRow[0], sheet.createRow(44));
//					copyRow(dataRow[1], sheet.createRow(45));
					copyRow(dataRow[0], sheet.createRow(46));
					copyRow(dataRow[1], sheet.createRow(47));

					margItemRow(sheet, 46, false);
				}

				// when a new page, copy the header
				// header
				for (int i = 1; i < pageTotal; i ++) {
					String pageStr = (i + 1) + "/" + pageTotal;
					int hidx = i * 48;
					addFillHeader(sheet, hidx, customerNo, "0", pageStr);

					List<SeikyuAnkenEntity> pageList = pages.get(i);

					for (int j = 0; j < pageList.size(); j++) {
						SeikyuAnkenEntity seikyuAnkenEntity = pageList.get(j);
						copyRow(dataRow[0], sheet.createRow(i * 48 + 6 + j * 2));
						copyRow(dataRow[1], sheet.createRow(i * 48 + 7 + j * 2));

						margItemRow(sheet, i * 48 + 6 + j * 2, false);

						fillDataRow(sheet, i * 48 + 6 + j * 2, seikyuAnkenEntity);
					}
				}

				// fill first page data
				List<SeikyuAnkenEntity> page0List = pages.get(0);
				for (int i = 0; i < page0List.size(); i++) {
					SeikyuAnkenEntity seikyuAnkenEntity = page0List.get(i);
					fillDataRow(sheet, i * 2 + 6, seikyuAnkenEntity);
				}

				// auto calc
				wb.getCreationHelper().createFormulaEvaluator().evaluateAll();

				String contentDisposition = "attachment;filename=" + fileName + ".xls";

				response.setHeader("Content-Disposition",
						new String(contentDisposition.getBytes("UTF-8"),
								"iso8859_1"));
				response.setContentType("application/vnd.ms-excel");
				wb.write(response.getOutputStream());
			} catch (Exception e) {e.printStackTrace();
			}
//			try {
//				response.setContentType("application/pdf;charset=utf-8");
//				response.setHeader("Content-disposition", "filename="
//						+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
//				StringBuffer contentDisposition = new StringBuffer();
//				contentDisposition.append("attachment;");
//				contentDisposition.append("filename=\"");
//				contentDisposition.append(fileName + ".xls");
//				contentDisposition.append("\"");
//				response.setContentType("application/pdf;charset=utf-8");
//				response.setHeader("Content-Disposition", new String(
//						contentDisposition.toString().getBytes("UTF-8"),
//						"iso8859_1"));
//
//				Freemarker.printFile(Constants.FTL_PACKAGE,
//						Constants.TEPLATE_SEIKYU_ANKEN_LIST, resultMap,
//						response.getWriter());
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//			}
		}

		return null;
	}

	private void fillSheet0(Sheet sheet0, int pageNo, SeikyuAnkenListForm seikyuAnkenListForm, MemberEntity member, MemberEntity myMember, Integer totalMn) throws Exception {
		int ser = pageNo * 62;

		sheet0.getRow(ser + 3).getCell(7).setCellValue(member.getCompanyCd());

		sheet0.getRow(ser + 5).getCell(1).setCellValue(member.getPostCode());
		sheet0.getRow(ser + 5).getCell(17).setCellValue(ComUtils.editDate(seikyuAnkenListForm.getSyukaDayTo(), "yyyy/MM/dd (E)", "yyyy年MM月dd日"));
		sheet0.getRow(ser + 5).getCell(35).setCellValue(DateUtils.formatDate(new Date(), "yyyy年MM月dd日"));

		sheet0.getRow(ser + 6).getCell(1).setCellValue(member.getPrefNm() + member.getCityNm());
		sheet0.getRow(ser + 7).getCell(1).setCellValue(member.getAddrOther());
		sheet0.getRow(ser + 8).getCell(1).setCellValue(member.getCompanyNm() + member.getOfficeNm());
		sheet0.getRow(ser + 9).getCell(1).setCellValue(member.getKeiriNm() + "　" + member.getKeiriBs());

		sheet0.getRow(ser + 8).getCell(27).setCellValue(myMember.getCompanyNm() + myMember.getOfficeNm());
		sheet0.getRow(ser + 9).getCell(28).setCellValue(myMember.getPostCode());
		sheet0.getRow(ser + 10).getCell(28).setCellValue(myMember.getPrefNm() + myMember.getCityNm());
		sheet0.getRow(ser + 11).getCell(28).setCellValue(myMember.getAddrOther());
		sheet0.getRow(ser + 12).getCell(28).setCellValue("TEL  " + myMember.getCompanyTel() + "  FAX  " + myMember.getCompanyFax());

		sheet0.getRow(ser + 19).getCell(5).setCellValue(myMember.getCompanyNm() + myMember.getOfficeNm());
		sheet0.getRow(ser + 19).getCell(20).setCellValue(myMember.getGinkouCd());
		sheet0.getRow(ser + 19).getCell(23).setCellValue(myMember.getGinkouNm());
		sheet0.getRow(ser + 19).getCell(33).setCellValue(myMember.getKozaSr());
		sheet0.getRow(ser + 20).getCell(20).setCellValue(myMember.getSitenCd());
		sheet0.getRow(ser + 20).getCell(23).setCellValue(myMember.getSitenNm());
		sheet0.getRow(ser + 20).getCell(33).setCellValue(myMember.getKozaNo());

		sheet0.getRow(ser + 22).getCell(11).setCellValue(totalMn);
	}


	private static final int cd_col_idx = 5;
	private static final int cd_col_conut = 4;
	private static final int p_col_idx = 51;
	private static final int p_col_conut = 2;
	private static final int pv_col_conut = 3;
	private static final int H_col_idx = 19;
	private static final int H_col_count = 17;

	private static void margItemRow(Sheet sheet, int rowIdx, boolean isHeader) {
		int nextIdx = rowIdx + 1;
		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx, //first row (0-based)
				nextIdx, //last row  (0-based)
				1, //first column (0-based)
				6  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx, //first row (0-based)
				rowIdx, //last row  (0-based)
				7, //first column (0-based)
				10  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				nextIdx, //first row (0-based)
				nextIdx, //last row  (0-based)
				7, //first column (0-based)
				10  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx, //first row (0-based)
				rowIdx, //last row  (0-based)
				11, //first column (0-based)
				18  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				nextIdx, //first row (0-based)
				nextIdx, //last row  (0-based)
				11, //first column (0-based)
				18  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx, //first row (0-based)
				rowIdx, //last row  (0-based)
				19, //first column (0-based)
				26  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				nextIdx, //first row (0-based)
				nextIdx, //last row  (0-based)
				19, //first column (0-based)
				26  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx, //first row (0-based)
				rowIdx, //last row  (0-based)
				27, //first column (0-based)
				28  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				nextIdx, //first row (0-based)
				nextIdx, //last row  (0-based)
				27, //first column (0-based)
				28  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx, //first row (0-based)
				rowIdx, //last row  (0-based)
				29, //first column (0-based)
				30  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				nextIdx, //first row (0-based)
				nextIdx, //last row  (0-based)
				29, //first column (0-based)
				30  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx, //first row (0-based)
				rowIdx, //last row  (0-based)
				31, //first column (0-based)
				36  //last column  (0-based)
		));

		if (isHeader) {
			sheet.addMergedRegion(new CellRangeAddress(
					nextIdx, //first row (0-based)
					nextIdx, //last row  (0-based)
					31, //first column (0-based)
					36  //last column  (0-based)
			));
		} else {
			sheet.addMergedRegion(new CellRangeAddress(
					nextIdx, //first row (0-based)
					nextIdx, //last row  (0-based)
					31, //first column (0-based)
					33  //last column  (0-based)
			));

			sheet.addMergedRegion(new CellRangeAddress(
					nextIdx, //first row (0-based)
					nextIdx, //last row  (0-based)
					34, //first column (0-based)
					36  //last column  (0-based)
			));
		}

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx, //first row (0-based)
				rowIdx, //last row  (0-based)
				37, //first column (0-based)
				55  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				nextIdx, //first row (0-based)
				nextIdx, //last row  (0-based)
				37, //first column (0-based)
				55  //last column  (0-based)
		));
	}

	private  void margItemHeader(Sheet sheet, int rowIdx) {
		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx + 1, //first row (0-based)
				rowIdx + 2, //last row  (0-based)
				H_col_idx, //first column (0-based)
				H_col_idx + H_col_count - 1  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx + 2, //first row (0-based)
				rowIdx + 2, //last row  (0-based)
				cd_col_idx, //first column (0-based)
				cd_col_idx + cd_col_conut - 1  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx + 1, //first row (0-based)
				rowIdx + 1, //last row  (0-based)
				p_col_idx, //first column (0-based)
				p_col_idx + p_col_conut - 1  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx + 1, //first row (0-based)
				rowIdx + 1, //last row  (0-based)
				p_col_idx + 2, //first column (0-based)
				p_col_idx + 2 + pv_col_conut - 1  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx + 2, //first row (0-based)
				rowIdx + 2, //last row  (0-based)
				p_col_idx, //first column (0-based)
				p_col_idx + p_col_conut - 1  //last column  (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(
				rowIdx + 2, //first row (0-based)
				rowIdx + 2, //last row  (0-based)
				p_col_idx + 2, //first column (0-based)
				p_col_idx + 2 + pv_col_conut - 1  //last column  (0-based)
		));

		margItemRow(sheet, rowIdx + 4, true);
	}

	private void addFillHeader(Sheet sheet, int hidx, String customCd, String no, String page) {
		Row row0 = sheet.getRow(0);
		Row row1 = sheet.getRow(1);
		Row row2 = sheet.getRow(2);
		Row row3 = sheet.getRow(3);
		Row row4 = sheet.getRow(4);
		Row row5 = sheet.getRow(5);

		Row[] header = new Row[]{row0, row1, row2, row3, row4, row5};

		for (int i = 0 ; i < 6; i ++) {
			copyRow(header[i], sheet.createRow(hidx + i));
		}
		margItemHeader(sheet, hidx);

		sheet.getRow(hidx + 2).getCell(cd_col_idx).setCellValue(customCd);

		sheet.getRow(hidx + 1).getCell(p_col_idx + 2).setCellValue(no);

		sheet.getRow(hidx + 2).getCell(p_col_idx + 2).setCellValue(page);
	}

	private void fillDataRow(Sheet sheet, int rowIndex, SeikyuAnkenEntity entity) {
		if (StringUtils.isBlank(entity.getAnkenNo())) {
			return;
		}

		Row rowFr = sheet.getRow(rowIndex);
		Row rowSc = sheet.getRow(rowIndex + 1);

		rowFr.getCell(1).setCellValue(entity.getAnkenNo() == null ? "" : entity.getAnkenNo());
		rowFr.getCell(7).setCellValue(entity.getSyukaDay() == null ? "" : entity.getSyukaDay());
		rowFr.getCell(11).setCellValue(entity.getSyukaNm() == null ? "" : entity.getSyukaNm());
		rowFr.getCell(19).setCellValue(entity.getSyukaBasho() == null ? "" : entity.getSyukaBasho());
		rowFr.getCell(27).setCellValue(entity.getSyukaNisugata() == null ? "" : entity.getSyukaNisugata());
		rowFr.getCell(29).setCellValue(entity.getSyasyuNm() == null ? "" : entity.getSyasyuNm());
		rowFr.getCell(31).setCellValue(StringUtils.isBlank(entity.getFare()) ? 0 : Integer.parseInt(entity.getFare()));
		rowFr.getCell(37).setCellValue(entity.getSyukaBiko() == null ? "" : entity.getSyukaBiko());

		rowSc.getCell(7).setCellValue(entity.getNohinDay() == null ? "" : entity.getNohinDay());
		rowSc.getCell(11).setCellValue(entity.getNohinNm() == null ? "" : entity.getNohinNm());
		rowSc.getCell(19).setCellValue(entity.getNohinBasho() == null ? "" : entity.getNohinBasho());
		rowSc.getCell(27).setCellValue(StringUtils.isBlank(entity.getKosu()) ? 0 : Integer.parseInt(entity.getKosu()));
		rowSc.getCell(29).setCellValue(entity.getZyuryo() == null ? "" : entity.getZyuryo());
		rowSc.getCell(31).setCellValue(StringUtils.isBlank(entity.getKosokuMn()) ? 0 : Integer.parseInt(entity.getKosokuMn()));
		rowSc.getCell(34).setCellValue(StringUtils.isBlank(entity.getNenryoscMn()) ? 0 : Integer.parseInt(entity.getNenryoscMn()));
		rowSc.getCell(37).setCellValue(entity.getNohinBiko() == null ? "" : entity.getNohinBiko());
	}

	private void copyRow(Row rowFrom, Row rowTo) {
		Cell cellFrom = null;
		Cell cellTo = null;

		rowTo.setHeight(rowFrom.getHeight());
		for (int j = 0; j < rowFrom.getLastCellNum(); j++) {
			cellFrom = rowFrom.getCell(j);
			if (null == cellFrom){
				continue;
			}

			cellTo = rowTo.createCell(j);
			cellTo.setCellStyle(cellFrom.getCellStyle());
			cellTo.setCellType(cellFrom.getCellType());

			if(Cell.CELL_TYPE_STRING == cellFrom.getCellType()){
				cellTo.setCellValue(cellFrom.getStringCellValue());
			}else if(Cell.CELL_TYPE_NUMERIC == cellFrom.getCellType()){
				cellTo.setCellValue(cellFrom.getNumericCellValue());
			}
		}
	}

}
