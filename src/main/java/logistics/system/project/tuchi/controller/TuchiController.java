package logistics.system.project.tuchi.controller;

import java.util.HashMap;

import logistics.system.project.base.controller.BaseController;
import logistics.system.project.common.service.AnkenModifyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TuchiController extends BaseController {


	@RequestMapping(value = "tuchi_debug01")
	public ModelAndView debug01(){
		return new ModelAndView("tuchi/debug");
	}

	@RequestMapping(value = "tuchi_debug02")
	public ModelAndView debug02(){
		HashMap<String, Object> results = new HashMap<String, Object>();

		results.put("data", "this is debug02");

		return new ModelAndView("tuchi/debug" , results);
	}

	@RequestMapping(value = "tuchi_list")
	public ModelAndView list(){
		HashMap<String, Object> results = new HashMap<String, Object>();



		results.put("data", "this is list");

		return new ModelAndView("tuchi/debug" , results);
	}

	@Autowired
	@Qualifier("tuchiService")
	private AnkenModifyService tuchiService;


}
