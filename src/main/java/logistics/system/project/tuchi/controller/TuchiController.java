package logistics.system.project.tuchi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.CityEntity;
import logistics.system.project.common.Entity.SyasyuEntity;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.service.ShukaAreaService;
import logistics.system.project.tuchi.Entity.TuchiEntity;
import logistics.system.project.tuchi.dao.RelationDao;
import logistics.system.project.tuchi.dao.TuchiDao;
import logistics.system.project.tuchi.form.TuchiEditForm;
import logistics.system.project.tuchi.service.TuchiService;
import logistics.system.project.utility.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TuchiController extends BaseController {

	HashMap<String, Object> results;

	protected void clearResults() {
		this.results = new HashMap<>();
	}


	@RequestMapping(value = "tuchi_list")
	public ModelAndView list() {

		clearResults();

		List<TuchiEntity> l = tuchiService.getTuchiByUser(null);

		results.put("list", l);

		results.put("prefList", Constants.getPrefList());

		return new ModelAndView("tuchi/list", results);
	}

	@RequestMapping(value = "tuchi_add")
	public ModelAndView Add() {
		clearResults();

		TuchiEntity e = new TuchiEntity();
		e.setTitle("新規作成");
		e.setUserId(userSession.getUserId());

		setData(e);

		return new ModelAndView("tuchi/edit", results);
	}

	@RequestMapping(value = "tuchi_post", method = RequestMethod.POST)
	public String AddPost(@ModelAttribute("tuchiEditForm") TuchiEditForm form) {
		clearResults();

		TuchiEntity e = new TuchiEntity();
		form.initEntity(e);

		tuchiService.save(e);

		// setData(e);

		return "redirect:tuchi_list";
	}

	@RequestMapping(value = "tuchi_edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		clearResults();

		String id = request.getParameter("tuchiId");

		TuchiEntity e = tuchiService.getTuchiById(Integer.parseInt(id) , true);

		setData(e);

		return new ModelAndView("tuchi/edit", results);
	}

	protected List<Object> getTruckOpData(TuchiEntity e) {

		List<Object> list = new ArrayList<>();

		List<String> values = e.getTruckOp();

		if( values == null ){
			values = new ArrayList<String>();
		}

		for (TruckOpEntity op : Constants.getTruckOpList()) {
			HashMap<String, Object> op2 = new HashMap<>();
			op2.put("opName", op.getOpName());
			op2.put("opCd", op.getOpCd());
			op2.put("value", values.contains(op.getOpCd()));
			list.add(op2);
		}

		return list;
	}

	protected List<Object> getSyasyuData(TuchiEntity e) {

		List<Object> list = new ArrayList<>();

		List<String> values = e.getSyasyu();

		if( values == null ){
			values = new ArrayList<String>();
		}

		for (SyasyuEntity syasyu : Constants.getSyasyuList()) {
			HashMap<String, Object> op2 = new HashMap<>();
			op2.put("syasyuCd", syasyu.getSyasyuCd());
			op2.put("syasyuName", syasyu.getSyasyuName());
			op2.put("value", values.contains(syasyu.getSyasyuCd()));
			list.add(op2);
		}

		return list;
	}

	protected void setData(TuchiEntity e) {
		results.put("tuchi", e);
		results.put("prefList", Constants.getPrefList());
		results.put("truckOp", getTruckOpData(e));
		results.put("syasyu", getSyasyuData(e));
		results.put("cityList", Constants.getCityList());
	}

	@RequestMapping(value = "tuchi_debug")
	public ModelAndView debug(){
		clearResults();

		List<CityEntity> cities = Constants.getCityList();

		results.put( "data",cities.size() );


		return new ModelAndView("tuchi/debug",results);
	}

	@Autowired
	@Qualifier("tuchiService")
	private TuchiService tuchiService;


	@Autowired
	@Qualifier("tuchiDao")
	private TuchiDao tuchiDao;

}
