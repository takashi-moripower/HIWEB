package logistics.system.project.common.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.AddressRRKEntity;
import logistics.system.project.common.Entity.ContactRRKEntity;
import logistics.system.project.common.Entity.NisugateEntity;
import logistics.system.project.common.Entity.NisyuEntity;
import logistics.system.project.common.Entity.PrefEntity;
import logistics.system.project.common.service.AddressRRKService;
import logistics.system.project.common.service.ContactRRKService;
import logistics.system.project.utility.ComUtils;
import logistics.system.project.utility.Constants;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonAreaController extends BaseController {

		@RequestMapping(value="common/addressrrk", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public ModelAndView getAdressRRKList(String companyCd, String addressKbn, HttpServletResponse response) {

			if(super.getUserSession() != null) {
				companyCd = super.getUserSession().getCompanyCd();
			}
			List<AddressRRKEntity> addressList = adressRRKService.getAddressRRKList(companyCd, addressKbn);

			for(AddressRRKEntity address : addressList) {
				address.setPostCode(ComUtils.formatPostCode(address.getPostCode()));
			}

			JSONArray json = JSONArray.fromObject(addressList);
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


		@RequestMapping(value="common/contactrrk", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public ModelAndView getContactRRKList(String companyCd, String addressKbn, HttpServletResponse response) {

			if(super.getUserSession() != null) {
				companyCd = super.getUserSession().getCompanyCd();
			}
			List<ContactRRKEntity> contactList = contactRRKService.getContactRRKList(companyCd, addressKbn);

			JSONArray json = JSONArray.fromObject(contactList);
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

		@RequestMapping(value="common/prefList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public ModelAndView getAllPrefList(HttpServletResponse response) {

			List<PrefEntity> preList = Constants.getPrefList();

			JSONArray json = JSONArray.fromObject(preList);
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

		@RequestMapping(value="common/nisugateList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public ModelAndView getAllNisugateList(HttpServletResponse response) {

			List<NisugateEntity> nisugateList = Constants.getNisutagaeList();

			JSONArray json = JSONArray.fromObject(nisugateList);
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

		@RequestMapping(value="common/nisyuList", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public ModelAndView getAllNisyuList(HttpServletResponse response) {

			List<NisyuEntity> nisyuList = Constants.getNisyuList();

			JSONArray json = JSONArray.fromObject(nisyuList);
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

		@RequestMapping(value="deleteContactById", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public ModelAndView deleteContactById(String rrkId, HttpServletResponse response) {

			String companyCd = this.userSession.getCompanyCd();
			int count = contactRRKService.deleteContactById(rrkId, companyCd);
            response.setCharacterEncoding("utf-8");

            try {
				response.getWriter().print(count);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            return null;
		}

		@RequestMapping(value="deleteAddrById", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
		public ModelAndView deleteAddrById(String rrkId, HttpServletResponse response) {

			String companyCd = this.userSession.getCompanyCd();
			int count = adressRRKService.deleteAddrById(rrkId, companyCd);
            response.setCharacterEncoding("utf-8");

            try {
				response.getWriter().print(count);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            return null;
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
}
