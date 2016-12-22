package logistics.system.project.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.AnkenDetailEntity;
import logistics.system.project.common.Entity.MemberEntity;
import logistics.system.project.common.Entity.NohinEntity;
import logistics.system.project.common.Entity.SyukaEntity;
import logistics.system.project.common.Entity.TruckEntity;
import logistics.system.project.common.Entity.UserEntity;
import logistics.system.project.common.form.AnkenDetailForm;
import logistics.system.project.common.service.AnkenDetailService;
import logistics.system.project.common.service.MemberListSearchService;
import logistics.system.project.common.service.PersonalService;
import logistics.system.project.pdf.PdfDocumentGenerator;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;
import logistics.system.project.utility.Freemarker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WaybillController extends BaseController {
	@Value("#{configProperties['export.pdf.waybill.path']}")
	private String exportPath;

	@Value("#{configProperties['trail.company.cd']}")
	private String trailCompanyCd;

	@Value("#{configProperties['consumption.tax']}")
	private String consumptionTax;


	@Autowired
	@Qualifier("ankenDetailService")
	private AnkenDetailService ankenDetailService;

	@Autowired
	@Qualifier("personalService")
	private PersonalService personalService;

	@Autowired
	@Qualifier("memberListSearchService")
	private MemberListSearchService memberListSearchService;

//	@Autowired
//	@Qualifier("truckDao")
//	private TruckDao truckDao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="expAnkenPdf")
	public ModelAndView export(@RequestParam("ankenNo") String ankenNo, HttpServletResponse response) throws Exception {
		DecimalFormat def = new DecimalFormat("#,###");
		
		AnkenDetailForm ankenDetailForm = ankenDetailService
				.getAnkenDetail(ankenNo);

		if (ankenDetailForm != null) {
			File root = new File(exportPath);

			if (!root.exists() || !root.isDirectory()) {
				root.mkdirs();
			}

			String fileName = ankenNo + "_" + new Date().getTime() + ".pdf";

			AnkenDetailEntity ankenDetail = ankenDetailForm.getAnkenDetail();

			// [運送委託業者]名称
			//【荷主、TRAIL様】が印刷する場合、≪案件情報≫の「荷主コード」の会社情報を印刷する
			//【運送】が印刷する場合、TRAIL様の会社情報を印刷する(TRAIL様の会社コードは設定ファイルで指定する）

			//UserEntity ninushi = personalService.getUserByCd(ankenDetail.getNinushiCd());
			MemberEntity ninMember = new MemberEntity();
			if (userSession.getGyomuSb().equals(Constants.GYOMU_SB_TRAIL) || userSession.getGyomuSb().equals(Constants.GYOMU_SB_NINUSHI)) {
				ninMember = memberListSearchService.getMemberByCd(ankenDetail.getNinushiCd());
			} else if(userSession.getGyomuSb().equals(Constants.GYOMU_SB_UNSO)) {
				ninMember = memberListSearchService.getMemberByCd(trailCompanyCd);
			}

//			ninMember = memberListSearchService.getMemberByCd(ankenDetail.getNinushiCd());

			//UserEntity unso = personalService.getUserByCd(ankenDetail.getUnsoCd());
			MemberEntity unsoMember = memberListSearchService.getMemberByCd(ankenDetail.getUnsoCd());

			UserEntity ninushi = personalService.getUserByCd(ankenDetail.getCreateId());

			TruckEntity truck = ankenDetailForm.getTruck();

			truck.setKosokuKbn(Constants.KOSOKU_KBN_0.equals(truck.getKosokuKbn()) ? "込み" : Constants.KOSOKU_KBN_1.equals(truck.getKosokuKbn()) ? "別料金" : truck.getKosokuKbn());

			truck.setNenryoscKbn(Constants.NENRYOSC_KBN_0.equals(truck.getNenryoscKbn()) ? "込み" : Constants.NENRYOSC_KBN_1.equals(truck.getNenryoscKbn()) ? "別料金" : truck.getNenryoscKbn());

			String fee = "";

			String yosanMnStr = StringUtils.isEmpty(truck.getYosanMn()) ? "0.00" : truck.getYosanMn();
			String KosokuMnStr = StringUtils.isEmpty(ankenDetail.getKosokuMn()) ? "0.00" : ankenDetail.getKosokuMn();

			// 運賃
			// 【荷主、TRAIL様】が印刷する場合、予算金額
			// 【運送】が印刷する場合、受注金額
			String fare = "";
			if (userSession.getGyomuSb().equals(Constants.GYOMU_SB_TRAIL) || userSession.getGyomuSb().equals(Constants.GYOMU_SB_NINUSHI)) {
				fare = StringUtils.isEmpty(truck.getYosanMn()) ? "0" : truck.getYosanMn();
			} else if(userSession.getGyomuSb().equals(Constants.GYOMU_SB_UNSO)) {
				fare = StringUtils.isEmpty(truck.getOrderMn()) ? "0" : truck.getOrderMn();
			}

			fee = String.valueOf(Math.round(Double.parseDouble(fare)  * Double.parseDouble(consumptionTax)));

			if (ankenDetailForm.getSyukaList() == null) {

				List<SyukaEntity> syukaList = new ArrayList<SyukaEntity>();
				syukaList.add(new SyukaEntity());
				syukaList.add(new SyukaEntity());
				syukaList.add(new SyukaEntity());
				syukaList.add(new SyukaEntity());
				ankenDetailForm.setSyukaList(syukaList);

			} else if (ankenDetailForm.getSyukaList().size() < 3) {
				int size = ankenDetailForm.getSyukaList().size();
				for (int i = 0; i < 3 - size; i++) {
					ankenDetailForm.getSyukaList().add(new SyukaEntity());
				}
			}

			SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMdd", Locale.JAPANESE);
			SimpleDateFormat sdfd = new SimpleDateFormat("yyyy/MM/dd(E)", Locale.JAPANESE);
			for(SyukaEntity syuka : ankenDetailForm.getSyukaList()) {
				if (!StringUtils.isEmpty(syuka.getSyukaDay())) {
//					syuka.setSyukaDay(sdfd.format(sdfs.parse(syuka.getSyukaDay())));
					syuka.setSyukaDay(syuka.getSyukaDay());
				}
			}

			if (ankenDetailForm.getNohinList() == null) {

				List<NohinEntity> nohinList = new ArrayList<NohinEntity>();
				nohinList.add(new NohinEntity());
				nohinList.add(new NohinEntity());
				nohinList.add(new NohinEntity());
				nohinList.add(new NohinEntity());
				ankenDetailForm.setNohinList(nohinList);

			} else if (ankenDetailForm.getNohinList().size() < 3) {
				int size = ankenDetailForm.getNohinList().size();
				for (int i = 0; i < 3 - size; i++) {
					ankenDetailForm.getNohinList().add(new NohinEntity());
				}
			}

			for(NohinEntity nohin : ankenDetailForm.getNohinList()) {
				if (!StringUtils.isEmpty(nohin.getNohinDay())) {
//					nohin.setNohinDay(sdfd.format(sdfs.parse(nohin.getNohinDay())));
					nohin.setNohinDay(nohin.getNohinDay());
				}
			}
			//車種
			//≪案件受注情報≫.トラックNO、≪トラック≫車種コード
//			TruckEntity truck = truckDao.getTruckByPK(ankenDetailForm.getAnkenDetail().getAnkenId(), ankenDetailForm.getAnkenDetail().getTruckNo());

			// 受託時記載事項
			// 【荷主】が印刷する場合、TRAIL様の会社情報を印刷する(TRAIL様の会社コードは設定ファイルで指定する）
			// 【運送、TRAIL様】が印刷する場合、≪案件情報≫の「運送会社コード」の会社情報を印刷する
			MemberEntity agMember = new MemberEntity();
			if (userSession.getGyomuSb().equals(Constants.GYOMU_SB_TRAIL) || userSession.getGyomuSb().equals(Constants.GYOMU_SB_UNSO)) {
				agMember = this.memberListSearchService.getMemberByCd(ankenDetail.getUnsoCd());
			} else if(userSession.getGyomuSb().equals(Constants.GYOMU_SB_NINUSHI)) {
				agMember = this.memberListSearchService.getMemberByCd(trailCompanyCd);
			}

			Map resultMap = new HashMap();
			resultMap.put("ninushi", ninushi);
			resultMap.put("ninMember", ninMember);
			resultMap.put("unsoMember", unsoMember);
			resultMap.put("anken", ankenDetailForm);
			resultMap.put("ankenDetail", ankenDetailForm.getAnkenDetail());
			resultMap.put("kosokuMn", def.format(ankenDetailForm.getAnkenDetail() != null && !StringUtils.isEmpty(ankenDetailForm.getAnkenDetail().getKosokuMn()) ? Long.parseLong(ankenDetailForm.getAnkenDetail().getKosokuMn()) : 0));
			resultMap.put("truck", truck);
			resultMap.put("fare", def.format(Long.parseLong(fare)));
			resultMap.put("fee", def.format(Long.parseLong(fee)));
			resultMap.put("agMember", agMember);

			String outputFile = exportPath + File.separator + fileName;
			PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
			pdfGenerator.generate(Freemarker.getTemplateString(Constants.FTL_PACKAGE, Constants.TEPLATE_WAYBILL, resultMap)
					, outputFile);
			response.setContentType("application/pdf;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			java.io.BufferedInputStream bis = null;
			java.io.BufferedOutputStream bos = null;

			try {
				long fileLength = new File(outputFile).length();
				response.setContentType("application/pdf;charset=utf-8");
				response.setHeader("Content-disposition", "filename="
						+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
				bis = new BufferedInputStream(new FileInputStream(outputFile));
				bos = new BufferedOutputStream(response.getOutputStream());
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
					bos.close();
			}
		}

		return null;
	}
}
