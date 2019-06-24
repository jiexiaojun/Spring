package com.allen.demo.service.impl;

import com.allen.demo.service.INamedService;
import com.allen.demo.service.IService;
import com.allen.framework.annotation.GPAutowired;
import com.allen.framework.annotation.GPService;

@GPService("myName")
public class NamedServiceImpl implements INamedService{

	@GPAutowired IService service;
	
}
