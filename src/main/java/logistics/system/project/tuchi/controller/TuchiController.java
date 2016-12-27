package logistics.system.project.tuchi.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.Entity.TruckOpEntity;
import logistics.system.project.common.form.AnkenSearchForm;
import logistics.system.project.tuchi.Entity.TuchiEntity;
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

	@RequestMapping(value = "tuchi_list")
	public ModelAndView list() {
		HashMap<String, Object> results = new HashMap<String, Object>();

		String data;

		TuchiEntity t = tuchiService.getTuchiByCd(1);

		List<TuchiEntity> l = tuchiService.getTuchiByUser(null);

		if (l == null) {
			data = "null";
		} else {
			data = String.valueOf(l.size());
		}

		results.put("data", data);

		results.put("list", l);
		return new ModelAndView("tuchi/list", results);
	}

	@RequestMapping(value = "tuchi_add")
	public ModelAndView Add() {
		HashMap<String, Object> results = new HashMap<String, Object>();

		setConstants(results);

		TuchiEntity e = new TuchiEntity();
		e.setTitle("新規作成");

		results.put("tuchi", e);

		return new ModelAndView("tuchi/edit", results);
	}

	@RequestMapping(value = "tuchi_add", method = RequestMethod.POST)
	public ModelAndView AddPost(@ModelAttribute("tuchiEditForm") TuchiEditForm form) {
		HashMap<String, Object> results = new HashMap<String, Object>();

		setConstants(results);

		TuchiEntity e = new TuchiEntity();
		form.initEntity( e ) ;

		String[] op0 = {"01","02","03"};
		e.setTruckOp( op0 );


		List<Object> list = new ArrayList<>();

		for( TruckOpEntity op : Constants.MAST_TRUCKOP_LIST ){
			HashMap<String,Object> op2 = new HashMap<>();
			op2.put("opName",op.getOpName());
			op2.put("opCd", op.getOpCd());
			op2.put("value",Arrays.asList(  e.getTruckOp() ).contains( op.getOpCd() ) );
			list.add( op2 );
		}

		results.put("truckOp", list);

		results.put("tuchi", e);

		return new ModelAndView("tuchi/edit", results);
	}

	protected void setConstants(HashMap<String, Object> results) {
		results.put("truckOp", Constants.MAST_TRUCKOP_LIST);
	}

	@Autowired
	@Qualifier("tuchiService")
	private TuchiService tuchiService;

}
