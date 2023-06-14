package com.example.team.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.team.dto.RestVO;
import com.example.team.mapper.RestMapper;

public class RestServiceImpl implements RestService {

	@Autowired
	RestMapper restMapper;

	@Override
	public void registerRSTR(RestVO rest) {
		restMapper.registerRSTR(rest);

	}

}
