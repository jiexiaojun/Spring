package com.allen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.allen.model.District;
import com.allen.service.DistrictService;
import com.allen.utils.Result;

@Controller
@RequestMapping("/districtManger")
public class DistrictController {

	@Autowired
	DistrictService districtService;

	@ResponseBody
	@RequestMapping("/create")
	public Result create(District test) throws Exception {
		return districtService.create(test);
	}

	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
	public Result getlist(District test) throws Exception {
		return districtService.selectByPrimaryKey(test);
	}



	@ResponseBody
	@RequestMapping("/testTx")
	public Result testTx(District test) throws Exception {
		return districtService.testTx(test);
	}


}
