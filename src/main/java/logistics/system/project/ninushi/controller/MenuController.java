package logistics.system.project.ninushi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.form.AnkenSearchForm;
import logistics.system.project.common.form.AnkenTorokuForm;
import logistics.system.project.common.service.AddressRRKService;
import logistics.system.project.common.service.ContactRRKService;
import logistics.system.project.ninushi.form.NohinForm;
import logistics.system.project.ninushi.form.SyukaForm;
import logistics.system.project.ninushi.form.TruckForm;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController extends BaseController {

		@RequestMapping(value="createcase")
		public ModelAndView modifyAnken(
				@ModelAttribute("ankenTorokuForm") AnkenTorokuForm ankenTorokuForm,
				HttpServletRequest request){			
			HashMap<String, Object> results = new HashMap<String, Object>();
			this.setHeader(new String[]{Constants.NINUSHI_ANKEN_TOROKU_TABTITLE, Constants.NINUSHI_ANKEN_TOROKU_PAGETITLE}, results);
			
//			String companyCd = null;
//			if(super.getUserSession() != null) {
//				companyCd = super.getUserSession().getCompanyCd();
//			}
		
			boolean updateFlag = request.getParameter("updateFlag") == null ? false : "true".equalsIgnoreCase(request.getParameter("updateFlag"));
			
//			List<NisyuEntity> nisyuList = shukaAreaService.getAllNisyuList();			
//			List<NisugateEntity> nisugateList = shukaAreaService.getAllNisugateList();			
//			List<PrefEntity> prefList = shukaAreaService.getAllPrefList();			
//			List<SyasyuEntity> syasyuList = ankenListSearchService.getSyasyuList();			
//			List<TruckOpEntity> truckOpList = ankenListSearchService.getTruckOpList();
			
			List<NisyuEntity> nisyuList = Constants.MAST_NISYU_LIST;			
			List<NisugateEntity> nisugateList = Constants.MAST_NISUGATE_LIST;			
			List<PrefEntity> prefList = Constants.MAST_PREF_LIST;			
			List<SyasyuEntity> syasyuList = Constants.MAST_SYASYU_LIST;			
			List<TruckOpEntity> truckOpList = Constants.MAST_TRUCKOP_LIST;
			
//			List<AddressRRKEntity> addressRRKList = adressRRKService.getAddressRRKList(companyCd, null);
//			
//			List<ContactRRKEntity> contactRRKList = contactRRKService.getContactRRKList(companyCd, null);
			
//			ankenTorokuForm.setAddressRRKList(addressRRKList);
//			ankenTorokuForm.setContactRRKList(contactRRKList);
						
			ankenTorokuForm.setPrefList(prefList);
			ankenTorokuForm.setNisyuList(nisyuList);			
			ankenTorokuForm.setNisugateList(nisugateList);	
			ankenTorokuForm.setSyasyuList(syasyuList);
			ankenTorokuForm.setTruckOpList(truckOpList);
			
			if (!updateFlag) {
//				StringBuffer startSeq = new StringBuffer();
////				UserEntity userEntity = (UserEntity) session.getAttribute("user");
////				startSeq = startSeq.append(userEntity.getCompanyCd());
//				startSeq = startSeq.append(this.userSession.getCompanyCd());
//				String sysData = ComUtils.getSysDate().substring(2);
//				startSeq = startSeq.append(sysData);
//				String maxSeq = ankenDetailService.getMaxSeqNo(startSeq.toString());
//				int nowSeq = maxSeq == null ? 1 : Integer.valueOf(maxSeq)+1;
//				ankenTorokuForm.setAnkenNo(userEntity.getCompanyCd() + Constants.CONNECT_DASH +sysData + String.format("%03d", nowSeq) + "1"+ Constants.CONNECT_DASH+ "01");
				ankenTorokuForm.setAnkenNo(StringUtils.EMPTY);
//				ankenTorokuForm.setAnkenId(userEntity.getCompanyCd() + sysData + String.format("%03d", nowSeq));
				ankenTorokuForm.setAnkenId(StringUtils.EMPTY);
				ankenTorokuForm.setAnkenStatus("10");
				ankenTorokuForm.setAnkenStatusNm("未確定");
				
				// 集荷先
				List<SyukaForm> syukaList = new ArrayList<SyukaForm>();
				SyukaForm syukaForm = new SyukaForm();
				syukaList.add(syukaForm);
				ankenTorokuForm.setSyukaList(syukaList);
				
				List<NohinForm> nohinList = new ArrayList<NohinForm>();
				NohinForm nohinForm = new NohinForm();
				nohinList.add(nohinForm);
				ankenTorokuForm.setNohinList(nohinList);	
				
				List<TruckForm> truckList = new ArrayList<TruckForm>();
				TruckForm truckForm = new TruckForm();
				truckList.add(truckForm);
				ankenTorokuForm.setTruckList(truckList);
			}
			request.setAttribute("updateFlag", updateFlag);
			return new ModelAndView("anken_edit", results);
		}	
		
		
		@RequestMapping(value="uncertain")
		public ModelAndView uncertain(HttpServletRequest request) {
			
			String exceedFlag = request.getParameter("exceed");
			
			AnkenSearchForm ankenSearchForm = new AnkenSearchForm();
			
			ankenSearchForm.setAnkenStatusList(new String[]{Constants.ANKEN_STATUS_10});
			ankenSearchForm.setSyukaDayFr(ComUtils.getSysJapanDate());
			if("true".equalsIgnoreCase(exceedFlag)) {
				String currentDate = ComUtils.getSysDate();				
				ankenSearchForm.setJutuKg(currentDate);;
			}
			session.setAttribute("ankenSearchForm", ankenSearchForm);	
			
			return new ModelAndView("redirect:ankenList", null);
		}
		
		@RequestMapping(value="certain", method = RequestMethod.GET)
		public ModelAndView certain() {

			AnkenSearchForm ankenSearchForm = new AnkenSearchForm();
			
			ankenSearchForm.setAnkenStatusList(new String[]{Constants.ANKEN_STATUS_20, Constants.ANKEN_STATUS_30});
			ankenSearchForm.setSyukaDayFr(ComUtils.getSysJapanDate());

			session.setAttribute("ankenSearchForm", ankenSearchForm);			
			return new ModelAndView("redirect:ankenList", null);
		}		
		
		@RequestMapping(value="syabanMinyuroku", method = RequestMethod.GET)
		public ModelAndView syabanMinyuroku() {


			AnkenSearchForm ankenSearchForm = new AnkenSearchForm();
			
			ankenSearchForm.setAnkenStatusList(new String[]{Constants.ANKEN_STATUS_20});
			ankenSearchForm.setSyukaDayFr(ComUtils.getSysJapanDate());

			session.setAttribute("ankenSearchForm", ankenSearchForm);			
			return new ModelAndView("redirect:ankenList", null);
		}
		
		@Autowired
		@Qualifier("adressRRKService")
		private AddressRRKService adressRRKService;
		
		@Autowired
		@Qualifier("contactRRKService")
		private ContactRRKService contactRRKService;
		
//		@Autowired
//		@Qualifier("areaService")
//		private ShukaAreaService shukaAreaService;
		
//		@Autowired
//		@Qualifier("ankenDetailService")
//		private AnkenDetailService ankenDetailService;
		
//		@Autowired
//		@Qualifier("ankenListSearchService")
//		private AnkenListSearchService ankenListSearchService;
}
